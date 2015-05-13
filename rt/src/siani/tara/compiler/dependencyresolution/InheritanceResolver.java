package siani.tara.compiler.dependencyresolution;

import siani.tara.compiler.core.errorcollection.DependencyException;
import siani.tara.compiler.model.*;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.compiler.model.impl.NodeReference;
import siani.tara.semantic.model.Tag;

import java.util.*;

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
	}

	private void resolve(NodeImpl parent) {
		List<NodeImpl> children = getChildrenSorted(parent);
		for (NodeImpl child : children) {
			resolveIncludes(parent, child);
			resolveFlags(parent, child);
			resolveAnnotations(parent, child);
			resolveVariables(parent, child);
			resolveAllowedFacets(parent, child);
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
		for (Facet facet : node.getFacets()) {
			for (Node include : facet.getIncludedNodes())
				collect(include, collection);
		}
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
			nodes.add(reference);
			reference.setContainer(child);
		}
		child.addIncludedNodes(0, nodes.toArray(new Node[nodes.size()]));

		return nodes;
	}

	private void resolveFlags(NodeImpl parent, NodeImpl child) {
		for (Tag tag : parent.getFlags()) {
			if (!tag.equals(Tag.ABSTRACT) && !child.getFlags().contains(tag))
				child.addFlags(tag.getName());
		}
	}

	private void resolveAnnotations(NodeImpl parent, NodeImpl child) {
		for (Tag tag : parent.getAnnotations())
			if (!tag.equals(Tag.ABSTRACT) && !child.getAnnotations().contains(tag))
				child.addAnnotations(tag.getName());
	}

	private void resolveVariables(NodeImpl parent, NodeImpl child) {
		List<Variable> variables = new ArrayList<>();
		for (Variable variable : parent.getVariables())
			if (!isOverridden(child, variable))
				variables.add(variable.cloneIt(child));
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
		List<NodeImpl> children = new ArrayList<>();
		for (Node node : parent.getChildren()) children.add((NodeImpl) node);
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
				for (Node child : node.getChildren())
					levels.add(maxLevel((NodeImpl) child));
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
