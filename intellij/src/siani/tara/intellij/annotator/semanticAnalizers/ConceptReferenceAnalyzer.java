package siani.tara.intellij.annotator.semanticAnalizers;

import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix;
import siani.tara.intellij.annotator.fix.RemoveConceptLinkFix;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraConceptReference;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;

import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;

public class ConceptReferenceAnalyzer extends TaraAnalyzer {

	TaraConceptReference reference;


	public ConceptReferenceAnalyzer(TaraConceptReference reference) {
		this.reference = reference;
	}

	@Override
	public void analyze() {
		if (isDuplicated())
			results.put(reference, createAnnotationAndFix(getMessageForDuplicated()));
		else if (reference.isAggregated() && destinyIsComponent(reference))
			results.put(reference, new AnnotateAndFix(ERROR, getMessageForAggregated(), new RemoveConceptLinkFix(reference)));
	}

	private AnnotateAndFix createAnnotationAndFix(String message) {
		return new AnnotateAndFix(ERROR, message, new RemoveConceptLinkFix(reference));
	}

	private String getMessageForDuplicated() {
		return MessageProvider.message("duplicate.link.concept");
	}

	private boolean isDuplicated() {
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

	private boolean destinyIsComponent(TaraConceptReference reference) {
		Concept concept = TaraPsiImplUtil.getConceptContainerOf(ReferenceManager.resolve(reference.getIdentifierReference()));
		return concept != null && concept.isComponent();
	}


	public String getMessageForAggregated() {
		return MessageProvider.message("aggregated.linkedto.component.concept");
	}
}
