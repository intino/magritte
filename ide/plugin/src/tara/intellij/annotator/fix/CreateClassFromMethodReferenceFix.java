package tara.intellij.annotator.fix;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.FileUtilRt;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.psi.*;
import com.intellij.psi.impl.file.PsiDirectoryImpl;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.siani.itrules.model.Frame;
import tara.Checker;
import tara.intellij.lang.psi.Identifier;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.Valued;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;
import tara.intellij.project.module.ModuleProvider;
import tara.lang.model.Parameter;
import tara.lang.model.Primitive;
import tara.lang.model.Variable;
import tara.lang.model.rules.Size;
import tara.lang.model.rules.variable.NativeRule;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.errorcollector.SemanticFatalException;
import tara.templates.MethodTemplate;

import static com.intellij.openapi.util.io.FileUtilRt.getNameWithoutExtension;
import static com.intellij.psi.search.GlobalSearchScope.allScope;
import static tara.intellij.codeinsight.languageinjection.NativeFormatter.buildContainerPath;
import static tara.intellij.codeinsight.languageinjection.helpers.QualifiedNameFormatter.cleanQn;
import static tara.intellij.lang.psi.impl.TaraPsiImplUtil.getContainerNodeOf;

public class CreateClassFromMethodReferenceFix extends ClassCreationIntention {
	private static final String ACTIONS = "actions";
	private final Valued valued;
	private final Identifier element;

	private final PsiDirectory srcDirectory;
	private final Module module;
	private final TaraFacetConfiguration conf;
	private PsiDirectory destiny;

	public CreateClassFromMethodReferenceFix(Valued valued, Identifier element) {
		this.valued = valued;
		this.element = element;
		final VirtualFile srcRoot = TaraUtil.getSrcRoot(TaraUtil.getSourceRoots(valued));
		this.srcDirectory = srcRoot == null ? null : new PsiDirectoryImpl((com.intellij.psi.impl.PsiManagerImpl) valued.getManager(), srcRoot);
		this.module = ModuleProvider.getModuleOf(valued);
		this.conf = TaraUtil.getFacetConfiguration(module);
		this.destiny = findActionsDirectory();
	}

	@Nls
	@NotNull
	@Override
	public String getText() {
		return "Create method object";
	}

	@Nls
	@NotNull
	@Override
	public String getFamilyName() {
		return getText();
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		return element.getContainingFile() instanceof TaraModel && destiny != null && srcDirectory != null;
	}

	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		final PsiMethod method = createMethodObjectClass();
		if (method != null) method.navigate(true);
	}

	private PsiMethod createMethodObjectClass() {
		PsiClass aClass = findClass();
		return addMethod(aClass != null ? aClass : JavaDirectoryService.getInstance().createClass(destiny, getNameWithoutExtension(valued.getContainingFile().getName())));
	}

	private PsiMethod addMethod(PsiClass aClass) {
		final PsiMethod method = JavaPsiFacade.getInstance(aClass.getProject()).getElementFactory().createMethodFromText(getMethodText(), null, LanguageLevel.JDK_1_8);
		return (PsiMethod) aClass.add(method);
	}

	private String getMethodText() {
		Frame frame = new Frame().addTypes("method");
		Size size = valued instanceof Parameter ? parameterSize() : ((Variable) valued).size();
		if (size != null && !size.isSingle()) frame.addTypes("multiple");
		frame.addFrame("name", element.getText());
		frame.addFrame("type", type());
		frame.addFrame("scope", cleanQn(buildContainerPath(valued.scope(), getContainerNodeOf(valued), conf.outputDsl())));
		return MethodTemplate.create().format(frame);
	}

	private Size parameterSize() {
		final Constraint.Parameter constraint = TaraUtil.getConstraint(getContainerNodeOf(valued), (Parameter) valued);
		return constraint != null ? constraint.size() : Size.MULTIPLE();
	}

	private String type() {
		if (valued.type() == null && valued instanceof Parameter) {
			try {
				new Checker(TaraUtil.getLanguage(valued)).check(getContainerNodeOf(valued).resolve());
			} catch (SemanticFatalException e) {
			}
		}
		return Primitive.FUNCTION.equals(valued.type()) ? getReturnType().getPresentableText() : valued.type().javaName();
	}

	private PsiType getReturnType() {
		final String genLanguage = conf.outputDsl().isEmpty() ? module.getName() : conf.outputDsl();
		final PsiClass aClass = JavaPsiFacade.getInstance(valued.getProject()).findClass(genLanguage.toLowerCase() + ".functions." + ((NativeRule) valued.rule()).interfaceClass(), GlobalSearchScope.allScope(module.getProject()));
		if (aClass == null || !aClass.isInterface()) return PsiType.VOID;
		return aClass.getMethods()[0].getReturnType();
	}

	private PsiClass findClass() {
		Module module = ModuleProvider.getModuleOf(element);
		final TaraFacet facet = TaraFacet.of(module);
		if (facet != null) {
			String outputDsl = facet.getConfiguration().outputDsl();
			return JavaPsiFacade.getInstance(element.getProject()).findClass(reference(outputDsl, element), allScope(module.getProject()));
		}
		return null;
	}

	@NotNull
	private String reference(String outputDsl, PsiElement element) {
		return outputDsl.toLowerCase() + ".actions." + FileUtilRt.getNameWithoutExtension(element.getContainingFile().getName());
	}

	private PsiDirectory findActionsDirectory() {
		final TaraFacet facet = TaraFacet.of(module);
		if (facet == null) return null;
		final TaraFacetConfiguration configuration = facet.getConfiguration();
		String[] path = new String[]{configuration.outputDsl().toLowerCase(), ACTIONS};
		PsiDirectory destinyDir = srcDirectory;
		if (destinyDir == null) return null;
		for (String name : path) {
			if (destinyDir == null) break;
			destinyDir = destinyDir.findSubdirectory(name) == null ? createDirectory(destinyDir, name) : destinyDir.findSubdirectory(name);
		}
		return destinyDir;
	}
}
