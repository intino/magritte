package siani.tara.intellij.findUsages;

import com.intellij.find.findUsages.FindUsagesHandler;
import com.intellij.find.findUsages.FindUsagesHandlerFactory;
import com.intellij.psi.PsiElement;
import siani.tara.intellij.lang.psi.TaraConcept;
import siani.tara.intellij.lang.psi.TaraFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TaraFindUsagesHandlerFactory extends FindUsagesHandlerFactory {

	@Override
	public boolean canFindUsages(@NotNull PsiElement element) {
		return element instanceof TaraConcept;
	}

	@Nullable
	@Override
	public FindUsagesHandler createFindUsagesHandler(@NotNull PsiElement element, boolean forHighlightUsages) {
		return element instanceof TaraFile ? new TaraConceptFindUsagesHandler(((TaraFile) element).getConcept()) : null;
	}
}
