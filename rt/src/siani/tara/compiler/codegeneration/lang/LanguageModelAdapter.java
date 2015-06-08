package siani.tara.compiler.codegeneration.lang;

import org.siani.itrules.model.Frame;
import siani.tara.Language;
import siani.tara.compiler.codegeneration.magritte.TemplateTags;
import siani.tara.compiler.model.FacetTarget;
import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.Variable;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.compiler.model.impl.NodeReference;
import siani.tara.compiler.model.impl.VariableReference;
import siani.tara.semantic.Allow;
import siani.tara.semantic.Assumption;
import siani.tara.semantic.model.Context;
import siani.tara.semantic.model.Tag;

import java.util.*;
import java.util.stream.Collectors;

import static siani.tara.semantic.model.Tag.IMPLICIT;
import static siani.tara.semantic.model.Tag.REQUIRED;

class LanguageModelAdapter implements org.siani.itrules.Adapter<Model>, TemplateTags {
	private final boolean plateRequired;
	private final int level;
	private Frame root;
	private Model model;
	private Set<Node> processed = new HashSet<>();
	private String languageName;
	private Locale locale;
	private siani.tara.Language language;

	public LanguageModelAdapter(String languageName, Locale locale, Language language, boolean plateRequired, int level) {
		this.languageName = languageName;
		this.locale = locale;
		this.language = language;
		this.plateRequired = plateRequired;
		this.level = level;
	}

	@Override
	public void execute(Frame root, Model model, FrameContext<Model> context) {
		this.root = root;
		this.model = model;
		root.addFrame(NAME, languageName);
		root.addFrame(TERMINAL, level == 1);
		root.addFrame(LOCALE, locale.getLanguage());
		buildNode(model);
		addInheritedRules();
	}

	private void addInheritedRules() {
		List<String> cases = collectAllTerminalRules();
		new LanguageInheritanceFiller(root, cases, language).fill();
	}

	private List<String> collectAllTerminalRules() {
		return language.catalog().entrySet().stream().
			filter(entry -> isTerminalInstance(entry.getValue())).
			map(Map.Entry::getKey).collect(Collectors.toList());
	}

	private boolean isTerminalInstance(Context value) {
		for (Assumption assumption : value.assumptions())
			if (assumption instanceof Assumption.TerminalInstance) return true;
		return false;
	}

	private void buildNode(Node node) {
		if (alreadyProcessed(node)) return;
		Frame frame = new Frame().addTypes(NODE);
		if (!node.isAbstract() && !node.isAnonymous() && !node.isTerminalInstance()) {
			frame.addFrame(NAME, getName(node));
			addTypes(node, frame);
			addAllows(node, frame);
			addRequires(node, frame);
			addAssumptions(node, frame);
			root.addFrame(NODE, frame);
		}
		for (Node inner : node.getIncludedNodes()) {
			if (inner instanceof NodeReference) continue;
			buildNode(inner);
		}
		for (FacetTarget target : node.getFacetTargets())
			for (Node inner : target.getIncludedNodes()) {
				if (inner instanceof NodeReference) continue;
				buildNode(inner);
			}

	}

	private void addTypes(Node node, Frame frame) {
		if (node.getType() == null) return;
		Frame typesFrame = new Frame().addTypes("nodeType");
		Set<String> typeSet = new LinkedHashSet<>();
		typeSet.add(node.getType());
		Collection<String> languageTypes = getLanguageTypes(node);
		if (languageTypes != null)
			typeSet.addAll(languageTypes);
		for (String type : typeSet) typesFrame.addFrame(TYPE, type);
		if (typesFrame.slots().length > 0)
			frame.addFrame("nodeType", typesFrame);
	}

	private Collection<String> getLanguageTypes(Node node) {
		return language.types(node.getType());
	}

	private boolean alreadyProcessed(Node node) {
		return !processed.add(node);
	}

	private void addAllows(Node node, Frame frame) {
		Frame allows = buildAllowedNodes(node);
		for (Frame allowFrame : getContextTerminalConstrains(collectAllTerminalRules(), node))
			allows.addFrame(ALLOW, allowFrame);
		addContextAllows(node, allows);
		if (allows.slots().length != 0) frame.addFrame("allows", allows);
	}

	private Collection<Frame> getContextTerminalConstrains(List<String> types, Node node) {
		final Collection<Allow> allows = language.allows(node.getType());
		return types.stream().
			filter(type -> allows.stream().
				filter(allow -> allow instanceof Allow.Include && ((Allow.Include) allow).type().equals(type)).findFirst().isPresent()).
			map(type -> new Frame().addTypes(MULTIPLE, ALLOW).addFrame(TYPE, type)).collect(Collectors.toList());
	}

	private void addRequires(Node node, Frame frame) {
		Frame requires = buildRequiredNodes(node);
		addContextRequires(node, requires);
		if (requires.slots().length != 0) frame.addFrame("requires", requires);
	}

