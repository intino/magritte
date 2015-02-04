package siani.tara.intellij.annotator.fix;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.codegeneration.LinkToJava;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.project.module.ModuleProvider;

public class LinkToJavaFix implements IntentionAction {
	private final Concept concept;

	public LinkToJavaFix(Concept concept) {
		this.concept = concept;
	}

	@NotNull
	@Override
	public String getText() {
		return "Link To Java";
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
		Module module = ModuleProvider.getModuleOf(file);
		LinkToJava.link(module);
	}

	@Override
	public boolean startInWriteAction() {
		return true;
	}
}
