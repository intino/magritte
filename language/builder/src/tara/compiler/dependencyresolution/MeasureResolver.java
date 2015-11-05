package tara.compiler.dependencyresolution;

import tara.compiler.core.errorcollection.DependencyException;
import tara.compiler.model.Model;
import tara.compiler.model.NodeImpl;
import tara.lang.model.*;
import tara.lang.model.rules.variable.CustomRule;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class MeasureResolver {

	private final Model model;

	public MeasureResolver(Model model) {
		this.model = model;
	}

	public void resolve() throws DependencyException {
		for (Node node : model.components())
			resolve(node);
	}

	private void resolve(Node node) throws DependencyException {
		if (!(node instanceof NodeImpl)) return;
		resolveMeasures(node.parameters());
		resolveMeasureVariables(node.variables());
		for (Node include : node.components()) resolve(include);
		resolveInFacetTargets(node.facetTargets());
		resolveInFacets(node.facets());
	}

	private void resolveMeasureVariables(List<Variable> variables) throws DependencyException {
		for (Variable variable : variables) {
			if ((variable.rule() instanceof CustomRule) && ((CustomRule) variable.rule()).isMetric() && variable.defaultMetric() != null) {
				final CustomRule rule = (CustomRule) variable.rule();
				final Metric metric = findMetric(rule.getLoadedClass(), variable.defaultMetric());
				if (metric == null) throw new DependencyException("Impossible to load Metric", variable);
				List<Object> result = variable.defaultValues().stream().map(metric::value).collect(Collectors.toList());
				variable.setDefaultValues(result);
			}
		}
	}

	private void resolveMeasures(List<Parameter> parameters) throws DependencyException {
		for (Parameter parameter : parameters) {
			if ((parameter.rule() instanceof CustomRule) && ((CustomRule) parameter.rule()).isMetric() && parameter.metric() != null) {
				final CustomRule rule = (CustomRule) parameter.rule();
				final Metric metric = findMetric(rule.getLoadedClass(), parameter.metric());
				if (metric == null) throw new DependencyException("Impossible to load Metric", parameter);
				List<Object> result = parameter.values().stream().map(metric::value).collect(Collectors.toList());
				parameter.values(result);
			}
		}
	}

	private Metric findMetric(Class<?> aClass, String metric) {
		for (Field field : aClass.getDeclaredFields()) {
			if (field.isEnumConstant() && field.getName().equals(metric))
				try {
					return (Metric) field.get(null);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
		}
		return null;
	}

	private void resolveInFacetTargets(List<? extends FacetTarget> facetTargets) throws DependencyException {
		for (FacetTarget facet : facetTargets) {
			resolveMeasures(facet.parameters());
			resolveMeasureVariables(facet.variables());
			for (Node node : facet.components()) resolve(node);
		}
	}

	private void resolveInFacets(List<? extends Facet> facets) throws DependencyException {
		for (Facet facet : facets) {
			resolveMeasures(facet.parameters());
			resolveMeasureVariables(facet.variables());
			for (Node node : facet.components()) resolve(node);
		}
	}

}
