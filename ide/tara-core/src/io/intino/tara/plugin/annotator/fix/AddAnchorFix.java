package io.intino.tara.plugin.annotator.fix;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import io.intino.tara.plugin.lang.psi.impl.TaraPsiImplUtil;
import org.jetbrains.annotations.NotNull;
import io.intino.tara.lang.model.Node;

public class AddAnchorFix implements IntentionAction {
	private final Node node;

	public AddAnchorFix(Node node) {
		this.node = node;
	}

	public AddAnchorFix(PsiElement element) {
		this.node = TaraPsiImplUtil.getContainerNodeOf(element);
	}


	@NotNull
	@Override
	public String getText() {
		return "Add anchor to this type";
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
		new AnchorAggregator(node).generate();
	}

	@Override
	public boolean startInWriteAction() {
		return true;
	}
}
