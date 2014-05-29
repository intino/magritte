package monet.tara.compiler.semantic;


import monet.tara.compiler.core.errorcollection.semantic.DuplicateAnnotationError;
import monet.tara.compiler.core.errorcollection.semantic.DuplicateIdentifierError;
import monet.tara.compiler.core.errorcollection.semantic.SemanticErrorList;
import monet.tara.lang.*;
import monet.tara.lang.AbstractNode;
import monet.tara.lang.AbstractNode.AnnotationType;

import java.util.HashSet;
import java.util.Set;

public class DuplicateDetector {

	private SemanticErrorList errors = new SemanticErrorList();

	public DuplicateDetector(SemanticErrorList errors) {
		this.errors = errors;
	}

	public void checkDuplicateRoots(AbstractTree concepts) {
		checkSiblings(concepts);
	}

	public void checkDuplicates(AbstractNode concept) {
		checkIdentifiers(concept);
		checkAnnotations(concept);
	}

	private void checkIdentifiers(AbstractNode concept) {
		Set<String> names = new HashSet<>();
		checkChildren(concept, names);
		checkAttributes(concept, names);
		checkWords(concept, names);
		checkReferences(concept, names);
	}

	private void checkSiblings(AbstractTree concepts) {
		Set<String> names = new HashSet<>();
		for (AbstractNode concept : concepts)
			if (!names.add(concept.getIdentifier()))
				errors.add(new DuplicateIdentifierError(concept.getIdentifier(), concept));
	}

	private void checkChildren(AbstractNode concept, Set<String> names) {
		for (AbstractNode child : concept.getInnerConcepts())
			if (!names.add(child.getIdentifier()) && !"".equals(child.getIdentifier()))
				errors.add(new DuplicateIdentifierError(child.getIdentifier(), concept));
	}

	private void checkAttributes(AbstractNode concept, Set<String> names) {
		for (NodeAttribute attribute : concept.getAttributes())
			if (!names.add(attribute.getName()))
				errors.add(new DuplicateIdentifierError(attribute.getName(), concept));
	}

	private void checkWords(AbstractNode concept, Set<String> names) {
		for (NodeWord word : concept.getWords()) {
			if (!names.add(word.getName()))
				errors.add(new DuplicateIdentifierError(word.getName(), concept));
			checkWordValues(concept, word);
		}
	}

	private void checkWordValues(AbstractNode concept, NodeWord word) {
		Set<String> wordValues = new HashSet<>();
		for (String value : word.getWordTypes())
			if (!wordValues.add(value))
				errors.add(new DuplicateIdentifierError(value, concept));
	}

	private void checkReferences(AbstractNode concept, Set<String> names) {
		for (Reference reference : concept.getReferences())
			if (!names.add(reference.getName()))
				errors.add(new DuplicateIdentifierError(reference.getName(), concept));
	}

	private void checkAnnotations(AbstractNode concept) {
		Set<String> annotations = new HashSet<>();
		for (AnnotationType annotation : concept.getAnnotations())
			if (!annotations.add(annotation.name()))
				errors.add(new DuplicateAnnotationError(annotation.name(), concept));
	}
}