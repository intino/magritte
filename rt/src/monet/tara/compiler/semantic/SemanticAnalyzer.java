package monet.tara.compiler.semantic;


import monet.tara.compiler.core.errorcollection.SemanticException;
import monet.tara.compiler.core.errorcollection.semantic.SemanticError;
import monet.tara.compiler.core.errorcollection.semantic.SemanticErrorList;
import monet.tara.lang.AST;
import monet.tara.lang.ASTNode;
import monet.tara.lang.ASTWrapper;

public class SemanticAnalyzer {
	private ASTWrapper ast;
	private SemanticErrorList errors = new SemanticErrorList();
	private DuplicateDetector detector = new DuplicateDetector(errors);
	private ReferenceVerifier verifier = new ReferenceVerifier(errors);
	private AnnotationChecker checker = new AnnotationChecker(errors);
	private ConceptsUsage useChecker = new ConceptsUsage(errors);

	public SemanticAnalyzer(ASTWrapper ast) {
		this.ast = ast;
	}

	public void analyze() throws SemanticException {
//		startAnalysis(ast.getAST());
		if (!errors.isEmpty()) throw new SemanticException(errors.toArray(new SemanticError[errors.size()]));
//		startReferenceAnalysis(ast.getAST());
//		if (!errors.isEmpty()) throw new SemanticException(errors.toArray(new SemanticError[errors.size()]));
	}

	private void startAnalysis(AST concepts) {
		detector.checkDuplicateRoots(concepts);
		checker.checkIfRoot(concepts);
		for (ASTNode concept : concepts)
			conceptAnalysis(concept);
	}

	private void conceptAnalysis(ASTNode concept) {
		detector.checkDuplicates(concept);
		checker.checkAnnotations(concept);
		for (ASTNode child : concept.getInnerConcepts())
			conceptAnalysis(child);
	}

	private void startReferenceAnalysis(AST concepts) {
		useChecker.start(concepts);
		referenceAnalysis(concepts);
		useChecker.finish();
	}

	private void referenceAnalysis(AST astNodes) {
		for (ASTNode concept : astNodes) {
			verifier.checkConcept(concept, ast);
			useChecker.checkUsage(concept, ast);
			referenceAnalysis(concept.getInnerConcepts());
		}
	}
}