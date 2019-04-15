package io.intino.tara.compiler.codegeneration.lang;

import io.intino.tara.Language;
import io.intino.tara.compiler.codegeneration.magritte.NameFormatter;
import io.intino.tara.compiler.codegeneration.magritte.TemplateTags;
import io.intino.tara.compiler.model.Model;
import io.intino.tara.compiler.model.NodeImpl;
import io.intino.tara.compiler.model.NodeReference;
import io.intino.tara.compiler.model.VariableReference;
import io.intino.tara.compiler.shared.Configuration.Level;
import io.intino.tara.dsl.ProteoConstants;
import io.intino.tara.lang.model.*;
import io.intino.tara.lang.model.rules.Size;
import io.intino.tara.lang.model.rules.composition.NodeCustomRule;
import io.intino.tara.lang.semantics.Assumption;
import io.intino.tara.lang.semantics.Constraint;
import io.intino.tara.lang.semantics.Context;
import org.siani.itrules.engine.FrameBuilder;
import org.siani.itrules.model.AbstractFrame;
import org.siani.itrules.model.Frame;

import java.util.*;
import java.util.stream.Collectors;

import static io.intino.tara.compiler.codegeneration.Format.capitalize;
import static io.intino.tara.compiler.dependencyresolution.ModelUtils.findFacetTargetNode;
import static io.intino.tara.compiler.shared.Configuration.Level.Product;
import static io.intino.tara.lang.model.Tag.*;
import static java.util.stream.Collectors.toList;

class LanguageModelAdapter implements org.siani.itrules.Adapter<Model>, TemplateTags {
	private static final String FacetSeparator = ":";
	private final Level level;
	private final String workingPackage;
	private final String languageWorkingPackage;
	private Model model;
	private Set<Node> processed = new HashSet<>();
	private String outDSL;
	private int rootNumber = 0;
	private Locale locale;
	private Language language;

	LanguageModelAdapter(String outDSL, Locale locale, Language language, Level type, String workingPackage, String languageWorkingPackage) {
		this.outDSL = outDSL;
		this.locale = locale;
		this.language = language;
		this.level = type;
		this.workingPackage = workingPackage;
		this.languageWorkingPackage = languageWorkingPackage;
	}

	private static boolean isInTerminal(Node component) {
		return component.container().isTerminal();
	}

	private static Frame transformSizeRuleOfTerminalNode(Node component) {
		final Size rule = component.container().sizeOf(component);
		final Size size = new Size(0, rule.max(), rule);
		return (Frame) new FrameBuilder().build(size);
	}

