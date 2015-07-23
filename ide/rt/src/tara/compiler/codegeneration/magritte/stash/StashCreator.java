package tara.compiler.codegeneration.magritte.stash;

import tara.io.*;
import tara.language.model.Node;

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

	private Type createType(Node container) {
		final Type type = new Type();
		type.types = container.types().toArray(new String[container.types().size()]);
		type.allowsMultiple = collectAllowsMultiple(container);
		type.requiresMultiple = collectRequiresMultiple(container);
		type.allowsSingle = collectAllowsSingle(container);
		type.requiresSingle = collectRequiresSingle(container);
		for (Node node : container.components()) {
			if (node.isTerminalInstance()) type.add(createCase(node));
			else if (node.isPrototype()) type.add(createPrototype(node));
		}
		return type;
	}

	private String[] collectAllowsMultiple(Node container) {
		return new String[0];
	}

	private String[] collectRequiresMultiple(Node container) {
		return new String[0];
	}

	private String[] collectAllowsSingle(Node container) {
		return new String[0];
	}

	private String[] collectRequiresSingle(Node container) {
		return new String[0];
	}

	private Prototype[] createPrototypes(List<Node> components) {
		List<Prototype> prototypes = components.stream().map(this::createPrototype).collect(Collectors.toList());
		return prototypes.toArray(new Prototype[prototypes.size()]);
	}

	private Prototype createPrototype(Node node) {
		return new Prototype(node.name(), node.types().toArray(new String[node.types().size()]), variablesOf(node), createPrototypes(node.components()));
	}

	private List<Case> createCases(List<Node> components) {
		return components.stream().map(this::createCase).collect(Collectors.toList());
	}

	private Case createCase(Node node) {
		return new Case(node.name(), node.types().toArray(new String[node.types().size()]), variablesOf(node), createCases(node.components()));
	}

	private Variable[] variablesOf(Node node) {
		final List<Variable> variables = node.parameters().stream().
			map(parameter -> new Variable(parameter.name(), parameter.values().toString())).
			collect(Collectors.toList());
		return variables.toArray(new Variable[variables.size()]);
	}
}
