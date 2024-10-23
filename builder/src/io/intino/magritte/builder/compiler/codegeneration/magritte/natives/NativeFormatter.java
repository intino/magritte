package io.intino.magritte.builder.compiler.codegeneration.magritte.natives;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.intino.itrules.Frame;
import io.intino.itrules.FrameBuilder;
import io.intino.itrules.FrameBuilderContext;
import io.intino.magritte.builder.compiler.codegeneration.magritte.NameFormatter;
import io.intino.magritte.builder.compiler.codegeneration.magritte.TemplateTags;
import io.intino.tara.Language;
import io.intino.tara.builder.utils.Format;
import io.intino.tara.language.semantics.Constraint;
import io.intino.tara.model.*;
import io.intino.tara.model.rules.Size;
import io.intino.tara.model.rules.property.FunctionRule;
import io.intino.tara.model.rules.property.NativeObjectRule;
import io.intino.tara.processors.model.ReferenceProperty;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

import static io.intino.magritte.builder.compiler.codegeneration.magritte.NameFormatter.cleanQn;
import static io.intino.magritte.builder.compiler.codegeneration.magritte.NameFormatter.layerQn;
import static io.intino.tara.model.Annotation.Feature;
import static io.intino.tara.model.Level.M1;
import static io.intino.tara.model.Primitive.OBJECT;
import static io.intino.tara.processors.Resolver.shortType;

@SuppressWarnings("ALL")
public class NativeFormatter implements TemplateTags {
	private final String outDsl;
	private final Language language;
	private final String aPackage;
	private final String workingPackage;
	private final boolean m1;
	private final Map<String, Set<String>> imports;
	private final Map<String, String> workingPackages;
	private String languageWorkingPackage;

	public NativeFormatter(Language language, String outDsl, String subPackage, String workingPackage, String languageWorkingPackage, boolean m1, File importsFile) {
		this.outDsl = outDsl;
		this.language = language;
		this.aPackage = subPackage;
		this.workingPackage = workingPackage;
		this.languageWorkingPackage = languageWorkingPackage;
		this.m1 = m1;
		this.workingPackages = Map.of(language.languageName(), languageWorkingPackage == null ? "" : languageWorkingPackage, outDsl, workingPackage);
		this.imports = load(importsFile);
	}

	private static String getQn(Mogram owner, String language, boolean m0) {
		return !m0 ? NameFormatter.getQn(owner, language) : language.toLowerCase() + DOT + owner.types().get(0);
	}

	private static String getQn(Facet facet, String language, boolean m0) {
		return asFacet(facet, language); //TODO
	}

	private static String asFacet(Facet facet, String language) {
		return null;
	}

	private static String getQn(Mogram owner, Mogram node, String workingPackage, boolean m0) {
		return !m0 ? NameFormatter.getQn(owner, workingPackage) : workingPackage.toLowerCase() + DOT + owner.types().get(0);
	}

	public static String getSignature(PropertyDescription parameter) {
		return parameter.definition().rule(FunctionRule.class).signature();
	}

	public static String getInterface(PropertyDescription parameter) {
		final FunctionRule rule = parameter.definition().rule(FunctionRule.class);
		if (rule.interfaceClass() == null)
			return "";//throw new SemanticException(new SemanticError("reject.native.signature.notfound", new LanguageParameter(parameter)));
		return rule.interfaceClass();
	}

	public static String getInterface(Property property) {
		final FunctionRule rule = property.rule(FunctionRule.class);
		if (rule == null || rule.interfaceClass() == null)
			return "";//throw new SemanticException(new SemanticError("reject.native.signature.notfound", new LanguageParameter(parameter)));
		return rule.interfaceClass();
	}

	public static String formatBody(String body, String signature) {
		final String returnText = RETURN + " ";
		String formattedBody = body.endsWith(";") || body.endsWith("}") ? body : body + ";";
		if (!signature.contains(" void ") && !formattedBody.contains("\n") && !formattedBody.startsWith(returnText))
			return returnText + formattedBody;
		return formattedBody;
	}

