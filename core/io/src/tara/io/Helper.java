package tara.io;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

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
		Stash stash = new Stash();
		stash.language = language;
		stash.nodes.addAll(nodes);
		return stash;
	}

	public static Concept newConcept(String name, boolean isAbstract, boolean isMetaConcept, boolean isMain, String className, String parent, List<String> types, List<Concept.Content> contents, List<Variable> variables, List<Variable> parameters, List<Node> nodes) {
		Concept concept = new Concept();
		concept.name = name;
		concept.isAbstract = isAbstract;
		concept.isMetaConcept = isMetaConcept;
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

	public static Node newNode(String name, List<String> facets, List<? extends Variable> variables, List<Node> nodes) {
		Node node = new Node();
		node.name = name;
		node.facets.addAll(facets);
		node.variables.addAll(variables);
		node.nodes.addAll(nodes);
		return node;
	}

	public static Variable.Integer newInteger(String name, List<Integer> values) {
		return (Variable.Integer) fillVariable(new Variable.Integer(), name, values);
	}

	public static Variable.Double newDouble(String name, List<Double> values) {
		return (Variable.Double) fillVariable(new Variable.Double(), name, values);
	}

	public static Variable.Boolean newBoolean(String name, List<Boolean> values) {
		return (Variable.Boolean) fillVariable(new Variable.Boolean(), name, values.stream().map(Object::toString).collect(toList()));
	}

	public static Variable.String newString(String name, List<String> values) {
		return (Variable.String) fillVariable(new Variable.String(), name, values);
	}

	public static Variable.String newConcept(String name, List<String> values) {
		return (Variable.String) fillVariable(new Variable.String(), name, values);
	}

	public static Variable.Resource newResource(String name, List<String> values) {
		return (Variable.Resource) fillVariable(new Variable.Resource(), name, values);
	}

	public static Variable.Reference newReference(String name, List<String> values) {
		return (Variable.Reference) fillVariable(new Variable.Reference(), name, values);
	}

	public static Variable.Word newWord(String name, List<String> values) {
		return (Variable.Word) fillVariable(new Variable.Word(), name, values);
	}

	public static Variable.Function newFunction(String name, List<String> values) {
		return (Variable.Function) fillVariable(new Variable.Function(), name, values);
	}

	public static Variable.Date newDate(String name, List<String> values) {
		return (Variable.Date) fillVariable(new Variable.Date(), name, values);
	}

	public static Variable.Time newTime(String name, List<String> values) {
		return (Variable.Time) fillVariable(new Variable.Time(), name, values);
	}

	public static Variable.Object newObject(String name, List<?> values) {
		return (Variable.Object) fillVariable(new Variable.Object(), name, values);
	}

	public static Variable.Integer newInteger(String name, Integer... values) {
		return newInteger(name, list(values));
	}

	public static Variable.Double newDouble(String name, Double... values) {
		return newDouble(name, list(values));
	}

	public static Variable.Boolean newBoolean(String name, Boolean... values) {
		return newBoolean(name, list(values));
	}

	public static Variable.String newString(String name, String... values) {
		return newString(name, list(values));
	}

	public static Variable.Resource newResource(String name, String... values) {
		return newResource(name, list(values));
	}

	public static Variable.Reference newReference(String name, String... values) {
		return newReference(name, list(values));
	}

	public static Variable.Word newWord(String name, String... values) {
		return newWord(name, list(values));
	}

	public static Variable.Function newFunction(String name, String... values) {
		return newFunction(name, list(values));
	}

	public static Variable.Date newDate(String name, String... values) {
		return newDate(name, list(values));
	}

	public static Variable.Time newTime(String name, String... values) {
		return newTime(name, list(values));
	}

	@SafeVarargs
	public static <T> List<T> list(T... elements) {
		return new ArrayList<>(Arrays.asList(elements));
	}

	private static Variable fillVariable(Variable variable, String name, List<?> values) {
		variable.name = name;
		variable.values = values;
		return variable;
	}

}
