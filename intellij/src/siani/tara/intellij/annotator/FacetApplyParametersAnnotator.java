package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraFacetApply;
import siani.tara.intellij.lang.psi.TaraParameters;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.lang.Model;
import siani.tara.lang.Node;
import siani.tara.lang.Variable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FacetApplyParametersAnnotator extends TaraAnnotator {
	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder annotationHolder) {
		if (!TaraFacetApply.class.isInstance(element)) return;
		TaraFacetApply facetApply = (TaraFacetApply) element;
		if (facetApply.getParameters() == null) return;
		Concept concept = TaraPsiImplUtil.getContextOf(element);
		Model model = TaraLanguage.getMetaModel(element.getContainingFile());
		Node node;
		if (model == null || (node = findNode(concept, model)) == null) return;
		TaraParameters parameters = facetApply.getParameters();
		List<Variable> facetVariables = getFacetVariables(node.getObject().getAllowedFacetsParameters(), facetApply.getMetaIdentifierList().get(0).getText());
		int minimum = collectMinimumNumberOfParameter(facetVariables);
		if (parameters == null && minimum > 0 || (parameters != null) && parameters.getParameters().length < minimum) {
			Annotation errorAnnotation = annotationHolder.createErrorAnnotation(element, "parameters missed: " + variablesToString(facetVariables));
			errorAnnotation.registerFix(new AddParametersFix(element, facetVariables));
		}
	}

	private List<Variable> getFacetVariables(Map<String, List<Variable>> facets, String facetName) {
		List<Variable> variables = new ArrayList<>();
		for (String key : facets.keySet()) if (key.endsWith(facetName)) variables = facets.get(key);
		return variables;
	}

	private int collectMinimumNumberOfParameter(List<Variable> variables) {
		int result = variables.size();
		for (Variable variable : variables)
			if (variable.getValue() != null)
				result--;
		return result;
	}

	private String variablesToString(List<Variable> variables) {
		StringBuilder builder = new StringBuilder();
		for (Variable variable : variables) builder.append(", ").append(variable.toString());
		return builder.toString().substring(2);
	}
}
