package siani.tara.compiler.codegeneration.magritte;

import org.siani.itrules.framebuilder.Adapter;
import org.siani.itrules.framebuilder.BuilderContext;
import org.siani.itrules.model.Frame;
import siani.tara.compiler.model.*;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.VariableReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static siani.tara.compiler.codegeneration.magritte.TemplateTags.*;

public class BoxVariableAdapter implements Adapter<Variable> {
	private final String project;
	private final Model boxModel;
	private final boolean terminal;

	public BoxVariableAdapter(String project, Model boxModel, boolean terminal) {
		this.project = project;
		this.boxModel = boxModel;
		this.terminal = terminal;
	}

	@Override
	public void adapt(Frame frame, Variable variable, BuilderContext context) {
		if (variable.getDefaultValues().isEmpty()) return;
		frame.add(getTypes(variable));
		fill(frame, variable);
		addVariableValue(frame, variable);
	}

	protected void fill(Frame frame, Variable variable) {
		frame.addFrame(NAME, variable.getName());
		frame.addFrame(TYPE, getType(variable));
		if (variable.getType().equals("word"))
			frame.addFrame(WORDS, (variable.getAllowedValues().toArray(new String[variable.getAllowedValues().size()])));
		else if (variable.getType().equals(Primitives.MEASURE)) {
			frame.addFrame(EXTENSION_TYPE, variable.getExtension());
			if (variable.getExtension() != null)
				frame.addFrame(EXTENSION_VALUE, resolveMetric(variable.getDefaultExtension()));
		}
	}

	private String getType(Variable variable) {
		if (variable.getType().equalsIgnoreCase(Primitives.NATURAL)) return Primitives.INTEGER;
		else return variable.getType();
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
			if (defaultValues.iterator().next() instanceof EmptyNode) values = new Object[]{"null"};
			else values = collectQualifiedNames(defaultValues);
		else values = defaultValues.toArray(new Object[defaultValues.size()]);
		frame.addFrame(VARIABLE_VALUE, values);
	}

	private Object[] collectQualifiedNames(Collection<Object> defaultValues) {
		Object[] values;
		List<String> nodeNames = new ArrayList<>();
		for (Object value : defaultValues) nodeNames.add(((Node) value).getQualifiedName());
		values = nodeNames.toArray(new Object[nodeNames.size()]);
		return values;
	}

//
//	protected Frame createTargetVarFrame(final String node, final String target, final Variable variable) {
//		return new Frame(getFacetTypes(variable)) {
//			{
//				addFrame(NAME, variable.getName());
//				addFrame(TYPE, getTargetVarTypes());
//				if (variable instanceof Word)
//					addFrame(WORDS, ((Word) variable).getWordTypes().toArray(new String[((Word) variable).getWordTypes().size()]));
//				else if (variable.getType().equals(Primitives.MEASURE)) {
//					addFrame(EXTENSION_TYPE, ((Attribute) variable).getMeasureType());
//					if (((Attribute) variable).getMeasureValue() != null)
//						addFrame(MEASURE_VALUE, resolveMetric(((Attribute) variable).getMeasureValue()));
//				}
//				addFrame(TARGET, target);
//				addFrame(NODE, node);
//			}
//
//			private String[] getTargetVarTypes() {
//				List<String> types = new ArrayList<>();
//				if (variable.getType().equals(NATURAL)) types.add(INTEGER);
//				else types.add(variable.getType());
//				return types.toArray(new String[types.size()]);
//			}
//		};
//	}

	//
	protected String resolveMetric(String metric) {
//		//TODO return correct reference to metric from the metricValue
//		Map<String, List<SimpleEntry<String, String>>> metrics = model.getMetrics();
//		for (Map.Entry<String, List<SimpleEntry<String, String>>> stringListEntry : metrics.entrySet())
//			for (SimpleEntry<String, String> metricValue : stringListEntry.getValue())
//				if (metricValue.getValue().equals(metric)) return metricValue.getKey();
		return "";
	}
}
