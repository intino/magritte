package tara.compiler.codegeneration.lang;

import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.model.Model;
import tara.compiler.model.NodeImpl;
import tara.compiler.model.NodeReference;
import tara.compiler.model.VariableReference;
import tara.language.model.*;
import tara.language.semantics.Allow;
import tara.language.semantics.Assumption;
import tara.language.semantics.Constraint;
import tara.language.semantics.Context;
import tara.language.semantics.constraints.RuleFactory;

import java.util.*;
import java.util.stream.Collectors;

import static tara.language.model.Tag.*;

class LanguageModelAdapter implements org.siani.itrules.Adapter<Model>, TemplateTags {
	private final boolean dynamicLoad;
	private final int level;
	private Frame root;
	private Model model;
	private Set<Node> processed = new HashSet<>();
	private String languageName;
	private Locale locale;
	private Language language;

	public LanguageModelAdapter(String languageName, Locale locale, Language language, boolean dynamicLoad, int level) {
		this.languageName = languageName;
		this.locale = locale;
		this.language = language;
		this.dynamicLoad = dynamicLoad;
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
		addInheritedRules(model);
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
			addDoc(node, frame);
			root.addFrame(NODE, frame);
		}
		node.components().stream().filter(inner -> !(inner instanceof NodeReference)).forEach(this::buildNode);
		addFacetTargetNodes(node);
	}

	private void addInheritedRules(Model model) {
		List<String> cases = collectAllTerminalRules();
		new LanguageInheritanceFiller(root, cases, language, model).fill();
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

	private void addDoc(Node node, Frame frame) {
		final Frame docFrame = new Frame();
		docFrame.addTypes("doc").addFrame("file", node.file().replace("\\", "\\\\")).addFrame("line", node.line()).addFrame("doc", node.doc() != null ? format(node) : "");
		frame.addFrame(DOC, docFrame);
	}

	private String format(Node node) {
		return node.doc().replace("\"", "\\\"").replace("\n", "\\n");
	}

	private void addTypes(Node node, Frame frame) {
		if (node.type() == null) return;
		Frame typesFrame = new Frame().addTypes(NODE_TYPE);
		Set<String> typeSet = new LinkedHashSet<>();
		typeSet.add(node.type());
		Collection<String> languageTypes = getLanguageTypes(node);
		if (languageTypes != null)
			typeSet.addAll(languageTypes);
		for (String type : typeSet) typesFrame.addFrame(TYPE, type);
		if (typesFrame.slots().length > 0)
			frame.addFrame(NODE_TYPE, typesFrame);
	}

	private Collection<String> getLanguageTypes(Node node) {
		return language.types(node.type());
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
		final Collection<Allow> allows = language.allows(node.type());
		return types.stream().
			filter(type -> allows.stream().
				filter(allow -> allow instanceof Allow.Include && sameType(allow, type) && isAllowed((Allow.Include) allow, node)).findFirst().isPresent()).
			map(type -> new Frame().addTypes(MULTIPLE, ALLOW).addFrame(TYPE, type)).collect(Collectors.toList());
	}

	private boolean sameType(Allow allow, String type) {
		return ((Allow.Include) allow).type().equals(type);
	}

	private boolean isAllowed(Allow.Include allow, Node node) {
		return !(node instanceof Model) || isMain(allow);
	}

	private boolean isMain(Allow.Include allow) {
		for (Assumption assumption : language.assumptions(allow.type()))
			if (assumption instanceof Assumption.Main) return true;
		return false;
	}

	private void addRequires(Node node, Frame frame) {
		Frame requires = buildRequiredNodes(node);
		addContextRequires(node, requires);
		if (requires.slots().length != 0) frame.addFrame("requires", requires);
	}

	private void addContextAllows(Node node, Frame allows) {
		if (node instanceof NodeImpl) {
			int index = new LanguageParameterAdapter(language, model.getMetrics(), languageName).addTerminalParameterAllows(node, allows);
			addParameterAllows(node.variables(), allows, index);
		}
		if (!node.isNamed()) allows.addFrame(ALLOW, NAME);
		addFacetAllows(node, allows);
	}

	private void addParameterAllows(List<? extends Variable> variables, Frame allows, int parentIndex) {
		for (int index = 0; index < variables.size(); index++) {
			Variable variable = variables.get(index);
			if (!isAllowedVariable(variables.get(index)) || variable.defaultValues().isEmpty() && !variable.isTerminal() || !variable.defaultValues().isEmpty() && variable.isFinal())
				continue;
			new LanguageParameterAdapter(language, model.getMetrics(), languageName).addParameterRequire(allows, parentIndex + index, variable, ALLOW);
		}
	}

	private void addFacetAllows(Node node, Frame allows) {
		for (String facet : node.allowedFacets()) {
			Frame frame = new Frame().addTypes(ALLOW, "facet").addFrame("value", facet);
			allows.addFrame(ALLOW, frame);
			FacetTarget facetTarget = findFacetTarget(node, facet);
			if (facetTarget == null) continue;
			if (facetTarget.constraints() != null && !facetTarget.constraints().isEmpty())
				frame.addFrame("with", facetTarget.constraints().toArray(new String[facetTarget.constraints().size()]));
			addParameterAllows(facetTarget.variables(), frame, 0);
			addParameterRequires(facetTarget.variables(), frame, 0);//TRUE? añadir terminales
			addAllowedComponents(frame, facetTarget);
			addRequiredComponents(frame, facetTarget);
			addAllowedTerminalComponents(frame, facetTarget.container());
			addRequiredTerminalComponents(frame, facetTarget.container());
		}
	}

	private void addAllowedTerminalComponents(Frame frame, NodeContainer container) {
		final List<Allow> allows = language.allows(container.type());
		List<Allow> terminalAllows = allows.stream().
			filter(allow -> allow instanceof Allow.Include && LanguageInheritanceFiller.isTerminal(((Allow.Include) allow).annotations()) ||
				allow instanceof Allow.Parameter && ((Allow.Parameter) allow).flags().contains(Tag.TERMINAL_INSTANCE.name())).collect(Collectors.toList());
		new LanguageInheritanceFiller(language).addAllows(terminalAllows, frame);
	}

	private void addRequiredTerminalComponents(Frame frame, NodeContainer container) {
		final List<Allow> allows = language.allows(container.type());
		List<Allow> terminalRequires = allows.stream().
			filter(allow -> (allow instanceof Allow.Include && LanguageInheritanceFiller.isTerminal(((Allow.Include) allow).annotations()) && isRequired(((Allow.Include) allow).annotations())) ||
				(allow instanceof Allow.Parameter && ((Allow.Parameter) allow).flags().contains(Tag.TERMINAL_INSTANCE.name()) && ((Allow.Parameter) allow).flags().contains(Tag.REQUIRED.name()))).
			collect(Collectors.toList());
		new LanguageInheritanceFiller(language).addRequires(allowsToRequires(terminalRequires), frame);
	}

	private List<Constraint> allowsToRequires(List<Allow> allows) {
		List<Constraint> constraints = new ArrayList<>();
		for (Allow allow : allows)
			if (allow instanceof Constraint.Require.Name)
				constraints.add(RuleFactory._name());
			else if (allow instanceof Allow.Parameter)
				constraints.add((Constraint.Require.Parameter) allow);
			else if (allow instanceof Allow.Single)
				constraints.add(RuleFactory._single(((Allow.Single) allow).type()));
			else if (allow instanceof Allow.Include.Multiple)
				constraints.add(RuleFactory._multiple(((Allow.Multiple) allow).type()));
			else if (allow instanceof Allow.Include.OneOf) {
				final List<Constraint.Require> requires = includesOfOneOf(((Allow.OneOf) allow));
				constraints.add(RuleFactory.oneOf(requires.toArray(new Constraint.Require[requires.size()])));
			}
		return constraints;
	}

	private List<Constraint.Require> includesOfOneOf(Allow.OneOf oneOf) {
		List<Constraint.Require> requires = new ArrayList<>();
		for (Allow allow : oneOf.allows())
			if (allow instanceof Constraint.Require.Single)
				requires.add(RuleFactory._single(((Allow.Single) allow).type(), ((Allow.Single) allow).annotations()));
			else
				requires.add(RuleFactory._multiple(((Allow.Multiple) allow).type(), ((Allow.Multiple) allow).annotations()));
		return requires;
	}

	private boolean isRequired(Tag[] annotations) {
		return Arrays.asList(annotations).contains(Tag.REQUIRED);
	}

	private FacetTarget findFacetTarget(Node target, String facet) {
		for (Node node : model.components())
			if (facet.equals(node.name())) return correspondingTarget(node, target);
		return null;
	}

	private FacetTarget correspondingTarget(Node node, Node target) {
		for (FacetTarget facetTarget : node.facetTargets())
			if (facetTarget.targetNode().equals(target) || isChild(facetTarget.targetNode(), target))
				return facetTarget;
		return null;
	}

	private boolean isChild(Node parent, Node target) {
		if (parent.children().contains(target)) return true;
		for (Node node : parent.children()) {
			boolean isChild = isChild(node, target);
			if (isChild) return true;
		}
		return false;
	}

	private void addContextRequires(Node node, Frame requires) {
		if (node instanceof NodeImpl) {
			int index = new LanguageParameterAdapter(language, model.getMetrics(), languageName).addTerminalParameterRequires(node, requires);
			addParameterRequires(node.variables(), requires, index);
			if (!node.isTerminal()) addRequiredVariableRedefines(requires, node);
		}
		if (node.isNamed()) requires.addFrame(REQUIRE, NAME);
		if (dynamicLoad && !(node instanceof Model)) requires.addFrame(REQUIRE, "plate");
	}

	private void addRequiredVariableRedefines(Frame requires, Node node) {
		node.variables().stream().
			filter(variable -> variable.isTerminal() && variable instanceof VariableReference && !((VariableReference) variable).getDestiny().isTerminal()).
			forEach(variable -> requires.addFrame(REQUIRE, new Frame().addTypes("redefine", REQUIRE).
				addFrame(NAME, variable.name()).addFrame("supertype", variable.type())));
	}

	private void addParameterRequires(List<? extends Variable> variables, Frame requires, int index) {
		for (int i = 0; i < variables.size(); i++) {
			Variable variable = variables.get(i);
			if (isAllowedVariable(variables.get(i))) continue;
			new LanguageParameterAdapter(language, model.getMetrics(), languageName).addParameterRequire(requires, index + i, variable, REQUIRE);
		}
	}

	private boolean isAllowedVariable(Variable variable) {
		final NodeContainer container = variable.container();
		return !variable.defaultValues().isEmpty() || ((container instanceof Node) && !((Node) container).isTerminal() && variable.isTerminal());
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
		node.annotations().stream().filter(tag -> !tag.equals(Tag.SINGLE) || tag.equals(REQUIRED)).forEach(tag -> assumptions.addFrame(ASSUMPTION, tag.name().toLowerCase()));
		for (Tag tag : node.flags()) {
			if (tag.equals(Tag.TERMINAL)) assumptions.addFrame(ASSUMPTION, Tag.TERMINAL_INSTANCE);
			else if (tag.equals(Tag.FEATURE)) assumptions.addFrame(ASSUMPTION, Tag.FEATURE_INSTANCE);
			else if (tag.equals(Tag.FACET)) assumptions.addFrame(ASSUMPTION, Tag.FACET_INSTANCE);
			else if (tag.equals(Tag.MAIN)) assumptions.addFrame(ASSUMPTION, capitalize(Tag.MAIN.name()));
		}
	}

	static String capitalize(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}

	private Frame buildRequiredNodes(Node node) {
		Frame requires = new Frame().addTypes("requires");
		addRequiredComponents(requires, node);
		return requires;
	}

	private Frame buildAllowedNodes(NodeContainer node) {
		Frame allows = new Frame().addTypes("allows");
		if (!node.components().isEmpty()) addAllowedComponents(allows, node);
		return allows;
	}

	private void addAllowedComponents(Frame allows, NodeContainer container) {
		List<Frame> multipleNodes = new ArrayList<>();
		List<Frame> singleNodes = new ArrayList<>();
		collectSingleAndMultipleComponentAllows(container, multipleNodes, singleNodes);
		addAllowedNodes(allows, multipleNodes, singleNodes);
	}

	private void collectSingleAndMultipleComponentAllows(NodeContainer container, List<Frame> multipleNodes, List<Frame> singleNodes) {
		for (Node include : container.components()) {
			if (isRequiredNode(include) ||
				container instanceof Model && ((level == 1 && !include.isMain()) || (level == 2 && include.isTerminal() && !include.isMain())))
				continue;
			for (Node candidate : collectCandidates(include))
				if (include.isSingle()) singleNodes.add(createAllowedSingle(candidate));
				else multipleNodes.add(createAllowedMultiple(candidate));
		}
	}

	private boolean isRequiredNode(Node include) {
		return (include.isRequired() && !include.isTerminal()) || (level == 1 && include.isTerminal() && include.isRequired());
	}

	private void addRequiredComponents(Frame requires, NodeContainer container) {
		List<Frame> multipleNodes = new ArrayList<>();
		List<Frame> singleNodes = new ArrayList<>();
		collectSingleAndMultipleInnerRequires(requires, container, multipleNodes, singleNodes);
		addRequiredNodes(requires, multipleNodes, singleNodes);
	}

	private void collectSingleAndMultipleInnerRequires(Frame requires, NodeContainer container, List<Frame> multipleNodes, List<Frame> singleNodes) {
		for (Node include : container.components()) {
			if (!isRequiredNode(include)) continue;
			Collection<Node> candidates = collectCandidates(include);
			if (candidates.size() > 1) {
				final Frame oneOf = createOneOf(candidates, include);
				if (!include.isAbstract()) oneOf.addFrame(REQUIRE, include.isSingle() ?
					createRequiredSingle(include) : createRequiredMultiple(include));
				requires.addFrame(REQUIRE, oneOf);
			} else for (Node candidate : candidates) {
				if (include.isSub()) continue;
				if (include.isSingle()) singleNodes.add(createRequiredSingle(candidate));
				else multipleNodes.add(createRequiredMultiple(candidate));
			}
		}
	}

	private Collection<Node> collectCandidates(Node node) {
		List<Node> nodes = new ArrayList<>();
		if (node.isAnonymous() || node.isTerminalInstance()) return nodes;
		if (node.isAbstract()) getNonAbstractChildren(node, nodes);
		else nodes.add(node);
		return nodes;
	}

	private void getNonAbstractChildren(Node node, List<Node> nodes) {
		for (Node child : node.children()) {
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

	private Frame createAllowedSingle(Node node) {
		Frame frame = new Frame().addTypes(SINGLE, ALLOW).addFrame(TYPE, getName(node));
		for (Tag tag : node.annotations())
			frame.addFrame(TAGS, tag.name());
		for (Tag tag : node.flags()) {
			if (tag.equals(NAMED)) continue;
			frame.addFrame(TAGS, convertTag(tag));
		}
		return frame;
	}

	private Frame createAllowedMultiple(Node node) {
		Frame frame = new Frame().addTypes(MULTIPLE, ALLOW).addFrame(TYPE, getName(node));
		for (Tag tag : node.annotations())
			frame.addFrame(TAGS, tag.name());
		for (Tag tag : node.flags()) {
			if (tag.equals(NAMED)) continue;
			frame.addFrame(TAGS, convertTag(tag));
		}
		return frame;
	}

	private String convertTag(Tag tag) {
		if (tag.equals(Tag.FEATURE)) return FEATURE_INSTANCE.name();
		if (tag.equals(Tag.FACET)) return Tag.FACET_INSTANCE.name();
		if (tag.equals(Tag.TERMINAL)) return Tag.TERMINAL_INSTANCE.name();
		return tag.name();
	}

	private String getName(Node node) {
		return node instanceof NodeReference ? ((NodeReference) node).getDestiny().qualifiedName() : node.qualifiedName();
	}

	private Frame createOneOf(Collection<Node> candidates, Node node) {
		Frame frame = new Frame().addTypes("oneOf", REQUIRE);
		for (Node candidate : candidates)
			frame.addFrame(REQUIRE, node.isSingle() ?
				createRequiredSingle(candidate) :
				createRequiredMultiple(candidate));
		return frame;
	}

	private Frame createRequiredSingle(Node node) {
		Frame frame = new Frame().addTypes(SINGLE, REQUIRE).addFrame(TYPE, getName(node));
		for (Tag tag : node.annotations())
			frame.addFrame(TAGS, tag.name());
		return frame;
	}

	private Frame createRequiredMultiple(Node node) {
		Frame frame = new Frame().addTypes(MULTIPLE, REQUIRE).addFrame(TYPE, getName(node));
		for (Tag tag : node.annotations()) frame.addFrame(TAGS, tag.name());
		return frame;
	}

	private void addFacetTargetNodes(Node node) {
		for (FacetTarget target : node.facetTargets())
			target.components().stream().filter(inner -> !(inner instanceof NodeReference)).forEach(this::buildNode);
	}
}