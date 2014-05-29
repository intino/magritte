package monet.tara.compiler.semantic;

import monet.tara.lang.AbstractNode;
import monet.tara.lang.AbstractTree;
import monet.tara.lang.Reference;
import monet.tara.lang.TreeWrapper;
import monet.tara.lang.AbstractNode.AnnotationType;
import monet.tara.compiler.core.errorcollection.semantic.SemanticErrorList;
import monet.tara.compiler.core.errorcollection.semantic.UnusedConceptError;

import java.util.*;

public class ConceptsUsage {

    private SemanticErrorList errors = new SemanticErrorList();
    private Map<String, AbstractNode> conceptList = new HashMap<>();

    public ConceptsUsage(SemanticErrorList errors) {
        this.errors = errors;
    }

    public void start(AbstractTree conceptList) {
        for (AbstractNode concept : conceptList)
            addToList(concept);
    }

    private void addToList(AbstractNode concept) {
        List<AnnotationType> annotations = new ArrayList<>(Arrays.asList(concept.getAnnotations()));
        if (!annotations.contains(AnnotationType.ROOT))
            this.conceptList.put(concept.getAbsolutePath(), concept);
    }

    public void checkUsage(AbstractNode concept, TreeWrapper ast) {
        checkIfUsed(concept.getParentConcept());
        checkReference(concept, ast);
    }

    private void checkIfUsed(AbstractNode ancestor) {
        String rootConcept = (ancestor != null)? ancestor.getAbsolutePath().split("\\.")[0] : "";
        conceptList.remove(rootConcept);
    }

    private void checkReference(AbstractNode concept, TreeWrapper ast) {
        for (Reference reference : concept.getReferences())
            checkIfUsed(ast.searchNode(reference.getNode(), concept));
    }

    public void finish() {
        for (String concept : conceptList.keySet())
            errors.add(new UnusedConceptError(concept, conceptList.get(concept)));
    }
}
