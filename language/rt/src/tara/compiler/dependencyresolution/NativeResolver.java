package tara.compiler.dependencyresolution;

import tara.compiler.core.errorcollection.DependencyException;
import tara.compiler.model.impl.Model;
import tara.compiler.model.*;
import tara.compiler.model.impl.NodeImpl;
import tara.semantic.model.Primitives;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import static tara.compiler.model.Variable.NATIVE_SEPARATOR;

public class NativeResolver {
	private final Model model;
	private final File nativePath;

	public NativeResolver(Model model, File nativePath) {
		this.model = model;
		this.nativePath = nativePath;
	}

	public void resolve() throws DependencyException {
		for (Node node : model.getIncludedNodes())
			resolve(node);
	}

	private void resolve(Node node) throws DependencyException {
		if (!(node instanceof NodeImpl)) return;
		resolveNative(node.getVariables());
		for (Node include : node.getIncludedNodes()) resolve(include);
		resolveInFacetTargets(node.getFacetTargets());
		resolveInFacets(node.getFacets());
	}

	private void resolveInFacetTargets(List<FacetTarget> facetTargets) throws DependencyException {
		for (FacetTarget facet : facetTargets) {
			resolveNative(facet.getVariables());
			for (Node node : facet.getIncludedNodes()) resolve(node);
		}
	}

	private void resolveInFacets(List<Facet> facets) throws DependencyException {
		for (Facet facet : facets) {
			resolveNative(facet.getVariables());
			for (Node node : facet.getIncludedNodes()) resolve(node);
		}
	}

	private void resolveNative(List<Variable> variables) throws DependencyException {
		for (Variable variable : variables)
			if (Primitives.NATIVE.equalsIgnoreCase(variable.getType()))
				variable.setContract(NativeResolver.this.updateContract(variable));
	}

	private String updateContract(Variable variable) throws DependencyException {
		final String nativeSignature = findNativeSignature(variable.getContract());
		if (nativeSignature.isEmpty())
			throw new DependencyException("reject.native.signature.not.found", variable);
		return variable.getContract() + NATIVE_SEPARATOR + nativeSignature;
	}

	private String findNativeSignature(String name) {
		if (nativePath == null || !nativePath.exists()) return "";
		File[] files = nativePath.listFiles((dir, filename) ->
			filename.endsWith(".java") && filename.substring(0, filename.lastIndexOf(".")).equalsIgnoreCase(name));
		if (files.length == 0) return "";
		try {
			String text = new String(Files.readAllBytes(files[0].toPath()));
			text = text.substring(text.indexOf("{") + 1, text.indexOf(";", text.indexOf("{") + 1)).trim();
			if (!text.startsWith("public")) text = "public " + text;
			return text;
		} catch (Exception e) {
			return "";
		}
	}
}
