package monet.tara.compiler.semantic;


import monet.tara.compiler.core.SourceUnit;
import monet.tara.compiler.core.errorcollection.SemanticException;
import monet.tara.compiler.core.errorcollection.semantic.SemanticError;
import monet.tara.compiler.core.errorcollection.semantic.SemanticErrorList;
import monet.tara.lang.AST;
import monet.tara.lang.ASTNode;
import monet.tara.lang.ASTWrapper;

import java.util.Collection;

public class SemanticAnalyzer {
	private ASTWrapper ast;
	private SemanticErrorList errors = new SemanticErrorList();
	private DuplicateDetector detector = new DuplicateDetector(errors);
	private ReferenceVerifier verifier = new ReferenceVerifier(errors);
	private AnnotationChecker checker = new AnnotationChecker(errors);
	private ConceptsUsage useChecker = new ConceptsUsage(errors);

	public SemanticAnalyzer(Collection<SourceUnit> sources) {
		this.ast = mergeAST(sources);
	}

	private ASTWrapper mergeAST(Collection<SourceUnit> units) {
		ASTWrapper newAst = new ASTWrapper();
		for (SourceUnit unit : units) {
			newAst.addAll(unit.getAST().getAST());
			newAst.putAllIdentifiers(unit.getAST().getIdentifiers());
			newAst.putAllInNodeNameTable(unit.getAST().getNodeNameLookUpTable());
		}
		return newAst;
	}

	public void analyze() throws SemanticException {
		startAnalysis(ast.getAST());
		if (!errors.isEmpty()) throw new SemanticException(errors.toArray(new SemanticError[errors.size()]));
		startReferenceAnalysis(ast.getAST());
		if (!errors.isEmpty()) throw new SemanticException(errors.toArray(new SemanticError[errors.size()]));
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
		for (ASTNode child : concept.getChildren())
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
			referenceAnalysis(concept.getChildren());
		}
	}
}