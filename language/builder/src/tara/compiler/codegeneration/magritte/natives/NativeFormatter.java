package tara.compiler.codegeneration.magritte.natives;

import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.Format;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.lang.model.*;
import tara.lang.model.rules.variable.NativeRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static tara.compiler.codegeneration.magritte.NameFormatter.cleanQn;
import static tara.lang.model.Tag.Feature;
import static tara.lang.model.Tag.Instance;

@SuppressWarnings("ALL")
public class NativeFormatter implements TemplateTags {

	private final String generatedLanguage;
	private final Language language;
	private final String aPackage;
	private final boolean m0;

	public NativeFormatter(String generatedLanguage, Language language, String aPackage, boolean m0) {
		this.generatedLanguage = generatedLanguage;
		this.language = language;
		this.aPackage = aPackage;
		this.m0 = m0;
	}

	public void fillFrameForNativeVariable(Frame frame, Variable variable, Object body) {
		final List<String> slots = Arrays.asList(frame.slots());
		final String signature = NativeFormatter.getSignature(variable);
		NativeExtractor extractor = new NativeExtractor(signature);
		frame.addFrame(PACKAGE, this.aPackage);
		final List<String> imports = ((NativeRule) variable.rule()).imports();
		frame.addFrame(IMPORTS, imports.toArray(new String[imports.size()]));
		if (!slots.contains(LANGUAGE.toLowerCase())) frame.addFrame(LANGUAGE, generatedLanguage.toLowerCase());
		if (!slots.contains(GENERATED_LANGUAGE.toLowerCase()))
			frame.addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
		if (!slots.contains(RULE.toLowerCase())) frame.addFrame(RULE, cleanQn(NativeFormatter.getInterface(variable)));
		if (!slots.contains(NAME.toLowerCase())) frame.addFrame(NAME, variable.name());
		if (!slots.contains(QN.toLowerCase())) frame.addFrame(QN, variable.container().qualifiedName());
		frame.addFrame(FILE, variable.file());
		frame.addFrame(LINE, variable.line());
		frame.addFrame(COLUMN, variable.column());
		if (body != null) frame.addFrame(BODY, NativeFormatter.formatBody(body.toString(), signature));
		frame.addFrame(NATIVE_CONTAINER, cleanQn(buildContainerPath(((NativeRule) variable.rule()).getLanguage(), variable.container(), language, generatedLanguage)));
		frame.addFrame(SIGNATURE, signature);
		frame.addFrame(UID, variable.getUID());
		frame.addFrame("methodName", extractor.methodName());
		frame.addFrame("parameters", extractor.parameters());
		frame.addFrame("returnType", extractor.returnValue());
	}

	public void fillFrameForNativeParameter(Frame frame, Parameter parameter, Object body) {
		final List<String> slots = Arrays.asList(frame.slots());
		final String signature = NativeFormatter.getSignature(parameter);
		NativeExtractor extractor = new NativeExtractor(signature);
		if (!slots.contains(GENERATED_LANGUAGE.toLowerCase()))
			frame.addFrame(GENERATED_LANGUAGE, this.generatedLanguage);
		if (!slots.contains(NAME.toLowerCase())) frame.addFrame(NAME, parameter.name());
		if (!this.aPackage.isEmpty()) frame.addFrame(PACKAGE, this.aPackage.toLowerCase());
		if (!slots.contains(QN.toLowerCase())) frame.addFrame(QN, parameter.container().qualifiedName());
		if (!slots.contains(LANGUAGE.toLowerCase()))
			frame.addFrame(LANGUAGE, NativeFormatter.getLanguageScope(parameter, language));
		if (!slots.contains(RULE.toLowerCase())) frame.addFrame(RULE, cleanQn(NativeFormatter.getInterface(parameter)));
		final List<String> imports = ((NativeRule) parameter.rule()).imports();
		frame.addFrame(IMPORTS, imports.toArray(new String[imports.size()]));
		frame.addFrame(SIGNATURE, signature);
		frame.addFrame(FILE, parameter.file());
		frame.addFrame(LINE, parameter.line());
		frame.addFrame(COLUMN, parameter.column());
		frame.addFrame(NATIVE_CONTAINER, cleanQn(buildContainerPath(((NativeRule) parameter.rule()).getLanguage(), parameter.container(), language, generatedLanguage)));
		frame.addFrame(UID, parameter.getUID());
		frame.addFrame("methodName", extractor.methodName());
		frame.addFrame("parameters", extractor.parameters());
		if (body != null) frame.addFrame(BODY, NativeFormatter.formatBody(body.toString(), signature));
		frame.addFrame("returnType", extractor.returnValue());
	}


