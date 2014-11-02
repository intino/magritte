package siani.tara.intellij.annotator.fix;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.Concept;

public class AddAddressFix implements IntentionAction {
	public AddAddressFix(Concept concept) {
	}

	@NotNull
	@Override
	public String getText() {
		return null;
	}

	@NotNull
	@Override
	public String getFamilyName() {
		return null;
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
		return false;
	}

	@Override
	public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {

	}

	@Override
	public boolean startInWriteAction() {
		return false;
	}
}
