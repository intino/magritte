package siani.tara.compiler.semantic;


import siani.tara.compiler.core.errorcollection.SemanticException;
import siani.tara.compiler.core.errorcollection.semantic.SemanticError;
import siani.tara.compiler.core.errorcollection.semantic.SemanticErrorList;
import siani.tara.lang.DeclaredNode;
import siani.tara.lang.Model;
import siani.tara.lang.NodeTree;

import java.util.List;

public class SemanticAnalyzer {
	private Model model;
	private SemanticErrorList errors = new SemanticErrorList();
	private DuplicateDetector detector = new DuplicateDetector(errors);
	private ReferenceVerifier referenceVerifier = new ReferenceVerifier(errors);
	private AnnotationChecker checker = new AnnotationChecker(errors);
	private ConceptsUsage usageChecker = new ConceptsUsage(errors);

	public SemanticAnalyzer(Model wrapper) {
		this.model = wrapper;
	}

	public void analyze() throws SemanticException {
		startAnalysis(model.getTree());
		if (!errors.isEmpty()) throw new SemanticException(errors.toArray(new SemanticError[errors.size()]));
		startReferenceAnalysis(model.getTree());
		checker.checkIfRoot(model.getTree());
		if (!errors.isEmpty()) throw new SemanticException(errors.toArray(new SemanticError[errors.size()]));

	}

	private void startAnalysis(NodeTree concepts) {
		detector.checkDuplicateRoots(concepts);
		for (DeclaredNode concept : concepts)
			conceptAnalysis(concept);
	}

	private void conceptAnalysis(DeclaredNode concept) {
		detector.checkDuplicates(concept);
		checker.checkAnnotations(concept);
		for (DeclaredNode child : concept.getInnerNodes())
			conceptAnalysis(child);
	}

	private void startReferenceAnalysis(NodeTree concepts) {
		usageChecker.start(concepts);
		referenceAnalysis(concepts);
		usageChecker.finish();
	}

	private void referenceAnalysis(List<DeclaredNode> astNodes) {
		for (DeclaredNode concept : astNodes) {
			referenceVerifier.checkConcept(concept, model);
			usageChecker.checkUsage(concept, model);
			referenceAnalysis(concept.getInnerNodes());
		}
	}
}