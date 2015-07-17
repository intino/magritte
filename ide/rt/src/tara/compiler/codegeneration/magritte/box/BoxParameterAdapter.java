package tara.compiler.codegeneration.magritte.box;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.language.model.*;

import java.util.AbstractMap.SimpleEntry;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class BoxParameterAdapter implements Adapter<Parameter>, TemplateTags {

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
		if (parameter.inferredType().equals(Primitives.MEASURE)) {
			frame.addFrame(EXTENSION_TYPE, parameter.contract());
			if (parameter.contract() != null)
				frame.addFrame(EXTENSION_VALUE, resolveMetric(parameter.contract()));
		}
		addParameterValue(frame, parameter);
	}

	private String buildName(Parameter parameter) {
		if (parameter.container() instanceof Facet)
			return ((Node) (parameter.container().container())).name() + "+" + parameter.name();
		else return parameter.name();
	}

	private boolean isTerminal(Parameter parameter) {
		return parameter.flags().stream().filter(a -> Tag.TERMINAL.name().equalsIgnoreCase(a)).findFirst().isPresent();
	}

	protected void addParameterValue(Frame frame, final Parameter parameter) {
		Collection<Object> values = prepareValues(parameter, parameter.values());
		for (Object value : values) frame.addFrame(VARIABLE_VALUE, value);
	}

	private Collection<Object> prepareValues(Parameter parameter, List<Object> parameterValues) {
		List<Object> values;
		final Object first = parameterValues.iterator().next();
		if (first instanceof Node)
			if (first instanceof EmptyNode) values = Collections.emptyList();
			else values = collectQualifiedNames(parameterValues);
		else if (first instanceof Primitives.Expression)
			values = Collections.singletonList(parameter.name() + "_" + parameter.getUID());
		else if ("word".equals(parameter.inferredType())) values = createWordReference(parameter);
		else values = format(parameterValues);
		return values;
	}

	private List<Object> createWordReference(Parameter parameter) {
		final List<String> allowedValues = parameter.getAllowedValues();
		return parameter.values().stream().map(v -> allowedValues.indexOf(v.toString())).collect(toList());
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
		return defaultValues.stream().map(v -> ((Node) v).qualifiedName()).collect(toList());
	}

	protected List<String> getTypes(Parameter parameter) {
		List<String> list = new ArrayList<>();
		list.add(parameter.getClass().getSimpleName());
		list.add(VARIABLE);
		if (parameter.values().get(0) instanceof Primitives.Expression) list.add(Primitives.NATIVE);
		list.add(parameter.inferredType());
		list.addAll(parameter.flags());
		return list;
	}

	protected String resolveMetric(String metric) {
		for (Map.Entry<String, List<SimpleEntry<String, String>>> metrics : this.metrics.entrySet())
			for (SimpleEntry<String, String> metricValue : metrics.getValue())
				if (metricValue.getValue().equals(metric)) return metricValue.getKey();
		return "";
	}
}
