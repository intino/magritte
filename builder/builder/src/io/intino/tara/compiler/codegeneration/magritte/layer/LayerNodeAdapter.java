package io.intino.tara.compiler.codegeneration.magritte.layer;

import io.intino.itrules.Adapter;
import io.intino.itrules.FrameBuilder;
import io.intino.itrules.FrameBuilderContext;
import io.intino.tara.Language;
import io.intino.tara.Resolver;
import io.intino.tara.compiler.codegeneration.magritte.Generator;
import io.intino.tara.compiler.codegeneration.magritte.NameFormatter;
import io.intino.tara.compiler.codegeneration.magritte.TemplateTags;
import io.intino.tara.compiler.model.Model;
import io.intino.tara.compiler.model.NodeReference;
import io.intino.tara.compiler.shared.Configuration.Level;
import io.intino.tara.dsl.Proteo;
import io.intino.tara.dsl.Verso;
import io.intino.tara.lang.model.FacetTarget;
import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.model.NodeContainer;
import io.intino.tara.lang.model.rules.Size;
import io.intino.tara.lang.semantics.Constraint.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import static io.intino.tara.compiler.codegeneration.magritte.NameFormatter.*;
import static io.intino.tara.compiler.codegeneration.magritte.layer.TypesProvider.getTypes;
import static io.intino.tara.compiler.dependencyresolution.ModelUtils.findFacetTarget;
import static io.intino.tara.lang.model.Tag.Decorable;
import static io.intino.tara.lang.model.Tag.Instance;

class LayerNodeAdapter extends Generator implements Adapter<Node>, TemplateTags {
	private static final Logger LOG = Logger.getGlobal();
	private final Level level;
	private Node initNode;
	private FrameBuilderContext context;

	LayerNodeAdapter(String outDsl, Level level, Language language, Node initNode, String workingPackage, String languageWorkingPackage) {
		super(language, outDsl, workingPackage, languageWorkingPackage);
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
		addAllowedFacets(node, context);
		addParent(node, context);
	}

	private Model findModel(Node node) {
		NodeContainer result = node;
		while (result != null && !(result instanceof Model))
			result = result.container();
		return (Model) result;
	}

	private void addNodeInfo(Node node, FrameBuilderContext context) {
		context.add(OUT_LANGUAGE, outDsl).add(WORKING_PACKAGE, workingPackage);
		if ((initNode != null && !node.equals(initNode)) || isInFacet(node) != null) context.add(INNER, true);
		if (node.doc() != null) context.add(DOC, node.doc());
		if (node.container() != null) context.add(CONTAINER_NAME, node.container().name() + facetName(node.container().facetTarget()));
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
					collectChildren(c).stream().filter(n -> !n.isAnonymous() && !n.isAbstract() && !components.contains(n)).
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
		if (!(language instanceof Proteo || language instanceof Verso)) {
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

	private void addAllowedFacets(Node node, FrameBuilderContext context) {
		for (String facet : node.allowedFacets()) {
			FrameBuilder builder = new FrameBuilder(AVAILABLE_FACET);
			builder.add(NAME, facet);
			FacetTarget facetTarget = findFacetTarget(findModel(node), node, facet);
			if (facetTarget == null) {
				LOG.severe("error finding facet: " + facet + " in node " + node.name());
				throw new RuntimeException("error finding facet: " + facet + " in node " + node.name());
			}
			if (facetTarget.owner().isAbstract()) builder.add(ABSTRACT, "null");
			builder.add(QN, cleanQn(getQn(facetTarget, facetTarget.owner(), workingPackage)));
			builder.add(STASH_QN, NameFormatter.stashQn(facetTarget.owner(), workingPackage));
			facetTarget.owner().variables().stream().filter(v -> v.size().isRequired()).forEach(variable -> builder.add(VARIABLE,
					FrameBuilder.from(context).append(variable).add(REQUIRED).add(CONTAINER, node.name() + facetName(node.facetTarget())).toFrame()));
			context.add(AVAILABLE_FACET, builder.toFrame());
		}
	}

	private void addName(FrameBuilderContext context, Node node) {
		context.add(QN, cleanQn(buildQN(node))).add(STASH_QN, stashQN(node));
		if (node.name() != null) context.add(NAME, node.name() + facetName(node.facetTarget()));
	}

	private String stashQN(Node node) {
		return stashQn(node instanceof NodeReference ? ((NodeReference) node).getDestiny() : node, workingPackage.toLowerCase());
	}

	private String buildQN(Node node) {
		return getQn(node instanceof NodeReference ? ((NodeReference) node).getDestiny() : node, workingPackage.toLowerCase());
	}

	private void addVariables(Node node, FrameBuilderContext context) {
		node.variables().forEach(v -> {
			final FrameBuilder builder = FrameBuilder.from(this.context)
					.add(OWNER)
					.append(v)
					.add(CONTAINER, node.name() + facetName(node.facetTarget()));
			context.add(VARIABLE, builder.toFrame());
		});
		addTerminalVariables(node, context);
	}

	void setInitNode(Node initNode) {
		this.initNode = initNode;
	}
}