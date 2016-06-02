package tara.compiler.codegeneration.magritte.natives;

import org.siani.itrules.model.Frame;
import tara.Language;
import tara.Resolver;
import tara.compiler.codegeneration.Format;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.model.Model;
import tara.lang.model.*;
import tara.lang.model.rules.variable.NativeRule;

import java.util.ArrayList;
import java.util.List;

import static tara.lang.model.Tag.Feature;
import static tara.lang.model.Tag.Instance;

public class NativeHelper {
	static Frame typeFrame(String type, boolean multiple) {
		return multiple ? new Frame().addTypes("type", "list").addFrame("value", type) : new Frame().addTypes("type").addFrame("value", type);
	}

	static String getLanguageScope(Parameter parameter, Language language) {
		return parameter.scope() != null && !parameter.scope().isEmpty() ? parameter.scope() : language.languageName();
	}

	public static String getQn(Node owner, Node node, String language, boolean m0) {
		final FacetTarget facetTarget = isInFacetTarget(node);
		if (owner.isFacet() && facetTarget != null) return asFacetTarget(owner, language, facetTarget);
		else return asNode(owner, language, m0, facetTarget);
	}

	private static String asNode(Node node, String language, boolean m0, FacetTarget facetTarget) {
		return !m0 ? language.toLowerCase() + NativeFormatter.DOT + (facetTarget == null ? node.qualifiedName() : NameFormatter.composeInFacetTargetQN(node, facetTarget)) :
			language.toLowerCase() + NativeFormatter.DOT + node.type();
	}

	private static String asFacetTarget(Node owner, String language, FacetTarget facetTarget) {
		return language.toLowerCase() + NativeFormatter.DOT + owner.name().toLowerCase() + NativeFormatter.DOT +
			Format.reference().format(owner.name()) + Format.reference().format(facetTarget.target());
	}

	static String getSignature(Parameter parameter) {
		return ((NativeRule) parameter.rule()).signature();
	}

	static String getInterface(Valued variable) {
		final NativeRule rule = (NativeRule) variable.rule();
		if (rule.interfaceClass() == null)
			return "";
		return rule.interfaceClass();
	}

	static String buildContainerPathOfExpression(Valued valued, String generatedLanguage) {
		return buildExpressionContainerPath(valued.scope(), valued.container(), generatedLanguage);
	}

	static String formatBody(String body, String signature) {
		final String returnText = NativeFormatter.RETURN + " ";
		String formattedBody = body.endsWith(";") || body.endsWith("}") ? body : body + ";";
		if (!signature.contains(" void ") && !formattedBody.contains("\n") && !formattedBody.startsWith(returnText))
			return returnText + formattedBody;
		return formattedBody;
	}

	static String getSignature(Variable variable) {
		return ((NativeRule) variable.rule()).signature();
	}

	static String buildContainerPath(String scopeLanguage, NodeContainer owner, String generatedLanguage) {
		if (((Node) owner).facetTarget() == null) {
			final Node scope = ((Node) owner).is(Instance) ? firstNoFeature(owner) : firstNoFeatureAndNamed(owner);
			if (scope == null) return "";
			if (scope.is(Instance)) return getTypeAsScope(scope, scopeLanguage);
			if (scope.facetTarget() != null) return NameFormatter.getQn(scope.facetTarget(), scope, generatedLanguage);
			return getQn(scope, (Node) owner, generatedLanguage, false);
		} else return NameFormatter.getQn(((Node) owner).facetTarget(), (Node) owner, generatedLanguage);
	}

	private static String buildExpressionContainerPath(String scopeLanguage, Node owner, String generatedLanguage) {
		final String ruleLanguage = extractLanguageScope(scopeLanguage, generatedLanguage);
		final Node scope = owner.is(Instance) ? firstNoFeature(owner) : firstNoFeatureAndNamed(owner);
		if (scope == null) return "";
		if (scope.is(Instance)) return getTypeAsScope(scope, ruleLanguage);
		if (scope.facetTarget() != null) return NameFormatter.getQn(scope.facetTarget(), scope, generatedLanguage);
		return getQn(scope, owner, generatedLanguage, false);
	}

	private static String extractLanguageScope(String scope, String language) {
		return scope != null && !scope.isEmpty() ? scope : language;
	}

	private static String getTypeAsScope(NodeContainer scope, String language) {
		return language.toLowerCase() + NativeFormatter.DOT +
			(scope instanceof Node ? NameFormatter.cleanQn(scope.type()) : NameFormatter.cleanQn(facetType((Facet) scope)));
	}

	private static String facetType(Facet scope) {
		return scope.type().toLowerCase() + NativeFormatter.DOT + scope.type() + Resolver.shortType(scope.container().type());//TODO cuando la faceta esté contenido dentro de otro concepto como saberlo
	}

	private static Node firstNoFeature(NodeContainer owner) {
		NodeContainer container = owner;
		while (container != null) {
			if (container instanceof Node && !(container instanceof NodeRoot) && !((Node) container).is(Feature)) return (Node) container;
			container = container.container();
		}
		return owner instanceof Node ? (Node) owner : owner.container();
	}

	private static Node firstNoFeatureAndNamed(NodeContainer owner) {
		NodeContainer container = owner;
		while (container != null) {
			if (container instanceof Node && !(container instanceof NodeRoot) && !((Node) container).isAnonymous() &&
				!((Node) container).is(Feature)) return (Node) container;
			container = container.container();
		}
		return owner instanceof Node ? (Node) owner : owner.container();
	}

	public static Model model(NodeContainer owner) {
		NodeContainer container = owner;
		while (container != null) {
			if (container instanceof Node && container instanceof Model) return (Model) container;
			container = container.container();
		}
		return null;
	}

	private static FacetTarget isInFacetTarget(Node node) {
		Node container = node.container();
		while (container != null && container.facetTarget() == null)
			container = container.container();
		return container != null ? container.facetTarget() : null;
	}

	public static String calculatePackage(Node container) {
		final Node node = firstNamedContainer(container);
		return node == null ? "" : node.cleanQn().replace("$", ".").replace("#", ".").toLowerCase();
	}

	private static Node firstNamedContainer(Node container) {
		List<Node> containers = collectStructure(container);
		for (Node node : containers)
			if (node != null && !node.isAnonymous()) return node;
		return null;
	}

	private static List<Node> collectStructure(Node container) {
		List<Node> containers = new ArrayList<>();
		Node current = container;
		while (current != null && !(current instanceof NodeRoot)) {
			containers.add(0, current);
			current = current.container();
		}
		return containers;
	}
}