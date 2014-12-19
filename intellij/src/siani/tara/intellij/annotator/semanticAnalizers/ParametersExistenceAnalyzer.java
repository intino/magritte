package siani.tara.intellij.annotator.semanticAnalizers;

import siani.tara.intellij.annotator.TaraAnnotator;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.VarInit;
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.intellij.project.module.ModuleProvider;
import siani.tara.lang.Node;
import siani.tara.lang.Variable;
import siani.tara.lang.Word;

import java.util.ArrayList;
import java.util.List;

import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;

public class ParametersExistenceAnalyzer extends TaraAnalyzer {


	private final Concept concept;
	private final Node node;

	public ParametersExistenceAnalyzer(Concept concept) {
		this.concept = concept;
		node = getMetaConcept(concept);
	}

	@Override
	public void analyze() {
		if(node == null) return;
		boolean system = ModuleConfiguration.getInstance(ModuleProvider.getModuleOfFile(concept.getContainingFile())).isTerminal();
		List<Variable> variables = node.getObject().getVariables();
		List<String> compare = compare(collectMinimumNumberOfParameter(variables, system), collectDeclaredParameters(concept));
		if (!compare.isEmpty())
			results.put(concept.getSignature(), new TaraAnnotator.AnnotateAndFix(ERROR, "parameters missed: " + parametersToString(compare)));
	}

	private List<String> compare(List<String> minimum, List<String> declared) {
		List<String> list = new ArrayList<>();
		for (String parameter : minimum)
			if (!declared.contains(parameter))
				list.add(parameter);
		return list;
	}

	private List<String> collectMinimumNumberOfParameter(List<Variable> variables, boolean terminal) {
		List<String> result = new ArrayList<>();
		for (Variable variable : variables)
			if (!((variable.getDefaultValues() != null && variable.getDefaultValues().length > 0) || (!terminal && variable.isTerminal()) || (variable instanceof Word)))
				result.add(variable.getName());
		return result;
	}

	private List<String> collectDeclaredParameters(Concept concept) {
		List<String> collected = new ArrayList<>();
		for (VarInit varInit : concept.getVarInits())
			collected.add(varInit.getName());
		return collected;
	}

	private String parametersToString(List<String> parameterList) {
		String params = "";
		for (String p : parameterList) params += ", " + p;
		return params.substring(2);
	}

}
