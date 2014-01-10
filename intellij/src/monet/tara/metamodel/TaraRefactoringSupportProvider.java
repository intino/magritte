package monet.tara.metamodel;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import monet.tara.metamodel.psi.TaraConceptDefinition;
import org.jetbrains.annotations.NotNull;

public class TaraRefactoringSupportProvider extends RefactoringSupportProvider {
	@Override
	public boolean isMemberInplaceRenameAvailable(PsiElement element, PsiElement context) {
		return element instanceof TaraConceptDefinition;
	}

	@Override
	public boolean isSafeDeleteAvailable(@NotNull PsiElement element) {
		return element instanceof TaraConceptDefinition;
	}
}