package monet.tara.intellij.annotator;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import monet.tara.intellij.TaraBundle;
import monet.tara.intellij.highlighting.TaraSyntaxHighlighter;
import monet.tara.intellij.metamodel.parser.TaraAnnotation;
import monet.tara.intellij.metamodel.psi.Annotations;
import monet.tara.intellij.metamodel.psi.Concept;
import monet.tara.intellij.metamodel.psi.TaraFile;
import monet.tara.intellij.metamodel.psi.impl.TaraPsiImplUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AnnotationsAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof Annotations)
			checkAnnotations((Annotations) element);
	}

	private void checkAnnotations(Annotations element) {
		for (PsiElement psiElement : checkCorrectAnnotation(TaraPsiImplUtil.getContextOf(element), element.getAnnotations())) {
			Annotation errorAnnotation = holder.createErrorAnnotation(psiElement.getNode(), TaraBundle.message("annotation.concept.key.error.message"));
			errorAnnotation.setTextAttributes(TaraSyntaxHighlighter.ANNOTATION_ERROR);
		}
	}

	private PsiElement[] checkCorrectAnnotation(Concept concept, PsiElement[] annotations) {
		List<PsiElement> incorrectAnnotations;
		if ((concept != null) && concept.getParent() instanceof TaraFile)
			incorrectAnnotations = checkAnnotationList(annotations, TaraAnnotation.ROOT_ANNOTATIONS);
		else if ((concept != null) && concept.isMorph())
			incorrectAnnotations = checkAnnotationList(annotations, TaraAnnotation.MORPH_ANNOTATIONS);
		else
			incorrectAnnotations = checkAnnotationList(annotations, TaraAnnotation.CHILD_ANNOTATIONS);
		return incorrectAnnotations.toArray(new PsiElement[incorrectAnnotations.size()]);
	}

	private List<PsiElement> checkAnnotationList(PsiElement[] annotations, String[] correctAnnotation) {
		List<PsiElement> incorrectAnnotations = new ArrayList<>();
		for (PsiElement annotation : annotations)
			if (!isIn(correctAnnotation, annotation.getText()))
				incorrectAnnotations.add(annotation);
		return incorrectAnnotations;
	}

	private boolean isIn(String[] correctAnnotation, String text) {
		for (String s : correctAnnotation)
			if (s.equals(text.split(":")[0])) return true;
		return false;
	}
}
