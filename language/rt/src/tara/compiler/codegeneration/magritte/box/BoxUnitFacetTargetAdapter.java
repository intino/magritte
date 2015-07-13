package tara.compiler.codegeneration.magritte.box;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.model.FacetTarget;
import tara.compiler.model.Node;
import tara.compiler.model.NodeContainer;
import tara.compiler.model.Variable;
import tara.compiler.model.impl.NodeReference;
import tara.semantic.model.Tag;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoxUnitFacetTargetAdapter implements Adapter<FacetTarget>, TemplateTags {

	private final Map<Node, Long> keys;
	private final boolean m0;

	public BoxUnitFacetTargetAdapter(Map<Node, Long> keys, boolean m0) {
		this.keys = keys;
		this.m0 = m0;
	}

	@Override
	public void execute(Frame frame, FacetTarget facetTarget, FrameContext<FacetTarget> context) {
		structure(facetTarget, frame);
		facetTargetVariables(facetTarget, frame, context);
		addComponents(facetTarget, frame);
	}

	private void structure(FacetTarget facetTarget, Frame newFrame) {
		final NodeContainer container = facetTarget.getContainer();
		newFrame.addFrame(NAME, clean(container.getQualifiedName()) + "+" + clean(facetTarget.getTargetNode().getQualifiedName()));
		addTypes(facetTarget, newFrame);
		newFrame.addFrame(PARENT, clean(container.getQualifiedName()));
	}

	private void facetTargetVariables(FacetTarget facetTarget, final Frame frame, FrameContext<FacetTarget> FrameContext) {
		for (final Variable variable : facetTarget.getVariables())
			frame.addFrame(VARIABLE, FrameContext.build(variable));
	}

	private void addComponents(FacetTarget facetTarget, Frame frame) {
		for (Node node : facetTarget.getIncludedNodes())
			addComponent(node, getQn(facetTarget), frame);
	}

	private String getQn(FacetTarget facetTarget) {
		return facetTarget.getContainer().getQualifiedName() + "+" + facetTarget.getTargetNode().getQualifiedName();
	}

	private void addComponent(Node inner, String container, Frame frame) {
		Long key = getKey(inner);
		Frame include = new Frame().addTypes(INCLUDE).addTypes(asString(inner.getFlags()));
		final boolean withKey = inner.isAnonymous() && inner.getPlate() == null;
		include.addFrame(VALUE, withKey ? key : '"' + container + "." + NameFormatter.cleanQn(searchNode(inner)) + '"');
		if (m0 || (inner.isFeatureInstance() || inner.isTerminalInstance()))
			include.addTypes(CASE);
		if (withKey) include.addTypes(KEY);
		if (inner.isFeature()) include.addTypes(Tag.SINGLE.name());
		frame.addFrame(INCLUDE, include);
	}

	private String searchNode(Node inner) {
		Node node = inner instanceof NodeReference ? ((NodeReference) inner).getDestiny() : inner;
		return (node.isAnonymous() ? node.getPlate() : node.getQualifiedName());
	}

	private String[] asString(Collection<Tag> flags) {
		List<String> list = flags.stream().map(Tag::name).collect(Collectors.toList());
		return list.toArray(new String[list.size()]);
	}

	private Long getKey(Node inner) {
		return inner instanceof NodeReference ? keys.get(((NodeReference) inner).getDestiny()) : keys.get(inner);
	}

	private String clean(String name) {
		return name.replace("[", "").replace("]", "").replaceAll(Node.ANNONYMOUS, "");
	}

	private void addTypes(FacetTarget facetTarget, Frame newFrame) {
		newFrame.addFrame(NODE_TYPE, ((Node) facetTarget.getContainer()).getType());
	}
}