	public static String getReturn(String body) {
		final String returnText = RETURN + " ";
		body = body.endsWith(";") || body.endsWith("}") ? body : body + ";";
		if (!body.contains("\n") && !body.startsWith(returnText)) return returnText;
		return "";
	}

	public static String getSignature(Property property) {
		return property.rule(FunctionRule.class).signature();
	}

	public static String buildContainerPath(String languageWorkingPackage, Mogram node, String workingPackage) {
		if (node instanceof Mogram) {
			final Mogram scope = node.level() == M1 ? firstNoFeature(node) : firstNoFeatureAndNamed(node);
			if (scope == null) return "";
			if (scope.level() == M1) return getTypeAsScope(scope, languageWorkingPackage);
			return getQn(scope, (Mogram) node, workingPackage, false);
		} else if (node instanceof Facet) {
			final Mogram scope = firstNoFeatureAndNamed(node);
			if (scope == null) return "";
			return scope.level() == M1 ? getTypeAsScope(scope, languageWorkingPackage) : getQn(scope, workingPackage, false);
		} else return "";
	}

	public static String buildExpressionContainerPath(String typeWorkingPackage, Mogram owner, String outDSL, String workingPackage) {
		final String trueWorkingPackage = extractWorkingPackage(typeWorkingPackage, workingPackage);
		if (owner instanceof Mogram) {
			final Mogram scope = ((Mogram) owner).level() == M1 ? firstNoFeature(owner) : firstNoFeatureAndNamed(owner);
			if (scope == null) return "";
			if (scope.level() == M1) return getTypeAsScope(scope, trueWorkingPackage);
			else return getQn(scope, (Mogram) owner, workingPackage, false);
		} else if (owner instanceof Facet) {
			return ((Mogram) owner.container()).level() == M1 ? getTypeAsScope(owner, trueWorkingPackage) : getQn((Facet) owner, workingPackage, false);
		} else return "";
	}

	private static String extractWorkingPackage(String scope, String language) {
		return scope != null && !scope.isEmpty() ? scope : language;
	}

	private static String getTypeAsScope(Mogram scope, String languageWorkingPackage) {
		return languageWorkingPackage + DOT +
				(scope instanceof Mogram ? cleanQn(scope.types().get(0)) : cleanQn(facetType((Facet) scope)));
	}

	private static List<Facet> containerFacets(Mogram scope) {
		ElementContainer container = scope.container();
		while (container != null && ((Mogram) container).appliedFacets().isEmpty())
			container = container.container();
		return container != null ? ((Mogram) container).appliedFacets() : Collections.emptyList();
	}

	private static String facetType(Facet facet) {
		return facet.type().toLowerCase() + DOT + facet.type() + shortType(facet.target().get().types().get(0));
	}

	private static Mogram firstNoFeature(ElementContainer owner) {
		ElementContainer container = owner;
		while (container != null) {
			if (container instanceof Mogram && !(container instanceof MogramRoot) && !((Mogram) container).is(Feature))
				return (Mogram) container;
			container = container.container();
		}
		return owner instanceof Mogram ? (Mogram) owner : (Mogram) owner.container();
	}

	private static Mogram firstNoFeatureAndNamed(ElementContainer owner) {
		ElementContainer container = owner;
		while (container != null) {
			if (container instanceof Mogram && !(container instanceof MogramRoot) && !((Mogram) container).isAnonymous() &&
					!((Mogram) container).is(Feature)) return (Mogram) container;
			container = container.container();
		}
		return owner instanceof Mogram ? (Mogram) owner : (Mogram) owner.container();
	}

	private static ElementContainer searchFeatureReference(Mogram owner) {
		final MogramRoot model = model(owner);
		if (model == null) return owner;
		final ElementContainer nodeContainer = searchFeatureReference(model, owner);
		return nodeContainer != null ? nodeContainer : owner;
	}

