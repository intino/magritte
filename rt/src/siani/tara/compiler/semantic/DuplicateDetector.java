package siani.tara.compiler.semantic;


import siani.tara.compiler.core.errorcollection.semantic.DuplicateAnnotationError;
import siani.tara.compiler.core.errorcollection.semantic.DuplicateIdentifierError;
import siani.tara.compiler.core.errorcollection.semantic.SemanticErrorList;
import siani.tara.model.*;

import java.util.HashSet;
import java.util.Set;

public class DuplicateDetector {

	private SemanticErrorList errors = new SemanticErrorList();

	public DuplicateDetector(SemanticErrorList errors) {
		this.errors = errors;
	}

	public void detectDuplicates(Node node) {
		checkIdentifiers(node);
		checkAnnotations(node);
	}

	public boolean detectDuplicateNode(Node node, NodeTree treeModel) {
		int count = 0;
		for (Node node1 : treeModel) {
			if (node1.is(LinkNode.class)) continue;
			if (node.getName().equals(node1.getName())) count++;
		}
		return count > 1;
	}


	private void checkIdentifiers(Node node) {
		Set<String> names = new HashSet<>();
		checkAttributes(node, names);
		checkWords(node, names);
		checkReferences(node, names);
		checkInnerLinks(node);
	}

	private void checkAttributes(Node node, Set<String> names) {
		for (Attribute attribute : node.getObject().getAttributes())
			if (!names.add(attribute.getName()))
				errors.add(new DuplicateIdentifierError(attribute.getName(), node));
	}

	private void checkWords(Node node, Set<String> names) {
		for (Word word : node.getObject().getWords()) {
			if (!names.add(word.getName()))
				errors.add(new DuplicateIdentifierError(word.getName(), node));
			checkWordValues(node, word);
		}
	}

	private void checkWordValues(Node node, Word word) {
		Set<String> wordValues = new HashSet<>();
		for (String value : word.getWordTypes())
			if (!wordValues.add(value))
				errors.add(new DuplicateIdentifierError(value, node));
	}

	private void checkReferences(Node node, Set<String> names) {
		for (Reference reference : node.getObject().getReferences())
			if (!names.add(reference.getName()))
				errors.add(new DuplicateIdentifierError(reference.getName(), node));
	}

	private void checkInnerLinks(Node node) {
		Set<String> set = new HashSet<>();
		for (Node inner : node.getInnerNodes())
			if (inner.is(LinkNode.class) && !set.add(((LinkNode) inner).getDestinyName() + (inner.isAggregated() ? "[Aggregated]" : "")))
				errors.add(new DuplicateIdentifierError(inner.getName(), inner));
	}

	private void checkAnnotations(Node node) {
		Set<String> annotations = new HashSet<>();
		for (Annotation annotation : node.getAnnotations())
			if (!annotations.add(annotation.name()))
				errors.add(new DuplicateAnnotationError(annotation.name(), node));
	}
}