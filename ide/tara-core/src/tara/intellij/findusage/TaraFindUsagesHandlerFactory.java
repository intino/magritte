package tara.intellij.findusage;

import com.intellij.find.findUsages.FindUsagesHandler;
import com.intellij.find.findUsages.FindUsagesHandlerFactory;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.Identifier;
import tara.intellij.lang.psi.Signature;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.lang.model.Node;

public class TaraFindUsagesHandlerFactory extends FindUsagesHandlerFactory {

	@Override
	public boolean canFindUsages(@NotNull PsiElement element) {
		return element instanceof Node || element instanceof Identifier;
	}

	@Nullable
	@Override
	public FindUsagesHandler createFindUsagesHandler(@NotNull PsiElement element, boolean forHighlightUsages) {
		if (element instanceof TaraModel) return new TaraFileFindUsagesHandler((TaraModel) element);
		if (element instanceof Identifier && element.getParent() instanceof Signature)
			return new TaraNodeFindUsagesHandler(TaraPsiImplUtil.getContainerNodeOf(element));
		return null;
	}
}
