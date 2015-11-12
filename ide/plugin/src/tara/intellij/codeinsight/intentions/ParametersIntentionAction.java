package tara.intellij.codeinsight.intentions;

import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.Parameters;
import tara.lang.model.Node;
import tara.lang.semantics.Constraint;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ParametersIntentionAction extends PsiElementBaseIntentionAction {

	protected Parameters getParametersScope(PsiElement element) {
		PsiElement parent = element.getParent();
		while (parent != null && !PsiFile.class.isInstance(parent) && !Node.class.isInstance(parent)) {
			if (parent instanceof Parameters) return (Parameters) parent;
			parent = parent.getParent();
		}
		return null;
	}

	protected Constraint.Parameter findCorrespondingAllow(List<Constraint.Parameter> allows, int position) {
		return position >= allows.size() ? null : allows.get(position);
	}


	protected List<Constraint.Parameter> filterParametersAllow(List<Constraint> allows) {
		return allows.stream().filter(constraint -> constraint instanceof Constraint.Parameter).
			map(constraint -> (Constraint.Parameter) constraint).
			collect(Collectors.toList());
	}


	protected Constraint.Parameter findCorrespondingAllow(List<Constraint.Parameter> parameters, String name) {
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