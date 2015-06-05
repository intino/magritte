package siani.tara.intellij.codeinsight;

import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.siani.itrules.model.Frame;
import siani.tara.intellij.framework.maven.NativeTemplate;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.facet.TaraFacet;
import siani.tara.intellij.project.facet.TaraFacetConfiguration;
import siani.tara.intellij.project.module.ModuleProvider;
import siani.tara.semantic.Allow;

import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getParentByType;
import static siani.tara.semantic.model.Variable.NATIVE_SEPARATOR;

public class TaraLanguageInjector implements LanguageInjector {

	private static Parameter getParameter(StringValue stringValue) {
		PsiElement element = getParentByType(stringValue, Parameter.class);
		return element instanceof Parameter ? (Parameter) element : null;
	}

	@Override
	public void getLanguagesToInject(@NotNull PsiLanguageInjectionHost host, @NotNull InjectedLanguagePlaces injectionPlacesRegistrar) {
		if (!StringValue.class.isInstance(host) || !host.isValidHost()) return;
		injectionPlacesRegistrar.addPlace(JavaLanguage.INSTANCE, getRangeInsideHost((StringValue) host), createPrefix((StringValue) host), createSuffix());
	}

	@NotNull
	private TextRange getRangeInsideHost(@NotNull StringValue host) {
		return (!host.isMultiLine()) ? new TextRange(1, host.getTextLength() - 1) : getMultiLineBounds(host);
	}

	private TextRange getMultiLineBounds(StringValue host) {
		final String value = host.getValue();
		final int i = host.getText().indexOf(value);
		return new TextRange(i, i + value.length());
	}

	private String createPrefix(StringValue stringValue) {
		String languageName = TaraUtil.getLanguage(stringValue).languageName();
		Frame frame = createFrame(stringValue, languageName);
		return NativeTemplate.create().format(frame);
	}

	private String createSuffix() {
		return "\n\t}\n" +
			"}";
	}

	@NotNull
	private Frame createFrame(StringValue stringValue, String languageName) {
		Frame frame = new Frame().addTypes("native");
		PsiElement element = getVarInit(stringValue);
		if (element == null) element = getParameter(stringValue);
		if (element == null) element = getVariable(stringValue);
		Node node = getContainerNode(element);
		frame.addFrame("languageGenerated", getGeneratedLanguage(stringValue));
		frame.addFrame("language", languageName.toLowerCase());
		Allow.Parameter allow = TaraUtil.getCorrespondingAllow(node, element);
		if (allow != null) frame.addFrame("intention", intention(allow.contract()));
		else frame.addFrame("intention", ((Variable) element).getContract().getText());
		final String replace = stringValue.getValue().replace("\\n", "\n").replace("\\\"", "\"");
		frame.addFrame("variable", getName(element)).
			addFrame("qn", node.getQualifiedName().replace("@anonymous", "").replace(".", "_")).
			addFrame("parent", firstNamedNode(node).replace(".", "_")).
			addFrame("signature", getSignature(allow != null ? allow.contract() : findNativeInterface(((Variable) element).getContract()))).
			addFrame("body", replace.endsWith(";") ? replace : replace + ";");
		return frame;
	}

	@NotNull
	private String getGeneratedLanguage(StringValue stringValue) {
		final Module module = ModuleProvider.getModuleOf(stringValue);
		final TaraFacet facet = TaraFacet.getTaraFacetByModule(module);
		if (facet == null) return "";
		final TaraFacetConfiguration configuration = facet.getConfiguration();
		return configuration.getGeneratedDslName().toLowerCase();
	}

	private String findNativeInterface(Contract contract) {
		final PsiElement element = ReferenceManager.resolveContract(contract);
		if (element == null || !(element instanceof PsiClass)) return "";
		PsiClass aClass = (PsiClass) element;
		if (aClass.getMethods().length == 0) return "";
		final PsiMethod psiMethod = aClass.getMethods()[0];
		return psiMethod.getText().replace(";", "");

	}

	private String getSignature(String contract) {
		final String signature = contract.substring(contract.indexOf(NATIVE_SEPARATOR) + 1);
		return signature.startsWith("public ") ? signature : "public " + signature;
	}

	private String intention(String contract) {
		return contract.contains(NATIVE_SEPARATOR) ? contract.substring(0, contract.indexOf(NATIVE_SEPARATOR)) : "";
	}

	private String firstNamedNode(Node node) {
		if (node.getName() != null) return node.getQualifiedName();
		Node candidate = node;
		while (candidate.container() != null)
			candidate = candidate.container();
		return candidate.getQualifiedName();
	}

	private Node getContainerNode(PsiElement element) {
		return (Node) getParentByType(element, Node.class);
	}

	private PsiElement getVarInit(StringValue stringValue) {
		return getParentByType(stringValue, VarInit.class);
	}

	private PsiElement getVariable(StringValue stringValue) {
		PsiElement element = getParentByType(stringValue, Variable.class);
		return element instanceof Variable ? (Variable) element : null;
	}

	private String getName(PsiElement element) {
		if (element instanceof Parameter) return ((Parameter) element).getExplicitName();
		else if (element instanceof VarInit) return ((VarInit) element).getName();
		return ((Variable) element).getName();
	}
}