	private static ElementContainer searchFeatureReference(ElementContainer container, Mogram target) {
		for (Mogram component : container.components()) {
			final ElementContainer nodeContainer = searchFeatureReference(component, target);
			if (nodeContainer != null) return nodeContainer;
		}
		return null;
	}

	private static MogramRoot model(ElementContainer owner) {
		ElementContainer container = owner;
		while (container != null) {
			if (container instanceof Mogram && container instanceof MogramRoot) return (MogramRoot) container;
			container = container.container();
		}
		return null;
	}

	public static String calculatePackage(Mogram container) {
		final Mogram node = firstNamedContainer(container);
		return node == null ? "" : layerQn((Mogram) node).replace("$", ".").replace("#", ".").toLowerCase();
	}

	private static Mogram firstNamedContainer(Mogram container) {
		List<Mogram> containers = collectStructure(container);
		Mogram candidate = null;
		for (Mogram nodeContainer : containers) {
			if (nodeContainer instanceof Mogram && !((Mogram) nodeContainer).isAnonymous()) candidate = nodeContainer;
			else if (nodeContainer instanceof Mogram) break;
			else candidate = nodeContainer;
		}
		return candidate;
	}

	private static List<Mogram> collectStructure(Mogram container) {
		List<Mogram> containers = new ArrayList<>();
		ElementContainer current = container;
		while (current != null && !(current instanceof MogramRoot)) {
			containers.add(0, (Mogram) current);
			current = current.container();
		}
		return containers;
	}

	private static Constraint.Property findParameter(List<Constraint.Property> props, String name) {
		return props.stream()
				.filter(prop -> prop.name().equals(name)).
				findFirst()
				.orElse(null);
	}

	private Map<String, Set<String>> load(File importsFile) {
		if (importsFile == null) return new HashMap<>();
		try {
			return new Gson().fromJson(new FileReader(importsFile), new TypeToken<Map<String, Set<String>>>() {
			}.getType());
		} catch (FileNotFoundException e) {
			return new HashMap<>();
		}
	}

	public void fillFrameForFunctionProperty(Property prop, Object body, FrameBuilderContext context) {
		final String signature = getSignature(prop);
		context.add(PACKAGE, this.aPackage);
		final Set<String> imports = new HashSet<>((prop.rule(FunctionRule.class)).imports());
		imports.addAll(collectImports(prop));
		if (!imports.isEmpty()) context.add(IMPORTS, imports.toArray(new String[imports.size()]));
		if (!context.contains(SCOPE)) context.add(SCOPE, workingPackage);
		if (!context.contains(OUT_LANGUAGE)) context.add(OUT_LANGUAGE, outDsl.toLowerCase());
		if (!context.contains(WORKING_PACKAGE))
			context.add(WORKING_PACKAGE, workingPackage.toLowerCase());
		if (!context.contains(RULE)) context.add(RULE, cleanQn(getInterface(prop)));
		if (!context.contains(NAME)) context.add(NAME, prop.name());
		if (!context.contains(QN)) context.add(QN, prop.container().qualifiedName());
		context.add(FILE, prop.source().getPath());
		context.add(LINE, prop.line());
		if (body != null) context.add(BODY, formatBody(body.toString(), signature));
		context.add(NATIVE_CONTAINER, cleanQn(buildContainerPath(workingPackages.getOrDefault(language.languageName(), workingPackage), prop.container(), workingPackage)));
		context.add(SIGNATURE, signature);
		context.add(UID, prop.getUID());
		NativeExtractor extractor = new NativeExtractor(signature);
		context.add("methodName", extractor.methodName());
		context.add("parameters", extractor.parameters());
		context.add("returnType", extractor.returnType());
		context.add("exception", extractor.exceptions());
	}

