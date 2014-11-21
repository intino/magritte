package siani.tara.intellij.annotator.fix;

import com.intellij.codeInsight.FileModificationService;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.actions.ImportModelAction;
import siani.tara.intellij.lang.psi.TaraBoxFile;

public class ImportMetamodelFix implements IntentionAction {
	private final TaraBoxFile file;

	public ImportMetamodelFix(@NotNull final TaraBoxFile taraBoxFile) {
		this.file = taraBoxFile;
	}

	@NotNull
	public String getText() {
		return MessageProvider.message("import.metamodel.intention.text");
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
			new ImportModelAction().importModel(project);
	}

	public boolean startInWriteAction() {
		return true;
	}
}