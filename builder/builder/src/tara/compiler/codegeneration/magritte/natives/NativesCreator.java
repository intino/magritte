package tara.compiler.codegeneration.magritte.natives;

import org.siani.itrules.Template;
import org.siani.itrules.engine.FrameBuilder;
import org.siani.itrules.model.Frame;
import tara.compiler.codegeneration.Format;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.model.Model;
import tara.compiler.model.NodeImpl;
import tara.compiler.model.NodeReference;
import tara.lang.model.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.Logger;

import static java.io.File.separator;
import static java.util.stream.Collectors.toList;
import static tara.compiler.codegeneration.magritte.natives.NativeFormatter.calculatePackage;
import static tara.lang.model.Primitive.FUNCTION;

public class NativesCreator {

	private static final Logger LOG = Logger.getLogger(NativesCreator.class.getName());

	private static String nativeExtension;
	private static final String NATIVES = "natives";
	private final String nativesPackage;
	private final Model model;
	private final CompilerConfiguration conf;
	private final File outDirectory;
	private final String generatedLanguage;

	public NativesCreator(Model model, CompilerConfiguration conf) {
		this.model = model;
		this.conf = conf;
		this.outDirectory = conf.getOutDirectory();
		generatedLanguage = (conf.outDsl() != null ? conf.outDsl().toLowerCase() : conf.getModule());
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

	private Map<File, String> createNativeParameterClasses(List<Parameter> parameters, Map<String, String> originToDestiny) {
		final Template expressionsTemplate = expressionsTemplate();
		Map<File, String> nativeCodes = new LinkedHashMap<>();
		parameters.forEach(p -> {
			FrameBuilder builder = new FrameBuilder();
			builder.register(Parameter.class, new NativeParameterAdapter(generatedLanguage, conf.language(), conf.moduleType(), calculatePackage(p.container()), conf.getImportsFile()));
			final File destiny = calculateDestiny(p);
			final Frame frame = ((Frame) builder.build(p)).addTypes(conf.nativeLanguage());
			if (FUNCTION.equals(p.type())) frame.addTypes(p.type().name());
			nativeCodes.put(destiny, expressionsTemplate.format(frame));
			if (!originToDestiny.containsKey(p.file())) originToDestiny.put(destiny.getAbsolutePath(), p.file());
		});
		return nativeCodes;
	}

	private Map<File, String> createNativeVariableClasses(List<Variable> natives, Map<String, String> files) {
		final Template expressionsTemplate = expressionsTemplate();
		Map<File, String> nativeCodes = new LinkedHashMap<>();
		natives.forEach(variable -> {
			FrameBuilder builder = new FrameBuilder();
			builder.register(Variable.class, new NativeVariableAdapter(conf.language(), generatedLanguage, calculatePackage(variable.container()), conf.getImportsFile()));
			final File destiny = calculateDestiny(variable);
			final Frame frame = ((Frame) builder.build(variable)).addTypes(conf.nativeLanguage());
			if (FUNCTION.equals(variable.type())) frame.addTypes(variable.type().name());
			nativeCodes.put(destiny, expressionsTemplate.format(frame));
			if (!files.containsKey(variable.file())) files.put(destiny.getAbsolutePath(), variable.file());
		});
		return nativeCodes;
	}

	private Template expressionsTemplate() {
		return Format.customize(ExpressionsTemplate.create());
	}

	private File calculateDestiny(Parameter parameter) {
		return new File(outDirectory, nativesPackage + calculatePackage(parameter.container()).replace(".", separator) + separator + nativeName(parameter));
	}

	private File calculateDestiny(Variable variable) {
		return new File(outDirectory, nativesPackage + calculatePackage(variable.container()).replace(".", separator) + separator + nativeName(variable));
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

	private void extractNativeParameters(Node node, List<Parameter> natives) {
		if (node instanceof NodeReference || (node instanceof NodeImpl && node.container() instanceof NodeRoot && !((NodeImpl) node).isDirty()))
			return;
		natives.addAll(node.parameters().stream().
			filter(p -> FUNCTION.equals(p.type()) || isExpression(p)).collect(toList()));
		for (Node component : node.components())
			extractNativeParameters(component, natives);
	}

	private void extractNativeVariables(Node node, List<Variable> natives) {
		if (node instanceof NodeReference || (node instanceof NodeImpl && node.container() instanceof NodeRoot && !((NodeImpl) node).isDirty()))
			return;
		natives.addAll(node.variables().stream().
			filter(v -> (FUNCTION.equals(v.type()) || isExpression(v)) && !v.values().isEmpty() && !v.isInherited()).collect(toList()));
		for (Node component : node.components()) extractNativeVariables(component, natives);
	}

	private boolean isExpression(Variable valued) {
		return !valued.values().isEmpty() && valued.values().get(0) instanceof Primitive.Expression || valued.flags().contains(Tag.Reactive);
	}

	private boolean isExpression(Parameter parameter) {
		return !parameter.values().isEmpty() && parameter.values().get(0) instanceof Primitive.Expression || parameter.flags().contains(Tag.Reactive);
	}
}
