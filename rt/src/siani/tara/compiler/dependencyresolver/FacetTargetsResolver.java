package siani.tara.compiler.dependencyresolver;

import siani.tara.compiler.core.errorcollection.DependencyException;
import siani.tara.lang.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static siani.tara.lang.Annotation.FACET;

public class FacetTargetsResolver {
	private final Model model;

	public FacetTargetsResolver(Model model) {
		this.model = model;
	}

	public void resolve() throws DependencyException {
		resolveSubTargets();
		for (Node node : model.getNodeTable().values())
			if (node.is(DeclaredNode.class) && node.getObject().is(FACET))
				processFacetTarget((DeclaredNode) node);
	}

	private void resolveSubTargets() throws DependencyException {
		for (Node node : model.getNodeTable().values())
			if (node.is(DeclaredNode.class) && node.getObject().is(FACET) && !node.isSub()) {
				DeclaredNode declaredNode = (DeclaredNode) node;
				if (declaredNode.hasSubs())
					processSubs(declaredNode);
			}
	}

	private void processSubs(DeclaredNode declaredNode) throws DependencyException {
		for (FacetTarget target : declaredNode.getObject().getFacetTargets())
			addFacetTargetToSubs(declaredNode, target);
	}

	private void addFacetTargetToSubs(DeclaredNode declaredNode, FacetTarget target) {
		for (DeclaredNode sub : declaredNode.getSubConcepts()) {
			sub.getObject().addFacetTarget(composeTarget(target, sub));
			cleanNode(declaredNode);
			for (DeclaredNode innerSub : declaredNode.getSubConcepts())
				addFacetTargetToSubs(innerSub, target);
		}
	}

	private void cleanNode(DeclaredNode node) {
		DeclaredNode[] subConcepts = node.getSubConcepts();
		node.getInnerNodes().clear();
		Collections.addAll(node.getInnerNodes(), subConcepts);
	}

	private FacetTarget composeTarget(FacetTarget target, DeclaredNode node) {
		FacetTarget clone = target.clone();
		for (Node inner : node.getInnerNodes()) {
			if (inner.isSub()) continue;
			clone.add(inner);
		}
		for (Variable variable : node.getObject().getVariables()) {
			clone.add(variable);
		}
		return clone;
	}

	private void removeFromNode(DeclaredNode node, List<Node> toRemove) {
		for (Node removeNode : toRemove) node.getInnerNodes().remove(removeNode);
	}

	private void removeVarFromNode(DeclaredNode node, List<Variable> toRemove) {
		for (Variable variable : toRemove) node.getObject().getVariables().remove(variable);
	}

	private void processFacetTarget(DeclaredNode facetNode) throws DependencyException {
		for (FacetTarget facetTarget : facetNode.getObject().getFacetTargets()) {
			List<Node> destinies = new ArrayList();
			resolveDestiniesOfTarget(facetNode, facetTarget, destinies);
			addAllowedFacetsToDestinies(facetNode, destinies, facetTarget);
		}
		facetNode.getObject().getVariables().clear();
	}


//	private void processFacetTarget(DeclaredNode facetNode) throws DependencyException {
//		for (FacetTarget facetTarget : facetNode.getObject().getFacetTargets()) {
//			List<Node> destinies = new ArrayList();
//			resolveDestiniesOfTarget(facetNode, facetTarget, destinies);
//			if (facetNode.is(ABSTRACT)) continue;
//			addAllowedFacetsToDestinies(facetNode, destinies, facetTarget);
//		}
//		facetNode.getObject().getVariables().clear();
//	}


	private void propagateComponentsToFacets(DeclaredNode node) throws DependencyException {
		for (FacetTarget target : node.getObject().getFacetTargets()) {
			for (Variable commonVariable : node.getObject().getVariables())
				target.add(0, commonVariable);
			resolveVariableReferences(node, target.getVariableReferences());
			for (Node inner : node.getInnerNodes())
				if (inner.is(LinkNode.class))
					target.add(new LinkNode(((LinkNode) inner).getDestiny(), null));
				else if (!inner.isSub())
					target.add(new LinkNode((DeclaredNode) inner, null));
		}
	}

	private void resolveVariableReferences(DeclaredNode node, List<Reference> references) throws DependencyException {
		for (Reference reference : references) {
			DeclaredNode declaredNode = model.searchDeclarationOfReference(reference.getType(), node);
			if (declaredNode == null) declaredNode = (DeclaredNode) model.get(reference.getType());
			if (declaredNode == null)
				throw new DependencyException("Not found reference: " + reference.getType(), node);
			reference.setType(declaredNode.getQualifiedName());
		}
	}


	private void resolveDestiniesOfTarget(Node facetNode, FacetTarget facetTarget, List<Node> destinies) throws DependencyException {
		Node node;
		if (facetTarget.getDestinyQN() != null)
			node = model.get(facetTarget.getDestinyQN());
		else {
			node = model.searchDeclarationOfReference(facetTarget.getDestinyName(), facetNode);
			if (node == null)
				throw new DependencyException("Not found reference: " + facetTarget.getDestinyName(), facetNode);
			facetTarget.setDestinyQN(node.getQualifiedName());
		}
		if (!node.getObject().is(FACET))
			destinies.add(node);
		else for (FacetTarget object : node.getObject().getFacetTargets())
			resolveDestiniesOfTarget(node, object, destinies);
	}

	private void addAllowedFacetsToDestinies(DeclaredNode facetNode, List<Node> targets, FacetTarget facet) {
		for (Node target : targets) {
			target.getObject().addAllowedFacet(facetNode.getQualifiedName(), facet);
			ArrayList<NodeObject> descendants = new ArrayList<>();
			collectDescentOfNode(target.getObject(), descendants);
			for (NodeObject destiny : descendants)
				destiny.addAllowedFacet(facetNode.getQualifiedName(), facet);
		}
	}

	private void collectDescentOfNode(NodeObject object, List<NodeObject> descendants) {
		if (object.getChildren() == null) return;
		for (NodeObject child : object.getChildren()) {
			descendants.add(child);
			if (child.getChildren() != null) collectDescentOfNode(child, descendants);
		}
	}
}
