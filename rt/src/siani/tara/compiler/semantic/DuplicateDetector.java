package siani.tara.compiler.semantic;


import siani.tara.compiler.core.errorcollection.semantic.DuplicateAnnotationError;
import siani.tara.compiler.core.errorcollection.semantic.DuplicateIdentifierError;
import siani.tara.compiler.core.errorcollection.semantic.SemanticErrorList;
import siani.tara.lang.*;
import siani.tara.lang.NodeObject.AnnotationType;

import java.util.HashSet;
import java.util.Set;

public class DuplicateDetector {

	private SemanticErrorList errors = new SemanticErrorList();

	public DuplicateDetector(SemanticErrorList errors) {
		this.errors = errors;
	}

	public void checkDuplicateRoots(NodeTree concepts) {
		checkSiblings(concepts);
	}

	public void checkDuplicates(DeclaredNode concept) {
		checkIdentifiers(concept);
		checkAnnotations(concept);
	}

	private void checkIdentifiers(DeclaredNode concept) {
		Set<String> names = new HashSet<>();
		checkChildren(concept, names);
		checkAttributes(concept, names);
		checkWords(concept, names);
		checkReferences(concept, names);
	}

	private void checkSiblings(NodeTree concepts) {
		Set<String> names = new HashSet<>();
		for (DeclaredNode concept : concepts)
			if (!names.add(concept.getName()))
				errors.add(new DuplicateIdentifierError(concept.getName(), concept));
	}

	private void checkChildren(DeclaredNode concept, Set<String> names) {
		for (DeclaredNode child : concept.getInnerNodes())
			if (!names.add(child.getName()) && !"".equals(child.getName()))
				errors.add(new DuplicateIdentifierError(child.getName(), concept));
	}

	private void checkAttributes(DeclaredNode concept, Set<String> names) {
		for (NodeAttribute attribute : concept.getObject().getAttributes())
			if (!names.add(attribute.getName()))
				errors.add(new DuplicateIdentifierError(attribute.getName(), concept));
	}

	private void checkWords(DeclaredNode concept, Set<String> names) {
		for (NodeWord word : concept.getObject().getWords()) {
			if (!names.add(word.getName()))
				errors.add(new DuplicateIdentifierError(word.getName(), concept));
			checkWordValues(concept, word);
		}
	}

	private void checkWordValues(DeclaredNode concept, NodeWord word) {
		Set<String> wordValues = new HashSet<>();
		for (String value : word.getWordTypes())
			if (!wordValues.add(value))
				errors.add(new DuplicateIdentifierError(value, concept));
	}

	private void checkReferences(DeclaredNode concept, Set<String> names) {
		for (Reference reference : concept.getObject().getReferences())
			if (!names.add(reference.getName()))
				errors.add(new DuplicateIdentifierError(reference.getName(), concept));
	}

	private void checkAnnotations(DeclaredNode concept) {
		Set<String> annotations = new HashSet<>();
		for (AnnotationType annotation : concept.getObject().getAnnotations())
			if (!annotations.add(annotation.name()))
				errors.add(new DuplicateAnnotationError(annotation.name(), concept));
	}
}