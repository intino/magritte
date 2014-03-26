package monet.::projectName::.intellij;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import monet.::projectName::.intellij.codeinspection.fix.RemoveAttributeFix;
import monet.::projectName::.intellij.codeinspection.fix.RemoveDefinitionFix;
import monet.::projectName::.intellij.metamodel.parser.Annotation;
import monet.::projectName::.intellij.metamodel.psi.*;
import monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::PsiImplUtil;
import monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::Util;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ::projectProperName::Annotator implements Annotator {


	AnnotationHolder holder = null;

	\@Override
	public void annotate(\@NotNull final PsiElement element, \@NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof Definition)
			checkDuplicated((Definition) element);
		else if (element instanceof ::projectProperName::Attribute)
			checkDuplicated((Attribute) element);
		else if (element instanceof Identifier)
			checkWellReferenced((Identifier) element);
		else if (element instanceof ::projectProperName::Morph)
			checkMorph((::projectProperName::Morph) element);
		else if (element instanceof ::projectProperName::Polymorphic)
			checkPolymorphic((::projectProperName::Polymorphic) element);
		else if (element instanceof Annotations)
			checkAnnotations((Annotations) element);
	}

	private void checkAnnotations(Annotations element) {
		PsiElement[] psiElements;
		if (element.getParent() instanceof ::projectProperName::DefinitionInjection)
			psiElements = checkDefinitionInjectionAnnotation(element.getAnnotations());
		else
			psiElements = checkCorrectAnnotation(::projectProperName::PsiImplUtil.getContextOf(element), element.getAnnotations());
		for (PsiElement psiElement \: psiElements)
			holder.createErrorAnnotation(psiElement.getNode(), ::projectProperName::Bundle.message("annotation.definition.key.error.message"));
	}

	private PsiElement[] checkDefinitionInjectionAnnotation(PsiElement[] annotations) {
		List<PsiElement> incorrectAnnotations;
		incorrectAnnotations = checkAnnotationList(annotations, Annotation.CHILD_ANNOTATIONS);
		return incorrectAnnotations.toArray(new PsiElement[incorrectAnnotations.size()]);
	}


	private PsiElement[] checkCorrectAnnotation(Definition definition, PsiElement[] annotations) {
		List<PsiElement> incorrectAnnotations;
		if ((definition != null) && definition.getParent() instanceof ::projectProperName::File)
			incorrectAnnotations = checkAnnotationList(annotations, Annotation.ROOT_ANNOTATIONS);
		else if ((definition != null) && definition.isMorph())
			incorrectAnnotations = checkAnnotationList(annotations, Annotation.MORPH_ANNOTATIONS);
		else
			incorrectAnnotations = checkAnnotationList(annotations, Annotation.CHILD_ANNOTATIONS);
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
			if (s.equals(text)) return true;
		return false;
	}

	private void checkPolymorphic(::projectProperName::Polymorphic element) {
		if (!hasMorphs(::projectProperName::PsiImplUtil.getContextOf(element)))
			holder.createErrorAnnotation(element.getNode(), ::projectProperName::Bundle.message("morph.non-existent.in.polymorphic.error.message"));
	}

	private boolean hasMorphs(Definition context) {
		if (context.getBody() != null)
			for (Definition definition \: context.getBody().getDefinitionList())
				if (definition.isMorph()) return true;
		return false;
	}

	private void checkMorph(::projectProperName::Morph element) {
		Definition definition = ::projectProperName::PsiImplUtil.getContextOf(::projectProperName::PsiImplUtil.getContextOf(element));
		if (!definition.isPolymorphic())
			holder.createErrorAnnotation(element.getNode(), ::projectProperName::Bundle.message("morph.not.in.polymorphic.error.message"));
	}

	private void checkWellReferenced(Identifier element) {
		Definition definition = ::projectProperName::Util.resolveReferences(element.getProject(), element);
		if (definition == null && element.getParent() instanceof ::projectProperName::ReferenceIdentifier)
			holder.createErrorAnnotation(element.getNode(), ::projectProperName::Bundle.message("reference.definition.key.error.message"));
	}

	private void checkDuplicated(Definition definition) {
		if (definition.getIdentifierNode() != null && ::projectProperName::Util.findDuplicates(definition.getProject(), definition) != 1)
			annotateAndFix(definition.getIdentifierNode(), new RemoveDefinitionFix(definition), ::projectProperName::Bundle.message("duplicate.definition.key.error.message"));
	}

	private void checkDuplicated(Attribute attribute) {
		if (::projectProperName::Util.findAttributeDuplicates(attribute).length != 1)
			annotateAndFix(attribute, new RemoveAttributeFix(attribute), ::projectProperName::Bundle.message("duplicate.attribute.key.error.message"));
	}

	private void annotateAndFix(PsiElement element, IntentionAction fix, String message) {
		holder.createErrorAnnotation(element.getNode(), message).registerFix(fix);
	}
}