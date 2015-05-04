package siani.tara.compiler.codegeneration.magritte;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import siani.tara.compiler.model.*;

import java.util.AbstractMap.SimpleEntry;
import java.util.*;

import static siani.tara.compiler.codegeneration.magritte.TemplateTags.*;

public class BoxParameterAdapter implements Adapter<Parameter> {

	private final Map<String, List<SimpleEntry<String, String>>> metrics;

	public BoxParameterAdapter(Map<String, List<SimpleEntry<String, String>>> metrics) {
		this.metrics = metrics;
	}

	@Override
	public void execute(Frame frame, Parameter parameter, FrameContext context) {
		frame.addTypes(getTypes(parameter));
		frame.addFrame(NAME, buildName(parameter));
		if (isTerminal(parameter))
			frame.addFrame(TERMINAL, "!");
		if (parameter.getInferredType().equals(Primitives.MEASURE)) {
			frame.addFrame(EXTENSION_TYPE, parameter.getMetric());
			if (parameter.getMetric() != null)
				frame.addFrame(EXTENSION_VALUE, resolveMetric(parameter.getMetric()));
		}
		addParameterValue(frame, parameter);
	}

	private String buildName(Parameter parameter) {
		if (parameter.getOwner() instanceof Facet)
			return (((Facet) parameter.getOwner()).getType()) + ":" + parameter.getName();
		else return parameter.getName();
	}

	private boolean isTerminal(Parameter parameter) {
		for (String annotation : parameter.getAnnotations()) if ("terminal".equals(annotation)) return true;
		return false;
	}

	protected void addParameterValue(Frame frame, final Parameter parameter) {
		Object[] values;
		Collection<Object> parameterValues = parameter.getValues();
		if (parameterValues.iterator().next() instanceof Node)
			if (parameterValues.iterator().next() instanceof EmptyNode) values = new Object[]{"null"};
			else values = collectQualifiedNames(parameterValues);
		else values = format(parameterValues);
		frame.addFrame(VARIABLE_VALUE, values);
	}

	private Object[] format(Collection<Object> parameterValues) {
		List<Object> objects = new ArrayList<>();
		for (Object value : parameterValues) {
			objects.add(value instanceof String && ((String) value).contains("\n") ? formatText((String) value) : value);
		}
		return objects.toArray(new Object[objects.size()]);
	}

	private Object formatText(String value) {
		return value.replace("\n", '"' + " + \"");
	}


	private Object[] collectQualifiedNames(Collection<Object> defaultValues) {
		Object[] values;
		List<String> nodeNames = new ArrayList<>();
		for (Object value : defaultValues) nodeNames.add(((Node) value).getQualifiedName());
		values = nodeNames.toArray(new Object[nodeNames.size()]);
		return values;
	}

	protected String[] getTypes(Parameter parameter) {
		List<String> list = new ArrayList<>();
		list.add(parameter.getClass().getSimpleName());
		list.add(VARIABLE);
		list.add(parameter.getInferredType());
		Collections.addAll(list, parameter.getAnnotations());
		return list.toArray(new String[list.size()]);
	}

	protected String resolveMetric(String metric) {
		for (Map.Entry<String, List<SimpleEntry<String, String>>> metrics : this.metrics.entrySet())
			for (SimpleEntry<String, String> metricValue : metrics.getValue())
				if (metricValue.getValue().equals(metric)) return metricValue.getKey();
		return "";
	}
}
