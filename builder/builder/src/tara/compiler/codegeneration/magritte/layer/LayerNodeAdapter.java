package tara.compiler.codegeneration.magritte.layer;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.Resolver;
import tara.compiler.codegeneration.magritte.Generator;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.core.CompilerConfiguration.ModuleType;
import tara.compiler.model.Model;
import tara.compiler.model.NodeReference;
import tara.dsl.Proteo;
import tara.dsl.Verso;
import tara.lang.model.FacetTarget;
import tara.lang.model.Node;
import tara.lang.model.NodeContainer;
import tara.lang.model.Variable;
import tara.lang.model.rules.CompositionRule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static tara.compiler.codegeneration.magritte.NameFormatter.cleanQn;
import static tara.compiler.codegeneration.magritte.NameFormatter.getQn;
import static tara.compiler.codegeneration.magritte.layer.TypesProvider.getTypes;
import static tara.compiler.dependencyresolution.ModelUtils.findFacetTarget;
import static tara.lang.model.Tag.Instance;

class LayerNodeAdapter extends Generator implements Adapter<Node>, TemplateTags {
	private static final Logger LOG = Logger.getGlobal();
	private Node initNode;
	private FrameContext context;
	private final ModuleType moduleType;


	LayerNodeAdapter(String outDsl, ModuleType moduleType, Language language, Node initNode, String workingPackage) {
		super(language, outDsl, workingPackage);
		this.moduleType = moduleType;
		this.initNode = initNode;
	}

	@Override
	public void execute(Frame frame, Node node, FrameContext context) {
		this.context = context;
		frame.addTypes(getTypes(node, language));
		frame.addFrame(MODEL_TYPE, moduleType == ModuleType.Platform ? PLATFORM : APPLICATION);
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
		if (node.components().stream().filter(c -> c.is(Instance)).findFirst().isPresent())
			frame.addFrame(META_TYPE, language.languageName().toLowerCase() + DOT + metaType(node));
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

	private List<tara.lang.model.Node> collectChildren(tara.lang.model.Node parent) {
		Set<Node> set = new HashSet<>();
		for (tara.lang.model.Node child : parent.children()) {
			set.add(child);
			set.addAll(collectChildren(child));
		}
		return new ArrayList<>(set);
	}

	private void addType(Frame frame, Node node) {
		if (!(language instanceof Proteo || language instanceof Verso)) {
			frame.addFrame(CONCEPT_LAYER, language.doc(node.type()).layer());
			frame.addFrame(TYPE, nodeType(node, node.container().ruleOf(node)));
		}
	}

	private String nodeType(Node node, CompositionRule rule) {
		return Resolver.shortType(node.type()) + (!rule.isSingle() ? "List" : "");
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
			available.addFrame(STASH_QN, getQn(facetTarget, facetTarget.owner(), workingPackage));
			final List<Variable> required = facetTarget.owner().variables().stream().filter(v -> v.size().isRequired()).collect(Collectors.toList());
			for (Variable variable : required) available.addFrame(VARIABLE, ((Frame) context.build(variable)).addTypes(REQUIRED));
			frame.addFrame(AVAILABLE_FACET, available);
		}
	}

	private void addName(Frame frame, Node node) {
		if (node.name() != null) frame.addFrame(NAME, node.name() + facetName(node.facetTarget()));
		frame.addFrame(QN, cleanQn(buildQN(node)));
		frame.addFrame(STASH_QN, buildQN(node));
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