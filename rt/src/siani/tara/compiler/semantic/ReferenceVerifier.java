package siani.tara.compiler.semantic;

import siani.tara.compiler.core.errorcollection.semantic.MorphWithoutParentError;
import siani.tara.compiler.core.errorcollection.semantic.SemanticErrorList;
import siani.tara.compiler.core.errorcollection.semantic.UndefinedReferenceError;
import siani.tara.lang.DeclaredNode;
import siani.tara.lang.Model;
import siani.tara.lang.Node;
import siani.tara.lang.Reference;

public class ReferenceVerifier {

	private SemanticErrorList errors = new SemanticErrorList();

	public ReferenceVerifier(SemanticErrorList errors) {
		this.errors = errors;
	}

	public void checkConcept(Node concept, Model ast) {
		DeclaredNode ancestor = ast.searchAncestry(concept);
		if (concept.getObject().getParentName() != null) checkParent(concept, ancestor);
		else if (concept.isCase()) checkCase(concept);
		checkVarReference(concept, ast);
	}

	private void checkParent(Node concept, DeclaredNode ancestor) {
		if (ancestor == null) errors.add(new UndefinedReferenceError(concept.getObject().getParentName(), concept));
	}

	private void checkVarReference(Node concept, Model ast) {
		for (Reference reference : concept.getObject().getReferences())
			if (ast.searchNode(reference.getType(), concept) == null)
				errors.add(new UndefinedReferenceError(reference.getType(), concept));
	}

	private void checkCase(Node concept) {
		noParent(concept);
		notBaseParent(concept);
	}

	private void noParent(Node concept) {
		if (concept.getContainer() == null)
			errors.add(new MorphWithoutParentError(concept.getName(), concept));
	}

	private void notBaseParent(Node concept) {
		if (concept.getContainer() != null)
			errors.add(new MorphWithoutParentError(concept.getName(), concept));
	}

}