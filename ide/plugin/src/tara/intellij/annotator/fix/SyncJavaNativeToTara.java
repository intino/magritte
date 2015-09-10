package tara.intellij.annotator.fix;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.TaraExpression;
import tara.intellij.lang.psi.TaraVariable;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.language.model.Variable;

public class SyncJavaNativeToTara implements IntentionAction {
	private final PsiClass psiClass;

	public SyncJavaNativeToTara(PsiClass psiClass) {
		this.psiClass = psiClass;
	}

	@Nls
	@NotNull
	@Override
	public String getText() {
		return "Sync native with tara code";
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
		final PsiElement element = ReferenceManager.resolveNativeImplementation(psiClass);
		if (element == null) return;
		final TaraVariable variable = (TaraVariable) findVariableScope(element);
		if (variable.getValue() == null || psiClass.getAllMethods().length == 0 || psiClass.getAllMethods()[0].getBody() == null)
			return;
		final TaraExpression taraExpression = variable.getValue().getExpressionList().get(0);
		String body = psiClass.getAllMethods()[0].getBody().getText();
		body = body.substring(1, body.length() - 1).trim();
		if (body.startsWith("return ")) body.substring("return ".length());
		taraExpression.updateText(body);
	}

	private Variable findVariableScope(PsiElement element) {
		return TaraPsiImplUtil.getParentVariableOf(element);
	}

	@Override
	public boolean startInWriteAction() {
		return true;
	}
}
