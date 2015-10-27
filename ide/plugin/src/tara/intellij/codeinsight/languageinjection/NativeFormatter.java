package tara.intellij.codeinsight.languageinjection;

import com.intellij.psi.PsiClass;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.dsl.Proteo;
import tara.intellij.codeinsight.languageinjection.helpers.Format;
import tara.intellij.codeinsight.languageinjection.helpers.NameFormatter;
import tara.intellij.codeinsight.languageinjection.helpers.NativeExtractor;
import tara.intellij.codeinsight.languageinjection.helpers.TemplateTags;
import tara.lang.model.*;
import tara.lang.model.rules.NativeRule;

import java.util.ArrayList;
import java.util.List;

public class NativeFormatter implements TemplateTags {

	private final String generatedLanguage;
	private final Language language;
	private final boolean m0;

	public NativeFormatter(String generatedLanguage, Language language, boolean m0) {
		this.generatedLanguage = generatedLanguage;
		this.language = language;
		this.m0 = m0;
	}

	public void fillFrameForNativeVariable(Frame frame, Variable variable, Object bodyValue) {
		final String body = String.valueOf(bodyValue);
		final String signature = getSignature(variable);
		final String nativeContainer = NameFormatter.cleanQn(buildContainerPath(((NativeRule) variable.rule()), variable.container(), language, generatedLanguage));
		final String aPackage = calculatePackage(variable.container());
		NativeExtractor extractor = new NativeExtractor(nativeContainer, variable.name(), signature);
		if (bodyValue != null) frame.addFrame("body", formatBody(body, signature));
		frame.addFrame(NATIVE_CONTAINER, nativeContainer);
		if (!aPackage.isEmpty()) frame.addFrame(PACKAGE, aPackage);
		frame.addFrame(SIGNATURE, signature);
		frame.addFrame("uid", variable.getUID());
		frame.addFrame("methodName", extractor.methodName());
		frame.addFrame("parameters", extractor.parameters());
		frame.addFrame("returnType", extractor.returnValue());
	}

	public void fillFrameExpressionVariable(Frame frame, Variable variable, Object next) {
		final String body = String.valueOf(next);
		final String type = variable.type().javaName();
		final String signature = "public " + type + " value()";
		final String aPackage = calculatePackage(variable.container());
		Frame nativeFrame = new Frame().addTypes(NATIVE).addFrame("body", formatBody(body, signature));
		if (!aPackage.isEmpty()) frame.addFrame(PACKAGE, aPackage.toLowerCase());
		nativeFrame.addFrame(GENERATED_LANGUAGE, generatedLanguage).addFrame("varName", variable.name());
		nativeFrame.addFrame(CONTAINER, buildContainerPathOfExpression(variable.container(), generatedLanguage, m0));
		nativeFrame.addFrame(INTERFACE, "magritte.Expression<" + type + ">");
		nativeFrame.addFrame(SIGNATURE, signature);
		nativeFrame.addFrame(CLASS_NAME, variable.name() + "_" + variable.getUID());
		frame.addFrame(NATIVE, nativeFrame);
	}

	public static void fillFrameExpressionParameter(Frame frame, Parameter parameter, String body, Language language, String generatedLanguage) {
		final String type = parameter.inferredType().javaName();
		final String signature = "public " + type + " value()";
		final String aPackage = calculatePackage(parameter.container()).toLowerCase();
		Frame nativeFrame = new Frame().addTypes(NATIVE).addFrame("body", formatBody(body, signature));
		nativeFrame.addFrame(GENERATED_LANGUAGE, generatedLanguage).addFrame("varName", parameter.name());
		nativeFrame.addFrame(CONTAINER, NameFormatter.cleanQn(buildContainerPath(((NativeRule) parameter.rule()), parameter.container(), language, generatedLanguage)));
		if (!aPackage.isEmpty()) nativeFrame.addFrame(PACKAGE, aPackage);
		nativeFrame.addFrame(INTERFACE, "magritte.Expression<" + type + ">");
		nativeFrame.addFrame(SIGNATURE, signature);
		nativeFrame.addFrame(CLASS_NAME, parameter.name() + "_" + parameter.getUID());
		frame.addFrame(NATIVE, nativeFrame);
	}

	public static String getLanguageScope(Parameter parameter, Language language) {
		final NativeRule rule = (NativeRule) parameter.rule();
		if (rule != null) return rule.getLanguage();
		else return language.languageName();
	}

	private static String getQn(Node owner, String language, boolean m0) {
		return asNode(owner, language, m0, null);
	}

	private static String getQn(Node owner, Node node, String language, boolean m0) {
		final FacetTarget facetTarget = facetTargetContainer(node);
		if (owner.isFacet() && facetTarget != null) return asFacetTarget(owner, language, facetTarget);
		else return asNode(owner, language, m0, facetTarget);
	}

	private static String asNode(Node node, String language, boolean m0, FacetTarget facetTarget) {
		return !m0 ? language.toLowerCase() + DOT + (facetTarget == null ? node.qualifiedName() : NameFormatter.composeInFacetTargetQN(node, facetTarget)) :
			language.toLowerCase() + DOT + node.type();
	}

	private static String asFacetTarget(Node owner, String language, FacetTarget facetTarget) {
		return language.toLowerCase() + DOT + owner.name().toLowerCase() + DOT +
			Format.reference().format(owner.name()) + "_" + Format.reference().format(facetTarget.target());
	}

	public static String getSignature(Parameter parameter) {
		final NativeRule rule = (NativeRule) parameter.rule();
		return rule != null ? rule.getSignature() : null;
	}

