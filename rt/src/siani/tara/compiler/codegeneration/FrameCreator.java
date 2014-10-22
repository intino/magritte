package siani.tara.compiler.codegeneration;

import org.siani.itrules.Frame;
import siani.tara.lang.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FrameCreator {
	public static Frame create(Model model) {
		Frame frame = new Frame("model");
		frame.addSlot("name", model.getModelName());
		for (Node node : model.getTreeModel())
			add(node, frame);
		return frame;
	}

	public static Frame createBoxFrame(List<Node> nodes) {
		Frame frame = new Frame("Box");
		frame.addSlot("name", nodes.get(0).getBox());
		for (String anImport : nodes.get(0).getImports())
			frame.addSlot("import", anImport);
		for (Node node : nodes)
			add(node, frame);
		return frame;
	}

	private static void add(final Node node, Frame frame) {
		if (node instanceof LinkNode) return;
		final Frame newFrame = new Frame(getTypes(node));
		frame.addSlot("node", newFrame);
		if (node.getObject().getDoc() != null)
			newFrame.addSlot("doc", node.getObject().getDoc());
		if (node.getName() != null && !node.getName().isEmpty())
			newFrame.addSlot("name", node.getName());
		if (node.getObject().getType() != null)
			newFrame.addSlot("nodeType", node.getObject().getType());
		addVariables(node, newFrame);

		addParameters(node, newFrame);
		addTargets(node, newFrame);
		addFacets(node, newFrame);
		addVarInitializations(node, newFrame);

		for (Node inner : node.getInnerNodes())
			add(inner, newFrame);
		for (Node sub : node.getObject().getSubConcepts())
			add(sub, frame);
	}

	private static void addVariables(Node node, final Frame newFrame) {
		for (final Variable variable : node.getObject().getVariables())
			if (variable instanceof Word) {
				final Word word = (Word) variable;
				newFrame.addSlot("Word", new Frame(getTypes(variable)) {{
					addSlot("name", word.getName());
					addSlot("words", word.getWordTypes().toArray(new String[word.getWordTypes().size()]));
				}});
			} else if (variable.getDefaultValues() != null && variable.getDefaultValues().length > 0)
				newFrame.addSlot("set", new Frame(getTypes(variable)) {{
					addSlot("name", variable.getName());
					addSlot("type", variable.getType());
					if (variable.getDefaultValues() != null && variable.getDefaultValues().length > 0)
						newFrame.addSlot("set", new Frame("Set") {{
							addSlot("type", variable.getType());
							addSlot("name", variable.getName());
							addSlot("value", variable.getDefaultValues()[0]);
						}});
				}});
	}

	private static void addVarInitializations(Node node, Frame newFrame) {
		for (final Map.Entry<String, Variable> entry : node.getObject().getVariableInits().entrySet())
			newFrame.addSlot("set", new Frame("Set") {{
				addSlot("Name", entry.getKey());
			}});
	}

	private static void addFacets(Node node, Frame newFrame) {
		for (final Facet facet : node.getObject().getFacets())
			newFrame.addSlot("facets", new Frame(getTypes(facet)) {{
				addSlot("name", facet.getName());
			}});
	}

	private static void addTargets(Node node, Frame newFrame) {
		for (final FacetTarget target : node.getObject().getFacetTargets())
			newFrame.addSlot("targets", new Frame(getTypes(target)) {{
				addSlot("name", target.getDestinyQN());
			}});
	}

	private static void addParameters(Node node, Frame newFrame) {
		for (final Map.Entry<String, Variable> entry : node.getObject().getParameters().entrySet())
			newFrame.addSlot("parameters", new Frame("parameter") {{
				addSlot("name", entry.getKey());
				addSlot("value", entry.getValue());//TODO
			}});
	}

	private static String[] getTypes(Facet facet) {
		List<String> list = new ArrayList<>();
		list.add("facet");
		list.add(facet.getName());
		return list.toArray(new String[list.size()]);
	}

	private static String[] getTypes(FacetTarget facetTarget) {
		List<String> list = new ArrayList<>();
		list.add("facetTarget");
		list.add(facetTarget.getDestinyQN());
		if (facetTarget.isAlways())
			list.add("always");
		return list.toArray(new String[list.size()]);
	}

	private static String[] getTypes(Variable variable) {
		List<String> list = new ArrayList<>();
		list.add(variable.getClass().getSimpleName());
		list.add("variable");
		if (variable.isTerminal()) list.add("terminal");
		if (variable.isList()) list.add("list");
		if (variable.isProperty()) list.add("property");
		return list.toArray(new String[list.size()]);
	}

	private static String[] getTypes(Node node) {
		List<String> types = new ArrayList<>();
		NodeObject object = node.getObject();
		types.add(object.getType());
		types.add("Node");
		for (Annotations.Annotation annotation : object.getAnnotations())
			types.add(annotation.getName());
		return types.toArray(new String[types.size()]);
	}
}
