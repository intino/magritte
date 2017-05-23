package io.intino.tara.compiler.codegeneration.magritte.natives;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.intino.tara.Language;
import io.intino.tara.compiler.codegeneration.Format;
import io.intino.tara.compiler.codegeneration.magritte.NameFormatter;
import io.intino.tara.compiler.codegeneration.magritte.TemplateTags;
import io.intino.tara.compiler.model.Model;
import io.intino.tara.compiler.model.VariableReference;
import io.intino.tara.lang.model.Facet;
import io.intino.tara.lang.model.*;
import io.intino.tara.lang.model.rules.variable.NativeObjectRule;
import io.intino.tara.lang.model.rules.variable.NativeRule;
import io.intino.tara.lang.semantics.Constraint;
import org.siani.itrules.model.Frame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

import static io.intino.tara.Resolver.shortType;
import static io.intino.tara.compiler.codegeneration.magritte.NameFormatter.cleanQn;
import static io.intino.tara.dsl.Proteo.FACET_SEPARATOR;
import static io.intino.tara.lang.model.Primitive.OBJECT;
import static io.intino.tara.lang.model.Tag.*;

@SuppressWarnings("ALL")
public class NativeFormatter implements TemplateTags {

	private final String outDsl;
	private final Language language;
	private final String aPackage;
	private final String workingPackage;
	private String languageWorkingPackage;
	private final boolean system;
	private final Map<String, Set<String>> imports;

