package tara.intellij.codeinsight.intentions;

import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.Signature;
import tara.intellij.lang.psi.TaraSignature;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.lang.model.Node;
import tara.lang.model.NodeRoot;

public class ExtractToRoot extends PsiElementBaseIntentionAction {
	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {

	}


	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		Node node = TaraPsiImplUtil.getContainerByType(element, Node.class);
		return element.isWritable() && isInSignature(element) && node != null && !(node.container() instanceof NodeRoot);
	}

	private boolean isInSignature(PsiElement element) {
		return element instanceof Signature || TaraPsiImplUtil.getContainerByType(element, TaraSignature.class) != null;
	}

	@NotNull
	public String getText() {
		return "Extract element to root TODO";
	}

	@Nls
	@NotNull
	@Override
	public String getFamilyName() {
		return getText();
	}
}
