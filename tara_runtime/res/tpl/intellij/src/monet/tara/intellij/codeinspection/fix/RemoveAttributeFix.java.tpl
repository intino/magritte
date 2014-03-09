package monet.::projectName::.intellij.codeinspection.fix;

import com.intellij.codeInsight.FileModificationService;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import monet.::projectName::.intellij.::projectProperName::Bundle;
import monet.::projectName::.intellij.metamodel.psi.::projectProperName::Attribute;
import org.jetbrains.annotations.NotNull;

/**
 * Created by oroncal on 09/01/14.
 */
public class RemoveAttributeFix implements IntentionAction {
	private final ::projectProperName::Attribute attribute;

	public RemoveAttributeFix(@NotNull final ::projectProperName::Attribute attribute) {
		this.attribute = attribute;
	}

	@NotNull
	public String getText() {
		return ::projectProperName::Bundle.message("remove.attribute.intention.text");
	}

	@NotNull
	public String getFamilyName() {
		return getText();
	}

	public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
		return file.isValid() && attribute.isValid() && attribute.getManager().isInProject(attribute);
	}

	public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
		if (!FileModificationService.getInstance().prepareFileForWrite(file)) return;
		attribute.delete();
	}

	public boolean startInWriteAction() {
		return true;
	}
}