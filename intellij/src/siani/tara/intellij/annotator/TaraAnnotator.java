package siani.tara.intellij.annotator;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

public abstract class TaraAnnotator implements Annotator {

	protected AnnotationHolder holder = null;

	protected Annotation annotateAndFix(PsiElement element, IntentionAction fix, String message) {
		Annotation errorAnnotation = holder.createErrorAnnotation(element.getNode(), message);
		errorAnnotation.registerFix(fix);
		return errorAnnotation;
	}

	protected Node findNode(Concept concept, Model model) {
		return model.searchNode(concept.getMetaQualifiedName());
	}

	protected Node findMetaNode(Concept concept) {
		if (concept == null) return null;
		Model metaModel = TaraLanguage.getMetaModel(concept.getFile());
		return metaModel != null ? metaModel.searchNode(concept.getMetaQualifiedName()) : null;
	}

	protected boolean isLinkConcept(Concept concept) {
		return concept.getName() == null && concept.getBody() == null;
	}
}