package tara.intellij.annotator.fix;

import com.intellij.codeInsight.FileModificationService;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import tara.intellij.messages.MessageProvider;
import tara.intellij.project.TaraModuleType;
import tara.intellij.project.module.ModuleProvider;

class ConfigureModuleFix implements IntentionAction {

	public ConfigureModuleFix(PsiElement element) {
	}

	@NotNull
	public String getText() {
		return MessageProvider.message("configure.module");
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
		if (!TaraModuleType.isTara(ModuleProvider.moduleOf(file))) return;
		//TODO
	}

	public boolean startInWriteAction() {
		return true;
	}
}