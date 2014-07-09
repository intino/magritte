package siani.tara.compiler.semantic;

import siani.tara.compiler.core.errorcollection.semantic.SemanticErrorList;
import siani.tara.compiler.core.errorcollection.semantic.UnusedConceptError;
import siani.tara.lang.*;
import siani.tara.lang.NodeObject.AnnotationType;

import java.util.*;

public class ConceptsUsage {

	private SemanticErrorList errors = new SemanticErrorList();
	private Map<String, DeclaredNode> conceptList = new HashMap<>();

	public ConceptsUsage(SemanticErrorList errors) {
		this.errors = errors;
	}

	public void start(NodeTree conceptList) {
		for (DeclaredNode concept : conceptList)
			addToList(concept);
	}

	private void addToList(DeclaredNode node) {
		NodeObject concept = node.getObject();
		List<AnnotationType> annotations = new ArrayList<>(Arrays.asList(concept.getAnnotations()));
		if (!annotations.contains(AnnotationType.ROOT))
			this.conceptList.put(node.getQualifiedName(), node);
	}

	public void checkUsage(DeclaredNode concept, Model ast) {
//		removeAncestor(concept.getParent());
		checkReference(concept, ast);
	}

	public void removeAncestor(DeclaredNode node) {
		String rootConcept = (node != null) ? node.getQualifiedName().split("\\.")[0] : "";
		conceptList.remove(rootConcept);
	}

	private void checkIfUsed(DeclaredNode node) {
		String rootConcept = (node != null) ? node.getQualifiedName().split("\\.")[0] : "";
		conceptList.remove(rootConcept);
	}

	private void checkReference(DeclaredNode concept, Model ast) {
		for (Reference reference : concept.getObject().getReferences())
			checkIfUsed(ast.searchNode(reference.getType(), concept));
	}

	public void finish() {
		for (String concept : conceptList.keySet())
			errors.add(new UnusedConceptError(concept, conceptList.get(concept)));
	}
}
