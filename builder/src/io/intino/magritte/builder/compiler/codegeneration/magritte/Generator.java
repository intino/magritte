package io.intino.magritte.builder.compiler.codegeneration.magritte;

import io.intino.itrules.Frame;
import io.intino.itrules.FrameBuilder;
import io.intino.itrules.FrameBuilderContext;
import io.intino.itrules.adapters.ExcludeAdapter;
import io.intino.magritte.builder.compiler.codegeneration.magritte.layer.TypesProvider;
import io.intino.magritte.io.StashSerializer;
import io.intino.magritte.io.model.Stash;
import io.intino.tara.Language;
import io.intino.tara.builder.model.Model;
import io.intino.tara.builder.model.MogramReference;
import io.intino.tara.builder.model.VariableReference;
import io.intino.tara.builder.parser.NativeExtractor;
import io.intino.tara.builder.utils.Format;
import io.intino.tara.language.model.*;
import io.intino.tara.language.model.rules.variable.NativeObjectRule;
import io.intino.tara.language.model.rules.variable.NativeRule;
import io.intino.tara.language.model.rules.variable.VariableCustomRule;
import io.intino.tara.language.model.rules.variable.WordRule;
import io.intino.tara.language.semantics.Constraint;
import io.intino.tara.language.semantics.constraints.parameter.ReferenceParameter;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static io.intino.magritte.builder.compiler.codegeneration.magritte.NameFormatter.cleanQn;
import static io.intino.magritte.builder.compiler.codegeneration.magritte.NameFormatter.getQn;
import static io.intino.tara.language.model.Primitive.OBJECT;
import static io.intino.tara.language.model.Tag.*;
import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;

public abstract class Generator implements TemplateTags {
	public static final int CHUNK_SIZE = 4000;
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

	public static boolean isInDecorable(Mogram node) {
		Mogram container = node.container();
		if (container instanceof MogramRoot) return false;
		while (!(container.container() instanceof MogramRoot))
			container = container.container();
		return container.is(Decorable);
	}

	public Set<String> getImports() {
		return imports;
	}

	protected void addParent(Mogram mogram, FrameBuilderContext context) {
		final Mogram parent = mogram.parent();
		if (parent == null) {
			if (!mogram.children().isEmpty() || context.contains(CREATE) || context.contains(NODE))
				context.add(PARENT_SUPER, false);
			return;
		}
		String parentQN = cleanQn(getQn(parent, workingPackage));
		context.add(PARENT, parentQN);
		if (context.contains(CREATE) || context.contains(NODE))
			context.add(PARENT_SUPER, true).add("parentName", parentQN);
		Mogram parentTarget = parent.container();
		if ((context.contains(NODE)) && hasLists(mogram.parent())
				|| (parent.isFacet() && !(parentTarget instanceof Model) && parentTarget.components().stream().anyMatch(c -> !c.isFacet() && !c.isMetaFacet()) && hasLists(parentTarget)))
			context.add("parentClearName", parentQN);
	}

	protected void addComponents(Mogram mogram, FrameBuilderContext context) {
		if (mogram instanceof MogramReference) return;
		mogram.components().stream().
				filter(c -> !c.is(Instance) && !c.isFacet() && !c.isAnonymous() && (!c.isReference() || (((MogramReference) c).isHas()))).
				forEach(c -> context.add(NODE, FrameBuilder.from(context).append(c).add(OWNER).toFrame()));
	}

	protected Frame stashFrame(Stash stash) {
		FrameBuilder builder = new FrameBuilder("stash");
		String code = Base64.getEncoder().encodeToString(StashSerializer.serialize(stash));
		int i;
		for (i = 0; i < code.length() / CHUNK_SIZE; i++)
			builder.add("part", new FrameBuilder("part").add("index", i).add("code", code.substring(CHUNK_SIZE * i, CHUNK_SIZE * (i + 1))));
		int rest = code.length() % CHUNK_SIZE;
		if (rest > 0)
			builder.add("part", new FrameBuilder("part").add("index", i).add("code", code.substring(code.length() - rest)));
		return builder.toFrame();
	}

