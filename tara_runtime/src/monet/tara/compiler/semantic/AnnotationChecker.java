package monet.tara.compiler.semantic;

import AST.ASTNode;
import AST.ASTNode.AnnotationType;
import Semantic.ErrorData.Error;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnnotationChecker {

    private ErrorData errors = new ErrorData();
    private List<AnnotationType> annotations = new ArrayList<>();

    public AnnotationChecker(ErrorData errors) {
        this.errors = errors;
    }

    public void checkAnnotations(ASTNode concept) {
        annotations = Arrays.asList(concept.getAnnotations());
        rootAnnotation(concept);
        optionalAnnotation(concept);
        multipleAnnotation(concept);
    }

    private void rootAnnotation(ASTNode concept) {
        if (!concept.isRoot() && annotations.contains(AnnotationType.Root))
            errors.addWrongAnnotation(new Error(AnnotationType.Root.name(), concept));
    }

    private void optionalAnnotation(ASTNode concept) {
        if ((concept.isRoot() || concept.isMorph()) && annotations.contains(AnnotationType.Optional))
            errors.addWrongAnnotation(new Error(AnnotationType.Optional.name(), concept));
    }

    private void multipleAnnotation(ASTNode concept) {
        if ((concept.isRoot() || concept.isMorph()) && annotations.contains(AnnotationType.Multiple))
            errors.addWrongAnnotation(new Error(AnnotationType.Multiple.name(), concept));
    }
}
