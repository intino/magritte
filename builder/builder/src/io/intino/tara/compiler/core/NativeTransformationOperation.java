package io.intino.tara.compiler.core;

import io.intino.itrules.FrameBuilder;
import io.intino.itrules.Template;
import io.intino.tara.compiler.codegeneration.FileSystemUtils;
import io.intino.tara.compiler.codegeneration.Format;
import io.intino.tara.compiler.codegeneration.magritte.natives.NativeExtractor;
import io.intino.tara.compiler.core.errorcollection.CompilationFailedException;
import io.intino.tara.compiler.core.operation.model.ModelOperation;
import io.intino.tara.compiler.model.Model;
import io.intino.tara.lang.model.*;
import io.intino.tara.lang.model.Primitive.MethodReference;
import io.intino.tara.lang.model.rules.variable.NativeRule;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static io.intino.tara.lang.model.Tag.Reactive;
import static java.util.Collections.singletonList;

class NativeTransformationOperation extends ModelOperation {

	private final File resources;
	private final List<File> sources;

	NativeTransformationOperation(CompilationUnit unit) {
		this.resources = unit.configuration().resourcesDirectory();
		this.sources = unit.configuration().sourceDirectories();
	}

	@Override
	public void call(Model model) throws CompilationFailedException {
		for (Parameter p : findReactiveParameters(model))
			p.substituteValues(new ArrayList<>(singletonList(transformValueToExpression(p))));
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
		if (v.values().get(0) instanceof EmptyNode) return "null";
		List<String> result = v.values().stream().map(value -> wrapValue(v, value)).collect(Collectors.toList());
		return v.isMultiple() ? "java.util.Arrays.asList(" + String.join(", ", result) + ")" : result.get(0);
	}

	private String wrapValue(Valued v, Object value) {
		final Template template = new ToNativeTransformerTemplate().add("url", url -> url.toString().substring(resources.getAbsolutePath().length() + 1));
		final FrameBuilder builder = new FrameBuilder(v.type().name(), "native");
		String toAdd = value.toString();
		if (value instanceof File) toAdd = ((File) value).getAbsolutePath().replace("\\", "/");
		builder.add("value", toAdd);
		return template.render(builder.toFrame());
	}

	private String transformMethodReference(String file, NativeRule rule, MethodReference value, String fileName) {
		String parameters = rule == null ? "" : namesOf(new NativeExtractor(rule.signature()).parameters());
		final String packageOf = packageOf(new File(file).getParent());
		return (!packageOf.isEmpty() ? packageOf + "." : "") +
				Format.javaValidName().format(FileSystemUtils.getNameWithoutExtension(fileName)).toString() + "." + value.destiny() + "(self" + (parameters.isEmpty() ? "" : ", " + parameters) + ");";
	}

	private String packageOf(String file) {
		final String replace = file.replace(selectSource(file).getAbsolutePath(), "");
		return replace.isEmpty() ? "" : replace.substring(1).replace(File.separator, ".");
	}

	private File selectSource(String file) {
		for (File source : sources) if (file.startsWith(source.getAbsolutePath())) return source;
		return sources.get(0);
	}

	private String namesOf(String parameters) {
		return Format.nativeParameterWithoutType().format(parameters).toString();
	}

	private Set<Parameter> findReactiveParameters(Parametrized parametrized) {
		Set<Parameter> parameters = parametrized.parameters().stream().
				filter(p -> p.flags().contains(Reactive) &&
						!(p.values().get(0) instanceof Primitive.Expression) && !(p.values().get(0) instanceof MethodReference)).collect(Collectors.toSet());
		if (parametrized instanceof Node && !((Node) parametrized).isReference())
			((Node) parametrized).components().forEach(n -> parameters.addAll(findReactiveParameters(n)));
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
