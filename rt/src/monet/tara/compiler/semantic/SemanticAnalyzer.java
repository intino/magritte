package monet.tara.compiler.semantic;


import monet.tara.compiler.core.SourceUnit;
import monet.tara.compiler.core.ast.AST;
import monet.tara.compiler.core.ast.ASTNode;
import monet.tara.compiler.core.errorcollection.SemanticException;
import monet.tara.compiler.core.errorcollection.semantic.SemanticError;
import monet.tara.compiler.core.errorcollection.semantic.SemanticErrorList;

import java.util.Collection;

public class SemanticAnalyzer {
	private AST ast;
	private SemanticErrorList errors = new SemanticErrorList();

	private DuplicateDetector detector = new DuplicateDetector(errors);
	private ReferenceVerifier verifier = new ReferenceVerifier(errors);
	private AnnotationChecker checker = new AnnotationChecker(errors);
	private ConceptsUsage useChecker = new ConceptsUsage(errors);

	public SemanticAnalyzer(Collection<SourceUnit> sources) {
		this.ast = mergeAST(sources);
	}

	private AST mergeAST(Collection<SourceUnit> units) {
		AST newAst = new AST();
		for (SourceUnit unit : units) {
			newAst.addAll(unit.getAST().getAstRootNodes());
			newAst.putAllIdentifiers(unit.getAST().getIdentifiers());
			newAst.putAllLookupTable(unit.getAST().getLookUpTable());
		}
		return newAst;
	}

	public void analyze() throws SemanticException {
		startAnalysis(ast.getAstRootNodes());
		if (!errors.isEmpty()) throw new SemanticException(errors.toArray(new SemanticError[errors.size()]));
		startReferenceAnalysis(ast.getAstRootNodes());
		if (!errors.isEmpty()) throw new SemanticException(errors.toArray(new SemanticError[errors.size()]));
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