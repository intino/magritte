package siani.tara.intellij.annotator.fix;

import com.intellij.codeInsight.FileModificationService;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.TaraBundle;
import siani.tara.intellij.lang.psi.TaraConceptReference;
import siani.tara.intellij.lang.psi.TaraTypes;

public class RemoveConceptLinkFix implements IntentionAction {
	private final TaraConceptReference conceptReference;

	public RemoveConceptLinkFix(@NotNull final TaraConceptReference conceptReference) {
		this.conceptReference = conceptReference;
	}

	@NotNull
	public String getText() {
		return TaraBundle.message("remove.reference.intention.text");
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