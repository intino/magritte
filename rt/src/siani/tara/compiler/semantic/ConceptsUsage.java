package siani.tara.compiler.semantic;

import siani.tara.compiler.core.errorcollection.semantic.SemanticErrorList;
import siani.tara.compiler.core.errorcollection.semantic.UnusedConceptError;
import siani.tara.lang.*;
import siani.tara.lang.NodeObject.AnnotationType;

import java.util.*;

public class ConceptsUsage {

	private SemanticErrorList errors = new SemanticErrorList();
	private Map<String, Node> conceptList = new HashMap<>();

	public ConceptsUsage(SemanticErrorList errors) {
		this.errors = errors;
	}

	public void start(NodeTree conceptList) {
		for (Node concept : conceptList)
			addToList(concept);
	}

	private void addToList(Node node) {
		NodeObject concept = node.getObject();
		List<AnnotationType> annotations = new ArrayList<>(Arrays.asList(concept.getAnnotations()));
		if (!annotations.contains(AnnotationType.ROOT))
			this.conceptList.put(node.getQualifiedName(), node);
	}

	public void checkUsage(Node concept, Model ast) {
//		removeAncestor(concept.getParent());
		checkReference(concept, ast);
	}

	public void removeAncestor(Node node) {
		String rootConcept = (node != null) ? node.getQualifiedName().split("\\.")[0] : "";
		conceptList.remove(rootConcept);
	}

	private void checkIfUsed(Node node) {
		String rootConcept = (node != null) ? node.getQualifiedName().split("\\.")[0] : "";
		conceptList.remove(rootConcept);
	}

	private void checkReference(Node concept, Model ast) {
		for (Reference reference : concept.getObject().getReferences())
			checkIfUsed(ast.searchNode(reference.getType(), concept));
	}

	public void finish() {
		for (String concept : conceptList.keySet())
			errors.add(new UnusedConceptError(concept, conceptList.get(concept)));
	}
}
