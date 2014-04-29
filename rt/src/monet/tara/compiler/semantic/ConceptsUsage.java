package monet.tara.compiler.semantic;

import monet.tara.lang.AST;
import monet.tara.lang.ASTWrapper;
import monet.tara.lang.ASTNode;
import monet.tara.lang.ASTNode.AnnotationType;
import monet.tara.compiler.core.errorcollection.semantic.SemanticErrorList;
import monet.tara.compiler.core.errorcollection.semantic.UnusedConceptError;

import java.util.*;

public class ConceptsUsage {

    private SemanticErrorList errors = new SemanticErrorList();
    private Map<String, ASTNode> conceptList = new HashMap<>();

    public ConceptsUsage(SemanticErrorList errors) {
        this.errors = errors;
    }

    public void start(AST conceptList) {
        for (ASTNode concept : conceptList)
            addToList(concept);
    }

    private void addToList(ASTNode concept) {
        List<AnnotationType> annotations = new ArrayList<>(Arrays.asList(concept.getAnnotations()));
        if (!annotations.contains(AnnotationType.ROOT))
            this.conceptList.put(concept.getAbsolutePath(), concept);
    }

    public void checkUsage(ASTNode concept, ASTWrapper ast) {
        checkIfUsed(ast.searchAncestry(concept));
        checkReference(concept, ast);
    }

    private void checkIfUsed(ASTNode ancestor) {
        String rootConcept = (ancestor != null)? ancestor.getAbsolutePath().split("\\.")[0] : "";
        conceptList.remove(rootConcept);
    }

    private void checkReference(ASTNode concept, ASTWrapper ast) {
        for (ASTNode.Reference reference : concept.getReferences())
            checkIfUsed(ast.searchNode(reference.getNode(), concept));
    }

    public void finish() {
        for (String concept : conceptList.keySet())
            errors.add(new UnusedConceptError(concept, conceptList.get(concept)));
    }
}
