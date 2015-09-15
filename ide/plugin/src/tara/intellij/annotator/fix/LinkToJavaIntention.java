package tara.intellij.annotator.fix;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.codegeneration.LinkToJava;
import tara.intellij.project.module.ModuleProvider;

public class LinkToJavaIntention implements IntentionAction {
	@Nls
	@NotNull
	@Override
	public String getText() {
		return "Link to java";
	}

	@Nls
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
		if (file != null) new LinkToJava().link(ModuleProvider.getModuleOf(file));
	}

	@Override
	public boolean startInWriteAction() {
		return true;
	}
}
