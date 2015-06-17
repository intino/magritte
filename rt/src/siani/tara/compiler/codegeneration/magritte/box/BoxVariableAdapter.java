package siani.tara.compiler.codegeneration.magritte.box;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import siani.tara.compiler.codegeneration.magritte.NameFormatter;
import siani.tara.compiler.model.*;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.VariableReference;
import siani.tara.semantic.model.Primitives;
import siani.tara.semantic.model.Tag;

import java.util.AbstractMap.SimpleEntry;
import java.util.*;
import java.util.stream.Collectors;

import static siani.tara.compiler.codegeneration.magritte.TemplateTags.*;

public class BoxVariableAdapter implements Adapter<Variable> {

	private final Map<String, List<SimpleEntry<String, String>>> metrics;
	private final int level;
	private static Map<NodeContainer, Integer> nativeIndex = new HashMap<>();

	public BoxVariableAdapter(Map<String, List<SimpleEntry<String, String>>> metrics, int level) {
		this.metrics = metrics;
		this.level = level;
	}

	@Override
	public void execute(Frame frame, Variable variable, FrameContext<Variable> context) {
		if (variable.getDefaultValues().isEmpty() || variable.getDefaultValues().get(0) instanceof EmptyNode || variable.isInherited())
			return;
		if (variable.getContainer() instanceof FacetTarget) {
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
		if (variable.getType().equals(Primitives.MEASURE)) asMeasure(frame, variable);
	}


	private String buildName(Variable parameter) {
		if (parameter.getContainer() instanceof Facet)
			return ((Node) (parameter.getContainer().getContainer())).getName() + "+" + parameter.getName();
		else return parameter.getName();
	}


	protected String[] getTypes(Variable variable) {
		List<String> list = new ArrayList<>();
		list.add(variable.getClass().getSimpleName());
		list.add(VARIABLE);
		if (variable instanceof VariableReference) list.add("reference");
		list.add(variable.getType());
		if (variable.isTerminal()) list.add(TERMINAL);
		if (variable.isMultiple()) list.add(MULTIPLE);
		list.addAll(variable.getFlags().stream().map(Tag::name).collect(Collectors.toList()));
		return list.toArray(new String[list.size()]);
	}

	protected void addVariableValue(Frame frame, final Variable variable) {
		Object[] values;
		Collection<Object> defaultValues = variable.getDefaultValues();
		if (defaultValues.iterator().next() instanceof Node)
			if (defaultValues.iterator().next() instanceof EmptyNode)
				return;
			else values = collectQualifiedNames(defaultValues);
		else
			values = variable.getType().equalsIgnoreCase(Primitives.NATIVE) ? buildNativeReference(variable) : format(defaultValues);
		frame.addFrame(VARIABLE_VALUE, values);
	}

	private Object[] buildNativeReference(Variable variable) {
		final String rootContainer = getRootContainer(variable);
		return new Object[]{cleanQn(rootContainer +
			(variable.getContainer() instanceof Node && !rootContainer.equals(((Node) variable.getContainer()).getName()) ?
				"_" + ((Node) variable.getContainer()).getName() : "") + "_" + variable.getName())};
	}

	public static String cleanQn(String qualifiedName) {
		return qualifiedName.replace(Node.ANNONYMOUS, "").replace("[", "").replace("]", "");
	}

	private String getRootContainer(Variable variable) {
		NodeContainer container = variable.getContainer();
		while (!(container.getContainer() instanceof Model)) container = container.getContainer();
		return ((Node) container).getName();
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
		List<String> nodeNames = defaultValues.stream().map(value -> ((Node) value).getQualifiedName()).collect(Collectors.toList());
		return nodeNames.toArray(new Object[nodeNames.size()]);
	}

	private void createTargetVarFrame(Frame frame, final Variable variable) {
		FacetTarget container = (FacetTarget) variable.getContainer();
		frame.addTypes(getFacetTypes(variable));
		frame.addFrame(NAME, (variable.isFinal() ? NameFormatter.cleanNativeReference(variable.getContainer().getQualifiedName()) : "") + variable.getName());
		if (variable.getType().equals(Primitives.MEASURE)) asMeasure(frame, variable);
		frame.addFrame(TARGET, container.getTarget());
		frame.addFrame(NODE, container.getContainer().getQualifiedName());
	}

	private void asMeasure(Frame frame, Variable variable) {
		frame.addFrame(EXTENSION_TYPE, variable.getContract());
		if (variable.getContract() != null)
			frame.addFrame(EXTENSION_VALUE, resolveMetric(variable.getDefaultExtension()));
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