	protected String getType(Variable variable) {
		if (variable instanceof VariableReference)
			return cleanQn(getQn(((VariableReference) variable).getTarget(), (((VariableReference) variable).isTypeReference() ? languageWorkingPackage : workingPackage).toLowerCase()));
		else if (Primitive.WORD.equals(variable.type()))
			return variable.rule() != null && variable.rule() instanceof VariableCustomRule ?
					workingPackage.toLowerCase() + ".rules." + Format.firstUpperCase().format(((VariableCustomRule) variable.rule()).externalClass()) :
					Format.firstUpperCase().format(variable.name()).toString();
		else if (OBJECT.equals(variable.type())) return (((NativeObjectRule) variable.rule()).type());
		else return variable.type().javaName();
	}

	protected FrameBuilder ruleToFrame(Rule rule) {
		if (rule == null) return null;
		final FrameBuilder builder = new FrameBuilder();
		builder.put(Rule.class, new ExcludeAdapter<>("loadedClass"));
		builder.append(rule);
		if (rule instanceof VariableCustomRule) {
			FrameBuilder frameBuilder = new FrameBuilder("customRule");
			frameBuilder.add(QN, cleanQn(((VariableCustomRule) rule).qualifiedName()));
			frameBuilder.add("aClass", cleanQn(((VariableCustomRule) rule).externalClass()));
			if (((VariableCustomRule) rule).isMetric()) {
				frameBuilder.add(METRIC);
				frameBuilder.add(DEFAULT, ((VariableCustomRule) rule).getDefaultUnit());
			}
			return frameBuilder;
		}
		return builder;
	}

	protected Predicate<Tag> isLayerInterface() {
		return tag -> tag.equals(Tag.Component) || tag.equals(Tag.Feature) || tag.equals(Tag.Terminal)
				|| tag.equals(Tag.Private) || tag.equals(Tag.Volatile);
	}

	protected void addTerminalVariables(Mogram node, final FrameBuilderContext context) {
		final List<Constraint> terminalCoreVariables = collectTerminalCoreVariables(node);
		if (node.parent() == null && !terminalCoreVariables.isEmpty()) {
			if (!context.contains(META_TYPE))
				context.add(META_TYPE, languageWorkingPackage + DOT + metaType(node));
		}
		terminalCoreVariables.forEach(c -> addTerminalVariable(node, languageWorkingPackage + "." + node.type(), context, (Constraint.Parameter) c, node.parent() != null, isRequired(node, (Constraint.Parameter) c), META_TYPE, languageWorkingPackage));
		addAspectVariables(node, context);
		if (!context.contains(CONTAINER))
			context.add(CONTAINER, isInDecorable(node) ? node.qualifiedName() : node.name());
	}

	private boolean isRequired(Mogram node, Constraint.Parameter allow) {
		Mogram n = node.isReference() ? node.targetOfReference() : node;
		while (n != null) {
			for (Parameter parameter : n.parameters())
				if (parameter.name().equals(allow.name())) return false;
			n = n.parent();
		}
		return true;
	}

	private void addAspectVariables(Mogram node, FrameBuilderContext context) {
		for (Facet aspect : node.appliedFacets())
			context.add(META_ASPECT, new FrameBuilder(META_ASPECT).add(NAME, aspect.type()).add(TYPE, languageWorkingPackage + "." + aspect.fullType()).toFrame());
		collectTerminalFacetVariables(node).forEach((key, value) -> value.forEach(c ->
				addTerminalVariable(node, languageWorkingPackage + "." + node.type(), context, (Constraint.Parameter) c, node.parent() != null, isRequired(node, (Constraint.Parameter) c), key, languageWorkingPackage)));
	}

	private List<Constraint> collectTerminalCoreVariables(Mogram node) {
		final Collection<Constraint> allows = language.constraints(node.type());
		if (allows == null) return emptyList();
		return allows.stream().filter(allow -> allow instanceof Constraint.Parameter &&
				((Constraint.Parameter) allow).flags().contains(Terminal) &&
				!isRedefined((Constraint.Parameter) allow, node.variables())).collect(Collectors.toList());
	}

