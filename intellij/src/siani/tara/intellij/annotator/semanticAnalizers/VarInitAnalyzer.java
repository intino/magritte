package siani.tara.intellij.annotator.semanticAnalizers;

import siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix;
import siani.tara.intellij.lang.psi.VarInit;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.lang.Node;
import siani.tara.lang.Reference;
import siani.tara.lang.Variable;
import siani.tara.lang.Word;

import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;
import static siani.tara.lang.Primitives.*;

public class VarInitAnalyzer extends TaraAnalyzer {

	private final VarInit varInit;

	public VarInitAnalyzer(VarInit varInit) {
		super();
		this.varInit = varInit;
	}

	@Override
	public void analyze() {
		Node node = getMetaConcept(TaraPsiImplUtil.getConceptContainerOf(varInit));
		if (node == null) return;
		Variable variable = searchVariable(node);
		if (variable == null) {
			results.put(varInit, new AnnotateAndFix(ERROR, "Variable not found"));
			return;
		}
		String valueType = varInit.getValueType();
		if (!valueType.equalsIgnoreCase(variable.getType())
			&& !(valueType.equals(NATURAL) && variable.getType().equals(INTEGER))
			&& !(valueType.equals(REFERENCE) && (variable instanceof Reference || variable instanceof Word)))
			results.put(varInit, new AnnotateAndFix(ERROR, "Incompatible types. Found " + valueType + ". " + variable.getType() + " expected"));
	}

	private Variable searchVariable(Node node) {
		for (Variable var : node.getObject().getVariables())
			if (var.getName().equals(varInit.getName())) return var;
		return null;
	}

}
