package monet.tara.compiler.semantic;

import monet.tara.compiler.core.ast.AST;
import monet.tara.compiler.core.ast.ASTNode;
import monet.tara.compiler.core.ast.ASTNode.AnnotationType;
import monet.tara.compiler.core.error_collection.semantic.SemanticErrorList;
import monet.tara.compiler.core.error_collection.semantic.UnusedConceptError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ConceptsUsage {

    private SemanticErrorList errors = new SemanticErrorList();
    private HashMap<String, ASTNode> conceptList = new HashMap<>();

    public ConceptsUsage(SemanticErrorList errors) {
        this.errors = errors;
    }

    public void start(ASTNode[] conceptList) {
        for (ASTNode concept : conceptList)
            addToList(concept);
    }

    private void addToList(ASTNode concept) {
        ArrayList<AnnotationType> annotations = new ArrayList<>(Arrays.asList(concept.getAnnotations()));
        if (!annotations.contains(AnnotationType.Root))
            this.conceptList.put(concept.getAbsolutePath(), concept);
    }

    public void checkUsage(ASTNode concept, AST ast) {
        checkIfUsed(ast.searchAncestry(concept));
        checkReference(concept, ast);
    }

    private void checkIfUsed(ASTNode ancestor) {
        String rootConcept = (ancestor != null)? ancestor.getAbsolutePath().split("\\.")[0] : "";
        conceptList.remove(rootConcept);
    }

    private void checkReference(ASTNode concept, AST ast) {
        for (ASTNode.Reference reference : concept.getReferences())
            checkIfUsed(ast.searchNode(reference.getNode(), concept));
    }

    public void finish() {
        for (String concept : conceptList.keySet())
            errors.add(new UnusedConceptError(concept, conceptList.get(concept)));
    }
}
