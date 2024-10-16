package io.intino.magritte.builder.compiler.codegeneration.magritte.natives;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.intino.itrules.Frame;
import io.intino.itrules.FrameBuilder;
import io.intino.itrules.FrameBuilderContext;
import io.intino.magritte.builder.compiler.codegeneration.magritte.NameFormatter;
import io.intino.magritte.builder.compiler.codegeneration.magritte.TemplateTags;
import io.intino.tara.Language;
import io.intino.tara.builder.model.Model;
import io.intino.tara.builder.model.MogramImpl;
import io.intino.tara.builder.model.VariableReference;
import io.intino.tara.builder.parser.NativeExtractor;
import io.intino.tara.builder.utils.Format;
import io.intino.tara.language.model.*;
import io.intino.tara.language.model.rules.variable.NativeObjectRule;
import io.intino.tara.language.model.rules.variable.NativeRule;
import io.intino.tara.language.semantics.Constraint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

import static io.intino.magritte.builder.compiler.codegeneration.magritte.NameFormatter.cleanQn;
import static io.intino.tara.Resolver.shortType;
import static io.intino.tara.language.model.Primitive.OBJECT;
import static io.intino.tara.language.model.Tag.*;

@SuppressWarnings("ALL")
public class NativeFormatter implements TemplateTags {

	private final String outDsl;
	private final Language language;
	private final String aPackage;
	private final String workingPackage;
	private final boolean system;
	private final Map<String, Set<String>> imports;
	private String languageWorkingPackage;

	public NativeFormatter(Language language, String outDsl, String aPackage, String workingPackage, String languageWorkingPackage, boolean system, File importsFile) {
		this.outDsl = outDsl;
		this.language = language;
		this.aPackage = aPackage;
		this.workingPackage = workingPackage;
		this.languageWorkingPackage = languageWorkingPackage;
		this.system = system;
		this.imports = load(importsFile);
	}

	public static String workingPackageScope(Valued valued, String workingPackage) {
		return valued.scope() != null && !valued.scope().isEmpty() ? valued.scope() : workingPackage;
	}

	private static String getQn(Mogram owner, String language, boolean m0) {
		return !m0 ? NameFormatter.getQn(owner, language) : language.toLowerCase() + DOT + owner.type();
	}

	private static String getQn(Facet facet, String language, boolean m0) {
		return asFacet(facet, language); //TODO
	}

	private static String asFacet(Facet facet, String language) {
		return null;
	}

	private static String getQn(Mogram owner, Mogram node, String workingPackage, boolean m0) {
		return !m0 ? NameFormatter.getQn(owner, workingPackage) : workingPackage.toLowerCase() + DOT + owner.type();
	}

	public static String getSignature(Parameter parameter) {
		return ((NativeRule) parameter.rule()).signature();
	}

	public static String getInterface(Parameter parameter) {
		final NativeRule rule = (NativeRule) parameter.rule();
		if (rule.interfaceClass() == null)
			return "";//throw new SemanticException(new SemanticError("reject.native.signature.notfound", new LanguageParameter(parameter)));
		return rule.interfaceClass();
	}

