package io.intino.tara.compiler.codegeneration.lang;

import io.intino.itrules.Frame;
import io.intino.itrules.FrameBuilder;
import io.intino.itrules.FrameBuilderContext;
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

import java.util.*;
import java.util.stream.Collectors;

import static io.intino.tara.compiler.codegeneration.Format.capitalize;
import static io.intino.tara.compiler.dependencyresolution.ModelUtils.findFacetTargetNode;
import static io.intino.tara.compiler.shared.Configuration.Level.Product;
import static io.intino.tara.lang.model.Tag.*;
import static java.util.stream.Collectors.toList;

class LanguageModelAdapter implements io.intino.itrules.Adapter<Model>, TemplateTags {
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

	@Override
	public void adapt(Model model, FrameBuilderContext context) {
		this.model = model;
		initRoot(context);
		buildRootNodes(model, context);
		addInheritedRules(model, context);
	}

	private void initRoot(FrameBuilderContext root) {
		root.add(NAME, outDSL);
		root.add(TERMINAL, level.equals(Product));
		root.add(META_LANGUAGE, language.languageName());
		root.add(LOCALE, locale.getLanguage());
	}

	private void buildRootNodes(Model model, FrameBuilderContext root) {
		FrameBuilder builder = new FrameBuilder(NODE);
		createRuleFrame(model, builder, root);
		model.components().forEach(n -> {
			final FrameBuilder rootNodeFrame = new FrameBuilder(ROOT);
			rootNodeFrame.add("number", rootNumber = ++rootNumber);
			rootNodeFrame.add("language", outDSL);
			root.add("root", rootNodeFrame.toFrame());
			buildNode(n, rootNodeFrame);
		});
	}

	private void buildNode(Node node, FrameBuilder root) {
		if (alreadyProcessed(node)) return;
		FrameBuilder frame = new FrameBuilder(NODE);
		if (!node.isAbstract() && !node.isAnonymous() && !node.is(Instance)) createRuleFrame(node, frame, root);
		else if (node.is(Instance) && !node.isAnonymous()) root.add(NODE, createInstanceFrame(node));
		if (!node.isAnonymous())
			node.components().stream().filter(inner -> !(inner instanceof NodeReference)).forEach(n -> buildNode(n, root));
	}

	private void createRuleFrame(Node node, FrameBuilder builder, FrameBuilderContext root) {
		builder.add(NAME, name(node));
		addTypes(node, builder);
		addConstraints(node, builder);
		addAssumptions(node, builder);
		addDoc(node, builder);
		root.add(NODE, builder.toFrame());
	}

	private Frame createInstanceFrame(Node node) {
		final FrameBuilder builder = new FrameBuilder(INSTANCE).add(QN, name(node));
		addTypes(node, builder);
		builder.add("path", outDSL);
		return builder.toFrame();
	}

