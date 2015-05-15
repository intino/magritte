package siani.tara.intellij.codeinsight.intentions;

import com.intellij.codeInsight.daemon.impl.AnnotationHolderImpl;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInsight.intention.LowPriorityAction;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationSession;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.StringValue;
import siani.tara.intellij.lang.psi.VarInit;
import siani.tara.intellij.lang.psi.impl.TaraUtil;

import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getParentByType;


public class TaraQuickEditAction implements IntentionAction, LowPriorityAction {

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
		if (!file.getLanguage().is(TaraLanguage.INSTANCE)) return false;
		StringValue element = PsiTreeUtil.getParentOfType(getElementInCaret(editor, file), StringValue.class, false);
		return element != null && TaraUtil.isNativeValue(element);
	}

	private boolean hasErrors(PsiFile file) {
		AnnotationSession session = new AnnotationSession(file);
		for (Annotation annotation : new AnnotationHolderImpl(session))
			if (annotation.getSeverity().compareTo(HighlightSeverity.ERROR) == 0) return true;
		return false;
	}

	private VarInit getVarInitOfValue(StringValue stringValue) {
		PsiElement parentByType = getParentByType(stringValue, VarInit.class);
		return parentByType instanceof VarInit ? (VarInit) parentByType : null;
	}

	private Node getContainerNode(VarInit varInit) {
		return (Node) getParentByType(varInit, Node.class);
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
