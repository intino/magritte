package tara.lang.semantics.constraints.component;

import tara.Resolver;
import tara.lang.model.Element;
import tara.lang.model.Node;
import tara.lang.model.NodeContainer;
import tara.lang.model.Tag;
import tara.lang.model.rules.CompositionRule;
import tara.lang.model.rules.Size;
import tara.lang.model.rules.composition.NodeRule;
import tara.lang.semantics.errorcollector.SemanticException;
import tara.lang.semantics.errorcollector.SemanticNotification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static tara.lang.model.Tag.Instance;
import static tara.lang.semantics.errorcollector.SemanticNotification.Level.ERROR;

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
		Node container = (Node) element;
		if (container.isReference()) return;
		List<Node> components = filterByType(container);
		final List<Node> accepted = acceptedComponents(components);
		if (!accepted.isEmpty()) {
			components.forEach(this::addFlags);
			if (rule.into() != null && !annotations.contains(Instance))
				components.stream().filter(c -> container.ruleOf(c) != null).forEach(c -> container.ruleOf(c).is(rule.into()));
		}
		final List<Node> notAccepted = notAccepted(components, accepted);
		if (!notAccepted.isEmpty()) error(element, notAccepted);
	}

	private List<Node> acceptedComponents(List<Node> components) {
		return components.stream().filter(component -> rule.accept(Collections.singletonList(component)) && rule.max() >= components.size() && rule.min() <= components.size()).collect(Collectors.toList());
	}

	private List<Node> notAccepted(List<Node> components, List<Node> accepted) {
		return components.stream().filter(c -> !accepted.contains(c)).collect(Collectors.toList());
	}

	public void error(Element element, List<Node> components) throws SemanticException {
		String message = rule.errorMessage();
		List<?> parameters = rule.errorParameters();
		Element destiny = element;
		if (rule instanceof Size || rule instanceof NodeRule) {
			if (components.isEmpty() && rule.isRequired()) {
				message = "required.type.in.context";
				parameters = Collections.singletonList(this.type.replace(":", " on "));
			} else {
				destiny = components.get(0);
				if (components.size() > 1 && rule.isSingle()) {
					message = "reject.multiple.type.in.context";
					parameters = Collections.singletonList(this.type.replace(":", " on "));
				} else if (rule instanceof NodeRule) message = rule.errorMessage();
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
