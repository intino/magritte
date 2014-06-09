package siani.tara.intellij;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import siani.tara.intellij.lang.psi.Concept;
import org.jetbrains.annotations.NotNull;

public class TaraRefactoringSupportProvider extends RefactoringSupportProvider {
	@Override
	public boolean isMemberInplaceRenameAvailable(@NotNull PsiElement element, PsiElement context) {
		return element instanceof Concept;
	}

	@Override
	public boolean isSafeDeleteAvailable(@NotNull PsiElement element) {
		return element instanceof Concept;
	}
}