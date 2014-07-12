package siani.tara.compiler.dependencyresolver;

import siani.tara.compiler.core.errorcollection.DependencyException;
import siani.tara.lang.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static siani.tara.lang.NodeObject.AnnotationType;

public class ModelDependencyResolver {
	Model model;
	List<String> toProcessNodes = new ArrayList<>();

	public ModelDependencyResolver(Model model) throws DependencyException {
		this.model = model;
	}

	public void resolve() throws DependencyException {
		model.sortNodeTable(new NodeComparator(model.getNodeTable()));
		toProcessNodes.addAll(model.getNodeTable().keySet());
		resolveHierarchyDependencies();
	}

	private void resolveHierarchyDependencies() throws DependencyException {
		List<LinkNode> toAddNodes = new ArrayList<>();
		while (!toProcessNodes.isEmpty()) {
			for (int i = 0; i < toProcessNodes.size(); i++) {
				Node node = model.get(toProcessNodes.get(i));
				if (node instanceof LinkNode)
					linkToDeclared((LinkNode) node, model.searchDeclaredNodeOfLink((LinkNode) node));
				else {
					NodeObject object = node.getObject();
					if (object.getParentName() != null || node.isCase()) {
						DeclaredNode parent = model.searchAncestry(node);
						if (parent == null) throwError(node);
						if (toProcessNodes.contains(parent.getQualifiedName())) continue;
						linkDeclaredToParent(object, parent);
						extractInfoFromParent(toAddNodes, (DeclaredNode) node, parent);
					}
				}
				toProcessNodes.remove(node.getQualifiedName());
				i--;
			}
		}
		addNewNodes(toAddNodes);
		List<String> list = updateLinks(toAddNodes);
		updateKeys(list);
	}

	private void addNewNodes(List<LinkNode> toAddNodes) {
		for (Node toAddNode : toAddNodes) {
			toAddNode.calculateQualifiedName();
			model.add(toAddNode.getQualifiedName(), toAddNode);
		}
		model.sortNodeTable(new NodeComparator(model.getNodeTable()));
	}

	private List<String> updateLinks(List<LinkNode> toAddNodes) {
		List<String> nodes = new ArrayList();
		for (Map.Entry<String, Node> node : model.getNodeTable().entrySet()) {
			if (node.getValue() instanceof LinkNode) {
				node.getValue().calculateQualifiedName();
				if (!toAddNodes.contains(node.getValue()))
					nodes.add(node.getKey());
			}
		}
		return nodes;
	}

	private void updateKeys(List<String> nodes) {
		Map<String, Node> nodeTable = model.getNodeTable();
		for (String nodeName : nodes) {
			Node nodeToUpdate = nodeTable.get(nodeName);
			nodeTable.remove(nodeName);
			nodeTable.put(nodeToUpdate.getQualifiedName(), nodeToUpdate);
		}
		model.sortNodeTable(new NodeComparator(model.getNodeTable()));
	}

	private void linkToDeclared(LinkNode node, DeclaredNode parent) {
		node.setDestinyQN(parent.getQualifiedName());
		node.setDestiny(parent);
	}

	private void extractInfoFromParent(List<LinkNode> toAddNodes, DeclaredNode node, DeclaredNode parent) {
		collectInnerConceptsInherited(parent, node, toAddNodes);
		calculateInheritedVariables(parent.getObject(), node);
		addInheritedAnnotations(parent.getObject(), node);
	}

	private void addInheritedAnnotations(NodeObject parent, DeclaredNode node) {
		for (AnnotationType annotation : parent.getAnnotations()) {
			if (annotation.equals(AnnotationType.PRIVATE)) continue;
			node.getObject().add(annotation);
		}
	}

	private void calculateInheritedVariables(NodeObject parent, DeclaredNode node) {
		for (Variable variable : parent.getVariables())
			node.getObject().add(0, variable.clone());
	}

	private void collectInnerConceptsInherited(DeclaredNode parent, DeclaredNode node, List<LinkNode> toAddNodes) {
		for (Node child : parent.getInnerNodes())
			if (!child.isCase()) {
				DeclaredNode destiny = (child instanceof LinkNode) ? ((LinkNode) child).getDestiny() : (DeclaredNode) child;
				LinkNode element = new LinkNode(destiny, node);
				element.setDestinyQN(destiny.getQualifiedName());
				toAddNodes.add(element);
				node.add(element, 0);
				element.calculateQualifiedName();
			}
	}

	private void linkDeclaredToParent(NodeObject object, DeclaredNode parent) {
		object.setParentObject(parent.getObject());
		object.setParentName(parent.getQualifiedName());
		parent.getObject().addChild(object);
	}

	private void throwError(Node node) throws DependencyException {
		throw new DependencyException("Dependency resolution fail in: " + node.getQualifiedName() +
			". Not found ancestry: " + node.getObject().getParentName(), node);
	}

}
