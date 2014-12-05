package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraFacetTarget;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;

public class FacetTargetAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (!TaraFacetTarget.class.isInstance(element)) return;
		TaraFacetTarget facetTarget = (TaraFacetTarget) element;
		if (isInnerFacetTarget(facetTarget)) {
			PsiElement parentFacet = facetTarget.getParent().getParent();
			Concept parent = TaraPsiImplUtil.getConceptContainerOf(ReferenceManager.resolve(((TaraFacetTarget) parentFacet).getIdentifierReference()));
			Concept child = TaraPsiImplUtil.getConceptContainerOf(ReferenceManager.resolve(facetTarget.getIdentifierReference()));
			if (!isChild(child, parent)) holder.createErrorAnnotation(facetTarget.getIdentifierReference().getNode(),
				MessageProvider.message("no.child.concept", child.getName(), parent.getName()));
		}
		Concept parent = TaraPsiImplUtil.getConceptContainerOf(facetTarget);
		if (parent != null && !parent.isSub() && !parent.isFacet())
			holder.createErrorAnnotation(facetTarget.getNode(),
				MessageProvider.message("target.in.nofacet.concept"));
		else if (parent != null && parent.isSub() && !TaraPsiImplUtil.getParentOf(parent).isFacet())
			holder.createErrorAnnotation(facetTarget.getNode(),
				MessageProvider.message("target.in.nofacet.concept"));
	}

	private boolean isInnerFacetTarget(TaraFacetTarget facetTarget) {
		return facetTarget.getParent().getParent() instanceof TaraFacetTarget;
	}

	private boolean isChild(Concept child, Concept parent) {
		for (Concept concept : parent.getSubConcepts())
			if (concept.getQualifiedName().equals(child.getQualifiedName()))
				return true;
		return false;
	}

}
