package monet.tara.intellij.actions;

import com.intellij.CommonBundle;
import com.intellij.ide.actions.CreateElementActionBase;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public abstract class NewTaraActionBase extends CreateElementActionBase {

	@NonNls
	public static final String TARA_EXTENSION = ".m2";

	public NewTaraActionBase(String text, String description, Icon icon) {
		super(text, description, icon);
	}

	@NotNull
	protected final PsiElement[] invokeDialog(final Project project, final PsiDirectory directory) {
		MyInputValidator validator = new MyInputValidator(project, directory);
		Messages.showInputDialog(project, getDialogPrompt(), getDialogTitle(), Messages.getQuestionIcon(), "", validator);
		return validator.getCreatedElements();
	}

	protected abstract String getDialogPrompt();

	protected abstract String getDialogTitle();

	@Override
	protected boolean isAvailable(DataContext dataContext) {
		return super.isAvailable(dataContext);
	}

	@NotNull
	protected PsiElement[] create(String newName, PsiDirectory directory) throws Exception {
		return doCreate(newName, directory);
	}

	@NotNull
	protected abstract PsiElement[] doCreate(String newName, PsiDirectory directory) throws Exception;


	protected String getErrorTitle() {
		return CommonBundle.getErrorTitle();
	}
}