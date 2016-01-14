package tara.intellij.annotator.fix;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.lang.model.Node;

public class AddRequiredElementFix extends WithLiveTemplateFix implements IntentionAction {

	private final Node node;

	public AddRequiredElementFix(PsiElement element) {
		this.node = element instanceof Node ? (Node) element : TaraPsiImplUtil.getContainerNodeOf(element);
	}


	@Nls
	@NotNull
	@Override
	public String getText() {
		return "Add required element";
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

	}

	@Override
	public boolean startInWriteAction() {
		return true;
	}
}
