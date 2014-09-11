package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.TaraBundle;
import siani.tara.intellij.highlighting.TaraSyntaxHighlighter;
import siani.tara.lang.TaraAnnotations;
import siani.tara.intellij.lang.psi.Annotations;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraBoxFile;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnnotationsAnnotator extends TaraAnnotator {

	HashMap<String, List<PsiElement>> annotations;

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof Annotations) {
			annotations = new HashMap<>();
			checkAnnotations((Annotations) element);
			checkDuplicates();
		}
	}

	private void checkAnnotations(@NotNull Annotations annotationsElement) {
		Concept contextOf = TaraPsiImplUtil.getContextOf(annotationsElement);
		for (PsiElement psiElement : getIncorrectAnnotations(contextOf, contextOf.getAnnotations())) {
			Annotation errorAnnotation = holder.createErrorAnnotation(psiElement.getNode(), TaraBundle.message("annotation.concept.key.error.message"));
			errorAnnotation.setTextAttributes(TaraSyntaxHighlighter.ANNOTATION_ERROR);
		}
	}

	private void checkDuplicates() {
		for (String annotation : annotations.keySet()) {
			List<PsiElement> annotationList = annotations.get(annotation);
			if (annotationList.size() > 1)
				for (PsiElement element : annotationList) {
					Annotation errorAnnotation = holder.createErrorAnnotation(element.getNode(), TaraBundle.message("duplicated.annotation.key.error.message"));
					errorAnnotation.setTextAttributes(TaraSyntaxHighlighter.ANNOTATION_ERROR);
				}
		}
	}

	private PsiElement[] getIncorrectAnnotations(Concept concept, PsiElement[] annotationList) {
		List<PsiElement> incorrects;
		if (isPrimeConcept(concept))
			incorrects = checkAnnotationList(annotationList, TaraAnnotations.PRIME_ANNOTATIONS);
		else if ((concept != null) && concept.isSub())
			incorrects = checkAnnotationList(annotationList, TaraAnnotations.SUB_ANNOTATIONS);
		else
			incorrects = checkAnnotationList(annotationList, TaraAnnotations.COMPONENT_ANNOTATIONS);
		return incorrects.toArray(new PsiElement[incorrects.size()]);
	}

	private boolean isPrimeConcept(Concept concept) {
		return (concept != null) && concept.getParent() instanceof TaraBoxFile;
	}


	private List<PsiElement> checkAnnotationList(PsiElement[] annotationList, String[] correctAnnotations) {
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


	private boolean isIn(String[] correctAnnotation, String text) {
		for (String s : correctAnnotation)
			if (s.equals(text.split(":")[0])) return true;
		return false;
	}
}
