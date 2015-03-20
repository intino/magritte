package siani.tara.compiler.dependencyresolver;

import siani.tara.compiler.core.errorcollection.DependencyException;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeImpl;

import java.util.ArrayList;
import java.util.List;

public class InsideModelDependencyResolver {
	Model model;
	List<NodeImpl> toProcessNodes = new ArrayList<>();

	public InsideModelDependencyResolver(Model model) throws DependencyException {
		this.model = model;
	}

	public void resolve() throws DependencyException {
//		model.sortNodeTable(new NodeComparator());
//		toProcessNodes.addAll(model.getNodeTable());
//		Collections.sort(toProcessNodes, buildNodeComparator());
//		resolveHierarchyDependencies();
//		propagateTerminalAnnotations();
	}

	private void resolveHierarchyDependencies() throws DependencyException {
//		List<LinkNode> toAddNodes = new ArrayList<>();
//		while (!toProcessNodes.isEmpty()) {
//			for (int i = 0; i < toProcessNodes.size(); i++) {
//				NodeImpl node = toProcessNodes.get(i);
//				if (node instanceof LinkNode)
//					linkToDeclared((LinkNode) node, model.searchDeclaredNodeOfLink((LinkNode) node));
//				else if (resolveAsDeclared(toAddNodes, (DeclaredNode) node)) continue;
//				toProcessNodes.remove(node);
//				i--;
//			}
//		}
//		addNewNodes(toAddNodes);
//		updateLinks(toAddNodes);
	}

	private void propagateTerminalAnnotations() {
	}

	private void propagateTerminal(NodeImpl node) {
	}

	private void setVariablesToTerminal(NodeImpl inner) {
	}

//	private void linkToDeclared(Node node, Node parent) throws DependencyException {
//	}

//	private boolean resolveAsDeclared(List<LinkNode> toAddNodes, DeclaredNode node) throws DependencyException {
//		NodeObject object = node.getObject();
//		if (object.getParentName() != null || node.isSub()) {
//			DeclaredNode parent = model.searchAncestry(node);
//			if (parent == null) throwError(node);
//			if (toProcessNodes.contains(parent.getQualifiedName())) return true;
//			linkDeclaredToParent(object, parent);
//			extractInfoFromParent(toAddNodes, node, parent);
//		}
//		resolveVariableReferences(node);
//		return false;
//	}
//
//
//	private void resolveVariableReferences(NodeImpl node) throws DependencyException {
//		List<Reference> references = node.getObject().getReferences();
//		DeclaredNode declaredNode;
//		for (Reference reference : references) {
//			NodeImpl refNode = model.searchDeclarationOfReference(reference.getType(), node);
//			declaredNode = refNode.is(DeclaredNode.class) ? (DeclaredNode) refNode : ((LinkNode) refNode).getDestiny();
//			if (declaredNode == null)
//				declaredNode = model.searchDeclarationOfReference(reference.getType(), node);
//			if (declaredNode == null) throwError(node);
//			reference.setType(declaredNode.getQualifiedName());
//		}
//	}
//
//	private void addNewNodes(List<LinkNode> toAddNodes) {
//		for (NodeImpl toAddNode : toAddNodes) {
//			toAddNode.calculateQualifiedName();
//			model.register(toAddNode);
//		}
//		model.sortNodeTable(new NodeComparator());
//	}
//
//	private List<String> updateLinks(List<LinkNode> toAddNodes) {
//		List<String> nodes = new ArrayList();
//		for (NodeImpl node : model.getNodeTable()) {
//			if (node instanceof LinkNode) {
//				node.calculateQualifiedName();
//				if (!toAddNodes.contains(node))
//					nodes.add(node.getQualifiedName());
//			}
//		}
//		return nodes;
//	}
//
//	private void extractInfoFromParent(List<LinkNode> toAddNodes, DeclaredNode node, DeclaredNode parent) {
//		collectInnerConceptsInherited(parent, node, toAddNodes);
//		calculateInheritedVariables(parent.getObject(), node);
//		addInheritedAnnotations(parent, node);
//	}
//
//	private void addInheritedAnnotations(NodeImpl parent, DeclaredNode node) {
//		for (Annotation annotation : parent.getAnnotations())
//			if (!annotation.equals(ABSTRACT))
//				node.add(annotation);
//	}
//
//	private void calculateInheritedVariables(NodeObject parent, DeclaredNode child) {
//		List<Variable> variables = new ArrayList<>();
//		for (Variable variable : parent.getVariables())
//			if (!isOverrided(child, variable))
//				variables.add(variable.clone());
//		child.getObject().getVariables().addAll(0, variables);
//	}
//
//	private boolean isOverrided(DeclaredNode child, Variable variable) {
//		for (Variable childVar : child.getObject().getVariables())
//			if (childVar.getName().equals(variable.getName()) && childVar.getType().equals(variable.getType()))
//				return true;
//		return false;
//	}
//
//	private void collectInnerConceptsInherited(DeclaredNode parent, DeclaredNode node, List<LinkNode> toAddNodes) {
//		for (NodeImpl child : parent.getInnerNodes()) {
//			if (!child.is(LinkNode.class) && child.isSub() || node.contains(child.getType(), child.getName(), child.isAggregated()))
//				continue;
//			DeclaredNode destiny = (child instanceof LinkNode) ? ((LinkNode) child).getDestiny() : (DeclaredNode) child;
//			LinkNode element = new LinkNode(destiny, node);
//			model.register(node);
//			element.setDestinyQN(destiny.getQualifiedName());
//			toAddNodes.add(element);
//			node.add(0, element);
//			element.calculateQualifiedName();
//		}
//	}
//
//	private void linkDeclaredToParent(NodeObject object, DeclaredNode parent) {
//		object.setParentObject(parent.getObject());
//		object.setParentName(parent.getQualifiedName());
//		parent.getObject().addChild(object);
//	}
//
//	private Comparator<NodeImpl> buildNodeComparator() {
//		return new Comparator<NodeImpl>() {
//			@Override
//			public int compare(NodeImpl o1, NodeImpl o2) {
//				boolean i1 = o1 instanceof LinkNode;
//				boolean i2 = o2 instanceof LinkNode;
//				if (i1 && !i2) return -1;
//				if (!i1 && !i2) return 0;
//				if (!i1) return 1;
//				return -1;
//			}
//		};
//	}
//
//	private void throwError(NodeImpl node) throws DependencyException {
//		if (node.getObject() != null)
//			throw new DependencyException("Not found reference: " + node.getObject().getParentName(), node);
//		else if (node instanceof LinkNode)
//			throw new DependencyException("Not found reference: " + ((LinkNode) node).getDestinyName(), node);
//	}
}