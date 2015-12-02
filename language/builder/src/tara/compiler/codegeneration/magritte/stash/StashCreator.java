package tara.compiler.codegeneration.magritte.stash;

import tara.Language;
import tara.compiler.codegeneration.Format;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.codegeneration.magritte.natives.NativeFormatter;
import tara.compiler.model.NodeReference;
import tara.io.*;
import tara.io.Variable;
import tara.lang.model.*;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static tara.lang.model.Primitive.*;

public class StashCreator {
	private static final String BLOB_KEY = "%";
	private final List<Node> nodes;
	private final File rootFolder;
	private String generatedLanguage;
	final Stash stash = new Stash();

	public StashCreator(List<Node> nodes, Language language, String generatedLanguage, File rootFolder) {
		this.nodes = nodes;
		this.rootFolder = rootFolder;
		stash.language = language.languageName();
		this.generatedLanguage = generatedLanguage;
	}

	public Stash create() {
		nodes.forEach(node -> create(node, null));
		return stash;
	}

	private void create(NodeContainer containerNode, Concept container) {
		if (containerNode instanceof NodeReference) return;
		if (containerNode instanceof Node) asNode((Node) containerNode, container);
		else if (containerNode instanceof FacetTarget)
			stash.addAll(createTypes((FacetTarget) containerNode));
	}

	private void asNode(Node node, Concept container) {
		if (isTerminalInstance(node))
			if (container == null) stash.add(createDeclaration(node));
			else container.add(createDeclaration(node));
		else if (node.isPrototype())
			createPrototype(node, container);
		else createType(node);
	}

	private void createPrototype(Node node, Concept container) {
		if (node.isAbstract()) createType(node);
		else if (node.isAnonymous()) addPrototype(node, container);
		else {
			createType(node);
			addPrototype(node, container);
		}
	}

	private void addPrototype(Node node, Concept container) {
		final Prototype prototype = createPrototype(node);
		if (container == null) stash.add(prototype);
		else container.add(prototype);
	}

	private Concept createType(Node node) {
		final Concept concept = new Concept();
		stash.add(concept);
		concept.name = node.qualifiedNameCleaned();
		if (node.parentName() != null)
			concept.parent = node.parent().qualifiedNameCleaned();
		concept.isAbstract = node.isAbstract() || node.isFacet();
		concept.isTerminal = node.isTerminal();
		concept.isMain = node.isComponent();
		if (node.name() != null && !node.name().isEmpty())
			concept.className = NameFormatter.getJavaQN(generatedLanguage, node);
		concept.types = collectTypes(node);
		addConstrains(node, concept);
		for (Node component : node.components())
			create(component, concept);
		for (FacetTarget facetTarget : node.facetTargets())
			create(facetTarget, concept);
		concept.addAll(variablesOf(node));
		return concept;
	}

	private List<Concept> createTypes(FacetTarget facetTarget) {
		List<Concept> concepts = new ArrayList<>();
		final Concept container = new Concept();
		container.name = facetTarget.qualifiedNameCleaned();
		container.className = NameFormatter.getJavaQN(generatedLanguage, facetTarget);
		container.types = collectTypes(facetTarget);
		container.parent = ((Node) facetTarget.container()).name();
		List<Node> components = collectTypeComponents(facetTarget.components());
//		container.allowsMultiple = collectAllowsMultiple(components);
//		container.requiresMultiple = collectRequiresMultiple(components);
//		container.allowsSingle = collectAllowsSingle(components);
//		container.requiresSingle = collectRequiresSingle(components);
		container.variables = facetTarget.parameters().stream().map(this::createVariableFromParameter).collect(Collectors.toList());
		for (Node component : facetTarget.components())
			create(component, container);
		concepts.add(container);
		concepts.addAll(facetTarget.targetNode().children().stream().
			map(node -> createChildFacetType(facetTarget, node, container)).
			collect(Collectors.toList()));
		return concepts;
	}

