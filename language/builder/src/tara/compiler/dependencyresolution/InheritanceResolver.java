package tara.compiler.dependencyresolution;

import tara.compiler.core.errorcollection.DependencyException;
import tara.compiler.model.Model;
import tara.compiler.model.NodeImpl;
import tara.compiler.model.NodeReference;
import tara.lang.model.*;
import tara.lang.model.rules.CompositionRule;

import java.util.*;
import java.util.stream.Collectors;

public class InheritanceResolver {

	Model model;

	public InheritanceResolver(Model model) {
		this.model = model;
	}


	public void resolve() throws DependencyException {
		List<NodeImpl> nodes = new ArrayList<>();
		nodes.addAll(collectNodes(model));
		sort(nodes);
		for (NodeImpl node : nodes) resolve(node);
		for (Node node : model.components()) resolveAsFacetTargetFragment((NodeImpl) node);
		mergeFragmentNodes(model);
	}

	private void resolve(NodeImpl node) throws DependencyException {
		List<NodeImpl> children = getChildrenSorted(node);
		if (!children.isEmpty() && !node.isAbstract() && node.isSub()) node.addFlag(Tag.Abstract);
		for (NodeImpl child : children) resolve(node, child);
		resolveAsFacetTargetFragment(node);
	}

	private void resolve(NodeImpl node, NodeImpl child) throws DependencyException {
		resolveComponents(node, child);
		resolveVariables(node, child);
		resolveFlags(node, child);
		resolveAnnotations(node, child);
		resolveAllowedFacets(node, child);
		resolveAppliedFacets(node, child);
		resolveFacetTarget(node, child);
		resolveCompositionRule(node, child);
		resolve(child);
	}

	private void mergeFragmentNodes(Model model) throws DependencyException {
		Map<String, List<Node>> toMerge = fragmentNodes(model);
		for (List<Node> nodes : toMerge.values()) merge(nodes);
		for (List<Node> nodes : toMerge.values()) for (int i = 1; i < nodes.size(); i++) model.remove(nodes.get(i));
	}

	private void merge(List<Node> nodes) throws DependencyException {
		if (nodes.size() < 2) return;
		if (!correctParent(nodes))
			throw new DependencyException("Error merging extension elements. Parents are not homogeneous.", nodes.get(0));
		Node target = nodes.get(0);
		for (Node node : nodes.subList(1, nodes.size())) merge((NodeImpl) node, (NodeImpl) target);
	}

	private boolean correctParent(List<Node> nodes) {
		String parent = nodes.get(0).parentName() == null ? "" : nodes.get(0).parentName();
		for (Node node : nodes) if (!parent.equals(node.parentName() == null ? "" : node.parentName())) return false;
		return true;
	}

	private void merge(NodeImpl node, NodeImpl target) {
		target.absorb(node);
	}

	private Map<String, List<Node>> fragmentNodes(Model model) {
		Map<String, List<Node>> toMerge = new LinkedHashMap<>();
		for (Node node : model.components()) {
			if (node.isAnonymous()) continue;
			if (!toMerge.containsKey(node.qualifiedName())) toMerge.put(node.qualifiedName(), new ArrayList<>());
			toMerge.get(node.qualifiedName()).add(node);
		}
		return toMerge;
	}

	private void resolveAsFacetTargetFragment(NodeImpl node) {
		if (node.facetTarget() == null || node.facetTarget().parent() == null) return;
		resolveComponents((NodeImpl) node.facetTarget().parent(), node);
		resolveVariables((NodeImpl) node.facetTarget().parent(), node);
	}

	private void resolveCompositionRule(NodeImpl node, NodeImpl child) {
		//TODO
	}

	private void resolveAllowedFacets(NodeImpl parent, NodeImpl child) {
		child.addAllowedFacets(parent.allowedFacets().toArray(new String[parent.allowedFacets().size()]));
	}

	private void resolveAppliedFacets(NodeImpl parent, NodeImpl child) {
		parent.facets().stream().filter(facet -> !isOverridden(child, facet)).forEach(child::addFacets);
	}

	private void resolveFacetTarget(NodeImpl parent, NodeImpl child) {
		if (parent.facetTarget() != null && child.facetTarget() == null) child.facetTarget(parent.facetTarget());
	}

	private boolean isOverridden(NodeImpl child, Facet facet) {
		for (Facet childFacet : child.facets()) if (childFacet.type().equals(facet.type())) return true;
		return false;
	}

