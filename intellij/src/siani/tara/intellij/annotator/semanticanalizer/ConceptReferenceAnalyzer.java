package siani.tara.intellij.annotator.semanticanalizer;

import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix;
import siani.tara.intellij.annotator.fix.RemoveConceptLinkFix;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Body;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.FacetApply;
import siani.tara.intellij.lang.psi.TaraConceptReference;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.lang.LinkNode;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;
import static siani.tara.intellij.lang.psi.impl.TaraUtil.findNode;
import static siani.tara.lang.Annotation.ABSTRACT;

public class ConceptReferenceAnalyzer extends TaraAnalyzer {

	private TaraConceptReference reference;
	private Model model;

	public ConceptReferenceAnalyzer(TaraConceptReference reference) {
		this.reference = reference;
		model = TaraLanguage.getMetaModel(reference.getContainingFile());
	}

	@Override
	public void analyze() {
		if (isDuplicated())
			results.put(reference, createAnnotationAndFix(getMessageForDuplicated()));
		else if (reference.isAggregated() && destinyIsComponent(reference))
			results.put(reference, new AnnotateAndFix(ERROR, getMessageForAggregated(), new RemoveConceptLinkFix(reference)));
		else if (model != null && !existsConceptTypeInMetamodel())
			results.put(reference, new AnnotateAndFix(ERROR, MessageProvider.message("Unknown.concept")));
	}

	private AnnotateAndFix createAnnotationAndFix(String message) {
		return new AnnotateAndFix(ERROR, message, new RemoveConceptLinkFix(reference));
	}

	private String getMessageForDuplicated() {
		return MessageProvider.message("duplicate.link.concept");
	}

	private boolean isDuplicated() {
		int count = 0;
		Body body = TaraPsiImplUtil.getBodyContextOf(reference);
		if (body == null) return false;
		TaraConceptReference[] links = body.getConceptLinks();
		for (TaraConceptReference link : links) {
			if (reference.getIdentifierReference().getText().equals(link.getIdentifierReference().getText()) && areIncompatibles(reference, link))
				count++;
		}
		return count > 1;
	}

	private boolean existsConceptTypeInMetamodel() {
		Node node = findNode(reference, model);
		if (node == null || isAbstract(node)) return false;
		else if (!node.is(ABSTRACT)) return true;
		Concept container = TaraPsiImplUtil.getConceptContainerOf(reference);
		if (container == null) return false;
		Node containerNode = findNode(container, model);
		return containerNode != null && hasAny(containerNode, getFacets(container.getFacetApplies()));
	}

	private boolean hasAny(Node container, Collection<String> facets) {
		for (Node node : container.getInnerNodes())
			if (facets.contains(node.getName())) return true;
		return false;
	}


	private Collection<String> getFacets(FacetApply[] facetApplies) {
		List<String> facets = new ArrayList<>();
		for (FacetApply facetApply : facetApplies) facets.add(facetApply.getFacetName());
		return facets;
	}

	private boolean isAbstract(Node node) {
		if (node.is(LinkNode.class)) return ((LinkNode) node).getDestiny().is(ABSTRACT);
		return node.is(ABSTRACT);
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
