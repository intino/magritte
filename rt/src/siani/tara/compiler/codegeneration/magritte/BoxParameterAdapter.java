package siani.tara.compiler.codegeneration.magritte;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import siani.tara.compiler.model.*;
import siani.tara.semantic.model.Tag;

import java.util.AbstractMap.SimpleEntry;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static siani.tara.compiler.codegeneration.magritte.TemplateTags.*;

public class BoxParameterAdapter implements Adapter<Parameter> {

	private final Map<String, List<SimpleEntry<String, String>>> metrics;

	public BoxParameterAdapter(Map<String, List<SimpleEntry<String, String>>> metrics) {
		this.metrics = metrics;
	}

	@Override
	public void execute(Frame frame, Parameter parameter, FrameContext context) {
		final List<String> types = getTypes(parameter);
		frame.addTypes(types.toArray(new String[types.size()]));
		frame.addFrame(NAME, buildName(parameter));
		frame.addFrame(MULTIPLE, parameter.isMultiple());
		if (isTerminal(parameter))
			frame.addFrame(TERMINAL, TERMINAL_KEY);
		if (parameter.getInferredType().equals(Primitives.MEASURE)) {
			frame.addFrame(EXTENSION_TYPE, parameter.getContract());
			if (parameter.getContract() != null)
				frame.addFrame(EXTENSION_VALUE, resolveMetric(parameter.getContract()));
		}
		addParameterValue(frame, parameter);
	}

	private String buildName(Parameter parameter) {
		if (parameter.getOwner() instanceof Facet)
			return (((Facet) parameter.getOwner()).getType()) + ":" + parameter.getName();
		else return parameter.getName();
	}

	private boolean isTerminal(Parameter parameter) {
		return parameter.getAnnotations().stream().filter(a -> Tag.TERMINAL.name().equalsIgnoreCase(a)).findFirst().isPresent();
	}

	protected void addParameterValue(Frame frame, final Parameter parameter) {
		Collection<Object> values = prepareValues(parameter, parameter.getValues());
		for (Object value : values) frame.addFrame(VARIABLE_VALUE, value);
	}

	private Collection<Object> prepareValues(Parameter parameter, List<Object> parameterValues) {
		List<Object> values;
		final Object first = parameterValues.iterator().next();
		if (first instanceof Node)
			if (first instanceof EmptyNode) values = Collections.emptyList();
			else values = collectQualifiedNames(parameterValues);
		else if (Primitives.NATIVE.equals(parameter.getInferredType())) values = createNativeReference(parameter);
		else if ("word".equals(parameter.getInferredType())) values = createWordReference(parameter);
		else values = format(parameterValues);
		return values;
	}

	private List<Object> createWordReference(Parameter parameter) {
		final List<String> allowedValues = parameter.getAllowedValues();
		return parameter.getValues().stream().map(v -> allowedValues.indexOf(v.toString())).collect(toList());
	}

	private List<Object> createNativeReference(Parameter parameter) {
		final String qualifiedName = parameter.getOwner().getQualifiedName();
		return Collections.singletonList(NameFormatter.createNativeReference(qualifiedName, parameter.getName()) + ".class");
	}


	private List<Object> format(List<Object> parameterValues) {
		return parameterValues.stream().map(p -> isMultiLineString(p) ? format((String) p) : p).collect(toList());
	}

	private boolean isMultiLineString(Object p) {
		return p instanceof String && ((String) p).contains("\n");
	}

	private Object format(String value) {
		return value.replace("\n", '"' + " + \"");
	}


	private List<Object> collectQualifiedNames(List<Object> defaultValues) {
		return defaultValues.stream().map(v -> ((Node) v).getQualifiedName()).collect(toList());
	}

	protected List<String> getTypes(Parameter parameter) {
		List<String> list = new ArrayList<>();
		list.add(parameter.getClass().getSimpleName());
		list.add(VARIABLE);
		list.add(parameter.getInferredType());
		list.addAll(parameter.getAnnotations());
		return list;
	}

	protected String resolveMetric(String metric) {
		for (Map.Entry<String, List<SimpleEntry<String, String>>> metrics : this.metrics.entrySet())
			for (SimpleEntry<String, String> metricValue : metrics.getValue())
				if (metricValue.getValue().equals(metric)) return metricValue.getKey();
		return "";
	}
}
