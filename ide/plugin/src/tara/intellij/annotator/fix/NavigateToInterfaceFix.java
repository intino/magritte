package tara.intellij.annotator.fix;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import tara.intellij.messages.MessageProvider;
import tara.intellij.lang.psi.TaraVariable;
import tara.intellij.lang.psi.resolve.ReferenceManager;

public class NavigateToInterfaceFix implements IntentionAction {

	private final TaraVariable variable;

	public NavigateToInterfaceFix(PsiElement element) {
		variable = (TaraVariable) element;
	}

	@NotNull
	public String getText() {
		return MessageProvider.message("create.native.signature");
	}

	@NotNull
	public String getFamilyName() {
		return getText();
	}

	public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
		return file.isValid() && variable != null && variable.getRuleContainer() != null;
	}

	public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
		final PsiElement psiElement = ReferenceManager.resolveRule(variable.getRuleContainer().getRule());
		if (psiElement != null)
			psiElement.getContainingFile().navigate(true);
	}

	public boolean startInWriteAction() {
		return false;
	}
}