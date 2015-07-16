package tara.compiler.codegeneration.magritte.box;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.model.VariableReference;
import tara.language.model.*;

import java.util.AbstractMap.SimpleEntry;
import java.util.*;
import java.util.stream.Collectors;

public class BoxVariableAdapter implements Adapter<Variable>, TemplateTags {

	private final Map<String, List<SimpleEntry<String, String>>> metrics;
	private final int level;

	public BoxVariableAdapter(Map<String, List<SimpleEntry<String, String>>> metrics, int level) {
		this.metrics = metrics;
		this.level = level;
	}

	@Override
	public void execute(Frame frame, Variable variable, FrameContext<Variable> context) {
		if (variable.defaultValues().isEmpty() || variable.defaultValues().get(0) instanceof EmptyNode || variable.isInherited())
			return;
		if (variable.container() instanceof FacetTarget) {
			createTargetVarFrame(frame, variable);
			addVariableValue(frame, variable);
			return;
		}
		frame.addTypes(getTypes(variable));
		fill(frame, variable);
		addVariableValue(frame, variable);
	}

	protected void fill(Frame frame, Variable variable) {
		frame.addFrame(NAME, buildName(variable));
		if (variable.isTerminal() && level == 2) frame.addFrame(TERMINAL, TERMINAL_KEY + TERMINAL_KEY);
		else if (level == 2) frame.addFrame(TERMINAL, TERMINAL_KEY);
		else if (!variable.isTerminalInstance() && level == 1) frame.addFrame(TERMINAL, TERMINAL_KEY);
		frame.addFrame(MULTIPLE, variable.isMultiple());
		if (variable.type().equals(Primitives.MEASURE)) asMeasure(frame, variable);
	}


	private String buildName(Variable parameter) {
		if (parameter.container() instanceof Facet)
			return ((Node) (parameter.container().container())).name() + "+" + parameter.name();
		else return parameter.name();
	}


	protected String[] getTypes(Variable variable) {
		List<String> list = new ArrayList<>();
		list.add(VARIABLE);
		if (variable.defaultValues().get(0) instanceof Primitives.Expression) list.add(Primitives.NATIVE);
		if (variable instanceof VariableReference) list.add(Primitives.REFERENCE);
		list.add(variable.type());
		if (variable.isTerminal()) list.add(TERMINAL);
		if (variable.isMultiple()) list.add(MULTIPLE);
		list.addAll(variable.flags().stream().map(Tag::name).collect(Collectors.toList()));
		return list.toArray(new String[list.size()]);
	}

	protected void addVariableValue(Frame frame, final Variable variable) {
		Object[] values;
		Collection<Object> defaultValues = variable.defaultValues();
		if (defaultValues.iterator().next() instanceof Node)
			if (defaultValues.iterator().next() instanceof EmptyNode)
				return;
			else values = collectQualifiedNames(defaultValues);
		else
			values = defaultValues.iterator().next() instanceof Primitives.Expression ? buildNativeReference(variable) : format(defaultValues);
		frame.addFrame(VARIABLE_VALUE, values);
	}

	private Object[] buildNativeReference(Variable variable) {
		return new Object[]{variable.name() + "_" + variable.getUID()};
	}

	private Object[] format(Collection<Object> parameterValues) {
		List<Object> objects = parameterValues.stream().
			map(value -> value instanceof String && ((String) value).contains("\n") ? formatText((String) value) : value).
			collect(Collectors.toList());
		return objects.toArray(new Object[objects.size()]);
	}

	private Object formatText(String value) {
		return value.replace("\n", '"' + " + \"");
	}

	private Object[] collectQualifiedNames(Collection<Object> defaultValues) {
		List<String> nodeNames = defaultValues.stream().map(value -> ((Node) value).qualifiedName()).collect(Collectors.toList());
		return nodeNames.toArray(new Object[nodeNames.size()]);
	}

	private void createTargetVarFrame(Frame frame, final Variable variable) {
		FacetTarget container = (FacetTarget) variable.container();
		frame.addTypes(getFacetTypes(variable));
		frame.addFrame(NAME, (variable.isFinal() ? NameFormatter.cleanNativeReference(variable.container().qualifiedName()) : "") + variable.name());
		if (variable.type().equals(Primitives.MEASURE)) asMeasure(frame, variable);
		frame.addFrame(TARGET, container.target());
		frame.addFrame(NODE, container.container().qualifiedName());
	}

	private void asMeasure(Frame frame, Variable variable) {
		frame.addFrame(EXTENSION_TYPE, variable.contract());
		if (variable.contract() != null)
			frame.addFrame(EXTENSION_VALUE, resolveMetric(variable.defaultExtension()));
	}

	private String[] getFacetTypes(Variable variable) {
		List<String> types = new ArrayList<>();
		Collections.addAll(types, getTypes(variable));
		types.add(TARGET);
		return types.toArray(new String[types.size()]);
	}

	private String resolveMetric(String metric) {
		for (Map.Entry<String, List<SimpleEntry<String, String>>> metrics : this.metrics.entrySet())
			for (SimpleEntry<String, String> metricValue : metrics.getValue())
				if (metricValue.getValue().equals(metric)) return metricValue.getKey();
		return "";
	}
}
