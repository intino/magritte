package tara.intellij.codeinsight.intentions;

import com.intellij.ide.util.DirectoryUtil;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.impl.file.PsiDirectoryImpl;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.annotator.fix.ClassCreationIntention;
import tara.intellij.codeinsight.languageinjection.helpers.Format;
import tara.intellij.lang.psi.Expression;
import tara.intellij.lang.psi.TaraElementFactory;
import tara.intellij.lang.psi.TaraExpression;
import tara.intellij.lang.psi.Valued;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;
import tara.intellij.project.module.ModuleProvider;
import tara.lang.model.Node;
import tara.lang.model.Primitive;
import tara.lang.model.rules.variable.NativeRule;

import java.util.*;

import static tara.intellij.codeinsight.languageinjection.NativeFormatter.buildContainerPath;
import static tara.intellij.codeinsight.languageinjection.helpers.QualifiedNameFormatter.cleanQn;
import static tara.intellij.lang.LanguageManager.getLanguage;
import static tara.intellij.lang.psi.impl.TaraPsiImplUtil.getContainerNodeOf;

public class MoveToNativePackage extends ClassCreationIntention {
	private static final String NATIVES = "natives";

	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		final Expression expression = expressionContext(element);
		final Node node = getContainerNodeOf(element);
		if (node == null) return;
		final Valued valued = TaraPsiImplUtil.getContainerByType(expression, Valued.class);
		final PsiClass aClass = createClass(findDestiny(cleanQn(node.qualifiedName()), expression), valued, expression);
		if (aClass == null) return;
		substituteByReference(aClass, expression);
		aClass.navigate(true);
	}

	public PsiClass createClass(PsiDirectory destiny, Valued valued, Expression value) {
		final Module module = ModuleProvider.getModuleOf(value);
		final TaraFacetConfiguration conf = TaraUtil.getFacetConfiguration(module);
		PsiFile file = destiny.findFile(Format.firstUpperCase().format(valued.name()).toString() + ".java");
		if (file != null) return null;
		return JavaDirectoryService.getInstance().createClass(destiny, Format.firstUpperCase().format(valued.name()).toString(), "NativeClass", true, fields(valued, value, module, conf));
	}

	private void substituteByReference(PsiClass psiClass, Expression expression) {
		final TaraExpression newExpression = TaraElementFactory.getInstance(psiClass.getProject()).createExpression(psiClass.getQualifiedName() + "." + psiClass.getMethods()[0].getName() + "($);");
		expression.replace(newExpression);
	}

	public Map<String, String> fields(Valued valued, Expression value, Module module, TaraFacetConfiguration conf) {
		Map<String, String> properties = new HashMap<>();
		properties.put("NAME", Format.firstUpperCase().format(valued.name()).toString());
		properties.put("VALUE", value.getValue());
		properties.put("SCOPE", cleanQn(buildContainerPath((NativeRule) valued.rule(), getContainerNodeOf(value), getLanguage(module), conf.outputDsl())));
		properties.put("RETURN", !Primitive.FUNCTION.equals(valued.type()) ? valued.type().javaName() : getReturnType(valued, module, conf));
		return properties;
	}

	private String getReturnType(Valued valued, Module module, TaraFacetConfiguration conf) {
		final String genLanguage = conf.outputDsl().isEmpty() ? module.getName() : conf.outputDsl();
		final PsiClass aClass = JavaPsiFacade.getInstance(valued.getProject()).findClass(genLanguage.toLowerCase() + ".functions." + ((NativeRule) valued.rule()).interfaceClass(), GlobalSearchScope.allScope(module.getProject()));
		if (aClass == null || !aClass.isInterface()) return "void";
		return aClass.getMethods()[0].getReturnType().getPresentableText();
	}

	private PsiDirectory findDestiny(String nodePath, Expression expression) {
		final PsiDirectory srcDirectory = new PsiDirectoryImpl((com.intellij.psi.impl.PsiManagerImpl) expression.getManager(), TaraUtil.getSrcRoot(TaraUtil.getSourceRoots(expression)));
		final TaraFacet facet = TaraFacet.of(ModuleProvider.getModuleOf(expression));
		if (facet == null) return null;
		final TaraFacetConfiguration configuration = facet.getConfiguration();
		List<String> path = new ArrayList<>(Arrays.asList(configuration.outputDsl().toLowerCase(), NATIVES));
		Collections.addAll(path, nodePath.split("\\."));
		return DirectoryUtil.createSubdirectories(String.join(DOT, path).toLowerCase(), srcDirectory, DOT);
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		return expressionContext(element) != null;
	}

	public Expression expressionContext(@NotNull PsiElement element) {
		return TaraPsiImplUtil.getContainerByType(element, Expression.class);
	}

	@Nls
	@NotNull
	@Override
	public String getText() {
		return "Change to external class reference";
	}

	@Nls
	@NotNull
	@Override
	public String getFamilyName() {
		return getText();
	}

}
