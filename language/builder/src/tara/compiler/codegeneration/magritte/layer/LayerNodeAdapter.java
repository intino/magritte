package tara.compiler.codegeneration.magritte.layer;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.magritte.Generator;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.model.NodeReference;
import tara.lang.model.FacetTarget;
import tara.lang.model.Node;
import tara.lang.model.Tag;
import tara.lang.model.Variable;

import java.util.function.Predicate;

import static tara.compiler.codegeneration.magritte.NameFormatter.getQn;
import static tara.compiler.codegeneration.magritte.layer.TypesProvider.getTypes;

public class LayerNodeAdapter extends Generator implements Adapter<Node>, TemplateTags {
	private Node initNode;
	private FrameContext context;
	private final int level;


	public LayerNodeAdapter(String generatedLanguage, int level, Language language, Node initNode) {
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
	}

	private void addNodeInfo(Frame frame, Node node) {
		frame.addFrame(GENERATED_LANGUAGE, generatedLanguage);
		if ((initNode != null && !node.equals(initNode)) || isInFacet(node) != null) frame.addFrame(INNER, true);
		if (node.doc() != null) frame.addFrame(DOC, node.doc());
		addName(frame, node);
		addParent(frame, node);
		if (node.isAbstract()) frame.addFrame(ABSTRACT, true);
		node.flags().stream().filter(isLayerInterface()).forEach(tag -> frame.addFrame(FLAG, tag));
		if (node.isTerminal()) frame.addFrame(FLAG, Tag.Concept);
		if (node.parent() != null) frame.addTypes(CHILD);
		addVariables(frame, node);
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

	private void addParent(Frame frame, Node node) {
		final Node parent = node.parent();
		if (parent != null) frame.addFrame(PARENT, getQn(parent, generatedLanguage));
	}

	protected void addVariables(final Frame frame, Node node) {
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

	public void setInitNode(Node initNode) {
		this.initNode = initNode;
	}

}