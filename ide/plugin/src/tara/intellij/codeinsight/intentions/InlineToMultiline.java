package tara.intellij.codeinsight.intentions;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.lang.model.Variable;

public class InlineToMultiline extends PsiElementBaseIntentionAction implements IntentionAction {
	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		final Expression expression = TaraPsiImplUtil.getContainerByType(element, Expression.class);
		if (expression == null) return;
		final String indent = getIndent(expression) + "\t\t";
		final PsiElement newExpression = TaraElementFactory.getInstance(project).createMultiLineExpression(expression.getValue(), indent, indent, "---");
		final Valued valued = TaraPsiImplUtil.getContainerByType(expression, Valued.class);
		if (valued == null) return;
		expression.getParent().getPrevSibling().delete();
		expression.getParent().getPrevSibling().delete();
		expression.getParent().getPrevSibling().delete();
		expression.delete();
		valued.addAfter(newExpression.getParent(), valued.getLastChild());
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		final Expression expression = TaraPsiImplUtil.getContainerByType(element, Expression.class);
		final Valued valued = TaraPsiImplUtil.getContainerByType(element, Valued.class);
		return valued != null && expression != null &&
			!expression.isMultiLine() && valued.getValue() != null && valued.getValue().getExpressionList().size() == 1 &&
			(valued instanceof Variable || valued instanceof TaraVarInit);
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
		return "To multi-line";
	}

	private static String getIndent(PsiElement element) {
		PsiElement child = element.getPrevSibling();
		while (child != null) {
			if (child.getNode().getElementType().equals(TaraTypes.NEW_LINE_INDENT) || child.getNode().getElementType().equals(TaraTypes.NEWLINE))
				return child.getText().substring(1);
			child = child.getNextSibling();
		}
		return "";
	}
}
