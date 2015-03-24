package siani.tara.compiler.codegeneration.magritte;

import org.siani.itrules.framebuilder.Adapter;
import org.siani.itrules.framebuilder.BuilderContext;
import org.siani.itrules.model.Frame;
import siani.tara.compiler.model.*;
import siani.tara.compiler.model.impl.VariableReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static siani.tara.compiler.codegeneration.magritte.TemplateTags.*;

public class BoxVariableAdapter implements Adapter<Variable> {

	public BoxVariableAdapter() {
	}

	@Override
	public void adapt(Frame frame, Variable variable, BuilderContext context) {
		if (variable.getDefaultValues().isEmpty()) return;
		if (variable.getContainer() instanceof FacetTarget) {
			createTargetVarFrame(frame, variable);
			addVariableValue(frame, variable);
			return;
		}
		frame.add(getTypes(variable));
		fill(frame, variable);
		addVariableValue(frame, variable);
	}

	protected void fill(Frame frame, Variable variable) {
		frame.addFrame(NAME, variable.getName());
		if (variable.isTerminal())
			frame.addFrame(TERMINAL, "!");
		if (variable.getType().equals(Primitives.MEASURE)) asMeasure(frame, variable);
	}

	protected String[] getTypes(Variable variable) {
		List<String> list = new ArrayList<>();
		list.add(variable.getClass().getSimpleName());
		list.add(VARIABLE);
		if (variable instanceof VariableReference) list.add("reference");
		list.add(variable.getType());
		if (variable.isTerminal()) list.add(TERMINAL);
		if (variable.isMultiple()) list.add(MULTIPLE);
		for (Annotation annotation : variable.getAnnotations()) list.add(annotation.getName());
		return list.toArray(new String[list.size()]);
	}

	protected void addVariableValue(Frame frame, final Variable variable) {
		Object[] values;
		Collection<Object> defaultValues = variable.getDefaultValues();
		if (defaultValues.iterator().next() instanceof Node)
			if (defaultValues.iterator().next() instanceof EmptyNode) values = new Object[]{"(Node) null"};
			else values = collectQualifiedNames(defaultValues);
		else values = format(defaultValues);
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

	private void createTargetVarFrame(Frame frame, final Variable variable) {
		FacetTarget container = (FacetTarget) variable.getContainer();
		frame.add(getFacetTypes(variable));
		frame.addFrame(NAME, variable.getName());
		if (variable.getType().equals(Primitives.MEASURE)) asMeasure(frame, variable);
		frame.addFrame(TARGET, container.getTarget());
		frame.addFrame(NODE, container.getContainer().getQualifiedName());
	}

	private void asMeasure(Frame frame, Variable variable) {
		frame.addFrame(EXTENSION_TYPE, variable.getExtension());
		if (variable.getExtension() != null)
			frame.addFrame(EXTENSION_VALUE, resolveMetric(variable.getDefaultExtension()));
	}

	private String[] getFacetTypes(Variable variable) {
		List<String> types = new ArrayList<>();
		Collections.addAll(types, getTypes(variable));
		types.add(TARGET);
		return types.toArray(new String[types.size()]);
	}

	private String resolveMetric(String metric) {
//		//TODO return correct reference to metric from the metricValue
//		Map<String, List<SimpleEntry<String, String>>> metrics = model.getMetrics();
//		for (Map.Entry<String, List<SimpleEntry<String, String>>> stringListEntry : metrics.entrySet())
//			for (SimpleEntry<String, String> metricValue : stringListEntry.getValue())
//				if (metricValue.getValue().equals(metric)) return metricValue.getKey();
		return "";
	}
}
