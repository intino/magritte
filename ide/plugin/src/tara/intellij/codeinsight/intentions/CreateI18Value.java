package tara.intellij.codeinsight.intentions;

import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.codeinsight.intentions.dialog.CreateStringValues;
import tara.intellij.lang.psi.StringValue;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.project.module.ModuleProvider;

public class CreateI18Value extends PsiElementBaseIntentionAction {

	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		final CreateStringValues dialog = new CreateStringValues(ModuleProvider.getModuleOf(element), TaraPsiImplUtil.getContainerByType(element, StringValue.class).getValue());
		dialog.pack();
		dialog.setLocationRelativeTo(dialog.getParent());
		dialog.setVisible(true);
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		final StringValue stringValue = TaraPsiImplUtil.getContainerByType(element, StringValue.class);
		return stringValue != null;
	}

	@Nls
	@NotNull
	@Override
	public String getFamilyName() {
		return getText();
	}

	@NotNull
	@Override
	public String getText() {
		return "Create i18n value";
	}

}
