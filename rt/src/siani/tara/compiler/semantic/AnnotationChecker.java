package siani.tara.compiler.semantic;


import siani.tara.compiler.core.errorcollection.semantic.NoRootError;
import siani.tara.compiler.core.errorcollection.semantic.SemanticErrorList;
import siani.tara.compiler.core.errorcollection.semantic.WrongAnnotationError;
import siani.tara.lang.DeclaredNode;
import siani.tara.lang.Node;
import siani.tara.lang.ModelObject.AnnotationType;
import siani.tara.lang.NodeTree;

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

	public void checkAnnotations(DeclaredNode concept) {
		annotations = Arrays.asList(concept.getObject().getAnnotations());
		rootAnnotation(concept);
		requiredAnnotation(concept);
//		multipleAnnotation(concept);
	}

	public void checkIfRoot(NodeTree conceptList) {
		findRootConcepts(conceptList);
		noRootConcepts();

	}

	private void findRootConcepts(NodeTree conceptList) {
		for (Node concept : conceptList)
			thereIsAnyRoot = isRootConcept(concept) || thereIsAnyRoot;
	}

	private boolean isRootConcept(Node concept) {
		annotations = Arrays.asList(concept.getObject().getAnnotations());
		return annotations.contains(AnnotationType.ROOT);
	}

	private void noRootConcepts() {
		if (!thereIsAnyRoot)
			errors.add(new NoRootError());
	}

	private void rootAnnotation(DeclaredNode concept) {
		if (!concept.isPrime() && annotations.contains(AnnotationType.ROOT))
			errors.add(new WrongAnnotationError(AnnotationType.ROOT.name(), concept));
	}

	private void requiredAnnotation(DeclaredNode concept) {
		if ((concept.isPrime() || concept.isCase()) && annotations.contains(AnnotationType.REQUIRED))
			errors.add(new WrongAnnotationError(AnnotationType.REQUIRED.name(), concept));
	}

//	private void multipleAnnotation(Node concept) {
//		if ((concept.isPrime() || concept.isCase()) && annotations.contains(AnnotationType.MULTIPLE))
//			errors.add(new WrongAnnotationError(AnnotationType.MULTIPLE.name(), concept));
//	}
}
