package tara.compiler.codegeneration.magritte.layer;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.compiler.codegeneration.magritte.Generator;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.model.NodeReference;
import tara.lang.model.FacetTarget;
import tara.lang.model.Node;

import java.util.HashSet;
import java.util.Set;

public class LayerFacetTargetAdapter extends Generator implements Adapter<FacetTarget>, TemplateTags {
	private final String generatedLanguage;
	private final int level;
	private FrameContext<FacetTarget> context;
	private Set<String> imports = new HashSet<>();

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
		frame.addFrame(NAME, facetTarget.owner().name() + facetTarget.targetNode().name());
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
		Node node = target.parent();
		if (node != null)
			newFrame.addFrame(PARENT, NameFormatter.getQn(node, generatedLanguage));
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
		target.owner().variables().stream().
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
			FacetTarget targetOf = findTargetOf(node, target.targetNode());
			if (targetOf.equals(target.targetNode())) continue;
			targetOf.owner().variables().stream().
				forEach(variable -> {
					final Frame varFrame = (Frame) context.build(variable);
					varFrame.addTypes(TARGET);
					frame.addFrame(VARIABLE, varFrame);
				});
		}
	}

	private FacetTarget findTargetOf(Node node, Node target) {
		final FacetTarget facetTarget = node.facetTarget();
		return facetTarget != null && target.equals(facetTarget.targetNode()) ? facetTarget : null;
	}

	private void addTargetComponents(FacetTarget target, Frame frame, FrameContext<FacetTarget> context) {
		target.targetNode().components().stream().
			forEach(component -> {
				if (!isOverriden(component, target)) { //TODO
						final Frame nodeFrame = (Frame) context.build(component);
						nodeFrame.addTypes(TARGET);
						nodeFrame.addFrame("targetContainer", target.targetNode().name());
						frame.addFrame(NODE, nodeFrame);
					}
				}
			);
	}

	private boolean isOverriden(Node node, FacetTarget facetTarget) {
		for (Node component : facetTarget.owner().components())
			if (component.name().equals(node.name())) return true;
		return false;
	}


	public Set<String> getImports() {
		return imports;
	}
}
