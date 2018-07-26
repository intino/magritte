package io.intino.tara.lang.semantics.constraints.component;

import io.intino.tara.Resolver;
import io.intino.tara.lang.model.*;
import io.intino.tara.lang.model.rules.NodeRule;
import io.intino.tara.lang.model.rules.Size;
import io.intino.tara.lang.semantics.Constraint;
import io.intino.tara.lang.semantics.errorcollector.SemanticException;
import io.intino.tara.lang.semantics.errorcollector.SemanticNotification;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static io.intino.tara.lang.semantics.errorcollector.SemanticNotification.Level.ERROR;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class OneOf implements Constraint.OneOf {
	private final List<Rule> rules;
	private final List<Component> constraints;

	public OneOf(List<Component> constraints, List<Rule> rules) {
		this.rules = rules;
		this.constraints = constraints;
	}

	@Override
	public void check(Element element) throws SemanticException {
		Node node = (Node) element;
		List<String> requireTypes = new ArrayList<>();
		Set<Node> accepted = new HashSet<>();
		for (Constraint constraint : constraints) {
			requireTypes.add(Resolver.shortType(((Component) constraint).type()));
			try {
				constraint.check(node);
			} catch (SemanticException ignored) {
			}
			final List<Node> components = filterByType(node, (Component) constraint);
			final List<Node> acceptedNodes = acceptedComponents(components);
			accepted.addAll(acceptedNodes);
			final List<Node> notAccepted = notAccepted(components, acceptedNodes);
			if (!notAccepted.isEmpty()) error(notAccepted.get(0));
		}
		Size size = (Size) rules.stream().filter(r -> r instanceof Size).findFirst().orElse(null);
		if (size != null) {
			if (accepted.isEmpty() && size.isRequired())
				throw new SemanticException(new SemanticNotification(ERROR, "required.any.type.in.context", element, singletonList(String.join(", ", requireTypes))));
			else if (size.max() < accepted.size())
				throw new SemanticException(new SemanticNotification(ERROR, "reject.much.types.in.context", element, asList(size.max(), String.join(", ", requireTypes))));
		}
	}

	@Override
	public String type() {
		return "";
	}

	@Override
	public NodeRule compositionRule() {
		return (NodeRule) rules.stream().filter(r -> r instanceof NodeRule).findFirst().orElse(null);
	}

	@Override
	public List<Rule> rules() {
		return rules;
	}

	@Override
	public List<Tag> annotations() {
		Set<Tag> tags = new HashSet<>();
		constraints.forEach(c -> tags.addAll(c.annotations()));
		return new ArrayList<>(tags);
	}

	private List<Node> filterByType(NodeContainer node, Constraint.Component constraint) {
		return node.components().stream().filter(component -> areCompatibles(component, constraint.type())).collect(Collectors.toList());
	}


	private static boolean areCompatibles(Node node, String type) {
		for (String nodeType : node.types())
			if (nodeType != null && nodeType.equals(type)) return true;
		return checkFacets(node, type);
	}

	private static boolean checkFacets(Node node, String type) {
		for (io.intino.tara.lang.model.Facet facet : node.facets())
			if (facet.type().equals(Resolver.shortType(type))) return true;
		return false;
	}


	private List<Node> acceptedComponents(List<Node> components) {
		return components.stream().filter(component -> rules.stream().allMatch(r -> accept(r, components, component))).collect(Collectors.toList());
	}

	private List<Node> notAccepted(List<Node> components, List<Node> accepted) {
		return components.stream().filter(c -> !accepted.contains(c)).collect(Collectors.toList());
	}

	private boolean accept(Rule r, List<Node> components, Node component) {
		return r instanceof Size ? r.accept(components) : r.accept(component);
	}

	private void error(Node notAccepted) throws SemanticException {
		for (Rule rule : rules)
			if (!accept(rule, notAccepted.container().components(), notAccepted))
				throw new SemanticException(new SemanticNotification(ERROR, rule.errorMessage(), notAccepted, rule.errorParameters()));
	}

	@Override
	public List<Component> components() {
		return constraints;
	}

	@Override
	public String toString() {
		List<String> types = constraints.stream().map(Component::type).collect(Collectors.toList());
		return "OneOf{" + String.join(", ", types) + '}';
	}

}
