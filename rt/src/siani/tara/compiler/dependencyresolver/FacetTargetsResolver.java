package siani.tara.compiler.dependencyresolver;

import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.lang.*;

import java.util.ArrayList;
import java.util.List;

import static siani.tara.lang.Annotation.Annotation.FACET;

public class FacetTargetsResolver {
	private final Model model;

	public FacetTargetsResolver(Model model) {
		this.model = model;
	}

	public void resolve() throws TaraException {
		for (Node node : model.getNodeTable().values())
			if (node.is(DeclaredNode.class) && node.getObject().is(FACET)) {
				DeclaredNode declaredNode = (DeclaredNode) node;
				if (declaredNode.getObject().getFacetTargets().size() > 0) {
					propagateVariablesHierarchy(declaredNode);
					processAsFacetTarget(declaredNode);
				}
			}
	}

	private void propagateVariablesHierarchy(DeclaredNode node) throws TaraException {
		for (FacetTarget target : node.getObject().getFacetTargets()) {
			for (Variable commonVariable : node.getObject().getVariables())
				target.add(0, commonVariable);
			if (target.getParentTarget() != null)
				for (Variable targetVar : target.getParentTarget().getVariables())
					if (!target.getVariables().contains(targetVar)) target.add(0, targetVar);
			resolveVariableReferences(node, target.getVariableReferences());
		}
	}

	private void resolveVariableReferences(DeclaredNode node, List<Reference> references) throws TaraException {
		for (Reference reference : references) {
			DeclaredNode declaredNode = model.searchDeclarationOfReference(reference.getType(), node);
			if (declaredNode == null) declaredNode = (DeclaredNode) model.get(reference.getType());
			if (declaredNode == null)
				throw new TaraException("Reference in facet target not found: " + node.getName() + ":" + reference.getType());
			reference.setType(declaredNode.getQualifiedName());
		}
	}

	private void processAsFacetTarget(DeclaredNode facetNode) {
		for (FacetTarget facetTarget : facetNode.getObject().getFacetTargets()) {
			List<Node> destinies = new ArrayList();
			resolveDestiniesOfTarget(facetNode, facetTarget, destinies);
			addAllowedFacetsToDestinies(facetNode, destinies, facetTarget);
		}
		facetNode.getObject().getVariables().clear();
	}


	private void resolveDestiniesOfTarget(Node facetNode, FacetTarget facetTarget, List<Node> destinies) {
		Node node;
		if (facetTarget.getDestinyQN() != null)
			node = model.get(facetTarget.getDestinyQN());
		else {
			node = model.searchDeclarationOfReference(facetTarget.getDestinyName(), facetNode);
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
