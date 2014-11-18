package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.TaraBundle;
import siani.tara.intellij.annotator.fix.RemoveConceptLinkFix;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraConceptReference;
import siani.tara.intellij.lang.psi.TaraIdentifierReference;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;

import java.awt.*;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class ConceptReferenceAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof TaraConceptReference) {
			TaraConceptReference reference = (TaraConceptReference) element;
			if (isDuplicated(reference))
				annotateAndFix(element, new RemoveConceptLinkFix(reference), TaraBundle.message("duplicate.link.concept.error.message"));
			TaraIdentifierReference identifierReference = reference.getIdentifierReference();
			if (reference.isAggregated() && destinyIsComponent(reference))
				annotateAndFix(element, new RemoveConceptLinkFix(reference), TaraBundle.message("duplicate.link.concept.error.message"));
			addReferenceHighlight(holder, identifierReference);
		}
	}

	private boolean destinyIsComponent(TaraConceptReference reference) {
		Concept concept = TaraPsiImplUtil.getConceptContainerOf(ReferenceManager.resolve(reference.getIdentifierReference()));
		return concept != null && concept.isComponent();
	}

	@SuppressWarnings("deprecation")
	private void addReferenceHighlight(AnnotationHolder holder, TaraIdentifierReference identifierReference) {
		if (identifierReference != null) {
			TextAttributesKey component = createTextAttributesKey("CONCEPT_REFERENCE",
				new TextAttributes(null, null, null, null, Font.ITALIC));
			holder.createInfoAnnotation(identifierReference, "reference").setTextAttributes(component);
		}
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
