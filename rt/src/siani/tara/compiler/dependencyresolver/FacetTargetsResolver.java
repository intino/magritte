package siani.tara.compiler.dependencyresolver;

import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.lang.*;

import java.util.Collection;
import java.util.List;

public class FacetTargetsResolver {
	private final Model model;

	public FacetTargetsResolver(Model model) {
		this.model = model;
	}

	public void resolve() throws TaraException {
		for (Node node : model.getTreeModel())
			if (node instanceof IntentionNode) {
				IntentionNode intentionNode = (IntentionNode) node;
				if (intentionNode.getObject().getFacetTargets().size() > 0) {
					propagateVariablesHierarchy(intentionNode);
					processAsFacetTarget(intentionNode);
				}
			}
	}

	private void propagateVariablesHierarchy(IntentionNode node) throws TaraException {
		for (IntentionObject target : node.getObject().getFacetTargets()) {
			for (Variable commonVariable : node.getObject().getVariables()) target.add(commonVariable);
			if (target.getParent() != null)
				for (Variable targetVar : target.getParent().getVariables())
					if (!target.getVariables().contains(targetVar)) target.add(targetVar);
			resolveReferences(node, target.getReferences());
		}
	}

	private void resolveReferences(IntentionNode node, List<Reference> references) throws TaraException {
		for (Reference reference : references) {
			DeclaredNode declaredNode = model.searchDeclarationOfReference(reference.getType(), node);
			if (declaredNode == null) declaredNode = (DeclaredNode) model.get(reference.getType());
			if (declaredNode == null)
				throw new TaraException("Reference in facet target not found: " + node.getName() + ":" + reference.getType());
			reference.setType(declaredNode.getQualifiedName());
		}
	}

	private void processAsFacetTarget(IntentionNode node) {
		for (IntentionObject facetTarget : node.getObject().getFacetTargets()) {
			Node targetNode = model.searchDeclarationOfReference(facetTarget.getName(), node);
			targetNode.getObject().addAllowedFacet(node.getQualifiedName(), facetTarget.getVariables());
			for (String constrain : facetTarget.getFacetConstrains())
				targetNode.getObject().putFacetConstrain(node.getQualifiedName(), model.searchDeclarationOfReference(constrain, node).getQualifiedName());
			propagateToChildren(targetNode.getObject(), node.getQualifiedName(), facetTarget.getVariables(), targetNode.getObject().getAllowedFacetsConstrains().values());
		}
		node.getObject().getVariables().clear();
	}

	private void propagateToChildren(NodeObject nodeObject, String name, List<Variable> variables, Collection<String> facetConstrains) {
		if (nodeObject.getChildren() == null) return;
		for (NodeObject object : nodeObject.getChildren()) {
			object.addAllowedFacet(name, variables);
			for (String constrain : facetConstrains)
				object.putFacetConstrain(name, constrain);
			propagateToChildren(object, name, variables, facetConstrains);
		}
	}
}
