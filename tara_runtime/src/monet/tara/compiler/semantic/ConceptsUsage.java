package monet.tara.compiler.semantic;

import AST.AST;
import AST.ASTNode;
import AST.ASTNode.AnnotationType;
import AST.ASTNode.Reference;
import Semantic.ErrorData.Warning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ConceptsUsage {

    private ErrorData errors = new ErrorData();
    private HashMap<String, ASTNode> conceptList = new HashMap<>();

    public ConceptsUsage(ErrorData errors) {
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
        for (Reference reference : concept.getReferences())
            checkIfUsed(ast.searchNode(reference.getNode(), concept));
    }

    public void finish() {
        for (String concept : conceptList.keySet())
            errors.addConceptUnused(new Warning(concept, conceptList.get(concept)));
    }
}
