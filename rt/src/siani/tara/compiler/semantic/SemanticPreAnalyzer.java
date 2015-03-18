package siani.tara.compiler.semantic;


import siani.tara.compiler.core.errorcollection.SemanticException;
import siani.tara.compiler.core.errorcollection.semantic.SemanticError;
import siani.tara.compiler.core.errorcollection.semantic.SemanticErrorList;
import siani.tara.model.DeclaredNode;
import siani.tara.model.Model;
import siani.tara.model.Node;
import siani.tara.model.NodeTree;

import java.util.Collection;

public class SemanticPreAnalyzer {
	private Model model;
	private SemanticErrorList errors = new SemanticErrorList();


	public SemanticPreAnalyzer(Model model) {
		this.model = model;
	}

	public void analyze() throws SemanticException {
		startDuplicatesAnalysis(model.getNodeTree());
		if (!errors.isEmpty()) throwError();
		startAnnotationsAnalysis(model.getNodeTable());
		if (!errors.isEmpty()) throwError();
	}

	private void startAnnotationsAnalysis(Collection<Node> treeModel) {
		AnnotationsAnalyzer analyzer = new AnnotationsAnalyzer(errors);
		for (Node node : treeModel)
			if (node.is(DeclaredNode.class))
				analyzer.checkAnnotations(node);
	}

	private void startDuplicatesAnalysis(NodeTree treeModel) {
		DuplicateDetector detector = new DuplicateDetector(errors);
		for (Node node : treeModel)
			if (node.is(DeclaredNode.class) && !node.getName().isEmpty()) {
				detector.detectDuplicateNode(node, treeModel);
				detector.detectDuplicates(node);
			}
	}

	private void throwError() throws SemanticException {
		throw new SemanticException(errors.toArray(new SemanticError[errors.size()]));
	}
}