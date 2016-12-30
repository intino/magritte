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
import org.siani.itrules.model.Frame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static io.intino.tara.compiler.codegeneration.magritte.NameFormatter.*;
import static io.intino.tara.compiler.codegeneration.magritte.layer.TypesProvider.getTypes;
import static io.intino.tara.compiler.dependencyresolution.ModelUtils.findFacetTarget;
import static io.intino.tara.lang.model.Tag.Instance;

class LayerNodeAdapter extends Generator implements Adapter<Node>, TemplateTags {
	private static final Logger LOG = Logger.getGlobal();
	private Node initNode;
	private FrameContext context;
	private final Level level;


	LayerNodeAdapter(String outDsl, Level level, Language language, Node initNode, String workingPackage, String languageWorkingPackage) {
		super(language, outDsl, workingPackage, languageWorkingPackage);
		this.level = level;
		this.initNode = initNode;
	}

	@Override
	public void execute(Frame frame, Node node, FrameContext context) {
		this.context = context;
		frame.addTypes(getTypes(node, language));
		frame.addFrame(MODEL_TYPE, level == Level.Platform ? PLATFORM : APPLICATION);
		addNodeInfo(frame, node);
		addComponents(frame, node, context);
		addNonAbstractCreates(frame, node);
		addAllowedFacets(frame, node, context);
	}

	private Model findModel(Node node) {
		NodeContainer result = node;
		while (result != null && !(result instanceof Model))
			result = result.container();
		return (Model) result;
	}

	private void addNodeInfo(Frame frame, Node node) {
		frame.addFrame(OUT_LANGUAGE, outDsl).addFrame(WORKING_PACKAGE, workingPackage);
		if ((initNode != null && !node.equals(initNode)) || isInFacet(node) != null) frame.addFrame(INNER, true);
		if (node.doc() != null) frame.addFrame(DOC, node.doc());
		if (node.container() != null) frame.addFrame(CONTAINER_NAME, node.container().name());
		addType(frame, node);
		addName(frame, node);
		addParent(frame, node);
		if (node.isAbstract()) frame.addFrame(ABSTRACT, true);
		node.flags().stream().filter(isLayerInterface()).forEach(tag -> frame.addFrame(FLAG, tag));
		if (node.parent() != null) frame.addTypes(CHILD);
		frame.addFrame(PARENT_SUPER, node.parent() != null);
		if (node.components().stream().anyMatch(c -> c.is(Instance)))
			frame.addFrame(META_TYPE, languageWorkingPackage + DOT + metaType(node));
		addVariables(frame, node);
	}

	private void addNonAbstractCreates(Frame frame, Node node) {
		if (node instanceof NodeReference) return;
		final List<Node> components = node.components();
		components.stream().
				filter(c -> !c.isAnonymous() && c.isAbstract()).
				forEach(c -> {
					List<Frame> children = new ArrayList<>();
					collectChildren(c).stream().filter(n -> !n.isAnonymous() && !n.isAbstract() && !components.contains(n)).
							forEach(n -> children.add(createFrame(n.isReference() ? n.destinyOfReference() : n)));
					for (Frame child : children) frame.addFrame(CREATE, child);
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
			frame.addFrame(CONCEPT_LAYER, language.doc(node.type()).layer());
			frame.addFrame(TYPE, nodeType(node, sizeConstraint(node)));
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

	private void addAllowedFacets(Frame frame, Node node, FrameContext context) {
		for (String facet : node.allowedFacets()) {
			Frame available = new Frame().addTypes(AVAILABLE_FACET);
			available.addFrame(NAME, facet);
			FacetTarget facetTarget = findFacetTarget(findModel(node), node, facet);
			if (facetTarget == null) {
				LOG.severe("error finding facet: " + facet + " in node " + node.name());
				throw new RuntimeException("error finding facet: " + facet + " in node " + node.name());
			}
			if (facetTarget.owner().isAbstract()) available.addFrame(ABSTRACT, "null");
			available.addFrame(QN, cleanQn(getQn(facetTarget, facetTarget.owner(), workingPackage)));
			available.addFrame(STASH_QN, NameFormatter.stashQn(facetTarget.owner(), workingPackage));
			final List<Variable> required = facetTarget.owner().variables().stream().filter(v -> v.size().isRequired()).collect(Collectors.toList());
			for (Variable variable : required) available.addFrame(VARIABLE, ((Frame) context.build(variable)).addTypes(REQUIRED));
			frame.addFrame(AVAILABLE_FACET, available);
		}
	}

	private void addName(Frame frame, Node node) {
		if (node.name() != null) frame.addFrame(NAME, node.name() + facetName(node.facetTarget()));
		frame.addFrame(QN, cleanQn(buildQN(node)));
		frame.addFrame(STASH_QN, stashQN(node));
	}

	private String stashQN(Node node) {
		return stashQn(node instanceof NodeReference ? ((NodeReference) node).getDestiny() : node, workingPackage.toLowerCase());
	}

	private String facetName(FacetTarget facetTarget) {
		return facetTarget != null ? facetTarget.target().replace(".", "") : "";
	}

	private String buildQN(Node node) {
		return getQn(node instanceof NodeReference ? ((NodeReference) node).getDestiny() : node, workingPackage.toLowerCase());
	}

	private void addVariables(final Frame frame, Node node) {
		node.variables().forEach(v -> addVariable(frame, v));
		addTerminalVariables(node, frame);
	}

	private void addVariable(Frame frame, Variable variable) {
		final Frame varFrame = (Frame) context.build(variable);
		varFrame.addTypes(OWNER);
		frame.addFrame(VARIABLE, varFrame);
	}

	void setInitNode(Node initNode) {
		this.initNode = initNode;
	}

}