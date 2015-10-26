package tara.intellij.annotator.fix;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.codeinsight.languageinjection.NativesGenerator;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.TaraVariable;

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
		return file.isValid() && file instanceof TaraModel;
	}

	@Override
	public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
		if (file == null || variable == null || variable.getRuleContainer() == null) return;
		final PsiClass destiny = new NativesGenerator(project, (TaraModel) file).generate(variable);
		if (destiny != null) destiny.navigate(true);
	}

	@Override
	public boolean startInWriteAction() {
		return true;
	}
}
