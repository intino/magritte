package tara.intellij.annotator.fix;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import tara.intellij.codegeneration.NameInstanceGenerator;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.language.model.Node;

public class AddAddressFix implements IntentionAction {
	private final Node node;

	public AddAddressFix(Node node) {
		this.node = node;
	}

	public AddAddressFix(PsiElement element) {
		this.node = TaraPsiImplUtil.getContainerNodeOf(element);
	}


	@NotNull
	@Override
	public String getText() {
		return "Add address to this elemen";
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
		NameInstanceGenerator generator = new NameInstanceGenerator(node);
		generator.generate();
	}

	@Override
	public boolean startInWriteAction() {
		return true;
	}
}
