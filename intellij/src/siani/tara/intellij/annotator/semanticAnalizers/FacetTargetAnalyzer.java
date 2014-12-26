package siani.tara.intellij.annotator.semanticAnalizers;

import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraFacetTarget;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.lang.Annotation;
import siani.tara.lang.Node;

import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;

public class FacetTargetAnalyzer extends TaraAnalyzer {

	private final TaraFacetTarget target;

	public FacetTargetAnalyzer(TaraFacetTarget target) {
		this.target = target;
	}

	@Override
	public void analyze() {
		Concept parent = TaraPsiImplUtil.getConceptContainerOf(target);
		Node metaConcept = getMetaConcept(parent);
		if (metaConcept == null) return;
		if (parent != null && !parent.isSub() && !parent.isFacet() && !metaConcept.getObject().is(Annotation.META_FACET))
			results.put(target, new AnnotateAndFix(ERROR, MessageProvider.message("target.in.nofacet.concept")));
		if (TaraPsiImplUtil.getContextOf(target) instanceof TaraFacetTarget)
			results.put(target, new AnnotateAndFix(ERROR, MessageProvider.message("target.not.allowed")));
	}
}
