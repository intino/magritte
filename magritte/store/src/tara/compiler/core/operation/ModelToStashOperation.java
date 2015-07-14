package tara.compiler.core.operation;

import tara.compiler.core.CompilationUnit;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.core.operation.model.ModelOperation;
import tara.compiler.model.Facet;
import tara.compiler.model.Node;
import tara.compiler.model.Parameter;
import tara.compiler.model.impl.Model;
import tara.compiler.model.impl.Stash;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ModelToStashOperation extends ModelOperation {
	private static final String STASH = ".stash";
	public static final String PASS_KEY = "!";
	private static final String BLOB_KEY = "%";
	private final Map<String, File> fileMap;
	private File outFolder;
	private final String storeDirectory;

	public ModelToStashOperation(CompilationUnit compilationUnit) {
		super();
		outFolder = compilationUnit.getConfiguration().getOutDirectory();
		fileMap = compilationUnit.getConfiguration().getClassPath();
		storeDirectory = compilationUnit.getConfiguration().getStoreDirectory();
	}

	@Override
	public void call(Model model) throws CompilationFailedException {
		List<Stash> stashs = new ArrayList<>();
		for (Node node : model.getIncludedNodes()) {
			final Stash root = new Stash();
			fillStash(node, root);
			stashs.add(root);
		}
		write(model.getFile(), stashs);
	}

	private Stash fillStash(Node node, Stash root) {
		root.name = node.getName();
		root.types = collectTypes(node);
		root.variables = collectVariables(node);
		root.components = collectComponents(node.getIncludedNodes());
		return root;
	}

	private Stash[] collectComponents(List<Node> components) {
		final List<Stash> stashes = components.stream().map(component -> fillStash(component, new Stash())).collect(Collectors.toList());
		return stashes.toArray(new Stash[stashes.size()]);
	}

	private Stash.Variable[] collectVariables(Node node) {
		List<Stash.Variable> variables = new ArrayList<>();
		for (Parameter parameter : node.getParameters())
			createVariable(variables, parameter);
		return variables.toArray(new Stash.Variable[variables.size()]);
	}

	private void createVariable(List<Stash.Variable> variables, Parameter parameter) {
		final Stash.Variable v = new Stash.Variable();
		v.name = parameter.getName();
		if (parameter.hasReferenceValue()) v.values = buildReferenceValues(parameter);
		else if (parameter.getValues().get(0).toString().startsWith("$")) v.values = buildResourceValue(parameter);
		else v.values = parameter.getValues().toArray();
		variables.add(v);
	}

	private Object[] buildResourceValue(Parameter parameter) {
		List<Object> values = parameter.getValues().stream().
			map(v -> BLOB_KEY + getPresentableName(new File(parameter.getFile()).getName()) + v.toString()).
			collect(Collectors.toList());
		return values.toArray();
	}

	private Object[] buildReferenceValues(Parameter parameter) {
		List<Object> values = parameter.getValues().stream().
			map(v -> {
				File file = searchFile(v.toString());
				if (file == null) return null; //TODO Throw an exception
				return PASS_KEY + file.getPath().replaceFirst(storeDirectory + File.separator, "") + "#" + getQn(file, v.toString());
			}).collect(Collectors.toList());
		return values.toArray();
	}

	private File searchFile(String value) {
		final List<String> split = Arrays.asList(value.split("\\."));
		String name;
		for (int i = 0; i < split.size() - 1; i++) {
			name = joinByDot(split.subList(0, i + 1));
			if (fileMap.containsKey(name)) return fileMap.get(name);
		}
		return null;
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
		types.add(node.getType());
		types.addAll(node.getFacets().stream().map(Facet::getFacetType).collect(Collectors.toList()));
		return types.toArray(new String[types.size()]);
	}

	private void write(String name, List<Stash> content) {
		try {
			final Stash root = new Stash();
			root.components = content.toArray(new Stash[content.size()]);
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
