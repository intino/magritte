package siani.tara.compiler.codegeneration;

import org.siani.itrules.Frame;
import siani.tara.lang.*;

import java.util.*;

import static siani.tara.compiler.codegeneration.InflectorProvider.getInflector;
import static siani.tara.compiler.codegeneration.NameFormatter.*;

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
					addFrame("value", annotation);
				if (terminal)
					addFrame("value", "case");
			}});
	}

	private void addNodeInfo(Node node, Frame newFrame) {
		if (node != initNode)
			newFrame.addFrame("inner", "inner");
		if (node.getObject().getDoc() != null)
			newFrame.addFrame("doc", node.getObject().getDoc());
		if (node.getName() != null && !node.getName().isEmpty())
			newFrame.addFrame("name", node.getName());
		if (node.getObject().getParent() != null)
			newFrame.addFrame("parent", node.getObject().getParent().getName());
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
		typeFrame.addFrame("target", "millener.extensions." + camelCase(node.getName()));
		for (FacetTarget target : node.getObject().getFacetTargets())
			typeFrame.addFrame("target", "millener.extensions." +
				getInflector(Locale.ENGLISH).plural(node.getName()).toLowerCase() + "." + camelCase(target.getDestinyName())+".class");
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
				addFrame("name", variable.getName());
				addFrame("type", variable.getType().equals("Natural") ? "Integer" : variable.getType());
				if (variable instanceof Word)
					addFrame("words", ((Word) variable).getWordTypes().toArray(new String[((Word) variable).getWordTypes().size()]));
			}};
			frame.addFrame("variable", varFrame);
			if (variable.getValues() != null && variable.getValues().length > 0) {
				addVariableValue(varFrame, variable);
			} else if (terminal)
				frame.addFrame("variable", new Frame(getTypes(variable)) {{
					addFrame("name", variable.getName());
					addFrame("variableValue", variable.getDefaultValues()[0]);
				}});
		}
	}

	private void addVariableValue(Frame frame, final Variable variable) {
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
		for (Annotation annotation : node.getAnnotations())
			types.add(annotation.getName());
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
