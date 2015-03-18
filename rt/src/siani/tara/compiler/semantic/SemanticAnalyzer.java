package siani.tara.compiler.semantic;


import siani.tara.compiler.core.errorcollection.SemanticException;
import siani.tara.compiler.core.errorcollection.semantic.RequiredNodeNotFoundError;
import siani.tara.compiler.core.errorcollection.semantic.SemanticError;
import siani.tara.compiler.core.errorcollection.semantic.SemanticErrorList;
import siani.tara.model.DeclaredNode;
import siani.tara.model.Model;
import siani.tara.model.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static siani.tara.model.Annotation.REQUIRED;
import static siani.tara.model.Annotation.TERMINAL;


public class SemanticAnalyzer {
	private Model model;
	private SemanticErrorList errors = new SemanticErrorList();


	public SemanticAnalyzer(Model model) {
		this.model = model;
	}

	public void analyze() throws SemanticException {
		usageAnalysis();
		if (model.getParentModel() != null)
			parentModelCoherenceAnalysis();
	}

	private void usageAnalysis() throws SemanticException {
		UsageAnalyzer usageAnalyzer = new UsageAnalyzer(model, errors);
		usageAnalyzer.checkNoConceptExistence(model.getNodeTable());
		if (!errors.isEmpty()) throwError();
		usageAnalyzer.checkUsage();
	}

	private void parentModelCoherenceAnalysis() throws SemanticException {
		for (Node node : model.getNodeTable()) {
			Collection<Node> requiredNodes = getRequiredInnerNodes(node);
			for (Node requiredNode : requiredNodes) {
				if (!existInstanceOf(requiredNode, getInnerNodes(node)))
					errors.add(new RequiredNodeNotFoundError(node.getName(), node));
			}
		}
	}

	private boolean existInstanceOf(Node requiredNode, Collection<Node> childrenOf) {
		for (Node node : childrenOf)
			if (requiredNode.getName().equals(node.getObject().getType()) || checkInSubs(requiredNode.getSubNodes(), node))
				return true;
		return false;
	}


	private List<Node> getInnerNodes(Node node) {
		List<Node> inners = new ArrayList<>();
		for (Node inner : node.getInnerNodes())
			if (!inner.isSub())
				inners.add(inner);
		return inners;
	}

	private boolean checkInSubs(DeclaredNode[] subConcepts, Node concept) {
		for (DeclaredNode subConcept : subConcepts)
			if (subConcept.getName().equals(concept.getObject().getType())) return true;
		return false;
	}

	private Collection<Node> getRequiredInnerNodes(Node node) {
		List<Node> required = new ArrayList<>();
		if (node == null) return Collections.EMPTY_LIST;
		for (Node inner : node.getInnerNodes())
			if (inner.getObject().is(REQUIRED) &&
				(model.getParentModel().isTerminal() && node.getObject().is(TERMINAL) ||
					!model.isTerminal() && !node.getObject().is(TERMINAL)))
				required.add(inner);
		return required;
	}

	private void throwError() throws SemanticException {
		throw new SemanticException(errors.toArray(new SemanticError[errors.size()]));
	}
}