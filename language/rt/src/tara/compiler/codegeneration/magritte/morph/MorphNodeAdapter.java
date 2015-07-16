package tara.compiler.codegeneration.magritte.morph;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.magritte.Generator;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.language.model.*;
import tara.language.semantics.Allow;

import java.util.Collection;
import java.util.List;

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
		addNodeInfo(node, frame);
		addComponents(node, frame, context);
	}

	private void addNodeInfo(Node node, Frame frame) {
		if ((initNode != null && !node.equals(initNode)) || isInFacetTarget(node) != null) frame.addFrame(INNER, true);
		if (node.doc() != null) frame.addFrame(DOC, node.doc());
		if (node.isAbstract() || node.isFacet()) frame.addFrame(ABSTRACT, true);
		addName(node, frame);
		addParent(node, frame);
		addVariables(node, frame);
		addParameters(node, frame);
	}

	private void addName(Node node, Frame frame) {
		frame.addFrame(NAME, node.name());
		frame.addFrame(QN, NameFormatter.cleanQn(node.qualifiedName()).replace(".","$"));
	}

	private void addParent(Node node, Frame newFrame) {
		final Node parent = node.parent();
		newFrame.addFrame(PARENT, parent != null ? NameFormatter.getQn(parent, generatedLanguage) : MORPH);
	}

	protected void addVariables(Node node, final Frame frame) {
		node.variables().stream().
			filter(variable -> !variable.isInherited()).
			forEach(variable -> addVariable(frame, variable));
		addTerminalVariables(node, frame);
	}

	private void addParameters(Node node, Frame frame) {
		node.parameters().stream().
			filter(p -> Primitives.NATIVE.equals(p.inferredType())).
			forEach(p -> addParameter(frame, p));
	}

	private void addTerminalVariables(Node node, final Frame frame) {
		final Collection<Allow> allows = language.allows(node.type());
		if (allows == null) return;
		allows.stream().
			filter(allow -> allow instanceof Allow.Parameter &&
				((Allow.Parameter) allow).flags().contains(Tag.TERMINAL.name()) &&
				!isRedefined(((Allow.Parameter) allow), node.variables())).
			forEach(allow -> addVariable(frame, (Allow.Parameter) allow));
	}

	private boolean isRedefined(Allow.Parameter allow, List<? extends Variable> variables) {
		for (Variable variable : variables) if (variable.name().equals(allow.name())) return true;
		return false;
	}

	private void addVariable(Frame frame, Variable variable) {
		frame.addFrame(VARIABLE, context.build(variable));
	}
	private void addParameter(Frame frame, Parameter parameter) {
		frame.addFrame(VARIABLE, context.build(parameter));
	}

	private void addVariable(Frame frame, Allow.Parameter variable) {
		frame.addFrame(VARIABLE, context.build(variable));
	}

	public void setInitNode(Node initNode) {
		this.initNode = initNode;
	}
}