	public NativeFormatter(Language language, String outDsl, String aPackage, String workingPackage, String languageWorkingPackage, boolean system, File importsFile) {
		this.outDsl = outDsl;
		this.language = language;
		this.aPackage = aPackage;
		this.workingPackage = workingPackage;
		this.languageWorkingPackage = languageWorkingPackage;
		this.system = system;
		this.imports = load(importsFile);
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

	public void fillFrameForFunctionVariable(Frame frame, Variable variable, Object body) {
		final List<String> slots = Arrays.asList(frame.slots());
		final String signature = getSignature(variable);
		frame.addFrame(PACKAGE, this.aPackage);
		final Set<String> imports = new HashSet<>(((NativeRule) variable.rule()).imports());
		imports.addAll(collectImports(variable));
		frame.addFrame(IMPORTS, imports.toArray(new String[imports.size()]));
		if (!slots.contains(SCOPE.toLowerCase())) frame.addFrame(SCOPE, workingPackage);
		if (!slots.contains(OUT_LANGUAGE.toLowerCase())) frame.addFrame(OUT_LANGUAGE, outDsl.toLowerCase());
		if (!slots.contains(WORKING_PACKAGE.toLowerCase()))
			frame.addFrame(WORKING_PACKAGE, workingPackage.toLowerCase());
		if (!slots.contains(RULE.toLowerCase())) frame.addFrame(RULE, cleanQn(getInterface(variable)));
		if (!slots.contains(NAME.toLowerCase())) frame.addFrame(NAME, variable.name());
		if (!slots.contains(QN.toLowerCase())) frame.addFrame(QN, variable.container().qualifiedName());
		frame.addFrame(FILE, variable.file());
		frame.addFrame(LINE, variable.line());
		frame.addFrame(COLUMN, variable.column());
		if (body != null) frame.addFrame(BODY, formatBody(body.toString(), signature));
		frame.addFrame(NATIVE_CONTAINER, cleanQn(buildContainerPath(variable.scope(), variable.container(), workingPackage)));
		frame.addFrame(SIGNATURE, signature);
		frame.addFrame(UID, variable.getUID());
		NativeExtractor extractor = new NativeExtractor(signature);
		frame.addFrame("methodName", extractor.methodName());
		frame.addFrame("parameters", extractor.parameters());
		frame.addFrame("returnType", extractor.returnType());
		frame.addFrame("exception", extractor.exceptions());
	}

	public void fillFrameForFunctionParameter(Frame frame, Parameter parameter, Object body) {
		final List<String> slots = Arrays.asList(frame.slots());
		final String signature = getSignature(parameter);
		if (!slots.contains(OUT_LANGUAGE.toLowerCase())) frame.addFrame(OUT_LANGUAGE, this.outDsl);
		if (!slots.contains(NAME.toLowerCase())) frame.addFrame(NAME, parameter.name());
		if (!this.aPackage.isEmpty()) frame.addFrame(PACKAGE, this.aPackage.toLowerCase());
		if (!slots.contains(QN.toLowerCase())) frame.addFrame(QN, parameter.container().qualifiedName());
		if (!slots.contains(SCOPE.toLowerCase())) frame.addFrame(SCOPE, workingPackageScope(parameter, workingPackage));
		if (!slots.contains(WORKING_PACKAGE.toLowerCase()))
			frame.addFrame(WORKING_PACKAGE, workingPackage.toLowerCase());
		if (!slots.contains(RULE.toLowerCase())) frame.addFrame(RULE, cleanQn(getInterface(parameter)));
		final Set<String> imports = new HashSet<String>(((NativeRule) parameter.rule()).imports());
		imports.addAll(collectImports(parameter));
		frame.addFrame(IMPORTS, imports.toArray(new String[imports.size()]));
		frame.addFrame(SIGNATURE, signature);
		frame.addFrame(FILE, parameter.file());
		frame.addFrame(LINE, parameter.line());
		frame.addFrame(COLUMN, parameter.column());
		frame.addFrame(NATIVE_CONTAINER, cleanQn(buildContainerPath(parameter.scope(), parameter.container(), workingPackage)));
		frame.addFrame(UID, parameter.getUID());
		NativeExtractor extractor = new NativeExtractor(signature);
		frame.addFrame("methodName", extractor.methodName());
		frame.addFrame("parameters", extractor.parameters());
		if (body != null) frame.addFrame(BODY, formatBody(body.toString(), signature));
		frame.addFrame("returnType", extractor.returnType());
		frame.addFrame("exception", extractor.exceptions());
	}

	public void fillFrameNativeVariable(Frame frame, Variable variable, Object body) {
		final List<String> slots = Arrays.asList(frame.slots());
		frame.addTypes(NATIVE);
		frame.addFrame(FILE, variable.file());
		frame.addFrame(LINE, variable.line());
		frame.addFrame(COLUMN, variable.column());
		final Set<String> imports = new HashSet<>(variable.rule() != null ? ((NativeRule) variable.rule()).imports() : new HashSet<>());
		imports.addAll(collectImports(variable));
		if (!slots.contains(RULE.toLowerCase())) frame.addFrame(RULE, cleanQn(getInterface(variable)));
		frame.addFrame(IMPORTS, imports.toArray(new String[imports.size()]));
		if (!aPackage.isEmpty()) frame.addFrame(PACKAGE, aPackage.toLowerCase());
		if (!slots.contains(NAME.toLowerCase())) frame.addFrame(NAME, variable.name());
		if (!slots.contains(OUT_LANGUAGE.toLowerCase())) frame.addFrame(OUT_LANGUAGE, outDsl);
		if (!slots.contains(WORKING_PACKAGE.toLowerCase()))
			frame.addFrame(WORKING_PACKAGE, workingPackage.toLowerCase());
		frame.addFrame(NATIVE_CONTAINER.toLowerCase(), buildContainerPathOfExpression(variable));
		if (!slots.contains(TYPE.toLowerCase())) frame.addFrame(TYPE, typeFrame(type(variable), variable.isMultiple()));
		frame.addFrame(UID, variable.getUID());
		if (body != null) frame.addFrame(BODY, formatBody(body.toString(), variable.type().getName()));
	}

	public void fillFrameNativeParameter(Frame frame, Parameter parameter, String body) {
		final List<String> slots = Arrays.asList(frame.slots());
		frame.addTypes(NATIVE);
		frame.addFrame(FILE, parameter.file());
		frame.addFrame(LINE, parameter.line());
		frame.addFrame(COLUMN, parameter.column());
		final Set<String> imports = new HashSet<>(parameter.rule() != null ? ((NativeRule) parameter.rule()).imports() : new HashSet<>());
		imports.addAll(collectImports(parameter));
		frame.addFrame(IMPORTS, imports.toArray(new String[imports.size()]));
		frame.addFrame(NATIVE_CONTAINER, buildContainerPathOfExpression(parameter));
		frame.addFrame(UID, parameter.getUID());
		if (!aPackage.isEmpty()) frame.addFrame(PACKAGE, aPackage.toLowerCase());
		if (!slots.contains(NAME.toLowerCase())) frame.addFrame(NAME, parameter.name());
		if (!slots.contains(OUT_LANGUAGE.toLowerCase())) frame.addFrame(OUT_LANGUAGE, outDsl.toLowerCase());
		if (!slots.contains(WORKING_PACKAGE.toLowerCase()))
			frame.addFrame(WORKING_PACKAGE, workingPackage.toLowerCase());
		if (!slots.contains(TYPE.toLowerCase()))
			frame.addFrame(TYPE, typeFrame(type(parameter), isMultiple(parameter)));
		if (body != null) frame.addFrame(BODY, formatBody(body, parameter.type().getName()));
	}

	public String type(Variable variable) {
		final boolean multiple = variable.isMultiple();
		if (variable.flags().contains(Concept)) return "io.intino.tara.magritte.Concept";
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
		return multiple ? new Frame().addTypes(TYPE, "list").addFrame("value", type) : new Frame().addTypes(TYPE).addFrame("value", type);
	}

	private List<String> collectImports(Valued parameter) {
		final String qn = (parameter.container().qualifiedName() + "." + parameter.name()).replace(":", "");
		return imports.containsKey(qn) ? new ArrayList<>(imports.get(qn)) : Collections.emptyList();
	}

	public static String workingPackageScope(Valued valued, String workingPackage) {
		return valued.scope() != null && !valued.scope().isEmpty() ? valued.scope() : workingPackage;
	}

	private static String getQn(Node owner, String language, boolean m0) {
		return asNode(owner, language, m0, null);
	}

	private static String getQn(Facet facet, String language, boolean m0) {
		return asFacet(facet, language); //TODO
	}

	private static String asFacet(Facet facet, String language) {
		return null;
	}

	private static String getQn(Node owner, Node node, String language, boolean m0) {
		final FacetTarget facetTarget = isInFacetTarget(node);
		if (owner.isFacet() && facetTarget != null) return asFacetTarget(owner, language, facetTarget);
		else return asNode(owner, language, m0, facetTarget);
	}

	private static String asNode(Node node, String language, boolean m0, FacetTarget facetTarget) {
		return !m0 ? language.toLowerCase() + DOT + (facetTarget == null ? node.qualifiedName() : NameFormatter.composeInFacetTargetQN(node, facetTarget)) :
				language.toLowerCase() + DOT + node.type();
	}

	private static String asFacetTarget(Node owner, String language, FacetTarget facetTarget) {
		return language.toLowerCase() + DOT + owner.name().toLowerCase() + DOT +
				Format.reference().format(owner.name()) + Format.reference().format(facetTarget.target());
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

	public String buildContainerPathOfExpression(Valued valued) {
		return buildExpressionContainerPath(valued.scope(), valued.container(), this.outDsl, system ? languageWorkingPackage : workingPackage);
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

	public static String buildContainerPath(String scopeLanguage, Node owner, String workingPackage) {
		if (owner instanceof Node && ((Node) owner).facetTarget() == null) {
			final Node scope = owner.is(Instance) ? firstNoFeature(owner) : firstNoFeatureAndNamed(owner);
			if (scope == null) return "";
			if (scope.is(Instance)) return getTypeAsScope(scope, scopeLanguage);
			if (scope.facetTarget() != null) return NameFormatter.getQn(scope.facetTarget(), scope, workingPackage);
			return getQn(scope, (Node) owner, workingPackage, false);
		} else if (owner instanceof Node) return NameFormatter.getQn(owner.facetTarget(), owner, workingPackage);
		else if (owner instanceof Facet) {
			final Node parent = firstNoFeatureAndNamed(owner);
			if (parent == null) return "";
			return parent.is(Instance) ? getTypeAsScope(parent, scopeLanguage) : getQn(parent, workingPackage, false);
		} else return "";
	}

	public static String buildExpressionContainerPath(String typeWorkingPackage, Node owner, String outDSL, String workingPackage) {
		final String trueWorkingPackage = extractWorkingPackage(typeWorkingPackage, workingPackage);
		if (owner instanceof Node) {
			final Node scope = ((Node) owner).is(Instance) ? firstNoFeature(owner) : firstNoFeatureAndNamed(owner);
			if (scope == null) return "";
			if (scope.is(Instance)) return getTypeAsScope(scope, trueWorkingPackage);
			if (scope.facetTarget() != null)
				return NameFormatter.getQn(((Node) scope).facetTarget(), scope, workingPackage);
			else return getQn(scope, (Node) owner, workingPackage, false);
		} else if (owner instanceof FacetTarget)
			return NameFormatter.getQn((FacetTarget) owner, workingPackage);
		else if (owner instanceof Facet) {
			return ((Node) owner.container()).is(Instance) ? getTypeAsScope(owner, trueWorkingPackage) : getQn((Facet) owner, workingPackage, false);
		} else return "";
	}

	private static String extractWorkingPackage(String scope, String language) {
		return scope != null && !scope.isEmpty() ? scope : language;
	}

	private static String getTypeAsScope(Node scope, String workingPackage) {
		return workingPackage + DOT +
				(scope instanceof Node ? cleanQn(qualifiedType(scope, containerFacets(scope))) : cleanQn(facetType((Facet) scope)));
	}

	private static List<Facet> containerFacets(Node scope) {
		NodeContainer container = scope.container();
		while (container != null && ((Node) container).facets().isEmpty())
			container = container.container();
		return container != null ? ((Node) container).facets() : Collections.emptyList();
	}

	private static String qualifiedType(Node scope, List<Facet> facets) {
		if (facets.isEmpty()) return scope.type();
		for (Facet facet : facets)
			if (scope.type().startsWith(facet.type() + FACET_SEPARATOR) || scope.type().startsWith(DOT + facet.type() + FACET_SEPARATOR))
				return facet.type().toLowerCase() + DOT + scope.type();
		return scope.type();
	}

	private static String facetType(Facet scope) {
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

	private static FacetTarget isInFacetTarget(Node node) {
		Node container = node.container();
		while (container != null && (container instanceof Node) && ((Node) container).facetTarget() == null)
			container = container.container();
		return container != null && container instanceof Node ? ((Node) container).facetTarget() : null;
	}

	public static String calculatePackage(Node container) {
		final Node node = firstNamedContainer(container);
		return node == null ? "" : node.cleanQn().replace("$", ".").replace("#", ".").toLowerCase();
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
			else if (constraint instanceof Constraint.Facet)
				parameters.addAll(((Constraint.Facet) constraint).constraints().stream().filter(c -> c instanceof Constraint.Parameter).map(c -> (Constraint.Parameter) c).collect(Collectors.toList()));
		return parameters;
	}

	private static Constraint.Parameter findParameter(List<Constraint.Parameter> parameters, String name) {
		for (Constraint.Parameter variable : parameters)
			if (variable.name().equals(name))
				return variable;
		return null;
	}
}
