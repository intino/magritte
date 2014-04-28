package monet.tara.intellij;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import monet.tara.intellij.metamodel.TaraIcons;
import monet.tara.intellij.metamodel.psi.Concept;
import monet.tara.intellij.metamodel.psi.Identifier;
import monet.tara.intellij.metamodel.psi.TaraIdentifier;
import monet.tara.intellij.metamodel.psi.impl.TaraPsiImplUtil;
import monet.tara.intellij.metamodel.psi.impl.TaraUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaraReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

	public TaraReference(@NotNull PsiElement element, TextRange textRange) {
		super(element, textRange);
	}

	@NotNull
	@Override
	public ResolveResult[] multiResolve(boolean incompleteCode) {
		List<ResolveResult> results = new ArrayList<>();
		PsiElement element = TaraUtil.resolveReference(myElement);
		if (element != null)
			results.add(new PsiElementResolveResult(element));
		return results.toArray(new ResolveResult[results.size()]);
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
		List<Concept> concepts = new ArrayList<>();
		if (isReferenceToConcept())
			getVariants((TaraIdentifier) myElement, concepts);
		else if (myElement.getPrevSibling().getPrevSibling() instanceof Identifier)
			getChildrenVariants((TaraIdentifier) myElement.getPrevSibling().getPrevSibling(), concepts);
		return fillVariants(concepts);
	}

	private boolean isReferenceToConcept() {
		return myElement.getPrevSibling() == null;
	}

	private Object[] fillVariants(List<Concept> concepts) {
		List<LookupElement> variants = new ArrayList<>();
		for (final Concept concept : concepts)
			if (concept.getName() != null && concept.getName().length() > 0)
				variants.add(LookupElementBuilder.create(concept).withIcon(TaraIcons.ICON_13).withTypeText(getFileName(concept)));
		return variants.toArray();
	}

	private void getVariants(TaraIdentifier parent, List<Concept> concepts) {
		concepts.addAll(TaraUtil.getRootConcepts(parent.getProject()));
		Concept context = TaraPsiImplUtil.getContextOf(parent);
		concepts.addAll(TaraUtil.getSiblings(context));
	}

	private void getChildrenVariants(TaraIdentifier parent, List<Concept> concepts) {
		Concept concept = TaraUtil.resolveConceptReference(parent);
		Collections.addAll(concepts, TaraUtil.getChildrenOf(concept));
	}

	private String getFileName(Concept concept) {
		return concept.getContainingFile().getName().split("\\.")[0];
	}
}