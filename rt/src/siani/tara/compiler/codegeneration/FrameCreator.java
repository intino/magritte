package siani.tara.compiler.codegeneration;

import org.siani.itrules.Frame;
import org.siani.itrules.formatter.Inflector;
import siani.tara.lang.*;

import java.util.*;

import static siani.tara.compiler.codegeneration.InflectorProvider.getInflector;
import static siani.tara.compiler.codegeneration.NameFormatter.*;

public class FrameCreator {

	private static final String SEPARATOR = ".";
	private final Model model;
	private final String projectName;
	private final boolean terminal;
	private Node initNode;

	public FrameCreator(Model model) {
		this.model = model;
		projectName = model.getModelName().substring(0, model.getModelName().indexOf("."));
		terminal = model.isTerminal();

	}

	public Frame createNodeFrame(Node node) {
		this.initNode = node;
		final Frame frame = new Frame("Morph");
		if (!composeMorphPackagePath(node.getQualifiedName()).isEmpty())
			frame.addFrame("package", composeMorphPackagePath(node.getQualifiedName()));
		add(node, frame);
		initNode = null;
		return frame;
	}

	public Frame createBoxFrame(List<Node> nodes, Collection<String> parentBoxes) {
		Frame frame = new Frame("Box");
		frame.addFrame("name", buildFileName(nodes.get(0).getFile(), nodes.get(0).getModelOwner()));
		for (String box : parentBoxes)
			frame.addFrame("import", box);
		for (String metric : model.getMetrics().keySet())
			frame.addFrame("importMetric", "import static " + projectName + SEPARATOR + "metrics" + SEPARATOR + metric + SEPARATOR + "*;");
		for (Node node : nodes)
			add(node, frame);
		return frame;
	}

	private void add(final Node node, Frame frame) {
		if (node.is(DeclaredNode.class)) {
			final Frame newFrame = new Frame(getTypes(node));
			frame.addFrame("node", newFrame);
			addAnnotations(node, newFrame);
			addNodeInfo(node, newFrame);
			addInner(node, newFrame);
			if (!node.isSub() && !node.equals(initNode))
				addSubs(node, frame);
		} else if (((LinkNode) node).isReference()) {
			LinkNode linkNode = (LinkNode) node;
			Frame newFrame = new Frame(getReferenceTypes(linkNode));
			newFrame.addFrame("parent", linkNode.getDestinyName());
			newFrame.addFrame("type", linkNode.getDestiny().getObject().getType());
			frame.addFrame("reference", newFrame);
		}
	}

	private void addAnnotations(final Node node, Frame frame) {
		if (node.getAnnotations().length > 0 || terminal)
			frame.addFrame("annotation", new Frame("Annotation") {{
				for (Annotation annotation : node.getAnnotations())
					if (!annotation.isMeta()) addFrame("value", annotation);
				if (terminal)
					addFrame("value", "case");
			}});
	}

	private void addNodeInfo(Node node, Frame newFrame) {
		if (node != initNode)
			newFrame.addFrame("inner", "static");
		if (node.getObject().getDoc() != null)
			newFrame.addFrame("doc", node.getObject().getDoc());
		if (node.getName() != null && !node.getName().isEmpty())
			newFrame.addFrame("name", node.getName());
		if (node.getObject().getParent() != null)
			newFrame.addFrame("parent", node.getObject().getParent().getName());
		else newFrame.addFrame("parent", "Morph");
		if (node.getObject().getType() != null) {
			Frame typeFrame = new Frame("nodeType");
			typeFrame.addFrame("name", node.getObject().getType());
			addFacetTargets(node, typeFrame);
			newFrame.addFrame("nodeType", typeFrame);
		}
		addVariables(node, newFrame);
		addTargets(node, newFrame);
		addFacets(node, newFrame);

	}

	private void addFacetTargets(Node node, Frame typeFrame) {
		if (node.getObject().getFacetTargets().isEmpty()) return;
		typeFrame.addFrame("target", projectName + ".extensions." + camelCase(node.getName()) + node.getType() + ".class");
		Inflector inflector = getInflector(Locale.getDefault());
		for (FacetTarget target : node.getObject().getFacetTargets()) {
			typeFrame.addFrame("target", projectName + ".extensions." + inflector.plural(node.getType()).toLowerCase() + "." +
				inflector.plural(node.getName()).toLowerCase() + "." + camelCase(target.getDestinyName()) + node.getType() + ".class");
		}
	}

	private void addSubs(Node node, Frame frame) {
		Set<Node> subs = new HashSet<>();
		collectSubs(node.getSubConcepts(), subs);
		for (Node sub : subs)
			add(sub, frame);
	}

	private void collectSubs(Node[] subs, Collection<Node> list) {
		for (Node sub : subs) {
			list.add(sub);
			Node[] innerSubs = sub.getSubConcepts();
			if (innerSubs.length > 0) {
				Collections.addAll(list, innerSubs);
				collectSubs(innerSubs, list);
			}
		}
	}

	private void addInner(Node node, Frame newFrame) {
		for (Node inner : node.getInnerNodes())
			if (!inner.isSub())
				add(inner, newFrame);
	}

