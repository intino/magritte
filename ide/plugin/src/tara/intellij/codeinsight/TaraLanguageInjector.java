package tara.intellij.codeinsight;

import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.Resolver;
import tara.intellij.lang.TaraLanguage;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;
import tara.intellij.project.module.ModuleProvider;
import tara.language.model.*;
import tara.language.semantics.Allow;
import tara.templates.NativeTemplate;

import static tara.intellij.lang.psi.impl.TaraPsiImplUtil.getParentByType;
import static tara.language.model.Variable.NATIVE_SEPARATOR;

public class TaraLanguageInjector implements LanguageInjector {

	private static final String DOT = ".";

	@Override
	public void getLanguagesToInject(@NotNull PsiLanguageInjectionHost host, @NotNull InjectedLanguagePlaces injectionPlacesRegistrar) {
		if (!Expression.class.isInstance(host) || !host.isValidHost()) return;
		injectionPlacesRegistrar.addPlace(JavaLanguage.INSTANCE,
			getRangeInsideHost((Expression) host),
			createPrefix((Expression) host),
			createSuffix(isWithSemicolon((Expression) host)));
	}

	private boolean isWithSemicolon(@NotNull Expression host) {
		return !host.getValue().trim().endsWith(";") && !host.getValue().trim().endsWith("}");
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

	private Parameter getParameter(Expression Expression) {
		PsiElement element = getParentByType(Expression, Valued.class);
		return element instanceof Parameter ? (Parameter) element : null;
	}

	private String createPrefix(Expression expression) {
		final Language language = TaraUtil.getLanguage(expression);
		if (language == null) return "";
		String languageName = language.languageName();
		Frame frame = createFrame(expression, languageName);
		if (frame == null) return "";
		return NativeTemplate.create().format(frame);
	}

	private String createSuffix(boolean withSemicolon) {
		return (withSemicolon ? ";" : "") + "\n\t}\n" +
			"}";
	}

	@Nullable
	private Frame createFrame(Expression expression, String languageName) {
		Frame frame = new Frame().addTypes("native");
		PsiElement element = (PsiElement) getParameter(expression);
		if (element == null) element = getVariable(expression);
		Node node = getContainerNode(element);
		final String language = getGeneratedLanguage(expression);
		frame.addFrame("languageGenerated", language);
		frame.addFrame("language", languageName.toLowerCase());
		Allow.Parameter allow = TaraUtil.getCorrespondingAllow(node, element);
		final String type = getType(allow, element);
		return Primitives.NATIVE.equals(type) ?
			fillAsNativeFrame(expression, frame, element, node, language, allow) :
			fillAsExpression(expression, frame, element, node, language, allow, type);
	}

	@NotNull
	private Frame fillAsExpression(Expression expression, Frame frame, PsiElement element, Node node, String language, @Nullable Allow.Parameter allow, String type) {
		final String body = expression.getValue().replace("\\n", "\n").replace("\\\"", "\"");
		frame.addFrame("interface", "magritte.Expression<" + mask(type) + ">");
		frame.addFrame("variable", getName(element)).
			addFrame("qn", node.qualifiedName().replace("@anonymous", "").replace(".", "_")).
			addFrame("parent", findParent(element, node, getCorrespondingLanguageName(allow, language))).
			addFrame("signature", "public " + mask(type) + " value()").
			addFrame("return", !body.contains("\n") && !body.startsWith("return ") ? "return " : "");
		return frame;
	}

	private String mask(String type) {
		return capitalize(Primitives.NATURAL.equals(type) ? Primitives.INTEGER : type);
	}

	private String capitalize(String type) {
		return type != null ? type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase() : "";
	}

	private Frame fillAsNativeFrame(Expression expression, Frame frame, PsiElement element, Node node, String language, Allow.Parameter allow) {
		frame.addFrame("interface", (allow != null ? intention(allow.contract()) : getVariableInterface(element)));
		final String body = expression.getValue().replace("\\n", "\n").replace("\\\"", "\"");
		final String signature = getSignature(allow != null ? allow.contract() : findNativeInterface(getContract((TaraVariable) element)));
		frame.addFrame("variable", getName(element)).
			addFrame("qn", node.qualifiedName().replace("@anonymous", "").replace(".", "_")).
			addFrame("parent", findParent(element, node, getCorrespondingLanguageName(allow, language))).
			addFrame("signature", signature).
			addFrame("return", !signature.contains(" void ") && !body.contains("\n") && !body.startsWith("return ") ? "return " : "");
		return frame;
	}

	private TaraContract getContract(TaraVariable variable) {
		return variable.getAttributeType() != null ? variable.getAttributeType().getContract() : null;
	}

	private String getType(Allow.Parameter allow, PsiElement element) {
		if (allow != null) return allow.type();
		if (element instanceof Variable) return ((Variable) element).type();
		if (element instanceof Parameter) ((Parameter) element).inferredType();
		return null;
	}

	private String getCorrespondingLanguageName(Allow.Parameter allow, String language) {
		if (allow == null) return language;
		return allow.contract().contains(NATIVE_SEPARATOR) && allow.contract().split(NATIVE_SEPARATOR).length == 3 ? allow.contract().split(NATIVE_SEPARATOR)[2] : language;
	}

	@NotNull
	private String getGeneratedLanguage(Expression stringValue) {
		final Module module = ModuleProvider.getModuleOf(stringValue);
		final TaraFacet facet = TaraFacet.getTaraFacetByModule(module);
		if (facet == null) return "";
		final TaraFacetConfiguration configuration = facet.getConfiguration();
		return configuration.getGeneratedDslName().isEmpty() ? module.getName().toLowerCase() : configuration.getGeneratedDslName().toLowerCase();
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
		final String signature = contract.contains(NATIVE_SEPARATOR) ? contract.split(NATIVE_SEPARATOR)[1] : contract;
		return signature.startsWith("public ") ? signature : "public " + signature;
	}

	private String intention(String contract) {
		if (contract.contains(NATIVE_SEPARATOR)) {
			final String[] split = contract.split(NATIVE_SEPARATOR);
			if (split.length == 3) return split[2].toLowerCase() + "." + "natives" + "." + split[0];
			else return split[0];
		} else return "";
	}

	private String findParent(PsiElement element, Node node, String language) {
		Node candidate = node;
		ensureTypeIsResolved(element, candidate);
		while (candidate != null) {
			if (candidate.name() != null && !candidate.isFeatureInstance())
				return candidate.isTerminalInstance() ? getTypeAsParent(candidate) : clean(getQn(candidate, element, language));
			else candidate = TaraPsiImplUtil.getContainerNodeOf((PsiElement) candidate);
		}
		return "magritte.Morph";
	}

	private String getTypeAsParent(Node candidate) {
		final Language language = TaraLanguage.getLanguage(((TaraNode) candidate).getContainingFile());
		if (language == null) return "";
		return language.languageName().toLowerCase() + DOT + clean(candidate.type());
	}

	private void ensureTypeIsResolved(PsiElement element, Node node) {
		new Resolver(TaraLanguage.getLanguage(element.getContainingFile())).resolve(node);
	}

	private String clean(String qn) {
		return qn.replace("@anonymous", "").replace("[", "").replace("]", "");
	}

	private static String getQn(Node owner, PsiElement element, String language) {
		final FacetTarget facetTarget = facetTargetContainer(element);
		if (owner.isFacet()) return language.toLowerCase() + getName(owner) + getFacetTarget(owner, facetTarget);
		else {
			final Node node = TaraPsiImplUtil.getContainerNodeOf(element);
			return language.toLowerCase() + DOT + (facetTarget == null ? node.qualifiedName() : composeInFacetTargetQN(node, facetTarget));
		}
	}

	@NotNull
	private static String getFacetTarget(Node owner, FacetTarget facetTarget) {
		return facetTarget != null ? DOT + owner.name() + "_" + format(facetTarget.target()) : "";
	}

	@NotNull
	private static String getName(Node owner) {
		return owner.name() != null ? DOT + owner.name().toLowerCase() : "";
	}

	private static String composeInFacetTargetQN(Node node, FacetTarget facetTarget) {
		final Node containerNode = TaraPsiImplUtil.getContainerNodeOf((PsiElement) facetTarget);
		if (containerNode == null || containerNode.name() == null) return "";
		return containerNode.name().toLowerCase() + DOT + format(facetTarget.target()) + DOT + node.qualifiedName();
	}

	private static String format(String value) {
		if (!value.contains(DOT)) return value.substring(0, 1).toUpperCase() + value.substring(1);
		return value;
	}

	private static FacetTarget facetTargetContainer(PsiElement element) {
		NodeContainer container = TaraPsiImplUtil.getContainerOf(element);
		while (container != null)
			if (container instanceof FacetTarget) return (FacetTarget) container;
			else container = TaraPsiImplUtil.getContainerOf((PsiElement) container);
		return null;
	}

	private Node getContainerNode(PsiElement element) {
		return (Node) getParentByType(element, TaraNode.class);
	}

	private PsiElement getVariable(Expression stringValue) {
		PsiElement element = getParentByType(stringValue, TaraVariable.class);
		return element instanceof Variable ? element : null;
	}

	private String getName(PsiElement element) {
		if (element instanceof Parameter) return ((Parameter) element).name();
		return ((Variable) element).name();
	}

	public String getVariableInterface(PsiElement element) {
		return element instanceof Variable ? getContract((TaraVariable) element).getText() : (element instanceof Parameter ? ((Parameter) element).contract() : "");
	}
}
