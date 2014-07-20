package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraFacetApply;
import siani.tara.intellij.lang.psi.TaraFile;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.lang.Model;
import siani.tara.lang.Node;
import siani.tara.lang.Variable;

import java.util.List;
import java.util.Map;

public class FacetApplyAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (!(element instanceof TaraFacetApply)) return;
		TaraFacetApply facetApply = (TaraFacetApply) element;
		Model model = TaraLanguage.getMetaModel(((TaraFile) facetApply.getContainingFile()).getParentModel());
		if (model == null) return;
		Concept concept = TaraPsiImplUtil.getContextOf(facetApply);
		Node node = findNode(concept, model);
		if (node == null) return;
		if (!isAllowedFacet(node, facetApply.getMetaIdentifierList().get(0).getText()))
			annotateErroneousFacet(facetApply, holder);
	}

	private boolean isAllowedFacet(Node node, String name) {
		for (Map.Entry<String, List<Variable>> entry : node.getObject().getAllowedFacets().entrySet())
			if (entry.getKey().substring(entry.getKey().lastIndexOf(".") + 1).equals(name)) return true;
		return false;
	}

	private void annotateErroneousFacet(PsiElement element, AnnotationHolder holder) {
		holder.createErrorAnnotation(element, "That facet is not allowed in this context");
	}
}
