package io.intino.tara.compiler.codegeneration.magritte;

import io.intino.itrules.FrameBuilder;
import io.intino.itrules.FrameBuilderContext;
import io.intino.itrules.adapters.ExcludeAdapter;
import io.intino.tara.Language;
import io.intino.tara.compiler.codegeneration.Format;
import io.intino.tara.compiler.codegeneration.magritte.layer.TypesProvider;
import io.intino.tara.compiler.codegeneration.magritte.natives.NativeExtractor;
import io.intino.tara.compiler.model.NodeReference;
import io.intino.tara.compiler.model.VariableReference;
import io.intino.tara.lang.model.*;
import io.intino.tara.lang.model.rules.variable.NativeObjectRule;
import io.intino.tara.lang.model.rules.variable.NativeRule;
import io.intino.tara.lang.model.rules.variable.VariableCustomRule;
import io.intino.tara.lang.model.rules.variable.WordRule;
import io.intino.tara.lang.semantics.Constraint;
import io.intino.tara.lang.semantics.constraints.parameter.ReferenceParameter;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static io.intino.tara.compiler.codegeneration.magritte.NameFormatter.cleanQn;
import static io.intino.tara.compiler.codegeneration.magritte.NameFormatter.getQn;
import static io.intino.tara.lang.model.Primitive.OBJECT;
import static io.intino.tara.lang.model.Tag.Terminal;
import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;

public abstract class Generator implements TemplateTags {

	protected final Language language;
	protected final String outDsl;
	protected final String workingPackage;
	protected final String languageWorkingPackage;
	protected Set<String> imports = new HashSet<>();

	public Generator(Language language, String outDsl, String workingPackage, String languageWorkingPackage) {
		this.language = language;
		this.outDsl = outDsl;
		this.workingPackage = workingPackage;
		this.languageWorkingPackage = languageWorkingPackage;
	}

	protected static FacetTarget isInFacet(Node node) {
		Node container = node.container();
		while (container != null && container.facetTarget() == null)
			container = container.container();
		return container != null ? container.facetTarget() : null;
	}

	private static Map<String, List<Constraint>> collectFacetConstrains(List<Constraint> constraints, Node node) {
		if (constraints == null) return emptyMap();
		Map<String, List<Constraint>> map = new HashMap<>();
		final List<Constraint> facets = constraints.stream().filter(c -> c instanceof Constraint.Facet && hasFacet(node, ((Constraint.Facet) c).type())).collect(Collectors.toList());
		for (Constraint facet : facets) map.put(((Constraint.Facet) facet).type(), new ArrayList<>());
		facets.forEach(f -> map.put(((Constraint.Facet) f).type(), ((Constraint.Facet) f).constraints().stream().filter(byTerminalParameters(node)).collect(Collectors.toList())));
		return map;
	}

	private static boolean hasFacet(Node node, String type) {
		for (Facet facet : node.facets()) if (facet.type().equals(type)) return true;
		return false;
	}

	private static Predicate<Constraint> byTerminalParameters(Node node) {
		return o -> o instanceof Constraint.Parameter &&
				((Constraint.Parameter) o).flags().contains(Terminal) &&
				!isRedefined((Constraint.Parameter) o, node.variables());
	}

	private static boolean isRedefined(Constraint.Parameter allow, List<? extends Variable> variables) {
		for (Variable variable : variables) if (variable.name().equals(allow.name())) return true;
		return false;
	}

	protected void addComponents(Node node, FrameBuilderContext context) {
		if (node instanceof NodeReference) return;
		node.components().stream().
				filter(component -> !component.isAnonymous() && (!component.isReference() || (((NodeReference) component).isHas()))).
				forEach(component -> context.add(NODE, FrameBuilder.from(context).append(component).add(OWNER).toFrame()));
	}

	protected String getType(Variable variable) {
		if (variable instanceof VariableReference)
			return cleanQn(getQn(((VariableReference) variable).getDestiny(), (((VariableReference) variable).isTypeReference() ? languageWorkingPackage : workingPackage).toLowerCase()));
		else if (Primitive.WORD.equals(variable.type()))
			return variable.rule() != null && variable.rule() instanceof VariableCustomRule ?
					workingPackage.toLowerCase() + ".rules." + Format.firstUpperCase().format(((VariableCustomRule) variable.rule()).getExternalWordClass()) :
					Format.firstUpperCase().format(variable.name()).toString();
		else if (OBJECT.equals(variable.type())) return (((NativeObjectRule) variable.rule()).type());
		else return variable.type().javaName();
	}

	protected FrameBuilder ruleToFrame(Rule rule) {
		if (rule == null) return null;
		final FrameBuilder frameBuilder = new FrameBuilder();
		frameBuilder.put(Rule.class, new ExcludeAdapter<>("loadedClass"));
		final FrameBuilder builder = frameBuilder.append(rule);
		if (rule instanceof VariableCustomRule) {
			builder.add(QN, ((VariableCustomRule) rule).qualifiedName());
			if (((VariableCustomRule) rule).isMetric()) {
				builder.add(METRIC);
				builder.add(DEFAULT, ((VariableCustomRule) rule).getDefaultUnit());
			}
		}
		return builder;
	}

	protected Predicate<Tag> isLayerInterface() {
		return tag -> tag.equals(Tag.Component) || tag.equals(Tag.Feature) || tag.equals(Tag.Terminal)
				|| tag.equals(Tag.Private) || tag.equals(Tag.Volatile);
	}

