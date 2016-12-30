package io.intino.tara.plugin.codeinsight.intentions;

import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import io.intino.tara.plugin.lang.psi.Parameters;
import org.jetbrains.annotations.NotNull;
import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.semantics.Constraint;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ParametersIntentionAction extends PsiElementBaseIntentionAction {

	Parameters getParametersScope(PsiElement element) {
		PsiElement parent = element.getParent();
		while (parent != null && !PsiFile.class.isInstance(parent) && !Node.class.isInstance(parent)) {
			if (parent instanceof Parameters) return (Parameters) parent;
			parent = parent.getParent();
		}
		return null;
	}

	List<Constraint.Parameter> filterParameterConstraints(List<Constraint> allows) {
		return allows.stream().filter(constraint -> constraint instanceof Constraint.Parameter).
			map(constraint -> (Constraint.Parameter) constraint).
			collect(Collectors.toList());
	}


	Constraint.Parameter findCorrespondingAllow(List<Constraint.Parameter> parameters, String name) {
		for (Constraint.Parameter parameter : parameters)
			if (name.equals(parameter.name())) return parameter;
		return null;
	}

	@NotNull
	@Override
	public String getFamilyName() {
		return getText();
	}

}