	private void addContextAllows(Node node, Frame allows) {
		if (node instanceof NodeImpl) addParameterAllows(node.getVariables(), allows);
		if (!node.isNamed() && !node.isPropertyInstance()) allows.addFrame("allow", NAME);
		addFacetAllows(node, allows);
	}

	private void addParameterAllows(List<Variable> variables, Frame allows) {
		for (int i = 0; i < variables.size(); i++) {
			Variable variable = variables.get(i);
			if (variable.getDefaultValues().isEmpty() && !variable.isTerminal()) continue;
			new LanguageParameterAdapter(language).addParameter(allows, i, variable, ALLOW);
		}
	}

	private void addFacetAllows(Node node, Frame allows) {
		for (String facet : node.getAllowedFacets()) {
			Frame frame = new Frame().addTypes(ALLOW, "facet").addFrame("value", facet);
			allows.addFrame(ALLOW, frame);
			FacetTarget facetNode = findFacetTarget(node, facet);
			if (facetNode == null) continue;
			addParameterAllows(facetNode.getVariables(), frame);
			addParameterRequires(facetNode.getVariables(), frame, true, 0);//TRUE? añadir terminales
		}
	}

	private FacetTarget findFacetTarget(Node target, String facet) {
		for (Node node : model.getIncludedNodes())
			if (facet.equals(node.getName())) return correspondingTarget(node, target);
		return null;
	}

	private FacetTarget correspondingTarget(Node node, Node target) {
		for (FacetTarget facetTarget : node.getFacetTargets())
			if (facetTarget.getTargetNode().equals(target)) //refactor to node hierarchy
				return facetTarget;
		return null;
	}

	private void addContextRequires(Node node, Frame requires) {
		if (node instanceof NodeImpl) {
			int index = new LanguageParameterAdapter(language).addTerminalParameters(node, requires);
			addParameterRequires(node.getVariables(), requires, node.isTerminal(), index);
			addRequiredVariableRedefines(requires, node);
		}
		if (node.isNamed()) requires.addFrame(REQUIRE, NAME);
		if (plateRequired && !(node instanceof Model)) requires.addFrame(REQUIRE, "plate");
	}

	private void addRequiredVariableRedefines(Frame requires, Node node) {
		node.getVariables().stream().
			filter(variable -> variable.isTerminal() && variable instanceof VariableReference && !((VariableReference) variable).getDestiny().isTerminal()).
			forEach(variable -> requires.addFrame(REQUIRE, new Frame().addTypes("redefine", REQUIRE).
				addFrame(NAME, variable.getName()).addFrame("supertype", variable.getType())));
	}

	private void addParameterRequires(List<Variable> variables, Frame requires, boolean terminalNode, int index) {
		for (int i = 0; i < variables.size(); i++) {
			Variable variable = variables.get(i);
			if (!variable.getDefaultValues().isEmpty() ||
				(variable.isTerminal() && !terminalNode)) continue;
			new LanguageParameterAdapter(language).addParameter(requires, index + i, variable, REQUIRE);
		}
	}

	private void addAssumptions(Node node, Frame frame) {
		Frame assumptions = buildAssumptions(node);
		if (assumptions.slots().length != 0) frame.addFrame("assumptions", assumptions);
	}

	private Frame buildAssumptions(Node node) {
		Frame assumptions = new Frame().addTypes("assumptions");
		addAnnotationAssumptions(node, assumptions);
		return assumptions;
	}

	private void addAnnotationAssumptions(Node node, Frame assumptions) {
		node.getAnnotations().stream().filter(tag -> !tag.equals(Tag.SINGLE) || tag.equals(REQUIRED)).forEach(tag -> assumptions.addFrame(ASSUMPTION, tag.name().toLowerCase()));
		for (Tag tag : node.getFlags()) {
			if (tag.name().toLowerCase().equals(TERMINAL))
				assumptions.addFrame(ASSUMPTION, Tag.TERMINAL_INSTANCE);
			else if (tag.equals(IMPLICIT)) assumptions.addFrame(ASSUMPTION, Tag.IMPLICIT_INSTANCE);
			else if (tag.equals(Tag.FEATURE)) assumptions.addFrame(ASSUMPTION, Tag.FEATURE_INSTANCE);
			else if (tag.equals(Tag.FACET)) assumptions.addFrame(ASSUMPTION, Tag.FACET_INSTANCE);
		}
	}

	private Frame buildRequiredNodes(Node node) {
		Frame requires = new Frame().addTypes("requires");
		addRequiredInnerNodes(requires, node);
		return requires;
	}

	private Frame buildAllowedNodes(Node node) {
		Frame allows = new Frame().addTypes("allows");
		if (!node.getIncludedNodes().isEmpty()) addAllowedInnerNodes(allows, node);
		return allows;
	}

