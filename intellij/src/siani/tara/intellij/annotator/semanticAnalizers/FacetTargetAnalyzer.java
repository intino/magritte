package siani.tara.intellij.annotator.semanticAnalizers;

import com.intellij.psi.PsiElement;
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraFacetTarget;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;

import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;

public class FacetTargetAnalyzer extends TaraAnalyzer {

	private final TaraFacetTarget target;

	public FacetTargetAnalyzer(TaraFacetTarget target) {
		this.target = target;
	}

	@Override
	public void analyze() {
		if (isInnerFacetTarget()) {
			PsiElement parentFacet = target.getParent().getParent();
			Concept parent = TaraPsiImplUtil.getConceptContainerOf(ReferenceManager.resolve(((TaraFacetTarget) parentFacet).getIdentifierReference()));
			Concept child = TaraPsiImplUtil.getConceptContainerOf(ReferenceManager.resolve(target.getIdentifierReference()));
			if (!isChild(child, parent)) results.put(target.getIdentifierReference(), new AnnotateAndFix(ERROR,
				MessageProvider.message("no.child.concept", child.getName(), parent.getName())));
		}
		Concept parent = TaraPsiImplUtil.getConceptContainerOf(target);
		if (parent != null && !parent.isSub() && !parent.isFacet())
			results.put(target, new AnnotateAndFix(ERROR, MessageProvider.message("target.in.nofacet.concept")));
		else if (parent != null && parent.isSub() && !TaraPsiImplUtil.getParentOf(parent).isFacet())
			results.put(target, new AnnotateAndFix(ERROR, MessageProvider.message("target.in.nofacet.concept")));
	}

	private boolean isInnerFacetTarget() {
		return target.getParent().getParent() instanceof TaraFacetTarget;
	}

	private boolean isChild(Concept child, Concept parent) {
		for (Concept concept : parent.getSubConcepts())
			if (concept.getQualifiedName().equals(child.getQualifiedName()))
				return true;
		return false;
	}

}
