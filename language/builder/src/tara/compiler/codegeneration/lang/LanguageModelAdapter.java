package tara.compiler.codegeneration.lang;

import org.siani.itrules.engine.FrameBuilder;
import org.siani.itrules.model.AbstractFrame;
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
import tara.lang.model.rules.Size;
import tara.lang.semantics.Assumption;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.Context;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static tara.lang.model.Tag.*;

class LanguageModelAdapter implements org.siani.itrules.Adapter<Model>, TemplateTags {
	private final File rootFolder;
	private final int level;
	private Frame root;
	private Model model;
	private Set<Node> processed = new HashSet<>();
	private String generatedLanguage;
	private Locale locale;
	private Language language;

	public LanguageModelAdapter(String genLanguage, Locale locale, Language language, File rootFolder, int level) {
		this.generatedLanguage = genLanguage;
		this.locale = locale;
		this.language = language;
		this.rootFolder = rootFolder;
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
		this.root.addFrame(NAME, generatedLanguage);
		this.root.addFrame(TERMINAL, level == 1);
		this.root.addFrame(META_LANGUAGE, language.languageName());
		this.root.addFrame(LOCALE, locale.getLanguage());
	}

	private void buildNode(Node node) {
		if (alreadyProcessed(node)) return;
		Frame frame = new Frame().addTypes(NODE);
		if (!node.isAbstract() && !node.isAnonymous() && !node.isInstance()) {
			frame.addFrame(NAME, getName(node));
			addTypes(node, frame);
			addConstraints(node, frame);
			addAssumptions(node, frame);
			addDoc(node, frame);
			root.addFrame(NODE, frame);
		} else if (node.isInstance() && !node.isAnonymous()) root.addFrame(NODE, createDeclarationFrame(node));
		if (!node.isAnonymous()) node.components().stream().filter(inner -> !(inner instanceof NodeReference)).forEach(this::buildNode);
		addFacetTargetNodes(node);
	}

	private Frame createDeclarationFrame(Node node) {
		final Frame frame = new Frame().addTypes(DECLARATION).addFrame(QN, getName(node));
		addTypes(node, frame);
		frame.addFrame("path", buildPath(node));
		return frame;

	}

	private String buildPath(Node node) {
		final File file = new File(node.file());
		File modelRoot = new File(rootFolder.getParent(), "model");
		final String stashPath = file.getAbsolutePath().substring(modelRoot.getAbsolutePath().length() + 1);
		return stashPath.substring(0, stashPath.lastIndexOf("."));
	}

	private void addInheritedRules(Model model) {
		new LanguageInheritanceResolver(root, collectInstanceConstraints(), language, model).fill();
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
			addParameterConstraints(node.variables(), constraints, new LanguageParameterAdapter(language, level).addTerminalParameterConstraints(node, constraints) + terminalParameterIndex(constraints));
		}
//		if (!node.isInstance() && dynamicLoad) constraintsFrame.addFrame(CONSTRAINT, ANCHOR);
		addFacetConstraints(node, constraints);
	}

	private int terminalParameterIndex(Frame constraints) {
		final Iterator<AbstractFrame> iterator = constraints.frames("constraint");
		int index = 0;
		while (iterator.hasNext())
			if (iterator.next().is(PARAMETER)) index++;
		return index;
	}

	private void addParameterConstraints(List<? extends Variable> variables, Frame constrainsFrame, int parentIndex) {
		for (int index = 0; index < variables.size(); index++) {
			Variable variable = variables.get(index);
			if (!variable.isPrivate() && !finalWithValues(variable))
				new LanguageParameterAdapter(language, level).addParameterConstraint(constrainsFrame, parentIndex + index, variable, CONSTRAINT);
		}
	}

	private boolean finalWithValues(Variable variable) {
		return variable.isFinal() && !variable.defaultValues().isEmpty();
	}

