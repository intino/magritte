package tara.compiler.codegeneration.lang;

import org.siani.itrules.engine.FrameBuilder;
import org.siani.itrules.model.AbstractFrame;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.core.CompilerConfiguration.ModuleType;
import tara.compiler.model.Model;
import tara.compiler.model.NodeImpl;
import tara.compiler.model.NodeReference;
import tara.compiler.model.VariableReference;
import tara.dsl.ProteoConstants;
import tara.lang.model.*;
import tara.lang.model.rules.CompositionRule;
import tara.lang.model.rules.Size;
import tara.lang.semantics.Assumption;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.Context;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static tara.compiler.codegeneration.Format.capitalize;
import static tara.compiler.core.CompilerConfiguration.ModuleType.Application;
import static tara.compiler.core.CompilerConfiguration.ModuleType.Ontology;
import static tara.compiler.dependencyresolution.ModelUtils.findFacetTargetNode;
import static tara.lang.model.Tag.*;

class LanguageModelAdapter implements org.siani.itrules.Adapter<Model>, TemplateTags {
	private final ModuleType moduleType;
	private Frame root;
	private Model model;
	private Set<Node> processed = new HashSet<>();
	private String generatedLanguage;
	private Locale locale;
	private Language language;

	LanguageModelAdapter(String genLanguage, Locale locale, Language language, ModuleType type) {
		this.generatedLanguage = genLanguage;
		this.locale = locale;
		this.language = language;
		this.moduleType = type;
	}

	@Override
	public void execute(Frame root, Model model, FrameContext<Model> context) {
		this.root = root;
		this.model = model;
		initRoot();
		buildNode(model);
		addInheritedRules(model);
	}

	private void initRoot() {
		this.root.addFrame(NAME, generatedLanguage);
		this.root.addFrame(TERMINAL, moduleType.equals(Ontology) || moduleType.equals(Application));
		this.root.addFrame(META_LANGUAGE, language.languageName());
		this.root.addFrame(LOCALE, locale.getLanguage());
	}

	private void buildNode(Node node) {
		if (alreadyProcessed(node)) return;
		Frame frame = new Frame().addTypes(NODE);
		if (!node.isAbstract() && !node.isAnonymous() && !node.is(Instance)) {
			frame.addFrame(NAME, name(node));
			addTypes(node, frame);
			addConstraints(node, frame);
			addAssumptions(node, frame);
			addDoc(node, frame);
			root.addFrame(NODE, frame);
		} else if (node.is(Instance) && !node.isAnonymous()) root.addFrame(NODE, createInstanceFrame(node));
		if (!node.isAnonymous()) node.components().stream().filter(inner -> !(inner instanceof NodeReference)).forEach(this::buildNode);
	}

	private Frame createInstanceFrame(Node node) {
		final Frame frame = new Frame().addTypes(INSTANCE).addFrame(QN, name(node));
		addTypes(node, frame);
		frame.addFrame("path", generatedLanguage);
		return frame;
	}

	private void addInheritedRules(Model model) {
		new LanguageInheritanceManager(root, collectInstanceConstraints(), language, model).fill();
	}

	private List<String> collectInstanceConstraints() {
		return language.catalog().entrySet().stream().
			filter(entry -> isInstance(entry.getValue())).
			map(Map.Entry::getKey).collect(toList());
	}

	private boolean isInstance(Context context) {
		return context.assumptions().stream().filter(a -> a instanceof Assumption.Instance).count() == 1;
	}

	private void addDoc(Node node, Frame frame) {
		final Frame docFrame = new Frame();
		docFrame.addTypes(DOC).addFrame(LAYER, findLayer(node)).addFrame(FILE, node.file().replace("\\", "\\\\")).addFrame(LINE, node.line()).addFrame(DOC, node.doc() != null ? format(node) : "");
		frame.addFrame(DOC, docFrame);
	}

