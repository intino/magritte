package siani.tara.compiler.semantic;

import siani.tara.compiler.core.errorcollection.semantic.NoRootError;
import siani.tara.compiler.core.errorcollection.semantic.SemanticErrorList;
import siani.tara.compiler.core.errorcollection.semantic.UnusedConceptError;
import siani.tara.lang.*;
import siani.tara.lang.ModelObject.AnnotationType;

import java.util.*;

public class UsageAnalyzer {

	private final Model model;
	private boolean thereIsAnyRoot;
	private SemanticErrorList errors = new SemanticErrorList();
	private Map<String, Node> toCheckNodes = new HashMap<>();
	private List<String> toRemove = new ArrayList();

	public UsageAnalyzer(Model model, SemanticErrorList errors) {
		this.model = model;
		this.errors = errors;
	}

	public void checkUsage() {
		for (Node node : model.getTreeModel())
			if (node.is(DeclaredNode.class)) addToList(node);
		for (Map.Entry<String, Node> entry : toCheckNodes.entrySet())
			removeParent(entry.getValue());
		processReferences();
		for (String key : toRemove) toCheckNodes.remove(key);
		for (Map.Entry<String, Node> node : toCheckNodes.entrySet())
			if (!isAbstract(node.getValue()))
				errors.add(new UnusedConceptError(node.getKey(), node.getValue()));
	}

	private void processReferences() {
		for (Map.Entry<String, Node> nodeEntry : toCheckNodes.entrySet())
			if (isReferenced(nodeEntry.getKey()))
				toRemove.add(nodeEntry.getKey());
	}

	private boolean isReferenced(String qn) {
		for (Node node : model.getNodeTable().values()) {
			if (node.is(DeclaredNode.class) && isReferencedInVar(node, qn))
				return true;
			else if (node.is(LinkNode.class) && isReferencedAsLink((LinkNode) node, qn)) return true;
		}
		return false;
	}

	private boolean isReferencedAsLink(LinkNode node, String qn) {
		return (qn.equals(node.getDestinyQN()));
	}

	private boolean isReferencedInVar(Node node, String qn) {
		for (Reference reference : node.getObject().getReferences())
			if (qn.equals(reference.getType())) return true;
		return false;
	}

	private boolean isAbstract(Node node) {
		return node.getCases().length > 0;
	}

	public void checkRootExistence(Collection<Node> nodeTree) {
		findRootConcepts(nodeTree);
		noRootConcepts();
	}

	private void addToList(Node node) {
		if (node.getObject().is(AnnotationType.COMPONENT)) //TODO
			this.toCheckNodes.put(node.getQualifiedName(), node);
	}

	private void removeParent(Node node) {
		toRemove.add(node.getObject().getParentName());
	}

	private void findRootConcepts(Collection<Node> nodeTree) {
		for (Node node : nodeTree)
			if (node instanceof DeclaredNode)
				thereIsAnyRoot = !node.getObject().is(AnnotationType.COMPONENT) || thereIsAnyRoot;
	}

	private void noRootConcepts() {
		if (!thereIsAnyRoot)
			errors.add(new NoRootError());
	}
}
