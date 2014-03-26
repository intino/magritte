package monet.tara.compiler.semantic;


import monet.tara.compiler.core.ast.ASTNode;
import monet.tara.compiler.core.ast.ASTNode.AnnotationType;
import monet.tara.compiler.core.ast.ASTNode.Attribute;
import monet.tara.compiler.core.ast.ASTNode.Reference;
import monet.tara.compiler.core.ast.ASTNode.Word;
import monet.tara.compiler.core.errorcollection.semantic.DuplicateAnnotationError;
import monet.tara.compiler.core.errorcollection.semantic.DuplicateIdentifierError;
import monet.tara.compiler.core.errorcollection.semantic.SemanticErrorList;

import java.util.HashSet;
import java.util.Set;

public class DuplicateDetector {

	private SemanticErrorList errors = new SemanticErrorList();

	public DuplicateDetector(SemanticErrorList errors) {
		this.errors = errors;
	}

	public void checkDuplicateRoots(ASTNode[] concepts) {
		checkSiblings(concepts);
	}

	public void checkDuplicates(ASTNode concept) {
		checkIdentifiers(concept);
		checkAnnotations(concept);
	}

	private void checkIdentifiers(ASTNode concept) {
		Set<String> names = new HashSet<>();
		checkChildren(concept, names);
		checkAttributes(concept, names);
		checkWords(concept, names);
		checkReferences(concept, names);
	}

	private void checkSiblings(ASTNode[] concepts) {
		Set<String> names = new HashSet<>();
		for (ASTNode concept : concepts)
			if (!names.add(concept.getIdentifier()))
				errors.add(new DuplicateIdentifierError(concept.getIdentifier(), concept));
	}

	private void checkChildren(ASTNode concept, Set<String> names) {
		for (ASTNode child : concept.getChildren())
			if (!names.add(child.getIdentifier()) && !"".equals(child.getIdentifier()))
				errors.add(new DuplicateIdentifierError(child.getIdentifier(), concept));
	}

	private void checkAttributes(ASTNode concept, Set<String> names) {
		for (Attribute attribute : concept.getAttributes())
			if (!names.add(attribute.getName()))
				errors.add(new DuplicateIdentifierError(attribute.getName(), concept));
	}

	private void checkWords(ASTNode concept, Set<String> names) {
		for (Word word : concept.getWords()) {
			if (!names.add(word.getIdentifier()))
				errors.add(new DuplicateIdentifierError(word.getIdentifier(), concept));
			checkWordValues(concept, word);
		}
	}

	private void checkWordValues(ASTNode concept, Word word) {
		Set<String> wordValues = new HashSet<>();
		for (String value : word.getWordTypes())
			if (!wordValues.add(value))
				errors.add(new DuplicateIdentifierError(value, concept));
	}

	private void checkReferences(ASTNode concept, Set<String> names) {
		for (Reference reference : concept.getReferences())
			if (!names.add(reference.getName()))
				errors.add(new DuplicateIdentifierError(reference.getName(), concept));
	}

	private void checkAnnotations(ASTNode concept) {
		Set<String> annotations = new HashSet<>();
		for (AnnotationType annotation : concept.getAnnotations())
			if (!annotations.add(annotation.name()))
				errors.add(new DuplicateAnnotationError(annotation.name(), concept));
	}
}