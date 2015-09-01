package tara.compiler.codegeneration.magritte.stash;

import tara.compiler.core.errorcollection.TaraException;
import tara.io.Case;
import tara.io.Stash;
import tara.io.Variable;
import tara.language.model.Facet;
import tara.language.model.Node;
import tara.language.model.Parameter;
import tara.language.model.Primitives;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StaticStashCreator {
	private static final String BLOB_KEY = "%";
	private final List<Node> nodes;
	private final List<String> uses;
	private final String language;
	private final File rootFolder;
	private final Set<String> stashPath;

	public StaticStashCreator(List<Node> nodes, List<String> uses, String language, File rootFolder, Set<String> stashPath) {
		this.nodes = nodes;
		this.uses = uses;
		this.language = language;
		this.rootFolder = rootFolder;
		this.stashPath = stashPath;
	}

	public Stash create() throws TaraException {
		List<Case> cases = new ArrayList<>();
		for (Node node : nodes) createCase(cases, node);
		final Stash stash = new Stash();
		stash.language = this.language;
		stash.uses = uses;
		stash.cases = cases;
		return stash;
	}

	private void createCase(List<Case> stashs, Node node) throws TaraException {
		final Case root = new Case();
		fillCase(node, root);
		stashs.add(root);
	}

	private Case fillCase(Node node, Case aCase) throws TaraException {
		if (!node.isAnonymous()) aCase.name = getStash(node) + "#" + node.qualifiedNameCleaned();
		aCase.types = collectTypes(node);
		aCase.variables = collectVariables(node);
		aCase.cases = collectComponents(node.components());
		return aCase;
	}

	private List<Case> collectComponents(List<? extends Node> components) throws TaraException {
		final List<Case> stashes = new ArrayList<>();
		for (Node component : components)
			stashes.add(fillCase(component, new Case()));
		return stashes.isEmpty() ? null : stashes;
	}

	private List<Variable> collectVariables(Node node) throws TaraException {
		List<Variable> variables = createVariables(node.parameters());
		for (Facet facet : node.facets()) {
			variables.addAll(createVariables(facet.parameters()));
		}
		return variables;
	}

	private List<Variable> createVariables(List<Parameter> parameters) throws TaraException {
		List<Variable> variables = new ArrayList<>();
		for (Parameter parameter : parameters)
			createVariable(variables, parameter);
		return variables;
	}

	private void createVariable(List<Variable> variables, Parameter parameter) throws TaraException {
		final Variable variable = new tara.io.Variable();
		variable.n = parameter.name();
		if (parameter.hasReferenceValue()) {
			final Object o = buildReferenceValues(parameter);
			if (o == null)
				throw new TaraException("Error finding file in stash reference: " + Arrays.toString(parameter.values().toArray()));
			variable.v = o;
		} else if (parameter.values().get(0).toString().startsWith("$")) variable.v = buildResourceValue(parameter);
		else variable.v = getValue(parameter);
		variables.add(variable);
	}

	private Object getValue(Parameter parameter) {
		final Primitives.Converter converter = Primitives.getConverter(parameter.inferredType());
		return (parameter.values().get(0) instanceof String && !(Primitives.STRING.equals(parameter.inferredType()))) ?
			Arrays.asList(converter.convert(parameter.values().toArray(new String[parameter.values().size()]))) :
			parameter.values();
	}

	private Object buildResourceValue(Parameter parameter) {
		List<Object> values = parameter.values().stream().
			map(v -> BLOB_KEY + getPresentableName(new File(parameter.file()).getName()) + v.toString()).
			collect(Collectors.toList());
		return values.size() == 1 ? values.get(0) : values.toArray();
	}

	private Object buildReferenceValues(Parameter parameter) {
		return parameter.values().stream().
			map(v -> {
				File file = searchFile(v.toString());
				if (file == null) return null;
				return getPresentableName(file.getPath().substring((rootFolder + File.separator).length())) + "#" + getQn(file, v.toString());
			}).collect(Collectors.toList());
	}

	private File searchFile(String value) {
		final List<String> split = Arrays.asList(value.split("\\."));
		String name;
		for (int i = 0; i < split.size() - 1; i++) {
			name = joinByDot(split.subList(0, i + 1));
			if (stashPath.contains(name)) return fileOf(name);
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

	private static String getPresentableName(String name) {
		return name.substring(0, name.lastIndexOf("."));
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

	private String getStash(Node node) {
		final String stashPath = node.file().replace(rootFolder + File.separator, "");
		return getPresentableName(stashPath).replace(File.separator, ".");
	}
}