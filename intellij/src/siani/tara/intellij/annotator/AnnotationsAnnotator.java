package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.highlighting.TaraSyntaxHighlighter;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.ConceptReference;
import siani.tara.intellij.lang.psi.TaraBoxFile;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.lang.Annotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class AnnotationsAnnotator extends TaraAnnotator {

	HashMap<String, List<PsiElement>> annotations;

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof siani.tara.intellij.lang.psi.Annotations) {
			annotations = new HashMap<>();
			checkAnnotations((siani.tara.intellij.lang.psi.Annotations) element);
			checkDuplicates();
		}
	}

	private void checkAnnotations(@NotNull siani.tara.intellij.lang.psi.Annotations element) {
		if (element.getParent() instanceof ConceptReference)
			for (PsiElement psiElement : getConceptReferenceIncorrectAnnotations(element)) {
				com.intellij.lang.annotation.Annotation errorAnnotation = holder.createErrorAnnotation(psiElement.getNode(), MessageProvider.message("annotation.concept.key.error.message"));
				errorAnnotation.setTextAttributes(TaraSyntaxHighlighter.ANNOTATION_ERROR);
			}
		else {
			Concept contextOf = TaraPsiImplUtil.getConceptContainerOf(element);
			for (PsiElement psiElement : getConceptIncorrectAnnotations(contextOf, contextOf.getAnnotations())) {
				com.intellij.lang.annotation.Annotation errorAnnotation = holder.createErrorAnnotation(psiElement.getNode(), MessageProvider.message("annotation.concept.key.error.message"));
				errorAnnotation.setTextAttributes(TaraSyntaxHighlighter.ANNOTATION_ERROR);
			}
		}
	}

	private Collection<PsiElement> getConceptReferenceIncorrectAnnotations(siani.tara.intellij.lang.psi.Annotations element) {
		return checkAnnotationList(element.getAnnotations(), Annotations.HAS_ANNOTATIONS);
	}

	private void checkDuplicates() {
		for (String annotation : annotations.keySet()) {
			List<PsiElement> annotationList = annotations.get(annotation);
			if (annotationList.size() > 1)
				for (PsiElement element : annotationList) {
					com.intellij.lang.annotation.Annotation errorAnnotation = holder.createErrorAnnotation(element.getNode(), MessageProvider.message("duplicated.annotation.key.error.message"));
					errorAnnotation.setTextAttributes(TaraSyntaxHighlighter.ANNOTATION_ERROR);
				}
		}
	}

	private PsiElement[] getConceptIncorrectAnnotations(Concept concept, PsiElement[] annotationList) {
		List<PsiElement> incorrects;
		if (isPrimeConcept(concept))
			incorrects = checkAnnotationList(annotationList, Annotations.PRIME_ANNOTATIONS);
		else if ((concept != null) && concept.isSub())
			incorrects = checkAnnotationList(annotationList, Annotations.SUB_ANNOTATIONS);
		else
			incorrects = checkAnnotationList(annotationList, Annotations.COMPONENT_ANNOTATIONS);
		return incorrects.toArray(new PsiElement[incorrects.size()]);
	}

	private boolean isPrimeConcept(Concept concept) {
		return (concept != null) && concept.getParent() instanceof TaraBoxFile;
	}


	private List<PsiElement> checkAnnotationList(PsiElement[] annotationList, Annotations.Annotation[] correctAnnotations) {
		List<PsiElement> incorrectAnnotations = new ArrayList<>();
		for (PsiElement annotation : annotationList) {
			count(annotation);
			if (!isIn(correctAnnotations, annotation.getText()))
				incorrectAnnotations.add(annotation);
		}
		return incorrectAnnotations;
	}

	private void count(PsiElement annotation) {
		if (annotations.containsKey(annotation.getText()))
			annotations.get(annotation.getText()).add(annotation);
		else {
			ArrayList<PsiElement> value = new ArrayList<>();
			value.add(annotation);
			annotations.put(annotation.getText(), value);
		}
	}

	private boolean isIn(Annotations.Annotation[] correctAnnotation, String text) {
		for (Annotations.Annotation s : correctAnnotation)
			if (s.getName().equals(text)) return true;
		return false;
	}
}