	private void addVariables(Node node, final Frame frame) {
		for (final Variable variable : node.getObject().getVariables()) {
			Frame varFrame = createVarFrame(variable);
			frame.addFrame("variable", varFrame);
			if (variable.getValues() != null && variable.getValues().length > 0) {
				addVariableValue(varFrame, variable);
			} else if (terminal)
				frame.addFrame("variable", new Frame(getTypes(variable)) {{
					addFrame("name", variable.getName());
					addFrame("variableValue", variable.getDefaultValues()[0]);
				}});
		}
		for (FacetTarget target : node.getObject().getFacetTargets())
			for (final Variable variable : target.getVariables()) {
				if (variable.getDefaultValues() == null) continue;
				Frame varFrame = createTargetVarFrame(node.getName(), target.getDestinyName(), variable);
				frame.addFrame("variable", varFrame);
				addVariableValue(varFrame, variable);//TODO terminal
			}
	}

	private Frame createVarFrame(final Variable variable) {
		return new Frame(getTypes(variable)) {
			{
				addFrame("name", variable.getName());
				addFrame("type", getType());
				if (variable instanceof Word)
					addFrame("words", ((Word) variable).getWordTypes().toArray(new String[((Word) variable).getWordTypes().size()]));
				else if (variable.getType().equals(Primitives.MEASURE)) {
					addFrame("measureType", ((Attribute) variable).getMeasureType());
					addFrame("measureValue", resolveMetric(((Attribute) variable).getMeasureValue()));
				}
			}

			private String getType() {
				if (variable.getType().equals("Natural")) return "Integer";
				else return variable.getType();
			}
		};
	}

	private String resolveMetric(String measureValue) {
		Map<String, List<Map.Entry<String, String>>> metrics = model.getMetrics();
		return metrics.get("Temperature").get(0).getKey();
	}

	private Frame createTargetVarFrame(final String node, final String target, final Variable variable) {
		return new Frame(getFacetTypes(variable)) {
			{
				addFrame("name", variable.getName());
				addFrame("type", getTargetVarTypes());
				if (variable instanceof Word)
					addFrame("words", ((Word) variable).getWordTypes().toArray(new String[((Word) variable).getWordTypes().size()]));
				else if (variable.getType().equals(Primitives.MEASURE)) {
					addFrame("measureType", ((Attribute) variable).getMeasureType());
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

	private String[] getFacetTypes(Variable variable) {
		List<String> types = new ArrayList<>();
		Collections.addAll(types, getTypes(variable));
		types.add("target");
		return types.toArray(new String[types.size()]);
	}

	private void addVariableValue(Frame frame, final Variable variable) {
		if (variable.getValues() != null && variable.getValues().length != 0)
			if (variable instanceof Word) {
				Word word = (Word) variable;
				for (Object value : word.values)
					frame.addFrame("variableValue", word.indexOf(value.toString()));
			} else {

				final Object value = variable.getType().equals(Primitives.STRING) ? "\"" + variable.getValues()[0].toString() + "\"" :
					variable.getValues()[0];
				Frame innerFrame = new Frame(variable.getType()) {{
					if (value instanceof Date)
						addFrame("value", ((Date) value).getTime());
					else
						addFrame("value", value);
				}};
				frame.addFrame("variableValue", innerFrame);
			}
		if (variable.getDefaultValues() != null && variable.getDefaultValues().length != 0)
			if (variable instanceof Word) {
				Word word = (Word) variable;
				for (Object value : word.values)
					frame.addFrame("defaultValue", word.indexOf(value.toString()));
			} else {

				final Object value = variable.getType().equals(Primitives.STRING) ? "\"" + variable.getDefaultValues()[0].toString() + "\"" :
					variable.getDefaultValues()[0];
				Frame innerFrame = new Frame(variable.getType()) {{
					if (value instanceof Date)
						addFrame("value", ((Date) value).getTime());
					else
						addFrame("value", value);
				}};
				frame.addFrame("defaultValue", innerFrame);
			}
	}

	private void addFacets(Node node, Frame newFrame) {
		for (final Facet facet : node.getObject().getFacets())
			newFrame.addFrame("facets", new Frame(getTypes(facet)) {{
				addFrame("name", facet.getName());
			}});
	}

	private void addTargets(Node node, Frame newFrame) {
		for (final FacetTarget target : node.getObject().getFacetTargets())
			newFrame.addFrame("targets", new Frame(getTypes(target)) {{
				addFrame("name", target.getDestinyName());
			}});
	}

	private String[] getTypes(Facet facet) {
		List<String> list = new ArrayList<>();
		list.add("Facet");
		list.add(facet.getName());
		return list.toArray(new String[list.size()]);
	}

	private String[] getTypes(FacetTarget facetTarget) {
		List<String> list = new ArrayList<>();
		list.add("FacetTarget");
		if (facetTarget.getDestinyQN() != null)
			list.add(facetTarget.getDestinyQN());
		if (facetTarget.isAlways())
			list.add("always");
		return list.toArray(new String[list.size()]);
	}

	private String[] getTypes(Variable variable) {
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

	private String[] getTypes(Node node) {
		List<String> types = new ArrayList<>();
		NodeObject object = node.getObject();
		types.add(object.getType());
		types.add(node.getClass().getSimpleName());
		types.add(Node.class.getSimpleName());
		for (Annotation annotation : node.getAnnotations())
			types.add(annotation.getName().replace("+", "meta"));
		return types.toArray(new String[types.size()]);
	}


	public String[] getReferenceTypes(LinkNode node) {
		List<String> types = new ArrayList<>();
		types.add("reference");
		if (node.isAggregated())
			types.add(Annotation.AGGREGATED.getName());
		return types.toArray(new String[types.size()]);
	}
}
