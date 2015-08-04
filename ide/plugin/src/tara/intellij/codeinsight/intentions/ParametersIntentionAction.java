package tara.intellij.codeinsight.intentions;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.Parameters;
import tara.language.model.Node;

public abstract class ParametersIntentionAction extends PsiElementBaseIntentionAction implements IntentionAction {

	Parameters getParametersScope(PsiElement element) {
		PsiElement parent = element.getParent();
		while (parent != null && !PsiFile.class.isInstance(parent) && !Node.class.isInstance(parent)) {
			if (parent instanceof Parameters) return (Parameters) parent;
			parent = parent.getParent();
		}
		return null;
	}

	@NotNull
	@Override
	public String getFamilyName() {
		return getText();
	}

}