package io.intino.magritte.io;

import io.intino.magritte.io.model.Concept;
import io.intino.magritte.io.model.Node;
import io.intino.magritte.io.model.Stash;
import io.intino.magritte.io.model.Variable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Helper {

	public static Stash newStash(String language, List<String> uses, List<Concept.Content> contentRules, List<Concept> concepts, List<Node> nodes) {
		Stash stash = new Stash();
		stash.language = language;
		stash.uses.addAll(uses);
		stash.contentRules.addAll(contentRules);
		stash.concepts.addAll(concepts);
		stash.nodes.addAll(nodes);
		return stash;
	}

	public static Stash newStash(String language, List<Node> nodes) {
        return stash(language, nodes, "", "");
	}

    private static Stash stash(String language, List<Node> nodes, String builder, String path) {
        Stash stash = new Stash();
        stash.language = language;
        stash.path = path;
        stash.builder = builder;
        stash.nodes.addAll(nodes);
        return stash;
    }

	public static Concept newConcept(String name, boolean isAbstract, boolean isMetaConcept, boolean isAspect, boolean isMain, String className, String parent, List<String> types, List<Concept.Content> contents, List<Variable> variables, List<Variable> parameters, List<Node> nodes) {
		Concept concept = new Concept();
		concept.name = name;
		concept.isAbstract = isAbstract;
		concept.isMetaConcept = isMetaConcept;
		concept.isAspect = isAspect;
		concept.isMain = isMain;
		concept.className = className;
		concept.parent = parent;
		concept.types.addAll(types);
		concept.contentRules.addAll(contents);
		concept.variables.addAll(variables);
		concept.parameters.addAll(parameters);
		concept.nodes.addAll(nodes);
		return concept;
	}

	public static Node newNode(String name, List<String> layers, List<? extends Variable> variables, List<Node> nodes) {
		Node node = new Node();
		node.name = name;
		node.layers.addAll(layers);
		node.variables.addAll(variables);
		node.nodes.addAll(nodes);
		return node;
	}

	@SafeVarargs
	public static <T> List<T> list(T... elements) {
		return new ArrayList<>(Arrays.asList(elements));
	}

	public static Variable newVariableOfList(String name, List<?> values) {
		Variable variable = new Variable();
		variable.name = name;
		variable.values = new ArrayList<>(values);
		return variable;
	}

	public static Variable newVariable(String name, Object... value) {
		Variable variable = new Variable();
		variable.name = name;
		variable.values = new ArrayList<>(Arrays.asList(value));
		return variable;
	}

}
