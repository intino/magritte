package monet.::projectName::.intellij;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import monet.::projectName::.intellij.metamodel.psi.Definition;
import org.jetbrains.annotations.NotNull;

public class ::projectProperName::RefactoringSupportProvider extends RefactoringSupportProvider {
	\@Override
	public boolean isMemberInplaceRenameAvailable(\@NotNull PsiElement element, PsiElement context) {
		return element instanceof Definition;
	}

	\@Override
	public boolean isSafeDeleteAvailable(\@NotNull PsiElement element) {
		return element instanceof Definition;
	}
}