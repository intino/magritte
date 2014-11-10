package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.TaraBundle;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.lang.Annotations;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.util.Collection;

public class FacetAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (!Concept.class.isInstance(element)) return;
		Concept conceptElement = (Concept) element;
		Concept concept = conceptElement.isSub() ? searchParent(conceptElement) : conceptElement;
		if (concept == null) return;
		Model model = TaraLanguage.getMetaModel(concept.getFile());
		if (model == null) return;
		Node node = findNode(concept, model);
		if (node == null || !node.getObject().is(Annotations.Annotation.INTENTION)) return;
		if (((Concept) element).getConceptLinks().length > 0)
			holder.createErrorAnnotation(element.getNode(), TaraBundle.message("facet.with.children.error.message"));
		Collection<Concept> conceptChildren = ((Concept) element).getInnerConcepts();
		if (!conceptChildren.isEmpty() && !allAreSub(conceptChildren))
			holder.createErrorAnnotation(element.getNode(), TaraBundle.message("facet.with.children.error.message"));
	}

	private boolean allAreSub(Collection<Concept> conceptChildren) {
		for (Concept conceptChild : conceptChildren) if (!conceptChild.isSub()) return false;
		return true;
	}

	private Concept searchParent(Concept concept) {
		Concept aConcept = concept;
		while (aConcept != null && aConcept.isSub())
			aConcept = TaraPsiImplUtil.getConceptContainerOf(aConcept);
		return aConcept;
	}
}
