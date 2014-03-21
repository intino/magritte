package monet.tara.compiler.semantic;

import AST.AST;
import AST.ASTNode;
import AST.ASTNode.Reference;
import Semantic.ErrorData.Error;

public class ReferenceVerifier {

    private ErrorData errors = new ErrorData();

    public ReferenceVerifier(ErrorData errors) {
        this.errors = errors;
    }

    public void checkConcept(ASTNode concept, AST ast) {
        ASTNode ancestor = ast.searchAncestry(concept);
        checkExtendedConcept(concept, ancestor);
        checkExtendedFromFinal(concept, ancestor);
        checkPolymorphic(concept);
        checkMorph(concept, ancestor);
        checkVarReference(concept, ast);
    }

    private void checkVarReference(ASTNode concept, AST ast) {
        for (Reference reference : concept.getReferences())
            if (ast.searchNode(reference.getNode(), concept) == null)
                errors.addUndefinedReference(new Error(reference.getNode(), concept));
    }

    private void checkExtendedConcept(ASTNode concept, ASTNode ancestor) {
        if (ancestor == null && concept.getExtendFrom() != null)
            errors.addUndefinedReference(new Error(concept.getExtendFrom(), concept));
    }

    private void checkExtendedFromFinal(ASTNode concept, ASTNode ancestor) {
        if (ancestor != null && ancestor.isFinal())
            errors.addInvalidHeritage(new Error(concept.getExtendFrom(), concept));
    }

    private void checkPolymorphic(ASTNode concept) {
        if (concept.isPolymorphic() && concept.getMorphs().length == 0)
            errors.addPolymorphicChildless(new Error(concept.getIdentifier(), concept));
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
            errors.addMorphWithoutParents(new Error(concept.getIdentifier(), concept));
    }

    private void notPolymorphicParent(ASTNode concept) {
        if (concept.getParent() != null && !concept.getParent().isPolymorphic())
            errors.addMorphWithoutParents(new Error(concept.getIdentifier(), concept));
    }

    private void notExtendedFromMorph(ASTNode concept, ASTNode ancestor) {
        if (ancestor != null && !ancestor.isMorph())
            errors.addInvalidHeritage(new Error(concept.getExtendFrom(), concept));
    }
}