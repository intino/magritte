package siani.tara.compiler.codegeneration.magritte;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import siani.tara.Language;
import siani.tara.compiler.model.*;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.compiler.model.impl.NodeReference;
import siani.tara.compiler.model.impl.VariableReference;
import siani.tara.semantic.Allow;
import siani.tara.semantic.constraints.ReferenceParameterAllow;
import siani.tara.semantic.model.Tag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static siani.tara.compiler.codegeneration.magritte.MorphCreatorHelper.getTypes;
import static siani.tara.compiler.codegeneration.magritte.NameFormatter.getQn;

public class MorphNodeAdapter implements Adapter<NodeImpl>, TemplateTags {
	private final String project;
	private final String generatedLanguage;
	private final Language language;
	private Node initNode;

	public MorphNodeAdapter(String project, String generatedLanguage, Language language, Node initNode) {
		this.project = project;
		this.generatedLanguage = generatedLanguage;
		this.language = language;
		this.initNode = initNode;
	}

	@Override
	public void execute(Frame frame, NodeImpl node, FrameContext context) {
		frame.addTypes(getTypes(node, language));
		addNodeInfo(node, frame);
		addInner(node, frame, context);
	}

	private void addNodeInfo(Node node, Frame frame) {
		if ((initNode != null && !node.equals(initNode)) || inFacetTarget(node) != null) frame.addFrame(INNER, "");
		if (node.getDoc() != null) frame.addFrame(DOC, node.getDoc());
		addName(node, frame);
		addParent(node, frame);
		addTargets(node, frame);
		addFacets(node, frame);
		addVariables(node, frame);
		addTargets(node, frame);
		addFacets(node, frame);
		addComponents(node, frame);
	}

	private FacetTarget inFacetTarget(Node node) {
		NodeContainer container = node.getContainer();
		while (container != null)
			if (container instanceof FacetTarget) return (FacetTarget) container;
			else container = container.getContainer();
		return null;
	}

	private void addName(Node node, Frame frame) {
		if (node.getName() != null && !node.getName().isEmpty())
			frame.addFrame(NAME, node.isAnonymous() ? node.getType() : node.getName());
		frame.addFrame(QN, getQn(node, generatedLanguage)).addFrame(PROJECT, project);
	}

	private void addInner(Node node, Frame frame, FrameContext context) {
		node.getIncludedNodes().stream().
			filter(inner -> !(inner instanceof NodeReference) && !inner.isAnonymous()).
			forEach(inner -> frame.addFrame("node", context.build(inner)));
	}

	private void addParent(Node node, Frame newFrame) {
		final Node parent = node.getParent();
		newFrame.addFrame(PARENT, parent != null ?
			getQn(parent, generatedLanguage) :
			isDefinition(node) ? DEFINITION : MORPH);
	}

	private boolean isDefinition(Node node) {//TODO si no va a llegar a M0. saber además si la Feature no es de M1
		for (Tag tag : node.getFlags())
			if (tag.equals(Tag.FEATURE)) return true;
		return isInFeature(node.getContainer());

	}

	private boolean isInFeature(NodeContainer node) {
		NodeContainer nodeContainer = node;
		while (nodeContainer != null && nodeContainer instanceof Node)
			if (((Node) nodeContainer).isFeature()) return true;
			else nodeContainer = nodeContainer.getContainer();
		return false;
	}

	private void addFacets(Node node, Frame newFrame) {
		for (final Facet facet : node.getFacets())
			newFrame.addFrame(FACETS, new Frame().addTypes(getTypes(facet)).addFrame(NAME, facet.getType()));
	}

	private void addComponents(Node node, Frame frame) {
		for (Node include : node.getIncludedNodes()) {
			if (include.isAnonymous()) continue;
			Frame includeFrame = new Frame().addTypes(collectReferenceTypes(include));
			includeFrame.addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
			if (include instanceof NodeReference) {
				if (!((NodeReference) include).isHas() || include.isAnonymous()) continue;
				addNodeReferenceName((NodeReference) include, includeFrame);
			} else if (include instanceof NodeImpl)
				addName(include, includeFrame);
			frame.addFrame("component", includeFrame);
		}
	}

	private void addNodeReferenceName(NodeReference node, Frame frame) {
		NodeImpl reference = node.getDestiny();
		frame.addFrame(NAME, reference.getName());
		frame.addFrame(QN, getQn(reference, generatedLanguage)).addFrame(PROJECT, project);
	}