	private Set<NodeImpl> collectNodes(Model model) {
		Set<NodeImpl> collection = new HashSet<>();
		for (Node node : model.components()) {
			if (!node.children().isEmpty()) collection.add((NodeImpl) node);
			collect(node, collection);
		}
		return collection;
	}

	private void collect(Node node, Set<NodeImpl> collection) {
		if (!(node instanceof NodeImpl)) return;
		if (!node.children().isEmpty()) collection.add((NodeImpl) node);
		for (Node component : node.components())
			collect(component, collection);
		collectInFacets(node, collection);
	}

	private void collectInFacets(Node node, Set<NodeImpl> collection) {
		for (Facet facet : node.facets())
			for (Node component : facet.components())
				collect(component, collection);
	}

	private List<Node> resolveComponents(NodeImpl parent, NodeImpl child) {
		Map<Node, CompositionRule> nodes = new LinkedHashMap<>();
		for (Node component : parent.components()) {
			if (isOverridden(child, component)) continue;
			NodeReference reference = component.isReference() ? new NodeReference(((NodeReference) component).getDestiny()) : new NodeReference((NodeImpl) component);
			addTags(component, reference);
			reference.setHas(false);
			reference.file(child.file());
			reference.line(child.line());
			reference.container(child);
			nodes.put(reference, component.container().ruleOf(component));
		}
		for (Node node : nodes.keySet())
			child.add(node, nodes.get(node));
		return new ArrayList<>(nodes.keySet());
	}

	private void addTags(Node component, NodeReference reference) {
		component.flags().stream().filter(tag -> !reference.flags().contains(tag) && Flags.forReference().contains(tag)).forEach(reference::addFlag);
		component.annotations().stream().filter(tag -> !reference.annotations().contains(tag)).forEach(reference::addAnnotations);
	}

	private void resolveFlags(NodeImpl parent, NodeImpl child) {
		parent.flags().stream().
			filter(tag -> !tag.equals(Tag.Abstract) && !child.flags().contains(tag)).
			forEach(child::addFlag);
	}

	private void resolveAnnotations(NodeImpl parent, NodeImpl child) {
		parent.annotations().stream().filter(tag -> !tag.equals(Tag.Abstract) && !child.annotations().contains(tag)).forEach(child::addAnnotations);
	}

	private void resolveVariables(NodeImpl parent, NodeImpl child) {
		List<Variable> variables = new ArrayList<>();
		for (Variable variable : parent.variables())
			if (isOverridden(child, variable)) {
				final Variable overridenVariable = findVariable(child, variable.name());
				overridenVariable.addFlags(variable.flags().toArray(new Tag[variable.flags().size()]));
				overridenVariable.overriden(true);
			} else variables.add(variable.cloneIt(child));
		child.add(0, variables.toArray(new Variable[variables.size()]));
	}

	private Variable findVariable(NodeImpl child, String name) {
		for (Variable variable : child.variables()) if (variable.name().equals(name)) return variable;
		return null;
	}

	private boolean isOverridden(NodeContainer child, Node node) {
		for (Node component : child.components())
			if (!(component instanceof NodeReference) && component.name() != null && component.name().equals(node.name()) && component.type().equals(node.type())) {
				if (component.parent() == null) ((NodeImpl) component).setParent(node);
				return true;
			}
		return false;
	}

	private boolean isOverridden(NodeContainer child, Variable variable) {
		for (Variable childVar : child.variables())
			if (childVar.name().equals(variable.name()) && childVar.type().equals(variable.type()))
				return true;
		return false;
	}

	private List<NodeImpl> getChildrenSorted(NodeImpl parent) {
		List<NodeImpl> children = parent.children().stream().
			map(node -> (NodeImpl) node).collect(Collectors.toList());
		sort(children);
		return children;
	}

	private void sort(List<NodeImpl> nodes) {
		if (nodes.isEmpty()) return;
		Collections.sort(nodes, inheritanceComparator());
		Collections.reverse(nodes);
	}

	private Comparator<NodeImpl> inheritanceComparator() {
		return new Comparator<NodeImpl>() {
			@Override
			public int compare(NodeImpl o1, NodeImpl o2) {
				return maxLevel(o1) - maxLevel(o2);
			}

			private int maxLevel(NodeImpl node) {
				List<Integer> levels = new ArrayList<>();
				levels.add(0);
				levels.addAll(node.children().stream().
					map(child -> maxLevel((NodeImpl) child)).
					collect(Collectors.toList()));
				Collections.sort(levels, Collections.reverseOrder());
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
