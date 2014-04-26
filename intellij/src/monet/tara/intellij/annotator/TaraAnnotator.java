package monet.tara.intellij.annotator;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import monet.tara.intellij.TaraBundle;
import monet.tara.intellij.annotator.fix.RemoveAttributeFix;
import monet.tara.intellij.annotator.fix.RemoveConceptFix;
import monet.tara.intellij.highlighting.TaraSyntaxHighlighter;
import monet.tara.intellij.metamodel.parser.TaraAnnotation;
import monet.tara.intellij.metamodel.psi.*;
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
			checkDuplicated((Attribute) element);
		else if (element instanceof Identifier)
			checkWellReferenced((Identifier) element);
		else if (element instanceof TaraMorph)
			checkMorph((TaraMorph) element);
		else if (element instanceof TaraPolymorphic)
			checkPolymorphic((TaraPolymorphic) element);
		else if (element instanceof Annotations)
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

	private void checkPolymorphic(TaraPolymorphic element) {
		if (!hasMorphs(TaraPsiImplUtil.getContextOf(element))) {
			Annotation errorAnnotation = holder.createErrorAnnotation(element.getNode(),
				TaraBundle.message("morph.non-existent.in.polymorphic.error.message"));
		}
	}

	private boolean hasMorphs(Concept context) {
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

	private void checkWellReferenced(Identifier element) {
		Concept concept = (Concept) resolveReference(element.getProject(), element);
		if (concept == null && (element.getParent() instanceof TaraReferenceIdentifier || element.getParent() instanceof TaraHeaderReference)) {
			Annotation errorAnnotation = holder.createErrorAnnotation(element.getNode(), TaraBundle.message("reference.concept.key.error.message"));
			errorAnnotation.setTextAttributes(TaraSyntaxHighlighter.UNRESOLVED_ACCESS);
		}
	}

	private PsiElement resolveReference(Project project, PsiElement element) {
		PsiElement reference = null;
		if (element.getParent() instanceof ReferenceIdentifier)
			reference = TaraUtil.resolveConceptReference(project, element);
		else if (element.getParent() instanceof ImportIdentifier) {
			reference = TaraUtil.resolveHeaderReference(project, element);
		}
		return reference;
	}

	private void checkDuplicated(Concept concept) {
		if (concept.getIdentifierNode() != null && TaraUtil.findDuplicates(concept.getProject(), concept) != 1)
			annotateAndFix(concept.getIdentifierNode(), new RemoveConceptFix(concept), TaraBundle.message("duplicate.concept.key.error.message"));
	}

	private void checkDuplicated(Attribute attribute) {
		if (TaraUtil.findAttributeDuplicates(attribute).length != 1)
			annotateAndFix(attribute, new RemoveAttributeFix(attribute), TaraBundle.message("duplicate.attribute.key.error.message"));
	}

	private void annotateAndFix(PsiElement element, IntentionAction fix, String message) {
		holder.createErrorAnnotation(element.getNode(), message).registerFix(fix);
	}
}