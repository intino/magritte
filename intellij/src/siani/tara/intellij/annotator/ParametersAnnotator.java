package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.annotator.fix.OfferCompletingParameters;
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
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (!Signature.class.isInstance(element)) return;
		Concept concept = TaraPsiImplUtil.getConceptContainerOf(element);
		boolean system = ModuleConfiguration.getInstance(TaraUtil.getModuleOfFile(element.getContainingFile())).isTerminal();
		if (isLinkConcept(concept)) return;
		Model heritage = TaraLanguage.getMetaModel(element.getContainingFile());
		Node node;
		if (heritage == null || (node = findNode(concept, heritage)) == null) return;
		NodeObject object = node.getObject();
		Parameters[] parameters = PsiTreeUtil.getChildrenOfType(element, Parameters.class);
		List<Variable> variables = node.getObject().getVariables();
		int minimum = collectMinimumNumberOfParameter(variables, system);
		if (parameters == null && minimum > 0 || (parameters != null) && parameters[0].getParameters().length < minimum) {
			Annotation annotation = holder.createErrorAnnotation(element, "parameters missed: " + variablesToString(object));
			if (parameters == null || parameters[0].getParameters().length == 0)
				annotation.registerFix(new OfferCompletingParameters((Signature) element, variables));
		} else if (variables.size() > 0 && (parameters == null || parameters.length < variables.size())) {
			Annotation annotation = holder.createInfoAnnotation(element, "Add explicit parameters");
			annotation.registerFix(new OfferCompletingParameters((Signature) element, variables));
		}
	}

	private int collectMinimumNumberOfParameter(List<Variable> variables, boolean terminal) {
		int result = variables.size();
		for (Variable variable : variables)
			if ((variable.getDefaultValues() != null && variable.getDefaultValues().length > 0) || (!terminal && variable.isTerminal()) || (variable instanceof Word))
				result--;
		return result;
	}

	private String variablesToString(NodeObject node) {
		StringBuilder builder = new StringBuilder();
		for (Variable variable : node.getVariables()) builder.append(", ").append(variable.toString());
		return builder.toString().substring(2);
	}
}
