package siani.tara.intellij.annotator.fix;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.codegeneration.AddressGenerator;
import siani.tara.intellij.lang.psi.Concept;

public class AddAddressFix implements IntentionAction {
	private final Concept concept;

	public AddAddressFix(Concept concept) {
		this.concept = concept;
	}

	@NotNull
	@Override
	public String getText() {
		return "Add address to concept";
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
		AddressGenerator generator = new AddressGenerator(concept);
		generator.generate();
	}

	@Override
	public boolean startInWriteAction() {
		return true;
	}
}