	private String findLayer(Node node) {
		if (node instanceof Model) return "";
		return NameFormatter.getQn(node, generatedLanguage);
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
		for (String type : typeSet)
			typesFrame.addFrame(TYPE, type);
		if (typesFrame.slots().length > 0) frame.addFrame(NODE_TYPE, typesFrame);
	}

	private Collection<String> getLanguageTypes(Node node) {
		return language.types(node.type());
	}

	private boolean alreadyProcessed(Node node) {
		return !processed.add(node);
	}

	private void addConstraints(Node node, Frame frame) {
		Frame constraints = buildNodeConstraints(node);
		addTerminalConstrains(node, constraints);
		addContextConstraints(node, constraints);
		frame.addFrame(CONSTRAINTS, constraints);
	}

	private void addContextConstraints(Node node, Frame constraints) {
		if (node instanceof NodeImpl) {
			if (!node.isTerminal()) addRequiredVariableRedefines(constraints, node);
			addParameterConstraints(node.variables(), constraints, new LanguageParameterAdapter(language, generatedLanguage, moduleType).addTerminalParameterConstraints(node, constraints) + terminalParameterIndex(constraints));
		}
//		if (!node.isInstance() && dynamicLoad) constraintsFrame.addFrame(CONSTRAINT, ANCHOR);
		if (node.type().startsWith(ProteoConstants.METAFACET + ":")) addMetaFacetConstraints(node, constraints);
		addFacetConstraints(node, constraints);

	}

	private int terminalParameterIndex(Frame constraints) {
		final Iterator<AbstractFrame> iterator = constraints.frames(CONSTRAINT);
		int index = 0;
		while (iterator.hasNext())
			if (iterator.next().is(PARAMETER)) index++;
		return index;
	}

	private void addParameterConstraints(List<? extends Variable> variables, Frame constrainsFrame, int parentIndex) {
		int privateVariables = 0;
		for (int index = 0; index < variables.size(); index++) {
			Variable variable = variables.get(index);
			if (!variable.isPrivate() && !finalWithValues(variable))
				new LanguageParameterAdapter(language, generatedLanguage, moduleType).addParameterConstraint(constrainsFrame, parentIndex + index - privateVariables, variable, CONSTRAINT);
			else privateVariables++;
		}
	}

	private boolean finalWithValues(Variable variable) {
		return variable.isFinal() && !variable.values().isEmpty();
	}

	private void addMetaFacetConstraints(Node node, Frame constraints) {
		final FacetTarget facetTarget = node.facetTarget();
		if (!node.type().equals(ProteoConstants.METAFACET) || facetTarget == null || node.isAbstract()) return;
		final Node target = facetTarget.targetNode();
		if (target.isAbstract())
			for (Node child : target.children())
				createMetaFacetConstraint(child, facetTarget.constraints(), constraints);
		else createMetaFacetConstraint(target, facetTarget.constraints(), constraints);
	}

	private void createMetaFacetConstraint(Node node, List<FacetTarget.Constraint> with, Frame constraints) {
		Frame frame = new Frame().addTypes(CONSTRAINT, METAFACET).addFrame(VALUE, node.qualifiedName());
		if (with != null && !with.isEmpty())
			frame.addFrame(WITH, with.stream().map(c -> c.node().qualifiedName()).collect(Collectors.toList()).toArray(new String[with.size()]));
		constraints.addFrame(CONSTRAINT, frame);
	}

	private void addFacetConstraints(Node node, Frame constraints) {
		for (String facet : node.allowedFacets()) {
			Frame frame = new Frame().addTypes(CONSTRAINT, FACET).addFrame(VALUE, facet);//TODO FULL FACET REFERENCE
			constraints.addFrame(CONSTRAINT, frame);
			Node facetTargetNode = findFacetTargetNode(model, node, facet);
			if (facetTargetNode == null || facetTargetNode.facetTarget() == null) continue;
			final FacetTarget facetTarget = facetTargetNode.facetTarget();
			frame.addFrame(TERMINAL, facetTargetNode.isTerminal() + "");
			if (facetTarget.constraints() != null && !facetTarget.constraints().isEmpty())
				for (FacetTarget.Constraint constraint : facetTarget.constraints())
					frame.addFrame(constraint.negated() ? WITHOUT : WITH, constraint.node().name());
			addParameterConstraints(facetTargetNode.variables(), frame, 0);
			addComponentsConstraints(frame, facetTargetNode);
			addTerminalConstrains(facetTargetNode, frame);
		}
		addTerminalFacets(node, constraints);
	}

