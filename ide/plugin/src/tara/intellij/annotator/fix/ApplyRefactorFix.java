package tara.intellij.annotator.fix;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.project.module.ModuleProvider;
import tara.lang.model.Node;

public class ApplyRefactorFix implements IntentionAction {
	private final Node node;

	public ApplyRefactorFix(Node node) {
		this.node = node;
	}

	public ApplyRefactorFix(PsiElement element) {
		this.node = TaraPsiImplUtil.getContainerNodeOf(element);
	}


	@NotNull
	@Override
	public String getText() {
		return "Apply language refactors";
	}

	@NotNull
	@Override
	public String getFamilyName() {
		return getText();
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
		return file.isValid();
	}

	@Override
	public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
		new LanguageRefactor(ModuleProvider.getModuleOf(file)).apply();
	}

	@Override
	public boolean startInWriteAction() {
		return true;
	}
}