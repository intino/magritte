package tara.compiler.dependencyresolution;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import tara.compiler.core.errorcollection.DependencyException;
import tara.compiler.model.Model;
import tara.compiler.model.NodeImpl;
import tara.lang.model.Facet;
import tara.lang.model.Node;
import tara.lang.model.Parameter;
import tara.lang.model.Primitive;
import tara.lang.model.rules.variable.NativeRule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class NativeParameterImportResolver {

	private final Model model;
	private final String generatedLanguage;
	private final Map<String, Set<String>> imports;

	public NativeParameterImportResolver(Model model, String generatedLanguage, File importsFile) {
		this.model = model;
		this.generatedLanguage = generatedLanguage;
		this.imports = load(importsFile);
	}

	private Map<String, Set<String>> load(File importsFile) {
		try {
			return new Gson().fromJson(new FileReader(importsFile), new TypeToken<Map<String, Set<String>>>() {
			}.getType());
		} catch (FileNotFoundException e) {
			return new HashMap<>();
		}
	}

	public void resolve() throws DependencyException {
		for (Node node : model.components())
			resolve(node);
	}

	private void resolve(Node node) throws DependencyException {
		if (!(node instanceof NodeImpl)) return;
		resolveNative(node.parameters());
		for (Node include : node.components()) resolve(include);
		resolveInFacets(node.facets());
	}

	private void resolveInFacets(List<? extends Facet> facets) throws DependencyException {
		for (Facet facet : facets) {
			resolveNative(facet.parameters());
			for (Node node : facet.components()) resolve(node);
		}
	}

	private void resolveNative(List<Parameter> valuedList) throws DependencyException {
		for (Parameter valued : valuedList)
			if (valued.rule() instanceof NativeRule || (!valued.values().isEmpty() && valued.values().get(0) instanceof Primitive.Expression))
				fillRule(valued);
	}

	private void fillRule(Parameter valued) throws DependencyException {
		if (valued.rule() == null) valued.rule(new NativeRule("", "", new ArrayList<>(), generatedLanguage));
		fillInfo(valued, (NativeRule) valued.rule());
	}

	private void fillInfo(Parameter valued, NativeRule rule) {
		if (valued != null && valued.type().equals(Primitive.FUNCTION)) {
			rule.imports(collectImports(valued));
		}
	}


	private List<String> collectImports(Parameter valued) {
		final String qn = (valued.container().qualifiedName() + "." + valued.name()).replace(":", "");
		return imports.containsKey(qn) ? new ArrayList<>(imports.get(qn)) : Collections.emptyList();
	}
}
