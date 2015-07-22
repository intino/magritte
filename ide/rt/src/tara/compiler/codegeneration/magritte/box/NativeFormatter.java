package tara.compiler.codegeneration.magritte.box;

import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.Format;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.codegeneration.magritte.NativeExtractor;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.language.model.*;

public class NativeFormatter implements TemplateTags {

	private final String generatedLanguage;
	private final Language language;
	private final boolean m0;

	public NativeFormatter(String generatedLanguage, Language language, boolean m0) {
		this.generatedLanguage = generatedLanguage;
		this.language = language;
		this.m0 = m0;
	}

	public void fillFrameForNativeVariable(Frame frame, Variable variable, Object next) {
		final String body = String.valueOf(next);
		final String signature = getSignature(variable);
		final String nativeContainer = NameFormatter.cleanQn(buildContainerPath(variable.contract(), variable.container(), language, generatedLanguage));
		NativeExtractor extractor = new NativeExtractor(nativeContainer, variable.name(), signature);
		frame.addFrame("body", formatBody(body, signature)).
			addFrame("nativeContainer", nativeContainer).
			addFrame("signature", signature).
			addFrame("uid", variable.getUID()).
			addFrame("methodName", extractor.methodName()).
			addFrame("parameters", extractor.parameters()).
			addFrame("returnType", extractor.returnValue());
	}

	public void fillFrameExpressionVariable(Frame frame, Variable variable, Object next) {
		final String body = String.valueOf(next);
		final String type = mask(variable.type());
		final String signature = "public " + type + " value()";
		Frame nativeFrame = new Frame().addTypes(NATIVE).addFrame("body", formatBody(body, signature));
		nativeFrame.addFrame(GENERATED_LANGUAGE, generatedLanguage).addFrame("varName", variable.name()).
			addFrame("container", buildContainerPathOfExpression(variable.container())).
			addFrame("interface", "magritte.Expression<" + type + ">").
			addFrame("signature", signature).
			addFrame("className", variable.name() + "_" + variable.getUID());
		frame.addFrame(NATIVE, nativeFrame);
	}

	public static void fillFrameExpressionParameter(Frame frame, Parameter parameter, String body, Language language, String generatedLanguage) {
		final String type = mask(parameter.inferredType());
		final String signature = "public " + type + " value()";
		Frame nativeFrame = new Frame().addTypes(NATIVE).addFrame("body", formatBody(body, signature));
		nativeFrame.addFrame(GENERATED_LANGUAGE, generatedLanguage).addFrame("varName", parameter.name()).
			addFrame("container", NameFormatter.cleanQn(buildContainerPath(parameter.contract(), parameter.container(), language, generatedLanguage))).
			addFrame("interface", "magritte.Expression<" + type + ">").
			addFrame("signature", signature).
			addFrame("className", parameter.name() + "_" + parameter.getUID());
		frame.addFrame(NATIVE, nativeFrame);
	}

	public static String getScope(Parameter parameter, Language language) {
		if (parameter.contract().contains(tara.language.model.Variable.NATIVE_SEPARATOR)) {
			final String[] split = parameter.contract().split(tara.language.model.Variable.NATIVE_SEPARATOR);
			if (split.length == 3) return split[2].toLowerCase();
			else return language.languageName();
		} else return language.languageName();
	}

	private static String getQn(Node owner, Node node, String language, boolean m0) {
		final FacetTarget facetTarget = facetTargetContainer(node);
		if (owner.isFacet())
			return language.toLowerCase() + NameFormatter.DOT + owner.name().toLowerCase() + NameFormatter.DOT +
				Format.reference().format(owner.name()) + "_" + Format.reference().format(facetTarget.target());
		else
			return !m0 ? language.toLowerCase() + NameFormatter.DOT + (facetTarget == null ? node.qualifiedName() : NameFormatter.composeInFacetTargetQN(node, facetTarget)) :
				language.toLowerCase() + NameFormatter.DOT + node.type();
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

	private String buildContainerPathOfExpression(NodeContainer owner) {
		if (owner instanceof Node)
			return getQn(firstNoFeatureAndNamed(owner), (Node) owner, generatedLanguage, m0);
		return NameFormatter.getQn((FacetTarget) owner, generatedLanguage);
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

	private String getSignature(Variable variable) {
		return variable.contract().substring(variable.contract().indexOf(Variable.NATIVE_SEPARATOR) + 1);
	}

	public static String buildContainerPath(String contract, NodeContainer owner, Language language, String generatedLanguage) {
		if (owner instanceof Node) {
			final Node parent = firstNoFeatureAndNamed(owner);
			if (parent == null) return "";
			return parent.isTerminalInstance() ? getTypeAsParent(parent, language) : getQn(parent, (Node) owner, withContract(contract, generatedLanguage), false);
		}
		return NameFormatter.getQn((FacetTarget) owner, withContract(contract, generatedLanguage));
	}

	private static String getTypeAsParent(Node parent, Language language) {
		return language.languageName().toLowerCase() + NameFormatter.DOT + NameFormatter.cleanQn(parent.type());
	}

	private static String withContract(String contract, String language) {
		if (contract.contains(tara.language.model.Variable.NATIVE_SEPARATOR)) {
			final String[] split = contract.split(tara.language.model.Variable.NATIVE_SEPARATOR);
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

}
