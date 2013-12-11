package monet.tara.metamodel;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import monet.tara.metamodelplugin.psi.TaraConceptDefinition;

public class TaraRefactoringSupportProvider extends RefactoringSupportProvider {
	@Override
	public boolean isMemberInplaceRenameAvailable(PsiElement element, PsiElement context) {
		return element instanceof TaraConceptDefinition;
	}
}