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
import java.util.stream.Collectors;


public class StashCreator {
	public static final String PASS_KEY = "!";
	private static final String BLOB_KEY = "%";
	private final List<Node> nodes;
	private final File rootFolder;
	private String generatedLanguage;
	final Stash stash = new Stash();

	public StashCreator(List<Node> nodes, List<String> uses, String generatedLanguage, File rootFolder) {
		this.nodes = nodes;
		this.rootFolder = rootFolder;
		stash.language = nodes.get(0).language();
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
		else if (containerNode instanceof FacetTarget) stash.add(createType((FacetTarget) containerNode));
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
		type.name = withDollar(node.qualifiedName());
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

	private Type createType(FacetTarget facetTarget) {
		final Type type = new Type();
		type.name = Format.javaValidName().format(((Node) facetTarget.container()).name() + "_" + facetTarget.targetNode().name()).toString();//TODO FACET INNER
		type.morph = NameFormatter.getJavaQN(generatedLanguage, facetTarget);
		type.types = collectTypes(facetTarget);
		List<Node> nodes = collectTypeComponents(facetTarget.components());
		type.allowsMultiple = collectAllowsMultiple(nodes);
		type.requiresMultiple = collectRequiresMultiple(nodes);
		type.allowsSingle = collectAllowsSingle(nodes);
		type.requiresSingle = collectRequiresSingle(nodes);
		for (Node component : facetTarget.components())
			create(component, type);
		return type;
	}

	private String[] collectTypes(Node node) {
		List<String> types = new ArrayList<>();
		if (node.parentName() != null) types.add(withDollar(node.parent().qualifiedName()));
		types.addAll(withDollar(node.types()));
		return types.toArray(new String[types.size()]);
	}

	private List<String> withDollar(List<String> names) {
		return names.stream().map(name -> name.replace(".", "$")).collect(Collectors.toList());
	}

	private String withDollar(String name) {
		return name.replace(".", "$");
	}

	private String[] collectTypes(FacetTarget facetTarget) {
		List<String> types = new ArrayList<>();
		types.add(facetTarget.container().type());
		types.add(((Node) facetTarget.container()).name());
		return types.toArray(new String[types.size()]);
	}

	private void addConstrains(Node node, Type type) {
		List<Node> nodes = collectTypeComponents(node.components());
		type.allowsMultiple = collectAllowsMultiple(nodes);
		type.requiresMultiple = collectRequiresMultiple(nodes);
		type.allowsSingle = collectAllowsSingle(nodes);
		type.requiresSingle = collectRequiresSingle(nodes);
	}

	private List<Node> collectTypeComponents(List<Node> nodes) {
		return nodes.stream().filter(component -> !component.isTerminalInstance() && component.isPrototype()).collect(Collectors.toList());
	}

	private String[] collectAllowsMultiple(List<Node> nodes) {
		List<String> components = nodes.stream().filter(component -> !component.isRequired() && !component.isSingle()).map(Node::type).collect(Collectors.toList());
		return components.toArray(new String[components.size()]);
	}

	private String[] collectRequiresMultiple(List<Node> nodes) {
		List<String> components = nodes.stream().filter(component -> component.isRequired() && !component.isSingle()).map(Node::type).collect(Collectors.toList());
		return components.toArray(new String[components.size()]);
	}

	private String[] collectAllowsSingle(List<Node> nodes) {
		List<String> components = nodes.stream().filter(component -> !component.isRequired() && component.isSingle()).map(Node::type).collect(Collectors.toList());
		return components.toArray(new String[components.size()]);
	}

	private String[] collectRequiresSingle(List<Node> nodes) {
		List<String> components = nodes.stream().filter(component -> component.isRequired() && component.isSingle()).map(Node::type).collect(Collectors.toList());
		return components.toArray(new String[components.size()]);
	}

	private Prototype[] createPrototypes(List<Node> nodes) {
		List<Prototype> prototypes = nodes.stream().map(this::createPrototype).collect(Collectors.toList());
		return prototypes.toArray(new Prototype[prototypes.size()]);
	}

	private Prototype createPrototype(Node node) {
		return new Prototype(node.name(), getMorphClass(node), collectTypes(node), variablesOf(node), createPrototypes(node.components()));
	}

	private String getMorphClass(Node node) {
		return node.name() != null && !node.name().isEmpty() ? NameFormatter.getJavaQN(generatedLanguage, node) : null;
	}

	private List<Case> createCases(List<Node> nodes) {
		return nodes.stream().map(this::createCase).collect(Collectors.toList());
	}

	private Case createCase(Node node) {
		return new Case(node.name(), collectTypes(node), variablesOf(node), createCases(node.components()));
	}

	private Variable[] variablesOf(Node node) {
		final List<Variable> variables = node.parameters().stream().
			map(this::createVariable).
			collect(Collectors.toList());
		return variables.toArray(new Variable[variables.size()]);
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
		return "magritte.natives." + parameter.name() + "_" + parameter.getUID();
	}

	private java.lang.Object getValue(Parameter parameter) {
		final Primitives.Converter converter = Primitives.getConverter(parameter.inferredType());
		final java.lang.Object[] objects = (parameter.values().get(0) instanceof String && !(Primitives.STRING.equals(parameter.inferredType()))) ?
			converter.convert(parameter.values().toArray(new String[parameter.values().size()])) :
			parameter.values().toArray();
		return objects.length == 1 ? objects[0] : objects;
	}

	private java.lang.Object buildResourceValue(Parameter parameter) {
		List<java.lang.Object> values = parameter.values().stream().
			map(v -> BLOB_KEY + getPresentableName(new File(parameter.file()).getName()) + v.toString()).
			collect(Collectors.toList());
		return values.size() == 1 ? values.get(0) : values.toArray();
	}

	private static String getPresentableName(String name) {
		return name.substring(0, name.lastIndexOf("."));
	}


	private java.lang.Object buildReferenceValues(Parameter parameter) {
		List<java.lang.Object> values = parameter.values().stream().
			map(v -> {
				File file = searchFile(v.toString());
				if (file == null) return null; //TODO Throw an exception
				return PASS_KEY + file.getPath().substring((rootFolder + File.separator).length()) + "#" + getQn(file, v.toString());
			}).collect(Collectors.toList());
		return values.size() == 1 ? values.get(0) : values.toArray();
	}

	private File searchFile(String value) {
		final List<String> split = Arrays.asList(value.split("\\."));
		String name;
		for (int i = 0; i < split.size() - 1; i++) {
			name = joinByDot(split.subList(0, i + 1));
			final File file = fileOf(name);
			if (file.exists()) return file;
		}
		return null;
	}

	private File fileOf(String name) {
		return new File(rootFolder, name.replace(".", File.separator) + ".tara");
	}

	private String joinByDot(List<String> names) {
		String result = "";
		for (String name : names) result += "." + name;
		return result.substring(1);
	}

	private String getQn(File prefix, String value) {
		return value.replaceFirst(getPresentableName(prefix.getName()) + '.', "");
	}
}