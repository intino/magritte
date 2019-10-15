package io.intino.tara.compiler.dependencyresolution;

import io.intino.tara.compiler.core.errorcollection.DependencyException;
import io.intino.tara.compiler.model.Model;
import io.intino.tara.compiler.model.NodeImpl;
import io.intino.tara.compiler.model.NodeReference;
import io.intino.tara.compiler.shared.Configuration;
import io.intino.tara.dsl.ProteoConstants;
import io.intino.tara.lang.model.*;
import io.intino.tara.lang.model.rules.Size;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class InheritanceResolver {
	private static final Logger LOG = Logger.getGlobal();

	private Model model;

	public InheritanceResolver(Model model) {
		this.model = model;
	}


	public void resolve() throws DependencyException {
		mergeFragmentNodes();
		List<Node> nodes = new ArrayList<>(collectNodes(model));
		sort(nodes);
		for (Node node : nodes) resolve(node);//TODO añadir la herencia de aspectos a la resolución
	}

	private void resolve(Node node) throws DependencyException {
		List<Node> children = getChildrenSorted(node);
		if (!node.isAbstract() && !node.subs().isEmpty()) node.addFlag(Tag.Abstract);
		for (Node child : children) resolve(node, child);
	}

	private void resolve(Node node, Node child) throws DependencyException {
		resolveComponents(node, child);
		resolveVariables(node, child);
		resolveFlags(node, child);
		resolveAnnotations(node, child);
		resolveAllowedAspects(node, child);
		resolveAppliedAspects(node, child);
		resolveNodeRules(node, child);
		resolve(child);
	}

	private void mergeFragmentNodes() throws DependencyException {
		if (model.level().equals(Configuration.Level.Solution)) return;
		for (List<Node> nodes : fragmentNodes().values()) merge(nodes);
	}

	private void merge(List<Node> nodes) throws DependencyException {
		if (nodes.size() <= 1) return;
		if (!correctParent(nodes))
			throw new DependencyException("Error merging extension elements. Parents are not homogeneous.", nodes.get(0));
		Node destination = nodes.get(0);
		if (destination == null) return;
		nodes.remove(destination);
		for (Node node : nodes) {
			((NodeImpl) destination).absorb((NodeImpl) node);
			model.remove(node);
		}
	}

	private boolean correctParent(List<Node> nodes) {
		String parent = nodes.get(0).parentName() == null ? "" : nodes.get(0).parentName();
		for (Node node : nodes) if (!parent.equals(node.parentName() == null ? "" : node.parentName())) return false;
		return true;
	}

	private Map<String, List<Node>> fragmentNodes() {
		Map<String, List<Node>> toMerge = new LinkedHashMap<>();
		for (Node node : model.components()) {
			if (node.isAnonymous()) continue;
			if (!toMerge.containsKey(name(node)))
				toMerge.put(name(node), new ArrayList<>());
			toMerge.get(name(node)).add(node);
		}
		return toMerge;
	}

	private String name(Node node) {
		return node.name() + (node.isAspect() ? ProteoConstants.ASPECT : "");
	}


	private void resolveNodeRules(Node parent, Node child) {
		List<Rule> parentRules = parent.container().rulesOf(parent);
		List<Rule> childRules = child.container().rulesOf(child);
		Size size = child.container().sizeOf(child);
		for (Rule rule : parentRules)
			if (!(rule instanceof Size)) childRules.add(rule);
			else if (isMoreRestrictiveThan((Size) rule, size)) {
				childRules.remove(size);
				childRules.add(rule);
			}
	}

	private boolean isMoreRestrictiveThan(Size parent, Size child) {
		return parent.min() > child.min() || parent.max() < child.max();
	}

	private void resolveAllowedAspects(Node parent, Node child) {
	}

	private void resolveAppliedAspects(Node parent, Node child) {
		parent.appliedAspects().stream().filter(facet -> !isOverridden(child, facet)).forEach(child::applyAspects);
	}

	private boolean isOverridden(Node child, Aspect aspect) {
		for (Aspect childAspect : child.appliedAspects()) if (childAspect.type().equals(aspect.type())) return true;
		return false;
	}

	private Set<Node> collectNodes(Model model) {
		Set<Node> collection = new HashSet<>();
		for (Node node : model.components()) {
			if (!node.children().isEmpty()) collection.add(node);
			collect(node, collection);
		}
		return collection;
	}

	private void collect(Node node, Set<Node> collection) {
		if (!(node instanceof NodeImpl)) return;
		if (!node.children().isEmpty()) collection.add(node);
		for (Node component : node.components()) collect(component, collection);
	}

	private void resolveComponents(Node parent, Node child) {
		Map<Node, List<Rule>> nodes = new LinkedHashMap<>();
		for (Node component : parent.components()) {
			if (isOverridden(child, component)) continue;
			NodeReference reference = component.isReference() ? new NodeReference(((NodeReference) component).destination()) : new NodeReference((NodeImpl) component);
			addTags(component, reference);
			reference.setHas(false);
			reference.file(child.file());
			reference.line(child.line());
			reference.container(child);
			nodes.put(reference, component.container().rulesOf(component));
		}
		for (Map.Entry<Node, List<Rule>> entry : nodes.entrySet())
			child.add(entry.getKey(), entry.getValue());
	}

	private void addTags(Node component, NodeReference reference) {
		component.flags().stream().filter(tag -> !reference.flags().contains(tag) && Flags.forReference().contains(tag)).forEach(reference::addFlag);
		component.annotations().stream().filter(tag -> !reference.annotations().contains(tag)).forEach(reference::addAnnotations);
	}

	private void resolveFlags(Node parent, Node child) {
		parent.flags().stream().
				filter(tag -> !tag.equals(Tag.Abstract) && !child.flags().contains(tag)).
				forEach(child::addFlag);
	}

	private void resolveAnnotations(Node parent, Node child) {
		parent.annotations().stream().filter(tag -> !tag.equals(Tag.Abstract) && !child.annotations().contains(tag)).forEach(child::addAnnotations);
	}

	private void resolveVariables(Node parent, Node child) {
		List<Variable> variables = new ArrayList<>();
		for (Variable variable : parent.variables())
			if (isOverridden(child, variable)) {
				final Variable overridenVariable = findVariable(child, variable.name());
				if (overridenVariable != null) {
					overridenVariable.addFlags(variable.flags().toArray(new Tag[0]));
					overridenVariable.overriden(true);
				}
			} else variables.add(variable.cloneIt(child));
		child.add(0, variables.toArray(new Variable[0]));
	}

	private Variable findVariable(Node child, String name) {
		for (Variable variable : child.variables()) if (variable.name().equals(name)) return variable;
		return null;
	}

	private boolean isOverridden(NodeContainer child, Node node) {
		for (Node c : child.components())
			if (!isHasReference(c) && areNamesake(node, c) && c.type().equals(node.type())) {
				if (c instanceof NodeImpl && c.parent() == null) ((NodeImpl) c).setParent(node);
				return true;
			}
		return false;
	}

	private boolean areNamesake(Node node, Node c) {
		return c.name() != null && c.name().equals(node.name());
	}

	private boolean isHasReference(Node component) {
		return component instanceof NodeReference && ((NodeReference) component).isHas();
	}

	private boolean isOverridden(Node child, Variable variable) {
		return child.variables().stream().anyMatch(childVar -> childVar.name().equals(variable.name()) && childVar.type().equals(variable.type()));
	}

	private List<Node> getChildrenSorted(Node parent) {
		List<Node> children = new ArrayList<>(parent.children());
		sort(children);
		return children;
	}

	private void sort(List<Node> nodes) {
		if (nodes.isEmpty()) return;
		nodes.sort(inheritanceComparator());
		Collections.reverse(nodes);
	}

	private Comparator<Node> inheritanceComparator() {
		return new Comparator<>() {
			@Override
			public int compare(Node o1, Node o2) {
				return maxLevel(o1) - maxLevel(o2);
			}

			private int maxLevel(Node node) {
				List<Integer> levels = new ArrayList<>(Collections.singletonList(0));
				levels.addAll(node.children().stream().map(this::maxLevel).collect(Collectors.toList()));
				levels.sort(Collections.reverseOrder());
				return 1 + levels.get(0);
			}

			@Override
			public boolean equals(Object obj) {
				return false;
			}

			@Override
			public int hashCode() {
				return super.hashCode();
			}
		};
	}
}
