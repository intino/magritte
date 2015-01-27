package siani.tara.intellij.annotator.semanticAnalizers;

import com.intellij.codeInsight.intention.IntentionAction;
import siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix;
import siani.tara.intellij.annotator.fix.OfferCompletingFacetParameters;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraFacetApply;
import siani.tara.intellij.lang.psi.TaraParameters;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.lang.FacetTarget;
import siani.tara.lang.Node;
import siani.tara.lang.Variable;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;

public class FacetApplyAnalyzer extends TaraAnalyzer {

	private final TaraFacetApply facetApply;

	public FacetApplyAnalyzer(TaraFacetApply facetApply) {
		this.facetApply = facetApply;
	}

	@Override
	public void analyze() {
		Concept concept = TaraPsiImplUtil.getConceptContainerOf(facetApply);
		Node node = TaraUtil.findNode(concept, getMetamodel(facetApply));
		if (node == null) return;
		if (!isAllowedFacet(node, facetApply.getMetaIdentifierList().get(0).getText()))
			results.put(facetApply, new AnnotateAndFix(ERROR, "This facet is not allowed in this context"));
		else if (isDuplicatedFacet(concept, facetApply))
			results.put(facetApply, new AnnotateAndFix(ERROR, "Duplicated facet"));
		else checkParameters(node);
	}

	public void checkParameters(Node node) {
		if (facetApply.getParameters() == null) return;
		TaraParameters parameters = facetApply.getParameters();
		List<Variable> facetVariables = getFacetVariables(node.getObject().getAllowedFacets(), facetApply.getMetaIdentifierList().get(0).getText());
		int minimum = collectMinimumNumberOfParameter(facetVariables);
		if ((parameters.getParameters().length < minimum)) {
			AnnotateAndFix value = new AnnotateAndFix(ERROR, "Parameters missed: " + variablesToString(facetVariables));
			if (parameters.getParameters().length == 0)
				value.setActions(new IntentionAction[]{new OfferCompletingFacetParameters(facetApply, facetVariables)});
			results.put(parameters, value);
		}
	}

	private boolean isAllowedFacet(Node node, String name) {
		for (String key : node.getObject().getAllowedFacets().keySet())
			if (key.substring(key.lastIndexOf(".") + 1).equals(name)) return true;
		return false;
	}

	private boolean isDuplicatedFacet(Concept concept, TaraFacetApply facetApply) {
		TaraFacetApply[] facetApplies = concept.getFacetApplies();
		int count = 0;
		for (TaraFacetApply apply : facetApplies) {
			if (apply == null || apply.getMetaIdentifierList().isEmpty() || facetApply.getMetaIdentifierList().isEmpty())
				continue;
			if (facetApply.getMetaIdentifierList().get(0).getText().equals(apply.getMetaIdentifierList().get(0).getText()))
				count++;
		}
		return (count > 1);
	}

	private List<Variable> getFacetVariables(Map<String, List<FacetTarget>> facets, String facetName) {
//		for (String key : facets.keySet())TODO
//			if (key.endsWith(facetName)) return facets.get(key).getVariables();
		return Collections.EMPTY_LIST;
	}

	private int collectMinimumNumberOfParameter(List<Variable> variables) {
		int result = variables.size();
		for (Variable variable : variables)
			if (variable.getDefaultValues() != null && variable.getDefaultValues().length > 0)
				result--;
		return result;
	}

	private String variablesToString(List<Variable> variables) {
		StringBuilder builder = new StringBuilder();
		for (Variable variable : variables) builder.append(", ").append(variable.toString());
		return builder.toString().substring(2);
	}
}
