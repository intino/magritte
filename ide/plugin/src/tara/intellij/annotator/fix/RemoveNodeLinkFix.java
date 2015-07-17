package tara.intellij.annotator.fix;

import com.intellij.codeInsight.FileModificationService;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import tara.intellij.MessageProvider;
import tara.intellij.lang.psi.NodeReference;
import tara.intellij.lang.psi.TaraTypes;

public class RemoveNodeLinkFix implements IntentionAction {
	private final NodeReference conceptReference;

	public RemoveNodeLinkFix(@NotNull final NodeReference conceptReference) {
		this.conceptReference = conceptReference;
	}

	@NotNull
	public String getText() {
		return MessageProvider.message("remove.reference.intention.text");
	}

	@NotNull
	public String getFamilyName() {
		return getText();
	}

	public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
		return file.isValid() && conceptReference.isValid() && conceptReference.getManager().isInProject(conceptReference);
	}

	public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
		if (!FileModificationService.getInstance().prepareFileForWrite(file)) return;
		if (conceptReference.getNextSibling() != null && conceptReference.getNextSibling().getNode().getElementType().equals(TaraTypes.NEWLINE))
			conceptReference.getNextSibling().delete();
		conceptReference.delete();
	}

	public boolean startInWriteAction() {
		return true;
	}
}