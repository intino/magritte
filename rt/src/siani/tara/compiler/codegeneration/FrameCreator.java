package siani.tara.compiler.codegeneration;

import org.siani.itrules.Frame;
import siani.tara.lang.*;

import java.util.*;

public class FrameCreator {

	private static final String SEPARATOR = ".";
	private final boolean terminal;
	private Node initNode;

	public FrameCreator(boolean terminal) {
		this.terminal = terminal;
	}

	public Frame createNodeFrame(Node node) {
		this.initNode = node;
		final Frame frame = new Frame("Morph");
		String box = node.getBox();
		frame.addSlot("box", box);
		if (!PathFormatter.composeMorphPackagePath(node.getQualifiedName()).isEmpty())
			frame.addSlot("package", PathFormatter.composeMorphPackagePath(node.getQualifiedName()));
		add(node, frame);
		initNode = null;
		return frame;
	}

	public Frame createBoxFrame(List<Node> nodes, Collection<String> parentBoxes, long buildNumber) {
		Frame frame = new Frame("Box");
		String name = nodes.get(0).getBox().substring(nodes.get(0).getBox().lastIndexOf(SEPARATOR) + 1);
		String box = nodes.get(0).getBox().substring(0, nodes.get(0).getBox().lastIndexOf(SEPARATOR));
		frame.addSlot("name", name).addSlot("box", box).addSlot("buildNumber", buildNumber);
		if (!PathFormatter.composeMorphPackagePath(box).isEmpty())
			frame.addSlot("package", PathFormatter.composeMorphPackagePath(box));
		for (String anImport : parentBoxes)
			frame.addSlot("import", composePath(anImport));
		for (Node node : nodes)
			add(node, frame);
		return frame;
	}

	private void add(final Node node, Frame frame) {
		if (node.is(DeclaredNode.class)) {
			final Frame newFrame = new Frame(getTypes(node));
			frame.addSlot("node", newFrame);
			addAnnotations(node, newFrame);
			addNodeInfo(node, newFrame);
			addInner(node, newFrame);
			if (!node.isSub() && !node.equals(initNode))
				addSubs(node, frame);
		} else if (((LinkNode) node).isReference()) {
			LinkNode linkNode = (LinkNode) node;
			Frame newFrame = new Frame(getReferenceTypes(linkNode));
			newFrame.addSlot("parent", linkNode.getDestinyName());
			newFrame.addSlot("type", linkNode.getDestiny().getObject().getType());
			frame.addSlot("reference", newFrame);
		}
	}

	private void addAnnotations(final Node node, Frame frame) {
		if (node.getObject().getAnnotations().length > 0 || terminal)
			frame.addSlot("annotation", new Frame("Annotation") {{
				for (Annotations.Annotation annotation : node.getObject().getAnnotations())
					addSlot("value", annotation);
				if (terminal)
					addSlot("value", "case");
			}});
	}

	private void addNodeInfo(Node node, Frame newFrame) {
		if (node != initNode)
			newFrame.addSlot("inner", "inner");
		if (node.getObject().getDoc() != null)
			newFrame.addSlot("doc", node.getObject().getDoc());
		if (node.getName() != null && !node.getName().isEmpty())
			newFrame.addSlot("name", node.getName());
		if (node.getObject().getParent() != null)
			newFrame.addSlot("parent", node.getObject().getParent().getName());
		if (node.getObject().getType() != null)
			newFrame.addSlot("nodeType", node.getObject().getType());
		addVariables(node, newFrame);
		addTargets(node, newFrame);
		addFacets(node, newFrame);

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
			Frame varFrame = new Frame(getTypes(variable)) {{
				addSlot("name", variable.getName());
				addSlot("type", variable.getType().equals("Natural") ? "Integer" : variable.getType());
				if (variable instanceof Word)
					addSlot("words", ((Word) variable).getWordTypes().toArray(new String[((Word) variable).getWordTypes().size()]));
			}};
			frame.addSlot("variable", varFrame);
			if (variable.getValues() != null && variable.getValues().length > 0) {
				addVariableValue(varFrame, variable);
			} else if (terminal)
				frame.addSlot("variable", new Frame(getTypes(variable)) {{
					addSlot("name", variable.getName());
					addSlot("variableValue", variable.getDefaultValues()[0]);
				}});
		}
	}

	private void addVariableValue(Frame frame, final Variable variable) {
		if (variable instanceof Word) {
			Word word = (Word) variable;
			for (Object value : word.values)
				frame.addSlot("variableValue", word.indexOf(value.toString()));
		} else {
			final Object value = variable.getType().equals(Primitives.STRING) ? "\"" + variable.getValues()[0].toString() + "\"" :
				variable.getValues()[0];
			Frame innerFrame = new Frame(variable.getType()) {{
				if (value instanceof Date)
					addSlot("value", ((Date) value).getTime());
				else if (variable.getType().equals(Primitives.COORDINATE)) {
					addSlot("value", Primitives.getConverter(Primitives.COORDINATE).convert(value)[0].replace("-", ","));
				} else
					addSlot("value", value);
			}};
			frame.addSlot("variableValue", innerFrame);
		}
	}

	private void addFacets(Node node, Frame newFrame) {
		for (final Facet facet : node.getObject().getFacets())
			newFrame.addSlot("facets", new Frame(getTypes(facet)) {{
				addSlot("name", facet.getName());
			}});
	}

	private void addTargets(Node node, Frame newFrame) {
		for (final FacetTarget target : node.getObject().getFacetTargets())
			newFrame.addSlot("targets", new Frame(getTypes(target)) {{
				addSlot("name", target.getDestinyName());
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
		if (variable.isTerminal()) list.add("terminal");
		if (variable.isList()) list.add("List");
		if (variable.isProperty()) list.add("property");
		return list.toArray(new String[list.size()]);
	}

	private String[] getTypes(Node node) {
		List<String> types = new ArrayList<>();
		NodeObject object = node.getObject();
		types.add(object.getType());
		types.add(node.getClass().getSimpleName());
		types.add(Node.class.getSimpleName());
		for (Annotations.Annotation annotation : object.getAnnotations())
			types.add(annotation.getName());
		return types.toArray(new String[types.size()]);
	}

	private String composePath(String box) {
		String name = box.substring(box.lastIndexOf(SEPARATOR) + 1);
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		String[] parts = name.split(" ");
		String camelName = "";
		for (String part : parts)
			camelName = camelName + properCase(part);

		return box.substring(0, box.lastIndexOf(SEPARATOR)) + SEPARATOR + camelName;
	}

	private String properCase(String part) {
		return part.substring(0, 1).toUpperCase() + part.substring(1).toLowerCase();
	}

	public String[] getReferenceTypes(LinkNode node) {
		List<String> types = new ArrayList<>();
		types.add("reference");
		if (node.isAggregated())
			types.add(Annotations.Annotation.AGGREGATED.getName());
		return types.toArray(new String[types.size()]);
	}
}
