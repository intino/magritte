package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.TaraBundle;
import siani.tara.intellij.highlighting.TaraSyntaxHighlighter;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.MetaIdentifier;
import siani.tara.intellij.lang.psi.TaraFile;
import siani.tara.intellij.lang.psi.TaraTypes;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.lang.Model;

public class ConceptTypeAnnotator extends TaraAnnotator {


	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof MetaIdentifier) {
			Concept concept = TaraPsiImplUtil.getContextOf(element);
			if (concept == null) return;
			Model model = TaraLanguage.getMetaModel(((TaraFile) element.getContainingFile()).getParentModel());
			if ("Concept".equals(element.getText()) | "Intention".equals(element.getText())) {
				if (concept.getName() == null)
					holder.createErrorAnnotation(concept, "Concept without name");
				IElementType elementType = concept.getPsiElement().getPrevSibling().getNode().getElementType();
				if (!(elementType.equals(TaraTypes.NEWLINE) || elementType.equals(TaraTypes.NEW_LINE_INDENT)))
					holder.createErrorAnnotation(concept, "Concept in bad position");
				if (model != null) {
					Annotation errorAnnotation = holder.createErrorAnnotation(concept, "Concept type not allowed here");
					errorAnnotation.setTextAttributes(TaraSyntaxHighlighter.UNRESOLVED_ACCESS);
				}
			}
			if (model == null) {
				if (!(element.getText().equals("Concept") || element.getText().equals("Intention")))
					holder.createErrorAnnotation(concept, "Concept type not allowed here");
			} else if (findNode(concept, model) == null) {
				Annotation errorAnnotation = holder.createErrorAnnotation
					(concept, TaraBundle.message("Unknown.concept.key.error.message"));
				errorAnnotation.setTextAttributes(TaraSyntaxHighlighter.UNRESOLVED_ACCESS);
			}
		}
	}
}



