package io.intino.magritte.compiler.codegeneration.magritte.natives;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.intino.itrules.Frame;
import io.intino.itrules.FrameBuilder;
import io.intino.itrules.FrameBuilderContext;
import io.intino.magritte.Language;
import io.intino.magritte.compiler.codegeneration.Format;
import io.intino.magritte.compiler.codegeneration.magritte.NameFormatter;
import io.intino.magritte.compiler.codegeneration.magritte.TemplateTags;
import io.intino.magritte.compiler.model.Model;
import io.intino.magritte.compiler.model.NodeImpl;
import io.intino.magritte.compiler.model.VariableReference;
import io.intino.magritte.lang.model.Aspect;
import io.intino.magritte.lang.model.*;
import io.intino.magritte.lang.model.rules.variable.NativeObjectRule;
import io.intino.magritte.lang.model.rules.variable.NativeRule;
import io.intino.magritte.lang.semantics.Constraint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

import static io.intino.magritte.Resolver.shortType;
import static io.intino.magritte.compiler.codegeneration.magritte.NameFormatter.cleanQn;
import static io.intino.magritte.lang.model.Primitive.OBJECT;
import static io.intino.magritte.lang.model.Tag.*;

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

	private static String getQn(Node owner, String language, boolean m0) {
		return !m0 ? NameFormatter.getQn(owner, language) : language.toLowerCase() + DOT + owner.type();
	}

	private static String getQn(Aspect aspect, String language, boolean m0) {
		return asFacet(aspect, language); //TODO
	}

	private static String asFacet(Aspect aspect, String language) {
		return null;
	}

	private static String getQn(Node owner, Node node, String workingPackage, boolean m0) {
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

	public static String buildContainerPath(String languageWorkingPackage, Node node, String workingPackage) {
		if (node instanceof Node) {
			final Node scope = node.is(Instance) ? firstNoFeature(node) : firstNoFeatureAndNamed(node);
			if (scope == null) return "";
			if (scope.is(Instance)) return getTypeAsScope(scope, languageWorkingPackage);
			return getQn(scope, (Node) node, workingPackage, false);
		} else if (node instanceof Aspect) {
			final Node scope = firstNoFeatureAndNamed(node);
			if (scope == null) return "";
			return scope.is(Instance) ? getTypeAsScope(scope, languageWorkingPackage) : getQn(scope, workingPackage, false);
		} else return "";
	}

	public static String buildExpressionContainerPath(String typeWorkingPackage, Node owner, String outDSL, String workingPackage) {
		final String trueWorkingPackage = extractWorkingPackage(typeWorkingPackage, workingPackage);
		if (owner instanceof Node) {
			final Node scope = ((Node) owner).is(Instance) ? firstNoFeature(owner) : firstNoFeatureAndNamed(owner);
			if (scope == null) return "";
			if (scope.is(Instance)) return getTypeAsScope(scope, trueWorkingPackage);
			else return getQn(scope, (Node) owner, workingPackage, false);
		} else if (owner instanceof Aspect) {
			return ((Node) owner.container()).is(Instance) ? getTypeAsScope(owner, trueWorkingPackage) : getQn((Aspect) owner, workingPackage, false);
		} else return "";
	}

	private static String extractWorkingPackage(String scope, String language) {
		return scope != null && !scope.isEmpty() ? scope : language;
	}

	private static String getTypeAsScope(Node scope, String languageWorkingPackage) {
		return languageWorkingPackage + DOT +
				(scope instanceof Node ? cleanQn(scope.type()) : cleanQn(facetType((Aspect) scope)));
	}

	private static List<Aspect> containerFacets(Node scope) {
		NodeContainer container = scope.container();
		while (container != null && ((Node) container).appliedAspects().isEmpty())
			container = container.container();
		return container != null ? ((Node) container).appliedAspects() : Collections.emptyList();
	}

	private static String facetType(Aspect scope) {
		return scope.type().toLowerCase() + DOT + scope.type() + shortType(scope.container().type());//TODO cuando la faceta esté contenido dentro de otro concepto como saberlo
	}

	private static Node firstNoFeature(NodeContainer owner) {
		NodeContainer container = owner;
		while (container != null) {
			if (container instanceof Node && !(container instanceof NodeRoot) && !((Node) container).is(Feature))
				return (Node) container;
			container = container.container();
		}
		return owner instanceof Node ? (Node) owner : (Node) owner.container();
	}

	private static Node firstNoFeatureAndNamed(NodeContainer owner) {
		NodeContainer container = owner;
		while (container != null) {
			if (container instanceof Node && !(container instanceof NodeRoot) && !((Node) container).isAnonymous() &&
					!((Node) container).is(Feature)) return (Node) container;
			container = container.container();
		}
		return owner instanceof Node ? (Node) owner : (Node) owner.container();
	}

	private static NodeContainer searchFeatureReference(Node owner) {
		final Model model = model(owner);
		if (model == null) return owner;
		final NodeContainer nodeContainer = searchFeatureReference(model, owner);
		return nodeContainer != null ? nodeContainer : owner;
	}

	private static NodeContainer searchFeatureReference(NodeContainer node, Node target) {
		if (node instanceof Node && ((Node) node).isReference() && target.equals(((Node) node).destinyOfReference()))
			return node.container();
		if (node instanceof Node && ((Node) node).isReference()) return null;
		for (Node component : node.components()) {
			final NodeContainer nodeContainer = searchFeatureReference(component, target);
			if (nodeContainer != null) return nodeContainer;
		}
		return null;
	}

	private static Model model(NodeContainer owner) {
		NodeContainer container = owner;
		while (container != null) {
			if (container instanceof Node && container instanceof Model) return (Model) container;
			container = container.container();
		}
		return null;
	}

	public static String calculatePackage(Node container) {
		final Node node = firstNamedContainer(container);
		return node == null ? "" : ((NodeImpl) node).layerQn().replace("$", ".").replace("#", ".").toLowerCase();
	}

	private static Node firstNamedContainer(Node container) {
		List<Node> containers = collectStructure(container);
		Node candidate = null;
		for (Node nodeContainer : containers) {
			if (nodeContainer instanceof Node && !((Node) nodeContainer).isAnonymous()) candidate = nodeContainer;
			else if (nodeContainer instanceof Node) break;
			else candidate = nodeContainer;
		}
		return candidate;
	}

	private static List<Node> collectStructure(Node container) {
		List<Node> containers = new ArrayList<>();
		Node current = container;
		while (current != null && !(current instanceof NodeRoot)) {
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
		final Set<String> imports = new HashSet<>(((NativeRule) variable.rule()).imports());
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
		final Set<String> imports = new HashSet<String>(((NativeRule) parameter.rule()).imports());
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
		final Set<String> imports = new HashSet<>(variable.rule() != null ? ((NativeRule) variable.rule()).imports() : new HashSet<>());
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
		final Set<String> imports = new HashSet<>(parameter.rule() != null ? ((NativeRule) parameter.rule()).imports() : new HashSet<>());
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
			return NameFormatter.getQn(((VariableReference) variable).destinyOfReference(), ((VariableReference) variable).isTypeReference() ? languageWorkingPackage : workingPackage);
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

	private List<Constraint.Parameter> parameterConstraintsOf(Node node) {
		if (language == null) return Collections.emptyList();
		final List<Constraint> nodeConstraints = language.constraints(node.resolve().type());
		if (nodeConstraints == null) return Collections.emptyList();
		final List<Constraint> constraints = new ArrayList<>(nodeConstraints);
		List<Constraint.Parameter> parameters = new ArrayList<>();
		for (Constraint constraint : constraints)
			if (constraint instanceof Constraint.Parameter) parameters.add((Constraint.Parameter) constraint);
			else if (constraint instanceof Constraint.Aspect)
				parameters.addAll(((Constraint.Aspect) constraint).constraints().stream().filter(c -> c instanceof Constraint.Parameter).map(c -> (Constraint.Parameter) c).collect(Collectors.toList()));
		return parameters;
	}
}
