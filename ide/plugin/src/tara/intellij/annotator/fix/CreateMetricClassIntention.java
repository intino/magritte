package tara.intellij.annotator.fix;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.impl.PsiManagerImpl;
import com.intellij.psi.impl.file.PsiDirectoryImpl;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import tara.intellij.codeinsight.languageinjection.helpers.Format;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.TaraRule;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;
import tara.intellij.project.module.ModuleProvider;
import tara.lang.model.Primitive;
import tara.lang.model.Rule;
import tara.lang.model.Variable;

import java.util.HashMap;
import java.util.Map;

public class CreateMetricClassIntention extends ClassCreationIntention {

	private static final String RULES_PACKAGE = ".rules";
	private final Rule rule;
	private final String rulesPath;
	private final Variable variable;

	public CreateMetricClassIntention(Rule rule) {
		this.rule = rule;
		this.variable = TaraPsiImplUtil.getContainerByType((TaraRule) rule, Variable.class);
		final TaraFacet facet = TaraFacet.of(ModuleProvider.getModuleOf((TaraRule) rule));
		if (facet == null) this.rulesPath = RULES_PACKAGE;
		else {
			final TaraFacetConfiguration configuration = facet.getConfiguration();
			this.rulesPath = configuration.outputDsl().toLowerCase() + RULES_PACKAGE;
		}
	}

	@NotNull
	@Override
	public String getText() {
		return "Create metric class " + Format.javaValidName().format(((TaraRule) rule).getText());
	}

	@NotNull
	@Override
	public String getFamilyName() {
		return "Create metric class";
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		return element.getContainingFile() instanceof TaraModel;
	}

	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		final PsiFile file = element.getContainingFile();
		VirtualFile srcDirectory = getSrcDirectory(TaraUtil.getSourceRoots(file));
		PsiDirectoryImpl srcPsiDirectory = new PsiDirectoryImpl((PsiManagerImpl) file.getManager(), srcDirectory);
		PsiClass aClass = createRuleClass(file, srcPsiDirectory);
		if (aClass != null) aClass.navigate(true);
	}

	public PsiClass createRuleClass(PsiFile file, PsiDirectoryImpl srcPsiDirectory) {
		PsiClass aClass;
		PsiDirectory destiny = findDestiny(file, srcPsiDirectory, rulesPath);
		aClass = createClass(destiny, ((TaraRule) rule).getText());
		return aClass;
	}

	public PsiClass createClass(PsiDirectory destiny, String className) {
		PsiFile file = destiny.findFile(className + ".java");
		if (file != null) return null;
		Map<String, String> additionalProperties = new HashMap<>();
		additionalProperties.put("TYPE", getRuleType());
		return JavaDirectoryService.getInstance().createClass(destiny, className, "MetricClass", true, additionalProperties);
	}

	public String getRuleType() {
		if (variable.type().equals(Primitive.WORD)) return "Enum";
		if (variable.type().equals(Primitive.RESOURCE)) return "java.io.File";
		return variable.type().javaName();
	}

	@Override
	public boolean startInWriteAction() {
		return true;
	}
}
