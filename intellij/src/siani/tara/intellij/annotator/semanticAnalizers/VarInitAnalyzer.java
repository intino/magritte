package siani.tara.intellij.annotator.semanticAnalizers;

import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix;
import siani.tara.intellij.lang.psi.VarInit;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.lang.*;

import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;
import static siani.tara.lang.Primitives.*;

public class VarInitAnalyzer extends TaraAnalyzer {

	private final VarInit varInit;
	private Variable variable;

	public VarInitAnalyzer(VarInit varInit) {
		super();
		this.varInit = varInit;
		Node node = getMetaConcept(TaraPsiImplUtil.getConceptContainerOf(varInit));
		if (node != null) variable = searchVariable(node);
	}

	@Override
	public void analyze() {
		if (variable == null) {
			results.put(varInit, new AnnotateAndFix(ERROR, "Variable not found"));
			return;
		}
		String valueType = varInit.getValueType();
		if (!valueType.equalsIgnoreCase(variable.getType())
			&& !(valueType.equals(NATURAL) && (variable.getType().equals(INTEGER) || variable.getType().equals(DOUBLE)) || variable.getType().equals(MEASURE))
			&& !(valueType.equals(INTEGER) && (variable.getType().equals(DOUBLE) || variable.getType().equals(MEASURE)))
			&& !(valueType.equals(DOUBLE) && (variable.getType().equals(MEASURE)))
			&& !(valueType.equals(REFERENCE) && (variable instanceof Reference || variable instanceof Word)))
			results.put(varInit, new AnnotateAndFix(ERROR, "Incompatible types. Found " + valueType + ". " + variable.getType() + " expected"));
		if (!hasErrors() && variable.getType().equals(Primitives.DOUBLE))
			analyzeAsTuple();

	}

	private void analyzeAsTuple() {
		Integer count;
		if ((count = ((Attribute) variable).count) <= 1) return;
		if (varInit.getValue().getDoubleValueList().size() != count)
			results.put(varInit, new AnnotateAndFix(ERROR, MessageProvider.message("tuple.restriction") + " " + count.toString()));
	}

	private Variable searchVariable(Node node) {
		for (Variable var : node.getObject().getVariables())
			if (var.getName().equals(varInit.getName())) return var;
		return null;
	}


}
