package siani.tara.compiler.codegeneration;

import org.siani.itrules.Frame;
import org.siani.itrules.formatter.Inflector;
import org.siani.itrules.formatter.InflectorFactory;
import siani.tara.lang.*;

import java.util.*;

import static siani.tara.compiler.codegeneration.InflectorProvider.getInflector;
import static siani.tara.compiler.codegeneration.NameFormatter.buildFileName;
import static siani.tara.compiler.codegeneration.NameFormatter.camelCase;


public class BoxFrameCreator extends FrameCreator {
	private static final String SEPARATOR = ".";
	private Node currentNode;

	public BoxFrameCreator(String project, Model model) {
		super(project, model);
	}

	public Frame create(List<Node> nodes, Collection<String> parentBoxes) {
		Frame frame = new Frame("Box");
		frame.addFrame("name", buildFileName(nodes.get(0).getFile(), model.getName()));
		for (String box : parentBoxes)
			frame.addFrame("dependency", box);
		addMetricImports(frame);
		addFacetImports(nodes, frame);
		boxToFrame(nodes, frame);
		this.currentNode = null;
		return frame;
	}

	private void boxToFrame(List<Node> nodes, Frame frame) {
		for (Node node : nodes) {
			if (!node.is(DeclaredNode.class) || (node.isAnonymous() && node.getContainer() != null)) continue;
			nodeToFrame(this.currentNode = node, frame);
			boxToFrame(node.getInnerNodes(), frame);
		}
	}

	private void nodeToFrame(final Node node, Frame boxFrame) {
		if (node.is(DeclaredNode.class)) {
			List<String> types = getTypes(node);
			final Frame nodeFrame = new Frame(types.toArray(new String[types.size()]));
			boxFrame.addFrame("node", nodeFrame);
			addAnnotations(node, nodeFrame);
			addNodeInfo(node, nodeFrame);
			addAggregated(node, nodeFrame);
			addInnerToNode(node, nodeFrame);
		}
	}

	private void innerToFrame(final Node node, Frame boxFrame) {
		if (node.is(DeclaredNode.class)) {
			List<String> types = getTypes(node);
			types.add("inner");
			final Frame nodeFrame = new Frame(types.toArray(new String[types.size()]));
			boxFrame.addFrame("node", nodeFrame);
			addAnnotations(node, nodeFrame);
			addNodeInfo(node, nodeFrame);
			addAggregated(node, nodeFrame);
			addInnerToInner(node, nodeFrame);
			nodeFrame.addFrame("back", "back");
		}
	}

	private void addInnerToNode(Node node, Frame nodeFrame) {
		for (Node inner : node.getInnerNodes())
			if (inner.is(DeclaredNode.class) && !inner.isSub() && inner.isAnonymous())
				innerToFrame(inner, nodeFrame);
	}

	private void addInnerToInner(Node node, Frame nodeFrame) {
		for (Node inner : node.getInnerNodes())
			if (inner.is(DeclaredNode.class) && !inner.isSub())
				innerToFrame(inner, nodeFrame);
	}

	private void addNodeInfo(Node node, Frame newFrame) {
		if (currentNode != null && node != currentNode) {
			newFrame.addFrame("inner", "");
			if (node.is(Annotation.AGGREGATED)) newFrame.addFrame("relation", "add");
			else newFrame.addFrame("relation", "has");
		}
		NodeObject object = node.getObject();
		if (node.getName() != null && !node.isAnonymous())
			newFrame.addFrame("name", node.getQualifiedName()); //TODO change for anonymous and inner facet nodes
		if (node.getObject().getParent() != null)
			newFrame.addFrame("parent", node.getObject().getParent().getName());
		addType(node, newFrame);
		addFacetApplies(node, newFrame);
		if (object.getAddress() != null) newFrame.addFrame("address", object.getAddress().replace(".", ""));
		addVariables(node, newFrame);
	}

	private void addAggregated(Node node, Frame frame) {
		for (Node inner : node.getInnerNodes())
			if (inner.isAggregated() && !inner.isSub()) frame.addFrame("aggregated", inner.getName());
	}

	private void addFacetApplies(Node node, Frame newFrame) {
		for (Facet facet : node.getObject().getFacets()) {
			Frame facetFrame = new Frame("facetApply").addFrame("name", facet.getName()).addFrame("apply", buildFacetPath(node, facet.getName()));
			newFrame.addFrame("facet", facetFrame);
		}
	}

	private void addType(Node node, Frame newFrame) {
		Frame typeFrame = new Frame("nodeType").addFrame("name", node.getObject().getType());
		addFacetTargets(node, typeFrame);
		newFrame.addFrame("nodeType", typeFrame);
	}

	private void addFacetTargets(Node node, Frame typeFrame) {
		if (node.getObject().getFacetTargets().isEmpty()) return;
		Frame targetFrame = new Frame("target", node.is(Annotation.INTENTION) ? "intention" : "");
		targetFrame.addFrame("target", project + ".extensions." + camelCase(node.getName()) + node.getType() + ".class");
		Inflector inflector = getInflector(model.getLanguage());
		for (FacetTarget target : node.getObject().getFacetTargets())
			targetFrame.addFrame("target", project + ".extensions." + inflector.plural(node.getType()).toLowerCase() + "." +
				inflector.plural(node.getName()).toLowerCase() + "." + camelCase(target.getDestinyName()) + node.getType() + ".class");
		typeFrame.addFrame("target", targetFrame);
	}


	private String buildFacetPath(Node node, String name) {
		Node aNode = node;
		String path = node.getName() + node.getType() + name + ".class";
		while (aNode.getContainer() != null) {
			aNode = aNode.getContainer();
			path = addToPath(name, aNode, path);
		}
		return path;
	}

	private String addToPath(String name, Node aNode, String path) {
		for (Facet facet : aNode.getObject().getFacets())
			if (facet.getName().equals(name))
				path = aNode.getName() + aNode.getType() + name + "." + path;
		return path;
	}


	private void addMetricImports(Frame frame) {
		for (String metric : model.getMetrics().keySet())
			frame.addFrame("importMetric", "import static " + project + SEPARATOR + "metrics" + SEPARATOR + metric + SEPARATOR + "*;");
	}

	private void addFacetImports(List<Node> nodes, Frame frame) {
		Set<String> imports = searchFacets(nodes);
		for (String anImport : imports)
			frame.addFrame("importFacet", "import " + project + SEPARATOR + "extensions" + SEPARATOR + anImport.toLowerCase() + ".*;");
	}

	private Set<String> searchFacets(List<Node> nodes) {
		Set<String> imports = new HashSet<>();
		for (Node node : nodes) {
			if (node.is(LinkNode.class)) continue;
			for (Facet facet : node.getObject().getFacets())
				imports.add(InflectorFactory.getInflector(Locale.getDefault()).plural(facet.getName()));
			imports.addAll(searchFacets(node.getInnerNodes()));
		}
		return imports;
	}
}
