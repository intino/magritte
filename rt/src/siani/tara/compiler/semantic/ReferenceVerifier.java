package siani.tara.compiler.semantic;

import siani.tara.compiler.core.errorcollection.semantic.*;
import siani.tara.lang.Node;
import siani.tara.lang.NodeObject;
import siani.tara.lang.Reference;
import siani.tara.lang.TreeWrapper;

public class ReferenceVerifier {

	private SemanticErrorList errors = new SemanticErrorList();

	public ReferenceVerifier(SemanticErrorList errors) {
		this.errors = errors;
	}

	public void checkConcept(Node concept, TreeWrapper ast) {
		Node ancestor = ast.searchAncestry(concept);
		if (concept.getObject().getParentName() != null) checkParent(concept, ancestor);
		if (concept.isBase()) checkBase(concept);
		else if (concept.isCase()) checkCase(concept, ancestor.getObject());
		checkVarReference(concept, ast);
	}

	private void checkParent(Node concept, Node ancestor) {
		if (ancestor == null) {
			errors.add(new UndefinedReferenceError(concept.getObject().getParentName(), concept));
		} else checkExtendedFromFinal(concept, ancestor.getObject());
	}

	private void checkVarReference(Node concept, TreeWrapper ast) {
		for (Reference reference : concept.getObject().getReferences())
			if (ast.searchNode(reference.getType(), concept) == null)
				errors.add(new UndefinedReferenceError(reference.getType(), concept));
	}

	private void checkExtendedFromFinal(Node concept, NodeObject ancestor) {
		if (ancestor != null && ancestor.isFinal())
			errors.add(new InvalidHeritageError(concept.getObject().getParentName(), concept));
	}

	private void checkBase(Node concept) {
		if (concept.getCases().length == 0)
			errors.add(new PolymorphicChildlessError(concept.getName(), concept));
	}

	private void checkCase(Node concept, NodeObject ancestor) {
		noParent(concept);
		notBaseParent(concept);
		notExtendedFromBase(concept, ancestor);
	}

	private void noParent(Node concept) {
		if (concept.getContainer() == null)
			errors.add(new MorphWithoutParentError(concept.getName(), concept));
	}

	private void notBaseParent(Node concept) {
		if (concept.getContainer() != null && !concept.getContainer().isBase())
			errors.add(new MorphWithoutParentError(concept.getName(), concept));
	}

	private void notExtendedFromBase(Node concept, NodeObject ancestor) {
		if (ancestor != null && !ancestor.isBase())
			errors.add(new InvalidHeritageError(concept.getObject().getParentName(), concept));
	}
}