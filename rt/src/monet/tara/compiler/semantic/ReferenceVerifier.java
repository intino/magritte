package monet.tara.compiler.semantic;


import monet.tara.compiler.core.ast.ASTWrapper;
import monet.tara.compiler.core.ast.ASTNode;
import monet.tara.compiler.core.ast.ASTNode.Reference;
import monet.tara.compiler.core.errorcollection.semantic.*;

public class ReferenceVerifier {

	private SemanticErrorList errors = new SemanticErrorList();

	public ReferenceVerifier(SemanticErrorList errors) {
		this.errors = errors;
	}

	public void checkConcept(ASTNode concept, ASTWrapper ast) {
		ASTNode ancestor = ast.searchAncestry(concept);
		checkExtendedConcept(concept, ancestor);
		checkExtendedFromFinal(concept, ancestor);
		checkPolymorphic(concept);
		checkMorph(concept, ancestor);
		checkVarReference(concept, ast);
	}

	private void checkVarReference(ASTNode concept, ASTWrapper ast) {
		for (Reference reference : concept.getReferences())
			if (ast.searchNode(reference.getNode(), concept) == null)
				errors.add(new UndefinedReferenceError(reference.getNode(), concept));
	}

	private void checkExtendedConcept(ASTNode concept, ASTNode ancestor) {
		if (ancestor == null && concept.getExtendFrom() != null)
			errors.add(new UndefinedReferenceError(concept.getExtendFrom(), concept));
	}

	private void checkExtendedFromFinal(ASTNode concept, ASTNode ancestor) {
		if (ancestor != null && ancestor.isFinal())
			errors.add(new InvalidHeritageError(concept.getExtendFrom(), concept));
	}

	private void checkPolymorphic(ASTNode concept) {
		if (concept.isPolymorphic() && concept.getMorphs().length == 0)
			errors.add(new PolymorphicChildlessError(concept.getIdentifier(), concept));
	}

	private void checkMorph(ASTNode concept, ASTNode ancestor) {
		if (concept.isMorph()) {
			noParent(concept);
			notPolymorphicParent(concept);
			notExtendedFromMorph(concept, ancestor);
		}
	}

	private void noParent(ASTNode concept) {
		if (concept.getParent() == null)
			errors.add(new MorphWithoutParentError(concept.getIdentifier(), concept));
	}

	private void notPolymorphicParent(ASTNode concept) {
		if (concept.getParent() != null && !concept.getParent().isPolymorphic())
			errors.add(new MorphWithoutParentError(concept.getIdentifier(), concept));
	}

	private void notExtendedFromMorph(ASTNode concept, ASTNode ancestor) {
		if (ancestor != null && !ancestor.isMorph())
			errors.add(new InvalidHeritageError(concept.getExtendFrom(), concept));
	}
}