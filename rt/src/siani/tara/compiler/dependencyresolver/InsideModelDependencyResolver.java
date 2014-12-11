package siani.tara.compiler.dependencyresolver;

import siani.tara.compiler.core.errorcollection.DependencyException;
import siani.tara.lang.*;

import java.util.*;

import static siani.tara.lang.Annotation.Annotation.ABSTRACT;

public class InsideModelDependencyResolver {
	Model model;
	List<String> toProcessNodes = new ArrayList<>();

	public InsideModelDependencyResolver(Model model) throws DependencyException {
		this.model = model;
	}

	public void resolve() throws DependencyException {
		model.sortNodeTable(new NodeComparator(model.getNodeTable()));
		toProcessNodes.addAll(model.getNodeTable().keySet());
		Collections.sort(toProcessNodes, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				boolean i1 = model.get(o1) instanceof LinkNode;
				boolean i2 = model.get(o2) instanceof LinkNode;
				if (i1 && !i2) return -1;
				if (!i1 && !i2) return 0;
				if (!i1) return 1;
				return -1;
			}
		});
		resolveHierarchyDependencies();
	}

	private void resolveHierarchyDependencies() throws DependencyException {
		List<LinkNode> toAddNodes = new ArrayList<>();
		while (!toProcessNodes.isEmpty()) {
			for (int i = 0; i < toProcessNodes.size(); i++) {
				Node node = model.get(toProcessNodes.get(i));
				if (node instanceof LinkNode)
					linkToDeclared((LinkNode) node, model.searchDeclaredNodeOfLink((LinkNode) node));
				else if (resolveAsDeclared(toAddNodes, node)) continue;
				toProcessNodes.remove(node.getQualifiedName());
				i--;
			}
		}
		addNewNodes(toAddNodes);
		List<String> list = updateLinks(toAddNodes);
		updateKeys(list);
	}

	private boolean resolveAsDeclared(List<LinkNode> toAddNodes, Node node) throws DependencyException {
		NodeObject object = node.getObject();
		if (object.getParentName() != null || node.isSub()) {
			DeclaredNode parent = model.searchAncestry(node);
			if (parent == null) throwError(node);
			if (toProcessNodes.contains(parent.getQualifiedName())) return true;
			linkDeclaredToParent(object, parent);
			extractInfoFromParent(toAddNodes, (DeclaredNode) node, parent);
		}
		resolveVariableReferences(node);
		return false;
	}

	private void resolveVariableReferences(Node node) throws DependencyException {
		List<Reference> references = node.getObject().getReferences();
		DeclaredNode declaredNode;
		for (Reference reference : references) {
			declaredNode = (DeclaredNode) model.get(reference.getType());
			if (declaredNode == null)
				declaredNode = model.searchDeclarationOfReference(reference.getType(), node);
			if (declaredNode == null) throwError(node);
			reference.setType(declaredNode.getQualifiedName());
		}
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

	private void linkToDeclared(LinkNode node, DeclaredNode parent) throws DependencyException {
		if (parent == null)
			throwError(node);
		node.setDestinyQN(parent.getQualifiedName());
		node.setDestiny(parent);
	}

	private void extractInfoFromParent(List<LinkNode> toAddNodes, DeclaredNode node, DeclaredNode parent) {
		collectInnerConceptsInherited(parent, node, toAddNodes);
		calculateInheritedVariables(parent.getObject(), node);
		addInheritedAnnotations(parent.getObject(), node);
	}

	private void addInheritedAnnotations(NodeObject parent, DeclaredNode node) {
		for (NormalAnnotation annotation : parent.getAnnotations())
			if (!annotation.equals(ABSTRACT))
				node.getObject().add(annotation);
	}

	private void calculateInheritedVariables(NodeObject parent, DeclaredNode node) {
		List<Variable> variables = new ArrayList<>();
		for (Variable variable : parent.getVariables())
			variables.add(variable.clone());
		node.getObject().getVariables().addAll(0, variables);
	}

	private void collectInnerConceptsInherited(DeclaredNode parent, DeclaredNode node, List<LinkNode> toAddNodes) {
		for (Node child : parent.getInnerNodes())
			if (!child.isSub()) {
				DeclaredNode destiny = (child instanceof LinkNode) ? ((LinkNode) child).getDestiny() : (DeclaredNode) child;
				LinkNode element = new LinkNode(destiny, node);
				if (node.contains(child.getName())) continue;
				element.setDestinyQN(destiny.getQualifiedName());
				toAddNodes.add(element);
				node.add(0, element);
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
			". Not found reference: " + node.getObject().getParentName(), node);
	}
}