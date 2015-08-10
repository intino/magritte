package tara.compiler.dependencyresolution;

import tara.compiler.core.errorcollection.DependencyException;
import tara.compiler.model.Model;
import tara.compiler.model.NodeImpl;
import tara.language.model.*;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Logger;


public class NativeResolver {
	private static final Logger LOG = Logger.getLogger(NativeResolver.class.getName());

	private final Model model;
	private final File nativePath;

	public NativeResolver(Model model, File nativePath) {
		this.model = model;
		this.nativePath = nativePath;
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
			if (Primitives.NATIVE.equalsIgnoreCase(variable.type()))
				variable.contract(NativeResolver.this.updateContract(variable));
	}

	private String updateContract(Variable variable) throws DependencyException {
		final String nativeSignature = findNativeSignature(variable.contract());
		if (nativeSignature.isEmpty())
			throw new DependencyException("reject.native.signature.not.found", variable);
		return variable.contract() + Variable.NATIVE_SEPARATOR + nativeSignature;
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
			LOG.severe("Signature not found: " + name);
			return "";
		}
	}
}
