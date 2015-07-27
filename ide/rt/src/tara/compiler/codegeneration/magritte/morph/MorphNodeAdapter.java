package tara.compiler.codegeneration.magritte.morph;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.magritte.Generator;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.model.NodeReference;
import tara.language.model.Node;
import tara.language.model.Primitives;
import tara.language.model.Tag;
import tara.language.model.Variable;
import tara.language.semantics.Allow;
import tara.language.semantics.constraints.ReferenceParameterAllow;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static tara.compiler.codegeneration.magritte.morph.TypesProvider.getTypes;

public class MorphNodeAdapter extends Generator implements Adapter<Node>, TemplateTags {
	private final String generatedLanguage;
	private final Language language;
	private Node initNode;
	private FrameContext context;

	public MorphNodeAdapter(String generatedLanguage, Language language, Node initNode) {
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
		if ((initNode != null && !node.equals(initNode)) || isInFacetTarget(node) != null) frame.addFrame(INNER, true);
		if (node.doc() != null) frame.addFrame(DOC, node.doc());
		if (node.isAbstract() || node.isFacet()) frame.addFrame(ABSTRACT, true);
		addName(frame, node);
		addParent(frame, node);
		addVariables(frame, node);
	}

	private void addName(Frame frame, Node node) {
		frame.addFrame(NAME, node.name());
		frame.addFrame(QN, buildQN(node));
	}

	private String buildQN(Node node) {
		return NameFormatter.cleanQn(
			node instanceof NodeReference ?
				((NodeReference) node).getDestiny().qualifiedName() :
				node.qualifiedName()).
			replace(".", "$");
	}

	private void addParent(Frame frame, Node node) {
		final Node parent = node.parent();
		frame.addFrame(PARENT, parent != null ? NameFormatter.getQn(parent, generatedLanguage) : MORPH_PATH);
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
				!isRedefined(((Allow.Parameter) allow), node.variables())).collect(Collectors.toList());
		if (terminalVariables.isEmpty()) return;
		frame.addFrame(TYPE_DECLARATION, language.languageName().toLowerCase() + DOT + node.type());
		terminalVariables.forEach(allow -> addVariable(frame, (Allow.Parameter) allow));
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

	private void addVariable(Frame frame, Allow.Parameter parameter) {
		frame.addFrame(VARIABLE, createFrame(parameter));
	}

	private Frame createFrame(final Allow.Parameter parameter) {
		final Frame frame = new Frame();
		frame.addTypes(TypesProvider.getTypes(parameter));
		frame.addTypes(TARGET);
		frame.addFrame(NAME, parameter.name());
		frame.addFrame(CONTAINER, "type");
		frame.addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
		frame.addFrame(TYPE, parameter instanceof ReferenceParameterAllow ? parameter.allowedValues().get(0) : getType(parameter));//TODO QN completo
		if (parameter.type().equals(Variable.WORD))
			frame.addFrame(WORD_VALUES, parameter.allowedValues().toArray(new String[(parameter.allowedValues().size())]));
		return frame;
	}

	private String getType(Allow.Parameter parameter) {
		if (parameter.type().equalsIgnoreCase(Primitives.NATURAL)) return Primitives.INTEGER;
		else return parameter.type();
	}

	public void setInitNode(Node initNode) {
		this.initNode = initNode;
	}
}