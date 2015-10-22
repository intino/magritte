package tara.compiler.codegeneration.magritte.stash;

import tara.compiler.codegeneration.Format;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.codegeneration.magritte.natives.NativeFormatter;
import tara.compiler.model.NodeReference;
import tara.io.*;
import tara.io.Variable;
import tara.language.model.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StashCreator {
	private static final String BLOB_KEY = "%";
	private final List<Node> nodes;
	private final File rootFolder;
	private String generatedLanguage;
	final Stash stash = new Stash();

	public StashCreator(List<Node> nodes, List<String> uses, String language, String generatedLanguage, File rootFolder) {
		this.nodes = nodes;
		this.rootFolder = rootFolder;
		stash.language = language;
		stash.uses = uses;
		this.generatedLanguage = generatedLanguage;
	}

	public Stash create() {
		nodes.forEach(node -> create(node, null));
		return stash;
	}

	private void create(NodeContainer containerNode, Type container) {
		if (containerNode instanceof NodeReference) return;
		if (containerNode instanceof Node) asNode((Node) containerNode, container);
		else if (containerNode instanceof FacetTarget)
			stash.addAll(createTypes((FacetTarget) containerNode));
	}

	private void asNode(Node node, Type container) {
		if (isCase(node))
			if (container == null) stash.add(createCase(node));
			else container.add(createCase(node));
		else if (node.isPrototype())
			createPrototype(node, container);
		else createType(node);
	}

	private void createPrototype(Node node, Type container) {
		if (node.isAbstract()) createType(node);
		else if (node.isAnonymous()) addPrototype(node, container);
		else {
			createType(node);
			addPrototype(node, container);
		}
	}

	private void addPrototype(Node node, Type container) {
		final Prototype prototype = createPrototype(node);
		if (container == null) stash.add(prototype);
		else container.add(prototype);
	}

	private Type createType(Node node) {
		final Type type = new Type();
		stash.add(type);
		type.name = node.qualifiedNameCleaned();
		if (node.parentName() != null) type.parent = node.parent().qualifiedNameCleaned();
		type.isAbstract = node.isAbstract() || node.isFacet();
		type.isTerminal = node.isTerminal();
		type.isMain = node.isMain();
		if (node.name() != null && !node.name().isEmpty())
			type.className = NameFormatter.getJavaQN(generatedLanguage, node);
		type.types = collectTypes(node);
		addConstrains(node, type);
		for (Node component : node.components())
			create(component, type);
		for (FacetTarget facetTarget : node.facetTargets())
			create(facetTarget, type);
		type.addAll(variablesOf(node));
		return type;
	}

	private List<Type> createTypes(FacetTarget facetTarget) {
		List<Type> types = new ArrayList<>();
		final Type container = new Type();
		container.name = facetTarget.qualifiedNameCleaned();
		container.className = NameFormatter.getJavaQN(generatedLanguage, facetTarget);
		container.types = collectTypes(facetTarget);
		List<Node> components = collectTypeComponents(facetTarget.components());
		container.allowsMultiple = collectAllowsMultiple(components);
		container.requiresMultiple = collectRequiresMultiple(components);
		container.allowsSingle = collectAllowsSingle(components);
		container.requiresSingle = collectRequiresSingle(components);
		container.variables = facetTarget.parameters().stream().map(this::createVariableFromParameter).collect(Collectors.toList());
		for (Node component : facetTarget.components())
			create(component, container);
		types.add(container);
		types.addAll(facetTarget.targetNode().children().stream().
			map(node -> createChildFacetType(facetTarget, node, container)).
			collect(Collectors.toList()));
		return types;
	}

	private Type createChildFacetType(FacetTarget facetTarget, Node node, Type parent) {
		final Type child = new Type();
		child.name = ((Node) facetTarget.container()).name() + "_" + node.name();
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
		final Set<String> facetTypes = node.facets().stream().map(Facet::type).collect(Collectors.toSet());
		types.addAll(withDollar(facetTypes.stream().map(type -> type + "_" + node.type()).collect(Collectors.toList())));
		return types;
	}

	private List<String> collectPrototypeTypes(Node node) {
		List<String> types = new ArrayList<>();
		types.add(withDollar(node.type()));
		if (!node.isAnonymous()) types.add(withDollar(node.qualifiedNameCleaned()));
		final Set<String> facetTypes = node.facets().stream().map(Facet::type).collect(Collectors.toSet());
		types.addAll(withDollar(facetTypes.stream().map(type -> type + "_" + node.type()).collect(Collectors.toList())));
		return types;
	}

	private List<String> withDollar(List<String> names) {
		return names.stream().map(name -> name.replace(".", "$")).collect(Collectors.toList());
	}

	private String withDollar(String name) {
		return name.replace(".", "$");
	}

	private List<String> collectTypes(FacetTarget facetTarget) {
		List<String> types = new ArrayList<>();
		types.add(facetTarget.container().type());
		types.add(((Node) facetTarget.container()).name());
		return types;
	}

	private void addConstrains(Node node, Type type) {
		List<Node> nodeList = collectTypeComponents(node.components());
		type.allowsMultiple = collectAllowsMultiple(nodeList);
		type.requiresMultiple = collectRequiresMultiple(nodeList);
		type.allowsSingle = collectAllowsSingle(nodeList);
		type.requiresSingle = collectRequiresSingle(nodeList);
	}

	private List<Node> collectTypeComponents(List<Node> nodes) {
		return nodes.stream().filter(component -> !isCase(component) && !component.isPrototype()).collect(Collectors.toList());
	}

	private List<String> collectAllowsMultiple(List<Node> nodes) {
		return nodes.stream().filter(component -> !component.isRequired() && !component.isSingle()).map(Node::qualifiedNameCleaned).collect(Collectors.toList());
	}

	private List<String> collectRequiresMultiple(List<Node> nodes) {
		return nodes.stream().filter(component -> component.isRequired() && !component.isSingle()).map(Node::qualifiedNameCleaned).collect(Collectors.toList());
	}

	private List<String> collectAllowsSingle(List<Node> nodes) {
		return nodes.stream().filter(component -> !component.isRequired() && component.isSingle()).map(Node::qualifiedNameCleaned).collect(Collectors.toList());
	}

	private List<String> collectRequiresSingle(List<Node> nodes) {
		return nodes.stream().filter(component -> component.isRequired() && component.isSingle()).map(Node::qualifiedNameCleaned).collect(Collectors.toList());
	}

	private List<Prototype> createPrototypes(List<Node> nodes) {
		return nodes.stream().map(this::createPrototype).collect(Collectors.toList());
	}

	private Prototype createPrototype(Node node) {
		return new Prototype(buildReferenceName(node), couldHaveMorph(node) ? getMorphClass(node) : null, collectPrototypeTypes(node), variablesOf(node), createPrototypes(node.components()));
	}

	private boolean couldHaveMorph(Node node) {
		return !node.qualifiedName().contains(Node.ANNONYMOUS);
	}

	private String getMorphClass(Node node) {
		return node.name() != null && !node.name().isEmpty() ? NameFormatter.getJavaQN(generatedLanguage, node) : null;
	}

	private List<Case> createCases(List<Node> nodes) {
		return nodes.stream().map(this::createCase).collect(Collectors.toList());
	}

	private Case createCase(Node node) {
		return new Case(buildReferenceName(node), collectTypes(node), variablesOf(node), createCases(node.components()));
	}

	private List<Variable> variablesOf(Node node) {
		List<Variable> variables = node.parameters().stream().map(this::createVariableFromParameter).collect(Collectors.toList());
		variables.addAll(node.variables().stream().filter(v -> !v.defaultValues().isEmpty() && !(v.defaultValues().get(0) instanceof EmptyNode) && !v.isInherited()).map(this::createVariableFromModelVariable).collect(Collectors.toList()));
		for (Facet facet : node.facets()) {
			variables.addAll(facet.parameters().stream().map(this::createVariableFromParameter).collect(Collectors.toList()));
			variables.addAll(facet.variables().stream().filter(v -> !v.defaultValues().isEmpty() && !(v.defaultValues().get(0) instanceof EmptyNode) && !v.isInherited()).map(this::createVariableFromModelVariable).collect(Collectors.toList()));
		}
		return variables;
	}

	private Variable createVariableFromParameter(Parameter parameter) {
		final Variable variable = new tara.io.Variable();
		variable.n = parameter.name();
		if (parameter.hasReferenceValue()) variable.v = buildReferenceValues(parameter.values());
		else if (Primitives.NATIVE.equals(parameter.inferredType()))
			variable.v = createNativeReference(parameter.container(), parameter.name(), parameter.getUID());
		else if (Primitives.MEASURE.equals(parameter.inferredType()))
			variable.v = createMeasureValue(parameter.values(), parameter.metric());
		else if (parameter.values().get(0).toString().startsWith("$"))
			variable.v = buildResourceValue(parameter.values(), parameter.file());
		else variable.v = getValue(parameter);
		return variable;
	}

	private Variable createVariableFromModelVariable(tara.language.model.Variable modelVariable) {
		final Variable variable = new tara.io.Variable();
		variable.n = modelVariable.name();
		if (modelVariable.isReference())
			variable.v = buildReferenceValues(modelVariable.defaultValues());
		else if (Primitives.NATIVE.equals(modelVariable.type()))
			variable.v = createNativeReference(modelVariable.container(), modelVariable.name(), modelVariable.getUID());
		else if (Primitives.MEASURE.equals(modelVariable.type()))
			variable.v = createMeasureValue(modelVariable.defaultValues(), modelVariable.defaultExtension());
		else if (modelVariable.defaultValues().get(0).toString().startsWith("$"))
			variable.v = buildResourceValue(modelVariable.defaultValues(), modelVariable.file());
		else variable.v = getValue(modelVariable);
		return variable;
	}

	private String createNativeReference(NodeContainer container, String name, String uid) {
		final String aPackage = NativeFormatter.calculatePackage(container);
		return generatedLanguage.toLowerCase() + ".natives." + (aPackage.isEmpty() ? "" : aPackage + ".") + Format.javaValidName().format(name).toString() + "_" + uid;
	}

	private Object getValue(Parameter parameter) {
		final Primitives.Converter converter = Primitives.getConverter(parameter.inferredType());
		return hasToBeConverted(parameter.values(), parameter.inferredType()) ? convert(parameter.values(), converter) : new ArrayList<>(parameter.values());
	}

	private Object getValue(tara.language.model.Variable variable) {
		final Primitives.Converter converter = Primitives.getConverter(variable.type());
		return hasToBeConverted(variable.defaultValues(), variable.type()) ? convert(variable.defaultValues(), converter) : new ArrayList<>(variable.defaultValues());
	}

	private boolean hasToBeConverted(List<Object> values, String type) {
		return values.get(0) instanceof String && !(Primitives.STRING.equals(type));
	}

	private List<Object> convert(List<Object> values, Primitives.Converter converter) {
		return new ArrayList<>(Arrays.asList(converter.convert(values.toArray(new String[values.size()]))));
	}

	private List<String> createMeasureValue(List<Object> values, String metric) {
		return values.stream().map(value -> value.toString() + " " + metric).collect(Collectors.toList());
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
		return new ArrayList<Object>(values.stream().map(v -> buildReferenceName((Node) v)).collect(Collectors.toList()));
	}

	private String buildReferenceName(Node node) {
		return (isCase(node) ? getStash(node) + "#" : "") + node.qualifiedNameCleaned();
	}

	private boolean isCase(Node node) {
		return (node.isTerminalInstance() || node.isFeatureInstance()) && !node.isPrototype();
	}

	private String getStash(Node node) {
		final File file = new File(node.file());
		File modelRoot = new File(rootFolder.getParent(), "model");
		final String stashPath = file.getAbsolutePath().substring(modelRoot.getAbsolutePath().length() + 1);
		return stashPath.substring(0, stashPath.lastIndexOf("."));
	}
}