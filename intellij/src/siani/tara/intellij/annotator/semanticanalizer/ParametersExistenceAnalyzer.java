package siani.tara.intellij.annotator.semanticanalizer;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.annotator.TaraAnnotator;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.intellij.project.module.ModuleProvider;
import siani.tara.lang.FacetTarget;
import siani.tara.lang.Node;
import siani.tara.lang.Variable;

import java.util.*;

import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;

public class ParametersExistenceAnalyzer extends TaraAnalyzer {


	private final Concept concept;
	private final Node node;
	private final PsiElement element;

	public ParametersExistenceAnalyzer(PsiElement element) {
		this.element = element;
		if (element instanceof Concept)
			this.concept = (Concept) element;
		else this.concept = TaraPsiImplUtil.getConceptContainerOf(element);
		node = findMetaConcept(concept);
	}

	@Override
	public void analyze() {
		if (node == null) return;
		ModuleConfiguration instance = ModuleConfiguration.getInstance(ModuleProvider.getModuleOf(concept.getContainingFile()));
		if (instance == null) return;
		boolean terminal = instance.isTerminal();
		List<Variable> variables = findVariables();
		List<String> compare = compare(collectMinimumNumberOfParameter(variables, terminal), collectDeclaredParameters(element, variables));
		if (!compare.isEmpty())
			results.put(element instanceof Concept ? concept.getSignature() : element, new TaraAnnotator.AnnotateAndFix(ERROR, "parameters missed: " + parametersToString(compare)));
	}

	@NotNull
	private List<Variable> findVariables() {
		List<Variable> variables;
		if (element instanceof FacetApply) {
			variables = findFacetVariables((FacetApply) element);
		} else {
			Node metaConcept = findMetaConcept((Concept) element);
			if (metaConcept == null) return Collections.EMPTY_LIST;
			variables = metaConcept.getObject().getVariables();
		}
		return variables;
	}

	@NotNull
	private List<Variable> findFacetVariables(FacetApply facetApply) {
		Node metaConcept = findMetaConcept(TaraPsiImplUtil.getConceptContainerOf(facetApply));
		if (metaConcept == null) return Collections.EMPTY_LIST;
		for (Map.Entry<String, List<FacetTarget>> entry : metaConcept.getObject().getAllowedFacets().entrySet()) {
			if (entry.getKey().equals(facetApply.getFacetName()))
				return entry.getValue().get(0).getVariables();
		}
		return Collections.EMPTY_LIST;
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
			if ((terminal && variable.isTerminal()) || (terminal && (variable.getDefaultValues() == null || variable.getDefaultValues().length == 0)))
				result.add(variable.getName());
		return result;
	}

	private List<String> collectDeclaredParameters(PsiElement element, List<Variable> variables) {
		List<String> collected = new ArrayList<>();
		for (VarInit varInit : getVarInits(element))
			collected.add(varInit.getName());
		collected.addAll(collectParametersNames(variables));
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

	private List<String> collectParametersNames(List<Variable> variables) {
		List<String> names = new ArrayList<>();
		Parameters parameters = element instanceof Concept ? concept.getParameters() : ((FacetApply) element).getParameters();
		if (parameters == null) return names;
		if (parameters.getParameters().length == 0 || variables.isEmpty()) return names;
		for (Parameter parameter : parameters.getParameters()) {
			names.add(parameter.isExplicit() ?
				((TaraExplicitParameter) parameter).getIdentifier().getText() :
				variables.get(parameter.getIndexInParent()).getName());
		}
		return names;
	}

	private String parametersToString(List<String> parameterList) {
		String params = "";
		for (String p : parameterList) params += ", " + p;
		return params.substring(2);
	}
}
