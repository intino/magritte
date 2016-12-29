package io.intino.tara.plugin.annotator.fix;

import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import io.intino.tara.plugin.lang.psi.impl.TaraPsiImplUtil;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import io.intino.tara.plugin.lang.psi.TaraNode;

public class ToLowerCaseInstanceFix extends PsiElementBaseIntentionAction {

	private final TaraNode node;

	public ToLowerCaseInstanceFix(PsiElement element) {
		this.node = TaraPsiImplUtil.getContainerByType(element, TaraNode.class);
	}

	@Override
	public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
		if (node != null) node.name(firstLowerCase(node.name()));
	}

	public static String firstLowerCase(String value) {
		return value.substring(0, 1).toLowerCase() + value.substring(1);
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
		return "To lowercase";
	}

}
