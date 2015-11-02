package tara.compiler.codegeneration.lang;

import org.siani.itrules.engine.FrameBuilder;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.Format;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.model.Model;
import tara.compiler.model.NodeImpl;
import tara.compiler.model.NodeReference;
import tara.compiler.model.VariableReference;
import tara.lang.model.*;
import tara.lang.model.rules.CompositionRule;
import tara.lang.semantics.Assumption;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.Context;

import java.util.*;
import java.util.stream.Collectors;

import static tara.lang.model.Tag.*;

class LanguageModelAdapter implements org.siani.itrules.Adapter<Model>, TemplateTags {
	private final int level;
	private Frame root;
	private Model model;
	private Set<Node> processed = new HashSet<>();
	private String languageName;
	private Locale locale;
	private Language language;

	public LanguageModelAdapter(String languageName, Locale locale, Language language, int level) {
		this.languageName = languageName;
		this.locale = locale;
		this.language = language;
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
			addConstraints(node, frame);
			addAssumptions(node, frame);
			addDoc(node, frame);
			root.addFrame(NODE, frame);
		}
		node.components().stream().filter(inner -> !(inner instanceof NodeReference)).forEach(this::buildNode);
		addFacetTargetNodes(node);
	}

	private void addInheritedRules(Model model) {
		List<String> cases = collectAllTerminalConstraints();
		new LanguageInheritanceFiller(root, cases, language, model).fill();
	}

	private List<String> collectAllTerminalConstraints() {
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

	private void addConstraints(Node node, Frame frame) {
		Frame constraints = buildNodeConstraints(node);
		addContextConstraints(node, constraints);
		for (Frame constraintFrame : getContextTerminalConstraints(collectAllTerminalConstraints(), node))
			constraints.addFrame(CONSTRAINT, constraintFrame);
		frame.addFrame(CONSTRAINTS, constraints);
	}

	private void addContextConstraints(Node node, Frame constraintsFrame) {
		if (node instanceof NodeImpl)
			addParameterConstraints(node.variables(), constraintsFrame, new LanguageParameterAdapter(language).addTerminalParameterAllows(node, constraintsFrame));
		if (node.isNamed()) constraintsFrame.addFrame(CONSTRAINT, NAME);
		addFacetConstraints(node, constraintsFrame);
	}


	private Collection<Frame> getContextTerminalConstraints(List<String> types, Node node) {
		return types.stream().
			filter(type -> language.constraints(node.type()).stream().
				filter(c -> c instanceof Constraint.Component && sameType(c, type) && isAllowed((Constraint.Component) c, node)).findFirst().isPresent()).
			map(type -> new Frame().addTypes(CONSTRAINT).addFrame(TYPE, type)).collect(Collectors.toList());
	}

	private void addParameterConstraints(List<? extends Variable> variables, Frame constrainsFrame, int parentIndex) {
		for (int index = 0; index < variables.size(); index++) {
			Variable variable = variables.get(index);
			if (isAllowedVariable(variables.get(index)) &&
				(!variable.defaultValues().isEmpty() || variable.isTerminal()) &&
				(variable.defaultValues().isEmpty() || !variable.isFinal()))
				new LanguageParameterAdapter(language).addParameterConstraint(constrainsFrame, parentIndex + index, variable, CONSTRAINT);
		}
	}

	private void addFacetConstraints(Node node, Frame allows) {
		for (String facet : node.allowedFacets()) {
			Frame frame = new Frame().addTypes(CONSTRAINT, FACET).addFrame(VALUE, facet);
			allows.addFrame(CONSTRAINT, frame);
			FacetTarget facetTarget = findFacetTarget(node, facet);
			if (facetTarget == null) continue;
			if (((Node) facetTarget.container()).isTerminal()) frame.addFrame(TERMINAL, "true");
			if (facetTarget.constraints() != null && !facetTarget.constraints().isEmpty())
				frame.addFrame(WITH, facetTarget.constraints().toArray(new String[facetTarget.constraints().size()]));
			addParameterConstraints(facetTarget.variables(), frame, 0);
			addComponentsConstraints(frame, facetTarget);
			addTerminalComponentConstrains(frame, facetTarget.container());
		}
		addTerminalFacets(node, allows);
	}

	private void addTerminalFacets(Node node, Frame frame) {
		final List<Constraint> facetAllows = language.constraints(node.type()).stream().filter(allow -> allow instanceof Constraint.Facet && ((Constraint.Facet) allow).terminal()).collect(Collectors.toList());
		new LanguageInheritanceFiller(language).addConstraints(facetAllows, frame);
	}

	private void addTerminalComponentConstrains(Frame frame, NodeContainer container) {
		final List<Constraint> allows = language.constraints(container.type());
		List<Constraint> terminalAllows = allows.stream().
			filter(constraint ->
				constraint instanceof Constraint.Component && is(annotations(constraint), TERMINAL_INSTANCE) ||
					constraint instanceof Constraint.Parameter && ((Constraint.Parameter) constraint).annotations().contains(TERMINAL_INSTANCE.name())).
			collect(Collectors.toList());
		new LanguageInheritanceFiller(language).addConstraints(terminalAllows, frame);
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

	private void addRequiredVariableRedefines(Frame requires, Node node) {
		node.variables().stream().
			filter(variable -> variable.isTerminal() && variable instanceof VariableReference && !((VariableReference) variable).getDestiny().isTerminal()).
			forEach(variable -> requires.addFrame(CONSTRAINT, new Frame().addTypes("redefine", CONSTRAINT).
				addFrame(NAME, variable.name()).addFrame("supertype", variable.type())));
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
			else if (tag.equals(Tag.MAIN)) assumptions.addFrame(ASSUMPTION, Format.capitalize(Tag.MAIN.name()));
		}
	}


	private Frame buildNodeConstraints(NodeContainer container) {
		Frame constraints = new Frame().addTypes(CONSTRAINTS);
		addComponentsConstraints(constraints, container);
		return constraints;
	}

	private void addComponentsConstraints(Frame allows, NodeContainer container) {
		List<Frame> frames = new ArrayList<>();
		createComponentsConstraints(container, frames);
		if (!frames.isEmpty()) allows.addFrame(CONSTRAINT, frames.toArray(new Frame[frames.size()]));
	}

	private void createComponentsConstraints(NodeContainer container, List<Frame> frames) {
		container.components().stream().
			filter(component -> !(container instanceof Model) ||
				isMainTerminal(component) || !component.isTerminal()).
			forEach(include -> createConstraintComponent(frames, include));
	}

	private void createConstraintComponent(List<Frame> frames, Node component) {
		final List<Node> candidates = collectCandidates(component);
		final CompositionRule rule = component.container().ruleOf(component);
		if (rule.isRequired() && candidates.size() > 1) {
			final Frame oneOf = createOneOf(candidates, rule);
			if (!component.isAbstract()) oneOf.addFrame(CONSTRAINT, createConstraintComponent(component));
			frames.add(oneOf);
		} else frames.addAll(
			candidates.stream().
				filter(candidate -> !component.isSub()).
				map(this::createConstraintComponent).collect(Collectors.toList()));
	}

	private Frame createConstraintComponent(Node node) {
		Frame frame = new Frame().addTypes(CONSTRAINT, COMPONENT).addFrame(TYPE, getName(node));
		frame.addFrame(SIZE, new FrameBuilder().build(node.container().ruleOf(node)));
		addParameterComponentConstraint(node, frame);
		return frame;
	}

	private void addParameterComponentConstraint(Node node, Frame frame) {
		for (Tag tag : node.annotations()) frame.addFrame(TAGS, tag.name());
		node.flags().stream().filter(tag -> !tag.equals(NAMED)).forEach(tag -> frame.addFrame(TAGS, convertTag(tag)));
	}

	private Frame createConstraintComponent(Node node, CompositionRule size) {
		Frame frame = new Frame().addTypes(CONSTRAINT, COMPONENT).addFrame(TYPE, getName(node));
		frame.addFrame(SIZE, new FrameBuilder().build(size));
		addParameterComponentConstraint(node, frame);
		return frame;
	}

	private List<Node> collectCandidates(Node node) {
		List<Node> nodes = new ArrayList<>();
		if (node.isAnonymous() || node.isTerminalInstance()) return nodes;
		if (node.isAbstract()) getNonAbstractChildren(node, nodes);
		else nodes.add(node);
		return nodes;
	}

	private void getNonAbstractChildren(Node node, List<Node> nodes) {
		for (Node child : node.children())
			if (child.isAbstract())
				getNonAbstractChildren(child, nodes);
			else nodes.add(child);
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

	private Frame createOneOf(Collection<Node> candidates, CompositionRule rule) {
		Frame frame = new Frame().addTypes("oneOf", CONSTRAINT);
		for (Node candidate : candidates)
			frame.addFrame(CONSTRAINT, createConstraintComponent(candidate, rule));
		return frame;
	}

	private boolean isMainTerminal(Node node) {
		return node.isTerminal() && node.isMain();
	}

	private void addFacetTargetNodes(Node node) {
		for (FacetTarget target : node.facetTargets())
			target.components().stream().filter(inner -> !(inner instanceof NodeReference)).forEach(this::buildNode);
	}

	private static List<Tag> annotations(Constraint constraint) {
		return ((Constraint.Component) constraint).annotations();
	}

	private static boolean is(List<Tag> annotations, Tag tag) {
		return annotations.contains(tag);
	}

	private boolean sameType(Constraint constraint, String type) {
		return ((Constraint.Component) constraint).type().equals(type);
	}

	private boolean isAllowed(Constraint.Component allow, Node node) {
		return !(node instanceof Model) || isMain(allow);
	}

	private boolean isMain(Constraint.Component allow) {
		for (Assumption assumption : language.assumptions(allow.type()))
			if (assumption instanceof Assumption.Main) return true;
		return false;
	}
}