package siani.tara.intellij.lang.psi.resolve;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.VariantsManager;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.Identifier;
import siani.tara.intellij.lang.psi.IdentifierReference;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;

import java.util.*;

public class TaraParameterReferenceSolver extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

	public TaraParameterReferenceSolver(PsiElement element, TextRange range) {
		super(element, range);
	}

	@NotNull
	@Override
	public ResolveResult[] multiResolve(boolean incompleteCode) {
		List<ResolveResult> results = new ArrayList<>();
		PsiElement reference = myElement.getFirstChild();
		if (!IdentifierReference.class.isInstance(reference)) return ResolveResult.EMPTY_ARRAY;
		PsiElement element = ReferenceManager.resolve((Identifier) reference.getLastChild(), false);
		if (element != null) results.add(new PsiElementResolveResult(element));
		return results.toArray(new ResolveResult[results.size()]);
	}

	@Nullable
	@Override
	public PsiElement resolve() {
		if (!IdentifierReference.class.isInstance(myElement.getFirstChild())) return null;
		ResolveResult[] resolveResults = multiResolve(false);
		return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
	}

	@NotNull
	@Override
	public Object[] getVariants() {
		Set<Concept> variants = new HashSet();
		PsiElement reference = myElement.getFirstChild();
		if (reference instanceof IdentifierReference)
			new VariantsManager(variants, reference.getLastChild()).resolveVariants();
		return fillVariants(variants);
	}

	public Object[] fillVariants(Collection<Concept> variants) {
		List<LookupElement> lookupElements = new ArrayList<>();
		for (final Concept concept : variants) {
			if (concept == null) continue;
			if (concept.getName() == null || concept.getName().length() == 0) continue;
			lookupElements.add(LookupElementBuilder.create(concept.getIdentifierNode()).
				withIcon(TaraIcons.getIcon(TaraIcons.ICON_13)).withTypeText(getFileName(concept)));
		}
		return lookupElements.toArray();
	}

	private String getFileName(PsiElement concept) {
		String name = concept.getContainingFile().getName();
		return name.substring(0, name.lastIndexOf("."));
	}
}