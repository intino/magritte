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
		nodes.forEach(this::resolve);
	}

	private void resolve(NodeImpl node) {
		List<NodeImpl> children = getChildrenSorted(node);
		if (!children.isEmpty() && !node.isAbstract() && node.isSub()) node.addFlag(Tag.ABSTRACT);
		for (NodeImpl child : children) {
			resolveComponents(node, child);
			resolveFlags(node, child);
			resolveAnnotations(node, child);
			resolveVariables(node, child);
			resolveAllowedFacets(node, child);
			resolveAppliedFacets(node, child);
			resolveCompositionRule(node, child);
			resolve(child);
		}
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
		for (Node include : node.components())
			collect(include, collection);
		collectInFacets(node, collection);
		collectInTargets(node, collection);
	}

	private void collectInFacets(Node node, Set<NodeImpl> collection) {
		for (Facet facet : node.facets())
			for (Node include : facet.components())
				collect(include, collection);
	}

	private void collectInTargets(Node node, Set<NodeImpl> collection) {
		for (FacetTarget facet : node.facetTargets()) {
			for (Node include : facet.components())
				collect(include, collection);
		}
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
		component.flags().stream().filter(tag -> !reference.flags().contains(tag) && Flags.hasTags().contains(tag)).forEach(reference::addFlag);
		component.annotations().stream().filter(tag -> !reference.annotations().contains(tag)).forEach(reference::addAnnotations);
	}

	private void resolveFlags(NodeImpl parent, NodeImpl child) {
		parent.flags().stream().
			filter(tag -> !tag.equals(Tag.ABSTRACT) && !child.flags().contains(tag)).
			forEach(child::addFlag);
	}

	private void resolveAnnotations(NodeImpl parent, NodeImpl child) {
		parent.annotations().stream().filter(tag -> !tag.equals(Tag.ABSTRACT) && !child.annotations().contains(tag)).forEach(child::addAnnotations);
	}

	private void resolveVariables(NodeImpl parent, NodeImpl child) {
		List<Variable> variables = new ArrayList<>();
		for (Variable variable : parent.variables())
			if (isOverridden(child, variable)) findVariable(child, variable.name()).overriden(true);
			else variables.add(variable.cloneIt(child));
		child.add(0, variables.toArray(new Variable[variables.size()]));
	}

	private Variable findVariable(NodeImpl child, String name) {
		for (Variable variable : child.variables()) if (variable.name().equals(name)) return variable;
		return null;
	}

	private boolean isOverridden(NodeContainer child, Node node) {
		for (Node include : child.components())
			if (!(include instanceof NodeReference) && include.name() != null && include.name().equals(node.name()) && include.type().equals(node.type())) {
				if (include.parent() == null) ((NodeImpl) include).setParent(node);
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