	public void fillFrameForFunctionParameter(PropertyDescription parameter, Object body, FrameBuilderContext context) {
		final String signature = getSignature(parameter);
		if (!context.contains(OUT_LANGUAGE)) context.add(OUT_LANGUAGE, this.outDsl);
		if (!context.contains(NAME)) context.add(NAME, parameter.name());
		if (!this.aPackage.isEmpty()) context.add(PACKAGE, this.aPackage.toLowerCase());
		if (!context.contains(QN)) context.add(QN, cleanQn(parameter.container().qualifiedName()));
		if (!context.contains(SCOPE)) context.add(SCOPE, workingPackageScope(parameter, workingPackage));
		if (!context.contains(WORKING_PACKAGE)) context.add(WORKING_PACKAGE, workingPackage.toLowerCase());
		if (!context.contains(RULE.toLowerCase())) context.add(RULE, cleanQn(getInterface(parameter)));
		final Set<String> imports = new HashSet<String>(((FunctionRule) parameter.definition().rule(FunctionRule.class)).imports());
		imports.addAll(collectImports(parameter));
		context.add(IMPORTS, imports.toArray(new String[imports.size()]));
		context.add(SIGNATURE, signature);
		context.add(FILE, parameter.source().getPath());
		context.add(LINE, parameter.line());
//		context.add(COLUMN, parameter.column());
		context.add(NATIVE_CONTAINER, cleanQn(buildContainerPath(parameter.scope(), parameter.container(), workingPackage)));
		context.add(UID, parameter.getUID());
		NativeExtractor extractor = new NativeExtractor(signature);
		context.add("methodName", extractor.methodName());
		context.add("parameters", extractor.parameters());
		if (body != null) context.add(BODY, formatBody(body.toString(), signature));
		context.add("returnType", extractor.returnType());
		context.add("exception", extractor.exceptions());
	}

	private String workingPackageScope(Valued valued, String workingPackage) {
		return workingPackages.getOrDefault(language.languageName(), this.workingPackage);
	}

	public void fillFrameFunctionProperty(FrameBuilderContext context, Property prop, Object body) {
		context.add(NATIVE);
		context.add(FILE, prop.source().getPath());
		context.add(LINE, prop.line());
//		context.add(COLUMN, prop.column());
		final Set<String> imports = new HashSet<>(prop.rule(FunctionRule.class) != null ? prop.rule(FunctionRule.class).imports() : new HashSet<>());
		imports.addAll(collectImports(prop));
		if (!context.contains(RULE.toLowerCase())) context.add(RULE, cleanQn(getInterface(prop)));
		context.add(IMPORTS, imports.toArray(new String[imports.size()]));
		if (!aPackage.isEmpty()) context.add(PACKAGE, aPackage.toLowerCase());
		if (!context.contains(NAME)) context.add(NAME, prop.name());
		if (!context.contains(OUT_LANGUAGE)) context.add(OUT_LANGUAGE, outDsl);
		if (!context.contains(WORKING_PACKAGE))
			context.add(WORKING_PACKAGE, workingPackage.toLowerCase());
		context.add(NATIVE_CONTAINER.toLowerCase(), buildContainerPathOfExpression(prop));
		if (!context.contains(TYPE)) context.add(TYPE, typeFrame(type(prop), prop.isMultiple()));
		context.add(UID, prop.getUID());
		if (body != null) context.add(BODY, formatBody(body.toString(), prop.type().getName()));
	}

