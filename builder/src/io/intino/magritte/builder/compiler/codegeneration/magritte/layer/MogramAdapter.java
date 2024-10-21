package io.intino.magritte.builder.compiler.codegeneration.magritte.layer;

import io.intino.itrules.Adapter;
import io.intino.itrules.Frame;
import io.intino.itrules.FrameBuilder;
import io.intino.itrules.FrameBuilderContext;
import io.intino.magritte.builder.compiler.codegeneration.magritte.Generator;
import io.intino.magritte.builder.compiler.codegeneration.magritte.NameFormatter;
import io.intino.magritte.builder.compiler.codegeneration.magritte.TemplateTags;
import io.intino.tara.Language;
import io.intino.tara.language.semantics.Constraint.Component;
import io.intino.tara.model.*;
import io.intino.tara.model.rules.Size;
import io.intino.tara.processors.Resolver;
import io.intino.tara.processors.model.HasMogram;
import io.intino.tara.processors.model.Model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.intino.magritte.builder.compiler.codegeneration.magritte.NameFormatter.cleanQn;
import static io.intino.magritte.builder.compiler.codegeneration.magritte.NameFormatter.getQn;
import static io.intino.tara.model.Annotation.Decorable;
import static io.intino.tara.model.Annotation.Generalization;
import static io.intino.tara.model.Level.M1;
import static io.intino.tara.model.Level.M3;

class MogramAdapter extends Generator implements Adapter<Mogram>, TemplateTags {
	private Mogram initNode;
	private FrameBuilderContext context;

	MogramAdapter(String outDsl, Language language, Mogram initNode, String workingPackage, String languageWorkingPackage) {
		super(language, outDsl, workingPackage, languageWorkingPackage);
		this.initNode = initNode;
	}

	@Override
	public void adapt(Mogram mogram, FrameBuilderContext context) {
		this.context = context;
		TypesProvider.getTypes(mogram, language).forEach(context::add);
		context.add(MODEL_TYPE, mogram.level() == M3 ? METAMETAMODEL : METAMODEL);
		addNodeInfo(mogram, context);
		addProperties(mogram, context);
		addComponents(mogram, context);
		addReferences(mogram, context);
		addNonAbstractCreates(mogram, context);
		addFacetClasses(mogram, context);
		addAvailableFacets(mogram, context);
		if (mogram.facetPrescription() != null) {
			addFacetConstrains(mogram, context);
			if (!(mogram.container() instanceof Model)) {
				addTargetComponents(mogram, context);
				addFacet(mogram, mogram.facetPrescription().get(), context);
			}
		}
		addParent(mogram, context);
	}

	private void addFacetClasses(Mogram node, FrameBuilderContext context) {
		facetMograms(node).forEach(m -> addFacetSlot(context, m));
	}

	private Stream<Mogram> facetMograms(Mogram mogram) {
		return mogram.mograms().stream().filter(n -> n.facetPrescription() != null);
	}

	private void addFacetSlot(FrameBuilderContext context, Mogram aspectNode) {
		context.add(NODE, FrameBuilder.from(context).append(aspectNode).add(ASPECT).toFrame());
	}

	private void addReferences(Mogram mogram, FrameBuilderContext context) {
		mogram.referenceComponents().forEach(r -> context.add(NODE, frameOf(r).add(OWNER).toFrame()));
	}

	private FrameBuilder frameOf(HasMogram ref) {
		FrameBuilder builder = new FrameBuilder();
		Mogram target = ref.target().get();
		addNodeInfo(target, builder);
		getTypes(ref).forEach(builder::add);
		addProperties(target, builder);
		return builder;
	}

	private List<String> getTypes(HasMogram ref) {
		Mogram target = ref.target().get();
		List<String> types = target.annotations().stream().map(Annotation::name).collect(Collectors.toList());
		types.add("Node");
		final Size size = ref.container().sizeOf(ref);
		if (size != null && size.isSingle()) types.add(SINGLE);
		return types;
	}

	private void addFacet(Mogram facet, Mogram target, FrameBuilderContext context) {
		String qn = cleanQn(getQn(target, workingPackage));
		final FrameBuilder builder = new FrameBuilder().add(ASPECT).add(NAME, target.name()).add(QN, qn).add(OUT_LANGUAGE, outDsl);
		if (facet.isSub() && facet.parent() != null) builder.add(OVERRIDEN);
		context.add(ASPECT, builder.toFrame());
		context.add("core", new FrameBuilder().add("core").add(QN, qn).add(NAME, target.name()).toFrame());
	}

