package siani.tara.intellij.codeinsight.intentions;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInsight.intention.LowPriorityAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.StringValue;
import siani.tara.intellij.lang.psi.impl.TaraUtil;


public class TaraQuickEditActionOld implements IntentionAction, LowPriorityAction {

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
	public void invoke(@NotNull final Project project, final Editor editor, PsiFile file) throws IncorrectOperationException {
		StringValue stringValue = PsiTreeUtil.getParentOfType(getElementInCaret(editor, file), StringValue.class, false);
		TaraQuickEditHandler handler = new TaraQuickEditHandler(project, file, stringValue, editor);
		handler.navigate();
	}

	@Nullable
	private PsiElement getElementInCaret(Editor editor, PsiFile file) {
		int offset = editor.getCaretModel().getOffset();
		return file.findElementAt(offset);
	}

	@Override
	public boolean startInWriteAction() {
		return false;
	}
}
