package tara.compiler.codegeneration.magritte.stash;

import tara.io.*;
import tara.language.model.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class StashCreator {

	private final List<Node> nodes;

	public StashCreator(List<Node> nodes) {
		this.nodes = nodes;
	}

	public Stash create() {
		final Stash stash = new Stash();
		for (Node node : nodes) {
			if (node.isTerminalInstance()) stash.add(createCase(node));
			else if (node.isPrototype()) stash.add(createPrototype(node));
			else stash.add(createType(node));
		}
		return stash;
	}

	private Type createType(Node node) {
		final Type type = new Type();
		type.isAbstract = node.isAbstract();
		type.name = node.name();
		type.types = collectTypes(node);
		List<Node> nodes = collectTypeComponents(node.components());
		type.allowsMultiple = collectAllowsMultiple(nodes);
		type.requiresMultiple = collectRequiresMultiple(nodes);
		type.allowsSingle = collectAllowsSingle(nodes);
		type.requiresSingle = collectRequiresSingle(nodes);
		for (Node component : node.components())
			if (component.isTerminalInstance()) type.add(createCase(component));
			else if (component.isPrototype()) type.add(createPrototype(component));
		return type;
	}

	private String[] collectTypes(Node node) {
		List<String> types = new ArrayList<>();
		types.add(node.parentName());
		types.addAll(node.types());
		return types.toArray(new String[node.types().size()]);
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
		return new Prototype(node.name(), collectTypes(node), variablesOf(node), createPrototypes(node.components()));
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
