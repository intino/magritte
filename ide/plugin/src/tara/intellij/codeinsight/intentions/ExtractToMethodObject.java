package tara.intellij.codeinsight.intentions;

import com.intellij.ide.util.DirectoryUtil;
import com.intellij.notification.Notification;
import com.intellij.notification.Notifications;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.annotator.fix.ClassCreationIntention;
import tara.intellij.codeinsight.languageinjection.helpers.Format;
import tara.intellij.codeinsight.languageinjection.imports.Imports;
import tara.intellij.lang.psi.Expression;
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

import static com.intellij.notification.NotificationType.ERROR;
import static tara.intellij.codeinsight.languageinjection.NativeFormatter.buildContainerPath;
import static tara.intellij.codeinsight.languageinjection.helpers.QualifiedNameFormatter.cleanQn;
import static tara.intellij.codeinsight.languageinjection.helpers.QualifiedNameFormatter.qnOf;
import static tara.intellij.lang.LanguageManager.getLanguage;
import static tara.intellij.lang.psi.impl.TaraPsiImplUtil.getContainerNodeOf;

public class ExtractToMethodObject extends ClassCreationIntention {
	private static final String NATIVES = "natives";
	private static final String NAME = "NAME";
	private static final String VALUE = "VALUE";
	private static final String SCOPE = "SCOPE";
	private static final String RETURN = "RETURN";

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		return expressionContext(element) != null;
	}

	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		final Expression expression = expressionContext(element);
		final Node node = getContainerNodeOf(element);
		if (node == null) return;
		final Valued valued = TaraPsiImplUtil.getContainerByType(expression, Valued.class);
		final PsiMethod method = createMethodObject(findDestiny(cleanQn(node.qualifiedName()), expression), valued, expression);
		if (method == null) return;
		PsiDocumentManager.getInstance(project).commitDocument(editor.getDocument());
		reference(method, expression, editor);
		PsiDocumentManager.getInstance(project).commitDocument(editor.getDocument());
		method.navigate(true);
	}

	private PsiMethod createMethodObject(PsiDirectory destiny, Valued valued, Expression value) {
		final Module module = ModuleProvider.getModuleOf(value);
		final TaraFacetConfiguration conf = TaraUtil.getFacetConfiguration(module);
		PsiFile file = destiny.findFile(Format.firstUpperCase().format(valued.name()).toString() + ".java");
		if (file != null) {
			if (file instanceof PsiJavaFile && ((PsiJavaFile) file).getClasses().length > 0)
				return addMethod(((PsiJavaFile) file).getClasses()[0], fields(valued, value, module, conf));
			else {
				Notifications.Bus.notify(new Notification("Tara Language", "Class already exists", Format.firstUpperCase().format(valued.name()).toString() + ".java", ERROR));
				return null;
			}
		}
		final PsiClass nativeClass = JavaDirectoryService.getInstance().createClass(destiny, Format.firstUpperCase().format(valued.name()).toString(), "NativeClass", true, fields(valued, value, module, conf));
		return nativeClass.getMethods()[0];
	}

	private PsiMethod addMethod(PsiClass aClass, Map<String, String> properties) {
		final PsiMethod method = JavaPsiFacade.getInstance(aClass.getProject()).getElementFactory().createMethodFromText(getMethodText(properties), null, LanguageLevel.JDK_1_8);
		return (PsiMethod) aClass.add(method);
	}

	private String getMethodText(Map<String, String> properties) {
		return "public static " + properties.get(RETURN) + " " + properties.get(NAME).toLowerCase() + "(" + properties.get(SCOPE) + " self) {\n" +
			properties.get(VALUE) + "\n" +
			"}\n";
	}

	private void reference(PsiMethod method, Expression expression, Editor editor) {
		final Module moduleOf = ModuleProvider.getModuleOf(expression);
		final Valued valued = TaraPsiImplUtil.getContainerByType(expression, Valued.class);
		final Expression replaced = (Expression) expression.updateText(method.getName() + "(self);");
		if (replaced.isMultiLine()) new MultilineToInline().invoke(replaced.getProject(), editor, replaced.getFirstChild());
		addStaticImport(moduleOf, valued, method.getContainingClass().getQualifiedName() + "." + method.getName());
	}

	private void addStaticImport(Module module, Valued valued, String method) {
		Imports imports = new Imports(module.getProject());
		String qn = qnOf(valued);
		final Map<String, Set<String>> map = imports.get(destiny(valued) + ".json");
		add(map, method, qn);
		imports.save(destiny(valued), qn, map.get(qn));
	}

	private void add(Map<String, Set<String>> map, String method, String qn) {
		map.putIfAbsent(qn, new HashSet<>());
		map.get(qn).add(method);
	}

	private Map<String, String> fields(Valued valued, Expression value, Module module, TaraFacetConfiguration conf) {
		Map<String, String> properties = new HashMap<>();
		properties.put(NAME, Format.firstUpperCase().format(valued.name()).toString());
		properties.put(VALUE, value.getValue());
		properties.put(SCOPE, cleanQn(buildContainerPath((NativeRule) valued.rule(), getContainerNodeOf(value), getLanguage(value.getContainingFile()), conf.outputDsl())));
		properties.put(RETURN, !Primitive.FUNCTION.equals(valued.type()) ? valued.type().javaName() : getReturnType(valued, module, conf).getPresentableText());
		return properties;
	}

	private PsiType getReturnType(Valued valued, Module module, TaraFacetConfiguration conf) {
		final String genLanguage = conf.outputDsl().isEmpty() ? module.getName() : conf.outputDsl();
		final PsiClass aClass = JavaPsiFacade.getInstance(valued.getProject()).findClass(genLanguage.toLowerCase() + ".functions." + ((NativeRule) valued.rule()).interfaceClass(), GlobalSearchScope.allScope(module.getProject()));
		if (aClass == null || !aClass.isInterface()) return PsiType.VOID;
		return aClass.getMethods()[0].getReturnType();
	}

	private PsiDirectory findDestiny(String nodePath, Expression expression) {
		final TaraFacet facet = TaraFacet.of(ModuleProvider.getModuleOf(expression));
		if (facet == null) return null;
		final TaraFacetConfiguration configuration = facet.getConfiguration();
		List<String> path = new ArrayList<>(Arrays.asList(configuration.outputDsl().toLowerCase(), NATIVES));
		Collections.addAll(path, nodePath.split("\\."));
		return DirectoryUtil.mkdirs(expression.getManager(), TaraUtil.getSrcRoot(TaraUtil.getSourceRoots(expression)).getPath() + SLASH + String.join(SLASH, path).toLowerCase());
	}

	private Expression expressionContext(@NotNull PsiElement element) {
		return TaraPsiImplUtil.getContainerByType(element, Expression.class);
	}

	@NotNull
	private String destiny(tara.intellij.lang.psi.Valued valued) {
		final String moduleName = ModuleProvider.getModuleOf(valued).getName();
		return moduleName + (TaraUtil.isDefinitionFile(valued.getContainingFile()) ? "" : "_model");
	}

	@Nls
	@NotNull
	@Override
	public String getText() {
		return "Extract to method object";
	}

	@Nls
	@NotNull
	@Override
	public String getFamilyName() {
		return getText();
	}

}
