package siani.tara.intellij.annotator.semanticanalizer;

import com.intellij.psi.PsiElement;
import siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.intellij.project.module.ModuleProvider;
import siani.tara.lang.FacetTarget;
import siani.tara.lang.Node;
import siani.tara.lang.Variable;

import java.util.*;

import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;

public class ParametersAnalyzer extends TaraAnalyzer {
	private final Parameters parameters;

	public ParametersAnalyzer(Parameters parameters) {
		this.parameters = parameters;
	}

	@Override
	public void analyze() {
		PsiElement element = TaraPsiImplUtil.getContextOf(parameters);
		List<Variable> variables;
		if (element instanceof FacetApply) {
			variables = findFacetVariables((FacetApply) element);
			if (variables == null) return;
		} else {
			Node node = findMetaConcept((Concept) element);
			if (node == null) return;
			variables = node.getObject().getVariables();
		}
		List<String> compare = compare(collectMinimumParametersNeeded(variables), collectDeclaredParameters(element, variables));
		if (!compare.isEmpty())
			results.put(parameters, new AnnotateAndFix(ERROR, "parameters missed: " + parametersToString(compare)));
	}

	private List<Variable> findFacetVariables(FacetApply facetApply) {
		Node node = findMetaConcept(TaraPsiImplUtil.getConceptContainerOf(facetApply));
		if (node == null) return null;
		for (Map.Entry<String, List<FacetTarget>> entry : node.getObject().getAllowedFacets().entrySet()) {
			if (entry.getKey().equals(facetApply.getFacetName()))
				return entry.getValue().get(0).getVariables();
		}
		return null;
	}

	private List<String> compare(List<String> minimum, List<String> declared) {
		List<String> list = new ArrayList<>();
		for (String parameter : minimum)
			if (!declared.contains(parameter)) list.add(parameter);
		return list;
	}

	private List<String> collectDeclaredParameters(PsiElement element, List<Variable> variables) {
		List<String> collected = new ArrayList<>();
		for (VarInit varInit : getVarInits(element))
			collected.add(varInit.getName());
		collectParametersNames(collected, variables);
		return collected;
	}

	private Collection<? extends VarInit> getVarInits(PsiElement element) {
		if (element instanceof Concept)
			return ((Concept) element).getVarInits();
		else {
			FacetApply apply = (FacetApply) element;
			if (apply.getBody() == null) return Collections.EMPTY_LIST;
			return apply.getBody().getVarInitList();
		}
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

	private List<String> collectMinimumParametersNeeded(List<Variable> variables) {
		boolean terminal = ModuleConfiguration.getInstance(ModuleProvider.getModuleOf(parameters.getContainingFile())).isTerminal();
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



