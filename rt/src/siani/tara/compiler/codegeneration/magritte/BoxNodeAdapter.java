package siani.tara.compiler.codegeneration.magritte;

import org.siani.itrules.framebuilder.Adapter;
import org.siani.itrules.framebuilder.BuilderContext;
import org.siani.itrules.model.Frame;
import siani.tara.Language;
import siani.tara.compiler.model.*;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.compiler.model.impl.NodeReference;
import siani.tara.semantic.Assumption;

import java.util.Collection;
import java.util.Map;

import static siani.tara.compiler.codegeneration.magritte.TemplateTags.*;

public class BoxNodeAdapter implements Adapter<Node> {
	private final Language language;
	private final Map<Node, Long> keys;

	public BoxNodeAdapter(Language language, Map<Node, Long> keys) {
		this.language = language;
		this.keys = keys;
	}

	@Override
	public void adapt(Frame frame, Node node, BuilderContext context) {
		if (node instanceof NodeImpl) {
			addNodeInfo(node, frame);
			addAnnotations(node, frame);
			addVariables(node, frame, context);
			addParameters(node, frame, context);
			addFacetVariables(node, frame, context);
			includes(node, frame);
		}
	}

	private void addNodeInfo(Node node, Frame newFrame) {
		newFrame.addFrame(KEY, this.keys.get(node));
		if (node.getName() != null && !node.isAnonymous())
			newFrame.addFrame(NAME, clean(node.getQualifiedName()));
		addTypes(node, newFrame);
		if (node.getParent() != null)
			newFrame.addFrame(PARENT, node.getParent().getName());
		if (node.getAddress() != null) newFrame.addFrame(ADDRESS, node.getAddress());
		addFacetApplies(node, newFrame);
	}

	private void addTypes(Node node, Frame newFrame) {
		Frame typeFrame = new Frame(NODE_TYPE).addFrame(NAME, node.getType());
		newFrame.addFrame(NODE_TYPE, typeFrame);
	}

	private void addAnnotations(final Node node, Frame frame) {
		if (node.getAnnotations().isEmpty() || node.isCase()) return;
		frame.addFrame(ANNOTATION, new Frame(ANNOTATION) {{
			for (Annotation annotation : node.getAnnotations())
				if (!annotation.isMeta()) addFrame(VALUE, annotation);
		}});
	}

	private void addVariables(Node node, final Frame frame, BuilderContext context) {
		for (final Variable variable : node.getVariables())
			frame.addFrame("variable", context.build(variable));
	}

	private void addFacetVariables(Node node, final Frame frame, BuilderContext context) {
		for (FacetTarget facetTarget : node.getFacetTargets())
			for (final Variable variable : facetTarget.getVariables())
				frame.addFrame("variable", context.build(variable));
	}

	private void addParameters(Node node, Frame frame, BuilderContext context) {
		for (final Parameter parameter : node.getParameters())
			frame.addFrame("variable", context.build(parameter));
	}

	private void includes(Node node, Frame frame) {
		for (Node inner : node.getIncludedNodes())
			if (inner.isAggregated()) addAggregated(frame, inner);
			else addComponent(frame, inner);
	}


	private void addAggregated(Frame frame, Node inner) {
		Long key = inner instanceof NodeReference ? keys.get(((NodeReference) inner).getDestiny()) : keys.get(inner);
		if (keys.isEmpty()) return;
		frame.addFrame(INCLUDE, new Frame("aggregated", "include").addFrame("value", key));
	}

	private void addComponent(Frame frame, Node inner) {
		frame.addFrame(INCLUDE, new Frame("composed", "include").addFrame("value", keys.get(inner)));
	}

	private String clean(String name) {
		return name.replace("[", "").replace("]", "").replaceAll(Node.ANNONYMOUS, "");
	}


	private void addFacetApplies(Node node, Frame newFrame) {
		for (Facet facet : node.getFacets()) {
			Frame facetFrame = new Frame(FACET_APPLY).addFrame(NAME, facet.type());
			if (isIntentionInstance(facet.type()))
				facetFrame.addFrame(APPLY, buildFacetPath(node, facet.type()));
			newFrame.addFrame(FACET, facetFrame);
		}
	}

	private boolean isIntentionInstance(String type) {
		Collection<Assumption> assumptions = language.assumptions(type);
		for (Assumption assumption : assumptions) if (assumption instanceof Assumption.IntentionInstance) return true;
		return false;
	}

	private String buildFacetPath(Node node, String facet) {
		NodeContainer aNode = node;
		String path = node.getName() + facet + DOT + CLASS;
		while (aNode.getContainer() != null && !(aNode.getContainer() instanceof Model)) {
			aNode = aNode.getContainer();
			path = addToPath(facet, aNode, path);
		}
		return path;
	}

	private String addToPath(String facetName, NodeContainer node, String path) {
		boolean faceted = false;
		for (Facet facet : ((Node) node).getFacets())
			if (facet.type().equals(facetName)) {
				path = ((Node) node).getName() + facetName + DOT + path;
				faceted = true;
			}
		if (!faceted) path = ((Node) node).getType() + DOT + path;
		return path;
	}

}
