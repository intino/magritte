package monet.tara.compiler.semantic;


import monet.tara.lang.AbstractNode;
import monet.tara.lang.AbstractTree;
import monet.tara.lang.AbstractNode.AnnotationType;
import monet.tara.compiler.core.errorcollection.semantic.NoRootError;
import monet.tara.compiler.core.errorcollection.semantic.SemanticErrorList;
import monet.tara.compiler.core.errorcollection.semantic.WrongAnnotationError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnnotationChecker {

	private boolean thereIsAnyRoot;
	private SemanticErrorList errors = new SemanticErrorList();
	private List<AnnotationType> annotations = new ArrayList<>();

	public AnnotationChecker(SemanticErrorList errors) {
		this.errors = errors;
		this.thereIsAnyRoot = false;
	}

	public void checkAnnotations(AbstractNode concept) {
		annotations = Arrays.asList(concept.getAnnotations());
		rootAnnotation(concept);
		requiredAnnotation(concept);
		multipleAnnotation(concept);
	}

	public void checkIfRoot(AbstractTree conceptList) {
		findRootConcepts(conceptList);
		noRootConcepts();

	}

	private void findRootConcepts(AbstractTree conceptList) {
		for (AbstractNode concept : conceptList)
			thereIsAnyRoot = isRootConcept(concept) || thereIsAnyRoot;
	}

	private boolean isRootConcept(AbstractNode concept) {
		annotations = Arrays.asList(concept.getAnnotations());
		return annotations.contains(AnnotationType.ROOT);
	}

	private void noRootConcepts() {
		if (!thereIsAnyRoot)
			errors.add(new NoRootError());
	}

	private void rootAnnotation(AbstractNode concept) {
		if (!concept.isPrime() && annotations.contains(AnnotationType.ROOT))
			errors.add(new WrongAnnotationError(AnnotationType.ROOT.name(), concept));
	}

	private void requiredAnnotation(AbstractNode concept) {
		if ((concept.isPrime() || concept.isCase()) && annotations.contains(AnnotationType.REQUIRED))
			errors.add(new WrongAnnotationError(AnnotationType.REQUIRED.name(), concept));
	}

	private void multipleAnnotation(AbstractNode concept) {
		if ((concept.isPrime() || concept.isCase()) && annotations.contains(AnnotationType.MULTIPLE))
			errors.add(new WrongAnnotationError(AnnotationType.MULTIPLE.name(), concept));
	}
}
