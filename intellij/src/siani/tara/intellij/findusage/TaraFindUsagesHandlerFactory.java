package siani.tara.intellij.findusage;

import com.intellij.find.findUsages.FindUsagesHandler;
import com.intellij.find.findUsages.FindUsagesHandlerFactory;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.Identifier;
import siani.tara.intellij.lang.psi.TaraModel;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;

public class TaraFindUsagesHandlerFactory extends FindUsagesHandlerFactory {

	@Override
	public boolean canFindUsages(@NotNull PsiElement element) {
		return element instanceof Node || element instanceof Identifier;
	}

	@Nullable
	@Override
	public FindUsagesHandler createFindUsagesHandler(@NotNull PsiElement element, boolean forHighlightUsages) {
		if (element instanceof Node) return new TaraNodeFindUsagesHandler((Node) element);
		if (element instanceof TaraModel) return new TaraFileFindUsagesHandler((TaraModel) element);
		else {
			Node contextOf = TaraPsiImplUtil.getContainerNodeOf(element);
			if (contextOf == null) return null;
			return new TaraNodeFindUsagesHandler(contextOf);
		}
	}
}
