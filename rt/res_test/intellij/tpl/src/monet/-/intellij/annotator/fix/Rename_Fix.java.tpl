package monet.::projectName::.intellij.annotator.fix;

import com.intellij.codeInsight.FileModificationService;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import monet.::projectName::.intellij.::projectProperName::Bundle;
import monet.::projectName::.intellij.lang.psi.Definition;
import org.jetbrains.annotations.NotNull;

public class RenameDefinitionFix implements IntentionAction {
	private final Definition myDefinition;

	public RenameDefinitionFix(\@NotNull final Definition origDefinition) {
		myDefinition = origDefinition;
	}

	\@NotNull
	public String getText() {
		return ::projectProperName::Bundle.message("remove.definition.intention.text");
	}

	\@NotNull
	public String getFamilyName() {
		return getText();
	}

	public boolean isAvailable(\@NotNull Project project, Editor editor, PsiFile file) {
		return file.isValid() && myDefinition.isValid() && myDefinition.getManager().isInProject(myDefinition);
	}

	public void invoke(\@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
		if (!FileModificationService.getInstance().prepareFileForWrite(file)) return;
		myDefinition.delete();
	}

	public boolean startInWriteAction() {
		return true;
	}
}