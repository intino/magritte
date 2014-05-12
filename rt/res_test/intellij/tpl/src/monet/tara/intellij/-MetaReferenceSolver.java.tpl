package monet.::projectName::.intellij;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import monet.::projectName::.intellij.metamodel.psi.Definition;
import monet.::projectName::.intellij.metamodel.psi.MetaIdentifier;
import monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::PsiImplUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ::projectProperName::MetaReferenceSolver extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

	public ::projectProperName::MetaReferenceSolver(MetaIdentifier metaIdentifier, TextRange textRange) {
		super(metaIdentifier,textRange);
	}

	\@NotNull
	\@Override
	public ResolveResult[] multiResolve(boolean incompleteCode) {
		List<ResolveResult> results = new ArrayList<>();
		Definition contextOf = ::projectProperName::PsiImplUtil.getContextOf(myElement);
		if (contextOf != null)
			results.add(new PsiElementResolveResult(contextOf));
		return results.toArray(new ResolveResult[results.size()]);
	}

	\@Nullable
	\@Override
	public PsiElement resolve() {
		ResolveResult[] resolveResults = multiResolve(false);
		return resolveResults.length == 1 ? resolveResults[0].getElement() \: null;
	}

	\@NotNull
	\@Override
	public Object[] getVariants() {
		return new Object[0];
	}
}
