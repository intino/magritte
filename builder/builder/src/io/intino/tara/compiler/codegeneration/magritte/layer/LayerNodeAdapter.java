package io.intino.tara.compiler.codegeneration.magritte.layer;

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
import io.intino.tara.lang.model.Variable;
import io.intino.tara.lang.model.rules.Size;
import io.intino.tara.lang.semantics.Constraint.Component;
import org.siani.itrules.Adapter;
import org.siani.itrules.engine.Context;
import org.siani.itrules.model.Frame;

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
	private Node initNode;
	private Context context;
	private final Level level;

	LayerNodeAdapter(String outDsl, Level level, Language language, Node initNode, String workingPackage, String languageWorkingPackage) {
		super(language, outDsl, workingPackage, languageWorkingPackage);
		this.level = level;
		this.initNode = initNode;
	}

	@Override
	public void adapt(Node node, Context context) {
		this.context = context;
		Frame frame = context.frame();
		frame.addTypes(getTypes(node, language));
		frame.addSlot(MODEL_TYPE, level == Level.Platform ? PLATFORM : PRODUCT);
		addNodeInfo(frame, node);
		addComponents(frame, node, context);
		addNonAbstractCreates(frame, node);
		addAllowedFacets(frame, node);
		addParent(frame, node);
	}

	private Model findModel(Node node) {
		NodeContainer result = node;
		while (result != null && !(result instanceof Model))
			result = result.container();
		return (Model) result;
	}

	private void addNodeInfo(Frame frame, Node node) {
		frame.addSlot(OUT_LANGUAGE, outDsl).addSlot(WORKING_PACKAGE, workingPackage);
		if ((initNode != null && !node.equals(initNode)) || isInFacet(node) != null) frame.addSlot(INNER, true);
		if (node.doc() != null) frame.addSlot(DOC, node.doc());
		if (node.container() != null) frame.addSlot(CONTAINER_NAME, node.container().name() + facetName(node.container().facetTarget()));
		addType(frame, node);
		addName(frame, node);
		if (node.isAbstract() || node.is(Decorable)) frame.addSlot(ABSTRACT, true);
		if (node.is(Decorable)) frame.addSlot(DECORABLE, true);
		node.flags().stream().filter(isLayerInterface()).forEach(tag -> frame.addSlot(FLAG, tag));
		if (node.parent() != null) frame.addTypes(CHILD);
		if (node.components().stream().anyMatch(c -> c.is(Instance)))
			frame.addSlot(META_TYPE, languageWorkingPackage + DOT + metaType(node));
		addVariables(frame, node);
	}

	private void addNonAbstractCreates(Frame frame, Node node) {
		if (node instanceof NodeReference) return;
		final List<Node> components = node.components();
		components.stream().
				filter(c -> !c.isAnonymous()).
				forEach(c -> {
					List<Frame> children = new ArrayList<>();
					collectChildren(c).stream().filter(n -> !n.isAnonymous() && !n.isAbstract() && !components.contains(n)).
							forEach(n -> children.add(createFrame(n.isReference() ? n.destinyOfReference() : n)));
					for (Frame child : children) frame.addSlot(CREATE, child.addTypes(NODE, OWNER));
				});
	}

	private Frame createFrame(Node node) {
		final Frame frame = new Frame().addTypes(REFERENCE, CREATE);
		frame.addTypes(getTypes(node, language));
		addName(frame, node);
		addVariables(frame, node);
		return frame;
	}

	private List<Node> collectChildren(Node parent) {
		Set<Node> set = new HashSet<>();
		for (Node child : parent.children()) {
			set.add(child);
			set.addAll(collectChildren(child));
		}
		return new ArrayList<>(set);
	}

	private void addType(Frame frame, Node node) {
		if (!(language instanceof Proteo || language instanceof Verso)) {
			frame.addSlot(CONCEPT_LAYER, language.doc(node.type()).layer());
			frame.addSlot(TYPE, nodeType(node, sizeConstraint(node)));
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

	private void addAllowedFacets(Frame frame, Node node) {
		for (String facet : node.allowedFacets()) {
			Frame available = new Frame().addTypes(AVAILABLE_FACET);
			available.addSlot(NAME, facet);
			FacetTarget facetTarget = findFacetTarget(findModel(node), node, facet);
			if (facetTarget == null) {
				LOG.severe("error finding facet: " + facet + " in node " + node.name());
				throw new RuntimeException("error finding facet: " + facet + " in node " + node.name());
			}
			if (facetTarget.owner().isAbstract()) available.addSlot(ABSTRACT, "null");
			available.addSlot(QN, cleanQn(getQn(facetTarget, facetTarget.owner(), workingPackage)));
			available.addSlot(STASH_QN, NameFormatter.stashQn(facetTarget.owner(), workingPackage));
			facetTarget.owner().variables().stream().filter(v -> v.size().isRequired()).forEach(variable -> {
				Frame varFrame = (Frame) context.build(variable);
				varFrame.addSlot(CONTAINER, node.name() + facetName(node.facetTarget()));
				available.addSlot(VARIABLE, varFrame.addTypes(REQUIRED));
			});
			frame.addSlot(AVAILABLE_FACET, available);
		}
	}

	private void addName(Frame frame, Node node) {
		if (node.name() != null) frame.addSlot(NAME, node.name() + facetName(node.facetTarget()));
		frame.addSlot(QN, cleanQn(buildQN(node)));
		frame.addSlot(STASH_QN, stashQN(node));
	}

	private String stashQN(Node node) {
		return stashQn(node instanceof NodeReference ? ((NodeReference) node).getDestiny() : node, workingPackage.toLowerCase());
	}

	private String buildQN(Node node) {
		return getQn(node instanceof NodeReference ? ((NodeReference) node).getDestiny() : node, workingPackage.toLowerCase());
	}

	private void addVariables(final Frame frame, Node node) {
		node.variables().forEach(v -> addVariable(frame, node, v));
		addTerminalVariables(node, frame);
	}

	private void addVariable(Frame frame, Node node, Variable variable) {
		final Frame varFrame = (Frame) context.build(variable);
		varFrame.addTypes(OWNER);
		varFrame.addSlot(CONTAINER, node.name() + facetName(node.facetTarget()));
		frame.addSlot(VARIABLE, varFrame);
	}

	void setInitNode(Node initNode) {
		this.initNode = initNode;
	}
}