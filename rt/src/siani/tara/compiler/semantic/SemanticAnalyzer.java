package siani.tara.compiler.semantic;


import siani.tara.compiler.core.errorcollection.SemanticException;
import siani.tara.compiler.core.errorcollection.semantic.RequiredConceptNotFoundError;
import siani.tara.compiler.core.errorcollection.semantic.SemanticError;
import siani.tara.compiler.core.errorcollection.semantic.SemanticErrorList;
import siani.tara.lang.DeclaredNode;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.util.ArrayList;
import java.util.List;

import static siani.tara.lang.Annotations.Annotation.*;

public class SemanticAnalyzer {
	private Model model;
	private SemanticErrorList errors = new SemanticErrorList();


	public SemanticAnalyzer(Model model) {
		this.model = model;
	}

	public void analyze() throws SemanticException {
		usageAnalysis();
		if (model.getParentModel() != null)
			parentModelCoherenceAnalysis();
	}

	private void usageAnalysis() throws SemanticException {
		UsageAnalyzer usageAnalyzer = new UsageAnalyzer(model, errors);
		usageAnalyzer.checkNoConceptExistence(model.getNodeTable().values());
		if (!errors.isEmpty()) throwError();
		usageAnalyzer.checkUsage();
	}

	private void parentModelCoherenceAnalysis() throws SemanticException {
		List<DeclaredNode> requiredNodes = collectRequiredNodes(model.getParentModel());
		for (Node node : model.getNodeTable().values())
			checkRequired(requiredNodes, node.getObject().getMetaQN());
		if (requiredNodes.size() > 0) {
			for (DeclaredNode requiredNode : requiredNodes)
				errors.add(new RequiredConceptNotFoundError(requiredNode.getName(), requiredNode));
			throwError();
		}
	}

	private void checkRequired(List<DeclaredNode> requiredNodes, String metaQN) {
		DeclaredNode toDelete = null;
		for (DeclaredNode requiredNode : requiredNodes)
			if (getMetaName(requiredNode).equals(metaQN))
				toDelete = requiredNode;
		if (toDelete != null) requiredNodes.remove(toDelete);
	}

	private String getMetaName(DeclaredNode requiredNode) {
		String qn = requiredNode.getQualifiedName();
		String[] split = qn.split("\\.");
		String name = "";
		for (int i = 3; i < split.length; i++) {
			name += "." + split[i];
		}
		return name.substring(1);
	}

	private List<DeclaredNode> collectRequiredNodes(Model parentModel) {
		List<DeclaredNode> arrayList = new ArrayList();
		for (Node node : parentModel.getNodeTable().values())
			if (node.is(DeclaredNode.class) && node.getObject().is(REQUIRED))
				if ((model.isTerminal() && node.getObject().is(TERMINAL)) || (!model.isTerminal() && !node.getObject().is(TERMINAL)))
					arrayList.add((DeclaredNode) node);
		return arrayList;
	}

	private void throwError() throws SemanticException {
		throw new SemanticException(errors.toArray(new SemanticError[errors.size()]));
	}
}