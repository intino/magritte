package tara.intellij.codeinsight.languageinjection;

import com.intellij.psi.PsiClass;
import org.siani.itrules.Formatter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.language.model.*;

public class NativeFormatter {

	private static final String DOT = ".";
	private final String generatedLanguage;
	private final Language language;

	public NativeFormatter(String generatedLanguage, Language language, boolean m0) {
		this.generatedLanguage = generatedLanguage;
		this.language = language;
	}

	public void fillFrameForNativeVariable(Frame frame, Variable variable, Object bodyValue) {
		final String body = String.valueOf(bodyValue);
		final String signature = getSignature(variable);
		final String nativeContainer = cleanQn(buildNativeContainerPath(variable.contract(), variable.container(), language, generatedLanguage));
		NativeExtractor extractor = new NativeExtractor(nativeContainer, variable.name(), signature);
		if (bodyValue != null) frame.addFrame("body", formatBody(body, signature));
		frame.addFrame("nativeContainer", nativeContainer);
		frame.addFrame("signature", signature);
		frame.addFrame("uid", variable.getUID());
		frame.addFrame("methodName", extractor.methodName());
		frame.addFrame("parameters", extractor.parameters());
		frame.addFrame("returnType", extractor.returnValue());
	}

	public static void fillFrameExpressionVariable(Frame frame, Variable variable, Object bodyText, String generatedLanguage, boolean m0) {
		final String body = String.valueOf(bodyText);
		final String type = mask(variable.type());
		final String signature = "public " + type + " value()";
		Frame nativeFrame = new Frame().addTypes("native").addFrame("body", formatBody(body, signature));
		nativeFrame.addFrame("generatedLanguage", generatedLanguage).addFrame("varName", variable.name()).
			addFrame("container", buildContainerPathOfExpression(variable.container(), generatedLanguage, m0)).
			addFrame("interface", "magritte.Expression<" + type + ">").
			addFrame("signature", signature).
			addFrame("className", variable.name() + "_" + variable.getUID());
		frame.addFrame("native", nativeFrame);
	}

	public static void fillFrameExpressionParameter(Frame frame, Parameter parameter, String body, Language language, String generatedLanguage) {
		final String type = mask(parameter.inferredType());
		final String signature = "public " + type + " value()";
		Frame nativeFrame = new Frame().addTypes("native").addFrame("body", formatBody(body, signature));
		nativeFrame.addFrame("generatedLanguage", generatedLanguage).addFrame("varName", parameter.name()).
			addFrame("container", cleanQn(buildNativeContainerPath(parameter.contract(), parameter.container(), language, generatedLanguage))).
			addFrame("interface", "magritte.Expression<" + type + ">").
			addFrame("signature", signature).
			addFrame("className", parameter.name() + "_" + parameter.getUID());
		frame.addFrame("native", nativeFrame);
	}

	public static String getScope(Parameter parameter, Language language) {
		if (parameter.contract().contains(Variable.NATIVE_SEPARATOR)) {
			final String[] split = parameter.contract().split(Variable.NATIVE_SEPARATOR);
			if (split.length == 3) return split[2].toLowerCase();
			else return language.languageName();
		} else return language.languageName();
	}

	private static String getQn(Node owner, Node node, String language, boolean m0) {
		final FacetTarget facetTarget = facetTargetContainer(node);
		if (owner.isFacet() && facetTarget != null)
			return language.toLowerCase() + DOT + owner.name().toLowerCase() + DOT +
				reference().format(owner.name()) + "_" + reference().format(facetTarget.target());
		else
			return !m0 ? language.toLowerCase() + DOT + (facetTarget == null ? node.qualifiedName() : composeInFacetTargetQN(node, facetTarget)) :
				language.toLowerCase() + DOT + node.type();
	}


	public static String getSignature(Parameter parameter) {
		return !parameter.contract().contains(Variable.NATIVE_SEPARATOR) ? parameter.contract() :
			parameter.contract().split(Variable.NATIVE_SEPARATOR)[1];
	}


