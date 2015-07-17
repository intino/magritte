package tara.intellij.annotator.fix;

import com.intellij.codeInsight.FileModificationService;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import tara.intellij.MessageProvider;
import tara.intellij.lang.psi.Variable;

public class RemoveAttributeFix implements IntentionAction {
	private final Variable variable;

	public RemoveAttributeFix(@NotNull final Variable variable) {
		this.variable = variable;
	}

	@NotNull
	public String getText() {
		return MessageProvider.message("remove.attribute.intention");
	}

	@NotNull
	public String getFamilyName() {
		return getText();
	}

	public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
		return file.isValid() && variable.isValid() && variable.getManager().isInProject(variable);
	}

	public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
		if (!FileModificationService.getInstance().prepareFileForWrite(file)) return;
		variable.delete();
	}

	public boolean startInWriteAction() {
		return true;
	}
}