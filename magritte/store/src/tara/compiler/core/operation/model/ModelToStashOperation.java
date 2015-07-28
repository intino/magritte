package tara.compiler.core.operation.model;

import tara.compiler.core.CompilationUnit;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.model.Model;
import tara.io.Case;
import tara.io.Stash;
import tara.io.StashSerializer;
import tara.io.Variable;
import tara.language.model.Facet;
import tara.language.model.Node;
import tara.language.model.Parameter;
import tara.language.model.Primitives;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ModelToStashOperation extends ModelOperation {
	private static final String STASH = ".stash";
	private static final String BLOB_KEY = "%";
	private final String rootFolder;
	private final File outFolder;
	private final Set<String> classPath;

	public ModelToStashOperation(CompilationUnit compilationUnit) {
		super();
		rootFolder = compilationUnit.getConfiguration().getRootFolder();
		outFolder = compilationUnit.getConfiguration().getOutFolder();
		classPath = compilationUnit.getConfiguration().getClassPath();
	}

	@Override
	public void call(Model model) throws CompilationFailedException {
		List<Case> stashs = new ArrayList<>();
		for (Node node : model.components()) {
			final Case root = new Case();
			fillCase(node, root);
			stashs.add(root);
		}
		write(model, stashs);
	}

	private Case fillCase(Node node, Case aCase) {
		if (!node.isAnonymous()) aCase.name = getStash(node) + "#" + cleanQn(node.qualifiedName());
		aCase.types = collectTypes(node);
		aCase.variables = collectVariables(node);
		aCase.cases = collectComponents(node.components());
		return aCase;
	}

	private List<Case> collectComponents(List<? extends Node> components) {
		final List<Case> stashes = components.stream().map(component -> fillCase(component, new Case())).collect(Collectors.toList());
		return stashes.isEmpty() ? null : stashes;
	}

	private Variable[] collectVariables(Node node) {
		List<Variable> variables = createVariables(node.parameters());
		for (Facet facet : node.facets()) {
			variables.addAll(createVariables(facet.parameters()));
		}
		return variables.toArray(new Variable[variables.size()]);
	}

	private List<Variable> createVariables(List<Parameter> parameters) {
		List<Variable> variables = new ArrayList<>();
		for (Parameter parameter : parameters)
			createVariable(variables, parameter);
		return variables;
	}

	private void createVariable(List<Variable> variables, Parameter parameter) {
		final Variable variable = new tara.io.Variable();
		variable.n = parameter.name();
		if (parameter.hasReferenceValue()) variable.v = buildReferenceValues(parameter);
		else if (parameter.values().get(0).toString().startsWith("$")) variable.v = buildResourceValue(parameter);
		else variable.v = getValue(parameter);
		variables.add(variable);
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

	private java.lang.Object buildReferenceValues(Parameter parameter) {
		List<java.lang.Object> values = parameter.values().stream().
			map(v -> {
				File file = searchFile(v.toString());
				if (file == null) return null; //TODO Throw an exception
				return getPresentableName(file.getPath().substring((rootFolder + File.separator).length())) + "#" + getQn(file, v.toString());
			}).collect(Collectors.toList());
		return values.size() == 1 ? values.get(0) : values.toArray();
	}

	private File searchFile(String value) {
		final List<String> split = Arrays.asList(value.split("\\."));
		String name;
		for (int i = 0; i < split.size() - 1; i++) {
			name = joinByDot(split.subList(0, i + 1));
			if (classPath.contains(name)) return fileOf(name);
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


	private String[] collectTypes(Node node) {
		List<String> types = new ArrayList<>();
		if (node.parentName() != null) types.add(withDollar(node.parent().qualifiedName()));
		types.add(withDollar(node.type()));
		final Set<String> facetTypes = node.facets().stream().map(Facet::type).collect(Collectors.toSet());
		types.addAll(withDollar(facetTypes.stream().map(type -> type + "_" + node.type()).collect(Collectors.toList())));
		return types.toArray(new String[types.size()]);
	}

	private List<String> withDollar(List<String> names) {
		return names.stream().map(name -> name.replace(".", "$")).collect(Collectors.toList());
	}

	private String withDollar(String name) {
		return name.replace(".", "$");
	}


	private void write(Model model, List<Case> content) {
		try {
			final Stash stash = new Stash();
			stash.language = model.language();
			stash.cases = content;
			final byte[] bytes = StashSerializer.serialize(stash);
			try (FileOutputStream stream = new FileOutputStream(new File(outFolder, model.file() + STASH))) {
				stream.write(bytes);
				stream.close();
			}
		} catch (IOException ignored) {
			ignored.printStackTrace();
		}
	}

	private String getStash(Node node) {
		final String stashPath = node.file().replace(rootFolder + File.separator, "");
		return getPresentableName(stashPath).replace(File.separator, ".");
	}

	public static String cleanQn(String qualifiedName) {
		return qualifiedName.replace(Node.ANNONYMOUS, "").replace("[", "").replace("]", "");
	}

}
