package siani.tara.compiler.codegeneration.magritte;

import org.siani.itrules.framebuilder.Adapter;
import org.siani.itrules.framebuilder.BuilderContext;
import org.siani.itrules.model.Frame;
import siani.tara.compiler.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static siani.tara.compiler.codegeneration.magritte.TemplateTags.*;

public class BoxParameterAdapter implements Adapter<Parameter> {

	@Override
	public void adapt(Frame frame, Parameter parameter, BuilderContext context) {
		frame.add(getTypes(parameter));
		frame.addFrame(NAME, buildNme(parameter));
		if (isTerminal(parameter))
			frame.addFrame(TERMINAL, "!");
		if (parameter.getInferredType().equals(Primitives.MEASURE)) {
			frame.addFrame(EXTENSION_TYPE, parameter.getExtension());
			if (parameter.getExtension() != null)
				frame.addFrame(EXTENSION_VALUE, resolveMetric(parameter.getExtension()));
		}
		addParameterValue(frame, parameter);
	}

	private String buildNme(Parameter parameter) {
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
//		//TODO return correct reference to metric from the metricValue
//		Map<String, List<SimpleEntry<String, String>>> metrics = model.getMetrics();
//		for (Map.Entry<String, List<SimpleEntry<String, String>>> stringListEntry : metrics.entrySet())
//			for (SimpleEntry<String, String> metricValue : stringListEntry.getValue())
//				if (metricValue.getValue().equals(metric)) return metricValue.getKey();
		return "";
	}
}
