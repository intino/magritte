package tara.compiler.core;

import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.core.operation.model.ModelOperation;
import tara.compiler.model.Model;
import tara.compiler.model.NodeImpl;
import tara.lang.model.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;
import static tara.lang.model.Tag.Reactive;

class NativeTransformationOperation extends ModelOperation {

	private final File resources;

	NativeTransformationOperation(CompilationUnit unit) {
		this.resources = unit.getConfiguration().getResourcesDirectory();
	}

	@Override
	public void call(Model model) throws CompilationFailedException {
		for (Parameter parameter : findReactiveParameters(model))
			parameter.substituteValues(new ArrayList<>(singletonList(transformValueToExpression(parameter.type(), parameter.values().get(0)))));
		for (Variable v : findReactiveVariables(model))
			v.values(new ArrayList<>(singletonList(transformValueToExpression(v.type(), v.values().get(0)))));
	}

	private Primitive.Expression transformValueToExpression(Primitive type, Object o) {
		return new Primitive.Expression(wrap(type, o));
	}

	private String wrap(Primitive type, Object value) {
		if (type.equals(Primitive.STRING)) return '"' + value.toString() + '"';
		if (type.equals(Primitive.DATE))
			return "tara.magritte.loaders.DateLoader.load(java.util.Collections.singletonList(\"" + value.toString() + '"' + ")).get(0)";
		if (type.equals(Primitive.RESOURCE))
			return "try {\n" +
				"\treturn new java.net.URL(\"" + relativePath(value.toString()) + "\");\n" +
				"} catch (java.net.MalformedURLException e) {\n" +
				"\treturn null;\n" +
				"}";
		if (type.equals(Primitive.REFERENCE))
			return value instanceof EmptyNode ? "null" : "self.model().loadInstance(\"" + value.toString() + "\");\n";
		else return value.toString();
	}

	private String relativePath(String value) {
		return value.substring(resources.getAbsolutePath().length() + 1);
	}

	private List<Parameter> findReactiveParameters(NodeContainer node) {
		List<Parameter> parameters = new ArrayList<>();
		for (Node component : node.components()) {
			parameters.addAll(component.parameters().stream().
				filter(parameter -> parameter.flags().contains(Reactive) && !(parameter.values().get(0) instanceof Primitive.Expression)).
				collect(Collectors.toList()));
			if (!component.isReference()) parameters.addAll(findReactiveParameters(component));
		}
		if (node instanceof NodeImpl) ((NodeImpl) node).facets().forEach(f -> parameters.addAll(findReactiveParameters(f)));
		return parameters;
	}

	private List<Variable> findReactiveVariables(NodeContainer node) {
		List<Variable> parameters = new ArrayList<>();
		for (Node component : node.components()) {
			parameters.addAll(component.variables().stream().
				filter(v -> v.flags().contains(Reactive) && !(v.values().get(0) instanceof Primitive.Expression)).
				collect(Collectors.toList()));
			if (!component.isReference()) parameters.addAll(findReactiveVariables(component));
		}
		if (node instanceof NodeImpl) ((NodeImpl) node).facets().forEach(f -> parameters.addAll(findReactiveVariables(f)));
		return parameters;
	}

}
