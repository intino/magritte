package monet.tara.transpiler.intellij.codeinspection.fix;

import com.intellij.codeInsight.FileModificationService;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import monet.tara.transpiler.intellij.metamodel.TaraBundle;
import monet.tara.transpiler.intellij.psi.TaraConcept;
import org.jetbrains.annotations.NotNull;

/**
 * Created by oroncal on 09/01/14.
 */
public class RemoveConceptFix implements IntentionAction {
	private final TaraConcept myConcept;

	public RemoveConceptFix(@NotNull final TaraConcept origConcept) {
		myConcept = origConcept;
	}

	@NotNull
	public String getText() {
		return TaraBundle.message("remove.concept.intention.text");
	}

	@NotNull
	public String getFamilyName() {
		return getText();
	}

	public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
		return file.isValid()
			&& myConcept != null
			&& myConcept.isValid()
			&& myConcept.getManager().isInProject(myConcept);
	}

	public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
		if (!FileModificationService.getInstance().prepareFileForWrite(file)) return;
		myConcept.delete();
	}

	public boolean startInWriteAction() {
		return true;
	}
}