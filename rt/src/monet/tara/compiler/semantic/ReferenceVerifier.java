package monet.tara.compiler.semantic;


import monet.tara.lang.AbstractNode;
import monet.tara.lang.TreeWrapper;
import monet.tara.lang.Reference;
import monet.tara.compiler.core.errorcollection.semantic.*;

public class ReferenceVerifier {

	private SemanticErrorList errors = new SemanticErrorList();

	public ReferenceVerifier(SemanticErrorList errors) {
		this.errors = errors;
	}

	public void checkConcept(AbstractNode concept, TreeWrapper ast) {
		AbstractNode ancestor = concept.getParentConcept();
		checkExtendedConcept(concept, ancestor);
		checkExtendedFromFinal(concept, ancestor);
		checkBase(concept);
		checkCase(concept, ancestor);
		checkVarReference(concept, ast);
	}

	private void checkVarReference(AbstractNode concept, TreeWrapper ast) {
		for (Reference reference : concept.getReferences())
			if (ast.searchNode(reference.getNode(), concept) == null)
				errors.add(new UndefinedReferenceError(reference.getNode(), concept));
	}

	private void checkExtendedConcept(AbstractNode concept, AbstractNode ancestor) {
		if (ancestor == null && concept.getParentName() != null)
			errors.add(new UndefinedReferenceError(concept.getParentName(), concept));
	}

	private void checkExtendedFromFinal(AbstractNode concept, AbstractNode ancestor) {
		if (ancestor != null && ancestor.isFinal())
			errors.add(new InvalidHeritageError(concept.getParentName(), concept));
	}

	private void checkBase(AbstractNode concept) {
		if (concept.isBase() && concept.getCases().length == 0)
			errors.add(new PolymorphicChildlessError(concept.getIdentifier(), concept));
	}

	private void checkCase(AbstractNode concept, AbstractNode ancestor) {
		if (concept.isCase()) {
			noParent(concept);
			notBaseParent(concept);
//			notExtendedFromBase(concept, ancestor);
		}
	}

	private void noParent(AbstractNode concept) {
		if (concept.getContainer() == null)
			errors.add(new MorphWithoutParentError(concept.getIdentifier(), concept));
	}

	private void notBaseParent(AbstractNode concept) {
		if (concept.getContainer() != null && !concept.getContainer().isBase())
			errors.add(new MorphWithoutParentError(concept.getIdentifier(), concept));
	}

//	private void notExtendedFromBase(monet.tara.lang.AbstractNode concept, monet.tara.lang.AbstractNode ancestor) {
//		if (ancestor != null && !ancestor.isCase())
//			errors.add(new InvalidHeritageError(concept.getParentName(), concept));
//	}
}