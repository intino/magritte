package monet.::projectName::.intellij.annotator;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import monet.::projectName::.intellij.::projectProperName::Bundle;
import monet.::projectName::.intellij.highlighting.::projectProperName::SyntaxHighlighter;
import monet.::projectName::.intellij.lang.parser.::projectProperName::Annotation;
import monet.::projectName::.intellij.lang.psi.Annotations;
import monet.::projectName::.intellij.lang.psi.Definition;
import monet.::projectName::.intellij.lang.psi.::projectProperName::File;
import monet.::projectName::.intellij.lang.psi.impl.::projectProperName::PsiImplUtil;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnnotationsAnnotator extends ::projectProperName::Annotator {

	HashMap<String, List<PsiElement>> duplicates;

	\@Override
	public void annotate(\@NotNull PsiElement element, \@NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof Annotations) {
			duplicates = new HashMap<>();
			checkAnnotations((Annotations) element);
			checkDuplicates();
		}
	}

	private void checkDuplicates() {
		for (String annotation \: duplicates.keySet()) {
			List<PsiElement> annotationList = duplicates.get(annotation);
			if (annotationList.size() > 1)
				for (PsiElement element \: annotationList) {
					Annotation errorAnnotation = holder.createErrorAnnotation(element.getNode(), ::projectProperName::Bundle.message("annotation.definition.key.error.message"));
					errorAnnotation.setTextAttributes(::projectProperName::SyntaxHighlighter.ANNOTATION_ERROR);
				}
		}
	}

	private PsiElement is(Annotations element, Class clazz) {
		for (PsiElement psi \: element.getAnnotations())
			if (clazz.isInstance(psi)) return psi;
		return null;
	}

	private void checkAnnotations(Annotations element) {
		for (PsiElement psiElement \: checkCorrectAnnotation(::projectProperName::PsiImplUtil.getContextOf(element), element.getAnnotations())) {
			Annotation errorAnnotation = holder.createErrorAnnotation(psiElement.getNode(), ::projectProperName::Bundle.message("annotation.definition.key.error.message"));
			errorAnnotation.setTextAttributes(::projectProperName::SyntaxHighlighter.ANNOTATION_ERROR);
		}
	}

	private PsiElement[] checkCorrectAnnotation(Definition definition, PsiElement[] annotations) {
		List<PsiElement> incorrectAnnotations;
		if (isRootDefinition(definition))
			incorrectAnnotations = checkAnnotationList(annotations, ::projectProperName::Annotation.ROOT_ANNOTATIONS);
		else if ((definition != null) && definition.isCase())
			incorrectAnnotations = checkAnnotationList(annotations, ::projectProperName::Annotation.CASE_ANNOTATIONS);
		else
			incorrectAnnotations = checkAnnotationList(annotations, ::projectProperName::Annotation.CHILD_ANNOTATIONS);
		return incorrectAnnotations.toArray(new PsiElement[incorrectAnnotations.size()]);
	}

	private boolean isRootDefinition(Definition definition) {
		return (definition != null) && definition.getParent() instanceof ::projectProperName::File;
	}


	private List<PsiElement> checkAnnotationList(PsiElement[] annotations, String[] correctAnnotation) {
		List<PsiElement> incorrectAnnotations = new ArrayList<>();
		for (PsiElement annotation \: annotations) {
			if (annotation instanceof PsiWhiteSpace) continue;
			count(annotation);
			if (!isIn(correctAnnotation, annotation.getText()))
				incorrectAnnotations.add(annotation);
		}
		return incorrectAnnotations;
	}

	private monet.::projectName::.intellij.lang.psi.::projectProperName::MetaIdentifier searchBaseDefinition(Definition definition) {
		Definition baseDefinition = monet.::projectName::.intellij.lang.psi.impl.::projectProperName::Util.getBaseDefinitionOf(definition);
		return com.intellij.psi.util.PsiTreeUtil.getChildrenOfType(baseDefinition.getSignature(), monet.::projectName::.intellij.lang.psi.::projectProperName::MetaIdentifier.class)[0];
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
		for (String s \: correctAnnotation)
			if (s.equals(text.split("\:")[0])) return true;
		return false;
	}
}
