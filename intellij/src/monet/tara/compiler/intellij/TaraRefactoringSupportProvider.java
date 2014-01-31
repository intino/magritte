package monet.tara.compiler.intellij;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import monet.tara.compiler.intellij.psi.TaraConcept;
import org.jetbrains.annotations.NotNull;

public class TaraRefactoringSupportProvider extends RefactoringSupportProvider {
	@Override
	public boolean isMemberInplaceRenameAvailable(PsiElement element, PsiElement context) {
		return element instanceof TaraConcept;
	}

	@Override
	public boolean isSafeDeleteAvailable(@NotNull PsiElement element) {
		return element instanceof TaraConcept;
	}
}