	private Concept createChildFacetType(FacetTarget facetTarget, Node node, Concept parent) {
		final Concept child = new Concept();
		child.name = ((Node) facetTarget.container()).name() + node.name();
		child.className = NameFormatter.getJavaQN(generatedLanguage, facetTarget);
		final List<String> childTypes = new ArrayList<>(parent.types);
		childTypes.add(parent.name);
		child.types = new ArrayList<>(childTypes);
		child.allowsMultiple = parent.allowsMultiple;
		child.requiresMultiple = parent.requiresMultiple;
		child.allowsSingle = parent.allowsSingle;
		child.requiresSingle = parent.requiresSingle;
		return child;
	}

	private List<String> collectTypes(Node node) {
		List<String> types = new ArrayList<>();
		types.add(withDollar(node.type()));
		final LinkedHashSet<String> facetTypes = node.facets().stream().map(Facet::type).collect(Collectors.toCollection(LinkedHashSet::new));
		types.addAll(withHashTag(facetTypes.stream().map(type -> type + node.type()).collect(Collectors.toList())));
		return types;
	}

	private List<String> collectPrototypeTypes(Node node) {
		List<String> types = new ArrayList<>();
		types.add(withDollar(node.type()));
		if (couldHaveLayer(node)) types.add(withDollar(node.qualifiedNameCleaned()));
		final Set<String> facetTypes = node.facets().stream().map(Facet::type).collect(Collectors.toSet());
		if (couldHaveLayer(node))
			types.addAll(withHashTag(facetTypes.stream().map(type -> type + node.type()).collect(Collectors.toList())));
		return types;
	}

	private List<String> withHashTag(List<String> names) {
		return names.stream().map(name -> name.replace(".", "#")).collect(Collectors.toList());
	}

	private String withDollar(String name) {
		return name.replace(".", "$");
	}

	private List<String> collectTypes(FacetTarget facetTarget) {
		List<String> types = new ArrayList<>();
		types.add(facetTarget.container().type());
		return types;
	}

	private void addConstrains(Node node, Concept concept) {
		List<Node> nodeList = collectTypeComponents(node.components());
//		type.allowsMultiple = collectAllowsMultiple(nodeList);
//		type.requiresMultiple = collectRequiresMultiple(nodeList);
//		type.allowsSingle = collectAllowsSingle(nodeList);
//		type.requiresSingle = collectRequiresSingle(nodeList);
	}

	private List<Node> collectTypeComponents(List<Node> nodes) {
		return nodes.stream().filter(component -> !isTerminalInstance(component) && !component.isPrototype()).collect(Collectors.toList());
	}

	//	private List<String> collectAllowsMultiple(List<Node> nodes) {
//		return nodes.stream().filter(component -> !component.isRequired() && !component.isSingle()).map(Node::qualifiedNameCleaned).collect(Collectors.toList());
//	}
//
//	private List<String> collectRequiresMultiple(List<Node> nodes) {
//		return nodes.stream().filter(component -> component.isRequired() && !component.isSingle()).map(Node::qualifiedNameCleaned).collect(Collectors.toList());
//	}
//
//	private List<String> collectAllowsSingle(List<Node> nodes) {
//		return nodes.stream().filter(component -> !component.isRequired() && component.isSingle()).map(Node::qualifiedNameCleaned).collect(Collectors.toList());
//	}
//
//	private List<String> collectRequiresSingle(List<Node> nodes) {
//		return nodes.stream().filter(component -> component.isRequired() && component.isSingle()).map(Node::qualifiedNameCleaned).collect(Collectors.toList());
//	}
//
	private List<Prototype> createPrototypes(List<Node> nodes) {
		return nodes.stream().map(this::createPrototype).collect(Collectors.toList());
	}

	private Prototype createPrototype(Node node) {
		return new Prototype(buildReferenceName(node), couldHaveLayer(node) ? getMorphClass(node) : null, collectPrototypeTypes(node), variablesOf(node), createPrototypes(node.components()));
	}

	private boolean couldHaveLayer(Node node) {
		return !node.qualifiedName().contains(Node.ANNONYMOUS);
	}

	private String getMorphClass(Node node) {
		return node.name() != null && !node.name().isEmpty() ? NameFormatter.getJavaQN(generatedLanguage, node) : null;
	}