	private Map<String, List<Constraint>> collectTerminalFacetVariables(Mogram node) {
		List<Constraint> constraints = language.constraints(node.type());
		if (constraints == null) return emptyMap();
		Map<String, List<Constraint>> map = new HashMap<>();
		final List<Constraint> aspects = constraints.stream().filter(c -> c instanceof Constraint.Facet && hasFacet(node, ((Constraint.Facet) c).type())).toList();
		for (Constraint aspect : aspects) map.put(((Constraint.Facet) aspect).type(), new ArrayList<>());
		aspects.forEach(f -> map.put(((Constraint.Facet) f).type(), ((Constraint.Facet) f).constraints().stream().filter(byTerminalParameters(node)).collect(Collectors.toList())));
		return map;
	}

	protected String metaType(Mogram node) {
		final String type = node.type();
		return type.contains(":") ? type.split(":")[1] + "." + node.type().replace(":", "") : node.type();
	}

	private void addTerminalVariable(Mogram node, String type, FrameBuilderContext context, Constraint.Parameter parameter, boolean inherited, boolean isRequired, String containerName, String languageWorkingPackage) {
		FrameBuilder varBuilder = createFrame(parameter, type, inherited, isRequired, containerName, languageWorkingPackage);
		if (!varBuilder.contains(CONTAINER))
			varBuilder.add(CONTAINER, isInDecorable(node) ? node.qualifiedName() : node.name());
		context.add(VARIABLE, varBuilder.toFrame());
	}

	private boolean hasFacet(Mogram node, String type) {
		return node.appliedFacets().stream().anyMatch(aspect -> aspect.type().equals(type));
	}

	private Predicate<Constraint> byTerminalParameters(Mogram node) {
		return o -> o instanceof Constraint.Parameter &&
				((Constraint.Parameter) o).flags().contains(Terminal) &&
				!isRedefined((Constraint.Parameter) o, node.variables());
	}

	private boolean isRedefined(Constraint.Parameter allow, List<? extends Variable> variables) {
		return variables.stream().anyMatch(variable -> variable.name().equals(allow.name()));
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
				builder.add(EXTERNAL_CLASS, cleanQn(rule.externalClass()));
			}
			builder.add(WORD_VALUES, words.toArray(new Object[0]));
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
		return parameter instanceof ReferenceParameter ?
				languageWorkingPackage + DOT + ((ReferenceParameter) parameter).referenceType() :
				parameter.type().getName();
	}


	//	private void addParent(FacetTarget target) {
//		Mogram parent = target.owner().parent() != null ? target.owner().parent() : target.parent();
//		if (parent != null) context.add(PARENT, cleanQn(NameFormatter.getQn(parent, workingPackage)));
//		if ((context.contains(CREATE) || context.contains(NODE)) || !target.owner().children().isEmpty()) {
//			context.add(PARENT_SUPER, parent != null);
//			if (parent != null) context.add("parentName", cleanQn(NameFormatter.getQn(parent, workingPackage)));
//		}
//		if ((context.contains(NODE)) && parent != null &&
//				(!multipleComponents(parent).isEmpty() ||
//						(parent.facetTarget() != null && !parent.facetTarget().targetNode().components().isEmpty() && hasLists(parent.facetTarget().targetNode()))))
//			context.add("parentClearName", cleanQn(NameFormatter.getQn(parent, workingPackage)));
//	}
//private List<Mogram> multipleComponents(Mogram parent) {
//	return parent.components().stream().filter(c -> c.container().rulesOf(c).stream().noneMatch(rule -> rule instanceof Size && ((Size) rule).isSingle())).collect(Collectors.toList());
//}
//
	private boolean hasLists(Mogram node) {
		return node.components().stream().filter(c -> !c.isFacet() && !c.isMetaFacet()).anyMatch(c -> !node.sizeOf(c).isSingle() && !c.is(Final));
	}

}
