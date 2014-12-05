package siani.tara.intellij.annotator.semanticAnalizers;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.annotator.TaraAnnotator;
import siani.tara.intellij.lang.psi.Annotations;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.ConceptReference;
import siani.tara.intellij.lang.psi.TaraBoxFile;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;

import java.util.*;

import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;
import static siani.tara.lang.Annotations.Annotation.NAMED;
import static siani.tara.lang.Annotations.Annotation.PROPERTY;

public class AnnotationsAnalyzer extends TaraAnalyzer {

	private final Annotations taraAnnotations;

	public AnnotationsAnalyzer(Annotations annotations) {
		this.taraAnnotations = annotations;
	}

	public void analyze() {
		if (hasErrors = analyzeAnnotations(taraAnnotations)) return;
		if (hasErrors = analyzeConsistency(taraAnnotations.getAnnotations())) return;
		hasErrors = analyzeDuplicates();
	}

	private boolean analyzeAnnotations(@NotNull Annotations element) {
		if (element.getParent() instanceof ConceptReference)
			for (PsiElement psiElement : getConceptReferenceIncorrectAnnotations(element))
				this.results.put(psiElement,
					new TaraAnnotator.AnnotateAndFix(ERROR, MessageProvider.message("annotation.concept")));
		else {
			Concept contextOf = TaraPsiImplUtil.getConceptContainerOf(element);
			for (PsiElement psiElement : getConceptIncorrectAnnotations(contextOf, contextOf.getAnnotations()))
				this.results.put(psiElement,
					new TaraAnnotator.AnnotateAndFix(ERROR, MessageProvider.message("annotation.concept")));
		}
		return this.results.isEmpty();
	}

	private Collection<PsiElement> getConceptReferenceIncorrectAnnotations(Annotations element) {
		return analyzeAnnotationList(element.getAnnotations(), siani.tara.lang.Annotations.HAS_ANNOTATIONS);
	}

	private PsiElement[] getConceptIncorrectAnnotations(Concept concept, PsiElement[] annotationList) {
		List<PsiElement> incorrects;
		if (isPrimeConcept(concept))
			incorrects = analyzeAnnotationList(annotationList, siani.tara.lang.Annotations.PRIME_ANNOTATIONS);
		else if ((concept != null) && concept.isSub())
			incorrects = analyzeAnnotationList(annotationList, siani.tara.lang.Annotations.SUB_ANNOTATIONS);
		else
			incorrects = analyzeAnnotationList(annotationList, siani.tara.lang.Annotations.COMPONENT_ANNOTATIONS);
		return incorrects.toArray(new PsiElement[incorrects.size()]);
	}

	private boolean isPrimeConcept(Concept concept) {
		return (concept != null) && concept.getParent() instanceof TaraBoxFile;
	}


	private List<PsiElement> analyzeAnnotationList(PsiElement[] annotationList, siani.tara.lang.Annotations.Annotation[] correctAnnotations) {
		List<PsiElement> incorrectAnnotations = new ArrayList<>();
		for (PsiElement annotation : annotationList) {
			if (!isIn(correctAnnotations, annotation.getText()))
				incorrectAnnotations.add(annotation);
		}
		return incorrectAnnotations;
	}

	private boolean isIn(siani.tara.lang.Annotations.Annotation[] correctAnnotation, String text) {
		for (siani.tara.lang.Annotations.Annotation s : correctAnnotation)
			if (s.getName().equals(text)) return true;
		return false;
	}

	public boolean analyzeConsistency(PsiElement[] annotations) {
		List<String> names = annotationsToString(annotations);
		if ((names.contains(PROPERTY.getName())) && names.contains(NAMED.getName())) {
			this.results.put(taraAnnotations,
				new TaraAnnotator.AnnotateAndFix(ERROR, MessageProvider.message("inconsistent.annotations")));
			return false;
		}
		return true;
	}

	private boolean analyzeDuplicates() {
		PsiElement[] psiAnnotations = taraAnnotations.getAnnotations();
		Map<String, List<PsiElement>> annotationMap = count(psiAnnotations);
		for (String annotation : annotationMap.keySet())
			if (annotationMap.get(annotation).size() > 1) {
				for (PsiElement element : annotationMap.get(annotation))
					this.results.put(element, new TaraAnnotator.AnnotateAndFix(ERROR, MessageProvider.message("duplicated.annotation.key.error.message")));
				return true;
			}
		return false;
	}

	private Map<String, List<PsiElement>> count(PsiElement[] psiAnnotations) {
		Map<String, List<PsiElement>> map = new HashMap();
		for (PsiElement psiAnnotation : psiAnnotations)
			count(map, psiAnnotation);
		return map;
	}

	private void count(Map<String, List<PsiElement>> map, PsiElement annotation) {
		if (map.containsKey(annotation.getText()))
			map.get(annotation.getText()).add(annotation);
		else {
			ArrayList<PsiElement> value = new ArrayList<>();
			value.add(annotation);
			map.put(annotation.getText(), value);
		}
	}

	private List<String> annotationsToString(PsiElement[] annotations) {
		List<String> names = new ArrayList<>();
		for (PsiElement annotation : annotations)
			names.add(annotation.getText());
		return names;
	}
}
