package tara.compiler.codegeneration.magritte.morph;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.magritte.Generator;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.model.Facet;
import tara.compiler.model.FacetTarget;
import tara.compiler.model.Node;
import tara.compiler.model.Variable;
import tara.compiler.model.impl.NodeImpl;
import tara.compiler.model.impl.NodeReference;
import tara.semantic.Allow;
import tara.semantic.model.Tag;

import java.util.Collection;

import static tara.compiler.codegeneration.magritte.NameFormatter.getQn;
import static tara.compiler.codegeneration.magritte.morph.TypesProvider.getTypes;

public class MorphNodeAdapter extends Generator implements Adapter<NodeImpl>, TemplateTags {
	private final String project;
	private final String generatedLanguage;
	private final Language language;
	private Node initNode;
	private final int modelLevel;
	private FrameContext context;

	public MorphNodeAdapter(String project, String generatedLanguage, Language language, Node initNode, int modelLevel) {
		this.project = project;
		this.generatedLanguage = generatedLanguage;
		this.language = language;
		this.initNode = initNode;
		this.modelLevel = modelLevel;
	}

	@Override
	public void execute(Frame frame, NodeImpl node, FrameContext context) {
		this.context = context;
		frame.addTypes(getTypes(node, language));
		addNodeInfo(node, frame);
		addComponents(node, frame, context, modelLevel);
	}

	private void addNodeInfo(Node node, Frame frame) {
		if ((initNode != null && !node.equals(initNode)) || isInFacetTarget(node) != null) frame.addFrame(INNER, EMPTY);
		if (node.doc() != null) frame.addFrame(DOC, node.doc());
		if (node.isAbstract() || node.isFacet()) frame.addFrame(ABSTRACT, ABSTRACT.toLowerCase());
		if (modelLevel == 2) addAggregables(frame, node);
		addCreates(frame, node);
		addName(node, frame);
		addParent(node, frame);
		addTargets(node, frame);
		addFacets(node, frame);
		addVariables(node, frame);
		addTargets(node, frame);
		addFacets(node, frame);
		addComponents(node, frame);
	}

	private void addCreates(Frame frame, Node node) {
		node.components().stream().filter(include -> !include.isAnonymous() && !isInherited(include) && (include.isTerminal() || modelLevel == 1)).forEach(include -> {
			final Frame creates = new Frame();
			creates.addTypes("create");
			creates.addFrame(TYPE, NameFormatter.cleanQn(NameFormatter.getQn(include instanceof NodeReference ? ((NodeReference) include).getDestiny() : include, generatedLanguage)));
			creates.addFrame(NAME, include.getName());
			frame.addFrame("create", creates);
		});
	}

	private boolean isInherited(Node include) {
		return !(include instanceof NodeImpl) && !((NodeReference) include).isHas();
	}

	private void addAggregables(Frame frame, Node node) {
		if (node.isFinal() || !containsMultiple(node)) return;
		Frame aggregables = new Frame();
		if (isDefinition(node, modelLevel)) aggregables.addTypes(DEFINITION);
		frame.addFrame("aggregables", aggregables);
	}

	private boolean containsMultiple(Node node) {
		for (Node include : node.components())
			if (!include.isSingle() && !include.isTerminal() && !include.isFeature()) return true;
		return false;
	}

	private void addParent(Node node, Frame newFrame) {
		final Node parent = node.getParent();
		newFrame.addFrame(PARENT, parent != null ?
			NameFormatter.getQn(parent, generatedLanguage) :
			isDefinition(node, modelLevel) ? DEFINITION_PATH : MORPH_PATH);
	}

	private void addFacets(Node node, Frame newFrame) {
		for (final Facet facet : node.facets())
			newFrame.
				addFrame(FACETS, new Frame().addTypes(getTypes(facet)).
					addFrame(NAME, facet.getFacetType()));
	}

	private void addComponents(Node node, Frame frame) {
		for (Node include : node.components()) {
			if (include.isAnonymous()) continue;
			Frame includeFrame = new Frame().addTypes(TypesProvider.getTypesOfReference(include));
			if (isDefinition(include, modelLevel) && !isDefinition(node, modelLevel))
				includeFrame.addFrame(DEFINITION, EMPTY);
			if (!isDefinition(node, modelLevel)) includeFrame.addFrame(DEFINITION_AGGREGABLE, EMPTY);
			includeFrame.addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
			if (include instanceof NodeReference) {
				if (!((NodeReference) include).isHas() || include.isAnonymous()) continue;
				addNodeReferenceName((NodeReference) include, includeFrame);
			} else if (include instanceof NodeImpl)
				addName(include, includeFrame);
			frame.addFrame(COMPONENT, includeFrame);
		}
	}

	private void addName(Node node, Frame frame) {
		if (node.name() != null && !node.name().isEmpty())
			frame.addFrame(NAME, node.isAnonymous() ? node.getType() : node.name());
		frame.addFrame(QN, NameFormatter.getQn(node, generatedLanguage)).addFrame(PROJECT, project);
	}

	private void addNodeReferenceName(NodeReference node, Frame frame) {
		NodeImpl reference = node.getDestiny();
		frame.addFrame(NAME, reference.name());
		frame.addFrame(QN, getQn(reference, generatedLanguage)).addFrame(PROJECT, project);
	}

	private void addTargets(Node node, Frame newFrame) {
		for (final FacetTarget target : node.facetTargets())
			newFrame.addFrame(TARGETS, new Frame().addTypes(getTypes(target)).addFrame(NAME, target.target()));
	}

	protected void addVariables(Node node, final Frame frame) {
		node.variables().stream().
			filter(variable -> !variable.isInherited()).
			forEach(variable -> addVariable(frame, variable));
		addTerminalVariables(node, frame);
	}

	private void addTerminalVariables(Node node, final Frame frame) {
		final Collection<Allow> allows = language.allows(node.getType());
		if (allows == null) return;
		allows.stream().
			filter(allow -> allow instanceof Allow.Parameter && ((Allow.Parameter) allow).flags().contains(Tag.TERMINAL.name()) && !isRedefined(((Allow.Parameter) allow), node.variables())).
			forEach(allow -> addVariable(frame, (Allow.Parameter) allow));
	}

	private boolean isRedefined(Allow.Parameter allow, Collection<Variable> variables) {
		for (Variable variable : variables) if (variable.name().equals(allow.name())) return true;
		return false;
	}

	private void addVariable(Frame frame, Variable variable) {
		frame.addFrame(VARIABLE, context.build(variable));
	}

	private void addVariable(Frame frame, Allow.Parameter variable) {
		frame.addFrame(VARIABLE, context.build(variable));
	}

	public void setInitNode(Node initNode) {
		this.initNode = initNode;
	}
}