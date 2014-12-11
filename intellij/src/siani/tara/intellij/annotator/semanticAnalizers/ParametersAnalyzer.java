package siani.tara.intellij.annotator.semanticAnalizers;

import siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.Parameters;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.intellij.project.module.ModuleProvider;
import siani.tara.lang.Node;
import siani.tara.lang.NodeObject;
import siani.tara.lang.Variable;
import siani.tara.lang.Word;

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
		boolean system = ModuleConfiguration.getInstance(ModuleProvider.getModuleOfFile(parameters.getContainingFile())).isTerminal();
		if (isLinkConcept(concept)) return;
		NodeObject object = node.getObject();
		List<Variable> variables = node.getObject().getVariables();
		int minimum = collectMinimumNumberOfParameter(variables, system);
		if (parameters.getParameters().length < minimum)
			results.put(parameters, new AnnotateAndFix(ERROR, "parameters missed: " + variablesToString(object)));
	}

	private int collectMinimumNumberOfParameter(List<Variable> variables, boolean terminal) {
		int result = variables.size();
		for (Variable variable : variables)
			if ((variable.getDefaultValues() != null && variable.getDefaultValues().length > 0) || (!terminal && variable.isTerminal()) || (variable instanceof Word))
				result--;
		return result;
	}

	private String variablesToString(NodeObject node) {
		StringBuilder builder = new StringBuilder();
		for (Variable variable : node.getVariables()) builder.append(", ").append(variable.toString());
		return builder.toString().substring(2);
	}

	private boolean isLinkConcept(Concept concept) {
		return concept.getName() == null && concept.getBody() == null;
	}
}



