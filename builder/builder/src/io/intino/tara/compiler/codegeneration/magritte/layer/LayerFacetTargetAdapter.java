package io.intino.tara.compiler.codegeneration.magritte.layer;

import io.intino.tara.Language;
import io.intino.tara.compiler.codegeneration.magritte.Generator;
import io.intino.tara.compiler.codegeneration.magritte.TemplateTags;
import io.intino.tara.compiler.model.NodeImpl;
import io.intino.tara.compiler.model.NodeReference;
import io.intino.tara.compiler.shared.Configuration.Level;
import io.intino.tara.lang.model.FacetTarget;
import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.model.Variable;
import org.siani.itrules.Adapter;
import org.siani.itrules.engine.Context;
import org.siani.itrules.model.Frame;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.intino.tara.compiler.codegeneration.magritte.NameFormatter.cleanQn;
import static io.intino.tara.compiler.codegeneration.magritte.NameFormatter.getQn;
import static io.intino.tara.compiler.shared.Configuration.Level.Platform;
import static io.intino.tara.lang.model.Tag.Instance;

class LayerFacetTargetAdapter extends Generator implements Adapter<FacetTarget>, TemplateTags {
	private final String outDSL;
	private final Level level;
	private Context context;
	private Set<String> imports = new HashSet<>();

	LayerFacetTargetAdapter(Language language, String outDSL, Level level, String workingPackage, String languageWorkingPackage) {
		super(language, outDSL, workingPackage, languageWorkingPackage);
		this.outDSL = outDSL;
		this.level = level;
	}

	@Override
	public void adapt(FacetTarget target, Context context) {
		this.context = context;
		Frame frame = context.frame();
		frame.addTypes("nodeimpl");
		frame.addSlot(MODEL_TYPE, level.compareLevelWith(Platform) == 0 ? PLATFORM : PRODUCT);
		frame.addSlot(OUT_LANGUAGE, outDSL).addSlot(WORKING_PACKAGE, workingPackage);
		addFacetTargetInfo(target, frame);
		addComponents(frame, target.owner(), context);
		addTargetComponents(target, frame, context);
		addParent(frame, target);
		if (!Arrays.asList(frame.slots()).contains(META_TYPE.toLowerCase()) && target.owner().components().stream().anyMatch(c -> c.is(Instance)))
			frame.addSlot(META_TYPE, languageWorkingPackage + DOT + metaType(target.owner()));
	}

	private void addFacetTargetInfo(FacetTarget target, Frame frame) {
		addName(target, frame);
		addConstrains(target, frame);
		addTags(target, frame);
		addFacetTarget(target, frame);
		addVariables(target, frame);
	}

	private void addTags(FacetTarget target, Frame frame) {
		target.owner().flags().stream().filter(isLayerInterface()).forEach(tag -> frame.addSlot(FLAG, tag));
	}

	private void addName(FacetTarget facetTarget, Frame frame) {
		frame.addSlot(NAME, name(facetTarget));
		frame.addSlot(QN, cleanQn(buildQN(facetTarget.targetNode())));
	}

	private void addConstrains(FacetTarget target, Frame frame) {
		target.constraints().stream().filter(c -> !c.negated()).forEach(c -> {
					final Frame constraint = new Frame().addTypes(CONSTRAINT);
					constraint.addSlot(NAME, c.node().name());
					constraint.addSlot(QN, cleanQn(buildQN(c.node())));
					frame.addSlot(CONSTRAINT, constraint);
				}
		);
	}

	private void addParent(Frame frame, FacetTarget target) {
		Node parent = target.owner().parent() != null ? target.owner().parent() : target.parent();
		if (parent != null) frame.addSlot(PARENT, cleanQn(getQn(parent, workingPackage)));
		final List<String> slots = Arrays.asList(frame.slots());
		if ((slots.contains(CREATE) || slots.contains(NODE)) || !target.owner().children().isEmpty()) {
			frame.addSlot(PARENT_SUPER, parent != null);
			if (parent != null) frame.addSlot("parentName", cleanQn(getQn(parent, workingPackage)));
		}
		if ((slots.contains(NODE)) && parent != null && (!parent.components().isEmpty() || (parent.facetTarget() != null && !parent.facetTarget().targetNode().components().isEmpty())))
			frame.addSlot("parentClearName", cleanQn(getQn(parent, workingPackage)));
	}

	private void addFacetTarget(FacetTarget target, Frame frame) {
		final Frame facetTargetFrame = new Frame();
		if (target.owner().isSub() && target.owner().parent() != null) facetTargetFrame.addTypes(OVERRIDEN);
		facetTargetFrame.addTypes(FACET_TARGET);
		facetTargetFrame.addSlot(NAME, target.targetNode().name());
		facetTargetFrame.addSlot(QN, buildQN(target.targetNode()));
		facetTargetFrame.addSlot(OUT_LANGUAGE, outDSL);
		frame.addSlot(FACET_TARGET, facetTargetFrame);
	}

	private String buildQN(Node node) {
		return getQn(node instanceof NodeReference ? ((NodeReference) node).getDestiny() : node, workingPackage.toLowerCase());
	}

	private void addVariables(FacetTarget target, final Frame frame) {
		target.owner().variables().stream().
				filter(variable -> !variable.isInherited()).
				forEach(variable -> frame.addSlot(VARIABLE, ((Frame) context.build(variable)).addTypes(OWNER).addSlot(CONTAINER, name(target))));
		target.targetNode().variables().stream().
				filter(variable -> !variable.isInherited() && !isOverriden(target.owner(), variable)).
				forEach(variable -> frame.addSlot(VARIABLE, ((Frame) context.build(variable)).addTypes(TARGET).addSlot(CONTAINER, name(target))));
		target.constraints().stream().filter(c -> !c.negated()).forEach(c -> {
			FacetTarget targetOf = findTargetOf(c.node(), target.targetNode());
			if (targetOf != null && !targetOf.equals(target.targetNode()))
				targetOf.owner().variables()
						.forEach(variable ->
								frame.addSlot(VARIABLE, ((Frame) context.build(variable)).addTypes(TARGET).addSlot(CONTAINER, name(target))));
		});
		addTerminalVariables(target.owner(), frame);
	}

	private String name(FacetTarget target) {
		return target.owner().name() + target.targetNode().name();
	}

	private FacetTarget findTargetOf(Node node, Node target) {
		final FacetTarget facetTarget = node.facetTarget();
		return facetTarget != null && target.equals(facetTarget.targetNode()) ? facetTarget : null;
	}

	private void addTargetComponents(FacetTarget target, Frame frame, Context context) {
		target.targetNode().components().forEach((Node component) -> {
					if (!isOverriden(component, target)) { //TODO
						final Frame nodeFrame = (Frame) context.build(component);
						nodeFrame.addTypes(TARGET);
						if (((component instanceof NodeReference && !((NodeReference) component).isHas()) || component instanceof NodeImpl) && (component.destinyOfReference().parent() != null))
							nodeFrame.addTypes(INHERITED).addSlot(PARENT_REF, component.destinyOfReference().parent().qualifiedName());
						nodeFrame.addSlot(TARGET_CONTAINER, target.targetNode().name());
						if (target.targetNode().sizeOf(component).isSingle()) nodeFrame.addTypes(SINGLE);
						frame.addSlot(NODE, nodeFrame);
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
