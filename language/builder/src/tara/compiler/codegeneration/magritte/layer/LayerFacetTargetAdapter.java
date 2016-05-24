package tara.compiler.codegeneration.magritte.layer;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.magritte.Generator;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.core.CompilerConfiguration.ModuleType;
import tara.compiler.model.NodeImpl;
import tara.compiler.model.NodeReference;
import tara.lang.model.FacetTarget;
import tara.lang.model.Node;
import tara.lang.model.Variable;

import java.util.HashSet;
import java.util.Set;

import static tara.compiler.core.CompilerConfiguration.ModuleType.Platform;

class LayerFacetTargetAdapter extends Generator implements Adapter<FacetTarget>, TemplateTags {
	private final String generatedLanguage;
	private final ModuleType moduleType;
	private FrameContext<FacetTarget> context;
	private Set<String> imports = new HashSet<>();

	LayerFacetTargetAdapter(String generatedLanguage, Language language, ModuleType moduleType) {
		super(language, generatedLanguage);
		this.generatedLanguage = generatedLanguage;
		this.moduleType = moduleType;
	}

	@Override
	public void execute(Frame frame, FacetTarget target, FrameContext<FacetTarget> context) {
		this.context = context;
		frame.addTypes("nodeimpl");
		frame.addFrame(MODEL_TYPE, moduleType.compareLevelWith(Platform) == 0 ? PLATFORM : APPLICATION);
		frame.addFrame(GENERATED_LANGUAGE, generatedLanguage);
		addFacetTargetInfo(target, frame);
		addComponents(frame, target.owner(), context);
		addTargetComponents(target, frame, context);
	}

	private void addFacetTargetInfo(FacetTarget target, Frame frame) {
		addName(target, frame);
		addConstrains(target, frame);
		addTags(target, frame);
		addParent(target, frame);
		addFacetTarget(target, frame);
		addVariables(target, frame);
	}

	private void addTags(FacetTarget target, Frame frame) {
		target.owner().flags().stream().filter(isLayerInterface()).forEach(tag -> frame.addFrame(FLAG, tag));
	}

	private void addName(FacetTarget facetTarget, Frame frame) {
		frame.addFrame(NAME, facetTarget.owner().name() + facetTarget.targetNode().name());
		frame.addFrame(QN, buildQN(facetTarget.targetNode()));
	}

	private void addConstrains(FacetTarget target, Frame frame) {
		target.constraints().stream().filter(c -> !c.negated()).forEach(c -> {
				final Frame constraint = new Frame().addTypes(CONSTRAINT);
				constraint.addFrame(NAME, c.node().name());
				constraint.addFrame(QN, buildQN(c.node()));
				frame.addFrame(CONSTRAINT, constraint);
			}
		);
	}

	private void addParent(FacetTarget target, Frame newFrame) {
		Node parent = target.owner().parent() != null ? target.owner().parent() : target.parent();
		if (target.owner().isAbstract()) newFrame.addFrame("abstract", true);
		if (parent != null) {
			newFrame.addFrame(PARENT, NameFormatter.getQn(parent, generatedLanguage));
			newFrame.addFrame(PARENT_SUPER, true);
		} else if (target.owner().isSub() && target.owner().parent() != null) {
			newFrame.addFrame(PARENT, NameFormatter.getQn(target.owner().parent(), generatedLanguage));
			newFrame.addFrame(PARENT_SUPER, true);
		} else newFrame.addFrame(PARENT_SUPER, false);
	}

	private void addFacetTarget(FacetTarget target, Frame frame) {
		final Frame facetTargetFrame = new Frame();
		if (target.owner().isSub() && target.owner().parent() != null) facetTargetFrame.addTypes(OVERRIDEN);
		facetTargetFrame.addTypes(FACET_TARGET);
		facetTargetFrame.addFrame(NAME, target.targetNode().name());
		facetTargetFrame.addFrame(QN, buildQN(target.targetNode()));
		facetTargetFrame.addFrame(GENERATED_LANGUAGE, generatedLanguage);
		frame.addFrame(FACET_TARGET, facetTargetFrame);
	}

	private String buildQN(Node node) {
		return NameFormatter.getQn(node instanceof NodeReference ? ((NodeReference) node).getDestiny() : node, generatedLanguage.toLowerCase());
	}

	private void addVariables(FacetTarget target, final Frame frame) {
		target.owner().variables().stream().
			filter(variable -> !variable.isInherited()).
			forEach(variable -> frame.addFrame(VARIABLE, ((Frame) context.build(variable)).addTypes(OWNER)));
		target.targetNode().variables().stream().
			filter(variable -> !variable.isInherited() && !isOverriden(target.owner(), variable)).
			forEach(variable -> frame.addFrame(VARIABLE, ((Frame) context.build(variable)).addTypes(TARGET)));
		target.constraints().stream().filter(c -> !c.negated()).forEach(c -> {
			FacetTarget targetOf = findTargetOf(c.node(), target.targetNode());
			if (targetOf != null && !targetOf.equals(target.targetNode()))
				targetOf.owner().variables().stream().forEach(variable -> frame.addFrame(VARIABLE, ((Frame) context.build(variable)).addTypes(TARGET)));
		});
		addTerminalVariables(target.owner(), frame);
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
						if (((component instanceof NodeReference && !((NodeReference) component).isHas()) || component instanceof NodeImpl) && (((Node) component.destinyOfReference()).parent() != null)) {
							nodeFrame.addTypes(INHERITED).addFrame(PARENT_REF, ((Node) component.destinyOfReference()).parent().qualifiedName());
						}
						nodeFrame.addFrame(TARGET_CONTAINER, target.targetNode().name());
						if (target.targetNode().ruleOf(component).isSingle()) nodeFrame.addTypes(SINGLE);
						frame.addFrame(NODE, nodeFrame);
					}
				}
			);
	}

	private boolean isOverriden(Node node, FacetTarget facetTarget) {
		for (Node component : facetTarget.owner().components())
			if (component.name() != null && component.name().equals(node.name())) return true;
		return false;
	}

	private boolean isOverriden(Node node, Variable variable) {
		for (Variable var : node.variables())
			if (var.name().equals(variable.name())) return true;
		return false;
	}


	public Set<String> getImports() {
		return imports;
	}
}
