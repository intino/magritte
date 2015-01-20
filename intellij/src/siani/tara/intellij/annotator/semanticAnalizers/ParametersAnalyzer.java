package siani.tara.intellij.annotator.semanticAnalizers;

import siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.intellij.project.module.ModuleProvider;
import siani.tara.lang.Node;
import siani.tara.lang.Variable;

import java.util.ArrayList;
import java.util.List;

import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;

public class ParametersAnalyzer extends TaraAnalyzer {
	private final Parameters parameters;

	public ParametersAnalyzer(Parameters parameters) {
		this.parameters = parameters;
	}

	@Override
	public void analyze() {
		Concept concept = TaraPsiImplUtil.getConceptContainerOf(parameters);
		Node node = getMetaConcept(concept);
		if (node == null) return;
		boolean terminal = ModuleConfiguration.getInstance(ModuleProvider.getModuleOf(parameters.getContainingFile())).isTerminal();
		List<Variable> variables = node.getObject().getVariables();
		List<String> compare = compare(collectMinimumNumberOfParameter(variables, terminal), collectDeclaredParameters(concept, variables));
		if (!compare.isEmpty())
			results.put(parameters, new AnnotateAndFix(ERROR, "parameters missed: " + parametersToString(compare)));
	}

	private List<String> compare(List<String> minimum, List<String> declared) {
		List<String> list = new ArrayList<>();
		for (String parameter : minimum)
			if (!declared.contains(parameter)) list.add(parameter);
		return list;
	}

	private List<String> collectDeclaredParameters(Concept concept, List<Variable> variables) {
		List<String> collected = new ArrayList<>();
		for (VarInit varInit : concept.getVarInits())
			collected.add(varInit.getName());
		collectParametersNames(collected, variables);
		return collected;
	}

	private void collectParametersNames(List<String> collected, List<Variable> variables) {
		if (parameters.getParameters().length == 0 || variables.isEmpty()) return;
		for (Parameter parameter : parameters.getParameters()) {
			if (parameter.isExplicit())
				collected.add(((TaraExplicitParameter) parameter).getIdentifier().getText());
			else
				collected.add(variables.get(parameter.getIndexInParent()).getName());
		}
	}

	private List<String> collectMinimumNumberOfParameter(List<Variable> variables, boolean terminal) {
		List<String> result = new ArrayList<>();
		for (Variable variable : variables) {
			if (hasDefaultValue(variable) || isTerminalVariable(terminal, variable))
				continue;
			result.add(variable.getName());
		}
		return result;
	}

	private boolean isTerminalVariable(boolean terminal, Variable variable) {
		return (!terminal && variable.isTerminal());
	}

	private boolean hasDefaultValue(Variable variable) {
		return (variable.getDefaultValues() != null && variable.getDefaultValues().length > 0);
	}

	private String parametersToString(List<String> parameterList) {
		String params = "";
		for (String p : parameterList) params += ", " + p;
		return params.substring(2);
	}
}



