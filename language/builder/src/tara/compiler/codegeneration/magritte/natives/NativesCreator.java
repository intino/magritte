package tara.compiler.codegeneration.magritte.natives;

import org.siani.itrules.Template;
import org.siani.itrules.engine.FrameBuilder;
import org.siani.itrules.model.Frame;
import tara.compiler.codegeneration.Format;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.model.Model;
import tara.compiler.model.NodeReference;
import tara.lang.model.*;
import tara.templates.ExpressionsTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.io.File.separator;
import static tara.lang.model.Primitive.FUNCTION;

public class NativesCreator {

	private static final Logger LOG = Logger.getLogger(NativesCreator.class.getName());

	private static String nativeExtension;
	private static final String NATIVES = "natives";
	private static final String JAVA_VALID_NAME = "javaValidName";
	private final String nativesPackage;
	private final Model model;
	private final CompilerConfiguration conf;
	private final File outDirectory;
	private final String generatedLanguage;

	public NativesCreator(Model model, CompilerConfiguration conf) {
		this.model = model;
		this.conf = conf;
		this.outDirectory = conf.getOutDirectory();
		generatedLanguage = (conf.generatedLanguage() != null ? conf.generatedLanguage().toLowerCase() : conf.getModule());
		nativesPackage = Format.javaValidName().format(generatedLanguage.toLowerCase()).toString().toLowerCase() + separator + NATIVES + separator;
		nativeExtension = "." + (conf.nativeLanguage().equalsIgnoreCase("kotlin") ? "kt" : conf.nativeLanguage().toLowerCase());
	}

	public Map<String, String> create() {
		List<Parameter> parameters = new ArrayList<>();
		List<Variable> variables = new ArrayList<>();
		extractNativeParameters(model, parameters);
		extractNativeVariables(model, variables);
		return createNativeClasses(parameters, variables);
	}

	private Map<String, String> createNativeClasses(List<Parameter> parameters, List<Variable> variables) {
		Map<String, String> destinyToOrigin = new HashMap<>();
		Map<File, String> nativeCodes = new HashMap<>();
		if (parameters.isEmpty() && variables.isEmpty()) return destinyToOrigin;
		if (!parameters.isEmpty()) nativeCodes.putAll(createNativeParameterClasses(parameters, destinyToOrigin));
		if (!variables.isEmpty()) nativeCodes.putAll(createNativeVariableClasses(variables, destinyToOrigin));
		for (Map.Entry<File, String> nativeCode : nativeCodes.entrySet())
			writeJavaCode(nativeCode.getKey(), nativeCode.getValue());
		return destinyToOrigin;
	}

	private Map<File, String> createNativeParameterClasses(List<Parameter> natives, Map<String, String> originToDestiny) {
		final Template expressionsTemplate = ExpressionsTemplate.create().add(JAVA_VALID_NAME, Format.javaValidName());
		Map<File, String> nativeCodes = new LinkedHashMap<>();
		natives.forEach(n -> {
			FrameBuilder builder = new FrameBuilder();
			builder.register(Parameter.class, new NativeParameterAdapter(generatedLanguage, conf.getLanguage(), conf.level(), NativeFormatter.calculatePackage(n.container()), conf.getImportsFile()));
			final File destiny = calculateDestiny(n);
			nativeCodes.put(destiny, expressionsTemplate.format(((Frame) builder.build(n)).addTypes(n.type().equals(FUNCTION) ? n.type().name() : Tag.Native.name(), conf.nativeLanguage())));
			if (!originToDestiny.containsKey(n.file())) originToDestiny.put(destiny.getAbsolutePath(), n.file());
		});
		return nativeCodes;
	}

	private Map<File, String> createNativeVariableClasses(List<Variable> natives, Map<String, String> files) {
		final Template expressionsTemplate = ExpressionsTemplate.create().add(JAVA_VALID_NAME, Format.javaValidName());
		Map<File, String> nativeCodes = new LinkedHashMap<>();
		natives.forEach(variable -> {
			FrameBuilder builder = new FrameBuilder();
			builder.register(Variable.class, new NativeVariableAdapter(conf.getLanguage(), generatedLanguage, NativeFormatter.calculatePackage(variable.container()), conf.getImportsFile()));
			final File destiny = calculateDestiny(variable);
			nativeCodes.put(destiny, expressionsTemplate.format(((Frame) builder.build(variable)).addTypes(variable.type().equals(FUNCTION) ? variable.type().name() : Tag.Native.name(), conf.nativeLanguage())));
			if (!files.containsKey(variable.file())) files.put(destiny.getAbsolutePath(), variable.file());
		});
		return nativeCodes;
	}

	private File calculateDestiny(Parameter parameter) {
		return new File(outDirectory, nativesPackage + NativeFormatter.calculatePackage(parameter.container()).replace(".", File.separator) + separator + nativeName(parameter));
	}

	private File calculateDestiny(Variable variable) {
		return new File(outDirectory, nativesPackage + NativeFormatter.calculatePackage(variable.container()).replace(".", File.separator) + separator + nativeName(variable));
	}

	private String nativeName(Variable variable) {
		return Format.javaValidName().format(variable.name()).toString() + "_" + variable.getUID() + nativeExtension;
	}

	private String nativeName(Parameter parameter) {
		return Format.javaValidName().format(parameter.name()).toString() + "_" + parameter.getUID() + nativeExtension;
	}

	private File writeJavaCode(File file, String nativeText) {
		try {
			file.getParentFile().mkdirs();
			file.createNewFile();
			Files.write(file.toPath(), nativeText.getBytes());
		} catch (IOException e) {
			LOG.severe(e.getMessage());
		}
		return file;
	}

	private void extractNativeParameters(NodeContainer node, List<Parameter> natives) {
		if (node instanceof NodeReference) return;
		if (node instanceof Parametrized)
			natives.addAll(((Parametrized) node).parameters().stream().
				filter(p -> (FUNCTION.equals(p.type()) || isExpression(p))).
				collect(Collectors.toList()));
		for (Node component : node.components())
			extractNativeParameters(component, natives);
		if (node instanceof Node) {
			for (Facet facet : ((Node) node).facets()) extractNativeParameters(facet, natives);
		}
	}

	private void extractNativeVariables(NodeContainer node, List<Variable> natives) {
		if (node instanceof NodeReference) return;
		natives.addAll(node.variables().stream().
			filter(v -> (FUNCTION.equals(v.type()) || isExpression(v)) && !v.values().isEmpty() && !v.isInherited()).
			collect(Collectors.toList()));
		for (Node component : node.components()) extractNativeVariables(component, natives);
		if (node instanceof Node) for (Facet facet : ((Node) node).facets()) extractNativeVariables(facet, natives);
	}

	private boolean isExpression(Variable variable) {
		return !variable.values().isEmpty() && variable.values().get(0) instanceof Primitive.Expression;
	}

	private boolean isExpression(Parameter parameter) {
		return !parameter.values().isEmpty() && parameter.values().get(0) instanceof Primitive.Expression || parameter.flags().contains(Tag.Native);
	}
}
