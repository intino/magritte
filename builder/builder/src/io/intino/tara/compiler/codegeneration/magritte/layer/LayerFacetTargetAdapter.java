package io.intino.tara.compiler.codegeneration.magritte.layer;

import io.intino.itrules.Adapter;
import io.intino.itrules.FrameBuilder;
import io.intino.itrules.FrameBuilderContext;
import io.intino.tara.Language;
import io.intino.tara.compiler.codegeneration.magritte.Generator;
import io.intino.tara.compiler.codegeneration.magritte.TemplateTags;
import io.intino.tara.compiler.model.NodeImpl;
import io.intino.tara.compiler.model.NodeReference;
import io.intino.tara.compiler.shared.Configuration.Level;
import io.intino.tara.lang.model.FacetTarget;
import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.model.Variable;

import java.util.HashSet;
import java.util.Set;

import static io.intino.tara.compiler.codegeneration.magritte.NameFormatter.cleanQn;
import static io.intino.tara.compiler.codegeneration.magritte.NameFormatter.getQn;
import static io.intino.tara.compiler.shared.Configuration.Level.Platform;
import static io.intino.tara.lang.model.Tag.Decorable;
import static io.intino.tara.lang.model.Tag.Instance;

class LayerFacetTargetAdapter extends Generator implements Adapter<FacetTarget>, TemplateTags {
	private final String outDSL;
	private final Level level;
	private FrameBuilderContext context;
	private Set<String> imports = new HashSet<>();

	LayerFacetTargetAdapter(Language language, String outDSL, Level level, String workingPackage, String languageWorkingPackage) {
		super(language, outDSL, workingPackage, languageWorkingPackage);
		this.outDSL = outDSL;
		this.level = level;
	}

	static String name(FacetTarget target) {
		return target.owner().name() + target.targetNode().name();
	}


	public void adapt(FacetTarget target, FrameBuilderContext context) {
		this.context = context;
		context.add("nodeimpl");
		context.add(MODEL_TYPE, level.compareLevelWith(Platform) == 0 ? PLATFORM : PRODUCT);
		context.add(OUT_LANGUAGE, outDSL).add(WORKING_PACKAGE, workingPackage);
		if (target.owner().isAbstract() || target.owner().is(Decorable)) context.add(ABSTRACT, true);
		if (target.owner().is(Decorable)) context.add(DECORABLE, true);
		addFacetTargetInfo(target);
		addComponents(target.owner(), this.context);
		addTargetComponents(target);
		addParent(target);
		if (!context.contains(META_TYPE) && target.owner().components().stream().anyMatch(c -> c.is(Instance)))
			context.add(META_TYPE, languageWorkingPackage + DOT + metaType(target.owner()));
	}

	private void addFacetTargetInfo(FacetTarget target) {
		addName(target);
		addConstrains(target);
		addTags(target);
		addFacetTarget(target);
		addVariables(target);
	}

	private void addTags(FacetTarget target) {
		target.owner().flags().stream().filter(isLayerInterface()).forEach(tag -> context.add(FLAG, tag));
	}

	private void addName(FacetTarget facetTarget) {
		context.add(NAME, name(facetTarget));
		context.add(QN, cleanQn(buildQN(facetTarget.targetNode())));
	}

	private void addConstrains(FacetTarget target) {
		target.constraints().stream()
				.filter(c -> !c.negated())
				.forEach(c -> context.add(CONSTRAINT,
						new FrameBuilder(CONSTRAINT).add(NAME, c.node().name()).add(QN, cleanQn(buildQN(c.node()))).toFrame()));
	}

	private void addParent(FacetTarget target) {
		Node parent = target.owner().parent() != null ? target.owner().parent() : target.parent();
		if (parent != null) context.add(PARENT, cleanQn(getQn(parent, workingPackage)));
		if ((context.contains(CREATE) || context.contains(NODE)) || !target.owner().children().isEmpty()) {
			context.add(PARENT_SUPER, parent != null);
			if (parent != null) context.add("parentName", cleanQn(getQn(parent, workingPackage)));
		}
		if ((context.contains(NODE)) && parent != null &&
				(!parent.components().isEmpty() ||
						(parent.facetTarget() != null && !parent.facetTarget().targetNode().components().isEmpty() && hasLists(parent.facetTarget().targetNode()))))
			context.add("parentClearName", cleanQn(getQn(parent, workingPackage)));
	}

	private boolean hasLists(Node node) {
		return node.components().stream().anyMatch(c -> !node.sizeOf(c).isSingle());
	}

	private void addFacetTarget(FacetTarget target) {
		final FrameBuilder builder = new FrameBuilder().add(FACET_TARGET).add(NAME, target.targetNode().name()).add(QN, buildQN(target.targetNode())).add(OUT_LANGUAGE, outDSL);
		if (target.owner().isSub() && target.owner().parent() != null) builder.add(OVERRIDEN);
		context.add(FACET_TARGET, builder.toFrame());
	}

	private String buildQN(Node node) {
		return getQn(node instanceof NodeReference ? ((NodeReference) node).getDestiny() : node, workingPackage.toLowerCase());
	}

	private void addVariables(FacetTarget target) {
		target.owner().variables().stream().
				filter(variable -> !variable.isInherited()).
				forEach(variable -> context.add(VARIABLE, FrameBuilder.from(context).append(variable).add(OWNER).add(CONTAINER, name(target)).toFrame()));
		target.targetNode().variables().stream().
				filter(variable -> !variable.isInherited() && !isOverriden(target.owner(), variable)).
				forEach(variable -> context.add(VARIABLE, FrameBuilder.from(context).append(variable).add(TARGET).add(CONTAINER, name(target)).toFrame()));
		target.constraints().stream().filter(c -> !c.negated()).forEach(c -> {
			FacetTarget targetOf = findTargetOf(c.node(), target.targetNode());
			if (targetOf != null && !targetOf.targetNode().equals(target.targetNode()))
				targetOf.owner().variables()
						.forEach(variable ->
								context.add(VARIABLE, FrameBuilder.from(context).append(variable).add(TARGET).add(CONTAINER, name(target)).toFrame()));
		});
		addTerminalVariables(target.owner(), context);
	}

	private FacetTarget findTargetOf(Node node, Node target) {
		final FacetTarget facetTarget = node.facetTarget();
		return facetTarget != null && target.equals(facetTarget.targetNode()) ? facetTarget : null;
	}

	private void addTargetComponents(FacetTarget target) {
		target.targetNode().components().forEach((Node component) -> {
					if (!isOverriden(component, target)) { //TODO
						final FrameBuilder builder = new FrameBuilder(TARGET);
						if (((component instanceof NodeReference && !((NodeReference) component).isHas()) || component instanceof NodeImpl) && (component.destinyOfReference().parent() != null))
							builder.add(INHERITED).add(PARENT_REF, component.destinyOfReference().parent().qualifiedName());
						builder.add(TARGET_CONTAINER, target.targetNode().name());
						if (target.targetNode().sizeOf(component).isSingle()) builder.add(SINGLE);
						context.add(NODE, builder.toFrame());
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
