package siani.tara.intellij.findusage;

import com.intellij.find.findUsages.FindUsagesHandler;
import com.intellij.find.findUsages.FindUsagesHandlerFactory;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.Identifier;
import siani.tara.intellij.lang.psi.TaraBoxFile;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;

public class TaraFindUsagesHandlerFactory extends FindUsagesHandlerFactory {

	@Override
	public boolean canFindUsages(@NotNull PsiElement element) {
		return element instanceof Concept || element instanceof Identifier;
	}

	@Nullable
	@Override
	public FindUsagesHandler createFindUsagesHandler(@NotNull PsiElement element, boolean forHighlightUsages) {
		if (element instanceof Concept) return new TaraConceptFindUsagesHandler((Concept) element);
		if (element instanceof TaraBoxFile) return new TaraFileFindUsagesHandler((TaraBoxFile) element);
		else {
			Concept contextOf = TaraPsiImplUtil.getConceptContainerOf(element);
			if (contextOf == null) return null;
			return new TaraConceptFindUsagesHandler(contextOf);
		}
	}
}
