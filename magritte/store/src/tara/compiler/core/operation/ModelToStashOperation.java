package tara.compiler.core.operation;

import tara.Entry;
import tara.Stash;
import tara.Var;
import tara.compiler.core.CompilationUnit;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.core.operation.model.ModelOperation;
import tara.compiler.model.Facet;
import tara.compiler.model.Node;
import tara.compiler.model.Parameter;
import tara.compiler.model.impl.Model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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
		for (Node node : model.getIncludedNodes()) {
			final Entry root = new Entry();
			fillStash(node, root);
			stashs.add(root);
		}
		write(model.getFile(), stashs);
	}

	private Entry fillStash(Node node, Entry root) {
		root.name = node.getName();
		root.types = collectTypes(node);
		root.vars = collectVariables(node);
		root.entries = collectComponents(node.getIncludedNodes());
		return root;
	}

	private Entry[] collectComponents(List<Node> components) {
		final List<Entry> stashes = components.stream().map(component -> fillStash(component, new Entry())).collect(Collectors.toList());
		return stashes.toArray(new Entry[stashes.size()]);
	}

	private Var[] collectVariables(Node node) {
		List<Var> variables = new ArrayList<>();
		for (Parameter parameter : node.getParameters())
			createVariable(variables, parameter);
		return variables.toArray(new Var[variables.size()]);
	}

	private void createVariable(List<Var> variables, Parameter parameter) {
		final Var var = new Var();
		var.n = parameter.getName();
		if (parameter.getName().equals("instant")) var.v = asDate(parameter.getValues().get(0).toString());
		else if (parameter.hasReferenceValue()) var.v = buildReferenceValues(parameter);
		else if (parameter.getValues().get(0).toString().startsWith("$")) var.v = buildResourceValue(parameter);
		else var.v = parameter.getValues().size() == 1 ? parameter.getValues().get(0) : parameter.getValues().toArray();
		variables.add(var);
	}

	private long asDate(String date) {
		LocalDateTime time = LocalDateTime.from(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").parse(date));
		return time.toInstant(ZoneOffset.UTC).toEpochMilli();
	}

	private Object buildResourceValue(Parameter parameter) {
		List<Object> values = parameter.getValues().stream().
			map(v -> BLOB_KEY + getPresentableName(new File(parameter.getFile()).getName()) + v.toString()).
			collect(Collectors.toList());
		return values.size() == 1 ? values.get(0) : values.toArray();
	}

	private Object buildReferenceValues(Parameter parameter) {
		List<Object> values = parameter.getValues().stream().
			map(v -> {
				File file = searchFile(v.toString());
				if (file == null) return null; //TODO Throw an exception
				return PASS_KEY + file.getPath().replaceFirst(rootFolder + File.separator, "") + "#" + getQn(file, v.toString());
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
		types.add(node.getType());
		types.addAll(node.getFacets().stream().map(Facet::getFacetType).collect(Collectors.toList()));
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