	private List<Instance> createDeclarations(List<Node> nodes) {
		return nodes.stream().map(this::createDeclaration).collect(Collectors.toList());
	}

	private Instance createDeclaration(Node node) {
		return new Instance(buildReferenceName(node), collectTypes(node), variablesOf(node), createDeclarations(node.components()));
	}

	private List<Variable> variablesOf(Node node) {
		List<Variable> variables = node.parameters().stream().map(this::createVariableFromParameter).collect(Collectors.toList());
		for (Facet facet : node.facets())
			variables.addAll(facet.parameters().stream().map(this::createVariableFromParameter).collect(Collectors.toList()));
		return variables;
	}

	private Variable createVariableFromParameter(Parameter parameter) {
		final Variable variable = new tara.io.Variable();
		variable.n = parameter.name();
		if (parameter.hasReferenceValue()) variable.v = buildReferenceValues(parameter.values());
		else if (FUNCTION.equals(parameter.inferredType()) || parameter.flags().contains(Tag.Native.name()))
			variable.v = createNativeReference(parameter);
		else if (parameter.values().get(0).toString().startsWith("$"))
			variable.v = buildResourceValue(parameter.values(), parameter.file());
		else variable.v = getValue(parameter);
		return variable;
	}


	private String createNativeReference(Parameter parameter) {
		final String aPackage = NativeFormatter.calculatePackage(parameter.container());
		return generatedLanguage.toLowerCase() + ".natives." + (aPackage.isEmpty() ? "" : aPackage + ".") + Format.javaValidName().format(parameter.name()).toString() + "_" + parameter.getUID();
	}

	private Object getValue(Parameter parameter) {
		if (parameter.values().get(0) instanceof EmptyNode) return new ArrayList<>();
		return new ArrayList<>(hasToBeConverted(parameter.values(), parameter.inferredType()) ? convert(parameter) : parameter.values());
	}

	private List<?> convert(Parameter parameter) {
		final Primitive type = parameter.inferredType();
		if (type.equals(WORD)) return type.convert(parameter.values().toArray());
		if (type.equals(FILE)) return (parameter.values()).stream().map(Object::toString).collect(Collectors.toList());
		else return type.convert(parameter.values().toArray(new String[parameter.values().size()]));
	}

	private boolean hasToBeConverted(List<Object> values, Primitive type) {
		return ((values.get(0) instanceof String && !(type.equals(STRING))) || type.equals(WORD)) || type.equals(FILE);
	}

	private Object buildResourceValue(List<Object> values, String filePath) {
		return new ArrayList<Object>(values.stream().
			map(v -> BLOB_KEY + getPresentableName(new File(filePath).getName()) + v.toString()).
			collect(Collectors.toList()));
	}

	private static String getPresentableName(String name) {
		return name.substring(0, name.lastIndexOf("."));
	}


	private Object buildReferenceValues(List<Object> values) {
		if (values.get(0) instanceof EmptyNode) return new ArrayList<>();
		return new ArrayList<Object>(values.stream().map(this::buildReferenceName).collect(Collectors.toList()));
	}

	private String buildReferenceName(Object o) {
		return o instanceof Node ? (isTerminalInstance((Node) o) ? getStash((Node) o) + "#" : "") + ((Node) o).qualifiedNameCleaned() : buildDeclarationReference(o);
	}

	private String buildDeclarationReference(Object o) {
		if (o instanceof Primitive.Reference) {
			Primitive.Reference reference = (Primitive.Reference) o;
			return reference.path() + "#" + withDollar(reference.get());
		}
		return "";
	}

	private boolean isTerminalInstance(Node node) {
		return !node.isPrototype() && (node.isDeclaration() || node.isFeatureInstance());
	}

	private String getStash(Node node) {
		final File file = new File(node.file());
		File modelRoot = new File(rootFolder.getParent(), "model");
		final String stashPath = file.getAbsolutePath().substring(modelRoot.getAbsolutePath().length() + 1);
		return stashPath.substring(0, stashPath.lastIndexOf("."));
	}
}