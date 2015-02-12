package siani.tara.intellij.lang.psi.resolve;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.VariantsManager;

import java.util.*;

public class TaraInternalReferenceSolver extends TaraReferenceSolver {


	public TaraInternalReferenceSolver(@NotNull PsiElement element, TextRange textRange) {
		super(element, textRange);
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
		final Set<Concept> variants = new LinkedHashSet();
		if (isConceptReference()) {
			VariantsManager manager = new VariantsManager(variants, myElement);
			manager.resolveVariants();
			if (isParameterReference()) filterParamsByContext();
		}
		return fillVariants(variants);
	}


	@Override
	protected PsiElement doMultiResolve() {
		return myElement;
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

	private boolean isConceptReference() {
		return myElement.getParent() instanceof IdentifierReference || myElement.getParent() instanceof HeaderReference;
	}

	public boolean isParameterReference() {
		return myElement.getParent().getParent() instanceof TaraParameterValue;
	}

	private void filterParamsByContext() {//TODO filter in implicit parameters mode
		Concept concept = TaraPsiImplUtil.getConceptContainerOf(myElement);
	}
}