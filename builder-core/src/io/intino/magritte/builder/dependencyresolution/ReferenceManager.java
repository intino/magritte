package io.intino.magritte.builder.dependencyresolution;

import io.intino.magritte.builder.model.Model;
import io.intino.magritte.builder.model.NodeImpl;
import io.intino.magritte.builder.model.NodeReference;
import io.intino.magritte.builder.model.VariableReference;
import io.intino.magritte.lang.model.Node;
import io.intino.magritte.lang.model.NodeContainer;
import io.intino.magritte.lang.model.Primitive;

import java.io.File;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static io.intino.magritte.builder.utils.FileSystemUtils.getNameWithoutExtension;

public class ReferenceManager {

	private Model model;

	ReferenceManager(Model model) {
		this.model = model;
	}

	public NodeImpl resolve(NodeReference reference) {
		return (NodeImpl) resolve(reference.getReference(), reference.container());
	}


	NodeImpl resolve(VariableReference variable, Node container) {
		Node result = resolve(variable.destinyName(), container);
		return result instanceof NodeReference ? ((NodeReference) result).destination() : (NodeImpl) result;
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


	Node resolveParent(String reference, Node node) {
		return resolve(reference.split("\\."), searchPossibleRoots(node, reference.split("\\.")[0], true));
	}

	private Node selectFromOptions(Node node, String[] path, Collection<Node> roots) {
		return resolve(path, sortRootsByFile(roots, node.file()));
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

	private Node resolve(String[] path, Collection<Node> roots) {
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
				reference = areNamesake(node, name) ? node : null;
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
		namesake(name, set, node);
		addInContext(name, set, node, parent);
		addNodeSiblings(name, node, set);
		addRoots(name, set);
		return set;
	}

	private void addRoots(String name, Set<Node> set) {
		set.addAll(model.components().stream().
				filter(node -> areNamesake(node, name)).
				collect(Collectors.toList()));
	}

	private void addNodeSiblings(String identifier, Node container, Set<Node> set) {
		if (container == null) return;
		set.addAll(container.components().stream().filter(node -> areNamesake(node, identifier)).collect(Collectors.toList()));
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

	private void collectParentComponents(String identifier, Set<Node> set, Node container, Node parent) {
		set.addAll(parent.components().stream().
				filter(sibling -> areNamesake(sibling, identifier) && !sibling.equals(container)).
				collect(Collectors.toList()));
	}

	private void namesake(String name, Set<Node> set, NodeContainer container) {
		if (container instanceof NodeImpl && areNamesake((Node) container, name)) set.add((Node) container);
	}

	private boolean areNamesake(Node node, String name) {
		return name.equals(node.name());
	}
}