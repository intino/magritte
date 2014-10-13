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
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.lang.*;

import java.util.List;

public class ParametersAnnotator extends TaraAnnotator {
	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder annotationHolder) {
		if (!Signature.class.isInstance(element)) return;
		Concept concept = TaraPsiImplUtil.getConceptContextOf(element);
		boolean system = ModuleConfiguration.getInstance(TaraUtil.getModuleOfFile(element.getContainingFile())).isSystem();
		if (isLinkConcept(concept)) return;
		Model heritage = TaraLanguage.getMetaModel(element.getContainingFile());
		Node node;
		if (heritage == null || (node = findNode(concept, heritage)) == null) return;
		NodeObject object = node.getObject();
		Parameters[] parameters = PsiTreeUtil.getChildrenOfType(element, Parameters.class);
		int minimum = collectMinimumNumberOfParameter(node.getObject().getVariables(), system);
		if (parameters == null && minimum > 0 || (parameters != null) && parameters[0].getParameters().length < minimum) {
			Annotation errorAnnotation = annotationHolder.createErrorAnnotation(element, "parameters missed: " + variablesToString(object));
			errorAnnotation.registerFix(new AddParametersFix(element, object.getVariables()));
		}
	}

	private int collectMinimumNumberOfParameter(List<Variable> variables, boolean system) {
		int result = variables.size();
		for (Variable variable : variables)
			if ((variable.getDefaultValues() != null && variable.getDefaultValues().length > 0) || (!system && variable.isTerminal()) || (variable instanceof Word))
				result--;
		return result;
	}

	private String variablesToString(NodeObject node) {
		StringBuilder builder = new StringBuilder();
		for (Variable variable : node.getVariables()) builder.append(", ").append(variable.toString());
		return builder.toString().substring(2);
	}
}
