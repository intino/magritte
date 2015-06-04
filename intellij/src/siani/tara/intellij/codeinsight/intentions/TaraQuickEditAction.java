package siani.tara.intellij.codeinsight.intentions;

import com.intellij.codeInsight.intention.impl.QuickEditAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Iconable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.StringValue;
import siani.tara.intellij.lang.psi.impl.TaraUtil;

import javax.swing.*;


public class TaraQuickEditAction extends QuickEditAction implements Iconable {

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
		if (!file.getLanguage().is(TaraLanguage.INSTANCE)) return false;
		StringValue element = PsiTreeUtil.getParentOfType(getElementInCaret(editor, file), StringValue.class, false);
		return element != null && TaraUtil.isNativeValue(element);
	}

	@NotNull
	@Override
	public String getText() {
		return "Edit native code";
	}

	@NotNull
	@Override
	public String getFamilyName() {
		return getText();
	}

	@Override
	public boolean startInWriteAction() {
		return false;
	}

	@Override
	public Icon getIcon(@IconFlags int flags) {
		return TaraIcons.ICON_13;
	}

	@Nullable
	private PsiElement getElementInCaret(Editor editor, PsiFile file) {
		int offset = editor.getCaretModel().getOffset();
		return file.findElementAt(offset);
	}
}
