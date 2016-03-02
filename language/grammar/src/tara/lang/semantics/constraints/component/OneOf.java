package tara.lang.semantics.constraints.component;

import tara.Resolver;
import tara.lang.model.Element;
import tara.lang.model.Node;
import tara.lang.model.NodeContainer;
import tara.lang.model.Tag;
import tara.lang.model.rules.CompositionRule;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.errorcollector.SemanticException;
import tara.lang.semantics.errorcollector.SemanticNotification;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static tara.lang.semantics.errorcollector.SemanticNotification.ERROR;

public class OneOf implements Constraint.OneOf {
	private final CompositionRule compositionRule;
	private final List<Component> constraints;

	public OneOf(List<Component> constraints, CompositionRule compositionRule) {
		this.compositionRule = compositionRule;
		this.constraints = constraints;
	}

	@Override
	public void check(Element element) throws SemanticException {
		Node node = (Node) element;
		List<String> requireTypes = new ArrayList<>();
		int acceptedTypes = 0;
		for (Constraint constraint : constraints) {
			requireTypes.add(Resolver.shortType(((Component) constraint).type()));
			try {
				constraint.check(node);
				acceptedTypes += filterByType(node, (Component) constraint).size();
			} catch (SemanticException ignored) {
			}
		}
		if (acceptedTypes == 0 && this.compositionRule.isRequired())
			throw new SemanticException(new SemanticNotification(ERROR, "required.any.type.in.context", element, singletonList(String.join(", ", requireTypes))));
		else if (compositionRule().max() < acceptedTypes)
			throw new SemanticException(new SemanticNotification(ERROR, "reject.much.types.in.context", element, asList(compositionRule.max(), String.join(", ", requireTypes))));
	}


	@Override
	public String type() {
		return "";
	}

	@Override
	public CompositionRule compositionRule() {
		return this.compositionRule;
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
		for (tara.lang.model.Facet facet : node.facets())
			if (facet.type().equals(Resolver.shortType(type))) return true;
		return false;
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
