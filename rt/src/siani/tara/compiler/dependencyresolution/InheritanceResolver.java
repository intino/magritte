package siani.tara.compiler.dependencyresolution;

import siani.tara.compiler.core.errorcollection.DependencyException;
import siani.tara.compiler.model.Facet;
import siani.tara.compiler.model.FacetTarget;
import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeImpl;

public class InheritanceResolver {

	Model model;

	InheritanceResolver(Model model) {
		this.model = model;
	}


	void resolve() throws DependencyException {
		for (Node node : model.getIncludedNodes())
			resolve(node);
	}

	private void resolve(Node node) throws DependencyException {
		if (!(node instanceof NodeImpl)) return;
		for (Node include : node.getIncludedNodes())
			resolve(include);
		resolveInFacets(node);
		resolveInTargets(node);
	}

	private void resolveInFacets(Node node) throws DependencyException {
		for (Facet facet : node.getFacets()) {
			for (Node include : facet.getIncludedNodes())
				resolve(include);
		}
	}

	private void resolveInTargets(Node node) throws DependencyException {
		for (FacetTarget facet : node.getFacetTargets()) {
			for (Node include : facet.getIncludedNodes())
				resolve(include);
		}
	}
	//	private void extractInfoFromParent(List<LinkNode> toAddNodes, DeclaredNode node, DeclaredNode parent) {
//		collectInnerConceptsInherited(parent, node, toAddNodes);
//		calculateInheritedVariables(parent.getObject(), node);
//		addInheritedAnnotations(parent, node);
//	}
//
//	private void addInheritedAnnotations(Node parent, DeclaredNode node) {
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
//		for (Node child : parent.getInnerNodes()) {
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
}
