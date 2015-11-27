package tara.lang.semantics.constraints.component;

import tara.Resolver;
import tara.lang.model.Element;
import tara.lang.model.Node;
import tara.lang.model.NodeContainer;
import tara.lang.model.Tag;
import tara.lang.model.rules.CompositionRule;
import tara.lang.model.rules.Size;
import tara.lang.semantics.errorcollector.SemanticException;
import tara.lang.semantics.errorcollector.SemanticNotification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static tara.lang.semantics.errorcollector.SemanticNotification.ERROR;

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
		NodeContainer container = (NodeContainer) element;
		List<Node> components = filterByType(container);
		if (rule.accept(components)) components.forEach(this::addFlags);
		else error(element, components);
	}

	public void error(Element element, List<Node> components) throws SemanticException {
		String message = rule.errorMessage();
		List<?> parameters = rule.errorParameters();
		Element destiny = element;
		if (rule instanceof Size) {
			if (components.isEmpty() && rule.isRequired()) {
				message = "required.type.in.context";
				parameters = Collections.singletonList(this.type);
			} else {
				destiny = components.get(0);
				if (!components.isEmpty() && rule.isSingle()) {
					message = "reject.multiple.type.in.context";
					parameters = Collections.singletonList(this.type);
				}
			}
		}
		throw new SemanticException(new SemanticNotification(ERROR, message, destiny, parameters));
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
