package siani.tara.compiler.codegeneration.magritte;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import siani.tara.compiler.model.*;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.compiler.model.impl.NodeReference;
import siani.tara.semantic.model.Tag;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoxNodeAdapter implements Adapter<Node>, TemplateTags {
	private final Map<Node, Long> keys;
	private final boolean terminalInstance;

	public BoxNodeAdapter(Map<Node, Long> keys, boolean terminalInstance) {
		this.keys = keys;
		this.terminalInstance = terminalInstance;
	}

	@Override
	public void execute(Frame frame, Node node, FrameContext<Node> context) {
		if (node instanceof NodeImpl) {
			structure(node, frame);
			flags(node, frame);
			variables(node, frame, context);
			parameters(node, frame, context);
			facetParameters(node, frame, context);
			componentOf(node.getContainer(), frame);
			includes(node, frame);
		}
	}

	private void structure(Node node, Frame newFrame) {
		if (node.isAnonymous() && node.getPlate() == null) newFrame.addFrame(KEY, String.valueOf(this.keys.get(node)));
		else {
			newFrame.addFrame(NAME, facetPrefix(node) + clean(node.getQualifiedName()));
			if (node.getPlate() != null) newFrame.addFrame(PLATE, "|" + String.valueOf(node.getPlate()));
		}
		addTypes(node, newFrame);
		if (node.getParent() != null) newFrame.addFrame(PARENT, clean(node.getParent().getQualifiedName()));
	}

	private String facetPrefix(Node node) {
		FacetTarget target = inFacetTarget(node);
		if (target == null) return "";
		final Node container = (Node) target.getContainer();
		return clean(container.getQualifiedName()) + "+" + clean(target.getTargetNode().getQualifiedName() + ".");
	}

	private FacetTarget inFacetTarget(Node node) {
		NodeContainer container = node.getContainer();
		while (container != null)
			if (container instanceof FacetTarget) return (FacetTarget) container;
			else container = container.getContainer();
		return null;
	}

	private void addTypes(Node node, Frame newFrame) {
		newFrame.addFrame(NODE_TYPE, node.getType());
		for (Facet facet : node.getFacets()) newFrame.addFrame(NODE_TYPE, facet.getType());
	}

	private void flags(final Node node, Frame frame) {
		Frame tagsFrame = new Frame();
		for (Tag tag : node.getFlags()) {
			if (!tag.equals(Tag.ABSTRACT) && !tag.equals(Tag.TERMINAL_INSTANCE) && !tag.equals(Tag.ROOT)) continue;
			if (terminalInstance && tag.equals(Tag.TERMINAL_INSTANCE)) continue;
			tagsFrame.addFrame(VALUE, tag.name());
		}
		if (terminalInstance && isRoot(node)) tagsFrame.addFrame(VALUE, Tag.ROOT.name());
		tagsFrame.addTypes(ANNOTATION);
		if (tagsFrame.slots().length != 0)
			frame.addFrame(ANNOTATION, tagsFrame);
	}

	private boolean isRoot(Node node) {
		return node.isRoot() || (node.getContainer() instanceof Model);
	}

	private void variables(Node node, final Frame frame, FrameContext<Node> context) {
		for (final Variable variable : node.getVariables())
			frame.addFrame(VARIABLE, context.build(variable));
	}

	private void parameters(Node node, Frame frame, FrameContext<Node> context) {
		node.getParameters().stream().filter(parameter -> !isOverriddenByFacets(parameter, node.getFacets())).forEach(parameter -> {
			frame.addFrame(VARIABLE, context.build(parameter));
		});
	}

	private boolean isOverriddenByFacets(Parameter parameter, Collection<Facet> facets) {
		for (Facet facet : facets)
			for (Parameter facetParameter : facet.getParameters())
				if (facetParameter.getName().equals(parameter.getName())) return true;
		return false;
	}

	private void facetParameters(Node node, final Frame frame, FrameContext<Node> context) {
		for (Facet facet : node.getFacets())
			for (final Parameter parameter : facet.getParameters())
				frame.addFrame(VARIABLE, context.build(parameter));
	}

	private void componentOf(NodeContainer container, Frame frame) {
		if (container instanceof Node && keys.get(container) != null)
			frame.addFrame(COMPONENT_OF, String.valueOf(keys.get(container)));
		//TODO else if FacetTarget or FacetApply
	}

	private void includes(Node node, Frame frame) {
		node.getIncludedNodes().stream().
			filter(inner -> !inherited(inner) && !isOverriddenByFacets(inner, node.getFacets())).
			forEach(inner -> addComponent(frame, inner));
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
				addComponent(frame, inner);
	}

	private Long getKey(Node inner) {
		return inner instanceof NodeReference ? keys.get(((NodeReference) inner).getDestiny()) : keys.get(inner);
	}

	private void addComponent(Frame frame, Node inner) {
		Long key = getKey(inner);
		Frame include = new Frame().addTypes("include").addTypes(asString(inner.getFlags()));
		final boolean withKey = inner.isAnonymous() && inner.getPlate() == null;
		include.addFrame(VALUE, withKey ? key : searchNode(inner));
		if (terminalInstance) include.addTypes(TERMINAL);
		if (withKey) include.addTypes(KEY);
		if (inner.isFeature()) include.addTypes(Tag.SINGLE.name());
		frame.addFrame(INCLUDE, include);
	}

	private String searchNode(Node inner) {
		Node node = inner instanceof NodeReference ? ((NodeReference) inner).getDestiny() : inner;
		return '"' + (node.isAnonymous() ? node.getPlate() : node.getQualifiedName()) + '"';
	}

	public static String clean(String name) {
		return name.replace("[", "").replace("]", "").replaceAll(Node.ANNONYMOUS, "");
	}

	private String[] asString(Collection<Tag> flags) {
		List<String> list = flags.stream().map(Tag::name).collect(Collectors.toList());
		return list.toArray(new String[list.size()]);
	}
}
