package siani.tara.compiler.semantic;


import siani.tara.compiler.core.errorcollection.SemanticException;
import siani.tara.compiler.core.errorcollection.semantic.SemanticError;
import siani.tara.compiler.core.errorcollection.semantic.SemanticErrorList;
import siani.tara.lang.DeclaredNode;
import siani.tara.lang.Model;
import siani.tara.lang.Node;
import siani.tara.lang.NodeTree;

import java.util.Collection;

public class SemanticAnalyzer {
	private Model model;
	private SemanticErrorList errors = new SemanticErrorList();


	public SemanticAnalyzer(Model model) {
		this.model = model;
	}

	public void analyze() throws SemanticException {
		startDuplicatesAnalysis(model.getTreeModel());
		if (!errors.isEmpty()) throwError();
		startAnnotationsAnalysis(model.getNodeTable().values());
		startUsageAnalysis();
		if (!errors.isEmpty()) throwError();
	}

	private void startUsageAnalysis() throws SemanticException {
		UsageAnalyzer usageAnalyzer = new UsageAnalyzer(model, errors);
		usageAnalyzer.checkRootExistence(model.getNodeTable().values());
		if (!errors.isEmpty()) throwError();
		usageAnalyzer.checkUsage();

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