package monet.tara.intellij.annotator;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import monet.tara.intellij.TaraBundle;
import monet.tara.intellij.highlighting.TaraSyntaxHighlighter;
import monet.tara.intellij.lang.parser.TaraAnnotation;
import monet.tara.intellij.lang.psi.Annotations;
import monet.tara.intellij.lang.psi.Concept;
import monet.tara.intellij.lang.psi.TaraFile;
import monet.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import monet.tara.lang.Extensible;
import monet.tara.lang.Modifiable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnnotationsAnnotator extends TaraAnnotator {

	HashMap<String, List<PsiElement>> duplicates;

	@Override
	@Extensible(tag = "AnnotationsAnnotator.annotate")
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof Annotations) {
			duplicates = new HashMap<>();
			checkAnnotations((Annotations) element);
			checkDuplicates();
		}
	}

	private void checkDuplicates() {
		for (String annotation : duplicates.keySet()) {
			List<PsiElement> annotationList = duplicates.get(annotation);
			if (annotationList.size() > 1)
				for (PsiElement element : annotationList) {
					Annotation errorAnnotation = holder.createErrorAnnotation(element.getNode(), TaraBundle.message("annotation.concept.key.error.message"));
					errorAnnotation.setTextAttributes(TaraSyntaxHighlighter.ANNOTATION_ERROR);
				}
		}
	}

	private PsiElement is(Annotations element, Class clazz) {
		for (PsiElement psi : element.getAnnotations())
			if (clazz.isInstance(psi)) return psi;
		return null;
	}

	private void checkAnnotations(Annotations element) {
		for (PsiElement psiElement : checkCorrectAnnotation(TaraPsiImplUtil.getContextOf(element), element.getAnnotations())) {
			Annotation errorAnnotation = holder.createErrorAnnotation(psiElement.getNode(), TaraBundle.message("annotation.concept.key.error.message"));
			errorAnnotation.setTextAttributes(TaraSyntaxHighlighter.ANNOTATION_ERROR);
		}
	}

	private PsiElement[] checkCorrectAnnotation(Concept concept, PsiElement[] annotations) {
		List<PsiElement> incorrectAnnotations;
		if (isRootConcept(concept))
			incorrectAnnotations = checkAnnotationList(annotations, TaraAnnotation.ROOT_ANNOTATIONS);
		else if ((concept != null) && concept.isCase())
			incorrectAnnotations = checkAnnotationList(annotations, TaraAnnotation.CASE_ANNOTATIONS);
		else
			incorrectAnnotations = checkAnnotationList(annotations, TaraAnnotation.CHILD_ANNOTATIONS);
		return incorrectAnnotations.toArray(new PsiElement[incorrectAnnotations.size()]);
	}

	private boolean isRootConcept(Concept concept) {
		return (concept != null) && concept.getParent() instanceof TaraFile;
	}


	@Modifiable(tag = "AnnotationsAnnotator.checkAnnotationList")
	private List<PsiElement> checkAnnotationList(PsiElement[] annotations, String[] correctAnnotation) {
		List<PsiElement> incorrectAnnotations = new ArrayList<>();
		for (PsiElement annotation : annotations) {
			if (annotation instanceof PsiWhiteSpace) continue;
			count(annotation);
			if (!isIn(correctAnnotation, annotation.getText()))
				incorrectAnnotations.add(annotation);
		}
		return incorrectAnnotations;
	}

	private void count(PsiElement annotation) {
		if (duplicates.containsKey(annotation.getText()))
			duplicates.get(annotation.getText()).add(annotation);
		else {
			ArrayList<PsiElement> value = new ArrayList<>();
			value.add(annotation);
			duplicates.put(annotation.getText(), value);
		}
	}


	private boolean isIn(String[] correctAnnotation, String text) {
		for (String s : correctAnnotation)
			if (s.equals(text.split(":")[0])) return true;
		return false;
	}
}
