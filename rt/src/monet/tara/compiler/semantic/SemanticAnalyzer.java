package monet.tara.compiler.semantic;


import monet.tara.compiler.core.errorcollection.SemanticException;
import monet.tara.compiler.core.errorcollection.semantic.SemanticError;
import monet.tara.compiler.core.errorcollection.semantic.SemanticErrorList;
import monet.tara.lang.AbstractNode;
import monet.tara.lang.AbstractTree;
import monet.tara.lang.TreeWrapper;

public class SemanticAnalyzer {
	private TreeWrapper ast;
	private SemanticErrorList errors = new SemanticErrorList();
	private DuplicateDetector detector = new DuplicateDetector(errors);
	private ReferenceVerifier verifier = new ReferenceVerifier(errors);
	private AnnotationChecker checker = new AnnotationChecker(errors);
	private ConceptsUsage useChecker = new ConceptsUsage(errors);

	public SemanticAnalyzer(TreeWrapper ast) {
		this.ast = ast;
	}

	public void analyze() throws SemanticException {
//		startAnalysis(ast.getTree());
		if (!errors.isEmpty()) throw new SemanticException(errors.toArray(new SemanticError[errors.size()]));
//		startReferenceAnalysis(ast.getTree());
//		if (!errors.isEmpty()) throw new SemanticException(errors.toArray(new SemanticError[errors.size()]));
	}

	private void startAnalysis(AbstractTree concepts) {
		detector.checkDuplicateRoots(concepts);
		checker.checkIfRoot(concepts);
		for (AbstractNode concept : concepts)
			conceptAnalysis(concept);
	}

	private void conceptAnalysis(AbstractNode concept) {
		detector.checkDuplicates(concept);
		checker.checkAnnotations(concept);
		for (AbstractNode child : concept.getInnerConcepts())
			conceptAnalysis(child);
	}

	private void startReferenceAnalysis(AbstractTree concepts) {
		useChecker.start(concepts);
		referenceAnalysis(concepts);
		useChecker.finish();
	}

	private void referenceAnalysis(AbstractTree astNodes) {
		for (AbstractNode concept : astNodes) {
			verifier.checkConcept(concept, ast);
			useChecker.checkUsage(concept, ast);
			referenceAnalysis(concept.getInnerConcepts());
		}
	}
}