	public static String getInterface(Parameter parameter) {
		final NativeRule rule = (NativeRule) parameter.rule();
		if (rule == null)
			return null;//throw new SemanticException(new SemanticError("reject.native.signature.notfound", new LanguageParameter(parameter)));
		return rule.interfaceClass();
	}

	public static String getInterface(Variable variable) {
		final NativeRule rule = (NativeRule) variable.rule();
		if (rule.interfaceClass() == null)
			return "";//throw new SemanticException(new SemanticError("reject.native.signature.notfound", new LanguageParameter(parameter)));
		return rule.interfaceClass();
	}

	public static String buildContainerPathOfExpression(NodeContainer owner, String generatedLanguage, boolean m0) {
		if (owner instanceof Node)
			return getQn(firstNoFeatureAndNamed(owner), (Node) owner, generatedLanguage, m0);
		return NameFormatter.getQn((FacetTarget) owner, generatedLanguage);
	}

	public static String formatBody(String body, String signature) {
		final String returnText = "return ";
		String formattedBody = body.endsWith(";") || body.endsWith("}") ? body : body + ";";
		if (!signature.contains(" void ") && !formattedBody.contains("\n") && !formattedBody.startsWith(returnText))
			return returnText + formattedBody;
		return formattedBody;
	}

	public static String getSignature(Variable variable) {
		return ((NativeRule) variable.rule()).getSignature();
	}

	public static String buildContainerPath(NativeRule rule, NodeContainer owner, Language language, String generatedLanguage) {
		final String languageScope = extractLanguageScope(rule, generatedLanguage);
		if (owner instanceof Node) {
			final Node scope = ((Node) owner).isTerminalInstance() ? firstNoFeature(owner) : firstNoFeatureAndNamed(owner);
			if (scope == null) return "";
			if (scope.isTerminalInstance() && !generatedLanguage.equalsIgnoreCase(languageScope))
				return getTypeAsScope(scope, languageScope);
			else if (!generatedLanguage.equalsIgnoreCase(languageScope) && !(language instanceof Proteo))
				return getTypeAsScope(scope, language.languageName());
			else return getQn(scope, (Node) owner, languageScope, false);
		} else if (owner instanceof FacetTarget)
			return NameFormatter.getQn((FacetTarget) owner, languageScope);
		else if (owner instanceof Facet) {
			final Node parent = firstNoFeatureAndNamed(owner);
			if (parent == null) return "";
			return parent.isTerminalInstance() ? getTypeAsScope(parent, language.languageName()) : getQn(parent, languageScope, false);
		} else return "";
	}

	private static String getTypeAsScope(Node scope, String language) {
		return language.toLowerCase() + NameFormatter.DOT + NameFormatter.cleanQn(scope.type());
	}

	private static String extractLanguageScope(NativeRule rule, String language) {
		return rule != null ? rule.getLanguage() : language;
	}

	private static Node firstNoFeature(NodeContainer owner) {
		NodeContainer container = owner;
		while (container != null) {
			if (container instanceof Node && !(container instanceof NodeRoot) && !((Node) container).isFeatureInstance())
				return (Node) container;
			container = container.container();
		}
		return null;
	}

	private static Node firstNoFeatureAndNamed(NodeContainer owner) {
		NodeContainer container = owner;
		while (container != null) {
			if (container instanceof Node && !(container instanceof NodeRoot) && !((Node) container).isAnonymous() &&
				!((Node) container).isFeatureInstance())
				return (Node) container;
			container = container.container();
		}
		return null;
	}

	private static FacetTarget facetTargetContainer(Node node) {
		NodeContainer container = node.container();
		while (container != null) if (container instanceof FacetTarget) return (FacetTarget) container;
		else container = container.container();
		return null;
	}

	public static String calculatePackage(NodeContainer container) {
		final NodeContainer nodeContainer = firstNamedContainer(container);
		return nodeContainer == null ? "" : nodeContainer.qualifiedNameCleaned().replace("$", ".").toLowerCase();
	}

	private static NodeContainer firstNamedContainer(NodeContainer container) {
		List<NodeContainer> containers = collectStructure(container);
		NodeContainer candidate = null;
		for (NodeContainer nodeContainer : containers) {
			if (nodeContainer instanceof Node && !((Node) nodeContainer).isAnonymous()) candidate = nodeContainer;
			else if (nodeContainer instanceof Node) break;
			else candidate = nodeContainer;
		}
		return candidate;
	}

	private static List<NodeContainer> collectStructure(NodeContainer container) {
		List<NodeContainer> containers = new ArrayList<>();
		NodeContainer current = container;
		while (current != null && !(current instanceof NodeRoot)) {
			containers.add(0, current);
			current = current.container();
		}
		return containers;
	}

	public static String getSignature(PsiClass nativeInterface) {
		if (nativeInterface.getMethods().length == 0) return "void NoSignatureFound()";
		final String text = nativeInterface.getMethods()[0].getText();
		return text.substring(0, text.length() - 1);
	}

	public static String getReturn(PsiClass nativeInterface, String body) {
		if (nativeInterface.getAllFields().length == 0) return "";
		if (body.isEmpty()) return body;
		body = body.endsWith(";") || body.endsWith("}") ? body : body + ";";
		if (!(nativeInterface.getMethods()[0].getReturnType() == null) && !body.contains("\n") && body.split(";").length == 1 && !body.startsWith(RETURN))
			return RETURN;
		return "";
	}

	public static String getReturn(String body, String signature) {
		final String returnText = RETURN;
		body = body.endsWith(";") || body.endsWith("}") ? body : body + ";";
		if (!signature.contains(" void ") && !body.contains("\n") && !body.startsWith(returnText))
			return returnText;
		return "";
	}

}
