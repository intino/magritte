package io.intino.tara.compiler.codegeneration.magritte.natives;

import io.intino.itrules.FrameBuilder;
import io.intino.itrules.Template;
import io.intino.tara.compiler.codegeneration.Format;
import io.intino.tara.compiler.core.CompilerConfiguration;
import io.intino.tara.compiler.model.Model;
import io.intino.tara.compiler.model.NodeImpl;
import io.intino.tara.compiler.model.NodeReference;
import io.intino.tara.lang.model.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.Logger;

import static io.intino.tara.lang.model.Primitive.FUNCTION;
import static java.io.File.separator;
import static java.util.stream.Collectors.toList;

public class NativesCreator {
	private static final Logger LOG = Logger.getLogger(NativesCreator.class.getName());
	private static final String NATIVES = "natives";
	private static String nativeExtension;
	private final String nativesPackage;
	private final Model model;
	private final CompilerConfiguration conf;
	private final File outDirectory;
	private final String outDSL;

	public NativesCreator(Model model, CompilerConfiguration conf) {
		this.model = model;
		this.conf = conf;
		this.outDirectory = conf.getOutDirectory();
		outDSL = (conf.model().outLanguage() != null ? conf.model().outLanguage().toLowerCase() : conf.getModule());
		nativesPackage = conf.workingPackage().toLowerCase().replace(".", separator) + separator + NATIVES + separator;
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
			builder.put(Parameter.class, new NativeParameterAdapter(model.language(), outDSL, conf.model().level(), conf.workingPackage(), conf.model().language().generationPackage(), NativeFormatter.calculatePackage(p.container()), conf.getImportsFile()));
			createNativeFrame(originToDestiny, expressionsTemplate, nativeCodes, calculateDestination(p), builder.append(p), p.type(), p.file());
		});
		return nativeCodes;
	}

	private Map<File, String> createNativeVariableClasses(List<Variable> natives, Map<String, String> files) {
		final Template expressionsTemplate = expressionsTemplate();
		Map<File, String> nativeCodes = new LinkedHashMap<>();
		natives.forEach(variable -> {
			FrameBuilder builder = new FrameBuilder();
			builder.put(Variable.class, new NativeVariableAdapter(model.language(), outDSL, conf.workingPackage(), conf.model().language().generationPackage(), NativeFormatter.calculatePackage(variable.container()), conf.getImportsFile()));
			createNativeFrame(files, expressionsTemplate, nativeCodes, calculateDestination(variable), builder.append(variable), variable.type(), variable.file());
		});
		return nativeCodes;
	}

	private void createNativeFrame(Map<String, String> originToDestiny, Template expressionsTemplate, Map<File, String> nativeCodes, File destination, FrameBuilder append, Primitive type, String file2) {
		final FrameBuilder frameBuilder = append.add(conf.nativeLanguage());
		if (FUNCTION.equals(type)) frameBuilder.add(type.name());
		nativeCodes.put(destination, expressionsTemplate.render(frameBuilder.toFrame()));
		if (!originToDestiny.containsKey(file2)) originToDestiny.put(destination.getAbsolutePath(), file2);
	}

	private Template expressionsTemplate() {
		return Format.customize(new ExpressionsTemplate());
	}

	private File calculateDestination(Parameter parameter) {
		return new File(outDirectory, nativesPackage + NativeFormatter.calculatePackage(parameter.container()).replace(".", separator) + separator + nativeName(parameter));
	}

	private File calculateDestination(Variable variable) {
		return new File(outDirectory, nativesPackage + NativeFormatter.calculatePackage(variable.container()).replace(".", separator) + separator + nativeName(variable));
	}

	private String nativeName(Variable variable) {
		return Format.javaValidName().format(Format.firstUpperCase().format(variable.name())).toString() + "_" + variable.getUID() + nativeExtension;
	}

	private String nativeName(Parameter parameter) {
		return Format.javaValidName().format(Format.firstUpperCase().format(parameter.name())).toString() + "_" + parameter.getUID() + nativeExtension;
	}

	private void writeJavaCode(File file, String nativeText) {
		try {
			file.getParentFile().mkdirs();
			file.createNewFile();
			Files.write(file.toPath(), nativeText.getBytes(conf.sourceEncoding()));
		} catch (IOException e) {
			LOG.severe(e.getMessage());
		}
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
