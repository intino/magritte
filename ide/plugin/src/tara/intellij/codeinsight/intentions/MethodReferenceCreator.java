package tara.intellij.codeinsight.intentions;

import com.intellij.openapi.module.Module;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.siani.itrules.model.Frame;
import tara.Checker;
import tara.intellij.codeinsight.languageinjection.helpers.Format;
import tara.intellij.codeinsight.languageinjection.helpers.NativeExtractor;
import tara.intellij.codeinsight.languageinjection.imports.Imports;
import tara.intellij.lang.psi.TaraRule;
import tara.intellij.lang.psi.TaraVariable;
import tara.intellij.lang.psi.Valued;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.module.ModuleProvider;
import tara.lang.model.Parameter;
import tara.lang.model.Primitive;
import tara.lang.model.Variable;
import tara.lang.model.rules.Size;
import tara.lang.model.rules.variable.NativeObjectRule;
import tara.lang.model.rules.variable.NativeRule;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.errorcollector.SemanticFatalException;
import tara.templates.MethodTemplate;

import java.util.*;
import java.util.stream.Collectors;

import static com.intellij.openapi.util.io.FileUtilRt.getNameWithoutExtension;
import static com.intellij.pom.java.LanguageLevel.JDK_1_8;
import static com.intellij.psi.search.GlobalSearchScope.allScope;
import static tara.intellij.codeinsight.languageinjection.NativeFormatter.buildContainerPath;
import static tara.intellij.codeinsight.languageinjection.helpers.QualifiedNameFormatter.cleanQn;
import static tara.intellij.codeinsight.languageinjection.helpers.QualifiedNameFormatter.qnOf;
import static tara.intellij.lang.psi.impl.TaraPsiImplUtil.getContainerNodeOf;
import static tara.intellij.lang.psi.impl.TaraUtil.importsFile;
import static tara.intellij.lang.psi.impl.TaraUtil.outputDsl;

public class MethodReferenceCreator {
	private final Valued valued;
	private final String reference;
	private final Module module;
	private final String outputDsl;

	public MethodReferenceCreator(Valued valued, String reference) {
		this.valued = valued;
		this.reference = reference;
		module = ModuleProvider.getModuleOf(valued);
		outputDsl = outputDsl(valued);

	}

	public PsiMethod create(String methodBody) {
		PsiClass aClass = findClass();
		return addMethod(aClass != null ? aClass : createClass(), methodBody);
	}

	@NotNull
	private PsiClass createClass() {
		PsiDirectory destiny = valued.getContainingFile().getParent();
		return JavaDirectoryService.getInstance().createClass(destiny, Format.javaValidName().format(getNameWithoutExtension(valued.getContainingFile().getName())).toString());
	}

	private PsiMethod addMethod(PsiClass aClass, String body) {
		final JavaPsiFacade facade = JavaPsiFacade.getInstance(aClass.getProject());
		final PsiMethod method = facade.getElementFactory().createMethodFromText(buildMethodWith(body), null, JDK_1_8);
		final PsiElement newMethod = aClass.add(method);
		addImports(aClass);
		return (PsiMethod) newMethod;
	}

	private String buildMethodWith(String methodBody) {
		Frame frame = new Frame().addTypes("method");
		Size size = valued instanceof Parameter ? parameterSize() : ((Variable) valued).size();
		final String type = type();
		if (size != null && !size.isSingle() && !"void".equals(type)) frame.addTypes("multiple");
		frame.addFrame("name", reference);
		frame.addFrame("type", type);
		final String[] parameters = findParameters();
		if (parameters.length != 0 && !parameters[0].isEmpty()) frame.addFrame("parameter", parameters);
		frame.addFrame("body", methodBody);
		frame.addFrame("scope", cleanQn(buildContainerPath(valued.scope(), getContainerNodeOf(valued), outputDsl)));
		return MethodTemplate.create().format(frame);
	}

	private String[] findParameters() {
		if (valued.type().equals(Primitive.FUNCTION)) if (valued instanceof Parameter) {
			final NativeRule rule = (NativeRule) valued.rule();
			if (rule.signature() == null || rule.signature().isEmpty()) return new String[0];
			return new String[]{new NativeExtractor(rule.interfaceClass(), valued.name(), rule.signature()).parameters()};
		} else return resolveInterfaceParameters();
		else return new String[0];
	}

	private String[] resolveInterfaceParameters() {
		final TaraRule rule = ((TaraVariable) valued).getRuleContainer().getRule();
		if (rule == null) return new String[0];
		final PsiElement reference = ReferenceManager.resolveRule(rule);
		if (!(reference instanceof PsiClass)) return new String[0];
		final PsiParameterList parameterList = ((PsiClass) reference).getAllMethods()[0].getParameterList();
		final List<String> collect = Arrays.asList(parameterList.getParameters()).stream().map(PsiParameter::getText).collect(Collectors.toList());
		return collect.toArray(new String[collect.size()]);
	}

