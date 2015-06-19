package siani.tara.compiler.codegeneration.magritte.box;

import org.siani.itrules.model.Frame;
import siani.tara.Language;
import siani.tara.compiler.codegeneration.Format;
import siani.tara.compiler.codegeneration.magritte.NameFormatter;
import siani.tara.compiler.codegeneration.magritte.TemplateTags;
import siani.tara.compiler.model.*;
import siani.tara.semantic.model.Primitives;

import static siani.tara.compiler.codegeneration.magritte.NameFormatter.*;
import static siani.tara.compiler.model.Variable.NATIVE_SEPARATOR;

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
		nativeFrame.addFrame("varName", parameter.getName());
		nativeFrame.addFrame("container", cleanQn(buildContainerPath(parameter.getContract(), parameter.getOwner())));
		nativeFrame.addFrame("parentIntention", getScope(parameter));
		nativeFrame.addFrame("interface", cleanQn(getInterface(parameter)));
		nativeFrame.addFrame("signature", signature);
		nativeFrame.addFrame("className", parameter.getName() + "_" + parameter.getUID());
		frame.addFrame(NATIVE, nativeFrame);
	}

	public void fillFrameForNativeVariable(Frame frame, Variable variable, Object next) {
		final String body = String.valueOf(next);
		final String signature = getSignature(variable);
		Frame nativeFrame = new Frame().addTypes(NATIVE).addFrame("body", formatBody(body, signature));
		nativeFrame.addFrame(GENERATED_LANGUAGE, generatedLanguage).addFrame("varName", variable.getName()).
			addFrame("container", cleanQn(buildContainerPath(variable.getContract(), variable.getContainer()))).
			addFrame("parentIntention", generatedLanguage).
			addFrame("interface", cleanQn(getInterface(variable))).
			addFrame("signature", signature).
			addFrame("className", variable.getName() + "_" + variable.getUID());
		frame.addFrame(NATIVE, nativeFrame);
	}

	public void fillFrameExpressionVariable(Frame frame, Variable variable, Object next) {
		final String body = String.valueOf(next);
		final String type = mask(variable.getType());
		final String signature = "public " + type + " value()";
		Frame nativeFrame = new Frame().addTypes(NATIVE).addFrame("body", formatBody(body, signature));
		nativeFrame.addFrame(GENERATED_LANGUAGE, generatedLanguage).addFrame("varName", variable.getName()).
			addFrame("container", buildContainerPathOfExpression(variable.getContainer())).
			addFrame("interface", "magritte.Expression<" + type + ">").
			addFrame("signature", signature).
			addFrame("className", variable.getName() + "_" + variable.getUID());
		frame.addFrame(NATIVE, nativeFrame);
	}

	public void fillFrameExpressionParameter(Frame frame, Parameter parameter, String body) {
		final String type = mask(parameter.getInferredType());
		final String signature = "public " + type + " value()";
		Frame nativeFrame = new Frame().addTypes(NATIVE).addFrame("body", formatBody(body, signature));
		nativeFrame.addFrame(GENERATED_LANGUAGE, generatedLanguage).addFrame("varName", parameter.getName()).
			addFrame("container", cleanQn(buildContainerPath(parameter.getContract(), parameter.getOwner()))).
			addFrame("interface", "magritte.Expression<" + type + ">").
			addFrame("signature", signature).
			addFrame("className", parameter.getName() + "_" + parameter.getUID());
		frame.addFrame(NATIVE, nativeFrame);
	}

	private String getScope(Parameter parameter) {
		if (parameter.getContract().contains(siani.tara.semantic.model.Variable.NATIVE_SEPARATOR)) {
			final String[] split = parameter.getContract().split(siani.tara.semantic.model.Variable.NATIVE_SEPARATOR);
			if (split.length == 3) return split[2].toLowerCase();
			else return language.languageName();
		} else return language.languageName();
	}

	private String getQn(Node owner, Node node, String language, boolean m0) {
		final FacetTarget facetTarget = facetTargetContainer(node);
		if (owner.isFacet())
			return composeMorphPackagePath(language) + DOT + owner.getName().toLowerCase() + DOT +
				Format.reference().format(owner.getName()) + "_" + Format.reference().format(facetTarget.getTarget());
		else
			return !m0 ? composeMorphPackagePath(language) + DOT + (facetTarget == null ? node.getQualifiedName() : composeInFacetTargetQN(node, facetTarget)) :
				language.toLowerCase() + DOT + node.getType();
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
		return variable.getContract().contains(NATIVE_SEPARATOR) ? variable.getContract().substring(0, variable.getContract().indexOf(NATIVE_SEPARATOR)) : variable.getContract();
	}

	private String getInterface(Parameter parameter) {
		if (!parameter.getContract().contains(NATIVE_SEPARATOR))
			return "";//throw new SemanticException(new SemanticError("reject.native.signature.notfound", new LanguageParameter(parameter)));
		return parameter.getContract().substring(0, parameter.getContract().indexOf(NATIVE_SEPARATOR));
	}

	private String getSignature(Parameter parameter) {
		return !parameter.getContract().contains(NATIVE_SEPARATOR) ? parameter.getContract() :
			parameter.getContract().split(NATIVE_SEPARATOR)[1];
	}

	private String getSignature(Variable variable) {
		return variable.getContract().substring(variable.getContract().indexOf(NATIVE_SEPARATOR) + 1);
	}

	private String buildContainerPath(String contract, NodeContainer owner) {
		if (owner instanceof Node)
			return getQn(firstNoFeatureAndNamed(owner), (Node) owner, withContract(contract, generatedLanguage), m0);
		return NameFormatter.getQn((FacetTarget) owner, withContract(contract, generatedLanguage));
	}

	private String withContract(String contract, String language) {
		if (contract.contains(siani.tara.semantic.model.Variable.NATIVE_SEPARATOR)) {
			final String[] split = contract.split(siani.tara.semantic.model.Variable.NATIVE_SEPARATOR);
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
			container = container.getContainer();
		}
		return null;
	}

	private FacetTarget facetTargetContainer(Node node) {
		NodeContainer container = node.getContainer();
		while (container != null) if (container instanceof FacetTarget) return (FacetTarget) container;
		else container = container.getContainer();
		return null;
	}

}
