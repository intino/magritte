package tara.intellij.codeinsight.languageinjection;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaDirectoryService;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.file.PsiDirectoryImpl;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.annotator.fix.ClassCreationIntention;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.lang.psi.impl.TaraVariableImpl;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;
import tara.intellij.project.module.ModuleProvider;
import tara.lang.model.Variable;
import tara.lang.model.rules.variable.NativeRule;

public class CreateNativeClassIntention extends ClassCreationIntention {

	private static final Logger LOG = Logger.getInstance(CreateNativeClassIntention.class.getName());
	private static final String FUNCTIONS = "functions";
	private final PsiDirectory srcDirectory;
	private final Module module;
	private final Variable variable;
	private PsiDirectory destiny;

	public CreateNativeClassIntention(Variable variable) {
		this.variable = variable;
		this.srcDirectory = new PsiDirectoryImpl((com.intellij.psi.impl.PsiManagerImpl) ((PsiElement) variable).getManager(), TaraUtil.getSrcRoot(TaraUtil.getSourceRoots((PsiElement) variable)));
		this.module = ModuleProvider.getModuleOf((PsiElement) variable);
		this.destiny = findNativesDirectory();
	}

	@Nls
	@NotNull
	@Override
	public String getText() {
		return "Create native interface";
	}

	@Nls
	@NotNull
	@Override
	public String getFamilyName() {
		return getText();
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		return element.getContainingFile() instanceof TaraModel && destiny != null;
	}

	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		final PsiClass nativeClass = createNativeClass();
		if (nativeClass != null) nativeClass.navigate(true);
	}

	@Override
	public boolean startInWriteAction() {
		return true;
	}

	private PsiClass createNativeClass() {
		PsiClass aClass = (PsiClass) ReferenceManager.resolveRule(((TaraVariableImpl) variable).getRule());
		if (aClass == null) {
			aClass = JavaDirectoryService.getInstance().createInterface(destiny, ((NativeRule) variable.rule()).interfaceClass());
			aClass.getModifierList().addAnnotation("java.lang.FunctionalInterface");
		}
		return aClass;
	}

	private PsiDirectory findNativesDirectory() {
		final TaraFacet facet = TaraFacet.of(module);
		if (facet == null) return null;
		final TaraFacetConfiguration configuration = facet.getConfiguration();
		String[] path = new String[]{configuration.getGeneratedDslName().toLowerCase(), FUNCTIONS};
		PsiDirectory destinyDir = srcDirectory;
		for (String name : path)
			destinyDir = destinyDir.findSubdirectory(name) == null ? createDirectory(destinyDir, name) : destinyDir.findSubdirectory(name);
		return destinyDir;
	}



}
