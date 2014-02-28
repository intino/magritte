package monet.tara.intellij;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import monet.tara.intellij.codeinspection.fix.RemoveAttributeFix;
import monet.tara.intellij.codeinspection.fix.RemoveConceptFix;
import monet.tara.intellij.metamodel.psi.*;
import monet.tara.intellij.metamodel.psi.impl.TaraAnnotationsImpl;
import monet.tara.intellij.metamodel.psi.impl.TaraPsiImplUtil;
import monet.tara.intellij.metamodel.psi.impl.TaraUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TaraAnnotator implements Annotator {

	AnnotationHolder holder = null;

	@Override
	public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof Concept)
			checkDuplicated((Concept) element);
		else if (element instanceof TaraAttribute)
			checkDuplicated((TaraAttribute) element);
		else if (element instanceof TaraIdentifier)
			checkExistence((TaraIdentifier) element);
		else if (element instanceof TaraMorph)
			checkMorph((TaraMorph) element);
		else if (element instanceof TaraPolymorphic)
			checkPolymorphic((TaraPolymorphic) element);
		else if (element instanceof TaraAnnotations)
			checkAnnotations((TaraAnnotations) element);
	}

	private void checkAnnotations(TaraAnnotations element) {
		PsiElement[] psiElements;
		if (element.getPrevSibling() instanceof TaraExtendedConcept)
			psiElements = checkConceptInjectionAnnotation(element);
		else
			psiElements = checkCorrectAnnotation(TaraPsiImplUtil.getContextOf(element), element.getAnnotations());
		for (PsiElement psiElement : psiElements)
			holder.createErrorAnnotation(psiElement.getNode(), TaraBundle.message("annotation.concept.key.error.message"));

	}

	private PsiElement[] checkConceptInjectionAnnotation(TaraAnnotations element) {

		return new PsiElement[0];
	}


	private PsiElement[] checkCorrectAnnotation(TaraConcept concept, PsiElement[] annotations) {
		List<PsiElement> incorrectAnnotations;
		if (concept.getParent() instanceof TaraFile)
			incorrectAnnotations = checkAnnotationList(annotations, TaraAnnotationsImpl.ROOT_ANNOTATIONS);
		else if (concept.isMorph())
			incorrectAnnotations = checkAnnotationList(annotations, TaraAnnotationsImpl.MORPH_ANNOTATIONS);
		else
			incorrectAnnotations = checkAnnotationList(annotations, TaraAnnotationsImpl.CHILD_ANNOTATIONS);
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
			if (s.equals(text)) return true;
		return false;
	}

	private void checkPolymorphic(TaraPolymorphic element) {
		if (!hasMorphs(TaraPsiImplUtil.getContextOf(element)))
			holder.createErrorAnnotation(element.getNode(), TaraBundle.message("morph.non-existent.in.polymorphic.error.message"));
	}

	private boolean hasMorphs(TaraConcept context) {
		if (context.getBody() != null)
			for (Concept concept : context.getBody().getConceptList())
				if (concept.isMorph()) return true;
		return false;
	}

	private void checkMorph(TaraMorph element) {
		Concept concept = TaraPsiImplUtil.getContextOf(TaraPsiImplUtil.getContextOf(element));
		if (!concept.isPolymorphic())
			holder.createErrorAnnotation(element.getNode(), TaraBundle.message("morph.not.in.polymorphic.error.message"));
	}

	private void checkExistence(TaraIdentifier element) {
		Concept concept = TaraUtil.resolveReferences(element.getProject(), element);
		if (concept == null && element.getParent() instanceof TaraExtendedConcept)
			holder.createErrorAnnotation(element.getNode(), TaraBundle.message("reference.concept.key.error.message"));
	}

	private void checkDuplicated(Concept concept) {
		if (concept.getIdentifierNode() != null)
			if (TaraUtil.findDuplicates(concept.getProject(), concept) != 1)
				annotateAndFix(concept.getIdentifierNode(), new RemoveConceptFix(concept), TaraBundle.message("duplicate.concept.key.error.message"));
	}

	private void checkDuplicated(TaraAttribute attribute) {
		if (TaraUtil.findAttributeDuplicates(attribute).length != 1)
			annotateAndFix(attribute, new RemoveAttributeFix(attribute), TaraBundle.message("duplicate.attribute.key.error.message"));
	}

	private void annotateAndFix(PsiElement element, IntentionAction fix, String message) {
		holder.createErrorAnnotation(element.getNode(), message).registerFix(fix);
	}
}