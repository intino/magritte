package io.intino.magritte.builder.compiler.codegeneration.magritte;

import io.intino.itrules.Engine;
import io.intino.itrules.Frame;
import io.intino.itrules.FrameBuilder;
import io.intino.itrules.FrameBuilderContext;
import io.intino.itrules.adapters.ExcludeAdapter;
import io.intino.itrules.template.Template;
import io.intino.magritte.builder.compiler.codegeneration.magritte.layer.TypesProvider;
import io.intino.magritte.io.StashSerializer;
import io.intino.magritte.io.model.Stash;
import io.intino.tara.Language;
import io.intino.tara.builder.utils.Format;
import io.intino.tara.language.semantics.Constraint;
import io.intino.tara.model.*;
import io.intino.tara.model.rules.property.*;
import io.intino.tara.processors.model.HasMogram;
import io.intino.tara.processors.model.ReferenceProperty;
import io.intino.tara.processors.parser.NativeExtractor;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.intino.magritte.builder.compiler.codegeneration.magritte.NameFormatter.cleanQn;
import static io.intino.magritte.builder.compiler.codegeneration.magritte.NameFormatter.getQn;
import static io.intino.tara.builder.utils.Format.javaValidName;
import static io.intino.tara.model.Annotation.Decorable;
import static io.intino.tara.model.Annotation.Final;
import static io.intino.tara.model.Primitive.OBJECT;
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

	public static boolean isInDecorable(Mogram mogram) {
		ElementContainer container = mogram.container();
		if (container instanceof MogramRoot) return false;
		while (!(container.container() instanceof MogramRoot))
			container = container.container();
		return ((Mogram) container).is(Decorable);
	}

	public Set<String> getImports() {
		return imports;
	}

	protected void addParent(Mogram mogram, FrameBuilderContext context) {
		if (mogram.parent() == null) {
			if (!mogram.children().isEmpty() || context.contains(CREATE) || context.contains(NODE))
				context.add(PARENT_SUPER, false);
			return;
		}
		final Mogram parent = mogram.parent().get();
		String parentQN = cleanQn(getQn(parent, workingPackage));
		context.add(PARENT, parentQN);
		if (context.contains(CREATE) || context.contains(NODE))
			context.add(PARENT_SUPER, true).add("parentName", parentQN);
		ElementContainer parentTarget = parent.container();
		if ((context.contains(NODE)) && hasLists(mogram.parent().get())
				|| (parent.facetPrescription() != null && !(parentTarget instanceof MogramRoot) && !parentTarget.components().isEmpty() && hasLists((Mogram) parentTarget)))
			context.add("parentClearName", parentQN);
	}

	protected Frame stashFrame(Stash stash) {
		FrameBuilder builder = new FrameBuilder("stash");
		String name = stash.path.replace(".stash", "");

		String code = Base64.getEncoder().encodeToString(StashSerializer.serialize(stash));
		int i;
		for (i = 0; i < code.length() / CHUNK_SIZE; i++)
			builder.add("part", new FrameBuilder("part").add("name", name).add("index", i).add("code", code.substring(CHUNK_SIZE * i, CHUNK_SIZE * (i + 1))));
		int rest = code.length() % CHUNK_SIZE;
		if (rest > 0)
			builder.add("part", new FrameBuilder("part").add("name", name).add("index", i).add("code", code.substring(code.length() - rest)));
		return builder.toFrame();
	}

	protected String getType(Property prop) {
		if (prop.isReference()) {
			return cleanQn(getQn(((ReferenceProperty) prop).target().get(), workingPackage.toLowerCase()));
		} else if (Primitive.WORD.equals(prop.type())) {
			PropertyCustomRule customRule = prop.rule(PropertyCustomRule.class);
			return customRule != null ?
					workingPackage.toLowerCase() + ".rules." + Format.firstUpperCase().format((customRule).externalClass()) :
					Format.firstUpperCase().format(prop.name()).toString();
		} else if (OBJECT.equals(prop.type())) return (prop.rule(NativeObjectRule.class)).type();
		else return prop.type().javaName();
	}


	private <T> T ruleOfTypeConcrete(List<Rule> rules, Class<T> aClass) {
		return (T) rules.stream().filter(aClass::isInstance).findFirst().orElse(null);
	}

	protected FrameBuilder ruleToFrame(Rule rule) {
		if (rule == null) return null;
		final FrameBuilder builder = new FrameBuilder();
		builder.put(Rule.class, new ExcludeAdapter<>("loadedClass"));
		builder.append(rule);
		if (rule instanceof PropertyCustomRule) {
			FrameBuilder frameBuilder = new FrameBuilder("customRule");
			frameBuilder.add(QN, cleanQn(((PropertyCustomRule) rule).qualifiedName()));
			frameBuilder.add("aClass", cleanQn(((PropertyCustomRule) rule).externalClass()));
			if (((PropertyCustomRule) rule).isMetric()) {
				frameBuilder.add(METRIC);
				frameBuilder.add(DEFAULT, ((PropertyCustomRule) rule).getDefaultUnit());
			}
			return frameBuilder;
		}
		return builder;
	}

	protected Predicate<Annotation> isLayerInterface() {
		return tag -> tag.equals(Annotation.Component) || tag.equals(Annotation.Feature) || tag.equals(Annotation.Private);
	}

	protected void addTerminalProperties(Mogram mogram, final FrameBuilderContext context) {
		final List<Constraint> terminalCoreVariables = collectTerminalCoreVariables(mogram);
		if (mogram.parent() == null && !terminalCoreVariables.isEmpty()) {
			if (!context.contains(META_TYPE))
				context.add(META_TYPE, languageWorkingPackage + DOT + metaType(mogram));
		}
		terminalCoreVariables.forEach(c -> addTerminalVariable(mogram, languageWorkingPackage + "." + mogram.types().get(0), context, (Constraint.Property) c, mogram.parent() != null, isRequired(mogram, (Constraint.Property) c), META_TYPE, languageWorkingPackage));
		addAspectVariables(mogram, context);
		if (!context.contains(CONTAINER))
			context.add(CONTAINER, isInDecorable(mogram) ? mogram.qualifiedName() : mogram.name());
	}

	private boolean isRequired(Mogram mogram, Constraint.Property c) {
		while (mogram != null) {
			for (PropertyDescription parameter : mogram.parameters())
				if (parameter.name().equals(c.name())) return false;
			mogram = (Mogram) mogram.parent();
		}
		return true;
	}

	private void addAspectVariables(Mogram mogram, FrameBuilderContext context) {
		for (Facet aspect : mogram.appliedFacets())
			context.add(META_ASPECT, new FrameBuilder(META_ASPECT).add(NAME, aspect.type()).add(TYPE, languageWorkingPackage + "." + aspect.fullType()).toFrame());
		collectTerminalFacetVariables(mogram).forEach((key, value) -> value.forEach(c ->
				addTerminalVariable(mogram, languageWorkingPackage + "." + mogram.types().get(0), context, (Constraint.Property) c, mogram.parent() != null, isRequired(mogram, (Constraint.Property) c), key, languageWorkingPackage)));
	}

	private List<Constraint> collectTerminalCoreVariables(Mogram mogram) {
		final Collection<Constraint> allows = language.constraints(mogram.types().get(0));
		return emptyList();
//		return allows.stream().filter(allow -> allow instanceof Constraint.Property &&
//				((Constraint.Property) allow).annotations().contains(Terminal) &&
//				!isRedefined((Constraint.Property) allow, node.variables())).collect(Collectors.toList());
	}

	private Map<String, List<Constraint>> collectTerminalFacetVariables(Mogram mogram) {
		List<Constraint> constraints = language.constraints(mogram.types().get(0));
		if (constraints == null) return emptyMap();
		Map<String, List<Constraint>> map = new HashMap<>();
		final List<Constraint> aspects = constraints.stream().filter(c -> c instanceof Constraint.Facet && hasFacet(mogram, ((Constraint.Facet) c).type())).toList();
		for (Constraint aspect : aspects) map.put(((Constraint.Facet) aspect).type(), new ArrayList<>());
		aspects.forEach(f -> map.put(((Constraint.Facet) f).type(), ((Constraint.Facet) f).constraints().stream().filter(byTerminalParameters(mogram)).collect(Collectors.toList())));
		return map;
	}

	protected String metaType(Mogram mogram) {
		final String type = mogram.types().get(0);
		return type.contains(":") ? type.split(":")[1] + "." + type.replace(":", "") : mogram.types().get(0);
	}

	private void addTerminalVariable(Mogram mogram, String type, FrameBuilderContext context, Constraint.Property parameter, boolean inherited, boolean isRequired, String containerName, String languageWorkingPackage) {
		FrameBuilder varBuilder = createFrame(parameter, type, inherited, isRequired, containerName, languageWorkingPackage);
		if (!varBuilder.contains(CONTAINER))
			varBuilder.add(CONTAINER, isInDecorable(mogram) ? mogram.qualifiedName() : mogram.name());
		context.add(PROPERTY, varBuilder.toFrame());
	}

	private boolean hasFacet(Mogram mogram, String type) {
		return mogram.appliedFacets().stream().anyMatch(aspect -> aspect.type().equals(type));
	}

	private Predicate<Constraint> byTerminalParameters(Mogram mogram) {
		return o -> o instanceof Constraint.Property &&
//				((Constraint.Property) o).annotations().contains(Terminal) &&
				!isRedefined((Constraint.Property) o, mogram.properties());
	}

	private boolean isRedefined(Constraint.Property c, List<? extends Property> property) {
		return property.stream().anyMatch(prop -> prop.name().equals(c.name()));
	}

	private FrameBuilder createFrame(final Constraint.Property parameter, String type, boolean inherited, boolean isRequired, String containerName, String workingPackage) {
		final FrameBuilder builder = new FrameBuilder(TypesProvider.getTypes(parameter, isRequired)).add(META_TYPE).add(TARGET);
		if (inherited) builder.add(INHERITED);
		builder.add(NAME, parameter.name());
		builder.add(CONTAINER_NAME, containerName);
		builder.add(QN, type);
		builder.add(LANGUAGE, language.languageName().toLowerCase());
		builder.add(WORKING_PACKAGE, workingPackage);
		builder.add(TYPE, type(parameter));
		if (parameter.type().equals(Primitive.WORD)) {
			final WordRule rule = ruleOfTypeConcrete(parameter.rules(), WordRule.class);
			final List<String> words = rule.words();
			if (rule.isCustom()) {
				builder.add(OUTDEFINED);
				builder.add(EXTERNAL_CLASS, cleanQn(rule.externalClass()));
			}
			builder.add(WORD_VALUES, words.toArray(new Object[0]));
		}
		if (parameter.type().equals(Primitive.FUNCTION)) {
			final FunctionRule rule = ruleOfTypeConcrete(parameter.rules(), FunctionRule.class);
			final String signature = rule.signature();
			NativeExtractor extractor = new NativeExtractor(signature);
			builder.add("methodName", extractor.methodName());
			builder.add("parameters", extractor.parameters());
			builder.add("returnType", extractor.returnType());
			builder.add(RULE, rule.interfaceClass());
//			builder.add(OUT_LANGUAGE, parameter.scope());//TODO
			imports.addAll(new ArrayList<>(rule.imports()));
		}
		if (!builder.contains(OUT_LANGUAGE))
			builder.add(OUT_LANGUAGE, outDsl.toLowerCase());
		return builder;
	}

	private String type(Constraint.Property parameter) {
		return parameter.type() == Primitive.REFERENCE ?
				languageWorkingPackage + DOT + ruleOfTypeConcrete(parameter.rules(), ReferenceRule.class).allowedReferences().get(0) :
				parameter.type().getName();
	}

	public static Engine customize(Template template) {
		Engine engine = new Engine(template);
		engine.add("string", Format.string());
		engine.add("reference", Format.reference());
		engine.add("toCamelCase", Format.toCamelCase());
		engine.add("snakeCaseToCamelCase", Format.snakeCaseToCamelCase());
		engine.add("withDollar", Format.withDollar());
		engine.add("noPackage", Format.noPackage());
		engine.add("key", Format.key());
//		engine.add("returnType", (trigger, type) -> trigger.frame().frames("returnType").next().value().equals(type));
		engine.add("WithoutType", Format.nativeParameterWithoutType());
		engine.add("javaValidName", javaValidName());
		engine.add("javaValidWord", Format.javaValidWord());
		engine.add("withoutGeneric", Format.withoutGeneric());
		return engine;
	}

	protected Stream<Property> effectiveProperties(Mogram mogram) {
		Map<String, Property> properties = new LinkedHashMap<>();
		Mogram current = mogram.parent() != null ? mogram.parent().get() : null;
		while (current != null) {
			current.properties().forEach(p -> properties.put(p.name(), p));
			current = current.parent() != null ? current.parent().get() : null;
		}
		ArrayList<Property> list = new ArrayList<>(properties.values());
		Collections.reverse(list);
		mogram.properties().stream().filter(property -> list.stream().noneMatch(p -> p.name().equals(property.name()))).forEach(list::add);
		return list.stream();
	}


	protected Stream<Mogram> effectiveComponents(Mogram mogram) {
		Map<String, Mogram> mograms = new LinkedHashMap<>();
		Mogram current = mogram.parent() != null ? mogram.parent().get() : null;
		while (current != null) {
			current.components().forEach(m -> mograms.put(m.name(), m));
			current.components().stream().flatMap(c -> c.children().stream()).forEach(c -> mograms.put(c.name(), c));
			current = current.parent() != null ? current.parent().get() : null;
		}
		List<Mogram> list = new ArrayList<>(mograms.values());
		Collections.reverse(list);
		mogram.components().stream()
				.filter(c -> list.stream().noneMatch(p -> p.name().equals(c.name())))
				.forEach(list::add);
		return list.stream();
	}

	protected Stream<HasMogram> effectiveReferenceComponents(Mogram mogram) {
		Map<String, HasMogram> refs = new LinkedHashMap<>();
		Mogram current = mogram.parent() != null ? mogram.parent().get() : null;
		while (current != null) {
			current.referenceComponents().forEach(r -> refs.put(r.target().get().name(), r));
			current = current.parent() != null ? current.parent().get() : null;
		}
		List<HasMogram> list = new ArrayList<>(refs.values());
		Collections.reverse(list);
		mogram.referenceComponents().stream()
				.filter(c -> list.stream().noneMatch(hs -> hs.target().get().name().equals(c.target().get().name())))
				.forEach(list::add);
		return list.stream();
	}

	private boolean hasLists(Mogram mogram) {
		return effectiveComponents(mogram).anyMatch(c -> !c.container().sizeOf(c).isSingle() && !c.is(Final));
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
}
