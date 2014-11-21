package siani.tara.intellij.lang.psi.resolve;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.VariantsManager;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.HeaderReference;
import siani.tara.intellij.lang.psi.Identifier;
import siani.tara.intellij.lang.psi.IdentifierReference;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;

import java.util.*;

public class TaraInternalReferenceSolver extends TaraReferenceSolver {


	public TaraInternalReferenceSolver(@NotNull PsiElement element, TextRange textRange) {
		super(element, textRange);
	}

	@Override
	protected PsiElement doMultiResolve() {
		return ReferenceManager.resolve((Identifier) myElement);
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
		final Set<Concept> variants = new HashSet();
		if (myElement.getParent() instanceof IdentifierReference || myElement.getParent() instanceof HeaderReference)
			new VariantsManager(variants, myElement).resolveVariants();
		return fillVariants(variants);
	}

	public Object[] fillVariants(Collection<Concept> variants) {
		List<LookupElement> lookupElements = new ArrayList<>();
		for (final Concept concept : variants) {
			if (concept == null || concept.getName() == null || concept.getName().length() == 0) continue;
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