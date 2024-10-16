package io.intino.magritte.builder.compiler.codegeneration.magritte.layer;

import io.intino.itrules.Adapter;
import io.intino.itrules.FrameBuilder;
import io.intino.itrules.FrameBuilderContext;
import io.intino.magritte.builder.compiler.codegeneration.magritte.Generator;
import io.intino.magritte.builder.compiler.codegeneration.magritte.TemplateTags;
import io.intino.tara.Language;
import io.intino.tara.Resolver;
import io.intino.tara.builder.core.CompilerConfiguration.Level;
import io.intino.tara.builder.model.Model;
import io.intino.tara.builder.model.MogramImpl;
import io.intino.tara.builder.model.MogramReference;
import io.intino.tara.language.model.Mogram;
import io.intino.tara.language.model.Variable;
import io.intino.tara.language.model.rules.Size;
import io.intino.tara.language.semantics.Constraint.Component;
import io.intino.tara.language.semantics.Documentation;

import java.util.*;
import java.util.stream.Stream;

import static io.intino.magritte.builder.compiler.codegeneration.magritte.NameFormatter.cleanQn;
import static io.intino.magritte.builder.compiler.codegeneration.magritte.NameFormatter.getQn;
import static io.intino.magritte.builder.compiler.codegeneration.magritte.layer.TypesProvider.getTypes;
import static io.intino.tara.language.model.Tag.Decorable;
import static io.intino.tara.language.model.Tag.Instance;

class MogramAdapter extends Generator implements Adapter<Mogram>, TemplateTags {
	private final Level level;
	private Mogram initNode;
	private FrameBuilderContext context;

	MogramAdapter(String outDsl, Level level, Language language, Mogram initNode, String workingPackage, String languageWorkingPackage) {
		super(language, outDsl, workingPackage, languageWorkingPackage);
		this.level = level;
		this.initNode = initNode;
	}

	@Override
	public void adapt(Mogram mogram, FrameBuilderContext context) {
		this.context = context;
		getTypes(mogram, language).forEach(context::add);
		context.add(MODEL_TYPE, level == Level.MetaMetaModel ? PLATFORM : PRODUCT);
		addNodeInfo(mogram, context);
		addVariables(mogram, context);
		addComponents(mogram, context);
		addNonAbstractCreates(mogram, context);
		addFacetClasses(mogram, context);
		addAllowedAspects(mogram, context);
		if (mogram.isFacet()) {
			addAspectConstrains(mogram, context);
			if (!(mogram.container() instanceof Model)) {
				addTargetComponents(mogram, context);
				addFacet(mogram, mogram.container(), context);
			}
		}
		addParent(mogram, context);
	}

	private void addFacetClasses(Mogram node, FrameBuilderContext context) {
		if (node.isReference()) return;
		facetMograms(node).forEach(m -> addFacetSlot(context, m));
	}

	private Stream<Mogram> facetMograms(Mogram node) {
		return node.components().stream().filter(n -> !n.isReference() && n.isFacet());
	}

	private void addFacetSlot(FrameBuilderContext context, Mogram aspectNode) {
		context.add(NODE, FrameBuilder.from(context).append(aspectNode).add("aspect").toFrame());
	}

	private void addFacet(Mogram facet, Mogram target, FrameBuilderContext context) {
		String qn = cleanQn(getQn(target, workingPackage));
		final FrameBuilder builder = new FrameBuilder().add(ASPECT).add(NAME, target.name()).add(QN, qn).add(OUT_LANGUAGE, outDsl);
		if (facet.isSub() && facet.parent() != null) builder.add(OVERRIDEN);
		context.add(ASPECT, builder.toFrame());
		context.add("core", new FrameBuilder().add("core").add(QN, qn).add(NAME, target.name()).toFrame());
	}

	private void addTargetComponents(Mogram facet, FrameBuilderContext context) {
		facet.container().components().stream()
				.filter(c -> !c.isFacet())
				.filter(c -> !isOverriden(c, facet))
				.forEach(c -> {
							final FrameBuilder builder = FrameBuilder.from(context).append(c).add(TARGET);
							if (((c instanceof MogramReference && !((MogramReference) c).isHas()) || c instanceof MogramImpl) && (c.targetOfReference().parent() != null))
								builder.add(INHERITED).add(PARENT_REF, c.targetOfReference().parent().qualifiedName());
							builder.add(TARGET_CONTAINER, facet.container().name());
							if (facet.container().sizeOf(c).isSingle()) builder.add(SINGLE);
							context.add(NODE, builder.toFrame());
						}
				);
	}

	private void addNodeInfo(Mogram node, FrameBuilderContext context) {
		context.add(OUT_LANGUAGE, outDsl).add(WORKING_PACKAGE, workingPackage);
		if ((initNode != null && !node.equals(initNode))) context.add(INNER, true);
		if (node.doc() != null) context.add(DOC, node.doc());
		if (node.container() != null) context.add(CONTAINER_NAME, node.container().name());
		addType(context, node);
		addName(this.context, node);
		boolean decorable = node.is(Decorable) || isInDecorable(node);
		if (node.isAbstract() || decorable) context.add(ABSTRACT, true);
		if (decorable) context.add(DECORABLE, true);
		node.flags().stream().filter(isLayerInterface()).forEach(tag -> context.add(FLAG, tag));
		if (node.parent() != null) context.add(CHILD);
		if (node.components().stream().anyMatch(c -> c.is(Instance)))
			context.add(META_TYPE, languageWorkingPackage + DOT + metaType(node));
	}


