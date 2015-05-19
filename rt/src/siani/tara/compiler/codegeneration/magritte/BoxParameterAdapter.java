package siani.tara.compiler.codegeneration.magritte;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import siani.tara.compiler.model.*;
import siani.tara.semantic.model.Tag;

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
		for (String annotation : parameter.getAnnotations())
			if (Tag.TERMINAL.name().equalsIgnoreCase(annotation)) return true;
		return false;
	}

	protected void addParameterValue(Frame frame, final Parameter parameter) {
		Collection<Object> values = prepareValues(parameter, parameter.getValues());
		frame.addFrame(VARIABLE_VALUE, values);
	}

	private Collection<Object> prepareValues(Parameter parameter, List<Object> parameterValues) {
		List<Object> values;
		final Object first = parameterValues.iterator().next();
		if (first instanceof Node)
			if (first instanceof EmptyNode)
				values = Collections.emptyList();
			else values = collectQualifiedNames(parameterValues);
		else if (Primitives.NATIVE.equals(parameter.getInferredType())) values = createNativeReference(parameter);
		else if ("word".equals(parameter.getInferredType())) values = createWordReference(parameter);
		else values = format(parameterValues);
		return values;
	}

	private List<Object> createWordReference(Parameter parameter) {
		List<Object> indexs = new ArrayList<>();
		final List<String> allowedValues = parameter.getAllowedValues();
		for (Object value : parameter.getValues())
			indexs.add(allowedValues.indexOf(value.toString()));
		return indexs;
	}

	private List<Object> createNativeReference(Parameter parameter) {
		final String qualifiedName = parameter.getOwner().getQualifiedName();
		final String s = NameFormatter.createNativeReference(qualifiedName, parameter.getName()) + ".class";
		return Collections.singletonList((Object) s);
	}


	private List<Object> format(List<Object> parameterValues) {
		ArrayList<Object> objects = new ArrayList<>();
		for (Object value : parameterValues)
			objects.add(value instanceof String && ((String) value).contains("\n") ? formatText((String) value) : value);
		return objects;
	}

	private Object formatText(String value) {
		return value.replace("\n", '"' + " + \"");
	}


	private List<Object> collectQualifiedNames(List<Object> defaultValues) {
		List<Object> nodeNames = new ArrayList<>();
		for (Object value : defaultValues) nodeNames.add(((Node) value).getQualifiedName());
		return nodeNames;
	}

	protected List<String> getTypes(Parameter parameter) {
		List<String> list = new ArrayList<>();
		list.add(parameter.getClass().getSimpleName());
		list.add(VARIABLE);
		list.add(parameter.getInferredType());
		Collections.addAll(list, parameter.getAnnotations());
		return list;
	}

	protected String resolveMetric(String metric) {
		for (Map.Entry<String, List<SimpleEntry<String, String>>> metrics : this.metrics.entrySet())
			for (SimpleEntry<String, String> metricValue : metrics.getValue())
				if (metricValue.getValue().equals(metric)) return metricValue.getKey();
		return "";
	}
}
