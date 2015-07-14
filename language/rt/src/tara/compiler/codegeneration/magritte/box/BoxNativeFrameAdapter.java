package tara.compiler.codegeneration.magritte.box;

import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.Format;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.language.model.*;

public class BoxNativeFrameAdapter implements TemplateTags {

	private final String generatedLanguage;
	private final Language language;
	private final boolean m0;

	public BoxNativeFrameAdapter(String generatedLanguage, Language language, boolean m0) {
		this.generatedLanguage = generatedLanguage;
		this.language = language;
		this.m0 = m0;
	}

	public void fillFrameForNativeParameter(Frame frame, Parameter parameter, String bodyText) {
		final String signature = getSignature(parameter);
		Frame nativeFrame = new Frame().addTypes("native").addFrame("body", formatBody(bodyText, signature));
		nativeFrame.addFrame(GENERATED_LANGUAGE, generatedLanguage);
		nativeFrame.addFrame("varName", parameter.name());
		nativeFrame.addFrame("container", NameFormatter.cleanQn(buildContainerPath(parameter.contract(), parameter.owner())));
		nativeFrame.addFrame("parentIntention", getScope(parameter));
		nativeFrame.addFrame("interface", NameFormatter.cleanQn(getInterface(parameter)));
		nativeFrame.addFrame("signature", signature);
		nativeFrame.addFrame("className", parameter.name() + "_" + parameter.getUID());
		frame.addFrame(NATIVE, nativeFrame);
	}

	public void fillFrameForNativeVariable(Frame frame, Variable variable, Object next) {
		final String body = String.valueOf(next);
		final String signature = getSignature(variable);
		Frame nativeFrame = new Frame().addTypes(NATIVE).addFrame("body", formatBody(body, signature));
		nativeFrame.addFrame(GENERATED_LANGUAGE, generatedLanguage).addFrame("varName", variable.name()).
			addFrame("container", NameFormatter.cleanQn(buildContainerPath(variable.contract(), variable.container()))).
			addFrame("parentIntention", generatedLanguage).
			addFrame("interface", NameFormatter.cleanQn(getInterface(variable))).
			addFrame("signature", signature).
			addFrame("className", variable.name() + "_" + variable.getUID());
		frame.addFrame(NATIVE, nativeFrame);
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

	public void fillFrameExpressionParameter(Frame frame, Parameter parameter, String body) {
		final String type = mask(parameter.inferredType());
		final String signature = "public " + type + " value()";
		Frame nativeFrame = new Frame().addTypes(NATIVE).addFrame("body", formatBody(body, signature));
		nativeFrame.addFrame(GENERATED_LANGUAGE, generatedLanguage).addFrame("varName", parameter.name()).
			addFrame("container", NameFormatter.cleanQn(buildContainerPath(parameter.contract(), parameter.owner()))).
			addFrame("interface", "magritte.Expression<" + type + ">").
			addFrame("signature", signature).
			addFrame("className", parameter.name() + "_" + parameter.getUID());
		frame.addFrame(NATIVE, nativeFrame);
	}

	private String getScope(Parameter parameter) {
		if (parameter.contract().contains(tara.language.model.Variable.NATIVE_SEPARATOR)) {
			final String[] split = parameter.contract().split(tara.language.model.Variable.NATIVE_SEPARATOR);
			if (split.length == 3) return split[2].toLowerCase();
			else return language.languageName();
		} else return language.languageName();
	}

	private String getQn(Node owner, Node node, String language, boolean m0) {
		final FacetTarget facetTarget = facetTargetContainer(node);
		if (owner.isFacet())
			return NameFormatter.composeMorphPackagePath(language) + NameFormatter.DOT + owner.name().toLowerCase() + NameFormatter.DOT +
				Format.reference().format(owner.name()) + "_" + Format.reference().format(facetTarget.target());
		else
			return !m0 ? NameFormatter.composeMorphPackagePath(language) + NameFormatter.DOT + (facetTarget == null ? node.qualifiedName() : NameFormatter.composeInFacetTargetQN(node, facetTarget)) :
				language.toLowerCase() + NameFormatter.DOT + node.type();
	}

	private String buildContainerPathOfExpression(NodeContainer owner) {
		if (owner instanceof Node)
			return getQn(firstNoFeatureAndNamed(owner), (Node) owner, generatedLanguage, m0);
		return NameFormatter.getQn((FacetTarget) owner, generatedLanguage);
	}

	private String mask(String type) {
		return capitalize(type.equals(Primitives.NATURAL) ? Primitives.INTEGER : type);
	}

	private String capitalize(String type) {
		return type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase();
	}

	private String formatBody(String body, String signature) {
		final String returnText = "return ";
		body = body.endsWith(";") || body.endsWith("}") ? body : body + ";";
		if (!signature.contains(" void ") && !body.contains("\n") && !body.startsWith(returnText))
			return returnText + body;
		return body;
	}

	private String getInterface(Variable variable) {
		return variable.contract().contains(Variable.NATIVE_SEPARATOR) ? variable.contract().substring(0, variable.contract().indexOf(Variable.NATIVE_SEPARATOR)) : variable.contract();
	}

	private String getInterface(Parameter parameter) {
		if (!parameter.contract().contains(Variable.NATIVE_SEPARATOR))
			return "";//throw new SemanticException(new SemanticError("reject.native.signature.notfound", new LanguageParameter(parameter)));
		return parameter.contract().substring(0, parameter.contract().indexOf(Variable.NATIVE_SEPARATOR));
	}

	private String getSignature(Parameter parameter) {
		return !parameter.contract().contains(Variable.NATIVE_SEPARATOR) ? parameter.contract() :
			parameter.contract().split(Variable.NATIVE_SEPARATOR)[1];
	}

	private String getSignature(Variable variable) {
		return variable.contract().substring(variable.contract().indexOf(Variable.NATIVE_SEPARATOR) + 1);
	}

	private String buildContainerPath(String contract, NodeContainer owner) {
		if (owner instanceof Node) {
			final Node parent = firstNoFeatureAndNamed(owner);
			if (parent == null) return "";
			return parent.isTerminalInstance() ? getTypeAsParent(parent) : getQn(parent, (Node) owner, withContract(contract, generatedLanguage), m0);
		}
		return NameFormatter.getQn((FacetTarget) owner, withContract(contract, generatedLanguage));
	}

	private String getTypeAsParent(Node parent) {
		return language.languageName().toLowerCase() + NameFormatter.DOT + NameFormatter.cleanQn(parent.type());
	}

	private String withContract(String contract, String language) {
		if (contract.contains(tara.language.model.Variable.NATIVE_SEPARATOR)) {
			final String[] split = contract.split(tara.language.model.Variable.NATIVE_SEPARATOR);
			if (split.length == 3) return split[2].toLowerCase();
			else return language;
		} else return language;
	}

	private Node firstNoFeatureAndNamed(NodeContainer owner) {
		NodeContainer container = owner;
		while (container != null) {
			if (container instanceof Node && !((Node) container).isAnonymous() &&
				!((Node) container).isFeatureInstance())
				return (Node) container;
			container = container.container();
		}
		return null;
	}

	private FacetTarget facetTargetContainer(Node node) {
		NodeContainer container = node.container();
		while (container != null) if (container instanceof FacetTarget) return (FacetTarget) container;
		else container = container.container();
		return null;
	}

}