	public void fillFrameExpressionVariable(Frame frame, Variable variable, Object body) {
		final List<String> slots = Arrays.asList(frame.slots());
		frame.addFrame(FILE, variable.file());
		frame.addFrame(LINE, variable.line());
		frame.addFrame(COLUMN, variable.column());
		if (!aPackage.isEmpty()) frame.addFrame(PACKAGE, aPackage.toLowerCase());
		if (!slots.contains(NAME.toLowerCase())) frame.addFrame(NAME, variable.name());
		if (!slots.contains(GENERATED_LANGUAGE.toLowerCase())) frame.addFrame(GENERATED_LANGUAGE, generatedLanguage);
		frame.addFrame(NATIVE_CONTAINER.toLowerCase(), buildContainerPathOfExpression(variable, generatedLanguage, m0));
		if (!slots.contains(TYPE.toLowerCase())) frame.addFrame(TYPE, variable.type().javaName());
		frame.addFrame(UID, variable.getUID());
		if (body != null) frame.addFrame(BODY, formatBody(body.toString(), variable.type().getName()));
	}

	public void fillFrameExpressionParameter(Frame frame, Parameter parameter, String body) {
		final List<String> slots = Arrays.asList(frame.slots());
		frame.addFrame(FILE, parameter.file());
		frame.addFrame(LINE, parameter.line());
		frame.addFrame(COLUMN, parameter.column());
		frame.addFrame(NATIVE_CONTAINER, buildContainerPathOfExpression(parameter, language, generatedLanguage));
		frame.addFrame(UID, parameter.getUID());
		if (!aPackage.isEmpty()) frame.addFrame(PACKAGE, aPackage.toLowerCase());
		if (!slots.contains(NAME.toLowerCase())) frame.addFrame(NAME, parameter.name());
		if (!slots.contains(GENERATED_LANGUAGE.toLowerCase()))
			frame.addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
		if (!slots.contains(TYPE.toLowerCase())) frame.addFrame(TYPE, parameter.type().javaName());
		if (body != null) frame.addFrame(BODY, formatBody(body, parameter.type().getName()));
	}

	public static String getLanguageScope(Parameter parameter, Language language) {
		final NativeRule rule = (NativeRule) parameter.rule();
		if (rule.getLanguage() != null) return rule.getLanguage();
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
			Format.reference().format(owner.name()) + Format.reference().format(facetTarget.target());
	}

	public static String getSignature(Parameter parameter) {
		return ((NativeRule) parameter.rule()).signature();
	}

	public static String getInterface(Parameter parameter) {
		final NativeRule rule = (NativeRule) parameter.rule();
		if (rule.interfaceClass() == null)
			return "";//throw new SemanticException(new SemanticError("reject.native.signature.notfound", new LanguageParameter(parameter)));
		return rule.interfaceClass();
	}

	public static String getInterface(Variable variable) {
		final NativeRule rule = (NativeRule) variable.rule();
		if (rule.interfaceClass() == null)
			return "";//throw new SemanticException(new SemanticError("reject.native.signature.notfound", new LanguageParameter(parameter)));
		return rule.interfaceClass();
	}

	public static String buildContainerPathOfExpression(Variable variable, String generatedLanguage, boolean m0) {
		final NodeContainer container = variable.container();
		if (container instanceof Node)
			return getQn(firstNoFeatureAndNamed(container), (Node) container, generatedLanguage, m0);
		return NameFormatter.getQn((FacetTarget) container, generatedLanguage);
	}