	private static void addTags(Node node, Frame frame) {
		Set<String> tags = node.annotations().stream().map(Tag::name).collect(Collectors.toCollection(LinkedHashSet::new));
		node.flags().stream().filter(f -> !f.equals(Decorable) && !Tag.Required.equals(f)).forEach(tag -> tags.add(convertTag(tag)));
		frame.addSlot(TAGS, tags.toArray(new String[0]));
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

	private static List<Tag> annotations(Constraint constraint) {
		return ((Constraint.Component) constraint).annotations();
	}

	private static boolean is(List<Tag> annotations, Tag tag) {
		return annotations.contains(tag);
	}

	@Override
	public void adapt(Model model, org.siani.itrules.engine.Context context) {
		Frame root = context.frame();
		this.model = model;
		initRoot(root);
		buildRootNodes(model, root);
		addInheritedRules(model, root);
	}

	private void initRoot(Frame root) {
		root.addSlot(NAME, outDSL);
		root.addSlot(TERMINAL, level.equals(Product));
		root.addSlot(META_LANGUAGE, language.languageName());
		root.addSlot(LOCALE, locale.getLanguage());
	}

	private void buildRootNodes(Model model, Frame root) {
		Frame frame = new Frame().addTypes(NODE);
		createRuleFrame(model, frame, root);
		model.components().forEach(n -> {
			final Frame rootNodeFrame = new Frame(ROOT);
			rootNodeFrame.addSlot("number", rootNumber = ++rootNumber);
			rootNodeFrame.addSlot("language", outDSL);
			root.addSlot("root", rootNodeFrame);
			buildNode(n, rootNodeFrame);
		});
	}

	private void buildNode(Node node, Frame root) {
		if (alreadyProcessed(node)) return;
		Frame frame = new Frame().addTypes(NODE);
		if (!node.isAbstract() && !node.isAnonymous() && !node.is(Instance)) createRuleFrame(node, frame, root);
		else if (node.is(Instance) && !node.isAnonymous()) root.addSlot(NODE, createInstanceFrame(node));
		if (!node.isAnonymous())
			node.components().stream().filter(inner -> !(inner instanceof NodeReference)).forEach(n -> buildNode(n, root));
	}

	private void createRuleFrame(Node node, Frame frame, Frame root) {
		frame.addSlot(NAME, name(node));
		addTypes(node, frame);
		addConstraints(node, frame);
		addAssumptions(node, frame);
		addDoc(node, frame);
		root.addSlot(NODE, frame);
	}

	private Frame createInstanceFrame(Node node) {
		final Frame frame = new Frame().addTypes(INSTANCE).addSlot(QN, name(node));
		addTypes(node, frame);
		frame.addSlot("path", outDSL);
		return frame;
	}

	private void addInheritedRules(Model model, Frame root) {
		new LanguageInheritanceManager(root, instanceConstraints(), language, model).fill();
	}

	private List<String> instanceConstraints() {
		return language.catalog().entrySet().stream().
				filter(entry -> isInstance(entry.getValue())).
				map(Map.Entry::getKey).collect(toList());
	}

	private boolean isInstance(Context context) {
		return context.assumptions().stream().anyMatch(a -> a instanceof Assumption.Instance);
	}

	private void addDoc(Node node, Frame frame) {
		final Frame docFrame = new Frame();
		docFrame.addTypes(DOC).addSlot(LAYER, findLayer(node)).addSlot(FILE, node.file().replace("\\", "\\\\")).addSlot(LINE, node.line()).addSlot(DOC, node.doc() != null ? format(node) : "");
		frame.addSlot(DOC, docFrame);
	}

	private String findLayer(Node node) {
		if (node instanceof Model) return "";
		return NameFormatter.getQn(node, workingPackage);
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
		if (languageTypes != null) typeSet.addAll(languageTypes);
		for (String type : typeSet) typesFrame.addSlot(TYPE, type);
		if (typesFrame.slots().length > 0) frame.addSlot(NODE_TYPE, typesFrame);
	}

	private Collection<String> getLanguageTypes(Node node) {
		return language.types(node.type());
	}

	private boolean alreadyProcessed(Node node) {
		return !processed.add(node);
	}

	private void addConstraints(Node node, Frame frame) {
		Frame constraints = buildComponentConstraints(node);
		addTerminalConstrains(node, constraints);
		addContextConstraints(node, constraints);
		frame.addSlot(CONSTRAINTS, constraints);
	}

	private void addContextConstraints(Node node, Frame constraints) {
		if (node instanceof NodeImpl) {
			if (!node.isTerminal()) addRequiredVariableRedefines(constraints, node);
			addParameterConstraints(node.variables(), node.type().startsWith(ProteoConstants.FACET + FacetSeparator) ? node.name() : "", constraints,
					LanguageParameterAdapter.terminalParameters(language, node) + terminalParameterIndex(constraints));
		}
		if (node.type().startsWith(ProteoConstants.METAFACET + FacetSeparator))
			addMetaFacetConstraints(node, constraints);
		addFacetConstraints(node, constraints);
	}

	private int terminalParameterIndex(Frame constraints) {
		final Iterator<AbstractFrame> iterator = constraints.frames(CONSTRAINT);
		int index = 0;
		while (iterator.hasNext()) if (iterator.next().is(PARAMETER)) index++;
		return index;
	}

	private void addParameterConstraints(List<Variable> variables, String facet, Frame constrainsFrame, int parentIndex) {
		int privateVariables = 0;
		for (int index = 0; index < variables.size(); index++) {
			Variable variable = variables.get(index);
			if (!variable.isPrivate() && !finalWithValues(variable))
				new LanguageParameterAdapter(language, outDSL, workingPackage, languageWorkingPackage, level).addParameterConstraint(constrainsFrame, facet, parentIndex + index - privateVariables, variable, CONSTRAINT);
			else privateVariables++;
		}
	}

	private boolean finalWithValues(Variable variable) {
		return variable.isFinal() && !variable.values().isEmpty();
	}

	private void addMetaFacetConstraints(Node node, Frame constraints) {
		final FacetTarget facetTarget = node.facetTarget();
		if (!node.type().startsWith(ProteoConstants.METAFACET + FacetSeparator) || facetTarget == null || node.isAbstract())
			return;
		final Node target = facetTarget.targetNode();
		if (target.isAbstract())
			for (Node child : target.children())
				createMetaFacetConstraint(child, facetTarget.constraints(), constraints);
		else createMetaFacetConstraint(target, facetTarget.constraints(), constraints);
	}

	private void createMetaFacetConstraint(Node node, List<FacetTarget.Constraint> with, Frame constraints) {
		Frame frame = new Frame().addTypes(CONSTRAINT, METAFACET).addSlot(VALUE, node.qualifiedName());
		if (with != null && !with.isEmpty())
			frame.addSlot(WITH, with.stream().map(c -> c.node().qualifiedName()).collect(Collectors.toList()).toArray(new String[with.size()]));
		constraints.addSlot(CONSTRAINT, frame);
	}

	private void addFacetConstraints(Node node, Frame constraints) {
		for (String facet : node.allowedFacets()) {
			Node facetTargetNode = findFacetTargetNode(model, node, facet);
			if (facetTargetNode == null || facetTargetNode.facetTarget() == null || facetTargetNode.isAbstract())
				continue;
			Frame frame = new Frame().addTypes(CONSTRAINT, FACET).addSlot(VALUE, facet);//TODO FULL FACET REFERENCE
			constraints.addSlot(CONSTRAINT, frame);
			final FacetTarget facetTarget = facetTargetNode.facetTarget();
			frame.addSlot(TERMINAL, facetTargetNode.isTerminal() + "");
			if (facetTarget.constraints() != null && !facetTarget.constraints().isEmpty())
				for (FacetTarget.Constraint constraint : facetTarget.constraints())
					frame.addSlot(constraint.negated() ? WITHOUT : WITH, constraint.node().name());
			if (facetTargetNode.flags().contains(Required)) frame.addSlot("required", "true");
			addParameterConstraints(facetTargetNode.variables(), facet, frame, 0);
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
				filter(c -> validComponent(container, c) || validParameter(container, c)).
				collect(toList());
		new TerminalConstraintManager(language, container).addConstraints(terminalConstraints, frame);
	}

	private boolean validParameter(Node container, Constraint c) {
		if (!(c instanceof Constraint.Parameter)) return false;
		return ((Constraint.Parameter) c).flags().contains(Tag.Terminal) && !isRedefined((Constraint.Parameter) c, container.variables());
	}

	private boolean validComponent(Node container, Constraint c) {
		if (!(c instanceof Constraint.Component)) return false;
		return is(annotations(c), Instance) && !sizeComplete(container, typeOf(c));
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
		return !components.isEmpty() && container.sizeOf(components.get(0)).max() == components.size();
	}

	private void addRequiredVariableRedefines(Frame constraints, Node node) {
		node.variables().stream().
				filter(variable -> variable.isTerminal() && variable instanceof VariableReference && !((VariableReference) variable).getDestiny().isTerminal()).
				forEach(variable -> constraints.addSlot(CONSTRAINT, new Frame().addTypes("redefine", CONSTRAINT).
						addSlot(NAME, variable.name()).addSlot("supertype", variable.type())));
	}

	private void addAssumptions(Node node, Frame frame) {
		Frame assumptions = buildAssumptions(node);
		if (assumptions.slots().length != 0) frame.addSlot(ASSUMPTIONS, assumptions);
	}

	private Frame buildAssumptions(Node node) {
		Frame assumptions = new Frame().addTypes(ASSUMPTIONS);
		addAnnotationAssumptions(node, assumptions);
		return assumptions;
	}

	private void addAnnotationAssumptions(Node node, Frame assumptions) {
		node.annotations().forEach(tag -> assumptions.addSlot(ASSUMPTION, tag.name().toLowerCase()));
		for (Tag tag : node.flags()) {
			if (tag.equals(Tag.Terminal)) assumptions.addSlot(ASSUMPTION, Instance);
			else if (tag.equals(Tag.Feature)) assumptions.addSlot(ASSUMPTION, Feature);
			else if (tag.equals(Tag.Component)) assumptions.addSlot(ASSUMPTION, capitalize(Tag.Component.name()));
			else if (tag.equals(Tag.Volatile)) assumptions.addSlot(ASSUMPTION, capitalize(Tag.Volatile.name()));
		}
		if (node.type().startsWith(ProteoConstants.METAFACET + FacetSeparator)) assumptions.addSlot(ASSUMPTION, Facet);
		if (node.isFacet()) assumptions.addSlot(ASSUMPTION, Terminal);
	}

	private Frame buildComponentConstraints(Node container) {
		Frame constraints = new Frame().addTypes(CONSTRAINTS);
		addComponentsConstraints(constraints, container);
		return constraints;
	}

	private void addComponentsConstraints(Frame constraints, Node container) {
		List<Frame> frames = new ArrayList<>();
		createComponentsConstraints(container, frames);
		if (!frames.isEmpty()) constraints.addSlot(CONSTRAINT, frames.toArray(new Frame[frames.size()]));
	}

	private void createComponentsConstraints(Node node, List<Frame> frames) {
		node.components().stream().
				filter(c -> componentCompliant(node, c)).
				forEach(c -> {
					if (c.type().startsWith(ProteoConstants.METAFACET + FacetSeparator))
						createMetaFacetComponentConstraint(frames, c);
					else if (!c.isSub()) createComponentConstraint(frames, c);
				});
		if (node.facetTarget() != null && node.facetTarget().parent() != null)
			node.facetTarget().parent().components().stream().
					filter(c -> !(node instanceof Model) || !c.into(Component) && !(c.isTerminal() && c.is(Component))).
					forEach(c -> {
						if (c.type().startsWith(ProteoConstants.METAFACET + FacetSeparator))
							createMetaFacetComponentConstraint(frames, c);
						else createComponentConstraint(frames, c);
					});
	}

	private boolean componentCompliant(Node container, Node node) {
		return !(container instanceof NodeRoot) || rootCompliant(node);
	}

	private boolean rootCompliant(Node c) {
		return !c.is(Component) && !rootFacetOverComponentOrAbstract(c) && !c.is(Feature) && !(c.isTerminal() && (c.into(Component) || c.into(Feature)));
	}

	private boolean rootFacetOverComponentOrAbstract(Node node) {
		final Node targetNode = node.facetTarget() != null ? node.facetTarget().targetNode() : null;
		return node.type().startsWith(ProteoConstants.FACET + FacetSeparator) && targetNode != null &&
				(targetNode.is(Component) || !(targetNode.container() instanceof NodeRoot) || targetNode.isAbstract());
	}

	private void createMetaFacetComponentConstraint(List<Frame> frames, Node node) {
		final FacetTarget facetTarget = node.facetTarget();
		if (!node.type().startsWith(ProteoConstants.METAFACET + FacetSeparator) || facetTarget == null || node.isAbstract())
			return;
		final Node target = facetTarget.targetNode();
		if (target.isAbstract())
			for (Node child : target.children()) {
				Frame frame = new Frame().addTypes(CONSTRAINT, COMPONENT).addSlot(TYPE, node.name() + FacetSeparator + child.qualifiedName());
				frame.addSlot(SIZE, node.isTerminal() && Product.compareLevelWith(level) > 0 ? transformSizeRuleOfTerminalNode(node) : createRulesFrames(node.container().rulesOf(node)));
				addTags(node, frame);
				frames.add(frame);
			}
		else createComponentConstraint(frames, node);

	}

	private void createComponentConstraint(List<Frame> frames, Node component) {
		final List<Node> candidates = collectCandidates(component);
		final Size size = component.container().sizeOf(component);
		final List<Rule> allRules = component.container().rulesOf(component).stream().distinct().collect(toList());
		if ((size.isSingle() || size.isRequired() || component.isReference()) && candidates.size() > 1) {
			final Frame oneOf = createOneOf(candidates, allRules);
			if (!component.isAbstract()) oneOf.addSlot(CONSTRAINT, createComponentConstraint(component, allRules));
			if (!component.isSub()) frames.add(oneOf);
		} else frames.addAll(candidates.stream().filter(c -> componentCompliant(c.container(), c)).
				map(c -> createComponentConstraint(c, allRules)).collect(toList()));
	}

	private Frame createComponentConstraint(Node component, List<Rule> rules) {
		Frame frame = new Frame().addTypes(CONSTRAINT, COMPONENT).addSlot(TYPE, name(component));
		if (isTerminal(component)) frame.addSlot(SIZE, transformSizeRuleOfTerminalNode(component));
		else frame.addSlot(SIZE, (AbstractFrame[]) createRulesFrames(rules));
		addTags(component, frame);
		return frame;
	}

	private boolean isTerminal(Node component) {
		return component.isTerminal() && !isInTerminal(component) && Product.compareLevelWith(level) > 0;
	}

	private String name(Node node) {
		return node instanceof NodeReference ? ((NodeReference) node).getDestiny().qualifiedName() : node.qualifiedName();
	}

	private Frame createOneOf(Collection<Node> candidates, List<Rule> rules) {
		Frame frame = new Frame().addTypes(ONE_OF, CONSTRAINT);
		frame.addSlot(RULE, (AbstractFrame[]) createRulesFrames(rules));
		for (Node candidate : candidates)
			frame.addSlot(CONSTRAINT, createComponentConstraint(candidate, rules));
		return frame;
	}

	private AbstractFrame[] createRulesFrames(List<Rule> rules) {
		return rules.stream().map(rule -> rule instanceof NodeCustomRule ? buildCustomRuleFrame((NodeCustomRule) rule) : (Frame) new FrameBuilder().build(rule)).toArray(AbstractFrame[]::new);
	}

	private Frame buildCustomRuleFrame(NodeCustomRule rule) {
		return new Frame().addTypes("rule", "customRule").addSlot("qn", rule.loadedClass().getName());
	}
}