package io.intino.tara.plugin.refactoring;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import io.intino.tara.plugin.lang.psi.TaraIdentifier;
import io.intino.tara.lang.model.Node;

public class TaraRefactoringSupportProvider extends RefactoringSupportProvider {
	@Override
	public boolean isMemberInplaceRenameAvailable(@NotNull PsiElement element, PsiElement context) {
		return element instanceof TaraIdentifier;
	}

	@Override
	public boolean isSafeDeleteAvailable(@NotNull PsiElement element) {
		return element instanceof Node;
	}
}