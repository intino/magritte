package tara.intellij.codeinsight.intentions;

import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;

public class MultilineToInline extends PsiElementBaseIntentionAction {
	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		final Expression expression = TaraPsiImplUtil.getContainerByType(element, Expression.class);
		final PsiElement newExpression = TaraElementFactory.getInstance(project).createExpression(expression.getValue());
		final Valued valued = TaraPsiImplUtil.getContainerByType(expression, Valued.class);
		if (valued == null) return;
		expression.delete();
		if (valued.getValue() != null) valued.getValue().getExpressionList().add((TaraExpression) newExpression);
		else {
			valued.addAfter(newExpression.getParent().getPrevSibling(), PsiTreeUtil.findChildOfType(valued, Identifier.class));
			valued.addAfter(newExpression.getParent().getPrevSibling().getPrevSibling(), PsiTreeUtil.findChildOfType(valued, Identifier.class));
			valued.addAfter(newExpression.getParent().getPrevSibling(), PsiTreeUtil.findChildOfType(valued, Identifier.class));
			valued.addAfter(newExpression.getParent(), PsiTreeUtil.findChildOfType(valued, Identifier.class).getNextSibling().getNextSibling().getNextSibling());
		}
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		final Expression expression = TaraPsiImplUtil.getContainerByType(element, Expression.class);
		return expression != null && expression.isMultiLine() && !expression.getValue().contains("\n");
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
		return "To inline";
	}
}
