package siani.tara.compiler.semantic;


import siani.tara.compiler.core.errorcollection.SemanticException;
import siani.tara.compiler.core.errorcollection.semantic.SemanticError;
import siani.tara.compiler.core.errorcollection.semantic.SemanticErrorList;
import siani.tara.lang.Node;
import siani.tara.lang.NodeTree;
import siani.tara.lang.TreeWrapper;

import java.util.List;

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

	private void startAnalysis(NodeTree concepts) {
		detector.checkDuplicateRoots(concepts);
		checker.checkIfRoot(concepts);
		for (Node concept : concepts)
			conceptAnalysis(concept);
	}

	private void conceptAnalysis(Node concept) {
		detector.checkDuplicates(concept);
		checker.checkAnnotations(concept);
		for (Node child : concept.getInnerNodes())
			conceptAnalysis(child);
	}

	private void startReferenceAnalysis(NodeTree concepts) {
		useChecker.start(concepts);
		referenceAnalysis(concepts);
		useChecker.finish();
	}

	private void referenceAnalysis(List<Node> astNodes) {
		for (Node concept : astNodes) {
			verifier.checkConcept(concept, ast);
			useChecker.checkUsage(concept, ast);
			referenceAnalysis(concept.getInnerNodes());
		}
	}
}