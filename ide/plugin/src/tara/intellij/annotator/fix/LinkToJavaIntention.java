package tara.intellij.annotator.fix;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.codegeneration.LinkToJava;
import tara.intellij.lang.psi.TaraVariable;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.intellij.project.module.ModuleProvider;

public class LinkToJavaIntention implements IntentionAction {

	private TaraVariable variable;

	public LinkToJavaIntention(TaraVariable variable) {
		this.variable = variable;
	}

	public LinkToJavaIntention() {
	}

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
		if (variable == null || variable.getAttributeType() == null) return;
		final NavigatablePsiElement destiny = (NavigatablePsiElement) ReferenceManager.resolveContract(variable.getAttributeType().getContract());
		if (destiny != null) destiny.navigate(true);
	}

	@Override
	public boolean startInWriteAction() {
		return true;
	}
}