	public static String buildContainerPathOfExpression(Parameter parameter, Language language, String generatedLanguage) {
		if (parameter.container() instanceof Node)
			return buildExpressionContainerPath((NativeRule) parameter.rule(), parameter.container(), language, generatedLanguage);
		return "";//QualifiedNameFormatter.getQn((Facet) parameter.container(), generatedLanguage);
	}

	public static String formatBody(String body, String signature) {
		final String returnText = RETURN + " ";
		String formattedBody = body.endsWith(";") || body.endsWith("}") ? body : body + ";";
		if (!signature.contains(" void ") && !formattedBody.contains("\n") && !formattedBody.startsWith(returnText))
			return returnText + formattedBody;
		return formattedBody;
	}

	public static String getReturn(String body) {
		final String returnText = RETURN + " ";
		body = body.endsWith(";") || body.endsWith("}") ? body : body + ";";
		if (!body.contains("\n") && !body.startsWith(returnText))
			return returnText;
		return "";
	}


	public static String getSignature(Variable variable) {
		return ((NativeRule) variable.rule()).signature();
	}

	public static String buildContainerPath(String ruleLanguage, NodeContainer owner, Language language, String generatedLanguage) {
		if (owner instanceof Node && ((Node) owner).facetTarget() == null) {
			final Node scope = ((Node) owner).is(Instance) ? firstNoFeature(owner) : firstNoFeatureAndNamed(owner);
			if (scope == null) return "";
			if (scope.is(Instance)) return getTypeAsScope(scope, ruleLanguage);
			if (scope.facetTarget() != null) return NameFormatter.getQn(((Node) scope).facetTarget(), scope, generatedLanguage);
			return getQn(scope, (Node) owner, generatedLanguage, false);
		} else if (owner instanceof Node) return NameFormatter.getQn(((Node) owner).facetTarget(), (Node) owner, generatedLanguage);
		else if (owner instanceof Facet) {
			final Node parent = firstNoFeatureAndNamed(owner);
			if (parent == null) return "";
			return parent.is(Instance) ? getTypeAsScope(parent, language.languageName()) : getQn(parent, ruleLanguage, false);
		} else return "";
	}

	public static String buildExpressionContainerPath(NativeRule rule, NodeContainer owner, Language language, String generatedLanguage) {
		final String ruleLanguage = extractLanguageScope(rule, generatedLanguage);
		if (owner instanceof Node) {
			final Node scope = ((Node) owner).is(Instance) ? firstNoFeature(owner) : firstNoFeatureAndNamed(owner);
			if (scope == null) return "";
			if (scope.is(Instance)) return getTypeAsScope(scope, ruleLanguage);
			else return getQn(scope, (Node) owner, generatedLanguage, false);
		} else if (owner instanceof FacetTarget)
			return NameFormatter.getQn((FacetTarget) owner, generatedLanguage);
		else if (owner instanceof Facet) {
			final Node parent = firstNoFeatureAndNamed(owner);
			if (parent == null) return "";
			return parent.is(Instance) ? getTypeAsScope(parent, language.languageName()) : getQn(parent, generatedLanguage, false);
		} else return "";
	}

	private static String extractLanguageScope(NativeRule rule, String language) {
		return rule != null ? rule.getLanguage() : language;
	}


	private static String getTypeAsScope(Node scope, String language) {
		return language.toLowerCase() + NameFormatter.DOT + cleanQn(scope.type());
	}

	private static Node firstNoFeature(NodeContainer owner) {
		NodeContainer container = owner;
		while (container != null) {
			if (container instanceof Node && !(container instanceof NodeRoot) && !((Node) container).is(Feature)) return (Node) container;
			container = container.container();
		}
		return null;
	}

	private static Node firstNoFeatureAndNamed(NodeContainer owner) {
		NodeContainer container = owner;
		while (container != null) {
			if (container instanceof Node && !(container instanceof NodeRoot) && !((Node) container).isAnonymous() &&
				!((Node) container).is(Feature)) return (Node) container;
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

}
