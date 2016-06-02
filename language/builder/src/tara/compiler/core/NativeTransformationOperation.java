package tara.compiler.core;

import org.siani.itrules.Template;
import org.siani.itrules.model.Frame;
import tara.compiler.codegeneration.FileSystemUtils;
import tara.compiler.codegeneration.Format;
import tara.compiler.codegeneration.magritte.natives.NativeExtractor;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.core.operation.model.ModelOperation;
import tara.compiler.model.Model;
import tara.lang.model.*;
import tara.lang.model.Primitive.MethodReference;
import tara.lang.model.rules.variable.NativeRule;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;
import static tara.lang.model.Tag.Reactive;

class NativeTransformationOperation extends ModelOperation {

	private final File resources;
	private final File sources;

	NativeTransformationOperation(CompilationUnit unit) {
		this.resources = unit.getConfiguration().resourcesDirectory();
		this.sources = unit.getConfiguration().sourceDirectory();
	}

	@Override
	public void call(Model model) throws CompilationFailedException {
		for (Parameter v : findReactiveParameters(model))
			v.substituteValues(new ArrayList<>(singletonList(transformValueToExpression(v))));
		for (Variable v : findReactiveVariables(model))
			v.values(new ArrayList<>(singletonList(transformValueToExpression(v))));
		for (Valued v : findMethodReferences(model))
			v.values(new ArrayList<>(singletonList(transformMethodReference(v, new File(v.file()).getName()))));
	}

	private Primitive.Expression transformMethodReference(Valued v, String fileName) {
		return new Primitive.Expression(wrapMethodReference(v, fileName));
	}

	private String wrapMethodReference(Valued v, String fileName) {
		List<Object> value = v.values();
		return transformMethodReference(v.file(), (NativeRule) v.rule(), (MethodReference) value.get(0), fileName);
	}

	private Primitive.Expression transformValueToExpression(Valued v) {
		return new Primitive.Expression(wrap(v));
	}

	private String wrap(Valued v) {
		List<String> result = v.values().stream().map(value -> wrapValue(v, value)).collect(Collectors.toList());
		return v.isMultiple() ? "java.util.Arrays.asList(" + String.join(", ", result) + ")" : result.get(0);
	}

	private String wrapValue(Valued v, Object value) {
		final Template template = ToNativeTransformerTemplate.create().add("url", url -> url.toString().substring(resources.getAbsolutePath().length() + 1));
		final Frame frame = new Frame().addTypes(v.type().name(), "native");
		frame.addFrame("value", value);
		return template.format(frame);
	}

	private String transformMethodReference(String file, NativeRule rule, MethodReference value, String fileName) {
		String parameters = namesOf(new NativeExtractor(rule.signature()).parameters());
		final String packageOf = packageOf(new File(file).getParent());
		return (!packageOf.isEmpty() ? packageOf + "." : "") +
			Format.javaValidName().format(FileSystemUtils.getNameWithoutExtension(fileName)).toString() + "." + value.destiny() + "(self" + (parameters.isEmpty() ? "" : ", " + parameters) + ");";
	}

	private String packageOf(String file) {
		final String replace = file.replace(sources.getAbsolutePath(), "");
		return replace.isEmpty() ? "" : replace.substring(1).replace(File.separator, ".");
	}

	private String namesOf(String parameters) {
		return Format.nativeParameterWithoutType().format(parameters).toString();
	}

	private Set<Parameter> findReactiveParameters(Parametrized parametrized) {
		Set<Parameter> parameters = new HashSet<>();
		parameters.addAll(parametrized.parameters().stream().
			filter(p -> p.flags().contains(Reactive) &&
				!(p.values().get(0) instanceof Primitive.Expression) && !(p.values().get(0) instanceof Primitive.MethodReference)).
			collect(Collectors.toList()));
		if (parametrized instanceof Node && !((Node) parametrized).isReference()) {
			((Node) parametrized).components().forEach(n -> parameters.addAll(findReactiveParameters(n)));
		}
		return parameters;
	}

	private Set<Variable> findReactiveVariables(NodeContainer node) {
		Set<Variable> parameters = new HashSet<>();
		for (Node component : node.components()) {
			parameters.addAll(component.variables().stream().
				filter(v -> v.flags().contains(Reactive) && !v.values().isEmpty() &&
					!(v.values().get(0) instanceof Primitive.Expression) && !(v.values().get(0) instanceof Primitive.MethodReference)).
				collect(Collectors.toList()));
			if (!component.isReference()) parameters.addAll(findReactiveVariables(component));
		}
		return parameters;
	}

	private Set<Valued> findMethodReferences(Node node) {
		Set<Valued> valued = new HashSet<>();
		valued.addAll(node.variables().stream().
			filter(v -> !v.values().isEmpty() && v.values().get(0) instanceof MethodReference).
			collect(Collectors.toList()));
		valued.addAll(node.parameters().stream().
			filter(v -> !v.values().isEmpty() && v.values().get(0) instanceof MethodReference).collect(Collectors.toList()));
		if (!node.isReference())
			for (Node component : node.components()) valued.addAll(findMethodReferences(component));
		return valued;
	}

}
