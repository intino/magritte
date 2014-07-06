package siani.tara.compiler.dependencyresolver;

import siani.tara.compiler.core.errorcollection.DependencyException;
import siani.tara.lang.*;

import java.util.Collections;
import java.util.Comparator;

import static siani.tara.lang.NodeObject.AnnotationType;

public class ModelDependencyResolver {
	TreeWrapper tree;
	NodeTree nodes = new NodeTree();

	public ModelDependencyResolver(TreeWrapper treeWrapper) throws DependencyException {
		tree = treeWrapper;
		nodes.addAll(tree.getNodeTable().values());
		resolveHierarchyDependencies();
	}

	public TreeWrapper getTree() {
		return tree;
	}

	public TreeWrapper resolve() throws DependencyException {
		restructure();
		refactorTable();
		resolveHeritage();
		return tree;
	}

	private void refactorTable() {
		tree.getNodeTable().clear();
		for (Node value : nodes)
			tree.add(value.getQualifiedName(), value);
	}

	private void resolveHeritage() {
		for (Node node : nodes) {
			NodeObject object = node.getObject();
			if (object.getParent() != null) {
				addInnerConceptsInherited(object.getParent().getDeclaredNode(), node);
				addInheritedVariables(object.getParent(), node);
				addInheritedAnnotations(object.getParent(), node);
			} else if (object.getBaseNode() != null) {
				addInnerConceptsInherited(object.getBaseNode(), node);
				addInheritedVariables(object.getBaseNode().getObject(), node);
			}
		}
	}

	private void addInheritedAnnotations(NodeObject parent, Node node) {
		for (AnnotationType annotation : parent.getAnnotations()) node.getObject().add(annotation);
	}

	private void addInheritedVariables(NodeObject parent, Node node) {
		for (Variable variable : parent.getVariables())
			node.getObject().add(0, variable.clone());
	}

	private void addInnerConceptsInherited(Node parent, Node node) {
		for (Node child : parent.getInnerNodes())
			if (!child.isCase() /*&& !child.getObject().isAbstract()*/) {
				Node element = new Node(child.getObject(), node);
				tree.add(element.getQualifiedName(), element);
				node.add(0, element);
				element.calculateQualifiedName();
				for (Node innerChild : child.getInnerNodes()) addInnerConceptsInherited(innerChild, element);
			}
	}

	private void restructure() {
		Collections.sort(nodes, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				String qn1 = o1.getQualifiedName().replaceAll("\\[.*\\]", "");
				int count1 = qn1.length() - qn1.replace(".", "").length();
				String qn2 = o2.getQualifiedName().replaceAll("\\[.*\\]", "");
				int count2 = qn2.length() - qn2.replace(".", "").length();
				return count1 - count2;
			}
		});
		for (Node node : nodes) {
			if (node.isCase()) {
				pullInsideBase(node);
				recalculateNames(node);
			}
		}
	}

	private void recalculateNames(Node node) {
		node.calculateQualifiedName();
		for (Node child : node.getInnerNodes())
			recalculateNames(child);
	}

	private void pullInsideBase(Node node) {
		Node base = node.getContainer();
		base.getObject().add(node);
		node.getContainer().removeChild(node);
		node.getObject().setBaseNode(base);
		node.getObject().setBaseName(base.getQualifiedName());
		node.setContainer(null);
	}

	private void resolveHierarchyDependencies() throws DependencyException {
		for (Node node : nodes)
			if (node.getObject().getParentName() != null || node.isCase()) {
				Node ancestry = tree.searchAncestry(node);
				if (ancestry == null)
					throw new DependencyException("Dependency resolution fail in: " + node.getQualifiedName() +
						". Not found ancestry: " + node.getObject().getParentName(), node);
				ancestry.getObject().addChild(node.getObject());
				if (node.isCase()) node.getObject().setBaseNode(ancestry);
				else node.getObject().setParentObject(ancestry.getObject());
			}
	}
}