	private String[] collectReferenceTypes(Node node) {
		List<String> types = new ArrayList<>();
		types.add("nodeReference");
		if (node.isSingle()) types.add("single");
		if (node.intoSingle()) types.add("into_single");
		if (node.isRequired()) types.add("required");
		if (node.isFeature()) types.add("feature");
		types.addAll(node.getFlags().stream().map(Tag::name).collect(Collectors.toList()));
		return types.toArray(new String[types.size()]);
	}

	private void addTargets(Node node, Frame newFrame) {
		for (final FacetTarget target : node.getFacetTargets())
			newFrame.addFrame(TARGETS, new Frame().addTypes(getTypes(target)).addFrame(NAME, target.getTarget()));
	}

	protected void addVariables(Node node, final Frame frame) {
		node.getVariables().stream().
			filter(variable -> !variable.isInherited()).
			forEach(variable -> addVariable(frame, variable, isDefinition(node)));
		addTerminalVariables(node, frame, isDefinition(node));

	}

	private void addTerminalVariables(Node node, final Frame frame, boolean definition) {
		final Collection<Allow> allows = language.allows(node.getType());
		if (allows == null) return;
		allows.stream().
			filter(allow -> allow instanceof Allow.Parameter && ((Allow.Parameter) allow).flags().contains(Tag.TERMINAL.name()) && !isRedefined(((Allow.Parameter) allow), node.getVariables())).
			forEach(allow -> addVariable(frame, (Allow.Parameter) allow, definition));
	}

	private boolean isRedefined(Allow.Parameter allow, Collection<Variable> variables) {
		for (Variable variable : variables) if (variable.getName().equals(allow.name())) return true;
		return false;
	}

	private void addVariable(Frame frame, Variable variable, boolean definition) {
		Frame varFrame = createVarFrame(variable);
		if (definition) varFrame.addTypes("definition");
		frame.addFrame(VARIABLE, varFrame);
	}

	private void addVariable(Frame frame, Allow.Parameter variable, boolean definition) {
		Frame varFrame = createVarFrame(variable);
		if (definition) varFrame.addTypes("definition");
		frame.addFrame(VARIABLE, varFrame);
	}


	protected Frame createVarFrame(final Variable variable) {
		Frame frame = new Frame() {
			{
				addFrame(NAME, variable.getName());
				addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
				if (variable.getContract() != null) addFrame(CONTRACT, format(variable.getContract()));
				addFrame(TYPE, variable instanceof VariableReference ? getQn(((VariableReference) variable).getDestiny(), generatedLanguage) : getType());
				if (variable.getType().equals(Variable.WORD))
					addFrame(WORDS, variable.getAllowedValues().toArray(new String[(variable.getAllowedValues().size())]));
				else if (variable.getType().equals(Primitives.MEASURE)) {
//					TODO addFrame(MEASURE, variable.getContract());
//					if (((Attribute) variable).getMeasureValue() != null)
//						addFrame(MEASURE_VALUE, resolveMetric(((Attribute) variable).getMeasureValue()));
				}
			}

			private String format(String contract) {
				if (contract == null) return "";
				final int i = contract.indexOf(siani.tara.semantic.model.Variable.NATIVE_SEPARATOR);
				return (i >= 0) ? contract.substring(0, i) : contract;
			}

			private String getType() {
				if (variable.getType().equalsIgnoreCase(Primitives.NATURAL)) return Primitives.INTEGER;
				else return variable.getType();
			}
		};

		return frame.addTypes(MorphCreatorHelper.getTypes(variable));
	}

	protected Frame createVarFrame(final Allow.Parameter variable) {
		Frame frame = new Frame() {
			{
				addFrame(NAME, variable.name());
				addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
				addFrame(TYPE, variable instanceof ReferenceParameterAllow ? variable.allowedValues().get(0) : getType());//TODO QN completo
				if (variable.type().equals(Variable.WORD))
					addFrame(WORDS, variable.allowedValues().toArray(new String[(variable.allowedValues().size())]));
				else if (variable.type().equals(Primitives.MEASURE)) {
//					TODO addFrame(MEASURE, variable.getContract());
//					if (((Attribute) variable).getMeasureValue() != null)
//						addFrame(MEASURE_VALUE, resolveMetric(((Attribute) variable).getMeasureValue()));
				}
			}

			private String getType() {
				if (variable.type().equalsIgnoreCase(Primitives.NATURAL)) return Primitives.INTEGER;
				else return variable.type();
			}
		};

		return frame.addTypes(MorphCreatorHelper.getTypes(variable));
	}

	public void setInitNode(Node initNode) {
		this.initNode = initNode;
	}
}