	private void addAllowedInnerNodes(Frame allows, Node node) {
		List<Frame> multipleNodes = new ArrayList<>();
		List<Frame> singleNodes = new ArrayList<>();
		for (Node include : node.getIncludedNodes()) {
			if (include.isRequired() && !include.isTerminal()) continue;
			if (node instanceof Model && level == 1 && !include.isMain()) continue;
			for (Node candidate : collectCandidates(include))
				if (include.isSingle()) singleNodes.add(createAllowedSingle(candidate, include.getAnnotations()));
				else multipleNodes.add(createAllowedMultiple(candidate, include.getAnnotations()));
		}
		addAllowedNodes(allows, multipleNodes, singleNodes);
	}

	private void addRequiredInnerNodes(Frame requires, Node node) {
		List<Frame> multipleNodes = new ArrayList<>();
		List<Frame> singleNodes = new ArrayList<>();
		for (Node include : node.getIncludedNodes()) {
			if ((!include.isRequired() && !include.isImplicit()) || (include.isTerminal() && isGenerativeLanguage()))
				continue;
			Collection<Node> candidates = collectCandidates(include);
			if (include.isAbstract() && candidates.size() > 1) {
				requires.addFrame(REQUIRE, createOneOf(candidates, include));
			} else for (Node candidate : candidates)
				if (include.isSingle()) singleNodes.add(createRequiredSingle(candidate, include.getAnnotations()));
				else multipleNodes.add(createRequiredMultiple(candidate, include.getAnnotations()));
		}
		addRequiredNodes(requires, multipleNodes, singleNodes);
	}

	private boolean isGenerativeLanguage() {
		return level > 1;
	}

	private Collection<Node> collectCandidates(Node node) {
		List<Node> nodes = new ArrayList<>();
		if (node.isAnonymous() || node.isTerminalInstance()) return nodes;
		if (node.isAbstract()) getNonAbstractChildren(node, nodes);
		else nodes.add(node);
		return nodes;
	}

	private void getNonAbstractChildren(Node node, List<Node> nodes) {
		for (Node child : node.getChildren()) {
			if (child.isAbstract())
				getNonAbstractChildren(child, nodes);
			else nodes.add(child);
		}
	}

	private void addAllowedNodes(Frame allows, List<Frame> multipleNodeFrames, List<Frame> singleNodes) {
		if (!multipleNodeFrames.isEmpty())
			allows.addFrame(ALLOW, multipleNodeFrames.toArray(new Frame[multipleNodeFrames.size()]));
		if (!singleNodes.isEmpty())
			allows.addFrame(ALLOW, singleNodes.toArray(new Frame[singleNodes.size()]));
	}

	private void addRequiredNodes(Frame allows, List<Frame> multipleNodes, List<Frame> singleNodes) {
		if (!multipleNodes.isEmpty())
			allows.addFrame(REQUIRE, multipleNodes.toArray(new Frame[multipleNodes.size()]));
		if (!singleNodes.isEmpty())
			allows.addFrame(REQUIRE, singleNodes.toArray(new Frame[singleNodes.size()]));
	}

	private Frame createAllowedSingle(Node node, List<Tag> annotations) {
		Frame frame = new Frame().addTypes(SINGLE, ALLOW).addFrame(TYPE, getName(node));
		for (Tag tag : annotations)
			frame.addFrame("tags", tag.name());
		return frame;
	}

	private Frame createAllowedMultiple(Node node, List<Tag> annotations) {
		Frame frame = new Frame().addTypes(MULTIPLE, ALLOW).addFrame(TYPE, getName(node));
		for (Tag tag : annotations)
			frame.addFrame("tags", tag.name());
		return frame;
	}

	private String getName(Node node) {
		return node instanceof NodeReference ? ((NodeReference) node).getDestiny().getQualifiedName() : node.getQualifiedName();
	}

	private Frame createOneOf(Collection<Node> candidates, Node node) {
		Frame frame = new Frame().addTypes("oneOf", REQUIRE);
		for (Node candidate : candidates)
			frame.addFrame(REQUIRE, node.isSingle() ?
				createRequiredSingle(candidate, node.getAnnotations()) :
				createRequiredMultiple(candidate, node.getAnnotations()));
		return frame;
	}

	private Frame createRequiredSingle(Node node, Collection<Tag> annotations) {
		Frame frame = new Frame().addTypes(SINGLE, REQUIRE).addFrame(TYPE, getName(node));
		for (Tag tag : annotations) frame.addFrame("tags", tag.name());
		return frame;
	}

	private Frame createRequiredMultiple(Node node, Collection<Tag> annotations) {
		Frame frame = new Frame().addTypes(MULTIPLE, REQUIRE).addFrame(TYPE, getName(node));
		for (Tag tag : annotations) frame.addFrame("tags", tag.name());
		return frame;
	}

}