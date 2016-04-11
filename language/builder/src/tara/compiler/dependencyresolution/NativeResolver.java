package tara.compiler.dependencyresolution;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import tara.compiler.core.errorcollection.DependencyException;
import tara.compiler.model.Model;
import tara.compiler.model.NodeImpl;
import tara.lang.model.*;
import tara.lang.model.rules.variable.NativeReferenceRule;
import tara.lang.model.rules.variable.NativeRule;
import tara.lang.model.rules.variable.ReferenceRule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class NativeResolver {
	private static final Logger LOG = Logger.getLogger(NativeResolver.class.getName());

	private final Model model;
	private final File nativePath;
	private final String generatedLanguage;

	public NativeResolver(Model model, File nativePath, String generatedLanguage) {
		this.model = model;
		this.nativePath = nativePath;
		this.generatedLanguage = generatedLanguage;
	}

	public void resolve() throws DependencyException {
		for (Node node : model.components())
			resolve(node);
	}

	private void resolve(Node node) throws DependencyException {
		if (!(node instanceof NodeImpl)) return;
		resolveNative(node.variables());
		resolveNative(node.parameters());
		for (Node include : node.components()) resolve(include);
		resolveInFacets(node.facets());
	}

	private void resolveInFacets(List<? extends Facet> facets) throws DependencyException {
		for (Facet facet : facets) {
			resolveNative(facet.variables());
			resolveNative(facet.parameters());
			for (Node node : facet.components()) resolve(node);
		}
	}

	private void resolveNative(List<? extends Valued> valuedList) throws DependencyException {
		for (Valued valued : valuedList)
			if (valued.rule() instanceof NativeRule || (!valued.values().isEmpty() && valued.values().get(0) instanceof Primitive.Expression) || valued.flags().contains(Tag.Reactive))
				fillRule(valued);
	}

	private void fillRule(Valued valued) throws DependencyException {
		if (valued.rule() == null) valued.rule(new NativeRule("", "", new ArrayList<>()));
		else if (valued.rule() instanceof ReferenceRule)
			valued.rule(new NativeReferenceRule(((ReferenceRule) valued.rule()).allowedReferences()));
		fillInfo(valued, (NativeRule) valued.rule());
	}

	private void fillInfo(Valued valued, NativeRule rule) throws DependencyException {
		if (valued instanceof Variable && valued.type().equals(Primitive.FUNCTION)) {
			fillVariableInfo((Variable) valued, rule);
		}
	}

	private void fillVariableInfo(Variable variable, NativeRule rule) throws DependencyException {
		if (nativePath == null || !nativePath.exists()) throw new DependencyException("reject.nonexisting.functions.directory", null);
		File[] files = nativePath.listFiles((dir, filename) -> filename.endsWith(".java") && filename.substring(0, filename.lastIndexOf(".")).equalsIgnoreCase(rule.interfaceClass()));
		if (files.length == 0) throw new DependencyException("reject.nonexisting.variable.rule", variable);
		final String text = readFile(files[0]);
		final String signature = getSignature(text);
		if (signature.isEmpty()) throw new DependencyException("reject.native.signature.not.found", variable);
		else rule.signature(signature);
		rule.imports(getInterfaceImports(Arrays.asList(text.split("\n"))));
	}

	private String getSignature(String text) {
		text = text.substring(text.indexOf("{") + 1, text.indexOf(";", text.indexOf("{") + 1)).trim();
		if (!text.startsWith("public")) text = "public " + text;
		return text;
	}

	private List<String> getInterfaceImports(List<String> text) {
		return new ArrayList<>(text.stream().filter(line -> line.trim().startsWith("import ")).map(String::trim).collect(Collectors.toSet()));
	}

	private String readFile(File file) {
		try {
			return new String(Files.readAllBytes(file.toPath()));
		} catch (IOException e) {
			LOG.severe("File cannot be read: " + file.getAbsolutePath());
			return "";
		}
	}

	private Map<String, Set<String>> load(File importsFile) {
		try {
			return new Gson().fromJson(new FileReader(importsFile), new TypeToken<Map<String, Set<String>>>() {
			}.getType());
		} catch (FileNotFoundException e) {
			return new HashMap<>();
		}
	}
}