	private void addNonAbstractCreates(Mogram node, FrameBuilderContext context) {
		if (node instanceof MogramReference) return;
		final List<Mogram> components = node.components();
		components.stream().
				filter(c -> !c.isAnonymous()).
				forEach(c -> {
					List<FrameBuilder> children = new ArrayList<>();
					collectChildren(c).stream().filter(n -> !n.isAnonymous() && !n.isAbstract() && !n.isFacet() && !components.contains(n)).
							forEach(n -> children.add(createFrame(n.isReference() ? n.targetOfReference() : n)));
					for (FrameBuilder child : children) context.add(CREATE, child.add(NODE).add(OWNER).toFrame());
				});
	}

	private FrameBuilder createFrame(Mogram node) {
		final FrameBuilder builder = new FrameBuilder(REFERENCE, CREATE);
		getTypes(node, language).forEach(builder::add);
		addName(builder, node);
		addVariables(node, builder);
		return builder;
	}

	private List<Mogram> collectChildren(Mogram parent) {
		Set<Mogram> set = new LinkedHashSet<>();
		for (Mogram child : parent.children()) {
			set.add(child);
			set.addAll(collectChildren(child));
		}
		return new ArrayList<>(set);
	}

	private void addType(FrameBuilderContext frame, Mogram node) {
		Documentation doc = language.doc(node.type());
		if (doc == null) return;
		frame.add(CONCEPT_LAYER, doc.layer());
		frame.add(TYPE, nodeType(node, sizeConstraint(node)));
	}

	private Size sizeConstraint(Mogram node) {
		final Component constraint = (Component) language.constraints(node.container().type()).stream().
				filter(c -> (c instanceof Component) && ((Component) c).type().equals(node.type())).
				findFirst().orElse(null);
		if (constraint == null) return Size.MULTIPLE();
		return (Size) constraint.rules().stream().filter(rule -> rule instanceof Size).findFirst().orElse(Size.MULTIPLE());
	}

	private String nodeType(Mogram node, Size size) {
		return Resolver.shortType(node.type()) + (!size.isSingle() ? "List" : "");
	}

	private void addAllowedAspects(Mogram node, FrameBuilderContext context) {
		allowedFacets(node).forEach(aspect -> {
			FrameBuilder builder = new FrameBuilder(AVAILABLE_ASPECT);
			builder.add(NAME, aspect.name());
			if (aspect.isAbstract()) builder.add(ABSTRACT);
			if (node.isAbstract()) builder.add(ABSTRACT, "null");
			String qn = cleanQn(getQn(aspect, workingPackage));
			builder.add(QN, qn);
			builder.add(STASH_QN, qn);
			aspect.variables().stream().filter(v -> v.size().isRequired()).forEach(variable -> builder.add(VARIABLE,
					FrameBuilder.from(context).append(variable).add(REQUIRED).add(CONTAINER, isInDecorable(node) ? node.qualifiedName() : node.name()).toFrame()));
			context.add(AVAILABLE_ASPECT, builder.toFrame());
		});
	}


	private Collection<Mogram> allowedFacets(Mogram node) {
		Set<Mogram> nodes = new LinkedHashSet<>();
		for (Mogram aspectNode : node.components().stream().filter(Mogram::isFacet).toList()) {
			if (aspectNode.isReference()) continue;
			nodes.add(aspectNode);
			nodes.addAll(collectChildren(aspectNode));
		}
		return nodes;
	}

	private void addName(FrameBuilderContext context, Mogram node) {
		if (node.name() != null) context.add(NAME, node.name());
		String qn = cleanQn(buildQN(node));
		context.add(QN, qn).add(STASH_QN, qn);
	}

	private String buildQN(Mogram node) {
		return getQn(node instanceof MogramReference ? ((MogramReference) node).destination() : node, workingPackage.toLowerCase());
	}

	private void addVariables(Mogram mogram, FrameBuilderContext context) {
		mogram.variables().forEach(v -> {
			final FrameBuilder builder = FrameBuilder.from(this.context)
					.add(OWNER)
					.append(v)
					.add(CONTAINER, isInDecorable(mogram) ? completeName(mogram) : mogram.name());
			context.add(VARIABLE, builder.toFrame());
		});
		if (mogram.isFacet()) {
			mogram.container().variables().stream().
					filter(variable -> !variable.isInherited() && !isOverriden(mogram, variable)).
					forEach(variable -> context.add(VARIABLE,
							FrameBuilder.from(context).append(variable).add(TARGET).
									add(CONTAINER, isInDecorable(mogram) ? completeName(mogram) : mogram.name()).
									toFrame()));
			mogram.facetConstraints().forEach(c ->
					c.node().variables().forEach(v ->
							context.add(VARIABLE,
									FrameBuilder.from(context).append(v).add(TARGET).
											add(CONTAINER, isInDecorable(mogram) ? completeName(mogram) : mogram.name()).
											toFrame())));
		}

		addTerminalVariables(mogram, context);
	}

	private String completeName(Mogram node) {
		return node.qualifiedName();
	}

	private boolean isOverriden(Mogram node, Variable variable) {
		return node.variables().stream().anyMatch(var -> var.name().equals(variable.name()));
	}

	private boolean isOverriden(Mogram targetNodeComponent, Mogram aspectNode) {
		return aspectNode.components().stream().anyMatch(component -> component.name() != null && component.name().equals(targetNodeComponent.name()));
	}

	private void addAspectConstrains(Mogram node, FrameBuilderContext context) {
		node.facetConstraints()
				.forEach(c -> context.add(CONSTRAINT,
						new FrameBuilder(CONSTRAINT).add(NAME, c.node().name()).add(QN, cleanQn(getQn(c.node(), workingPackage))).toFrame()));
	}

	void setInitNode(Mogram initNode) {
		this.initNode = initNode;
	}
}