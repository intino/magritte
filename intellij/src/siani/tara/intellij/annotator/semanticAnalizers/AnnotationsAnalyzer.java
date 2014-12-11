package siani.tara.intellij.annotator.semanticAnalizers;

import com.intellij.psi.PsiElement;
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.Annotation;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;

import java.util.*;

import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;
import static siani.tara.lang.Annotation.*;

public class AnnotationsAnalyzer extends TaraAnalyzer {

	private final Annotations annotations;
	private final List<? extends Annotation> annotationList;

	public AnnotationsAnalyzer(Annotations annotations) {
		this.annotations = annotations;
		this.annotationList = annotations.getNormalAnnotations();
	}

	public void analyze() {
		if (hasErrors = !analyzeAnnotations()) return;
		if (hasErrors = !analyzeConsistency()) return;
		hasErrors = analyzeDuplicates();
	}

	private boolean analyzeAnnotations() {
		if (annotations.getParent() instanceof ConceptReference)
			for (PsiElement psiElement : getConceptReferenceIncorrectAnnotations(annotations))
				this.results.put(psiElement,
					new AnnotateAndFix(ERROR, MessageProvider.message("annotation.concept")));
		else {
			Concept contextOf = TaraPsiImplUtil.getConceptContainerOf(annotations);
			if (contextOf == null) return true;
			for (PsiElement psiElement : getConceptIncorrectAnnotations(contextOf, contextOf.getNormalAnnotations()))
				this.results.put(psiElement,
					new AnnotateAndFix(ERROR, MessageProvider.message("annotation.concept")));
		}
		return this.results.isEmpty();
	}

	public boolean analyzeConsistency() {
		List<String> names = annotationsToString(annotationList);
		if ((names.contains(PROPERTY.getName()) || names.contains(FACET.getName())) && names.contains(NAMED.getName())) {
			this.results.put(this.annotations,
				new AnnotateAndFix(ERROR, MessageProvider.message("inconsistent.annotations")));
			return false;
		}
		return true;
	}

	private boolean analyzeDuplicates() {
		Map<String, List<PsiElement>> annotationMap = count(annotationList);
		for (String annotation : annotationMap.keySet())
			if (annotationMap.get(annotation).size() > 1) {
				for (PsiElement element : annotationMap.get(annotation))
					this.results.put(element, new AnnotateAndFix(ERROR, MessageProvider.message("duplicated.annotation.key.error.message")));
				return true;
			}
		return false;
	}

	private Collection<PsiElement> getConceptReferenceIncorrectAnnotations(Annotations element) {
		return analyzeAnnotationList(element.getNormalAnnotations(), siani.tara.lang.Annotations.HAS_ANNOTATIONS);
	}


	private PsiElement[] getConceptIncorrectAnnotations(Concept concept, Collection<? extends Annotation> annotationList) {
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

	private List<PsiElement> analyzeAnnotationList(Collection<? extends Annotation> annotationList, siani.tara.lang.Annotation[] correctAnnotations) {
		List<PsiElement> incorrectAnnotations = new ArrayList<>();
		for (PsiElement annotation : annotationList) {
			if (!isIn(correctAnnotations, annotation.getText()))
				incorrectAnnotations.add(annotation);
		}
		return incorrectAnnotations;
	}

	private boolean isIn(siani.tara.lang.Annotation[] correctAnnotation, String text) {
		for (siani.tara.lang.Annotation s : correctAnnotation)
			if (s.getName().equals(text)) return true;
		return false;
	}

	private Map<String, List<PsiElement>> count(Collection<? extends Annotation> psiAnnotations) {
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

	private List<String> annotationsToString(Collection<? extends Annotation> annotations) {
		List<String> names = new ArrayList<>();
		for (PsiElement annotation : annotations)
			names.add(annotation.getText());
		return names;
	}
}
