package tara.compiler.codegeneration.magritte.stash;

import tara.compiler.codegeneration.Format;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.model.NodeReference;
import tara.io.*;
import tara.language.model.FacetTarget;
import tara.language.model.Node;
import tara.language.model.NodeContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class StashCreator {

	private final List<Node> nodes;
	private String generatedLanguage;
	final Stash stash = new Stash();

	public StashCreator(List<Node> nodes, String generatedLanguage) {
		this.nodes = nodes;
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
		else stash.add(createType(node));
	}

	private Type createType(Node node) {
		final Type type = new Type();
		type.isAbstract = node.isAbstract() || node.isFacet();
		type.name = node.name();
		if (node.name() != null && !node.name().isEmpty())
			type.morph = NameFormatter.getJavaQN(generatedLanguage, node);
		type.types = collectTypes(node);
		List<Node> nodes = collectTypeComponents(node.components());
		type.allowsMultiple = collectAllowsMultiple(nodes);
		type.requiresMultiple = collectRequiresMultiple(nodes);
		type.allowsSingle = collectAllowsSingle(nodes);
		type.requiresSingle = collectRequiresSingle(nodes);
		for (Node component : node.components())
			create(component, type);
		for (FacetTarget facetTarget : node.facetTargets())
			create(facetTarget, type);
		return type;
	}

	private Type createType(FacetTarget facetTarget) {
		final Type type = new Type();
		type.name = Format.javaValidName().format(((Node) facetTarget.container()).name() + "_" + facetTarget.targetNode().name()).toString();
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
		types.add(node.parentName());
		types.addAll(node.types());
		return types.toArray(new String[types.size()]);
	}

	private String[] collectTypes(FacetTarget facetTarget) {
		List<String> types = new ArrayList<>();
		types.add(facetTarget.container().type());
		types.add(((Node) facetTarget.container()).name());
		return types.toArray(new String[types.size()]);
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
			map(parameter -> new Variable(parameter.name(), parameter.values().toString())).
			collect(Collectors.toList());
		return variables.toArray(new Variable[variables.size()]);
	}
}
