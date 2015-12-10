package tara.intellij.annotator.fix;

import com.intellij.codeInsight.FileModificationService;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import tara.intellij.MessageProvider;

public class ImportMetamodelFix implements IntentionAction {

	private static final Logger LOG = Logger.getInstance(ImportMetamodelFix.class.getName());

	public ImportMetamodelFix(PsiElement element) {
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
		try {
//			new FrameworkImporter(ModuleProvider.getModuleOf(file)).importLanguage(); //TODO
		} catch (Exception e) {
			LOG.info(e.getMessage(), e);
		}
	}

	public boolean startInWriteAction() {
		return true;
	}
}