package tara.compiler.core;

import tara.compiler.codegeneration.FileSystemUtils;
import tara.compiler.codegeneration.Format;
import tara.compiler.codegeneration.magritte.natives.NativeExtractor;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.core.operation.model.ModelOperation;
import tara.compiler.model.Model;
import tara.compiler.model.NodeImpl;
import tara.lang.model.*;
import tara.lang.model.Primitive.MethodReference;
import tara.lang.model.rules.variable.NativeRule;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;
import static tara.lang.model.Tag.Reactive;

class NativeTransformationOperation extends ModelOperation {

	private final File resources;
	private final String outDsl;

	NativeTransformationOperation(CompilationUnit unit) {
		this.resources = unit.getConfiguration().getResourcesDirectory();
		outDsl = (unit.configuration.generatedLanguage() == null ? unit.configuration.getModule() : unit.getConfiguration().generatedLanguage()).toLowerCase();
	}

	@Override
	public void call(Model model) throws CompilationFailedException {
		final List<Parameter> reactiveParameters = findReactiveParameters(model);
		for (Parameter v : reactiveParameters)
			v.substituteValues(new ArrayList<>(singletonList(transformValueToExpression(v, new File(v.file()).getName()))));
		for (Variable v : findReactiveVariables(model))
			v.values(new ArrayList<>(singletonList(transformValueToExpression(v, new File(v.file()).getName()))));
		for (Valued v : findMethodReferences(model))
			v.values(new ArrayList<>(singletonList(transformValueToExpression(v, new File(v.file()).getName()))));
	}

	private Primitive.Expression transformValueToExpression(Valued v, String fileName) {
		return new Primitive.Expression(wrap(v, fileName));
	}

	private String wrap(Valued p, String fileName) {
		Object value = p.values().get(0);
		if (value instanceof MethodReference) return transformMethodReference((NativeRule) p.rule(), (MethodReference) value, fileName);
		if (p.type().equals(Primitive.STRING)) return '"' + value.toString() + '"';
		if (p.type().equals(Primitive.DATE))
			return "tara.magritte.loaders.DateLoader.load(java.util.Collections.singletonList(\"" + value.toString() + '"' + "), self" + ").get(0)";
		if (p.type().equals(Primitive.RESOURCE))
			return "try {\n" +
				"\treturn new java.net.URL(\"" + relativePath(value.toString()) + "\");\n" +
				"} catch (java.net.MalformedURLException e) {\n" +
				"\treturn null;\n" +
				"}";
		if (p.type().equals(Primitive.REFERENCE))
			return value instanceof EmptyNode ? "null" : "self.model().loadInstance(\"" + value.toString() + "\");\n";
		else return value.toString();
	}

	private String transformMethodReference(NativeRule rule, MethodReference value, String fileName) {
		String parameters = namesOf(new NativeExtractor(rule.signature()).parameters());
		return Format.javaValidName().format(outDsl).toString().toLowerCase() + ".natives." + FileSystemUtils.getNameWithoutExtension(fileName) + "." + value.destiny() + "(self" + (parameters.isEmpty() ? "" : ", " + parameters) + ");";
	}

	private String namesOf(String parameters) {
		return Format.nativeParameterWithoutType().format(parameters).toString();
	}

	private String relativePath(String value) {
		return value.substring(resources.getAbsolutePath().length() + 1);
	}

	private List<Parameter> findReactiveParameters(Parametrized parametrized) {
		List<Parameter> parameters = new ArrayList<>();
		parameters.addAll(parametrized.parameters().stream().
			filter(parameter -> parameter.flags().contains(Reactive) && !(parameter.values().get(0) instanceof Primitive.Expression)).
			collect(Collectors.toList()));
		if (parametrized instanceof Node && !((Node) parametrized).isReference()) {
			((Node) parametrized).components().forEach(n -> parameters.addAll(findReactiveParameters(n)));
			((Node) parametrized).facets().forEach(f -> parameters.addAll(findReactiveParameters(f)));
		}
		return parameters;
	}

	private List<Variable> findReactiveVariables(NodeContainer node) {
		List<Variable> parameters = new ArrayList<>();
		for (Node component : node.components()) {
			parameters.addAll(component.variables().stream().
				filter(v -> v.flags().contains(Reactive) && !v.values().isEmpty() && !(v.values().get(0) instanceof Primitive.Expression)).
				collect(Collectors.toList()));
			if (!component.isReference()) parameters.addAll(findReactiveVariables(component));
		}
		if (node instanceof NodeImpl) ((NodeImpl) node).facets().forEach(f -> parameters.addAll(findReactiveVariables(f)));
		return parameters;
	}

	private List<Valued> findMethodReferences(NodeContainer node) {
		List<Valued> valued = new ArrayList<>();
		valued.addAll(node.variables().stream().
			filter(v -> !v.values().isEmpty() && v.values().get(0) instanceof MethodReference).
			collect(Collectors.toList()));
		if (node instanceof Parametrized)
			valued.addAll(((Parametrized) node).parameters().stream().
				filter(v -> !v.values().isEmpty() && v.values().get(0) instanceof MethodReference).collect(Collectors.toList()));
		if (node instanceof NodeImpl) ((NodeImpl) node).facets().forEach(f -> valued.addAll(findMethodReferences(f)));
		if (!(node instanceof Node) || !((Node) node).isReference())
			for (Node component : node.components()) valued.addAll(findMethodReferences(component));
		return valued;
	}

}
