package tara.compiler.codegeneration.magritte.stash;

import tara.compiler.codegeneration.Format;
import tara.compiler.codegeneration.magritte.NameFormatter;
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
		if (node.isTerminalInstance())
			if (container == null) stash.add(createCase(node));
			else container.add(createCase(node));
		else if (node.isPrototype() && !node.isAbstract())
			if (container == null) stash.add(createPrototype(node));
			else container.add(createPrototype(node));
		else createType(node);
	}

	private Type createType(Node node) {
		final Type type = new Type();
		stash.add(type);
		type.isAbstract = node.isAbstract() || node.isFacet();
		type.name = node.qualifiedNameCleaned();
		if (node.name() != null && !node.name().isEmpty())
			type.morph = NameFormatter.getJavaQN(generatedLanguage, node);
		type.types = collectTypes(node);
		addConstrains(node, type);
		for (Node component : node.components())
			create(component, type);
		for (FacetTarget facetTarget : node.facetTargets())
			create(facetTarget, type);
		return type;
	}

	private List<Type> createTypes(FacetTarget facetTarget) {
		List<Type> types = new ArrayList<>();
		final Type parent = new Type();
		parent.name = facetTarget.qualifiedNameCleaned();
		parent.morph = NameFormatter.getJavaQN(generatedLanguage, facetTarget);
		parent.types = collectTypes(facetTarget);
		List<Node> components = collectTypeComponents(facetTarget.components());
		parent.allowsMultiple = collectAllowsMultiple(components);
		parent.requiresMultiple = collectRequiresMultiple(components);
		parent.allowsSingle = collectAllowsSingle(components);
		parent.requiresSingle = collectRequiresSingle(components);
		for (Node component : facetTarget.components())
			create(component, parent);
		types.add(parent);
		types.addAll(facetTarget.targetNode().children().stream().
			map(node -> createChildFacetType(facetTarget, node, parent)).
			collect(Collectors.toList()));
		return types;
	}

	private Type createChildFacetType(FacetTarget facetTarget, Node node, Type parent) {
		final Type child = new Type();
		child.name = ((Node) facetTarget.container()).name() + "_" + node.name();
		child.morph = NameFormatter.getJavaQN(generatedLanguage, facetTarget);
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
		if (node.parentName() != null) types.add(node.parent().qualifiedNameCleaned());
		types.add(withDollar(node.type()));
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
		return nodes.stream().filter(component -> !component.isTerminalInstance() && !component.isPrototype()).collect(Collectors.toList());
	}

	private List<String> collectAllowsMultiple(List<Node> nodes) {
		return nodes.stream().filter(component -> !component.isRequired() && !component.isSingle()).map(Node::name).collect(Collectors.toList());
	}

	private List<String> collectRequiresMultiple(List<Node> nodes) {
		return nodes.stream().filter(component -> component.isRequired() && !component.isSingle()).map(Node::name).collect(Collectors.toList());
	}

	private List<String> collectAllowsSingle(List<Node> nodes) {
		return nodes.stream().filter(component -> !component.isRequired() && component.isSingle()).map(Node::name).collect(Collectors.toList());
	}

	private List<String> collectRequiresSingle(List<Node> nodes) {
		return nodes.stream().filter(component -> component.isRequired() && component.isSingle()).map(Node::name).collect(Collectors.toList());
	}

	private List<Prototype> createPrototypes(List<Node> nodes) {
		return nodes.stream().map(this::createPrototype).collect(Collectors.toList());
	}

	private Prototype createPrototype(Node node) {
		return new Prototype(buildReferenceName(node), couldHaveMorph(node) ? getMorphClass(node) : null, collectTypes(node), variablesOf(node), createPrototypes(node.components()));
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
		final List<Variable> variables = node.parameters().stream().
			map(this::createVariable).
			collect(Collectors.toList());
		for (Facet facet : node.facets()) {
			variables.addAll(facet.parameters().stream().
				map(this::createVariable).
				collect(Collectors.toList()));
		}
		return variables;
	}

	private Variable createVariable(Parameter parameter) {
		final Variable variable = new tara.io.Variable();
		variable.n = parameter.name();
		if (parameter.hasReferenceValue()) variable.v = buildReferenceValues(parameter);
		else if (Primitives.NATIVE.equals(parameter.inferredType())) variable.v = createNativeReference(parameter);
		else if (parameter.values().get(0).toString().startsWith("$")) variable.v = buildResourceValue(parameter);
		else variable.v = getValue(parameter);
		return variable;
	}

	private String createNativeReference(Parameter parameter) {
		return "magritte.natives." + Format.javaValidName().format(generatedLanguage).toString() + "Natives$" + Format.javaValidName().format(parameter.name()).toString() + "_" + parameter.getUID();
	}

	private Object getValue(Parameter parameter) {
		final Primitives.Converter converter = Primitives.getConverter(parameter.inferredType());
		return hasToBeConverted(parameter) ? convert(parameter, converter) : new ArrayList<>(parameter.values());
	}

	private boolean hasToBeConverted(Parameter parameter) {
		return parameter.values().get(0) instanceof String && !(Primitives.STRING.equals(parameter.inferredType()));
	}

	private List<Object> convert(Parameter parameter, Primitives.Converter converter) {
		return new ArrayList<>(Arrays.asList(converter.convert(parameter.values().toArray(new String[parameter.values().size()]))));
	}

	private Object buildResourceValue(Parameter parameter) {
		return new ArrayList<Object>(parameter.values().stream().
			map(v -> BLOB_KEY + getPresentableName(new File(parameter.file()).getName()) + v.toString()).
			collect(Collectors.toList()));
	}

	private static String getPresentableName(String name) {
		return name.substring(0, name.lastIndexOf("."));
	}


	private Object buildReferenceValues(Parameter parameter) {
		return new ArrayList<Object>(parameter.values().stream().
			map(v -> buildReferenceName((Node) v)).collect(Collectors.toList()));
	}

	private String buildReferenceName(Node node) {
		return (node.isTerminalInstance() ? getStash(node) + "#" : "") + node.qualifiedNameCleaned();
	}

	private String getStash(Node node) {
		final File file = new File(node.file());
		File modelRoot = new File(rootFolder.getParent(), "model");
		final String stashPath = file.getAbsolutePath().substring(modelRoot.getAbsolutePath().length() + 1);
		return stashPath.substring(0, stashPath.lastIndexOf("."));
	}
}