	public static String getInterface(Variable variable) {
		final NativeRule rule = (NativeRule) variable.rule();
		if (rule.interfaceClass() == null)
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

	public static String getSignature(Variable variable) {
		return ((NativeRule) variable.rule()).signature();
	}

	public static String buildContainerPath(String languageWorkingPackage, Mogram node, String workingPackage) {
		if (node instanceof Mogram) {
			final Mogram scope = node.is(Instance) ? firstNoFeature(node) : firstNoFeatureAndNamed(node);
			if (scope == null) return "";
			if (scope.is(Instance)) return getTypeAsScope(scope, languageWorkingPackage);
			return getQn(scope, (Mogram) node, workingPackage, false);
		} else if (node instanceof Facet) {
			final Mogram scope = firstNoFeatureAndNamed(node);
			if (scope == null) return "";
			return scope.is(Instance) ? getTypeAsScope(scope, languageWorkingPackage) : getQn(scope, workingPackage, false);
		} else return "";
	}

	public static String buildExpressionContainerPath(String typeWorkingPackage, Mogram owner, String outDSL, String workingPackage) {
		final String trueWorkingPackage = extractWorkingPackage(typeWorkingPackage, workingPackage);
		if (owner instanceof Mogram) {
			final Mogram scope = ((Mogram) owner).is(Instance) ? firstNoFeature(owner) : firstNoFeatureAndNamed(owner);
			if (scope == null) return "";
			if (scope.is(Instance)) return getTypeAsScope(scope, trueWorkingPackage);
			else return getQn(scope, (Mogram) owner, workingPackage, false);
		} else if (owner instanceof Facet) {
			return ((Mogram) owner.container()).is(Instance) ? getTypeAsScope(owner, trueWorkingPackage) : getQn((Facet) owner, workingPackage, false);
		} else return "";
	}

	private static String extractWorkingPackage(String scope, String language) {
		return scope != null && !scope.isEmpty() ? scope : language;
	}

	private static String getTypeAsScope(Mogram scope, String languageWorkingPackage) {
		return languageWorkingPackage + DOT +
				(scope instanceof Mogram ? cleanQn(scope.type()) : cleanQn(facetType((Facet) scope)));
	}

	private static List<Facet> containerFacets(Mogram scope) {
		MogramContainer container = scope.container();
		while (container != null && ((Mogram) container).appliedFacets().isEmpty())
			container = container.container();
		return container != null ? ((Mogram) container).appliedFacets() : Collections.emptyList();
	}

	private static String facetType(Facet scope) {
		return scope.type().toLowerCase() + DOT + scope.type() + shortType(scope.container().type());//TODO cuando la faceta esté contenido dentro de otro concepto como saberlo
	}

	private static Mogram firstNoFeature(MogramContainer owner) {
		MogramContainer container = owner;
		while (container != null) {
			if (container instanceof Mogram && !(container instanceof MogramRoot) && !((Mogram) container).is(Feature))
				return (Mogram) container;
			container = container.container();
		}
		return owner instanceof Mogram ? (Mogram) owner : (Mogram) owner.container();
	}

	private static Mogram firstNoFeatureAndNamed(MogramContainer owner) {
		MogramContainer container = owner;
		while (container != null) {
			if (container instanceof Mogram && !(container instanceof MogramRoot) && !((Mogram) container).isAnonymous() &&
					!((Mogram) container).is(Feature)) return (Mogram) container;
			container = container.container();
		}
		return owner instanceof Mogram ? (Mogram) owner : (Mogram) owner.container();
	}

	private static MogramContainer searchFeatureReference(Mogram owner) {
		final Model model = model(owner);
		if (model == null) return owner;
		final MogramContainer nodeContainer = searchFeatureReference(model, owner);
		return nodeContainer != null ? nodeContainer : owner;
	}

	private static MogramContainer searchFeatureReference(MogramContainer node, Mogram target) {
		if (node instanceof Mogram && ((Mogram) node).isReference() && target.equals(((Mogram) node).targetOfReference()))
			return node.container();
		if (node instanceof Mogram && ((Mogram) node).isReference()) return null;
		for (Mogram component : node.components()) {
			final MogramContainer nodeContainer = searchFeatureReference(component, target);
			if (nodeContainer != null) return nodeContainer;
		}
		return null;
	}

	private static Model model(MogramContainer owner) {
		MogramContainer container = owner;
		while (container != null) {
			if (container instanceof Mogram && container instanceof Model) return (Model) container;
			container = container.container();
		}
		return null;
	}

	public static String calculatePackage(Mogram container) {
		final Mogram node = firstNamedContainer(container);
		return node == null ? "" : ((MogramImpl) node).layerQn().replace("$", ".").replace("#", ".").toLowerCase();
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
		Mogram current = container;
		while (current != null && !(current instanceof MogramRoot)) {
			containers.add(0, current);
			current = current.container();
		}
		return containers;
	}

	private static Constraint.Parameter findParameter(List<Constraint.Parameter> parameters, String name) {
		for (Constraint.Parameter variable : parameters)
			if (variable.name().equals(name))
				return variable;
		return null;
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

	public void fillFrameForFunctionVariable(Variable variable, Object body, FrameBuilderContext context) {
		final String signature = getSignature(variable);
		context.add(PACKAGE, this.aPackage);
		final Set<String> imports = new LinkedHashSet<>(((NativeRule) variable.rule()).imports());
		imports.addAll(collectImports(variable));
		if (!imports.isEmpty()) context.add(IMPORTS, imports.toArray(new String[imports.size()]));
		if (!context.contains(SCOPE)) context.add(SCOPE, workingPackage);
		if (!context.contains(OUT_LANGUAGE)) context.add(OUT_LANGUAGE, outDsl.toLowerCase());
		if (!context.contains(WORKING_PACKAGE))
			context.add(WORKING_PACKAGE, workingPackage.toLowerCase());
		if (!context.contains(RULE)) context.add(RULE, cleanQn(getInterface(variable)));
		if (!context.contains(NAME)) context.add(NAME, variable.name());
		if (!context.contains(QN)) context.add(QN, variable.container().qualifiedName());
		context.add(FILE, variable.file());
		context.add(LINE, variable.line());
		context.add(COLUMN, variable.column());
		if (body != null) context.add(BODY, formatBody(body.toString(), signature));
		context.add(NATIVE_CONTAINER, cleanQn(buildContainerPath(variable.scope(), variable.container(), workingPackage)));
		context.add(SIGNATURE, signature);
		context.add(UID, variable.getUID());
		NativeExtractor extractor = new NativeExtractor(signature);
		context.add("methodName", extractor.methodName());
		context.add("parameters", extractor.parameters());
		context.add("returnType", extractor.returnType());
		context.add("exception", extractor.exceptions());
	}

	public void fillFrameForFunctionParameter(Parameter parameter, Object body, FrameBuilderContext context) {
		final String signature = getSignature(parameter);
		if (!context.contains(OUT_LANGUAGE)) context.add(OUT_LANGUAGE, this.outDsl);
		if (!context.contains(NAME)) context.add(NAME, parameter.name());
		if (!this.aPackage.isEmpty()) context.add(PACKAGE, this.aPackage.toLowerCase());
		if (!context.contains(QN)) context.add(QN, cleanQn(parameter.container().qualifiedName()));
		if (!context.contains(SCOPE)) context.add(SCOPE, workingPackageScope(parameter, workingPackage));
		if (!context.contains(WORKING_PACKAGE)) context.add(WORKING_PACKAGE, workingPackage.toLowerCase());
		if (!context.contains(RULE.toLowerCase())) context.add(RULE, cleanQn(getInterface(parameter)));
		final Set<String> imports = new LinkedHashSet<String>(((NativeRule) parameter.rule()).imports());
		imports.addAll(collectImports(parameter));
		context.add(IMPORTS, imports.toArray(new String[imports.size()]));
		context.add(SIGNATURE, signature);
		context.add(FILE, parameter.file());
		context.add(LINE, parameter.line());
		context.add(COLUMN, parameter.column());
		context.add(NATIVE_CONTAINER, cleanQn(buildContainerPath(parameter.scope(), parameter.container(), workingPackage)));
		context.add(UID, parameter.getUID());
		NativeExtractor extractor = new NativeExtractor(signature);
		context.add("methodName", extractor.methodName());
		context.add("parameters", extractor.parameters());
		if (body != null) context.add(BODY, formatBody(body.toString(), signature));
		context.add("returnType", extractor.returnType());
		context.add("exception", extractor.exceptions());
	}

	public void fillFrameNativeVariable(FrameBuilderContext context, Variable variable, Object body) {
		context.add(NATIVE);
		context.add(FILE, variable.file());
		context.add(LINE, variable.line());
		context.add(COLUMN, variable.column());
		final Set<String> imports = new LinkedHashSet<>(variable.rule() != null ? ((NativeRule) variable.rule()).imports() : new LinkedHashSet<>());
		imports.addAll(collectImports(variable));
		if (!context.contains(RULE.toLowerCase())) context.add(RULE, cleanQn(getInterface(variable)));
		context.add(IMPORTS, imports.toArray(new String[imports.size()]));
		if (!aPackage.isEmpty()) context.add(PACKAGE, aPackage.toLowerCase());
		if (!context.contains(NAME)) context.add(NAME, variable.name());
		if (!context.contains(OUT_LANGUAGE)) context.add(OUT_LANGUAGE, outDsl);
		if (!context.contains(WORKING_PACKAGE))
			context.add(WORKING_PACKAGE, workingPackage.toLowerCase());
		context.add(NATIVE_CONTAINER.toLowerCase(), buildContainerPathOfExpression(variable));
		if (!context.contains(TYPE)) context.add(TYPE, typeFrame(type(variable), variable.isMultiple()));
		context.add(UID, variable.getUID());
		if (body != null) context.add(BODY, formatBody(body.toString(), variable.type().getName()));
	}

	public void fillFrameNativeParameter(FrameBuilderContext context, Parameter parameter, String body) {
		context.add(NATIVE);
		context.add(FILE, parameter.file());
		context.add(LINE, parameter.line());
		context.add(COLUMN, parameter.column());
		final Set<String> imports = new LinkedHashSet<>(parameter.rule() != null ? ((NativeRule) parameter.rule()).imports() : new LinkedHashSet<>());
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

	public String type(Variable variable) {
		final boolean multiple = variable.isMultiple();
		if (variable.flags().contains(Concept)) return "io.intino.magritte.framework.Concept";
		if (variable.isReference()) {
			return NameFormatter.getQn(((VariableReference) variable).targetOfReference(), ((VariableReference) variable).isTypeReference() ? languageWorkingPackage : workingPackage);
		} else if (OBJECT.equals(variable.type())) return ((NativeObjectRule) variable.rule()).type();
		else if (Primitive.WORD.equals(variable.type()))
			return NameFormatter.getQn(variable.container(), workingPackage) + "." + Format.firstUpperCase().format(variable.name());
		else return variable.type().javaName();
	}

	private boolean isMultiple(Parameter parameter) {
		final Constraint.Parameter constraint = parameterConstraintOf(parameter);
		return constraint != null && !constraint.size().isSingle();
	}

	public String type(Parameter parameter) {
		final boolean multiple = parameter.isMultiple();
		return parameter.type().equals(OBJECT) ?
				((NativeObjectRule) parameter.rule()).type() :
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
		return cleanQn(buildExpressionContainerPath(valued.scope(), valued.container(), this.outDsl, system ? languageWorkingPackage : workingPackage));
	}

	public Constraint.Parameter parameterConstraintOf(Parameter parameter) {
		List<Constraint.Parameter> parameters = parameterConstraintsOf(parameter.container());
		if (parameters.isEmpty() || parameters.size() <= parameter.position()) return null;
		return findParameter(parameters, parameter.name());
	}

	private List<Constraint.Parameter> parameterConstraintsOf(Mogram node) {
		if (language == null) return Collections.emptyList();
		final List<Constraint> nodeConstraints = language.constraints(node.resolve().type());
		if (nodeConstraints == null) return Collections.emptyList();
		final List<Constraint> constraints = new ArrayList<>(nodeConstraints);
		List<Constraint.Parameter> parameters = new ArrayList<>();
		for (Constraint constraint : constraints)
			if (constraint instanceof Constraint.Parameter) parameters.add((Constraint.Parameter) constraint);
			else if (constraint instanceof Constraint.Facet)
				parameters.addAll(((Constraint.Facet) constraint).constraints().stream().filter(c -> c instanceof Constraint.Parameter).map(c -> (Constraint.Parameter) c).collect(Collectors.toList()));
		return parameters;
	}
}
