package tara.compiler.dependencyresolution;

import tara.compiler.core.errorcollection.DependencyException;
import tara.compiler.model.Model;
import tara.compiler.model.NodeImpl;
import tara.lang.model.*;
import tara.lang.model.rules.variable.NativeRule;

import java.io.File;
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
		for (Node include : node.components()) resolve(include);
		resolveInFacetTargets(node.facetTargets());
		resolveInFacets(node.facets());
	}

	private void resolveInFacetTargets(List<? extends FacetTarget> facetTargets) throws DependencyException {
		for (FacetTarget facet : facetTargets) {
			resolveNative(facet.variables());
			for (Node node : facet.components()) resolve(node);
		}
	}

	private void resolveInFacets(List<? extends Facet> facets) throws DependencyException {
		for (Facet facet : facets) {
			resolveNative(facet.variables());
			for (Node node : facet.components()) resolve(node);
		}
	}

	private void resolveNative(List<? extends Variable> variables) throws DependencyException {
		for (Variable variable : variables)
			if (Primitive.FUNCTION.equals(variable.type())) fillRule(variable, (NativeRule) variable.rule());
	}

	private void fillRule(Variable variable, NativeRule rule) throws DependencyException {
		rule.language(generatedLanguage);
		fillInfo(variable, rule);
	}

	private void fillInfo(Variable variable, NativeRule rule) throws DependencyException {
		if (nativePath == null || !nativePath.exists()) throw new DependencyException("reject.nonexisting.variable.rule", variable);
		File[] files = nativePath.listFiles((dir, filename) -> filename.endsWith(".java") && filename.substring(0, filename.lastIndexOf(".")).equalsIgnoreCase(rule.interfaceClass()));
		if (files.length == 0) throw new DependencyException("reject.nonexisting.variable.rule", variable);
		final String text = readFile(files[0]);
		final String signature = getSignature(text);
		if (signature.isEmpty()) throw new DependencyException("reject.native.signature.not.found", variable);
		else rule.signature(signature);
		final Set<String> imports = getImports(Arrays.asList(text.split("\n")));
		imports.addAll(getImplementationImports(text));
		rule.imports(new ArrayList<>(imports));
	}

	private String getSignature(String text) {
		text = text.substring(text.indexOf("{") + 1, text.indexOf(";", text.indexOf("{") + 1)).trim();
		if (!text.startsWith("public")) text = "public " + text;
		return text;
	}

	private Set<String> getImports(List<String> text) {
		return text.stream().filter(line -> line.trim().startsWith("import ")).map(String::trim).collect(Collectors.toSet());
	}

	private List<String> getImplementationImports(String file) {
		if (!file.contains("/**")) return Collections.emptyList();
		final String substring = file.substring(file.indexOf("/**"), file.indexOf("*/"));
		final List<String> imports = Arrays.asList(substring.split("\n"));
		return imports.stream().filter(line -> line.contains("import ")).map(line -> line.trim().startsWith("*") ? line.trim().substring(1).trim() : line.trim()).collect(Collectors.toList());
	}

	private String readFile(File file) {
		try {
			return new String(Files.readAllBytes(file.toPath()));
		} catch (IOException e) {
			LOG.severe("File cannot be read: " + file.getAbsolutePath());
			return "";
		}
	}
}
