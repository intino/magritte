package tara.compiler.codegeneration.magritte.layer;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.magritte.Generator;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.core.operation.sourceunit.ParseOperation;
import tara.compiler.model.Model;
import tara.compiler.model.NodeReference;
import tara.lang.model.*;

import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static tara.compiler.codegeneration.magritte.NameFormatter.getQn;
import static tara.compiler.codegeneration.magritte.layer.TypesProvider.getTypes;
import static tara.compiler.dependencyresolution.ModelUtils.findFacetTarget;

class LayerNodeAdapter extends Generator implements Adapter<Node>, TemplateTags {
	private static final Logger LOG = Logger.getLogger(ParseOperation.class.getName());
	private static final String AVAILABLE_FACET = "availableFacet";
	private Node initNode;
	private FrameContext context;
	private final int level;


	LayerNodeAdapter(String generatedLanguage, int level, Language language, Node initNode) {
		super(language, generatedLanguage);
		this.level = level;
		this.initNode = initNode;
	}

	@Override
	public void execute(Frame frame, Node node, FrameContext context) {
		this.context = context;
		frame.addTypes(getTypes(node, language));
		frame.addFrame(MODEL_TYPE, level == 2 ? PLATFORM : APPLICATION);
		addNodeInfo(frame, node);
		addComponents(frame, node, context);
		addAllowedFacets(frame, node, context);
	}

	private Model findModel(Node node) {
		NodeContainer result = node;
		while (result != null && !(result instanceof Model))
			result = result.container();
		return (Model) result;
	}

	private void addNodeInfo(Frame frame, Node node) {
		frame.addFrame(GENERATED_LANGUAGE, generatedLanguage);
		if ((initNode != null && !node.equals(initNode)) || isInFacet(node) != null) frame.addFrame(INNER, true);
		if (node.doc() != null) frame.addFrame(DOC, node.doc());
		if (node.container() instanceof Node) frame.addFrame(CONTAINER_NAME, ((Node) node.container()).name());
		addName(frame, node);
		addParent(frame, node);
		if (node.isAbstract()) frame.addFrame(ABSTRACT, true);
		node.flags().stream().filter(isLayerInterface()).forEach(tag -> frame.addFrame(FLAG, tag));
		if (node.isTerminal()) frame.addFrame(FLAG, Tag.Concept);
		if (node.parent() != null) frame.addTypes(CHILD);
		addVariables(frame, node);
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
			available.addFrame(QN, NameFormatter.getJavaQN(generatedLanguage, facetTarget, facetTarget.owner()));
			final List<Variable> required = facetTarget.owner().variables().stream().filter(v -> v.size().isRequired()).collect(Collectors.toList());
			for (Variable variable : required) available.addFrame(VARIABLE, context.build(variable));
			frame.addFrame(AVAILABLE_FACET, available);
		}
	}

	private Predicate<Tag> isLayerInterface() {
		return tag -> tag.equals(Tag.Component) || tag.equals(Tag.Concept) || tag.equals(Tag.Feature) || tag.equals(Tag.Private) || tag.equals(Tag.Prototype);
	}

	private void addName(Frame frame, Node node) {
		if (node.name() != null) frame.addFrame(NAME, node.name() + facetName(node.facetTarget()));
		frame.addFrame(QN, buildQN(node));
	}

	private String facetName(FacetTarget facetTarget) {
		return facetTarget != null ? facetTarget.target() : "";
	}

	private String buildQN(Node node) {
		return getQn(node instanceof NodeReference ? ((NodeReference) node).getDestiny() : node, generatedLanguage.toLowerCase());
	}

	private void addVariables(final Frame frame, Node node) {
		node.variables().stream().
			filter(v -> !v.isInherited()).
			forEach(v -> addVariable(frame, v));
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