package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.TaraBundle;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.lang.IntentionNode;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

public class FacetAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (!(element instanceof Concept)) return;
		Concept concept = (((Concept) element).isCase()) ? searchParent((Concept) element) : (Concept) element;
		if (concept== null) return;
		Model model = TaraLanguage.getMetaModel(concept.getFile());
		if (model == null) return;
		Node node = findNode(concept, model);
		if (node == null || !node.is(IntentionNode.class)) return;
		if (((Concept) element).getConceptLinks().length > 0)
			holder.createErrorAnnotation(element.getNode(), TaraBundle.message("facet.with.children.error.message"));
		Concept[] conceptChildren = ((Concept) element).getConceptChildren();
		if (conceptChildren.length > 0 && !allAreCases(conceptChildren))
			holder.createErrorAnnotation(element.getNode(), TaraBundle.message("facet.with.children.error.message"));
	}

	private boolean allAreCases(Concept[] conceptChildren) {
		for (Concept conceptChild : conceptChildren) if (!conceptChild.isCase()) return false;
		return true;
	}

	private Concept searchParent(Concept concept) {
		Concept aConcept = concept;
		while (aConcept != null && aConcept.isCase())
			aConcept = TaraPsiImplUtil.getContextOf(concept);
		return aConcept;
	}
}
