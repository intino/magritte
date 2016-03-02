package tara.intellij.lang.psi.resolve;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class TaraTableReferenceSolver extends TaraReferenceSolver {
	public TaraTableReferenceSolver(@NotNull PsiElement element, TextRange range) {
		super(element, range);
	}

	@Override
	protected List<PsiElement> doMultiResolve() {
		return Collections.singletonList(ReferenceManager.resolveTable(myElement));
	}

	@Nullable
	@Override
	public PsiElement resolve() {
		ResolveResult[] resolveResults = multiResolve(false);
		return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
	}

	@NotNull
	@Override
	public Object[] getVariants() {
		return new Object[0];
	}
}