	private void addTerminalFacets(Node node, Frame frame) {
		final List<Constraint> facetAllows = language.constraints(node.type()).stream().filter(allow -> allow instanceof Constraint.Facet && ((Constraint.Facet) allow).terminal()).collect(toList());
		new TerminalConstraintManager(language, node).addConstraints(facetAllows, frame);
	}

	private void addTerminalConstrains(Node container, Frame frame) {
		final List<Constraint> constraints = language.constraints(container.type());
		List<Constraint> terminalConstraints = constraints.stream().
			filter(c -> c instanceof Constraint.Component && is(annotations(c), Instance) && !sizeComplete(container, typeOf(c)) ||
				(c instanceof Constraint.Parameter && ((Constraint.Parameter) c).flags().contains(Tag.Terminal) && !isRedefined((Constraint.Parameter) c, container.variables()))).
			collect(toList());
		new TerminalConstraintManager(language, container).addConstraints(terminalConstraints, frame);
	}

	private boolean isRedefined(Constraint.Parameter allow, List<? extends Variable> variables) {
		for (Variable variable : variables) if (variable.name().equals(allow.name())) return true;
		return false;
	}

	private String typeOf(Constraint constraint) {
		return ((Constraint.Component) constraint).type();
	}

	private boolean sizeComplete(NodeContainer container, String type) {
		final List<Node> components = container.components().stream().filter(node -> node.type().equals(type)).collect(Collectors.toList());
		return !components.isEmpty() && container.ruleOf(components.get(0)).max() == components.size();
	}

	private void addRequiredVariableRedefines(Frame constraints, Node node) {
		node.variables().stream().
			filter(variable -> variable.isTerminal() && variable instanceof VariableReference && !((VariableReference) variable).getDestiny().isTerminal()).
			forEach(variable -> constraints.addFrame(CONSTRAINT, new Frame().addTypes("redefine", CONSTRAINT).
				addFrame(NAME, variable.name()).addFrame("supertype", variable.type())));
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
		node.annotations().stream().forEach(tag -> assumptions.addFrame(ASSUMPTION, tag.name().toLowerCase()));
		for (Tag tag : node.flags()) {
			if (tag.equals(Tag.Terminal)) assumptions.addFrame(ASSUMPTION, Instance);
			else if (tag.equals(Tag.Feature)) assumptions.addFrame(ASSUMPTION, Feature);
			else if (tag.equals(Tag.Component)) assumptions.addFrame(ASSUMPTION, capitalize(Tag.Component.name()));
			else if (tag.equals(Tag.Volatile)) assumptions.addFrame(ASSUMPTION, capitalize(Tag.Volatile.name()));
			else if (tag.equals(Tag.Versioned)) assumptions.addFrame(ASSUMPTION, capitalize(Tag.Versioned.name()));
		}
		if (node.type().startsWith(ProteoConstants.METAFACET + ":")) assumptions.addFrame(ASSUMPTION, Facet);
		if (node.isFacet()) assumptions.addFrame(ASSUMPTION, Terminal);
	}

	private Frame buildNodeConstraints(Node container) {
		Frame constraints = new Frame().addTypes(CONSTRAINTS);
		addComponentsConstraints(constraints, container);
		return constraints;
	}

	private void addComponentsConstraints(Frame constraints, Node container) {
		List<Frame> frames = new ArrayList<>();
		createComponentsConstraints(container, frames);
		if (!frames.isEmpty()) constraints.addFrame(CONSTRAINT, frames.toArray(new Frame[frames.size()]));
	}

