package tara.compiler.codegeneration.magritte.box;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.compiler.codegeneration.magritte.Generator;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.model.*;
import tara.compiler.model.impl.*;
import tara.semantic.model.Tag;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoxUnitNodeAdapter extends Generator implements Adapter<Node>, TemplateTags {
	private final Map<Node, Long> keys;
	private final boolean m0;

	public BoxUnitNodeAdapter(Map<Node, Long> keys, boolean terminalBox) {
		this.keys = keys;
		this.m0 = terminalBox;
	}

	@Override
	public void execute(Frame frame, Node node, FrameContext<Node> context) {
		if (node instanceof NodeImpl) {
			structure(node, frame);
			flags(node, frame);
			variables(node, frame, context);
			parameters(node, frame, context);
			facetParameters(node, frame, context);
			componentOf(node.container(), frame);
			includes(node, frame);
		}
	}

	private void structure(Node node, Frame newFrame) {
		if (node.isAnonymous() && node.plate() == null)
			newFrame.addFrame(KEY, String.valueOf(this.keys.get(node)));
		else {
			String prefix = inFacet(node) != null ? facetPrefix(node) : facetTargetPrefix(node);
			newFrame.addFrame(NAME, prefix + clean(node.qualifiedName()));
			if (node.plate() != null) newFrame.addFrame(PLATE, "|" + String.valueOf(node.plate()));
		}
		addTypes(node, newFrame);
		if (node.getParent() != null)
			newFrame.addFrame(PARENT, getQn(node.getParent()));
	}

	private String facetPrefix(Node node) {
		Facet facet = inFacet(node);
		if (facet == null) return "";
		final Node container = (Node) facet.container();
		return clean(facet.getFacetType()) + "+" + clean(container.getType()) + ".";
	}

	private boolean isInFacet(Node node) {
		return inFacetTarget(node) != null || inFacet(node) != null;
	}

	private String facetTargetPrefix(Node node) {
		FacetTarget target = inFacetTarget(node);
		if (target == null) return "";
		final Node container = (Node) target.container();
		return clean(container.qualifiedName()) + "+" + clean(target.targetNode().qualifiedName() + ".");
	}

	private FacetTarget inFacetTarget(Node node) {
		NodeContainer container = node.container();
		while (container != null)
			if (container instanceof FacetTarget) return (FacetTarget) container;
			else container = container.container();
		return null;
	}

	private Facet inFacet(Node node) {
		NodeContainer container = node.container();
		while (container != null)
			if (container instanceof Facet) return (Facet) container;
			else container = container.container();
		return null;
	}

	private void addTypes(Node node, Frame newFrame) {
		if (node.facets().isEmpty())
			newFrame.addFrame(NODE_TYPE, node.getType());
		else
			for (Facet facet : node.facets())
				newFrame.addFrame(NODE_TYPE, facet.getFacetType() + "+" + node.getType());
	}

	private void flags(final Node node, Frame frame) {
		Frame tagsFrame = new Frame();
		tagsFrame.addTypes(ANNOTATION);
		if (node.isFacet()) tagsFrame.addFrame(VALUE, FACET, ABSTRACT);
		else if (node.isAbstract()) tagsFrame.addFrame(VALUE, ABSTRACT);
		if (node.isTerminalInstance() && !m0) tagsFrame.addFrame(VALUE, PROTOTYPE);
		else if ((node.isFeatureInstance() || node.isTerminalInstance()) && !m0) tagsFrame.addFrame(VALUE, CASE);
		if (node.isMain() && !m0) tagsFrame.addFrame(VALUE, MAIN);
		if ((node.isMain() || node.container() instanceof Model) && m0) tagsFrame.addFrame(VALUE, ROOT);
		if (tagsFrame.slots().length != 0) frame.addFrame(ANNOTATION, tagsFrame);
	}

	private void variables(Node node, final Frame frame, FrameContext<Node> context) {
		for (final Variable variable : node.variables())
			frame.addFrame(VARIABLE, context.build(variable));
	}

	private void parameters(Node node, Frame frame, FrameContext<Node> context) {
		node.getParameters().stream().
			filter(parameter -> !isOverriddenByFacets(parameter, node.facets())).
			forEach(parameter -> frame.addFrame(VARIABLE, context.build(parameter)));
	}

	private boolean isOverriddenByFacets(Parameter parameter, Collection<Facet> facets) {
		for (Facet facet : facets)
			for (Parameter facetParameter : facet.getParameters())
				if (facetParameter.name().equals(parameter.name())) return true;
		return false;
	}

	private void facetParameters(Node node, final Frame frame, FrameContext<Node> context) {
		for (Facet facet : node.facets())
			for (final Parameter parameter : facet.getParameters())
				frame.addFrame(VARIABLE, context.build(parameter));
	}

	private void componentOf(NodeContainer container, Frame frame) {
		if (container instanceof Node && keys.get(container) != null)
			frame.addFrame(COMPONENT_OF, String.valueOf(keys.get(container)));
		//TODO else if FacetTarget or FacetApply
	}

	private void includes(Node node, Frame frame) {
		node.components().stream().
			filter(inner -> !isOverriddenByFacets(inner, node.facets())).
			forEach(inner -> addComponent(frame, inner));
		addFacetNodes(node, frame);
	}

	private boolean isOverriddenByFacets(Node inner, Collection<Facet> facets) {
		for (Facet facet : facets)
			for (Node facetNode : facet.components())
				if (facetNode.getFullType().equals(inner.getFullType()) && hasSameName(inner, facetNode))
					return true;
		return false;
	}

	private boolean hasSameName(Node node, Node facetNode) {
		return (facetNode.name() == null && node.name() == null) || (facetNode.name() != null && facetNode.name().equals(node.name()));
	}

	private void addFacetNodes(Node node, Frame frame) {
		for (Facet facet : node.facets())
			for (Node inner : facet.components())
				addComponent(frame, inner);
	}

	private Long getKey(Node inner) {
		return inner instanceof NodeReference ? keys.get(((NodeReference) inner).getDestiny()) : keys.get(inner);
	}

	private void addComponent(Frame frame, Node inner) {
		Long key = getKey(inner);
		Frame include = new Frame().addTypes(INCLUDE).addTypes(asString(inner.flags()));
		final boolean withKey = inner.isAnonymous() && inner.plate() == null;
		if (m0 || inner.isTerminalInstance() || inner.isFeatureInstance())
			include.addTypes(CASE);
		if (withKey) include.addTypes(KEY);
		if (inner.isFeature()) include.addTypes(Tag.SINGLE.name());
		include.addFrame(VALUE, withKey ? key : '"' + getQn(inner instanceof NodeReference ? ((NodeReference) inner).getDestiny() : inner) + '"');
		frame.addFrame(INCLUDE, include);
	}

	private String getQn(Node node) {
		if (isInFacet(node)) {
			final FacetTarget target = inFacetTarget(node);
			if (target == null) return "";
			return clean(getQn(target) + DOT + node.qualifiedName());
		}
		return clean(node.qualifiedName());
	}

	private String getQn(FacetTarget facetTarget) {
		return clean(facetTarget.container().qualifiedName() + "+" + facetTarget.targetNode().qualifiedName());
	}

	private String[] asString(Collection<Tag> flags) {
		List<String> list = flags.stream().map(Tag::name).collect(Collectors.toList());
		return list.toArray(new String[list.size()]);
	}

	private String clean(String name) {
		return name.replace("[", "").replace("]", "").replaceAll(Node.ANNONYMOUS, "");
	}
}
