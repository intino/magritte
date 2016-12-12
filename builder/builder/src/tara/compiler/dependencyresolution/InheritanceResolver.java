package tara.compiler.dependencyresolution;

import tara.compiler.core.errorcollection.DependencyException;
import tara.compiler.model.FacetTargetImpl;
import tara.compiler.model.Model;
import tara.compiler.model.NodeImpl;
import tara.compiler.model.NodeReference;
import tara.dsl.Proteo;
import tara.dsl.ProteoConstants;
import tara.lang.model.*;
import tara.lang.model.rules.Size;

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
		List<Node> nodes = new ArrayList<>();
		nodes.addAll(collectNodes(model));
		sort(nodes);
		model.components().forEach(this::resolveAsMetaFacet);
		for (Node node : nodes) resolve(node);
		model.components().forEach(this::resolveAsFacetTargetFragment);
		mergeFragmentNodes(model);
	}

	private void resolve(Node node) throws DependencyException {
		List<Node> children = getChildrenSorted(node);
		if (!children.isEmpty() && !node.isAbstract() && node.isSub()) node.addFlag(Tag.Abstract);
		for (Node child : children) resolve(node, child);
		resolveAsFacetTargetFragment(node);
	}

	private void resolveAsMetaFacet(Node node) {
		if (node.type().startsWith(ProteoConstants.METAFACET + Proteo.FACET_SEPARATOR) && node.facetTarget() != null && !node.facetTarget().targetNode().children().isEmpty())
			for (Node child : node.facetTarget().targetNode().children())
				node.container().add(createChildMetaFacet(node, child), node.container().rulesOf(node));
	}

	private Node createChildMetaFacet(Node node, Node child) {
		final NodeImpl metaFacet = new NodeImpl();
		metaFacet.setVirtual(true);
		metaFacet.setDirty(true);
		metaFacet.file(node.file());
		metaFacet.line(node.line());
		metaFacet.column(node.column());
		metaFacet.doc(node.doc());
		metaFacet.container(node.container());
		metaFacet.name(node.name());
		metaFacet.type(node.type());
		metaFacet.setParent(node);
		final FacetTargetImpl target = new FacetTargetImpl();
		target.targetNode(child);
		target.target(child.qualifiedName());
		target.setConstraints(node.facetTarget().constraints());
		target.owner(metaFacet);
		metaFacet.facetTarget(target);
		return metaFacet;
	}

	private void resolve(Node node, Node child) throws DependencyException {
		resolveComponents(node, child);
		resolveVariables(node, child);
		resolveFlags(node, child);
		resolveAnnotations(node, child);
		resolveAllowedFacets(node, child);
		resolveAppliedFacets(node, child);
		resolveFacetTarget(node, child);
		resolveNodeRules(node, child);
		resolve(child);
	}

	private void mergeFragmentNodes(Model model) throws DependencyException {
		Map<String, List<Node>> toMerge = fragmentNodes(model);
		for (List<Node> nodes : toMerge.values()) merge(nodes);
	}

	private void merge(List<Node> nodes) throws DependencyException {
		if (nodes.size() < 2) return;
		if (!correctParent(nodes))
			throw new DependencyException("Error merging extension elements. Parents are not homogeneous.", nodes.get(0));
		Node parent = selectParent(nodes);
		if (parent == null) return;
		final ArrayList<Node> receivers = new ArrayList<>(nodes);
		receivers.remove(parent);
		for (Node node : receivers) ((NodeImpl) node).absorb((NodeImpl) parent);
	}

	private boolean correctParent(List<Node> nodes) {
		String parent = nodes.get(0).parentName() == null ? "" : nodes.get(0).parentName();
		for (Node node : nodes) if (!parent.equals(node.parentName() == null ? "" : node.parentName())) return false;
		return true;
	}

	private Node selectParent(List<Node> nodes) {
		for (Node node : nodes) {
			if (node.facetTarget() != null && node.facetTarget().targetNode().isAbstract() && !hasParent(node, nodes)) return node;
		}
		return null;
	}

	private boolean hasParent(Node node, List<Node> nodes) {
		for (Node candidate : nodes)
			if (candidate.equals(node.parent())) return true;
		return false;
	}

	private Map<String, List<Node>> fragmentNodes(Model model) {
		Map<String, List<Node>> toMerge = new LinkedHashMap<>();
		for (Node node : model.components()) {
			if (node.isAnonymous()) continue;
			if (!toMerge.containsKey(node.name())) toMerge.put(node.name(), new ArrayList<>());
			toMerge.get(node.name()).add(node);
		}
		return toMerge;
	}

	private void resolveAsFacetTargetFragment(Node node) {
		if (node.facetTarget() == null || node.facetTarget().parent() == null) return;
		resolveComponents(node.facetTarget().parent(), node);
		resolveVariables(node.facetTarget().parent(), node);
	}

	private void resolveNodeRules(Node parent, Node child) {
		List<Rule> parentRules = parent.container().rulesOf(parent);
		List<Rule> childRules = child.container().rulesOf(child);
		Size size = child.container().sizeOf(child);
		for (Rule rule : parentRules) {
			if (!(rule instanceof Size)) childRules.add(rule);
			else if (isMoreRestrictiveThan((Size) rule, size)) {
				childRules.remove(size);
				childRules.add(rule);
			}
		}
	}

	private boolean isMoreRestrictiveThan(Size parent, Size child) {
		return parent.min() > child.min() || parent.max() < child.max();
	}

	private void resolveAllowedFacets(Node parent, Node child) {
		child.addAllowedFacets(parent.allowedFacets().toArray(new String[parent.allowedFacets().size()]));
	}

	private void resolveAppliedFacets(Node parent, Node child) {
		parent.facets().stream().filter(facet -> !isOverridden(child, facet)).forEach(child::addFacets);
	}

	private void resolveFacetTarget(Node parent, Node child) {
		if (parent.facetTarget() != null && child.facetTarget() == null) {
			try {
				final FacetTargetImpl clone = ((FacetTargetImpl) parent.facetTarget()).clone();
				clone.inherited(true);
				clone.owner(child);
				child.facetTarget(clone);
			} catch (CloneNotSupportedException e) {
				LOG.severe(e.getMessage());
			}
			if (child.isSub())
				child.parent().children().stream().filter(sibling -> !sibling.equals(child)).forEach(s -> child.facetTarget().constraints().add(rejectSiblings(s)));
		}
	}

	private FacetTarget.Constraint rejectSiblings(final Node node) {
		return new FacetTarget.Constraint() {
			@Override
			public String name() {
				return node.qualifiedName();
			}

			@Override
			public Node node() {
				return node;
			}

			@Override
			public void node(Node node) {
			}

			@Override
			public boolean negated() {
				return true;
			}

			@Override
			public String toString() {
				return "without" + " " + node.qualifiedName();
			}

			@Override
			public FacetTarget.Constraint clone() throws CloneNotSupportedException {
				return (FacetTarget.Constraint) super.clone();
			}
		};
	}

	private boolean isOverridden(Node child, Facet facet) {
		for (Facet childFacet : child.facets()) if (childFacet.type().equals(facet.type())) return true;
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

	private List<Node> resolveComponents(Node parent, Node child) {
		Map<Node, List<Rule>> nodes = new LinkedHashMap<>();
		for (Node component : parent.components()) {
			if (isOverridden(child, component)) continue;
			NodeReference reference = component.isReference() ? new NodeReference(((NodeReference) component).getDestiny()) : new NodeReference((NodeImpl) component);
			addTags(component, reference);
			reference.setHas(false);
			reference.file(child.file());
			reference.line(child.line());
			reference.container(child);
			nodes.put(reference, component.container().rulesOf(component));
		}
		for (Map.Entry<Node, List<Rule>> entry : nodes.entrySet())
			child.add(entry.getKey(), entry.getValue());
		return new ArrayList<>(nodes.keySet());
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
				overridenVariable.addFlags(variable.flags().toArray(new Tag[variable.flags().size()]));
				overridenVariable.overriden(true);
			} else variables.add(variable.cloneIt(child));
		child.add(0, variables.toArray(new Variable[variables.size()]));
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
		for (Variable childVar : child.variables())
			if (childVar.name().equals(variable.name()) && childVar.type().equals(variable.type()))
				return true;
		return false;
	}

	private List<Node> getChildrenSorted(Node parent) {
		List<Node> children = parent.children().stream().
			map(node -> node).collect(Collectors.toList());
		sort(children);
		return children;
	}

	private void sort(List<Node> nodes) {
		if (nodes.isEmpty()) return;
		Collections.sort(nodes, inheritanceComparator());
		Collections.reverse(nodes);
	}

	private Comparator<Node> inheritanceComparator() {
		return new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return maxLevel(o1) - maxLevel(o2);
			}

			private int maxLevel(Node node) {
				List<Integer> levels = new ArrayList<>(Collections.singletonList(0));
				levels.addAll(node.children().stream().map(child -> maxLevel((Node) child)).collect(Collectors.toList()));
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
