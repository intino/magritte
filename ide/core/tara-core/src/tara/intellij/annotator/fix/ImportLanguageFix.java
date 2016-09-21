package tara.intellij.annotator.fix;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.framework.LanguageImporter;
import tara.intellij.lang.file.TaraFileType;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.messages.MessageProvider;
import tara.intellij.project.module.ModuleProvider;

public class ImportLanguageFix implements IntentionAction {


	public ImportLanguageFix(PsiElement element) {
	}

	@Nls
	@NotNull
	@Override
	public String getText() {
		return MessageProvider.message("import.dsl");
	}

	@Nls
	@NotNull
	@Override
	public String getFamilyName() {
		return getText();
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
		return file.isValid() && file.getFileType() == TaraFileType.instance();
	}

	@Override
	public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
		LanguageImporter importer = new LanguageImporter(ModuleProvider.moduleOf(file));
		importer.importLanguage(((TaraModel) file).dsl(), "LATEST");

	}

	@Override
	public boolean startInWriteAction() {
		return false;
	}
}
