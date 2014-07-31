package siani.tara.intellij.findUsages;

import com.intellij.find.findUsages.FindUsagesHandler;
import com.intellij.find.findUsages.FindUsagesHandlerFactory;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.Identifier;
import siani.tara.intellij.lang.psi.TaraConcept;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;

public class TaraFindUsagesHandlerFactory extends FindUsagesHandlerFactory {

	@Override
	public boolean canFindUsages(@NotNull PsiElement element) {
		return element instanceof TaraConcept || element instanceof Identifier;
	}

	@Nullable
	@Override
	public FindUsagesHandler createFindUsagesHandler(@NotNull PsiElement element, boolean forHighlightUsages) {
		return element instanceof Concept ? new TaraConceptFindUsagesHandler((Concept) element)
			: new TaraConceptFindUsagesHandler(TaraPsiImplUtil.getContextOf(element));
	}
}