	public void fillFrameFunctionParameter(FrameBuilderContext context, PropertyDescription parameter, String body) {
		context.add(NATIVE);
		context.add(FILE, parameter.source().getPath());
		context.add(LINE, parameter.line());
//		context.add(COLUMN, parameter.column());
		final Set<String> imports = new HashSet<>(parameter.rule(FunctionRule.class) != null ? (parameter.rule(FunctionRule.class)).imports() : new HashSet<>());
		imports.addAll(collectImports(parameter));
		context.add(IMPORTS, imports.toArray(new String[imports.size()]));
		context.add(NATIVE_CONTAINER, buildContainerPathOfExpression(parameter));
		context.add(UID, parameter.getUID());
		if (!aPackage.isEmpty()) context.add(PACKAGE, aPackage.toLowerCase());
		if (!context.contains(NAME.toLowerCase())) context.add(NAME, parameter.name());
		if (!context.contains(OUT_LANGUAGE.toLowerCase())) context.add(OUT_LANGUAGE, outDsl.toLowerCase());
		if (!context.contains(WORKING_PACKAGE.toLowerCase()))
			context.add(WORKING_PACKAGE, workingPackage.toLowerCase());
		if (!context.contains(TYPE.toLowerCase()))
			context.add(TYPE, typeFrame(type(parameter), isMultiple(parameter)));
		if (body != null) context.add(BODY, formatBody(body, parameter.type().getName()));
	}

	public String type(Property prop) {
		final boolean multiple = prop.isMultiple();
		if (prop.isReference()) {
			return NameFormatter.getQn(((ReferenceProperty) prop).target().get(), workingPackage);
		} else if (OBJECT.equals(prop.type())) return ((NativeObjectRule) prop.rule(NativeObjectRule.class)).type();
		else if (Primitive.WORD.equals(prop.type()))
			return NameFormatter.getQn(prop.container(), workingPackage) + "." + Format.firstUpperCase().format(prop.name());
		else return prop.type().javaName();
	}

	private boolean isMultiple(PropertyDescription parameter) {
		final Constraint.Property constraint = propertyConstraintOf(parameter);
		if (constraint == null) return false;
		Size rule = (Size) constraint.rules().stream().filter(r -> r instanceof Size).findFirst().orElse(null);
		return rule != null && !rule.isSingle();
	}

	public String type(PropertyDescription parameter) {
		final boolean multiple = parameter.isMultiple();
		return parameter.type().equals(OBJECT) ?
				(parameter.rule(NativeObjectRule.class)).type() :
				parameter.type().javaName();
	}

	private Frame typeFrame(String type, boolean multiple) {
		return (multiple ?
				new FrameBuilder("list").add("value", type) :
				new FrameBuilder().add("value", type)).toFrame();
	}

	private List<String> collectImports(Valued parameter) {
		final String qn = (parameter.container().qualifiedName() + "." + parameter.name()).replace(":", "");
		return imports.containsKey(qn) ? new ArrayList<>(imports.get(qn)) : Collections.emptyList();
	}

	public String buildContainerPathOfExpression(Valued valued) {
		return cleanQn(buildExpressionContainerPath(workingPackage, valued.container(), this.outDsl, workingPackage));
	}

	public Constraint.Property propertyConstraintOf(PropertyDescription parameter) {
		List<Constraint.Property> properties = parameterConstraintsOf(parameter.container());
		if (properties.isEmpty() || properties.size() <= parameter.position()) return null;
		return findParameter(properties, parameter.name());
	}

	private List<Constraint.Property> parameterConstraintsOf(Mogram mogram) {
		if (language == null) return Collections.emptyList();
		final List<Constraint> nodeConstraints = language.constraints(mogram.types().get(0));
		if (nodeConstraints == null) return Collections.emptyList();
		final List<Constraint> constraints = new ArrayList<>(nodeConstraints);
		List<Constraint.Property> parameters = new ArrayList<>();
		for (Constraint constraint : constraints)
			if (constraint instanceof Constraint.Property) parameters.add((Constraint.Property) constraint);
			else if (constraint instanceof Constraint.Facet)
				parameters.addAll(((Constraint.Facet) constraint).constraints().stream().filter(c -> c instanceof Constraint.Property).map(c -> (Constraint.Property) c).collect(Collectors.toList()));
		return parameters;
	}
}
