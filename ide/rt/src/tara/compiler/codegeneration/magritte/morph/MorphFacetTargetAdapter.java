package tara.compiler.codegeneration.magritte.morph;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.compiler.codegeneration.magritte.Generator;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.language.model.FacetTarget;
import tara.language.model.Node;
import tara.language.model.NodeContainer;

public class MorphFacetTargetAdapter extends Generator implements Adapter<FacetTarget>, TemplateTags {
	private final String generatedLanguage;
	private FrameContext<FacetTarget> context;

	public MorphFacetTargetAdapter(String generatedLanguage) {
		this.generatedLanguage = generatedLanguage;
	}

	@Override
	public void execute(Frame frame, FacetTarget target, FrameContext<FacetTarget> context) {
		this.context = context;
		frame.addTypes("nodeimpl");
		addFacetTargetInfo(target, frame);
		addComponents(frame, target, context);
		addTargetComponents(target, frame, context);
	}

	private void addFacetTargetInfo(FacetTarget target, Frame frame) {
		addName(target, frame);
		addParent(target, frame);
		addFacetTarget(target, frame);
		addVariables(target, frame);
	}

	private void addFacetTarget(FacetTarget target, Frame frame) {
		final Frame facetTargetFrame = new Frame();
		facetTargetFrame.addTypes(FACET_TARGET);
		facetTargetFrame.addFrame(NAME, target.targetNode().name());
		facetTargetFrame.addFrame(QN, target.targetNode().qualifiedName());
		facetTargetFrame.addFrame(GENERATED_LANGUAGE, generatedLanguage);
		frame.addFrame(FACET_TARGET, facetTargetFrame);
	}

	private void addName(FacetTarget node, Frame frame) {
		frame.addFrame(NAME, ((Node) node.container()).name() + "_" + node.targetNode().name());
		frame.addFrame(QN, node.targetNode().qualifiedName());
	}

	private void addParent(FacetTarget target, Frame newFrame) {
		NodeContainer nodeContainer = target.container();
		newFrame.addFrame(PARENT, NameFormatter.getQn((Node) nodeContainer, generatedLanguage));
	}

	protected void addVariables(FacetTarget target, final Frame frame) {
		target.variables().stream().
			filter(variable -> !variable.isInherited()).
			forEach(variable -> {
				final Frame varFrame = (Frame) context.build(variable);
				varFrame.addTypes("owner");
				frame.addFrame(VARIABLE, varFrame);
			});
		target.targetNode().variables().stream().
			filter(variable -> !variable.isInherited()).
			forEach(variable -> {
				final Frame varFrame = (Frame) context.build(variable);
				varFrame.addTypes("target");
				frame.addFrame(VARIABLE, varFrame);
			});
	}

	private void addTargetComponents(FacetTarget target, Frame frame, FrameContext<FacetTarget> context) {
		target.targetNode().components().stream().
			forEach(node -> {
				final Frame nodeFrame = (Frame) context.build(node);
				nodeFrame.addTypes("target");
				frame.addFrame(NODE, nodeFrame);
			});
	}

}
