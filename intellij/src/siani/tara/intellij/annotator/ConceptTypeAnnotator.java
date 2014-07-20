package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.TaraBundle;
import siani.tara.intellij.highlighting.TaraSyntaxHighlighter;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.MetaIdentifier;
import siani.tara.intellij.lang.psi.TaraFile;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.lang.Model;

public class ConceptTypeAnnotator extends TaraAnnotator {


	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof MetaIdentifier) {
			Concept concept = TaraPsiImplUtil.getContextOf(element);
			if (concept == null) return;
			if ("Concept".equals(element.getText())) return;
			Model model = TaraLanguage.getMetaModel(((TaraFile) element.getContainingFile()).getParentModel());
			if (model != null) {
				if (findNode(concept, model) == null) {
					Annotation errorAnnotation = holder.createErrorAnnotation
						(concept, TaraBundle.message("Unknown.concept.key.error.message"));
					errorAnnotation.setTextAttributes(TaraSyntaxHighlighter.ANNOTATION_ERROR);
				}
			}
		}
	}


}
