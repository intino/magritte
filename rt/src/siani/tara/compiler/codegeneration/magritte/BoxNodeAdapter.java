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

public class BoxNodeAdapter implements Adapter<Node>, TemplateTags {
	private final Language language;
	private final Map<Node, Long> keys;

	public BoxNodeAdapter(Language language, Map<Node, Long> keys) {
		this.language = language;
		this.keys = keys;
	}

	@Override
	public void adapt(Frame frame, Node node, BuilderContext context) {
		if (node instanceof NodeImpl) {
			structure(node, frame);
			annotations(node, frame);
			variables(node, frame, context);
			facetTargetVariables(node, frame, context);
			parameters(node, frame, context);
			facetParameters(node, frame, context);
			includes(node, frame);
		}
	}

	private void structure(Node node, Frame newFrame) {
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
		newFrame.addFrame(NODE_TYPE, node.getType());
	}

	private void annotations(final Node node, Frame frame) {
		if (node.getAnnotations().isEmpty() || node.isCase()) return;
		frame.addFrame(ANNOTATION, new Frame(ANNOTATION) {{
			for (Annotation annotation : node.getAnnotations())
				if (!annotation.isMeta()) addFrame(TemplateTags.VALUE, annotation);
		}});
	}

	private void variables(Node node, final Frame frame, BuilderContext context) {
		for (final Variable variable : node.getVariables())
			context.buildIn(frame, VARIABLE, variable);
	}

	private void facetTargetVariables(Node node, final Frame frame, BuilderContext context) {
		for (FacetTarget facetTarget : node.getFacetTargets())
			for (final Variable variable : facetTarget.getVariables())
				context.buildIn(frame, VARIABLE, variable);
	}

	private void parameters(Node node, Frame frame, BuilderContext context) {
		for (final Parameter parameter : node.getParameters())
			if (!isOverriddenByFacets(parameter, node.getFacets()))
				context.buildIn(frame, VARIABLE, parameter);
	}

	private boolean isOverriddenByFacets(Parameter parameter, Collection<Facet> facets) {
		for (Facet facet : facets)
			for (Parameter facetParameter : facet.getParameters())
				if (facetParameter.getName().equals(parameter.getName())) return true;
		return false;
	}

	private void facetParameters(Node node, final Frame frame, BuilderContext context) {
		for (Facet facet : node.getFacets())
			for (final Parameter parameter : facet.getParameters())
				context.buildIn(frame, VARIABLE, parameter);
	}

	private void includes(Node node, Frame frame) {
		for (Node inner : node.getIncludedNodes())
			if (!isOverriddenByFacets(inner, node.getFacets()))
				addNode(frame, inner);
		addFacetNodes(node, frame);
	}

	private boolean isOverriddenByFacets(Node node, Collection<Facet> facets) {
		for (Facet facet : facets)
			for (Node facetNode : facet.getIncludedNodes())
				if (facetNode.getFullType().equals(node.getFullType()) && hasSameName(node, facetNode))
					return true;
		return false;
	}

	private boolean hasSameName(Node node, Node facetNode) {
		return (facetNode.getName() == null && node.getName() == null) || (facetNode.getName() != null && facetNode.getName().equals(node.getName()));
	}

	private void addFacetNodes(Node node, Frame frame) {
		for (Facet facet : node.getFacets())
			for (Node inner : facet.getIncludedNodes())
				addNode(frame, inner);
	}

	private void addNode(Frame frame, Node inner) {
		if (inner.isAggregated()) addAggregated(frame, inner);
		else addComponent(frame, inner);
	}


	private void addAggregated(Frame frame, Node inner) {
		Long key = inner instanceof NodeReference ? keys.get(((NodeReference) inner).getDestiny()) : keys.get(inner);
		if (keys.isEmpty()) return;
		frame.addFrame(INCLUDE, new Frame(AGGREGATED, "include").addFrame(VALUE, key));
	}

	private void addComponent(Frame frame, Node inner) {
		frame.addFrame(INCLUDE, new Frame("composed", "include").addFrame(VALUE, keys.get(inner)));
	}

	private String clean(String name) {
		return name.replace("[", "").replace("]", "").replaceAll(Node.ANNONYMOUS, "");
	}


	private void addFacetApplies(Node node, Frame newFrame) {
		for (Facet facet : node.getFacets()) {
			Frame facetFrame = new Frame(FACET_APPLY).addFrame(NAME, facet.getType());
			if (isIntentionInstance(facet.getType()))
				facetFrame.addFrame(APPLY, buildFacetPath(node, facet.getType()));
			newFrame.addFrame(FACET, facetFrame);
		}
	}

	private boolean isIntentionInstance(String type) {
		Collection<Assumption> assumptions = language.assumptions(type);
		for (Assumption assumption : assumptions) if (assumption instanceof Assumption.IntentionInstance) return true;
		return false;
	}

	private String buildFacetPath(Node node, String facet) {
		NodeContainer aNode = node.getContainer();
		String path = node.getName() + facet + DOT + CLASS;
		while (aNode != null && !(aNode instanceof Model)) {
			path = addToPath(facet, aNode, path);
			aNode = aNode.getContainer();
		}
		return path;
	}

	private String addToPath(String facetName, NodeContainer node, String path) {
		boolean faceted = false;
		for (Facet facet : ((Node) node).getFacets())
			if (facet.getType().equals(facetName)) {
				path = ((Node) node).getName() + facetName + DOT + path;
				faceted = true;
			}
		if (!faceted) path = shortType(((Node) node).getType()) + DOT + path;
		return path;
	}

	private String shortType(String type) {
		return type.contains(".") ? type.substring(type.lastIndexOf(".") + 1) : type;
	}

}
