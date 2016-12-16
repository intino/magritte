package tara.compiler.core.operation.model;

import tara.compiler.core.CompilationUnit;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.core.errorcollection.DependencyException;
import tara.compiler.core.errorcollection.message.Message;
import tara.compiler.model.Model;
import tara.compiler.model.NodeImpl;
import tara.lang.model.Metric;
import tara.lang.model.Node;
import tara.lang.model.Parameter;
import tara.lang.model.Variable;
import tara.lang.model.rules.variable.VariableCustomRule;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class MetricResolutionOperation extends ModelOperation {

	private static final Logger LOG = Logger.getGlobal();
	private final CompilationUnit unit;

	public MetricResolutionOperation(CompilationUnit unit) {
		this.unit = unit;
	}

	@Override
	public void call(Model model) throws CompilationFailedException {
		try {
			resolve(model);
		} catch (DependencyException e) {
			LOG.severe("Error during dependency resolution: " + e.getMessage());
			unit.getErrorCollector().addError(Message.create(e, unit.getSourceUnits().get(e.getElement().file())), true);
		}
	}


	public void resolve(Model model) throws DependencyException {
		for (Node node : model.components())
			resolve(node);
	}

	private void resolve(Node node) throws DependencyException {
		if (!(node instanceof NodeImpl)) return;
		resolveMeasures(node.parameters());
		resolveVariableMetrics(node.variables());
		for (Node include : node.components()) resolve(include);
	}

	private void resolveVariableMetrics(List<Variable> variables) throws DependencyException {
		for (Variable variable : variables) {
			if ((variable.rule() instanceof VariableCustomRule) && ((VariableCustomRule) variable.rule()).isMetric() && variable.defaultMetric() != null) {
				final VariableCustomRule rule = (VariableCustomRule) variable.rule();
				final Metric metric = findMetric(rule.getLoadedClass(), variable.defaultMetric());
				if (metric == null) throw new DependencyException("Metric not found", variable);
				variable.values(variable.values().stream().map((Function<Object, Object>) metric::value).collect(Collectors.toList()));
			}
		}
	}

	private void resolveMeasures(List<Parameter> parameters) throws DependencyException {
		for (Parameter parameter : parameters) {
			if ((parameter.rule() instanceof Metric) && parameter.metric() != null) {
				final Metric metric = findMetric(parameter.rule().getClass(), parameter.metric());
				if (metric == null) throw new DependencyException("Metric not found", parameter);
				parameter.values(parameter.values().stream().map((Function<Object, Object>) metric::value).collect(Collectors.toList()));
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
}