	private Size parameterSize() {
		final Constraint.Parameter constraint = TaraUtil.parameterConstraintOf((Parameter) valued);
		return constraint != null ? constraint.size() : Size.MULTIPLE();
	}

	private String type() {
		if (valued.type() == null && valued instanceof Parameter) {
			try {
				new Checker(TaraUtil.getLanguage(valued)).check(getContainerNodeOf(valued).resolve());
			} catch (SemanticFatalException ignored) {
				return "";
			}
		}
		if (Primitive.FUNCTION.equals(valued.type())) return getFunctionReturnType().getPresentableText();
		else if (Primitive.OBJECT.equals(valued.type())) return getObjectReturnType();
		else return valued.type().javaName();
	}

	private PsiType getFunctionReturnType() {
		final PsiClass aClass = JavaPsiFacade.getInstance(valued.getProject()).findClass(valued.scope().toLowerCase() + ".functions." + ((NativeRule) valued.rule()).interfaceClass(), allScope(module.getProject()));
		if (aClass == null || !aClass.isInterface()) return PsiType.VOID;
		return aClass.getMethods()[0].getReturnType();
	}

	private String getObjectReturnType() {
		return ((NativeObjectRule) valued.rule()).type();
	}

	private PsiClass findClass() {
		Module module = ModuleProvider.getModuleOf(valued);
		final TaraFacet facet = TaraFacet.of(module);
		final JavaPsiFacade instance = JavaPsiFacade.getInstance(valued.getProject());
		return facet != null ? instance.findClass(TaraUtil.methodReference(valued), allScope(module.getProject())) : null;
	}

	private void addImports(PsiClass aClass) {
		if (valued.type().equals(Primitive.FUNCTION))
			addImports(aClass, valued instanceof Variable ? findFunctionImports() : ((NativeRule) valued.rule()).imports());
		Imports imports = new Imports(module.getProject());
		String qn = qnOf(valued);
		final Map<String, Set<String>> map = imports.get(importsFile(valued));
		if (map == null) return;
		imports.save(importsFile(valued), qn, map.get(qn));
		if (map.get(qn) == null) return;
		addImports(aClass, map.get(qn));
		map.remove(qn);
	}

	private Collection<String> findFunctionImports() {
		final String genLanguage = outputDsl.isEmpty() ? module.getName() : outputDsl;
		final PsiClass aClass = JavaPsiFacade.getInstance(valued.getProject()).findClass(genLanguage.toLowerCase() + ".functions." + ((NativeRule) valued.rule()).interfaceClass(), allScope(module.getProject()));
		if (aClass == null || !aClass.isInterface()) return Collections.emptyList();
		List<String> imports = new ArrayList<>();
		if (((PsiJavaFile) aClass.getContainingFile()).getImportList() == null) return Collections.emptyList();
		for (PsiImportStatementBase psiImportStatementBase : ((PsiJavaFile) aClass.getContainingFile()).getImportList().getAllImportStatements())
			imports.add(psiImportStatementBase.getText());
		return imports;
	}

	private void addImports(PsiClass aClass, Collection<String> imports) {
		final PsiJavaFile file = (PsiJavaFile) aClass.getContainingFile();
		for (String statement : imports)
			if (statement.contains(" static ")) addStaticImport(aClass, file, statement.split(" ")[2].replace(";", ""));
			else addOnDemandImport(aClass, file, statement.split(" ")[1].replace(";", ""));
	}

	private void addOnDemandImport(PsiClass aClass, PsiJavaFile file, String importReference) {
		final PsiClass reference = JavaPsiFacade.getInstance(valued.getProject()).findClass(importReference, allScope(module.getProject()));
		if (reference != null) {
			final PsiImportStatement importStatement = JavaPsiFacade.getElementFactory(aClass.getProject()).createImportStatement(reference);
			if (file.getImportList() != null) file.getImportList().add(importStatement);
			else file.addAfter(importStatement, file.getPackageStatement());
		}
	}

	private void addStaticImport(PsiClass aClass, PsiJavaFile file, String reference) {
		final PsiClass classReference = JavaPsiFacade.getInstance(valued.getProject()).findClass(reference.substring(0, reference.lastIndexOf(".")), allScope(module.getProject()));
		final PsiImportStaticStatement importStaticStatement = JavaPsiFacade.getElementFactory(aClass.getProject()).createImportStaticStatement(classReference, reference.substring(reference.lastIndexOf(".") + 1));
		if (file.getImportList() != null) file.getImportList().add(importStaticStatement);
		else file.addAfter(importStaticStatement.getParent().copy(), file.getPackageStatement());
	}
}
