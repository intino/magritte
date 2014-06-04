package monet.tara.compiler.semantic;

import monet.tara.compiler.core.errorcollection.semantic.SemanticErrorList;
import monet.tara.compiler.core.errorcollection.semantic.UnusedConceptError;
import monet.tara.lang.*;
import monet.tara.lang.NodeObject.AnnotationType;

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
			this.conceptList.put(node.getAbsolutePath(), node);
	}

	public void checkUsage(Node concept, TreeWrapper ast) {
//		removeAncestor(concept.getObject().getParent());
		checkReference(concept, ast);
	}

//	public void removeAncestor(NodeObject nodeObject) {
//		String rootConcept = (nodeObject != null) ? nodeObject.getAbsolutePath().split("\\.")[0] : "";
//		conceptList.remove(rootConcept);
//	}

	private void checkIfUsed(Node node) {
		String rootConcept = (node != null) ? node.getAbsolutePath().split("\\.")[0] : "";
		conceptList.remove(rootConcept);
	}

	private void checkReference(Node concept, TreeWrapper ast) {
		for (Reference reference : concept.getObject().getReferences())
			checkIfUsed(ast.searchNode(reference.getType(), concept));
	}

	public void finish() {
		for (String concept : conceptList.keySet())
			errors.add(new UnusedConceptError(concept, conceptList.get(concept)));
	}
}
