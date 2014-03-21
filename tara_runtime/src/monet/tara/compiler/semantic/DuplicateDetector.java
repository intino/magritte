package monet.tara.compiler.semantic;

import AST.ASTNode;
import AST.ASTNode.AnnotationType;
import AST.ASTNode.Attribute;
import AST.ASTNode.Reference;
import AST.ASTNode.Word;
import Semantic.ErrorData.Error;

import java.util.HashSet;
import java.util.Set;

public class DuplicateDetector {

    private ErrorData errors = new ErrorData();

    public DuplicateDetector(ErrorData errors) {
        this.errors = errors;
    }

    public void checkDuplicateRoots(ASTNode[] concepts) {
        checkSiblings(concepts);
    }

    public void checkDuplicates(ASTNode concept) {
        checkIdentifiers(concept);
        checkAnnotations(concept);
    }

    private void checkIdentifiers(ASTNode concept) {
        Set<String> names = new HashSet<>();
        checkChildren(concept, names);
        checkAttributes(concept, names);
        checkWords(concept, names);
        checkReferences(concept, names);
    }

    private void checkSiblings (ASTNode[] concepts) {
        Set<String> names = new HashSet<>();
        for (ASTNode concept : concepts)
            if (!names.add(concept.getIdentifier()))
                errors.addDuplicateIdentifier(new Error(concept.getIdentifier(), concept));
    }

    private void checkChildren (ASTNode concept, Set<String> names) {
        for (ASTNode child : concept.getChildren())
            if (!names.add(child.getIdentifier()) && !child.getIdentifier().equals(""))
                errors.addDuplicateIdentifier(new Error(child.getIdentifier(), concept));
    }

    private void checkAttributes (ASTNode concept, Set<String> names) {
        for (Attribute attribute : concept.getAttributes())
            if (!names.add(attribute.getName()))
                errors.addDuplicateIdentifier(new Error(attribute.getName(), concept));
    }

    private void checkWords (ASTNode concept, Set<String> names) {
        for (Word word : concept.getWords()) {
            if (!names.add(word.getIdentifier()))
                errors.addDuplicateIdentifier(new Error(word.getIdentifier(), concept));
            checkWordValues(concept, word);
        }
    }

    private void checkWordValues(ASTNode concept, Word word) {
        Set<String> wordValues = new HashSet<>();
        for (String value : word.getWordTypes())
            if (!wordValues.add(value))
                errors.addDuplicateIdentifier(new Error(value, concept));
    }

    private void checkReferences (ASTNode concept, Set<String> names) {
        for (Reference reference : concept.getReferences())
            if (!names.add(reference.getName()))
                errors.addDuplicateIdentifier(new Error(reference.getName(), concept));
    }

    private void checkAnnotations (ASTNode concept) {
        Set<String> annotations = new HashSet<>();
        for (AnnotationType annotation : concept.getAnnotations())
            if (!annotations.add(annotation.name()))
                errors.addDuplicateAnnotation(new Error(annotation.name(), concept));
    }
}