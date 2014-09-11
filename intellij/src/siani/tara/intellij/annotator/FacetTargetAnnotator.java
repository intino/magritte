package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.TaraBundle;
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
		if (facetTarget.getParent().getParent() instanceof TaraFacetTarget) {
			Concept parent = TaraPsiImplUtil.getContextOf(
				ReferenceManager.resolve(((TaraFacetTarget) facetTarget.getParent().getParent()).getIdentifierReference()));
			Concept child = TaraPsiImplUtil.getContextOf(ReferenceManager.resolve(facetTarget.getIdentifierReference()));
			if (!isChild(child, parent)) {
				holder.createErrorAnnotation(facetTarget.getIdentifierReference().getNode(),
					TaraBundle.message("no.child.concept.error.message", child.getName(), parent.getName()));
			}
		}
	}

	private boolean isChild(Concept child, Concept parent) {
		for (Concept concept : parent.getConceptChildren())
			if (concept.getQualifiedName().equals(child.getQualifiedName()))
				return true;
		return false;
	}

}
