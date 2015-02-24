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
	private Map<String, List<Long>> keymap = new LinkedHashMap<>();
	private long count = 1;

	public BoxFrameCreator(String project, Model model) {
		super(project, model);
		createKeyMap(model.getTreeModel());
	}

	private void createKeyMap(NodeTree nodeTable) {
		for (Node node : nodeTable) {
			if (node.is(LinkNode.class)) continue;
			if (!keymap.containsKey(node.getQualifiedName()))
				keymap.put(node.getQualifiedName(), new ArrayList<Long>());
			keymap.get(node.getQualifiedName()).add(count);
			count++;
			createKeyMap(node.getInnerNodes());
			for (FacetTarget facetTarget : node.getObject().getFacetTargets())
				createKeyMap(facetTarget.getInner());
		}
	}

	public Frame create(List<Node> nodes, Collection<String> parentBoxes) {
		Frame frame = new Frame("Box");
		frame.addFrame("name", buildFileName(nodes.get(0).getFile(), model.getName()));
		for (String box : parentBoxes)
			frame.addFrame("dependency", box);
		addMetricImports(frame);
		addFacetImports(nodes, frame);
		boxToFrame(nodes, frame);
		return frame;
	}

	private void boxToFrame(List<Node> nodes, Frame frame) {
		for (Node node : nodes) {
			if (!node.is(DeclaredNode.class)) continue;
			nodeToFrame(node, frame);
			boxToFrame(node.getInnerNodes(), frame);
		}
	}

	private void nodeToFrame(final Node node, Frame boxFrame) {
		if (node.is(DeclaredNode.class)) {
			DeclaredNode declaredNode = (DeclaredNode) node;
			List<String> types = getTypes(declaredNode);
			final Frame nodeFrame = new Frame(types.toArray(new String[types.size()]));
			boxFrame.addFrame("node", nodeFrame);
			addAnnotations(declaredNode, nodeFrame);
			addNodeInfo(declaredNode, nodeFrame);
			addAggregated(declaredNode, nodeFrame);
			addComponents(declaredNode, nodeFrame);
		}
	}

	private void addNodeInfo(DeclaredNode node, Frame newFrame) {
		List<Long> keys = keymap.get(node.getQualifiedName());
		newFrame.addFrame("key", keys.get(0));
		keys.remove(0);
		NodeObject object = node.getObject();
		if (node.getName() != null && !node.isAnonymous())
			newFrame.addFrame("name", node.getQualifiedName()); //TODO change for anonymous and inner facet nodes
		if (node.getObject().getParent() != null)
			newFrame.addFrame("parent", node.getObject().getParent().getName());
		if (node.is(Annotation.INTENTION)) addType(node, newFrame);
		addFacetApplies(node, newFrame);
		if (object.getAddress() != null) newFrame.addFrame("address", object.getAddress().replace(".", ""));
		addVariables(node, newFrame);
	}

	private void addAggregated(Node node, Frame frame) {
		for (Node inner : node.getInnerNodes())
			if (inner.isAggregated() && !inner.isSub()) {
				List<Long> keys = inner.is(LinkNode.class) ?
					keymap.get(((LinkNode) inner).getDestiny().getQualifiedName()) :
					keymap.get(inner.getQualifiedName());
				for (Long key : keys)
					frame.addFrame("aggregated", key);
			}
	}

	private void addComponents(DeclaredNode node, Frame frame) {
		Map<String, List<Long>> extractedNodes = new LinkedHashMap<>();
		for (Node inner : node.getInnerNodes())
			if (!inner.isSub() && !inner.isAggregated()) {
				String qn = (inner.is(LinkNode.class)) ? ((LinkNode) inner).getDestiny().getQualifiedName() : inner.getQualifiedName();
				if (!extractedNodes.containsKey(qn))
					extractedNodes.put(qn, new ArrayList<>(keymap.get(qn)));
				List<Long> keys = extractedNodes.get(qn);
				if (keys.isEmpty()) continue;//TODO
				frame.addFrame("component", keys.get(0));
				keys.remove(0);
			}
	}

	private void addFacetApplies(Node node, Frame newFrame) {
		for (Facet facet : node.getObject().getFacets()) {
			if (!facet.isIntention()) continue;
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
		targetFrame.addFrame("target", project.toLowerCase() + ".extensions." + camelCase(node.getName()) + node.getType() + ".class");
		Inflector inflector = getInflector(model.getLanguage());
		for (FacetTarget target : node.getObject().getFacetTargets())
			targetFrame.addFrame("target", project.toLowerCase() + ".extensions." + inflector.plural(node.getType()).toLowerCase() + "." +
				inflector.plural(node.getName()).toLowerCase() + "." + camelCase(target.getDestinyName()) + node.getType() + ".class");
		typeFrame.addFrame("target", targetFrame);
	}


	private String buildFacetPath(Node node, String facet) {
		Node aNode = node;
		String path = node.getName() + facet + ".class";
		while (aNode.getContainer() != null) {
			aNode = aNode.getContainer();
			path = addToPath(facet, aNode, path);
		}
		return path;
	}

	private String addToPath(String facetName, Node aNode, String path) {
		boolean faceted = false;
		for (Facet facet : aNode.getObject().getFacets())
			if (facet.getName().equals(facetName)) {
				path = aNode.getName() + facetName + "." + path;
				faceted = true;
			}
		if (!faceted) path = aNode.getType() + "." + path;
		return path;
	}


	private void addMetricImports(Frame frame) {
		for (String metric : model.getMetrics().keySet())
			frame.addFrame("importMetric", "import static " + project.toLowerCase() + SEPARATOR + "metrics" + SEPARATOR + metric + SEPARATOR + "*;");
	}

	private void addFacetImports(List<Node> nodes, Frame frame) {
		Set<String> imports = searchFacets(nodes);
		for (String anImport : imports)
			frame.addFrame("importFacet", "import " + project.toLowerCase() + SEPARATOR + "extensions" + SEPARATOR + anImport.toLowerCase() + ".*;");
	}

	private Set<String> searchFacets(List<Node> nodes) {
		Set<String> imports = new HashSet<>();
		for (Node node : nodes) {
			if (node.is(LinkNode.class)) continue;
			for (Facet facet : node.getObject().getFacets()) {
				if (!facet.isIntention()) continue;
				imports.add(InflectorFactory.getInflector(model.getLanguage()).plural(facet.getName()));
			}
			imports.addAll(searchFacets(node.getInnerNodes()));
		}
		return imports;
	}
}
