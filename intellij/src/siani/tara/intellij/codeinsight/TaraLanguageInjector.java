package siani.tara.intellij.codeinsight;

import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.siani.itrules.model.Frame;
import siani.tara.Language;
import siani.tara.intellij.annotator.semanticanalizer.NodeAnalyzer;
import siani.tara.intellij.framework.maven.NativeTemplate;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.facet.TaraFacet;
import siani.tara.intellij.project.facet.TaraFacetConfiguration;
import siani.tara.intellij.project.module.ModuleProvider;
import siani.tara.semantic.Allow;

import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getParentByType;
import static siani.tara.semantic.model.Variable.NATIVE_SEPARATOR;

public class TaraLanguageInjector implements LanguageInjector {

	private static final String DOT = ".";

	private static Parameter getParameter(Expression Expression) {
		PsiElement element = getParentByType(Expression, Parameter.class);
		return element instanceof Parameter ? (Parameter) element : null;
	}

	@Override
	public void getLanguagesToInject(@NotNull PsiLanguageInjectionHost host, @NotNull InjectedLanguagePlaces injectionPlacesRegistrar) {
		if (!Expression.class.isInstance(host) || !host.isValidHost()) return;
		injectionPlacesRegistrar.addPlace(JavaLanguage.INSTANCE,
			getRangeInsideHost((Expression) host),
			createPrefix((Expression) host),
			createSuffix(!((Expression) host).getValue().endsWith(";")));
	}

	@NotNull
	private TextRange getRangeInsideHost(@NotNull Expression host) {
		return (!host.isMultiLine()) ? new TextRange(1, host.getTextLength() - 1) : getMultiLineBounds(host);
	}

	private TextRange getMultiLineBounds(Expression host) {
		final String value = host.getValue();
		final int i = host.getText().indexOf(value);
		return new TextRange(i, i + value.length());
	}

	private String createPrefix(Expression expression) {
		final Language language = TaraUtil.getLanguage(expression);
		if (language == null) return "";
		String languageName = language.languageName();
		Frame frame = createFrame(expression, languageName);
		return NativeTemplate.create().format(frame);
	}

	private String createSuffix(boolean withSemicolon) {
		return (withSemicolon ? ";" : "") + "\n\t}\n" +
			"}";
	}

	@NotNull
	private Frame createFrame(Expression expression, String languageName) {
		Frame frame = new Frame().addTypes("native");
		PsiElement element = getVarInit(expression);
		if (element == null) element = getParameter(expression);
		if (element == null) element = getVariable(expression);
		Node node = getContainerNode(element);
		final String generatedLanguage = getGeneratedLanguage(expression);
		frame.addFrame("languageGenerated", generatedLanguage);
		frame.addFrame("language", languageName.toLowerCase());
		Allow.Parameter allow = TaraUtil.getCorrespondingAllow(node, element);
		String contract = (allow != null) ? intention(allow.contract()) : getVariableIntention(element);
		frame.addFrame("intention", contract);
		final String signature = getSignature(allow != null ? allow.contract() : findNativeInterface(((Variable) element).getContract()));
		frame.addFrame("variable", getName(element, allow)).
			addFrame("qn", node.getQualifiedName().replace("@anonymous", "").replace(".", "_")).
			addFrame("parent", findParent(element, node, generatedLanguage)).
			addFrame("signature", signature).
			addFrame("return", !signature.contains(" void ") ? "return " : "");
		return frame;
	}

	@NotNull
	private String getGeneratedLanguage(Expression stringValue) {
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

	private String findParent(PsiElement element, Node node, String generatedLanguage) {
		Node candidate = node;
		new NodeAnalyzer(node.getContainer()).analyze();
		while (candidate.getContainer() != null)
			if (node.getName() != null && !node.isFeatureInstance())
				return getQn(candidate, element, generatedLanguage);
			else candidate = candidate.getContainer();
		return getQn(candidate, element, generatedLanguage);
	}

	private static String getQn(Node owner, PsiElement element, String generatedLanguage) {
		final FacetTarget facetTarget = facetTargetContainer(element);
		if (owner.isFacet())
			return generatedLanguage.toLowerCase() + DOT + owner.getName().toLowerCase() + DOT + format(facetTarget.getTarget());
		else {
			final Node node = TaraPsiImplUtil.getContainerNodeOf(element);
			return generatedLanguage.toLowerCase() + DOT + (facetTarget == null ? node.getQualifiedName() : composeInFacetTargetQN(node, facetTarget));
		}
	}

	private static String composeInFacetTargetQN(Node node, FacetTarget facetTarget) {
		return (TaraPsiImplUtil.getContainerNodeOf(facetTarget).getName().toLowerCase() + DOT + format(facetTarget.getTarget()) + DOT + node.getQualifiedName());
	}

	private static String format(String value) {
		if (!value.contains(DOT)) return value.substring(0, 1).toUpperCase() + value.substring(1);
		return value;
	}

	private static FacetTarget facetTargetContainer(PsiElement element) {
		PsiElement container = TaraPsiImplUtil.getContextOf(element);
		while (container != null)
			if (container instanceof FacetTarget) return (FacetTarget) container;
			else container = TaraPsiImplUtil.getContextOf(container);
		return null;
	}

	private Node getContainerNode(PsiElement element) {
		return (Node) getParentByType(element, Node.class);
	}

	private PsiElement getVarInit(Expression stringValue) {
		return getParentByType(stringValue, VarInit.class);
	}

	private PsiElement getVariable(Expression stringValue) {
		PsiElement element = getParentByType(stringValue, Variable.class);
		return element instanceof Variable ? (Variable) element : null;
	}

	private String getName(PsiElement element, Allow.Parameter allow) {
		if (element instanceof Parameter) return allow.name();
		else if (element instanceof VarInit) return ((VarInit) element).getName();
		return ((Variable) element).getName();
	}

	public String getVariableIntention(PsiElement element) {
		return element instanceof Variable ? ((Variable) element).getContract().getText() : (element instanceof VarInit ? ((VarInit) element).getContract() : "");
	}
}
