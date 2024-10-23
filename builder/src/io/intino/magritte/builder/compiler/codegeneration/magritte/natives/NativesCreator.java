package io.intino.magritte.builder.compiler.codegeneration.magritte.natives;

import io.intino.builder.CompilerConfiguration;
import io.intino.itrules.Engine;
import io.intino.itrules.FrameBuilder;
import io.intino.magritte.builder.compiler.codegeneration.magritte.Generator;
import io.intino.tara.Language;
import io.intino.tara.builder.utils.Format;
import io.intino.tara.model.Mogram;
import io.intino.tara.model.Primitive;
import io.intino.tara.model.Property;
import io.intino.tara.model.PropertyDescription;
import io.intino.tara.processors.model.Model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.Logger;

import static io.intino.tara.builder.utils.Format.javaValidName;
import static io.intino.tara.model.Annotation.Reactive;
import static io.intino.tara.model.Primitive.FUNCTION;
import static java.io.File.separator;

public class NativesCreator {
	private static final Logger LOG = Logger.getLogger(NativesCreator.class.getName());
	private static final String NATIVES = "natives";
	private static final String nativeExtension = ".java";
	private final String nativesPackage;
	private final Model model;
	private final Language language;
	private final CompilerConfiguration conf;
	private final File genDirectory;
	private final String outDSL;

	public NativesCreator(Model model, Language language, CompilerConfiguration conf) {
		this.model = model;
		this.language = language;
		this.conf = conf;
		this.genDirectory = conf.genDirectory();
		this.outDSL = (conf.dsl().outDsl() != null ? conf.dsl().outDsl().toLowerCase() : conf.module());
		this.nativesPackage = conf.generationPackage().toLowerCase().replace(".", separator) + separator + NATIVES + separator;
	}

	public Map<String, String> create() {
		List<PropertyDescription> parameters = new ArrayList<>();
		List<Property> properties = new ArrayList<>();
		for (Mogram mogram : model.mograms()) {
			extractNativeParameters(mogram, parameters);
			extractNativeProperties(mogram, properties);
		}
		return createNativeClasses(parameters, properties);
	}

	private Map<String, String> createNativeClasses(List<PropertyDescription> parameters, List<Property> props) {
		Map<String, String> destination2Origin = new HashMap<>();
		Map<File, String> nativeCodes = new HashMap<>();
		if (parameters.isEmpty() && props.isEmpty()) return destination2Origin;
		if (!parameters.isEmpty()) nativeCodes.putAll(createNativeParameterClasses(parameters, destination2Origin));
		if (!props.isEmpty()) nativeCodes.putAll(createNativePropertyClasses(props, destination2Origin));
		for (Map.Entry<File, String> nativeCode : nativeCodes.entrySet())
			writeJavaCode(nativeCode.getKey(), nativeCode.getValue());
		return destination2Origin;
	}

	private Map<File, String> createNativeParameterClasses(List<PropertyDescription> parameters, Map<String, String> originToDestiny) {
		final Engine expressionsTemplate = expressionsTemplate();
		Map<File, String> nativeCodes = new LinkedHashMap<>();
		parameters.forEach(p -> {
			FrameBuilder builder = new FrameBuilder();
			builder.put(PropertyDescription.class, new NativeParameterAdapter(language, outDSL, p.container().level(), conf.generationPackage(), conf.dsl().generationPackage(), NativeFormatter.calculatePackage(p.container()), conf.intinoProjectDirectory()));
			createNativeFrame(originToDestiny, expressionsTemplate, nativeCodes, calculateDestination(p), builder.append(p), p.type(), p.source().getPath());
		});
		return nativeCodes;
	}

	private Map<File, String> createNativePropertyClasses(List<Property> natives, Map<String, String> files) {
		final Engine expressionsTemplate = expressionsTemplate();
		Map<File, String> nativeCodes = new LinkedHashMap<>();
		natives.forEach(prop -> {
			FrameBuilder builder = new FrameBuilder();
			builder.put(Property.class, new NativePropertyAdapter(language, outDSL, conf.generationPackage(), conf.dsl().generationPackage(), NativeFormatter.calculatePackage(prop.container()), conf.intinoProjectDirectory()));
			createNativeFrame(files, expressionsTemplate, nativeCodes, calculateDestination(prop), builder.append(prop), prop.type(), prop.source().getPath());
		});
		return nativeCodes;
	}

	private void createNativeFrame(Map<String, String> destination2Origin, Engine engine, Map<File, String> nativeCodes, File destination, FrameBuilder builder, Primitive type, String file2) {
		final FrameBuilder frameBuilder = builder.add("java");
		if (FUNCTION.equals(type)) frameBuilder.add(type.name());
		nativeCodes.put(destination, engine.render(frameBuilder.toFrame()));
		if (!destination2Origin.containsKey(file2)) destination2Origin.put(destination.getAbsolutePath(), file2);
	}

	private Engine expressionsTemplate() {
		return Generator.customize(new ExpressionsTemplate());
	}

	private File calculateDestination(PropertyDescription parameter) {
		return new File(genDirectory, nativesPackage + NativeFormatter.calculatePackage(parameter.container()).replace(".", separator) + separator + nativeName(parameter));
	}

	private File calculateDestination(Property prop) {
		return new File(genDirectory, nativesPackage + NativeFormatter.calculatePackage(prop.container()).replace(".", separator) + separator + nativeName(prop));
	}

	private String nativeName(Property property) {
		return javaValidName().format(Format.firstUpperCase().format(property.name())).toString() + "_" + property.getUID() + nativeExtension;
	}

	private String nativeName(PropertyDescription parameter) {
		return javaValidName().format(Format.firstUpperCase().format(parameter.name())).toString() + "_" + parameter.getUID() + nativeExtension;
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

	private void extractNativeParameters(Mogram mogram, List<PropertyDescription> natives) {
		natives.addAll(mogram.parameters().stream().
				filter(p -> FUNCTION.equals(p.type()) || isExpression(p)).toList());
		for (Mogram inner : mogram.mograms())
			extractNativeParameters(inner, natives);
	}

	private void extractNativeProperties(Mogram mogram, List<Property> natives) {
		natives.addAll(mogram.properties().stream()
				.filter(v -> (FUNCTION.equals(v.type()) || isExpression(v)) && !v.values().isEmpty())
				.toList());
		for (Mogram inner : mogram.mograms()) extractNativeProperties(inner, natives);
	}

	private boolean isExpression(Property valued) {
		return !valued.values().isEmpty() && valued.values().get(0) instanceof Primitive.Expression || valued.annotations().contains(Reactive);
	}

	private boolean isExpression(PropertyDescription parameter) {
		return !parameter.values().isEmpty() && parameter.values().get(0) instanceof Primitive.Expression || parameter.definition().annotations().contains(Reactive);
	}
}