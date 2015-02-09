package siani.tara.compiler.codegeneration;

import org.siani.itrules.Frame;
import siani.tara.lang.*;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class FrameCreator {

	final Model model;
	final String project;
	final boolean terminal;

	protected FrameCreator(String project, Model model) {
		this.model = model;
		this.project = project;
		terminal = model.isTerminal();
	}

	protected List<String> getTypes(Node node) {
		List<String> types = new ArrayList<>();
		NodeObject object = node.getObject();
		types.add(object.getType());
		types.add(node.getClass().getSimpleName());
		types.add(Node.class.getSimpleName());
		for (Annotation annotation : node.getAnnotations())
			types.add(annotation.getName().replace("+", "meta"));
		return types;
	}

	protected void addAnnotations(final Node node, Frame frame) {
		if (node.getAnnotations().length > 0 || terminal)
			frame.addFrame("annotation", new Frame("Annotation") {{
				for (Annotation annotation : node.getAnnotations())
					if (!annotation.isMeta()) addFrame("value", annotation);
				if (terminal)
					addFrame("value", "case");
			}});
	}

	protected String[] getLinkNodeTypes(LinkNode node) {
		List<String> types = new ArrayList<>();
		types.add("node");
		types.add("reference");
		for (Annotation annotation : node.getAnnotations())
			types.add(annotation.getName());
		return types.toArray(new String[types.size()]);
	}

	protected void addVariables(Node node, final Frame frame) {
		for (final Variable variable : node.getObject().getVariables()) {
			Frame varFrame = createVarFrame(variable);
			frame.addFrame("variable", varFrame);
			if ((variable.getValues() != null && variable.getValues().length > 0) || (variable.getDefaultValues() != null && variable.getDefaultValues().length > 0))
				addVariableValue(varFrame, variable);
		}
		for (FacetTarget target : node.getObject().getFacetTargets())
			for (final Variable variable : target.getVariables()) {
				if (variable.getDefaultValues() == null) continue;
				Frame varFrame = createTargetVarFrame(node.getName(), target.getDestinyName(), variable);
				frame.addFrame("variable", varFrame);
				addVariableValue(varFrame, variable);//TODO terminal
			}
	}

	void addVariableValue(Frame frame, final Variable variable) {
		if (variable.getValues() != null && variable.getValues().length != 0)
			if (variable instanceof Word) {
				Word word = (Word) variable;
				for (Object value : word.values)
					frame.addFrame("variableValue", word.indexOf(value.toString()));
			} else
				frame.addFrame("variableValue", new Frame(variable.getType()).addFrame("value", variable.getValues()[0]));
		else if (variable.getDefaultValues() != null && variable.getDefaultValues().length != 0)
			if (variable instanceof Word) {
				Word word = (Word) variable;
				for (Object value : word.getDefaultValues())
					frame.addFrame("variableValue", word.indexOf(value.toString()));
			} else {
				final Object value = variable.getDefaultValues()[0];
				Frame innerFrame = new Frame(variable.getType()).addFrame("value", value);
				frame.addFrame("variableValue", innerFrame);
			}
	}

	Frame createTargetVarFrame(final String node, final String target, final Variable variable) {
		return new Frame(getFacetTypes(variable)) {
			{
				addFrame("name", variable.getName());
				addFrame("type", getTargetVarTypes());
				if (variable instanceof Word)
					addFrame("words", ((Word) variable).getWordTypes().toArray(new String[((Word) variable).getWordTypes().size()]));
				else if (variable.getType().equals(Primitives.MEASURE)) {
					addFrame("measureType", ((Attribute) variable).getMeasureType());
					if (((Attribute) variable).getMeasureValue() != null)
						addFrame("measureValue", resolveMetric(((Attribute) variable).getMeasureValue()));
				}
				addFrame("target", target);
				addFrame("node", node);
			}

			private String[] getTargetVarTypes() {
				List<String> types = new ArrayList<>();
				if (variable.getType().equals("Natural")) types.add("Integer");
				else types.add(variable.getType());
				return types.toArray(new String[types.size()]);
			}
		};
	}

	String[] getFacetTypes(Variable variable) {
		List<String> types = new ArrayList<>();
		Collections.addAll(types, getTypes(variable));
		types.add("target");
		return types.toArray(new String[types.size()]);
	}

	String[] getTypes(Variable variable) {
		List<String> list = new ArrayList<>();
		list.add(variable.getClass().getSimpleName());
		list.add("Variable");
		list.add(variable.getType());
		if (variable.isTerminal()) list.add("terminal");
		if (variable.isList()) list.add("List");
		if (variable.isProperty()) list.add("property");
		if (variable.isReadOnly()) list.add("readonly");
		return list.toArray(new String[list.size()]);
	}

	Frame createVarFrame(final Variable variable) {
		return new Frame(getTypes(variable)) {
			{
				addFrame("name", variable.getName());
				addFrame("type", getType());
				if (variable instanceof Word)
					addFrame("words", ((Word) variable).getWordTypes().toArray(new String[((Word) variable).getWordTypes().size()]));
				else if (variable.getType().equals(Primitives.MEASURE)) {
					addFrame("measureType", ((Attribute) variable).getMeasureType());
					if (((Attribute) variable).getMeasureValue() != null)
						addFrame("measureValue", resolveMetric(((Attribute) variable).getMeasureValue()));
				}
			}

			private String getType() {
				if (variable.getType().equals("Natural")) return "Integer";
				else return variable.getType();
			}
		};
	}

	String resolveMetric(String metric) {//TODO return correct reference to metric from the metricValue
		Map<String, List<SimpleEntry<String, String>>> metrics = model.getMetrics();
		for (Map.Entry<String, List<SimpleEntry<String, String>>> stringListEntry : metrics.entrySet())
			for (SimpleEntry<String, String> metricValue : stringListEntry.getValue())
				if (metricValue.getValue().equals(metric)) return metricValue.getKey();
		return null;
	}
}
