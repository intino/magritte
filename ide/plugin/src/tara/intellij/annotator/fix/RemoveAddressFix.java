package tara.intellij.annotator.fix;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.TaraNode;
import tara.lang.model.Node;

public class RemoveAddressFix implements IntentionAction {
	private final TaraNode node;

	public RemoveAddressFix(Node node) {
		this.node = (TaraNode) node;
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
		node.getSignature().getAddress().delete();
	}

	@Override
	public boolean startInWriteAction() {
		return true;
	}
}