	private void addInheritedRules(Model model, FrameBuilderContext root) {
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

	private void addDoc(Node node, FrameBuilder frame) {
		frame.add(DOC, new FrameBuilder(DOC).
				add(LAYER, findLayer(node)).
				add(FILE, node.file().replace("\\", "\\\\")).
				add(LINE, node.line()).
				add(DOC, node.doc() != null ? format(node) : "").
				toFrame());
	}

	private String findLayer(Node node) {
		if (node instanceof Model) return "";
		return NameFormatter.getQn(node, workingPackage);
	}

	private String format(Node node) {
		return node.doc().replace("\"", "\\\"").replace("\n", "\\n");
	}

	private void addTypes(Node node, FrameBuilder builder) {
		if (node.type() == null) return;
		FrameBuilder typesFrameBuilder = new FrameBuilder(NODE_TYPE);
		Set<String> typeSet = new LinkedHashSet<>();
		typeSet.add(node.type());
		Collection<String> languageTypes = getLanguageTypes(node);
		if (languageTypes != null) typeSet.addAll(languageTypes);
		for (String type : typeSet) typesFrameBuilder.add(TYPE, type);
		if (typesFrameBuilder.slots() > 0) builder.add(NODE_TYPE, typesFrameBuilder.toFrame());
	}

	private Collection<String> getLanguageTypes(Node node) {
		return language.types(node.type());
	}

	private boolean alreadyProcessed(Node node) {
		return !processed.add(node);
	}

	private void addConstraints(Node node, FrameBuilder builder) {
		FrameBuilder constraints = buildComponentConstraints(node);
		addTerminalConstrains(node, constraints);
		addContextConstraints(node, constraints);
		builder.add(CONSTRAINTS, constraints.toFrame());
	}

	private void addContextConstraints(Node node, FrameBuilder constraints) {
		if (node instanceof NodeImpl) {
			if (!node.isTerminal()) addRequiredVariableRedefines(constraints, node);
			addParameterConstraints(node.variables(), node.type().startsWith(ProteoConstants.FACET + FacetSeparator) ? node.name() : "", constraints,
					LanguageParameterAdapter.terminalParameters(language, node) + terminalParameterIndex(constraints.toFrame()));
		}
		if (node.type().startsWith(ProteoConstants.METAFACET + FacetSeparator))
			addMetaFacetConstraints(node, constraints);
		addFacetConstraints(node, constraints);
	}

	private int terminalParameterIndex(Frame constraints) {
		final Iterator<Frame> iterator = constraints.frames(CONSTRAINT);
		int index = 0;
		while (iterator.hasNext()) if (iterator.next().is(PARAMETER)) index++;
		return index;
	}

	private void addParameterConstraints(List<Variable> variables, String facet, FrameBuilder constrainsFrame, int parentIndex) {
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

	private void addMetaFacetConstraints(Node node, FrameBuilder constraints) {
		final FacetTarget facetTarget = node.facetTarget();
		if (!node.type().startsWith(ProteoConstants.METAFACET + FacetSeparator) || facetTarget == null || node.isAbstract())
			return;
		final Node target = facetTarget.targetNode();
		if (target.isAbstract())
			for (Node child : target.children())
				createMetaFacetConstraint(child, facetTarget.constraints(), constraints);
		else createMetaFacetConstraint(target, facetTarget.constraints(), constraints);
	}

	private void createMetaFacetConstraint(Node node, List<FacetTarget.Constraint> with, FrameBuilder constraints) {
		FrameBuilder builder = new FrameBuilder(CONSTRAINT, METAFACET).add(VALUE, node.qualifiedName());
		if (with != null && !with.isEmpty())
			builder.add(WITH, with.stream().map(c -> c.node().qualifiedName()).toArray(Object[]::new));
		constraints.add(CONSTRAINT, builder.toFrame());
	}

	private void addFacetConstraints(Node node, FrameBuilder constraintsBuilder) {
		for (String facet : node.allowedFacets()) {
			Node facetTargetNode = findFacetTargetNode(model, node, facet);
			if (facetTargetNode == null || facetTargetNode.facetTarget() == null || facetTargetNode.isAbstract()) continue;
			FrameBuilder builder = new FrameBuilder(CONSTRAINT, FACET).add(VALUE, facet);//TODO FULL FACET REFERENCE
			final FacetTarget facetTarget = facetTargetNode.facetTarget();
			builder.add(TERMINAL, facetTargetNode.isTerminal() + "");
			if (facetTarget.constraints() != null && !facetTarget.constraints().isEmpty())
				for (FacetTarget.Constraint constraint : facetTarget.constraints())
					builder.add(constraint.negated() ? WITHOUT : WITH, constraint.node().name());
			if (facetTargetNode.flags().contains(Required)) builder.add("required", "true");
			addParameterConstraints(facetTargetNode.variables(), facet, builder, 0);
			addComponentsConstraints(builder, facetTargetNode);
			addTerminalConstrains(facetTargetNode, builder);
			constraintsBuilder.add(CONSTRAINT, builder.toFrame());
		}
		addTerminalFacets(node, constraintsBuilder);
	}

	private void addTerminalFacets(Node node, FrameBuilder context) {
		final List<Constraint> facetAllows = language.constraints(node.type()).stream().filter(allow -> allow instanceof Constraint.Facet && ((Constraint.Facet) allow).terminal()).collect(toList());
		new TerminalConstraintManager(language, node).addConstraints(facetAllows, context);
	}

	private void addTerminalConstrains(Node container, FrameBuilder frame) {
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

	private void addRequiredVariableRedefines(FrameBuilder constraints, Node node) {
		node.variables().stream().
				filter(variable -> variable.isTerminal() && variable instanceof VariableReference && !((VariableReference) variable).getDestiny().isTerminal()).
				forEach(variable -> constraints.add(CONSTRAINT, new FrameBuilder("redefine", CONSTRAINT).add(NAME, variable.name()).add("supertype", variable.type()).toFrame()));
	}

	private void addAssumptions(Node node, FrameBuilder frame) {
		FrameBuilder assumptions = buildAssumptions(node);
		if (assumptions.slots() != 0) frame.add(ASSUMPTIONS, assumptions.toFrame());
	}

	private FrameBuilder buildAssumptions(Node node) {
		FrameBuilder assumptions = new FrameBuilder(ASSUMPTIONS);
		addAnnotationAssumptions(node, assumptions);
		return assumptions;
	}

	private void addAnnotationAssumptions(Node node, FrameBuilder assumptions) {
		node.annotations().forEach(tag -> assumptions.add(ASSUMPTION, tag.name().toLowerCase()));
		for (Tag tag : node.flags()) {
			if (tag.equals(Tag.Terminal)) assumptions.add(ASSUMPTION, Instance);
			else if (tag.equals(Tag.Feature)) assumptions.add(ASSUMPTION, Feature);
			else if (tag.equals(Tag.Component)) assumptions.add(ASSUMPTION, capitalize(Tag.Component.name()));
			else if (tag.equals(Tag.Volatile)) assumptions.add(ASSUMPTION, capitalize(Tag.Volatile.name()));
		}
		if (node.type().startsWith(ProteoConstants.METAFACET + FacetSeparator)) assumptions.add(ASSUMPTION, Facet);
		if (node.isFacet()) assumptions.add(ASSUMPTION, Terminal);
	}

	private FrameBuilder buildComponentConstraints(Node container) {
		FrameBuilder constraints = new FrameBuilder(CONSTRAINTS);
		addComponentsConstraints(constraints, container);
		return constraints;
	}

	private void addComponentsConstraints(FrameBuilder constraints, Node container) {
		List<Frame> frames = new ArrayList<>();
		createComponentsConstraints(container, frames);
		frames.forEach(frame -> constraints.add(CONSTRAINT, frame));
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
				FrameBuilder builder = new FrameBuilder(CONSTRAINT, COMPONENT).add(TYPE, node.name() + FacetSeparator + child.qualifiedName());
				builder.add(SIZE, node.isTerminal() && Product.compareLevelWith(level) > 0 ? transformSizeRuleOfTerminalNode(node) : createRulesFrames(node.container().rulesOf(node)));
				addTags(node, builder);
				frames.add(builder.toFrame());
			}
		else createComponentConstraint(frames, node);

	}

	private void createComponentConstraint(List<Frame> frames, Node component) {
		final List<Node> candidates = collectCandidates(component);
		final Size size = component.container().sizeOf(component);
		final List<Rule> allRules = component.container().rulesOf(component).stream().distinct().collect(toList());
		if ((size.isSingle() || size.isRequired() || component.isReference()) && candidates.size() > 1) {
			final FrameBuilder oneOfBuilder = createOneOf(candidates, allRules);
			if (!component.isAbstract()) oneOfBuilder.add(CONSTRAINT, createComponentConstraint(component, allRules));
			if (!component.isSub()) frames.add(oneOfBuilder.toFrame());
		} else frames.addAll(candidates.stream().filter(c -> componentCompliant(c.container(), c)).
				map(c -> createComponentConstraint(c, allRules)).collect(toList()));
	}

	private Frame createComponentConstraint(Node component, List<Rule> rules) {
		FrameBuilder builder = new FrameBuilder(CONSTRAINT, COMPONENT).add(TYPE, name(component));
		if (isTerminal(component)) builder.add(SIZE, transformSizeRuleOfTerminalNode(component));
		else builder.add(SIZE, createRulesFrames(rules));
		addTags(component, builder);
		return builder.toFrame();
	}

	private boolean isTerminal(Node component) {
		return component.isTerminal() && !isInTerminal(component) && Product.compareLevelWith(level) > 0;
	}

	private String name(Node node) {
		return node instanceof NodeReference ? ((NodeReference) node).getDestiny().qualifiedName() : node.qualifiedName();
	}

	private FrameBuilder createOneOf(Collection<Node> candidates, List<Rule> rules) {
		FrameBuilder builder = new FrameBuilder(ONE_OF, CONSTRAINT);
		builder.add(RULE, createRulesFrames(rules));
		for (Node candidate : candidates)
			builder.add(CONSTRAINT, createComponentConstraint(candidate, rules));
		return builder;
	}

	private Frame[] createRulesFrames(List<Rule> rules) {
		return rules.stream().map(rule -> rule instanceof NodeCustomRule ? buildCustomRuleFrame((NodeCustomRule) rule) : new FrameBuilder().append(rule).toFrame()).toArray(Frame[]::new);
	}

	private Frame buildCustomRuleFrame(NodeCustomRule rule) {
		return new FrameBuilder("rule", "customRule").add("qn", rule.loadedClass().getName()).toFrame();
	}

	private boolean isInTerminal(Node component) {
		return component.container().isTerminal();
	}

	private Frame transformSizeRuleOfTerminalNode(Node component) {
		final Size rule = component.container().sizeOf(component);
		final Size size = new Size(0, rule.max(), rule);
		return new FrameBuilder().append(size).toFrame();
	}

	private void addTags(Node node, FrameBuilder frame) {
		Set<String> tags = node.annotations().stream().map(Tag::name).collect(Collectors.toCollection(LinkedHashSet::new));
		node.flags().stream().filter(f -> !f.equals(Decorable) && !Tag.Required.equals(f)).forEach(tag -> tags.add(convertTag(tag)));
		frame.add(TAGS, tags.toArray(new Object[0]));
	}

	private List<Node> collectCandidates(Node node) {
		Set<Node> nodes = new LinkedHashSet<>();
		if (node.isAnonymous() || node.is(Tag.Instance)) return new ArrayList<>(nodes);
		if (!node.isAbstract()) nodes.add(node);
		getNonAbstractChildren(node, nodes);
		return new ArrayList<>(nodes);
	}

	private void getNonAbstractChildren(Node node, Set<Node> nodes) {
		for (Node child : node.children())
			if (child.isAbstract())
				getNonAbstractChildren(child, nodes);
			else if (child.container().equals(node.container()) || node.isReference()) nodes.add(child);
	}

	private String convertTag(Tag tag) {
		if (tag.equals(Tag.Terminal)) return Tag.Instance.name();
		return tag.name();
	}

	private List<Tag> annotations(Constraint constraint) {
		return ((Constraint.Component) constraint).annotations();
	}

	private boolean is(List<Tag> annotations, Tag tag) {
		return annotations.contains(tag);
	}
}