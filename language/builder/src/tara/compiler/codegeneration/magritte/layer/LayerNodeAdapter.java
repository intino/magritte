package tara.compiler.codegeneration.magritte.layer;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.magritte.Generator;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.model.NodeReference;
import tara.lang.model.Node;
import tara.lang.model.Primitive;
import tara.lang.model.Tag;
import tara.lang.model.Variable;
import tara.lang.model.rules.variable.NativeRule;
import tara.lang.model.rules.variable.ReferenceRule;
import tara.lang.model.rules.variable.WordRule;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.constraints.parameter.ReferenceParameter;

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
	private Frame initFrame;
	private final int level;


	public LayerNodeAdapter(String generatedLanguage, int level, Language language, Node initNode) {
		this.generatedLanguage = generatedLanguage;
		this.level = level;
		this.language = language;
		this.initNode = initNode;
	}

	@Override
	public void execute(Frame frame, Node node, FrameContext context) {
		this.context = context;
		frame.addTypes(getTypes(node, language));
		frame.addFrame(MODEL_TYPE, level == 2 ? ENGINE : DOMAIN);
		addNodeInfo(frame, node);
		addComponents(frame, node, context);
	}

	private void addNodeInfo(Frame frame, Node node) {
		frame.addFrame(GENERATED_LANGUAGE, generatedLanguage);
		if ((initNode != null && !node.equals(initNode)) || isInFacetTarget(node) != null) frame.addFrame(INNER, true);
		if (node.doc() != null) frame.addFrame(DOC, node.doc());
		addName(frame, node);
		addParent(frame, node);
		if (node.isAbstract() || node.isFacet()) {
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
		final Collection<Constraint> allows = language.constraints(node.type());
		if (allows == null) return;
		final List<Constraint> terminalVariables = allows.stream().
			filter(allow -> allow instanceof Constraint.Parameter &&
				((Constraint.Parameter) allow).annotations().contains(Tag.Terminal.name()) &&
				!isRedefined((Constraint.Parameter) allow, node.variables())).collect(Collectors.toList());
		if (terminalVariables.isEmpty()) return;
		if (node.parent() == null)
			frame.addFrame(TYPE_INSTANCE, language.languageName().toLowerCase() + DOT + node.type());
		terminalVariables.forEach(allow -> addVariable(node.language().toLowerCase() + "." + node.type(), frame, (Constraint.Parameter) allow));
	}

	private boolean isRedefined(Constraint.Parameter allow, List<? extends Variable> variables) {
		for (Variable variable : variables) if (variable.name().equals(allow.name())) return true;
		return false;
	}

	private void addVariable(Frame frame, Variable variable) {
		final Frame varFrame = (Frame) context.build(variable);
		varFrame.addTypes("owner");
		frame.addFrame(VARIABLE, varFrame);
	}

	private void addVariable(String type, Frame frame, Constraint.Parameter parameter) {
		frame.addFrame(VARIABLE, createFrame(parameter, type));
	}

	private Frame createFrame(final Constraint.Parameter parameter, String type) {
		final Frame frame = new Frame();
		frame.addTypes(TypesProvider.getTypes(parameter));
		frame.addTypes(TARGET);
		frame.addFrame(NAME, parameter.name());
		frame.addFrame(CONTAINER_NAME, "metaType");
		frame.addFrame(QN, type);
		frame.addFrame(LANGUAGE, language.languageName().toLowerCase());
		frame.addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
		frame.addFrame(TYPE, parameter instanceof ReferenceParameter ? language.languageName().toLowerCase() + DOT + ((ReferenceRule) parameter.rule()).getAllowedReferences().get(0) : parameter.type().getName());
		if (parameter.type().equals(Primitive.WORD)) {
			final List<String> words = ((WordRule) parameter.rule()).words();
			frame.addFrame(WORD_VALUES, words.toArray(new String[words.size()]));
		}
		if (parameter.type().equals(Primitive.FUNCTION))
			for (String i : ((NativeRule) parameter.rule()).imports())
				initFrame.addFrame(IMPORTS, i);
		return frame;
	}

	public void setInitNode(Node initNode) {
		this.initNode = initNode;
	}

	public void setInitFrame(Frame initFrame) {
		this.initFrame = initFrame;
	}
}