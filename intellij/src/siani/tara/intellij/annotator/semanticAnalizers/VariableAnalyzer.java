package siani.tara.intellij.annotator.semanticAnalizers;

import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix;
import siani.tara.intellij.annotator.fix.RemoveAttributeFix;
import siani.tara.intellij.lang.psi.Body;
import siani.tara.intellij.lang.psi.Variable;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;

import java.util.ArrayList;
import java.util.List;

import static siani.tara.intellij.MessageProvider.message;
import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;

public class VariableAnalyzer extends TaraAnalyzer {

	private final Variable variable;

	public VariableAnalyzer(Variable variable) {
		this.variable = variable;
	}

	@Override
	public void analyze() {
		if (isDuplicated())
			results.put(variable,
				new AnnotateAndFix(ERROR, message("duplicate.attribute"), new RemoveAttributeFix(variable)));

	}

	private boolean isDuplicated() {
		return findAttributeDuplicates(variable).length != 1;
	}

	@NotNull
	private Variable[] findAttributeDuplicates(Variable variable) {
		List<Variable> result = new ArrayList<>();
		List<Variable> variables = TaraPsiImplUtil.getVariablesInBody((Body) variable.getParent());
		for (Variable taraVariable : variables)
			if (taraVariable.getName() != null && taraVariable.getName().equals(variable.getName()))
				result.add(taraVariable);
		return result.toArray(new Variable[result.size()]);
	}

}
