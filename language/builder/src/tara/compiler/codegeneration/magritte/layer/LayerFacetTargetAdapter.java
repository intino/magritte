package tara.compiler.codegeneration.magritte.layer;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.compiler.codegeneration.magritte.Generator;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.model.NodeReference;
import tara.lang.model.FacetTarget;
import tara.lang.model.Node;
import tara.lang.model.NodeContainer;

public class LayerFacetTargetAdapter extends Generator implements Adapter<FacetTarget>, TemplateTags {
	private final String generatedLanguage;
	private final int level;
	private FrameContext<FacetTarget> context;

	public LayerFacetTargetAdapter(String generatedLanguage, int level) {
		this.generatedLanguage = generatedLanguage;
		this.level = level;
	}

	@Override
	public void execute(Frame frame, FacetTarget target, FrameContext<FacetTarget> context) {
		this.context = context;
		frame.addTypes("nodeimpl");
		frame.addFrame(MODEL_TYPE, level == 2 ? ENGINE : DOMAIN);
		frame.addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
		addFacetTargetInfo(target, frame);
		addComponents(frame, target, context);
		addTargetComponents(target, frame, context);
	}

	private void addFacetTargetInfo(FacetTarget target, Frame frame) {
		addName(target, frame);
		addConstrains(target, frame);
		addParent(target, frame);
		addFacetTarget(target, frame);
		addVariables(target, frame);
	}

	private void addName(FacetTarget facetTarget, Frame frame) {
		frame.addFrame(NAME, ((Node) facetTarget.container()).name() + "_" + facetTarget.targetNode().name());
		frame.addFrame(QN, buildQN(facetTarget.targetNode()));
	}

	private void addConstrains(FacetTarget target, Frame frame) {
		for (Node node : target.constraintNodes()) {
			final Frame constraint = new Frame().addTypes(CONSTRAINT);
			constraint.addFrame(NAME, node.name());
			constraint.addFrame(QN, buildQN(node));
			frame.addFrame(CONSTRAINT, constraint);
		}
	}

	private void addParent(FacetTarget target, Frame newFrame) {
		NodeContainer nodeContainer = target.container();
		newFrame.addFrame(PARENT, NameFormatter.getQn((Node) nodeContainer, generatedLanguage));
	}

	private void addFacetTarget(FacetTarget target, Frame frame) {
		final Frame facetTargetFrame = new Frame();
		facetTargetFrame.addTypes(FACET_TARGET);
		facetTargetFrame.addFrame(NAME, target.targetNode().name());
		facetTargetFrame.addFrame(QN, buildQN(target.targetNode()));
		facetTargetFrame.addFrame(GENERATED_LANGUAGE, generatedLanguage);
		frame.addFrame(FACET_TARGET, facetTargetFrame);
	}

	private String buildQN(Node node) {
		return NameFormatter.getQn(node instanceof NodeReference ? ((NodeReference) node).getDestiny() : node, generatedLanguage.toLowerCase());
	}

	protected void addVariables(FacetTarget target, final Frame frame) {
		target.variables().stream().
			filter(variable -> !variable.isInherited()).
			forEach(variable -> {
				final Frame varFrame = (Frame) context.build(variable);
				varFrame.addTypes(OWNER);
				frame.addFrame(VARIABLE, varFrame);
			});
		target.targetNode().variables().stream().
			filter(variable -> !variable.isInherited()).
			forEach(variable -> {
				final Frame varFrame = (Frame) context.build(variable);
				varFrame.addTypes(TARGET);
				frame.addFrame(VARIABLE, varFrame);
			});
		for (Node node : target.constraintNodes()) {
			NodeContainer targetOf = findTargetOf(node, target.targetNode());
			if (targetOf.equals(target.targetNode())) continue;
			targetOf.variables().stream().
				forEach(variable -> {
					final Frame varFrame = (Frame) context.build(variable);
					varFrame.addTypes(TARGET);
					frame.addFrame(VARIABLE, varFrame);
				});
		}
	}

	private NodeContainer findTargetOf(Node node, Node target) {
		for (FacetTarget facetTarget : node.facetTargets())
			if (target.equals(facetTarget.targetNode())) return facetTarget;
		return target;
	}

	private void addTargetComponents(FacetTarget target, Frame frame, FrameContext<FacetTarget> context) {
		target.targetNode().components().stream().
			forEach(node -> {
				final Frame nodeFrame = (Frame) context.build(node);
				nodeFrame.addTypes(TARGET);
				nodeFrame.addFrame("targetContainer", target.targetNode().name());
				frame.addFrame(NODE, nodeFrame);
			});
	}

}
