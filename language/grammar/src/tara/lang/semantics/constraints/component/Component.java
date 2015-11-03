package tara.lang.semantics.constraints.component;

import tara.Resolver;
import tara.lang.model.Element;
import tara.lang.model.Node;
import tara.lang.model.Tag;
import tara.lang.model.rules.CompositionRule;
import tara.lang.semantics.SemanticError;
import tara.lang.semantics.SemanticException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Component implements tara.lang.semantics.Constraint.Component {
	private final String type;
	private final CompositionRule rule;
	private final List<Tag> annotations;

	public Component(String type, CompositionRule rule, List<Tag> annotations) {
		this.type = type;
		this.rule = rule;
		this.annotations = annotations;
	}

	@Override
	public String type() {
		return type;
	}

	public CompositionRule compositionRule() {
		return rule;
	}

	@Override
	public List<Tag> annotations() {
		return annotations;
	}

	@Override
	public void check(Element element) throws SemanticException {
		if (element instanceof Node) {
			Node node = (Node) element;
			List<Node> components = filterByType(node);
			if (rule.accept(components)) components.forEach(this::addFlags);
			else throw new SemanticException(new SemanticError(rule.errorMessage(), element, rule.errorParameters()));
		}
	}

	public void addFlags(Node node) {
		List<Tag> flags = new ArrayList<>(node.flags());
		for (Tag flag : this.annotations) {
			if (!flags.contains(flag)) node.addFlag(flag);
			flags.add(flag);
		}
	}

	private List<Node> filterByType(Node node) {
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

}
