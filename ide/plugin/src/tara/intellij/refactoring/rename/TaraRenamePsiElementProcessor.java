package tara.intellij.refactoring.rename;

import com.intellij.psi.PsiElement;
import com.intellij.refactoring.rename.RenamePsiElementProcessor;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.TaraModel;

public class TaraRenamePsiElementProcessor extends RenamePsiElementProcessor {

	@Override
	public boolean canProcessElement(@NotNull PsiElement element) {
		return element instanceof TaraModel;
	}
}
