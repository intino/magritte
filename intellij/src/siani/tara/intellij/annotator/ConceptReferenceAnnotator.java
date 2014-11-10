package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.TaraBundle;
import siani.tara.intellij.annotator.fix.RemoveConceptLinkFix;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraConceptReference;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;

public class ConceptReferenceAnnotator extends TaraAnnotator {
	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof TaraConceptReference && isDuplicated((TaraConceptReference) element))
			annotateAndFix(element, new RemoveConceptLinkFix((TaraConceptReference) element), TaraBundle.message("duplicate.link.concept.error.message"));
	}

	private boolean isDuplicated(TaraConceptReference reference) {
		int count = 0;
		Concept contextOf = TaraPsiImplUtil.getConceptContainerOf(reference);
		if (contextOf == null) return false;
		TaraConceptReference[] links = contextOf.getConceptLinks();
		for (TaraConceptReference link : links) {
			if (reference.getIdentifierReference() == null || link.getIdentifierReference() == null) continue;
			if (reference.getIdentifierReference().getText().equals(link.getIdentifierReference().getText()) && areIncompatibles(reference, link))
				count++;
		}
		return count > 1;
	}

	private boolean areIncompatibles(TaraConceptReference reference, TaraConceptReference link) {
		return reference.isAggregated() == link.isAggregated();
	}
}
