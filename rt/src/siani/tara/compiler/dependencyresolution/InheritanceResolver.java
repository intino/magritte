package siani.tara.compiler.dependencyresolution;

import siani.tara.compiler.core.errorcollection.DependencyException;
import siani.tara.compiler.model.*;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.compiler.model.impl.NodeReference;
import siani.tara.semantic.model.Tag;

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
		if (!children.isEmpty() && !node.isAbstract()) node.addFlags(Tag.ABSTRACT.name());
		for (NodeImpl child : children) {
			resolveIncludes(node, child);
			resolveFlags(node, child);
			resolveAnnotations(node, child);
			resolveVariables(node, child);
			resolveAllowedFacets(node, child);
			resolve(child);
		}
	}

	private void resolveAllowedFacets(NodeImpl parent, NodeImpl child) {
		child.addAllowedFacets(parent.getAllowedFacets().toArray(new String[parent.getAllowedFacets().size()]));
	}

	private Set<NodeImpl> collectNodes(Model model) {
		Set<NodeImpl> collection = new HashSet<>();
		for (Node node : model.getIncludedNodes()) {
			if (!node.getChildren().isEmpty()) collection.add((NodeImpl) node);
			collect(node, collection);
		}
		return collection;
	}

	private void collect(Node node, Set<NodeImpl> collection) {
		if (!(node instanceof NodeImpl)) return;
		if (!node.getChildren().isEmpty()) collection.add((NodeImpl) node);
		for (Node include : node.getIncludedNodes())
			collect(include, collection);
		collectInFacets(node, collection);
		collectInTargets(node, collection);
	}

	private void collectInFacets(Node node, Set<NodeImpl> collection) {
		for (Facet facet : node.getFacets())
			for (Node include : facet.getIncludedNodes())
				collect(include, collection);
	}

	private void collectInTargets(Node node, Set<NodeImpl> collection) {
		for (FacetTarget facet : node.getFacetTargets()) {
			for (Node include : facet.getIncludedNodes())
				collect(include, collection);
		}
	}

	private List<Node> resolveIncludes(NodeImpl parent, NodeImpl child) {
		List<Node> nodes = new ArrayList<>();
		for (Node include : parent.getIncludedNodes()) {
			if (isOverridden(child, include)) continue;
			NodeReference reference = (include instanceof NodeImpl) ? new NodeReference((NodeImpl) include) : new NodeReference(((NodeReference) include).getDestiny());
			addTags(include, reference);
			nodes.add(reference);
			reference.setFile(((Element) include).getFile());
			reference.setLine(child.getLine());
			reference.setContainer(child);
		}
		child.addIncludedNodes(0, nodes.toArray(new Node[nodes.size()]));

		return nodes;
	}

	private void addTags(Node include, NodeReference reference) {
		include.getFlags().stream().filter(tag -> !reference.getFlags().contains(tag)).forEach(tag -> reference.addFlags(tag.name()));
		include.getAnnotations().stream().filter(tag -> !reference.getAnnotations().contains(tag)).forEach(tag -> reference.addAnnotations(tag.name()));
	}

	private void resolveFlags(NodeImpl parent, NodeImpl child) {
		parent.getFlags().stream().
			filter(tag -> !tag.equals(Tag.ABSTRACT) && !child.getFlags().contains(tag)).
			forEach(tag -> child.addFlags(tag.name()));
	}

	private void resolveAnnotations(NodeImpl parent, NodeImpl child) {
		parent.getAnnotations().stream().filter(tag -> !tag.equals(Tag.ABSTRACT) && !child.getAnnotations().contains(tag)).forEach(tag -> child.addAnnotations(tag.name()));
	}

	private void resolveVariables(NodeImpl parent, NodeImpl child) {
		List<Variable> variables = new ArrayList<>();
		for (Variable variable : parent.getVariables())
			if (!isOverridden(child, variable)) variables.add(variable.cloneIt(child));
			else variable.setOverriden(true);
		child.addVariables(0, variables.toArray(new Variable[variables.size()]));
	}

	private boolean isOverridden(NodeContainer child, Node node) {
		for (Node include : child.getIncludedNodes())
			if (include.getName().equals(node.getName()) && include.getType().equals(node.getType()))
				return true;
		return false;
	}


	private boolean isOverridden(NodeContainer child, Variable variable) {
		for (Variable childVar : child.getVariables())
			if (childVar.getName().equals(variable.getName()) && childVar.getType().equals(variable.getType()))
				return true;
		return false;
	}

	private List<NodeImpl> getChildrenSorted(NodeImpl parent) {
		List<NodeImpl> children = parent.getChildren().stream().
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
				levels.addAll(node.getChildren().stream().
					map(child -> maxLevel((NodeImpl) child)).collect(Collectors.toList()));
				Collections.sort(levels, Collections.reverseOrder());
				return 1 + levels.get(0);
			}

			@Override
			public boolean equals(Object obj) {
				return false;
			}
		};
	}
}
