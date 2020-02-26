package io.intino.magritte.compiler.codegeneration.magritte.layer;

import io.intino.Configuration.Artifact.Model.Level;
import io.intino.itrules.Adapter;
import io.intino.itrules.FrameBuilder;
import io.intino.itrules.FrameBuilderContext;
import io.intino.magritte.Language;
import io.intino.magritte.Resolver;
import io.intino.magritte.compiler.codegeneration.magritte.Generator;
import io.intino.magritte.compiler.codegeneration.magritte.TemplateTags;
import io.intino.magritte.compiler.model.Model;
import io.intino.magritte.compiler.model.NodeImpl;
import io.intino.magritte.compiler.model.NodeReference;
import io.intino.magritte.dsl.Meta;
import io.intino.magritte.dsl.Proteo;
import io.intino.magritte.lang.model.Node;
import io.intino.magritte.lang.model.Variable;
import io.intino.magritte.lang.model.rules.Size;
import io.intino.magritte.lang.semantics.Constraint.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.intino.magritte.compiler.codegeneration.magritte.NameFormatter.cleanQn;
import static io.intino.magritte.compiler.codegeneration.magritte.NameFormatter.getQn;
import static io.intino.magritte.compiler.codegeneration.magritte.layer.TypesProvider.getTypes;
import static io.intino.magritte.lang.model.Tag.Decorable;
import static io.intino.magritte.lang.model.Tag.Instance;

class NodeAdapter extends Generator implements Adapter<Node>, TemplateTags {
	private final Model model;
	private final Level level;
	private Node initNode;
	private FrameBuilderContext context;

	NodeAdapter(String outDsl, Model model, Level level, Language language, Node initNode, String workingPackage, String languageWorkingPackage) {
		super(language, outDsl, workingPackage, languageWorkingPackage);
		this.model = model;
		this.level = level;
		this.initNode = initNode;
	}

	@Override
	public void adapt(Node node, FrameBuilderContext context) {
		this.context = context;
		getTypes(node, language).forEach(context::add);
		context.add(MODEL_TYPE, level == Level.Platform ? PLATFORM : PRODUCT);
		addNodeInfo(node, context);
		addVariables(node, context);
		addComponents(node, context);
		addNonAbstractCreates(node, context);
		addAspectClasses(node, context);
		addAllowedAspects(node, context);
		if (node.isAspect()) {
			addAspectConstrains(node, context);
			addTargetComponents(node, context);
			addAspect(node, node.container(), context);
		}
		addParent(node, context);
	}

	private void addAspectClasses(Node node, FrameBuilderContext context) {
		if (node.isReference()) return;
		aspectNodes(node).
				forEach(aspectNode -> addAspectSlot(context, aspectNode));
	}

	private Stream<Node> aspectNodes(Node node) {
		return node.components().stream().filter(n -> !n.isReference() && n.isAspect());
	}

	private void addAspectSlot(FrameBuilderContext context, Node aspectNode) {
		context.add(NODE, FrameBuilder.from(context).append(aspectNode).add("aspect").toFrame());
	}

	private void addAspect(Node aspect, Node target, FrameBuilderContext context) {
		String qn = cleanQn(getQn(target, workingPackage));
		final FrameBuilder builder = new FrameBuilder().add(ASPECT).add(NAME, target.name()).add(QN, qn).add(OUT_LANGUAGE, outDsl);
		if (aspect.isSub() && aspect.parent() != null) builder.add(OVERRIDEN);
		context.add(ASPECT, builder.toFrame());
		context.add("core", new FrameBuilder().add("core").add(QN, qn).add(NAME, target.name()).toFrame());
	}

	private void addTargetComponents(Node node, FrameBuilderContext context) {
		node.container().components().stream().filter(c -> !c.isAspect()).forEach((Node c) -> {
					if (!isOverriden(c, node)) {
						final FrameBuilder builder = FrameBuilder.from(context).append(c).add(TARGET);
						if (((c instanceof NodeReference && !((NodeReference) c).isHas()) || c instanceof NodeImpl) && (c.destinyOfReference().parent() != null))
							builder.add(INHERITED).add(PARENT_REF, c.destinyOfReference().parent().qualifiedName());
						builder.add(TARGET_CONTAINER, node.container().name());
						if (node.container().sizeOf(c).isSingle()) builder.add(SINGLE);
						context.add(NODE, builder.toFrame());
					}
				}
		);
	}

	private void addNodeInfo(Node node, FrameBuilderContext context) {
		context.add(OUT_LANGUAGE, outDsl).add(WORKING_PACKAGE, workingPackage);
		if ((initNode != null && !node.equals(initNode))) context.add(INNER, true);
		if (node.doc() != null) context.add(DOC, node.doc());
		if (node.container() != null) context.add(CONTAINER_NAME, node.container().name());
		addType(context, node);
		addName(this.context, node);
		if (node.isAbstract() || node.is(Decorable)) context.add(ABSTRACT, true);
		if (node.is(Decorable)) context.add(DECORABLE, true);
		node.flags().stream().filter(isLayerInterface()).forEach(tag -> context.add(FLAG, tag));
		if (node.parent() != null) context.add(CHILD);
		if (node.components().stream().anyMatch(c -> c.is(Instance)))
			context.add(META_TYPE, languageWorkingPackage + DOT + metaType(node));
	}

