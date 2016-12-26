package tara.lang.semantics.constraints.component;

import tara.Resolver;
import tara.lang.model.*;
import tara.lang.model.rules.NodeRule;
import tara.lang.model.rules.Size;
import tara.lang.semantics.errorcollector.SemanticException;
import tara.lang.semantics.errorcollector.SemanticNotification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static tara.lang.semantics.errorcollector.SemanticNotification.Level.ERROR;

public class Component implements tara.lang.semantics.Constraint.Component {
	private final String type;
	private final List<Rule> rules;
	private final List<Tag> annotations;

	public Component(String type, List<Rule> rules, List<Tag> annotations) {
		this.type = type;
		this.rules = rules;
		this.annotations = annotations;
	}

	@Override
	public String type() {
		return type;
	}

	public NodeRule compositionRule() {
		return (NodeRule) rules.stream().filter(r -> r instanceof NodeRule).findFirst().orElse(null);
	}

	public List<Rule> rules() {
		return rules;
	}

	@Override
	public List<Tag> annotations() {
		return annotations;
	}

	@Override
	public void check(Element element) throws SemanticException {
		Node container = (Node) element;
		if (container.isReference()) return;
		List<Node> components = filterByType(container);
		final List<Node> accepted = acceptedComponents(components);
		if (!accepted.isEmpty()) components.forEach(this::addFlags);
		final List<Node> notAccepted = notAccepted(components, accepted);
		if (!notAccepted.isEmpty()) error(notAccepted.get(0));
		else checkRequired(element, accepted);
	}

	private List<Node> acceptedComponents(List<Node> components) {
		return components.stream().filter(component -> rules.stream().allMatch(r -> accept(r, components, component))).collect(Collectors.toList());
	}

	private boolean accept(Rule r, List<Node> components, Node component) {
		return r instanceof Size ? r.accept(components) : r.accept(component);
	}

	private List<Node> notAccepted(List<Node> components, List<Node> accepted) {
		return components.stream().filter(c -> !accepted.contains(c)).collect(Collectors.toList());
	}

	public void error(Node notAccepted) throws SemanticException {
		for (Rule rule : rules)
			if (!accept(rule, notAccepted.container().components(), notAccepted))
				throw new SemanticException(new SemanticNotification(ERROR, rule.errorMessage(), notAccepted, rule.errorParameters()));
	}

	private void checkRequired(Element element, List<Node> accepted) throws SemanticException {
		if (rules instanceof Size && ((Size) rules).isRequired() && !isAccepted(accepted, type())) {
			String message = "required.type.in.context";
			List<?> parameters = Collections.singletonList(this.type.replace(":", " on "));
			throw new SemanticException(new SemanticNotification(ERROR, message, element, parameters));
		}
	}

	private boolean isAccepted(List<Node> accepted, String type) {
		for (Node node : accepted) if (node.type().equals(type)) return true;
		return false;
	}

	public void addFlags(Node node) {
		List<Tag> flags = new ArrayList<>(node.flags());
		for (Tag flag : this.annotations) {
			if (!flags.contains(flag)) node.addFlag(flag);
			flags.add(flag);
		}
	}

	private List<Node> filterByType(NodeContainer node) {
		return node.components().stream().filter(component -> areCompatibles(component, type())).collect(Collectors.toList());
	}

	private static boolean areCompatibles(Node node, String type) {
		for (String nodeType : node.types())
			if (nodeType != null && nodeType.equals(type)) return true;
		return checkFacets(node, type);
	}

	private static boolean checkFacets(Node node, String type) {
		for (tara.lang.model.Facet facet : node.facets())
			if (facet.type().equals(Resolver.shortType(type))) return true;
		return false;
	}

	@Override
	public String toString() {
		return "Component{" + type + '}';
	}
}