	public static String getInterface(Parameter parameter) {
		if (!parameter.contract().contains(Variable.NATIVE_SEPARATOR))
			return "";//throw new SemanticException(new SemanticError("reject.native.signature.notfound", new LanguageParameter(parameter)));
		return parameter.contract().substring(0, parameter.contract().indexOf(Variable.NATIVE_SEPARATOR));
	}

	private static String buildContainerPathOfExpression(NodeContainer owner, String generatedLanguage, boolean m0) {
		if (owner instanceof Node)
			return getQn(firstNoFeatureAndNamed(owner), (Node) owner, generatedLanguage, m0);
		return getQn((FacetTarget) owner, generatedLanguage);
	}

	private static String mask(String type) {
		return capitalize(type.equals(Primitives.NATURAL) ? Primitives.INTEGER : type);
	}

	private static String capitalize(String type) {
		return type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase();
	}

	public static String formatBody(String body, String signature) {
		final String returnText = "return ";
		body = body.endsWith(";") || body.endsWith("}") ? body : body + ";";
		if (!signature.contains(" void ") && !body.contains("\n") && !body.startsWith(returnText))
			return returnText + body;
		return body;
	}

	public static String getReturn(String body, String signature) {
		final String returnText = "return ";
		body = body.endsWith(";") || body.endsWith("}") ? body : body + ";";
		if (!signature.contains(" void ") && !body.contains("\n") && !body.startsWith(returnText))
			return returnText;
		return "";
	}

	private String getSignature(Variable variable) {
		return variable.contract().substring(variable.contract().indexOf(Variable.NATIVE_SEPARATOR) + 1);
	}

	public static String buildNativeContainerPath(String contract, NodeContainer owner, Language language, String generatedLanguage) {
		if (owner instanceof Node) {
			final Node parent = firstNoFeatureAndNamed(owner);
			if (parent == null) return "";
			return parent.isTerminalInstance() ? getTypeAsParent(parent, language) : getQn(parent, (Node) owner, withContract(contract, generatedLanguage), false);
		}
		if (owner instanceof FacetTarget)
			return getQn((FacetTarget) owner, withContract(contract, generatedLanguage));
		return "";
	}

	private static String getTypeAsParent(Node parent, Language language) {
		return language.languageName().toLowerCase() + DOT + cleanQn(parent.type());
	}

	private static String withContract(String contract, String language) {
		if (contract.contains(Variable.NATIVE_SEPARATOR)) {
			final String[] split = contract.split(Variable.NATIVE_SEPARATOR);
			if (split.length == 3) return split[2].toLowerCase();
			else return language;
		} else return language;
	}

	private static Node firstNoFeatureAndNamed(NodeContainer owner) {
		NodeContainer container = owner;
		while (container != null) {
			if (container instanceof Node && !((Node) container).isAnonymous() &&
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

	public static String cleanQn(String qualifiedName) {
		return qualifiedName.replace(Node.ANNONYMOUS, "").replace("[", "").replace("]", "");
	}

	public static String getQn(FacetTarget target, String generatedLanguage) {
		return generatedLanguage.toLowerCase() + DOT + target.targetNode().qualifiedName();
	}

	public static String composeInFacetTargetQN(Node node, FacetTarget facetTarget) {
		final Node container = (Node) facetTarget.container();
		return container.name().toLowerCase() + "." + node.qualifiedName();
	}

	public static Formatter reference() {
		return value -> {
			String val = value.toString();
			if (!val.contains(DOT)) return (val.substring(0, 1).toUpperCase() + val.substring(1)).replace("-", "");
			return val.replace("-", "");
		};
	}

	public static String getSignature(PsiClass nativeInterface) {
		final String text = nativeInterface.getMethods()[0].getText();
		return text.substring(0, text.length() - 1);
	}

	public static String getReturn(PsiClass nativeInterface, String body) {
		final String returnText = "return ";
		body = body.endsWith(";") || body.endsWith("}") ? body : body + ";";
		if (!(nativeInterface.getMethods()[0].getReturnType() == null) && !body.contains("\n") && !body.startsWith(returnText))
			return returnText;
		return body;
	}
}
