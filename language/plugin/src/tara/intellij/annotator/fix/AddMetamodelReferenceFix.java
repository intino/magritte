package tara.intellij.annotator.fix;

import com.intellij.codeInsight.FileModificationService;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import tara.intellij.MessageProvider;
import tara.intellij.lang.psi.TaraModel;

public class AddMetamodelReferenceFix implements IntentionAction {

	private final PsiFile element;

	public AddMetamodelReferenceFix(PsiElement element) {
		this.element = (PsiFile) element;
	}

	@NotNull
	public String getText() {
		return MessageProvider.message("add.metamodel.reference.intention");
	}

	@NotNull
	public String getFamilyName() {
		return getText();
	}

	public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
		return file.isValid();
	}

	public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
		if (!FileModificationService.getInstance().prepareFileForWrite(file)) return;
		((TaraModel) element).updateDSL();
	}

	public boolean startInWriteAction() {
		return true;
	}
}