	private void addTargetComponents(Mogram facet, FrameBuilderContext context) {
		Mogram target = facet.facetPrescription().get();
		effectiveComponents(target)
				.filter(c -> !isOverriden(c, facet))
				.forEach(c -> addCoreMogram(facet, context, c));
		effectiveReferenceComponents(target)
				.filter(c -> !isOverriden(c.target().get(), facet))
				.forEach(c -> addCoreMogramReference(facet, context, c));
	}

	private void addCoreMogramReference(Mogram facet, FrameBuilderContext context, HasMogram c) {
		final FrameBuilder builder = frameOf(c).add(TARGET);
		builder.add(TARGET_CONTAINER, facet.container().name());
		if (c.container().sizeOf(c).isSingle()) builder.add(SINGLE);
		context.add(NODE, builder.toFrame());
	}

	private static void addCoreMogram(Mogram facet, FrameBuilderContext context, Mogram c) {
		final FrameBuilder builder = FrameBuilder.from(context).append(c).add(TARGET);
		builder.add(TARGET_CONTAINER, facet.facetPrescription().get().name());
		if (c.container().sizeOf(c).isSingle()) builder.add(SINGLE);
		context.add(NODE, builder.toFrame());
	}

	private void addNodeInfo(Mogram node, FrameBuilderContext context) {
		context.add(OUT_LANGUAGE, outDsl).add(WORKING_PACKAGE, workingPackage);
		if ((initNode != null && !node.equals(initNode))) context.add(INNER, true);
		if (node.doc() != null) context.add(DOC, node.doc());
		if (node.container() != null) context.add(CONTAINER_NAME, node.container().name());
		addType(context, node);
		addName(context, node);
		boolean decorable = node.is(Decorable) || isInDecorable(node);
		if (node.is(Generalization) || decorable) context.add(ABSTRACT, true);
		if (decorable) context.add(DECORABLE, true);
		node.annotations().stream().filter(isLayerInterface()).forEach(tag -> context.add(FLAG, tag));
		if (node.parent() != null) context.add(CHILD);
		if (node.components().stream().anyMatch(c -> c.level().equals(M1)))
			context.add(META_TYPE, languageWorkingPackage + DOT + metaType(node));
	}

	private void addNonAbstractCreates(Mogram node, FrameBuilderContext context) {
		if (node instanceof MogramReference) return;
		final List<Mogram> components = node.components();
		components.stream()
				.filter(c -> !c.isAnonymous())
				.flatMap(c -> collectChildren(c).stream())
				.filter(m -> !m.isAnonymous() && !m.is(Generalization) && m.facetPrescription() == null && !components.contains(m))
				.map(this::createFrame)
				.forEach(child -> context.add(CREATE, child.add(NODE).add(OWNER).toFrame()));
	}

