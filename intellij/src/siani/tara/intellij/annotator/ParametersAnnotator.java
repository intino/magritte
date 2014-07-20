package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.Parameters;
import siani.tara.intellij.lang.psi.Signature;
import siani.tara.intellij.lang.psi.TaraFile;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.lang.Model;
import siani.tara.lang.Node;
import siani.tara.lang.NodeObject;
import siani.tara.lang.Variable;

import java.util.List;

public class ParametersAnnotator extends TaraAnnotator {
	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder annotationHolder) {
		if (!Signature.class.isInstance(element)) return;
		Concept concept = TaraPsiImplUtil.getContextOf(element);
		if (isLinkConcept(concept)) return;
		Model heritage = TaraLanguage.getMetaModel(((TaraFile) element.getContainingFile()).getParentModel());
		Node node;
		if (heritage == null || (node = findNode(concept, heritage)) == null) return;
		NodeObject object = node.getObject();
		Parameters[] parameters = PsiTreeUtil.getChildrenOfType(element, Parameters.class);
		int minimum = collectMinimumNumberOfParameter(node.getObject().getVariables());
		if (parameters == null && minimum > 0 || (parameters != null) && parameters[0].getParameters().length < minimum) {
			Annotation errorAnnotation = annotationHolder.createErrorAnnotation(element, "parameters missed: " + variablesToString(object));
			errorAnnotation.registerFix(new AddParametersFix((Signature) element, object.getVariables()));
		}
	}//TODO annotate also the facet parameters.

	private int collectMinimumNumberOfParameter(List<Variable> variables) {
		int result = variables.size();
		for (Variable variable : variables)
			if (variable.getValue() != null)
				result--;
		return result;
	}

	private String variablesToString(NodeObject node) {
		StringBuilder builder = new StringBuilder();
		for (Variable variable : node.getVariables()) builder.append(", ").append(variable.toString());
		return builder.toString().substring(2);
	}
}
