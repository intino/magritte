package io.intino.tara.plugin.lang.psi.resolve;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class TaraReferenceSolver extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

	public TaraReferenceSolver(@NotNull PsiElement element, TextRange textRange) {
		super(element, textRange);
	}

	@NotNull
	@Override
	public ResolveResult[] multiResolve(boolean incompleteCode) {
		List<ResolveResult> results = new ArrayList<>();
		List<PsiElement> resolve = doMultiResolve();
		if (resolve.isEmpty() || resolve.get(0) == null) return ResolveResult.EMPTY_ARRAY;
		results.addAll(resolve.stream().map(PsiElementResolveResult::new).collect(Collectors.toList()));
		return results.toArray(new ResolveResult[results.size()]);
	}

	protected abstract List<PsiElement> doMultiResolve();

	@Override
	public PsiElement bindToElement(@NotNull PsiElement element) throws IncorrectOperationException {
		return element;
	}
}