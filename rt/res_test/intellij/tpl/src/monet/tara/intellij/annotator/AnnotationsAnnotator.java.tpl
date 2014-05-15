package monet.::projectName::.intellij.annotator;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import monet.::projectName::.intellij.::projectProperName::Bundle;
import monet.::projectName::.intellij.highlighting.::projectProperName::SyntaxHighlighter;
import monet.::projectName::.intellij.lang.parser.::projectProperName::Annotation;
import monet.::projectName::.intellij.lang.psi.*;
import monet.::projectName::.intellij.lang.psi.impl.*;
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
			else if (::projectProperName::Types.CODE.equals(annotation.getNode().getElementType()))
				isCodeableDefinition(::projectProperName::PsiImplUtil.getContextOf(annotation));

		}
		return incorrectAnnotations;
	}

	private boolean isCodeableDefinition(Definition definition) {
		::projectProperName::MetaIdentifier[] metaIds = com.intellij.psi.util.PsiTreeUtil.getChildrenOfType(definition.getSignature(), ::projectProperName::MetaIdentifier.class);
		::projectProperName::MetaIdentifier metaId;
		if (metaIds == null) metaId = searchBaseDefinition(definition);
		else metaId = metaIds[0];

		return conceptHasCode(metaId);
	}

	private ::projectProperName::MetaIdentifier searchBaseDefinition(Definition definition) {
		Definition baseDefinition = ::projectProperName::Util.getBaseDefinitionOf(definition);
		return com.intellij.psi.util.PsiTreeUtil.getChildrenOfType(baseDefinition.getSignature(), ::projectProperName::MetaIdentifier.class)[0];
	}

	private boolean conceptHasCode(::projectProperName::MetaIdentifier metaId) {
		monet.tara.lang.ASTWrapper heritage = monet.::projectName::.intellij.lang.::projectProperName::Language.getHeritage();
		monet.tara.lang.ASTNode node = heritage.getNodeNameLookUpTable().get(metaId.getText()).get(0);
		if (node.hasCode()) return true;
		monet.tara.lang.ASTNode ancestry;
		while ((ancestry = heritage.searchAncestry(node)) != null) if (ancestry.hasCode()) return true;
		return false;
	}

	private void count(PsiElement annotation) {
		if (duplicates.containsKey(annotation.getText())) {
			duplicates.get(annotation.getText()).add(annotation);
		} else {
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
