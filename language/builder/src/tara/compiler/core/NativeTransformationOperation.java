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

public class NativeTransformationOperation extends ModelOperation {

	private final File resources;

	public NativeTransformationOperation(CompilationUnit unit) {
		this.resources = unit.getConfiguration().getResourcesDirectory();
	}

	@Override
	public void call(Model model) throws CompilationFailedException {
		for (Parameter parameter : findNativeParameters(model))
			parameter.substituteValues(new ArrayList<>(singletonList(transformValueToExpression(parameter.type(), parameter.values().get(0)))));
	}

	private Primitive.Expression transformValueToExpression(Primitive type, Object o) {
		return new Primitive.Expression(wrap(type, o.toString()));
	}

	private String wrap(Primitive type, String value) {
		if (type.equals(Primitive.STRING)) return '"' + value + '"';
		if (type.equals(Primitive.DATE))
			return "tara.magritte.loaders.DateLoader.load(java.util.Collections.singletonList(\"" + value + '"' + ")).get(0)";
		if (type.equals(Primitive.RESOURCE))
			return "try {\n" +
				"\treturn new java.net.URL(\"" + relativePath(value) + "\");\n" +
				"} catch (java.net.MalformedURLException e) {\n" +
				"\treturn null;\n" +
				"}";
		else return value;
	}

	private String relativePath(String value) {
		return value.substring(resources.getAbsolutePath().length() + 1);
	}

	private List<Parameter> findNativeParameters(NodeContainer node) {
		List<Parameter> parameters = new ArrayList<>();
		for (Node component : node.components()) {
			parameters.addAll(component.parameters().stream().
				filter(parameter -> parameter.flags().contains(Tag.Native.name()) && !(parameter.values().get(0) instanceof Primitive.Expression)).
				collect(Collectors.toList()));
			parameters.addAll(findNativeParameters(component));
		}
		if (node instanceof NodeImpl) ((NodeImpl) node).facets().forEach(f -> parameters.addAll(findNativeParameters(f)));
		return parameters;
	}

}
