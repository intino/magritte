package tara.intellij.annotator.fix;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.codeinsight.intentions.MethodReferenceCreator;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.Valued;

public class CreateClassFromMethodReferenceFix extends ClassCreationIntention {
	private final Valued valued;

	public CreateClassFromMethodReferenceFix(Valued valued) {
		this.valued = valued;

	}

	@Nls
	@NotNull
	@Override
	public String getText() {
		return "Create method reference";
	}

	@Nls
	@NotNull
	@Override
	public String getFamilyName() {
		return getText();
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		return element.getContainingFile() instanceof TaraModel;
	}

	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		final PsiMethod method = new MethodReferenceCreator(valued, element.getText()).create("");
		if (method != null) {
			QuickEditHandler handler = new QuickEditHandler(project, editor, method.getContainingFile(), method);
			if (!ApplicationManager.getApplication().isUnitTestMode()) handler.navigate();
		}
	}

}
