package io.intino.tara.compiler.dependencyresolution;

import io.intino.tara.compiler.model.Model;
import io.intino.tara.compiler.model.NodeImpl;
import io.intino.tara.compiler.model.NodeReference;
import io.intino.tara.compiler.model.VariableReference;
import io.intino.tara.lang.model.FacetTarget;
import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.model.NodeContainer;
import io.intino.tara.lang.model.Primitive;

import java.io.File;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static io.intino.tara.compiler.codegeneration.FileSystemUtils.getNameWithoutExtension;
import static io.intino.tara.dsl.ProteoConstants.FACET_SEPARATOR;

public class ReferenceManager {

	private Model model;

	ReferenceManager(Model model) {
		this.model = model;
	}

	private static boolean areNamesake(String name, Node node) {
		return name.equals(node.name());
	}

	private static void addNodeSiblings(String identifier, Node container, Set<Node> set) {
		if (container == null) return;
		set.addAll(container.components().stream().filter(node -> areNamesake(identifier, node)).collect(Collectors.toList()));
	}

	private static void collectParentComponents(String identifier, Set<Node> set, Node container, Node parent) {
		set.addAll(parent.components().stream().
				filter(sibling -> areNamesake(identifier, sibling) && !sibling.equals(container)).
				collect(Collectors.toList()));
	}

	public NodeImpl resolve(NodeReference reference) {
		return (NodeImpl) resolve(reference.getReference(), reference.container());
	}

	Node resolve(FacetTarget target, Node owner) {
		Node result = resolve(target.target(), owner);
		return result instanceof NodeReference ? ((NodeReference) result).getDestiny() : result;
	}

	NodeImpl resolve(VariableReference variable, Node container) {
		Node result = resolve(variable.destinyName(), container);
		return result instanceof NodeReference ? ((NodeReference) result).getDestiny() : (NodeImpl) result;
	}

	Node resolveParameterReference(Primitive.Reference value, Node node) {
		String[] path = value.get().split("\\.");
		List<Node> roots = findRoots(node, path).stream().filter(n -> !n.equals(node)).collect(Collectors.toList());
		return selectFromOptions(node, path, roots);
	}

	Node resolve(String reference, Node node) {
		String[] path = reference.split("\\.");
		Collection<Node> roots = findRoots(node, path);
		return selectFromOptions(node, path, roots);
	}

	private Node selectFromOptions(Node node, String[] path, Collection<Node> roots) {
		roots = sortRootsByFile(roots, node.file());
		if (roots.isEmpty()) return null;
		if (roots.size() == 1 && path.length == 1) return roots.iterator().next();
		for (Node root : roots) {
			Node candidate = resolvePathInNode(path, root);
			if (candidate != null) return candidate;
		}
		return null;
	}

	private Collection<Node> sortRootsByFile(Collection<Node> roots, String file) {
		List<Node> nodes = roots.stream().filter(node -> node.file().equals(file)).collect(Collectors.toList());
		roots.stream().filter(root -> !nodes.contains(root)).forEach(nodes::add);
		return nodes;
	}

	private Collection<Node> findRoots(Node node, String[] path) {
		Collection<Node> roots = searchPossibleRoots(node, path[0], false);
		if (!roots.isEmpty()) return roots;
		for (Node root : model.components())
			if (getNameWithoutExtension(new File(root.file()).getName()).equals(path[0])) {
				if (path.length == 1) continue;
				roots = searchPossibleRoots(root, path[1], false);
				break;
			}
		return roots;
	}

	Node resolveFacetConstraint(String facet, String target) {
		for (Node node : model.components())
			if (facet.equals(node.name()) && node.facetTarget() != null && target.equals(node.facetTarget().target()))
				return node;
		return null;
	}

	Node resolveParent(String reference, Node node) {
		String[] path = reference.split("\\.");
		Collection<Node> roots = searchPossibleRoots(node, path[0], true);
		if (roots.isEmpty()) return null;
		if (roots.size() == 1 && path.length == 1) return roots.iterator().next();
		for (Node root : roots) {
			Node candidate = resolvePathInNode(path, root);
			if (candidate != null) return candidate;
		}
		return null;
	}

	private Node resolvePathInNode(String[] path, Node node) {
		Node reference = null;
		for (String name : path) {
			if (reference == null) {
				reference = areNamesake(name, node) ? node : null;
				continue;
			}
			final List<Node> components = reference.component(name);
			if (components.isEmpty() && reference.parent() != null)
				reference = reference.parent().component(name).get(0);
			else reference = components.isEmpty() ? null : components.get(0);
			if (reference == null) return null;
		}
		return reference;
	}

	private Collection<Node> searchPossibleRoots(Node node, String name, boolean parent) {
		Set<Node> set = new LinkedHashSet<>();
		final String[] names = name.split("\\" + FACET_SEPARATOR);//TODO Candidate to remove. Now all components are in the node body
		namesake(names[0], set, node);
		addInContext(names[0], set, node, parent);
		addNodeSiblings(names[0], node, set);
		addRoots(names[0], set);
		return names.length == 1 || set.isEmpty() ? set : filterByFacet(set, names[1]);
	}

	private Collection<Node> filterByFacet(Set<Node> set, String name) {
		return set.stream().filter(node -> {
			final FacetTarget target = node.facetTarget() == null ? findTargetInParent(node) : node.facetTarget();
			return target != null && (target.target().endsWith("." + name) || target.target().equals(name));
		}).collect(Collectors.toSet());
	}

	private FacetTarget findTargetInParent(Node node) {
		Node parent = node.parent();
		while (parent != null) {
			if (parent.facetTarget() != null) return parent.facetTarget();
			else parent = parent.parent();
		}
		return null;
	}

	private void addRoots(String name, Set<Node> set) {
		set.addAll(model.components().stream().
				filter(node -> areNamesake(name, node)).
				collect(Collectors.toList()));
	}

	private void addInContext(String name, Set<Node> set, Node node, boolean parent) {
		checkSiblings(name, set, node);
		Node container = node.container();
		while (container != null) {
			namesake(name, set, container);
			checkSiblings(name, set, container);
			container = container.container();
			if (parent) {
				final Node parentNode = node.parent();
				if (parentNode != null) collectParentComponents(name, set, container, parentNode);
			}
		}
	}

	private void checkSiblings(String name, Set<Node> set, Node container) {
		for (Node sibling : container.siblings()) namesake(name, set, sibling);
	}

	private void namesake(String name, Set<Node> set, NodeContainer container) {
		if (container instanceof NodeImpl && namesake((Node) container, name)) set.add((Node) container);
	}

	private boolean namesake(Node node, String name) {
		return areNamesake(name, node);
	}

}