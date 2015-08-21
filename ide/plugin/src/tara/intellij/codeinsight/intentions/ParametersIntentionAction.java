package tara.intellij.codeinsight.intentions;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.Parameters;
import tara.language.model.Node;
import tara.language.semantics.Allow;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ParametersIntentionAction extends PsiElementBaseIntentionAction implements IntentionAction {

	protected Parameters getParametersScope(PsiElement element) {
		PsiElement parent = element.getParent();
		while (parent != null && !PsiFile.class.isInstance(parent) && !Node.class.isInstance(parent)) {
			if (parent instanceof Parameters) return (Parameters) parent;
			parent = parent.getParent();
		}
		return null;
	}

	protected Allow.Parameter findCorrespondingAllow(List<Allow.Parameter> allows, int position) {
		return position >= allows.size() ? null : allows.get(position);
	}


	protected List<Allow.Parameter> filterParametersAllow(List<Allow> allows) {
		return allows.stream().filter(allow -> allow instanceof Allow.Parameter).
			map(allow -> (Allow.Parameter) allow).
			collect(Collectors.toList());
	}


	protected Allow.Parameter findCorrespondingAllow(List<Allow.Parameter> parameters, String name) {
		for (Allow.Parameter parameter : parameters)
			if (name.equals(parameter.name())) return parameter;
		return null;
	}

	@NotNull
	@Override
	public String getFamilyName() {
		return getText();
	}

}