//	private boolean isAllowedVariable(Variable variable) {
//		final NodeContainer container = variable.container();
//		return !variable.defaultValues().isEmpty() || ((container instanceof Node) && !((Node) container).isTerminal() && variable.isTerminal());
//	}

	private void addFacetConstraints(Node node, Frame allows) {
		for (String facet : node.allowedFacets()) {
			Frame frame = new Frame().addTypes(CONSTRAINT, FACET).addFrame(VALUE, facet);
			allows.addFrame(CONSTRAINT, frame);
			FacetTarget facetTarget = findFacetTarget(node, facet);
			if (facetTarget == null) continue;
			frame.addFrame(TERMINAL, ((Node) facetTarget.container()).isTerminal() + "");
			if (facetTarget.constraints() != null && !facetTarget.constraints().isEmpty())
				frame.addFrame(WITH, facetTarget.constraints().toArray(new String[facetTarget.constraints().size()]));
			addParameterConstraints(facetTarget.variables(), frame, 0);
			addComponentsConstraints(frame, facetTarget);
			addTerminalConstrains(facetTarget.container(), frame);
		}
		addTerminalFacets(node, allows);
	}

	private void addTerminalFacets(Node node, Frame frame) {
		final List<Constraint> facetAllows = language.constraints(node.type()).stream().filter(allow -> allow instanceof Constraint.Facet && ((Constraint.Facet) allow).terminal()).collect(toList());
		new LanguageInheritanceResolver(language).addConstraints(facetAllows, frame);
	}

	private void addTerminalConstrains(NodeContainer container, Frame frame) {
		final List<Constraint> constraints = language.constraints(container.type());
		List<Constraint> terminalConstraints = constraints.stream().
			filter(c ->
				c instanceof Constraint.Component && is(annotations(c), Instance) && !sizeComplete(container, typeOf(c)) ||
					c instanceof Constraint.Parameter && ((Constraint.Parameter) c).annotations().contains(Tag.Terminal.name())).
			collect(toList());
		new LanguageInheritanceResolver(language).addConstraints(terminalConstraints, frame);
	}

	private String typeOf(Constraint constraint) {
		return ((Constraint.Component) constraint).type();
	}

	private boolean sizeComplete(NodeContainer container, String type) {
		final List<Node> components = container.components().stream().filter(node -> node.type().equals(type)).collect(Collectors.toList());
		return !components.isEmpty() && container.ruleOf(components.get(0)).max() > components.size();
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
			else if (tag.equals(Tag.Component)) assumptions.addFrame(ASSUMPTION, Format.capitalize(Tag.Component.name()));
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
			forEach(include -> createComponentConstraint(frames, include));
	}

	private void createComponentConstraint(List<Frame> frames, Node component) {
		final List<Node> candidates = collectCandidates(component);
		final CompositionRule rule = component.container().ruleOf(component);
		if (rule.isRequired() && candidates.size() > 1) {
			final Frame oneOf = createOneOf(candidates, rule);
			if (!component.isAbstract()) oneOf.addFrame(CONSTRAINT, createComponentConstraint(component, rule));
			if (!component.isSub()) frames.add(oneOf);
		} else frames.addAll(
			candidates.stream().
				filter(candidate -> !component.isSub()).
				map(c -> createComponentConstraint(c, c.container().ruleOf(c))).collect(toList()));
	}

	private Frame createComponentConstraint(Node component, CompositionRule size) {
		Frame frame = new Frame().addTypes(CONSTRAINT, COMPONENT).addFrame(TYPE, getName(component));
		frame.addFrame(SIZE, component.isTerminal() && level > 1 ? transformSizeRuleOfTerminalNode(component) : new FrameBuilder().build(size));
		addParameterComponentConstraint(component, frame);
		return frame;
	}

	private Frame transformSizeRuleOfTerminalNode(Node component) {
		final CompositionRule rule = component.container().ruleOf(component);
		final Size size = new Size(0, rule.max(), rule);
		return (Frame) new FrameBuilder().build(size);
	}

	private void addParameterComponentConstraint(Node node, Frame frame) {
		Set<String> tags = node.annotations().stream().map(Tag::name).collect(Collectors.toCollection(LinkedHashSet::new));
		node.flags().stream().forEach(tag -> tags.add(convertTag(tag)));
		frame.addFrame(TAGS, tags.toArray(new String[tags.size()]));
	}

	private List<Node> collectCandidates(Node node) {
		List<Node> nodes = new ArrayList<>();
		if (node.isAnonymous() || node.isInstance()) return nodes;
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
		if (tag.equals(Tag.Terminal)) return Tag.Instance.name();
		return tag.name();
	}

	private String getName(Node node) {
		return node instanceof NodeReference ? ((NodeReference) node).getDestiny().qualifiedName() : node.qualifiedName();
	}

	private Frame createOneOf(Collection<Node> candidates, CompositionRule rule) {
		Frame frame = new Frame().addTypes("oneOf", CONSTRAINT);
		frame.addFrame(SIZE, new FrameBuilder().build(rule));
		for (Node candidate : candidates)
			frame.addFrame(CONSTRAINT, createComponentConstraint(candidate, rule));
		return frame;
	}

	private boolean isMainTerminal(Node node) {
		return node.isTerminal() && !node.isComponent();
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

}