	private FrameBuilder createFrame(Mogram mogram) {
		final FrameBuilder builder = new FrameBuilder(REFERENCE, CREATE);
		TypesProvider.getTypes(mogram, language).forEach(builder::add);
		addName(builder, mogram);
		addProperties(mogram, builder);
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

	private void addType(FrameBuilderContext frame, Mogram mogram) {
		List<Mogram> mograms = mogram.metaMograms();
		frame.add(CONCEPT_LAYER, NameFormatter.layerQualifiedName(mograms.get(0)));
		frame.add(TYPE, mogramType(mogram, sizeConstraint(mogram)));
	}

	private Size sizeConstraint(Mogram mogram) {
		final Component constraint = (Component) language.constraints(mogram.container() instanceof Mogram m ? m.types().get(0) : "").stream().
				filter(c -> (c instanceof Component) && ((Component) c).type().equals(mogram.types().get(0))).
				findFirst().orElse(null);
		if (constraint == null) return Size.MULTIPLE();
		return (Size) constraint.rules().stream().filter(rule -> rule instanceof Size).findFirst().orElse(Size.MULTIPLE());
	}

	private String mogramType(Mogram node, Size size) {
		return Resolver.shortType(node.types().get(0)) + (!size.isSingle() ? "List" : "");
	}

	private void addAvailableFacets(Mogram mogram, FrameBuilderContext context) {
		allowedFacets(mogram).forEach(facet -> {
			FrameBuilder builder = new FrameBuilder(AVAILABLE_ASPECT);
			builder.add(NAME, facet.name());
			if (facet.is(Generalization)) builder.add(ABSTRACT).add(Generalization.name());
			if (mogram.is(Generalization)) builder.add(ABSTRACT, "null");
			String qn = cleanQn(getQn(facet, workingPackage));
			builder.add(QN, qn);
			builder.add(STASH_QN, qn);
			effectiveProperties(facet)
					.filter(p -> p.rule(Size.class).isRequired())
					.filter(p -> {
						Property overrided = overriden(p, facet);
						return overrided == null || overrided.rule(Size.class).isRequired();
					})
					.forEach(p -> builder.add(PROPERTY, facetPropertyFrame(mogram, context, p)));
			context.add(AVAILABLE_ASPECT, builder.toFrame());
		});
	}

	private Property overriden(Property p, Mogram m) {
		return m.properties().stream().filter(pr -> pr.name().equals(p.name())).findFirst().orElse(null);
	}

	private static Frame facetPropertyFrame(Mogram mogram, FrameBuilderContext context, Property p) {
		return FrameBuilder.from(context).append(p).add(REQUIRED).add(CONTAINER, isInDecorable(mogram) ? mogram.qualifiedName() : mogram.name()).toFrame();
	}


	private Collection<Mogram> allowedFacets(Mogram mogram) {
		Set<Mogram> mograms = new LinkedHashSet<>();
		for (NamedReference<Mogram> facet : mogram.applicableFacets()) {
			mograms.add(facet.get());
			mograms.addAll(collectChildren(facet.get()));
		}
		return mograms;
	}

	private void addName(FrameBuilderContext context, Mogram node) {
		if (node.name() != null) context.add(NAME, node.name());
		String qn = cleanQn(buildQN(node));
		context.add(QN, qn).add(STASH_QN, qn);
	}

	private String buildQN(Mogram mogram) {
		return getQn(mogram, workingPackage.toLowerCase());
	}

	private void addProperties(Mogram mogram, FrameBuilderContext context) {
		effectiveProperties(mogram).forEach(p -> {
			FrameBuilder builder = FrameBuilder.from(this.context);
			if (!p.container().equals(mogram)) builder.add(INHERITED);
			context.add(PROPERTY, builder
					.add(OWNER)
					.append(p)
					.add(CONTAINER, isInDecorable(mogram) ? completeName(mogram) : mogram.name()).toFrame());
		});
		if (mogram.facetPrescription() != null) {
			mogram.facetPrescription().get().properties().stream().
					filter(p -> !isOverriden(mogram, p)).
					forEach(p -> context.add(PROPERTY, FrameBuilder.from(context).append(p).add(TARGET).
							add(CONTAINER, isInDecorable(mogram) ? completeName(mogram) : mogram.name()).
							toFrame()));
			mogram.facetConstraints().stream().flatMap(c -> c.target().get().properties().stream()).forEach(p ->
					context.add(PROPERTY, FrameBuilder.from(context).append(p).add(TARGET).
							add(CONTAINER, isInDecorable(mogram) ? completeName(mogram) : mogram.name()).
							toFrame()));
		}
//		addTerminalProperties(mogram, context);
	}

	protected void addComponents(Mogram mogram, FrameBuilderContext context) {
		effectiveComponents(mogram)
				.filter(c -> !c.level().equals(Level.M1) && !c.isAnonymous())
				.forEach(c -> {
					FrameBuilder builder = FrameBuilder.from(context).append(c).add(OWNER);
					if (!c.container().equals(mogram)) builder.add(INHERITED);
					context.add(NODE, builder.toFrame());
				});
	}

	private String completeName(Mogram node) {
		return node.qualifiedName();
	}

	private boolean isOverriden(Mogram node, Property prop) {
		return node.properties().stream().anyMatch(p -> p.name().equals(prop.name()));
	}

	private boolean isOverriden(Mogram targetNodeComponent, Mogram aspectNode) {
		return aspectNode.components().stream().anyMatch(component -> component.name() != null && component.name().equals(targetNodeComponent.name()));
	}

	private void addFacetConstrains(Mogram node, FrameBuilderContext context) {
		node.facetConstraints().forEach(c -> context.add(CONSTRAINT,
				new FrameBuilder(CONSTRAINT).add(NAME, c.target().get().name()).add(QN, cleanQn(getQn(c.target().get(), workingPackage))).toFrame()));
	}

	void setInitNode(Mogram initNode) {
		this.initNode = initNode;
	}
}