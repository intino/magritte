package siani.tara.intellij.lang.psi.resolve;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.psi.Identifier;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;

public class TaraExternalReferenceSolver extends TaraReferenceSolver {


	public TaraExternalReferenceSolver(@NotNull PsiElement element, TextRange textRange) {
		super(element, textRange);
	}


	@Override
	protected PsiElement doMultiResolve() {
		return ReferenceManager.resolveExternal((Identifier) myElement);
	}

	@Nullable
	@Override
	public PsiElement resolve() {
		return null;
	}

	@NotNull
	@Override
	public Object[] getVariants() {
		return EMPTY_ARRAY;
	}

}