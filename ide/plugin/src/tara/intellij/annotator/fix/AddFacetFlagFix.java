package tara.intellij.annotator.fix;

import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.Flags;
import tara.intellij.lang.psi.TaraElementFactory;
import tara.intellij.lang.psi.TaraNode;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.lang.model.Tag;

public class AddFacetFlagFix extends PsiElementBaseIntentionAction {

	private static final String FACET = Tag.FACET.name().toLowerCase();
	private final TaraNode node;

	public AddFacetFlagFix(PsiElement element) {
		this.node = (TaraNode) TaraPsiImplUtil.getContainerNodeOf(element);
	}

	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		if (node == null) return;
		Flags flags = node.getSignature().getFlags();
		TaraElementFactory factory = TaraElementFactory.getInstance(node.getProject());
		if (flags != null) flags.add(factory.createFlag(FACET));
		else node.addAfter(factory.createFlags(FACET), node.getSignature());
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement element) {
		return element.getContainingFile().isValid();
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
		return "Add facet flag";
	}

}