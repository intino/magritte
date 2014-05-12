package monet.tara.intellij.annotator;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import monet.tara.intellij.TaraBundle;
import monet.tara.intellij.highlighting.TaraSyntaxHighlighter;
import monet.tara.intellij.metamodel.parser.TaraAnnotation;
import monet.tara.intellij.metamodel.psi.*;
import monet.tara.intellij.metamodel.psi.impl.TaraPsiImplUtil;
import monet.tara.intellij.metamodel.psi.impl.TaraUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AnnotationsAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof Annotations) {
			checkAnnotations((Annotations) element);
			PsiElement extension = is((Annotations) element, TaraExtension.class);
			if (extension != null) checkCorrectExtension(extension);
			PsiElement extensible = is((Annotations) element, TaraExtensible.class);
			if (extensible != null) checkEmptyExtensible(extensible);
			if (extensible != null && extension != null) {
				Annotation errorAnnotation = holder.createErrorAnnotation(element.getNode(), TaraBundle.message("annotation.extension.extensible.key.error.message"));
				errorAnnotation.setTextAttributes(TaraSyntaxHighlighter.ANNOTATION_ERROR);
			}
		}
	}

	private void checkCorrectExtension(PsiElement element) {
		Concept context = TaraPsiImplUtil.getContextOf(element);
		if (TaraPsiImplUtil.getContextOf(context) == null || !((TaraFile)element.getContainingFile()).getConcept().isExtensible()) {
			Annotation errorAnnotation = holder.createErrorAnnotation(element.getNode(), TaraBundle.message("annotation.extension.key.error.message"));
			errorAnnotation.setTextAttributes(TaraSyntaxHighlighter.ANNOTATION_ERROR);
		}
	}

	private void checkEmptyExtensible(PsiElement element) {
		Concept context = TaraPsiImplUtil.getContextOf(element);
		Concept[] children = TaraUtil.getChildrenOf(context);
		for (Concept child : children) if (child.isExtension()) return;
		holder.createWarningAnnotation(element.getNode(), TaraBundle.message("annotation.extensible.key.warning.message"));
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


	//%extension%
	private List<PsiElement> checkAnnotationList(PsiElement[] annotations, String[] correctAnnotation) {
		List<PsiElement> incorrectAnnotations = new ArrayList<>();
		for (PsiElement annotation : annotations) {
			if (annotation instanceof PsiWhiteSpace) continue;
			if (!isIn(correctAnnotation, annotation.getText()))
				incorrectAnnotations.add(annotation);
		}
		return incorrectAnnotations;
	}
	//end_extension

	private boolean isIn(String[] correctAnnotation, String text) {
		for (String s : correctAnnotation)
			if (s.equals(text.split(":")[0])) return true;
		return false;
	}
}
