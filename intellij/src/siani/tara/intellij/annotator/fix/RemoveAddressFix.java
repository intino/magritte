package siani.tara.intellij.annotator.fix;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.Node;

public class RemoveAddressFix implements IntentionAction {
	private final Node node;

	public RemoveAddressFix(Node node) {
		this.node = node;
	}

	@NotNull
	@Override
	public String getText() {
		return "Remove Address";
	}

	@NotNull
	@Override
	public String getFamilyName() {
		return "Remove address";
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
		return file.isValid();
	}

	@Override
	public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
		node.getAddress().delete();
	}

	@Override
	public boolean startInWriteAction() {
		return true;
	}
}
