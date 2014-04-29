package monet.::projectName::.intellij.annotator;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import monet.::projectName::.intellij.::projectProperName::Bundle;
import monet.::projectName::.intellij.highlighting.::projectProperName::SyntaxHighlighter;
import monet.::projectName::.intellij.metamodel.parser.::projectProperName::Annotation;
import monet.::projectName::.intellij.metamodel.psi.Annotations;
import monet.::projectName::.intellij.metamodel.psi.Definition;
import monet.::projectName::.intellij.metamodel.psi.::projectProperName::File;
import monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::PsiImplUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AnnotationsAnnotator extends ::projectProperName::Annotator {

	\@Override
	public void annotate(\@NotNull PsiElement element, \@NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof Annotations)
			checkAnnotations((Annotations) element);
	}

	private void checkAnnotations(Annotations element) {
		for (PsiElement psiElement \: checkCorrectAnnotation(::projectProperName::PsiImplUtil.getContextOf(element), element.getAnnotations())) {
			Annotation errorAnnotation = holder.createErrorAnnotation(psiElement.getNode(), ::projectProperName::Bundle.message("annotation.definition.key.error.message"));
			errorAnnotation.setTextAttributes(::projectProperName::SyntaxHighlighter.ANNOTATION_ERROR);
		}
	}

	private PsiElement[] checkCorrectAnnotation(Definition definition, PsiElement[] annotations) {
		List<PsiElement> incorrectAnnotations;
		if ((definition != null) && definition.getParent() instanceof ::projectProperName::File)
			incorrectAnnotations = checkAnnotationList(annotations, ::projectProperName::Annotation.ROOT_ANNOTATIONS);
		else if ((definition != null) && definition.isMorph())
			incorrectAnnotations = checkAnnotationList(annotations, ::projectProperName::Annotation.MORPH_ANNOTATIONS);
		else
			incorrectAnnotations = checkAnnotationList(annotations, ::projectProperName::Annotation.CHILD_ANNOTATIONS);
		return incorrectAnnotations.toArray(new PsiElement[incorrectAnnotations.size()]);
	}

	private List<PsiElement> checkAnnotationList(PsiElement[] annotations, String[] correctAnnotation) {
		List<PsiElement> incorrectAnnotations = new ArrayList<>();
		for (PsiElement annotation \: annotations)
			if (!isIn(correctAnnotation, annotation.getText()))
				incorrectAnnotations.add(annotation);
		return incorrectAnnotations;
	}

	private boolean isIn(String[] correctAnnotation, String text) {
		for (String s \: correctAnnotation)
			if (s.equals(text.split("\:")[0])) return true;
		return false;
	}
}
