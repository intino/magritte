package monet.tara.compiler.semantic;


import monet.tara.compiler.core.ast.AST;
import monet.tara.compiler.core.ast.ASTNode;

public class SemanticAnalyzer {
    private AST ast;
    private ErrorData errors = new ErrorData();

    private DuplicateDetector detector = new DuplicateDetector(errors);
    private ReferenceVerifier verifier = new ReferenceVerifier(errors);
    private AnnotationChecker checker = new AnnotationChecker(errors);
    private ConceptsUsage useChecker = new ConceptsUsage(errors);

    public SemanticAnalyzer(AST ast) {
        this.ast = ast;
    }

    // todo Just to make assert in tests
    public ErrorData getErrors() {
        return errors;
    }

    public void analyze() {
        startAnalysis(ast.getAstRootNodes());
        //if (!errors.isEmpty()) throw new SemanticException(errors);

        startReferenceAnalysis(ast.getAstRootNodes());
        //if (!errors.isEmpty()) throw new SemanticException(errors);
    }

    private void startAnalysis(ASTNode[] concepts) {
        detector.checkDuplicateRoots(concepts);
        for (ASTNode concept : concepts)
            conceptAnalysis(concept);
    }

    private void conceptAnalysis(ASTNode concept) {
        detector.checkDuplicates(concept);
        checker.checkAnnotations(concept);
        for (ASTNode child : concept.getChildren())
            conceptAnalysis(child);
    }

    private void startReferenceAnalysis(ASTNode[] concepts) {
        useChecker.start(concepts);
        referenceAnalysis(concepts);
        useChecker.finish();
    }

    private void referenceAnalysis(ASTNode[] concepts) {
        for (ASTNode concept : concepts) {
            verifier.checkConcept(concept, ast);
            useChecker.checkUsage(concept, ast);
            referenceAnalysis(concept.getChildren());
        }
    }
}