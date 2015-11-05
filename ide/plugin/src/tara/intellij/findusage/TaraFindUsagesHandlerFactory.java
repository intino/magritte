package tara.intellij.findusage;

import com.intellij.find.findUsages.FindUsagesHandler;
import com.intellij.find.findUsages.FindUsagesHandlerFactory;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.Identifier;
import tara.intellij.lang.psi.TaraModel;
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
		if (element instanceof Node) return new TaraNodeFindUsagesHandler((Node) element);
		return null;
	}
}