	private void addNonAbstractCreates(Node node, FrameBuilderContext context) {
		if (node instanceof NodeReference) return;
		final List<Node> components = node.components();
		components.stream().
				filter(c -> !c.isAnonymous()).
				forEach(c -> {
					List<FrameBuilder> children = new ArrayList<>();
					collectChildren(c).stream().filter(n -> !n.isAnonymous() && !n.isAbstract() && !n.isAspect() && !components.contains(n)).
							forEach(n -> children.add(createFrame(n.isReference() ? n.destinyOfReference() : n)));
					for (FrameBuilder child : children) context.add(CREATE, child.add(NODE).add(OWNER).toFrame());
				});
	}

	private FrameBuilder createFrame(Node node) {
		final FrameBuilder builder = new FrameBuilder(REFERENCE, CREATE);
		getTypes(node, language).forEach(builder::add);
		addName(builder, node);
		addVariables(node, builder);
		return builder;
	}

	private List<Node> collectChildren(Node parent) {
		Set<Node> set = new HashSet<>();
		for (Node child : parent.children()) {
			set.add(child);
			set.addAll(collectChildren(child));
		}
		return new ArrayList<>(set);
	}

	private void addType(FrameBuilderContext frame, Node node) {
		if (!(language instanceof Proteo || language instanceof Meta)) {
			frame.add(CONCEPT_LAYER, language.doc(node.type()).layer());
			frame.add(TYPE, nodeType(node, sizeConstraint(node)));
		}
	}

	private Size sizeConstraint(Node node) {
		final Component constraint = (Component) language.constraints(node.container().type()).stream().
				filter(c -> (c instanceof Component) && ((Component) c).type().equals(node.type())).
				findFirst().orElse(null);
		if (constraint == null) return Size.MULTIPLE();
		return (Size) constraint.rules().stream().filter(rule -> rule instanceof Size).findFirst().orElse(Size.MULTIPLE());
	}

	private String nodeType(Node node, Size size) {
		return Resolver.shortType(node.type()) + (!size.isSingle() ? "List" : "");
	}

	private void addAllowedAspects(Node node, FrameBuilderContext context) {
		allowedAspects(node).forEach(aspect -> {
			FrameBuilder builder = new FrameBuilder(AVAILABLE_ASPECT);
			builder.add(NAME, aspect.name());
			if (aspect.isAbstract()) builder.add(ABSTRACT);
			if (node.isAbstract()) builder.add(ABSTRACT, "null");
			String qn = cleanQn(getQn(aspect, workingPackage));
			builder.add(QN, qn);
			builder.add(STASH_QN, qn);
			aspect.variables().stream().filter(v -> v.size().isRequired()).forEach(variable -> builder.add(VARIABLE,
					FrameBuilder.from(context).append(variable).add(REQUIRED).add(CONTAINER, node.name()).toFrame()));
			context.add(AVAILABLE_ASPECT, builder.toFrame());
		});
	}


	private Collection<Node> allowedAspects(Node node) {
		Set<Node> nodes = new HashSet<>();
		for (Node aspectNode : node.components().stream().filter(Node::isAspect).collect(Collectors.toList())) {
			if (aspectNode.isReference()) continue;
			nodes.add(aspectNode);
			nodes.addAll(collectChildren(aspectNode));
		}
		return nodes;
	}

	private void addName(FrameBuilderContext context, Node node) {
		if (node.name() != null) context.add(NAME, node.name());
		String qn = cleanQn(buildQN(node));
		context.add(QN, qn).add(STASH_QN, qn);
	}

	private String buildQN(Node node) {
		return getQn(node instanceof NodeReference ? ((NodeReference) node).destination() : node, workingPackage.toLowerCase());
	}

	private void addVariables(Node node, FrameBuilderContext context) {
		node.variables().forEach(v -> {
			final FrameBuilder builder = FrameBuilder.from(this.context)
					.add(OWNER)
					.append(v)
					.add(CONTAINER, node.name());
			context.add(VARIABLE, builder.toFrame());
		});
		if (node.isAspect()) {
			node.container().variables().stream().
					filter(variable -> !variable.isInherited() && !isOverriden(node, variable)).
					forEach(variable -> context.add(VARIABLE, FrameBuilder.from(context).append(variable).add(TARGET).add(CONTAINER, node.name()).toFrame()));
			node.aspectConstraints().forEach(c ->
					c.node().variables().forEach(v ->
							context.add(VARIABLE, FrameBuilder.from(context).append(v).add(TARGET).add(CONTAINER, node.name()).toFrame())));
		}
		addTerminalVariables(node, context);
	}

	private boolean isOverriden(Node node, Variable variable) {
		return node.variables().stream().anyMatch(var -> var.name().equals(variable.name()));
	}

	private boolean isOverriden(Node targetNodeComponent, Node aspectNode) {
		return aspectNode.components().stream().anyMatch(component -> component.name() != null && component.name().equals(targetNodeComponent.name()));
	}


	private void addAspectConstrains(Node node, FrameBuilderContext context) {
		node.aspectConstraints()
				.forEach(c -> context.add(CONSTRAINT,
						new FrameBuilder(CONSTRAINT).add(NAME, c.node().name()).add(QN, cleanQn(getQn(c.node(), workingPackage))).toFrame()));
	}

	void setInitNode(Node initNode) {
		this.initNode = initNode;
	}
}