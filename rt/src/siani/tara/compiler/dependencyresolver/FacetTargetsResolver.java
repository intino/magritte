package siani.tara.compiler.dependencyresolver;

import siani.tara.lang.*;

import java.util.Collection;
import java.util.List;

public class FacetTargetsResolver {
	private final Model model;

	public FacetTargetsResolver(Model model) {
		this.model = model;
	}

	public void resolve() {
		for (Node node : model.getTree())
			if (node instanceof IntentionNode) {
				IntentionNode intentionNode = (IntentionNode) node;
				if (intentionNode.getObject().getFacetTargets().size() > 0) {
					propagateVariablesHierarchy(intentionNode.getObject());
					processAsFacetTarget(intentionNode);
				}
			}
	}

	private void propagateVariablesHierarchy(IntentionObject object) {
		for (IntentionObject target : object.getFacetTargets()) {
			for (Variable commonVariable : object.getVariables())
				target.add(commonVariable);
			if (target.getParent() == null) continue;
			for (Variable targetVar : target.getParent().getVariables())
				if (!target.getVariables().contains(targetVar)) target.add(targetVar);
		}
	}

	private void processAsFacetTarget(IntentionNode node) {
		for (IntentionObject facetTarget : node.getObject().getFacetTargets()) {
			Node targetNode = model.searchDeclarationOfRefererence(facetTarget.getName(), node);
			targetNode.getObject().addAllowedFacet(node.getQualifiedName(), facetTarget.getVariables());
			for (String constrain : facetTarget.getFacetConstrains())
				targetNode.getObject().putFacetConstrain(node.getQualifiedName(), model.searchDeclarationOfRefererence(constrain, node).getQualifiedName());
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
