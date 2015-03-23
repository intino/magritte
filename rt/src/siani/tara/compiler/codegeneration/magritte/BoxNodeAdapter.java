package siani.tara.compiler.codegeneration.magritte;

import org.siani.itrules.framebuilder.Adapter;
import org.siani.itrules.framebuilder.BuilderContext;
import org.siani.itrules.model.Frame;
import siani.tara.compiler.model.Annotation;
import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.Variable;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.compiler.model.impl.NodeReference;

import java.util.List;
import java.util.Map;

import static siani.tara.compiler.codegeneration.magritte.TemplateTags.*;

public class BoxNodeAdapter implements Adapter<Node> {
	private final String project;
	private final Model boxModel;
	private final boolean terminal;
	private final Map<String, List<Long>> keys;

	public BoxNodeAdapter(String project, Model boxModel, boolean terminal, Map<String, List<Long>> keys) {
		this.project = project;
		this.boxModel = boxModel;
		this.terminal = terminal;
		this.keys = keys;
	}

	@Override
	public void adapt(Frame frame, Node node, BuilderContext context) {
		if (node instanceof NodeImpl) {
			addNodeInfo(node, frame);
			addAnnotations(node, frame);
			addVariables(node, frame, context);
			includes(node, frame);
		}
	}

	private void addNodeInfo(Node node, Frame newFrame) {
		List<Long> keys = this.keys.get(node.getQualifiedName());
		newFrame.addFrame(KEY, keys.get(0));
		if (node.getName() != null && !node.isAnonymous())
			newFrame.addFrame(NAME, clean(node.getQualifiedName())); //TODO change for anonymous and inner facet nodes
		if (node.getParent() != null)
			newFrame.addFrame(PARENT, node.getParent().getName());
		if (node.getAddress() != null) newFrame.addFrame(ADDRESS, node.getAddress());
//		if (node.is(Annotation.INTENTION)) addType(node, newFrame);
//		addFacetApplies(node, newFrame);

	}

	protected void addAnnotations(final Node node, Frame frame) {
		if (node.getAnnotations().isEmpty() || node.isCase()) return;
		frame.addFrame(ANNOTATION, new Frame(ANNOTATION) {{
			for (Annotation annotation : node.getAnnotations())
				if (!annotation.isMeta()) addFrame(VALUE, annotation);
		}});
	}


	protected void addVariables(Node node, final Frame frame, BuilderContext context) {
		for (final Variable variable : node.getVariables())
			frame.addFrame("variable", context.build(variable));
	}

	private String clean(String name) {
		return name.replace("[", "").replace("]", "").replaceAll(Node.ANNONYMOUS, "");
	}


	private void includes(Node node, Frame frame) {
		for (Node inner : node.getIncludedNodes())
			if (inner.isAggregated()) addAggregated(frame, inner);
			else addComponent(frame, inner);
	}

	private void addComponent(Frame frame, Node inner) {
		String qn = (inner instanceof NodeReference) ? ((NodeReference) inner).getDestiny().getQualifiedName() : inner.getQualifiedName();
		frame.addFrame(COMPONENT, keys.get(qn).get(0));
	}

	private void addAggregated(Frame frame, Node inner) {
		List<Long> key = inner instanceof NodeReference ?
			keys.get(((NodeReference) inner).getDestiny().getQualifiedName()) :
			keys.get(inner.getQualifiedName());
		if (keys.isEmpty()) return;
		frame.addFrame(INCLUDE, new Frame("aggregated").addFrame("value", key.get(0)));
	}

//	private void addFacetApplies(Node node, Frame newFrame) {
//		for (Facet facet : node.getObject().getFacets()) {
//			if (!facet.isIntention()) continue;
//			Frame facetFrame = new Frame(FACET_APPLY).addFrame(NAME, facet.getName()).addFrame(APPLY, buildFacetPath(node, facet.getName()));
//			newFrame.addFrame(FACET, facetFrame);
//		}
//	}
//
//	private void addType(Node node, Frame newFrame) {
//		Frame typeFrame = new Frame(NODE_TYPE).addFrame(NAME, node.getObject().getType());
//		addFacetTargets(node, typeFrame);
//		newFrame.addFrame(NODE_TYPE, typeFrame);
//	}
//
//	private void addFacetTargets(Node node, Frame typeFrame) {
//		if (node.getObject().getFacetTargets().isEmpty()) return;
//		Frame targetFrame = new Frame(TARGET, node.is(Annotation.INTENTION) ? INTENTION : "");
//		targetFrame.addFrame(TARGET, project.toLowerCase() + DOT + EXTENSIONS + DOT + camelCase(node.getName()) + node.getType() + DOT + CLASS);
//		Inflector inflector = getInflector(model.getLocale());
//		for (FacetTarget target : node.getObject().getFacetTargets())
//			targetFrame.addFrame(TARGET, project.toLowerCase() + DOT + EXTENSIONS + DOT + inflector.plural(node.getType()).toLowerCase() + DOT +
//				inflector.plural(node.getName()).toLowerCase() + DOT + camelCase(target.getDestinyName()) + node.getType() + DOT + CLASS);
//		typeFrame.addFrame(TARGET, targetFrame);
//	}
//
//
//	private String buildFacetPath(Node node, String facet) {
//		Node aNode = node;
//		String path = node.getName() + facet + DOT + CLASS;
//		while (aNode.getContainer() != null) {
//			aNode = aNode.getContainer();
//			path = addToPath(facet, aNode, path);
//		}
//		return path;
//	}
//
//	private String addToPath(String facetName, Node aNode, String path) {
//		boolean faceted = false;
//		for (Facet facet : aNode.getObject().getFacets())
//			if (facet.getName().equals(facetName)) {
//				path = aNode.getName() + facetName + DOT + path;
//				faceted = true;
//			}
//		if (!faceted) path = aNode.getType() + DOT + path;
//		return path;
//	}
//
}