	protected void addTerminalVariables(Node node, final FrameBuilderContext context) {
		final List<Constraint> terminalCoreVariables = collectTerminalCoreVariables(node);
		if (node.parent() == null && !terminalCoreVariables.isEmpty()) {
			if (!context.contains(META_TYPE))
				context.add(META_TYPE, languageWorkingPackage + DOT + metaType(node));
		}
		terminalCoreVariables.forEach(c -> addTerminalVariable(node, languageWorkingPackage + "." + node.type(), context, (Constraint.Parameter) c, node.parent() != null, isRequired(node, (Constraint.Parameter) c), META_TYPE, languageWorkingPackage));
		addFacetVariables(node, context);
		if (!context.contains(CONTAINER)) context.add(CONTAINER, node.name() + facetName(node.facetTarget()));
	}

	private boolean isRequired(Node node, Constraint.Parameter allow) {
		Node n = node.isReference() ? node.destinyOfReference() : node;
		while (n != null) {
			for (Parameter parameter : n.parameters())
				if (parameter.name().equals(allow.name())) return false;
			n = n.parent();
		}
		return true;
	}

	private void addFacetVariables(Node node, FrameBuilderContext context) {
		for (Facet facet : node.facets())
			context.add(META_FACET, new FrameBuilder(META_FACET).add(NAME, facet.type()).add(TYPE, metaType(facet)).toFrame());
		collectTerminalFacetVariables(node).forEach((key, value) -> value.forEach(c ->
				addTerminalVariable(node, languageWorkingPackage + "." + node.type(), context, (Constraint.Parameter) c, node.parent() != null, isRequired(node, (Constraint.Parameter) c), key, languageWorkingPackage)));
	}

	private List<Constraint> collectTerminalCoreVariables(Node node) {
		final Collection<Constraint> allows = language.constraints(node.type());
		if (allows == null) return emptyList();
		return allows.stream().filter(allow -> allow instanceof Constraint.Parameter &&
				((Constraint.Parameter) allow).flags().contains(Terminal) &&
				!isRedefined((Constraint.Parameter) allow, node.variables())).collect(Collectors.toList());
	}

	private Map<String, List<Constraint>> collectTerminalFacetVariables(Node node) {
		return collectFacetConstrains(language.constraints(node.type()), node);
	}

	private String metaType(Facet facet) {
		for (String key : language.catalog().keySet())
			if (key.startsWith(facet.type() + ":"))
				return language.catalog().get(key).doc().layer();
		return "";
	}

	protected String metaType(Node node) {
		final String type = node.type();
		return type.contains(":") ? type.split(":")[0].toLowerCase() + "." + node.type().replace(":", "") : node.type();
	}

	private void addTerminalVariable(Node node, String type, FrameBuilderContext context, Constraint.Parameter parameter, boolean inherited, boolean isRequired, String containerName, String languageWorkingPackage) {
		FrameBuilder varBuilder = createFrame(parameter, type, inherited, isRequired, containerName, languageWorkingPackage);
		if (!varBuilder.contains(CONTAINER))
			varBuilder.add(CONTAINER, node.name() + facetName(node.facetTarget()));
		context.add(VARIABLE, varBuilder.toFrame());
	}

	protected String facetName(FacetTarget facetTarget) {
		return facetTarget != null ? facetTarget.targetNode().name().replace(".", "") : "";
	}

	private FrameBuilder createFrame(final Constraint.Parameter parameter, String type, boolean inherited, boolean isRequired, String containerName, String workingPackage) {
		final FrameBuilder builder = new FrameBuilder(TypesProvider.getTypes(parameter, isRequired)).add(META_TYPE).add(TARGET);
		if (inherited) builder.add(INHERITED);
		builder.add(NAME, parameter.name());
		builder.add(CONTAINER_NAME, containerName);
		builder.add(QN, type);
		builder.add(LANGUAGE, language.languageName().toLowerCase());
		builder.add(WORKING_PACKAGE, workingPackage);
		builder.add(TYPE, type(parameter));
		if (parameter.type().equals(Primitive.WORD)) {
			final WordRule rule = (WordRule) parameter.rule();
			final List<String> words = rule.words();
			if (rule.isCustom()) {
				builder.add(OUTDEFINED);
				builder.add(EXTERNAL_CLASS, rule.externalWordClass());
			}
			builder.add(WORD_VALUES, (Object[]) words.toArray(new Object[0]));
		}
		if (parameter.type().equals(Primitive.FUNCTION)) {
			final NativeRule rule = (NativeRule) parameter.rule();
			final String signature = rule.signature();
			NativeExtractor extractor = new NativeExtractor(signature);
			builder.add("methodName", extractor.methodName());
			builder.add("parameters", extractor.parameters());
			builder.add("returnType", extractor.returnType());
			builder.add(RULE, rule.interfaceClass());
			builder.add(OUT_LANGUAGE, parameter.scope());
			imports.addAll(new ArrayList<>(rule.imports()));
		}
		if (!builder.contains(OUT_LANGUAGE))
			builder.add(OUT_LANGUAGE, outDsl.toLowerCase());
		return builder;
	}

	private String type(Constraint.Parameter parameter) {
		if (parameter instanceof ReferenceParameter)
			return languageWorkingPackage + DOT + ((ReferenceParameter) parameter).referenceType();
		else return parameter.type().getName();
	}

	public Set<String> getImports() {
		return imports;
	}

	protected void addParent(Node node, FrameBuilderContext context) {
		final Node parent = node.parent();
		if (parent == null) {
			if (!node.children().isEmpty() || context.contains(CREATE) || context.contains(NODE)) context.add(PARENT_SUPER, false);
			return;
		}
		String parentQN = cleanQn(getQn(parent, workingPackage));
		context.add(PARENT, parentQN);
		if (context.contains(CREATE) || context.contains(NODE)) {
			context.add(PARENT_SUPER, true).add("parentName", parentQN);
		}
		if ((context.contains(NODE)) && !node.parent().components().isEmpty())
			context.add("parentClearName", parentQN);
	}
}
