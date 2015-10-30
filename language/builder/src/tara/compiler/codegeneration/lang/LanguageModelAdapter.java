package tara.compiler.codegeneration.lang;

import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.model.Model;
import tara.compiler.model.NodeImpl;
import tara.compiler.model.NodeReference;
import tara.compiler.model.VariableReference;
import tara.lang.model.*;
import tara.lang.semantics.Allow;
import tara.lang.semantics.Assumption;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.Context;
import tara.lang.semantics.constraints.RuleFactory;

import java.util.*;
import java.util.stream.Collectors;

import static tara.lang.model.Tag.*;

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
		initRoot();
		buildNode(model);
		addInheritedRules(model);
	}

	public void initRoot() {
		this.root.addFrame(NAME, languageName);
		this.root.addFrame(TERMINAL, level == 1);
		this.root.addFrame(LOCALE, locale.getLanguage());
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

	private boolean isTerminalInstance(Context context) {
		for (Assumption assumption : context.assumptions())
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
		for (Frame allowFrame : getContextTerminalAllows(collectAllTerminalRules(), node))
			allows.addFrame(ALLOW, allowFrame);
		addContextAllows(node, allows);
		if (allows.slots().length != 0) frame.addFrame(ALLOWS, allows);
	}

	private Collection<Frame> getContextTerminalAllows(List<String> types, Node node) {
		return types.stream().
			filter(type -> language.allows(node.type()).stream().
				filter(allow -> allow instanceof Allow.Include && !is(annotations(allow), Tag.REQUIRED) && sameType(allow, type) && isAllowed((Allow.Include) allow, node)).findFirst().isPresent()).
			map(type -> new Frame().addTypes(MULTIPLE, ALLOW).addFrame(TYPE, type)).collect(Collectors.toList());
	}

	private void addRequires(Node node, Frame frame) {
		Frame requires = buildRequiredNodes(node);
		addContextRequires(node, requires);
		for (Frame require : getContextTerminalRequires(collectAllTerminalRules(), node))
			requires.addFrame(REQUIRE, require);
		if (requires.slots().length != 0) frame.addFrame(REQUIRES, requires);
	}

	private Collection<Frame> getContextTerminalRequires(List<String> types, Node node) {
		List<Frame> frame = types.stream().
			filter(type -> language.allows(node.type()).stream().
				filter(allow -> correctTerminalRequired(node, allow, type)).findFirst().isPresent()).
			map(type -> new Frame().addTypes(MULTIPLE, REQUIRE).addFrame(TYPE, type)).collect(Collectors.toList());
		types.stream().
			filter(type -> language.constraints(node.type()).stream().
				filter(require -> correctTerminalRequired(node, require, type)).findFirst().isPresent()).
			map(type -> new Frame().addTypes(MULTIPLE, REQUIRE).addFrame(TYPE, type)).collect(Collectors.toList());
		return frame;
	}

	private boolean correctTerminalRequired(Node node, Allow allow, String type) {
		return !(allow instanceof Allow.Single && isDeclared(node, type)) &&
			allow instanceof Allow.Include && is(annotations(allow), Tag.REQUIRED) &&
			sameType(allow, type) && isAllowed((Allow.Include) allow, node);
	}

	private boolean isDeclared(Node node, String type) {
		for (Node node1 : node.components())
			if (node1.type().equals(type)) return true;
		return false;
	}

	private boolean correctTerminalRequired(Node node, Constraint require, String type) {
		return require instanceof Constraint.Has.Component &&
			is(annotations(require), Tag.REQUIRED) &&
			!is(annotations(require), Tag.SINGLE) &&
			sameType(require, type) &&
			isAllowed((Allow.Include) require, node);
	}

	private void addContextAllows(Node node, Frame allows) {
		if (node instanceof NodeImpl) {
			int index = new LanguageParameterAdapter(language).addTerminalParameterAllows(node, allows);
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
			new LanguageParameterAdapter(language).addParameterRequire(allows, parentIndex + index, variable, ALLOW);
		}
	}

	private void addFacetAllows(Node node, Frame allows) {
		for (String facet : node.allowedFacets()) {
			Frame frame = new Frame().addTypes(ALLOW, FACET).addFrame(VALUE, facet);
			allows.addFrame(ALLOW, frame);
			FacetTarget facetTarget = findFacetTarget(node, facet);
			if (facetTarget == null) continue;
			if (((Node) facetTarget.container()).isTerminal()) frame.addFrame(TERMINAL, "true");
			if (facetTarget.constraints() != null && !facetTarget.constraints().isEmpty())
				frame.addFrame(WITH, facetTarget.constraints().toArray(new String[facetTarget.constraints().size()]));
			addParameterAllows(facetTarget.variables(), frame, 0);
			addParameterRequires(facetTarget.variables(), frame, 0);//TRUE? añadir terminales
			addAllowedComponents(frame, facetTarget);
			addRequiredComponents(frame, facetTarget);
			addAllowedTerminalComponents(frame, facetTarget.container());
			addRequiredTerminalComponents(frame, facetTarget.container());
		}
		addTerminalFacets(node, allows);
	}

	private void addTerminalFacets(Node node, Frame frame) {
		final List<Allow> facetAllows = language.allows(node.type()).stream().filter(allow -> allow instanceof Allow.Facet && ((Allow.Facet) allow).terminal()).collect(Collectors.toList());
		new LanguageInheritanceFiller(language).addAllows(facetAllows, frame);
	}

	private void addAllowedTerminalComponents(Frame frame, NodeContainer container) {
		final List<Allow> allows = language.allows(container.type());
		List<Allow> terminalAllows = allows.stream().
			filter(allow ->
				allow instanceof Allow.Include && !is(annotations(allow), Tag.REQUIRED) && is(annotations(allow), TERMINAL_INSTANCE) ||
					allow instanceof Allow.Parameter && ((Allow.Parameter) allow).flags().contains(TERMINAL_INSTANCE.name())).
			collect(Collectors.toList());
		new LanguageInheritanceFiller(language).addAllows(terminalAllows, frame);
	}

	private void addRequiredTerminalComponents(Frame frame, NodeContainer container) {
		final List<Allow> allows = language.allows(container.type());
		List<Allow> terminalRequires = allows.stream().
			filter(allow -> (allow instanceof Allow.Include && LanguageInheritanceFiller.isTerminal(annotations(allow)) && is(annotations(allow), Tag.REQUIRED)) ||
				(allow instanceof Allow.Parameter && ((Allow.Parameter) allow).flags().contains(TERMINAL_INSTANCE.name()) && ((Allow.Parameter) allow).flags().contains(Tag.REQUIRED.name()))).
			collect(Collectors.toList());
		new LanguageInheritanceFiller(language).addRequires(allowsToRequires(terminalRequires), frame);
	}

	private List<Constraint> allowsToRequires(List<Allow> allows) {
		List<Constraint> hases = new ArrayList<>();
		for (Allow allow : allows)
			if (allow instanceof Constraint.Has.Name)
				hases.add(RuleFactory.name());
			else if (allow instanceof Allow.Parameter)
				hases.add((Constraint.Has.Parameter) allow);
			else if (allow instanceof Allow.Single)
				hases.add(RuleFactory._single(((Allow.Single) allow).type()));
			else if (allow instanceof Allow.Include.Multiple)
				hases.add(RuleFactory._multiple(((Allow.Multiple) allow).type()));
			else if (allow instanceof Allow.Include.OneOf) {
				final List<Constraint.Has> requires = includesOfOneOf(((Allow.OneOf) allow));
				hases.add(RuleFactory.oneOf(requires.toArray(new Constraint.Has[requires.size()])));
			}
		return hases;
	}

	private List<Constraint.Has> includesOfOneOf(Allow.OneOf oneOf) {
		List<Constraint.Has> requires = new ArrayList<>();
		for (Allow allow : oneOf.allows())
			if (allow instanceof Constraint.Has.Single)
				requires.add(RuleFactory._single(((Allow.Single) allow).type(), ((Allow.Single) allow).annotations()));
			else
				requires.add(RuleFactory._multiple(((Allow.Multiple) allow).type(), ((Allow.Multiple) allow).annotations()));
		return requires;
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
			int index = new LanguageParameterAdapter(language).addTerminalParameterRequires(node, requires);
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
			new LanguageParameterAdapter(language).addParameterRequire(requires, index + i, variable, REQUIRE);
		}
	}

	private boolean isAllowedVariable(Variable variable) {
		final NodeContainer container = variable.container();
		return !variable.defaultValues().isEmpty() || ((container instanceof Node) && !((Node) container).isTerminal() && variable.isTerminal());
	}

	private void addAssumptions(Node node, Frame frame) {
		Frame assumptions = buildAssumptions(node);
		if (assumptions.slots().length != 0) frame.addFrame(ASSUMPTIONS, assumptions);
	}

	private Frame buildAssumptions(Node node) {
		Frame assumptions = new Frame().addTypes(ASSUMPTIONS);
		addAnnotationAssumptions(node, assumptions);
		return assumptions;
	}

	private void addAnnotationAssumptions(Node node, Frame assumptions) {
		node.annotations().stream().filter(tag -> !tag.equals(Tag.SINGLE) || tag.equals(Tag.REQUIRED)).forEach(tag -> assumptions.addFrame(ASSUMPTION, tag.name().toLowerCase()));
		for (Tag tag : node.flags()) {
			if (tag.equals(Tag.TERMINAL)) assumptions.addFrame(ASSUMPTION, TERMINAL_INSTANCE);
			else if (tag.equals(Tag.FEATURE)) assumptions.addFrame(ASSUMPTION, FEATURE_INSTANCE);
			else if (tag.equals(Tag.FACET)) assumptions.addFrame(ASSUMPTION, FACET_INSTANCE);
			else if (tag.equals(Tag.MAIN)) assumptions.addFrame(ASSUMPTION, capitalize(Tag.MAIN.name()));
		}
	}

	static String capitalize(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}

	private Frame buildRequiredNodes(Node node) {
		Frame requires = new Frame().addTypes(REQUIRES);
		addRequiredComponents(requires, node);
		return requires;
	}

	private Frame buildAllowedNodes(NodeContainer container) {
		Frame allows = new Frame().addTypes(ALLOWS);
		if (!container.components().isEmpty()) addAllowedComponents(allows, container);
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
		if (tag.equals(Tag.FEATURE)) return Tag.FEATURE_INSTANCE.name();
		if (tag.equals(Tag.FACET)) return Tag.FACET_INSTANCE.name();
		if (tag.equals(Tag.TERMINAL)) return TERMINAL_INSTANCE.name();
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

	private static Tag[] annotations(Allow allow) {
		return ((Allow.Include) allow).annotations();
	}

	private static boolean is(Tag[] annotations, Tag tag) {
		return Arrays.asList(annotations).contains(tag);
	}

	private Tag[] annotations(Constraint require) {
		return ((Constraint.Has.Component) require).annotations();
	}

	private boolean sameType(Allow allow, String type) {
		return ((Allow.Include) allow).type().equals(type);
	}

	private boolean sameType(Constraint constraint, String type) {
		return ((Constraint.Has.Component) constraint).type().equals(type);
	}

	private boolean isAllowed(Allow.Include allow, Node node) {
		return !(node instanceof Model) || isMain(allow);
	}

	private boolean isMain(Allow.Include allow) {
		for (Assumption assumption : language.assumptions(allow.type()))
			if (assumption instanceof Assumption.Main) return true;
		return false;
	}
}