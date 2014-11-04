package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.annotator.fix.AddAnnotationFix;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.lang.Annotations;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

public class AggregableConceptAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (!Concept.class.isInstance(element)) return;
		this.holder = holder;
		Concept concept = (Concept) element;
		Model model = TaraLanguage.getMetaModel(concept.getFile());
		if (model == null || model.isSystem()) return;
		Node node = findNode(concept, model);
		if (node.getObject().is(Annotations.Annotation.AGGREGABLE) && !concept.isAggregable())
			annotateAndFix(element, new AddAnnotationFix(concept, Annotations.Annotation.AGGREGABLE), "This concept should be aggregable");
	}
}