	private void createComponentsConstraints(Node node, List<Frame> frames) {
		node.components().stream().
			filter(c -> (!(node instanceof Model) || !c.into(Component) || !c.into(Feature) && !(c.isTerminal() && (c.is(Component) || !c.into(Feature))))).
			forEach(component -> createComponentConstraint(frames, component));
		if (node.facetTarget() != null && node.facetTarget().parent() != null)
			node.facetTarget().parent().components().stream().
				filter(c -> !(node instanceof Model) || !c.into(Component) && !(c.isTerminal() && c.is(Component))).
				forEach(component -> createComponentConstraint(frames, component));
	}

	private void createComponentConstraint(List<Frame> frames, Node component) {
		final List<Node> candidates = collectCandidates(component);
		final CompositionRule rule = component.container().ruleOf(component);
		if (rule.isSingle() && candidates.size() > 1) {
			final Frame oneOf = createOneOf(candidates, rule);
			if (!component.isAbstract()) oneOf.addFrame(CONSTRAINT, createComponentConstraint(component, rule));
			if (!component.isSub()) frames.add(oneOf);
		} else frames.addAll(candidates.stream().
			filter(candidate -> !component.isSub()).
			map(c -> createComponentConstraint(c, c.container().ruleOf(c))).collect(toList()));
	}

	private Frame createComponentConstraint(Node component, CompositionRule size) {
		Frame frame = new Frame().addTypes(CONSTRAINT, COMPONENT).addFrame(TYPE, name(component));
		frame.addFrame(SIZE, component.isTerminal() && !isInTerminal(component) && Application.compareLevelWith(moduleType) > 0 ? transformSizeRuleOfTerminalNode(component) : new FrameBuilder().build(size));
		addTags(component, frame);
		return frame;
	}

	private static boolean isInTerminal(Node component) {
		return !(component.container() instanceof Node) || ((Node) component.container()).isTerminal();
	}

	private static Frame transformSizeRuleOfTerminalNode(Node component) {
		final CompositionRule rule = component.container().ruleOf(component);
		final Size size = new Size(0, rule.max(), rule);
		return (Frame) new FrameBuilder().build(size);
	}

	private static void addTags(Node node, Frame frame) {
		Set<String> tags = node.annotations().stream().map(Tag::name).collect(Collectors.toCollection(LinkedHashSet::new));
		node.flags().stream().forEach(tag -> tags.add(convertTag(tag)));
		frame.addFrame(TAGS, tags.toArray(new String[tags.size()]));
	}

	private static List<Node> collectCandidates(Node node) {
		Set<Node> nodes = new LinkedHashSet<>();
		if (node.isAnonymous() || node.is(Tag.Instance)) return new ArrayList<>(nodes);
		if (!node.isAbstract()) nodes.add(node);
		getNonAbstractChildren(node, nodes);
		return new ArrayList<>(nodes);
	}

	private static void getNonAbstractChildren(Node node, Set<Node> nodes) {
		for (Node child : node.children())
			if (child.isAbstract())
				getNonAbstractChildren(child, nodes);
			else if (child.container().equals(node.container()) || node.isReference()) nodes.add(child);
	}

	private static String convertTag(Tag tag) {
		if (tag.equals(Tag.Terminal)) return Tag.Instance.name();
		return tag.name();
	}

	private String name(Node node) {
		return node instanceof NodeReference ? ((NodeReference) node).getDestiny().qualifiedName() : node.qualifiedName();
	}

	private Frame createOneOf(Collection<Node> candidates, CompositionRule rule) {
		Frame frame = new Frame().addTypes(ONE_OF, CONSTRAINT);
		frame.addFrame(SIZE, new FrameBuilder().build(rule));
		for (Node candidate : candidates)
			frame.addFrame(CONSTRAINT, createComponentConstraint(candidate, rule));
		return frame;
	}

	private static List<Tag> annotations(Constraint constraint) {
		return ((Constraint.Component) constraint).annotations();
	}

	private static boolean is(List<Tag> annotations, Tag tag) {
		return annotations.contains(tag);
	}

}