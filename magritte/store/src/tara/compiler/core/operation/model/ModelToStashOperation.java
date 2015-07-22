package tara.compiler.core.operation.model;

import tara.compiler.core.CompilationUnit;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.model.Model;
import tara.io.Entry;
import tara.io.Stash;
import tara.io.Variable;
import tara.language.model.*;
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
	public static final String PASS_KEY = "!";
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
		List<Entry> stashs = new ArrayList<>();
		for (Node node : model.components()) {
			final Entry root = new Entry();
			fillStash(node, root);
			stashs.add(root);
		}
		write(model.file(), stashs);
	}

	private Entry fillStash(Node node, Entry entry) {
		entry.name = node.name();
		entry.types = collectTypes(node);
		entry.variables = collectVariables(node);
		entry.entries = collectComponents(node.components());
		return entry;
	}

	private Entry[] collectComponents(List<? extends Node> components) {
		final List<Entry> stashes = components.stream().map(component -> fillStash(component, new Entry())).collect(Collectors.toList());
		return stashes.isEmpty() ? null : stashes.toArray(new Entry[stashes.size()]);
	}

	private Variable[] collectVariables(Node node) {
		List<Variable> variables = new ArrayList<>();
		for (Parameter parameter : node.parameters())
			createVariable(variables, parameter);
		return variables.toArray(new Variable[variables.size()]);
	}

	private void createVariable(List<Variable> variables, Parameter parameter) {
		final Variable variable = new Variable();
		variable.n = parameter.name();
		if (parameter.hasReferenceValue()) variable.v = buildReferenceValues(parameter);
		else if (parameter.values().get(0).toString().startsWith("$")) variable.v = buildResourceValue(parameter);
		else variable.v = getValue(parameter);
		variables.add(variable);
	}

	private Object getValue(Parameter parameter) {
		final Primitives.Converter converter = Primitives.getConverter(parameter.inferredType());
		final Object[] objects = (parameter.values().get(0) instanceof String && !(Primitives.STRING.equals(parameter.inferredType()))) ?
			converter.convert(parameter.values().toArray(new String[parameter.values().size()])) :
			parameter.values().toArray();
		return objects.length == 1 ? objects[0] : objects;
	}

	private Object buildResourceValue(Parameter parameter) {
		List<Object> values = parameter.values().stream().
			map(v -> BLOB_KEY + getPresentableName(new File(parameter.file()).getName()) + v.toString()).
			collect(Collectors.toList());
		return values.size() == 1 ? values.get(0) : values.toArray();
	}

	private Object buildReferenceValues(Parameter parameter) {
		List<Object> values = parameter.values().stream().
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
		types.add(node.type());
		types.addAll(node.facets().stream().map(Facet::type).collect(Collectors.toList()));
		return types.toArray(new String[types.size()]);
	}

	private void write(String name, List<Entry> content) {
		try {
			final Stash root = new Stash();
			root.entries = content.toArray(new Entry[content.size()]);
			final byte[] bytes = StashSerializer.serialize(root);
			try (FileOutputStream stream = new FileOutputStream(new File(outFolder, name + STASH))) {
				stream.write(bytes);
				stream.close();
			}
		} catch (IOException ignored) {
			ignored.printStackTrace();
		}
	}
}
