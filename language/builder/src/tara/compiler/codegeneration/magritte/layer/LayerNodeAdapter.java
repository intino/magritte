package tara.compiler.codegeneration.magritte.layer;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.magritte.Generator;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.model.NodeReference;
import tara.language.model.Node;
import tara.language.model.Primitive;
import tara.language.model.Tag;
import tara.language.model.Variable;
import tara.language.semantics.Allow;
import tara.language.semantics.constraints.allowed.ReferenceParameterAllow;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static tara.compiler.codegeneration.magritte.NameFormatter.getQn;
import static tara.compiler.codegeneration.magritte.layer.TypesProvider.getTypes;

public class LayerNodeAdapter extends Generator implements Adapter<Node>, TemplateTags {
	private final String generatedLanguage;
	private final Language language;
	private Node initNode;
	private FrameContext context;

	public LayerNodeAdapter(String generatedLanguage, Language language, Node initNode) {
		this.generatedLanguage = generatedLanguage;
		this.language = language;
		this.initNode = initNode;
	}

	@Override
	public void execute(Frame frame, Node node, FrameContext context) {
		this.context = context;
		frame.addTypes(getTypes(node, language));
		addNodeInfo(frame, node);
		addComponents(frame, node, context);
	}

	private void addNodeInfo(Frame frame, Node node) {
		frame.addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
		if ((initNode != null && !node.equals(initNode)) || isInFacetTarget(node) != null) frame.addFrame(INNER, true);
		if (node.doc() != null) frame.addFrame(DOC, node.doc());
		addName(frame, node);
		addParent(frame, node);
		if (node.isAbstract()) {
			frame.addFrame(ABSTRACT, true);
			frame.addFrame(ABSTRACT_INNER, true);
		}
		addVariables(frame, node);
	}

	private void addName(Frame frame, Node node) {
		if (node.name() != null) frame.addFrame(NAME, node.name());
		frame.addFrame(QN, buildQN(node));
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

	private void addTerminalVariables(Node node, final Frame frame) {
		final Collection<Allow> allows = language.allows(node.type());
		if (allows == null) return;
		final List<Allow> terminalVariables = allows.stream().
			filter(allow -> allow instanceof Allow.Parameter &&
				((Allow.Parameter) allow).flags().contains(Tag.TERMINAL.name()) &&
				!isRedefined((Allow.Parameter) allow, node.variables())).collect(Collectors.toList());
		if (terminalVariables.isEmpty()) return;
		if (node.parent() == null)
			frame.addFrame(TYPE_DECLARATION, language.languageName().toLowerCase() + DOT + node.type());
		terminalVariables.forEach(allow -> addVariable(node.language().toLowerCase() + "." + node.type(), frame, (Allow.Parameter) allow));
	}

	private boolean isRedefined(Allow.Parameter allow, List<? extends Variable> variables) {
		for (Variable variable : variables) if (variable.name().equals(allow.name())) return true;
		return false;
	}

	private void addVariable(Frame frame, Variable variable) {
		final Frame varFrame = (Frame) context.build(variable);
		varFrame.addTypes("owner");
		frame.addFrame(VARIABLE, varFrame);
	}

	private void addVariable(String type, Frame frame, Allow.Parameter parameter) {
		frame.addFrame(VARIABLE, createFrame(parameter, type));
	}

	private Frame createFrame(final Allow.Parameter parameter, String type) {
		final Frame frame = new Frame();
		frame.addTypes(TypesProvider.getTypes(parameter));
		frame.addTypes(TARGET);
		frame.addFrame(NAME, parameter.name());
		frame.addFrame(CONTAINER_NAME, "metaType");
		frame.addFrame(QN, type);
		frame.addFrame(LANGUAGE, language.languageName().toLowerCase());
		frame.addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
		frame.addFrame(TYPE, parameter instanceof ReferenceParameterAllow ? parameter.name() : parameter.type());
		if (parameter.type().equals(Primitive.WORD))
			frame.addFrame(WORD_VALUES, parameter.allowedValues().toArray(new String[parameter.allowedValues().size()]));
		return frame;
	}

	public void setInitNode(Node initNode) {
		this.initNode = initNode;
	}
}