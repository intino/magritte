package siani.tara.compiler.codegeneration.magritte;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import siani.tara.compiler.model.*;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.compiler.model.impl.NodeReference;

import java.util.Collection;
import java.util.Map;

public class BoxNodeAdapter implements Adapter<Node>, TemplateTags {
	private final Map<Node, Long> keys;
	private final boolean terminal;

	public BoxNodeAdapter(Map<Node, Long> keys, boolean terminal) {
		this.keys = keys;
		this.terminal = terminal;
	}

	@Override
	public void execute(Frame frame, Node node, FrameContext<Node> FrameContext) {
		if (node instanceof NodeImpl) {
			structure(node, frame);
			annotations(node, frame);
			variables(node, frame, FrameContext);
			facetTargetVariables(node, frame, FrameContext);
			parameters(node, frame, FrameContext);
			facetParameters(node, frame, FrameContext);
			componentOf(node.getContainer(), frame);
			includes(node, frame);
		}
	}

	private void structure(Node node, Frame newFrame) {
		newFrame.addFrame(KEY, String.valueOf(this.keys.get(node)));
		if (node.getName() != null && !node.isAnonymous())
			newFrame.addFrame(NAME, clean(node.getQualifiedName()));
		addTypes(node, newFrame);
		if (node.getParent() != null)
			newFrame.addFrame(PARENT, node.getParent().getName());
		if (node.getAddress() != null) newFrame.addFrame(ADDRESS, String.valueOf(node.getAddress()));
		addFacetApplies(node, newFrame);
	}

	private void addTypes(Node node, Frame newFrame) {
		newFrame.addFrame(NODE_TYPE, node.getType());
	}

	private void annotations(final Node node, Frame frame) {
		Frame annotationFrame = new Frame(frame) {{
			for (Tag tag : node.getFlags()) {
				if (!tag.equals(Tag.ABSTRACT) && !tag.equals(Tag.TERMINAL_INSTANCE)) continue;
				if (terminal && tag.equals(Tag.TERMINAL_INSTANCE)) continue;
				addFrame(VALUE, tag.getName());
			}
		}};
		if (terminal && (isRoot(node))) annotationFrame.addFrame(VALUE, Tag.ROOT.getName());
		annotationFrame.addTypes(ANNOTATION);
		if (annotationFrame.slots().length != 0)
			frame.addFrame(ANNOTATION, annotationFrame);
	}

	private boolean isRoot(Node node) {
		return node.isRoot() || node.isAggregated() && (node.getContainer() instanceof Node && isRoot((Node) node.getContainer()));
	}

	private void variables(Node node, final Frame frame, FrameContext<Node> FrameContext) {
		for (final Variable variable : node.getVariables())
			frame.addFrame(VARIABLE, FrameContext.build(variable));
	}

	private void facetTargetVariables(Node node, final Frame frame, FrameContext<Node> FrameContext) {
		for (FacetTarget facetTarget : node.getFacetTargets())
			for (final Variable variable : facetTarget.getVariables())
				frame.addFrame(VARIABLE, FrameContext.build(variable));
	}

	private void parameters(Node node, Frame frame, FrameContext<Node> FrameContext) {
		for (final Parameter parameter : node.getParameters())
			if (!isOverriddenByFacets(parameter, node.getFacets()))
				frame.addFrame(VARIABLE, FrameContext.build(parameter));
	}

	private boolean isOverriddenByFacets(Parameter parameter, Collection<Facet> facets) {
		for (Facet facet : facets)
			for (Parameter facetParameter : facet.getParameters())
				if (facetParameter.getName().equals(parameter.getName())) return true;
		return false;
	}

	private void facetParameters(Node node, final Frame frame, FrameContext<Node> FrameContext) {
		for (Facet facet : node.getFacets())
			for (final Parameter parameter : facet.getParameters())
				frame.addFrame(VARIABLE, FrameContext.build(parameter));
	}

	private void componentOf(NodeContainer container, Frame frame) {
		if (container instanceof Node && keys.get(container) != null)
			frame.addFrame(COMPONENT_OF, String.valueOf(keys.get(container)));
		//TODO else if FacetTarget or FacetApply
	}

	private void includes(Node node, Frame frame) {
		for (Node inner : node.getIncludedNodes())
			if (!inherited(inner) && !isOverriddenByFacets(inner, node.getFacets()))
				addNode(frame, inner);
		addFacetNodes(node, frame);
	}

	private boolean inherited(Node inner) {
		return !(isHas(inner) || inner instanceof NodeImpl);
	}

	private boolean isHas(Node inner) {
		return inner instanceof NodeReference && ((NodeReference) inner).isHas();
	}

	private boolean isOverriddenByFacets(Node inner, Collection<Facet> facets) {
		for (Facet facet : facets)
			for (Node facetNode : facet.getIncludedNodes())
				if (facetNode.getFullType().equals(inner.getFullType()) && hasSameName(inner, facetNode))
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
		Frame include = new Frame(frame).addTypes(AGGREGATED, "include").addFrame(VALUE, String.valueOf(key));
		frame.addFrame(INCLUDE, include);
	}

	private void addComponent(Frame frame, Node inner) {
		Frame include = new Frame(frame).addTypes("composed", "include").addFrame(VALUE, String.valueOf(keys.get(inner instanceof NodeReference ? ((NodeReference) inner).getDestiny() : inner)));
		frame.addFrame(INCLUDE, include);
	}

	private String clean(String name) {
		return name.replace("[", "").replace("]", "").replaceAll(Node.ANNONYMOUS, "");
	}


	private void addFacetApplies(Node node, Frame newFrame) {
		for (Facet facet : node.getFacets()) {
			Frame facetFrame = new Frame(newFrame).addTypes(FACET_APPLY).addFrame(NAME, facet.getType());
			facetFrame.addFrame(APPLY, buildFacetPath(node, facet.getType()));
			newFrame.addFrame(FACET, facetFrame);
		}
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
