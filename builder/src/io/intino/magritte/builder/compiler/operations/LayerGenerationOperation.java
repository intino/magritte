package io.intino.magritte.builder.compiler.operations;

import io.intino.builder.CompilerConfiguration;
import io.intino.itrules.Engine;
import io.intino.itrules.Frame;
import io.intino.itrules.FrameBuilder;
import io.intino.magritte.builder.compiler.codegeneration.magritte.Generator;
import io.intino.magritte.builder.compiler.codegeneration.magritte.TemplateTags;
import io.intino.magritte.builder.compiler.codegeneration.magritte.layer.AbstractGraphCreator;
import io.intino.magritte.builder.compiler.codegeneration.magritte.layer.GraphLoaderCreator;
import io.intino.magritte.builder.compiler.codegeneration.magritte.layer.LayerFrameCreator;
import io.intino.magritte.builder.compiler.codegeneration.magritte.layer.LayerTemplate;
import io.intino.magritte.builder.compiler.codegeneration.magritte.layer.templates.GraphTemplate;
import io.intino.magritte.builder.compiler.codegeneration.magritte.natives.NativesCreator;
import io.intino.tara.builder.core.CompilationUnit;
import io.intino.tara.builder.core.errorcollection.CompilationFailedException;
import io.intino.tara.builder.core.operation.model.ModelOperation;
import io.intino.tara.builder.utils.Format;
import io.intino.tara.model.Annotation;
import io.intino.tara.model.Mogram;
import io.intino.tara.processors.model.Model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static io.intino.builder.BuildConstants.PRESENTABLE_MESSAGE;
import static io.intino.tara.builder.utils.Format.firstUpperCase;
import static io.intino.tara.builder.utils.Format.javaValidName;
import static io.intino.tara.model.Annotation.Generalization;
import static io.intino.tara.model.Level.M1;
import static java.io.File.separator;
import static java.util.Objects.requireNonNull;

@SuppressWarnings("resource")
public class LayerGenerationOperation extends ModelOperation implements TemplateTags {
	private static final Logger LOG = Logger.getGlobal();
	private static final String DOT = ".";
	private static final String JAVA = ".java";
	private static final String GRAPH = "Graph";

	private final CompilerConfiguration conf;
	private final File srcFolder;
	private final File outFolder;
	private final Engine engine;
	private final Map<String, List<String>> outMap = new LinkedHashMap<>();

	public LayerGenerationOperation(CompilationUnit compilationUnit) {
		super(compilationUnit);
		this.conf = compilationUnit.configuration();
		this.outFolder = conf.genDirectory();
		this.srcFolder = conf.srcDirectory();
		this.engine = Generator.customize(new LayerTemplate());
	}

	@Override
	public void call(Model model) {
		try {
			if (conf.isVerbose()) conf.out().println(prefix() + " Cleaning Old Layers...");
			if (model.mograms().stream().anyMatch(m -> m.level() != M1)) cleanOldLayers(model);
			if (conf.isVerbose()) conf.out().println(prefix() + " Generating Layers...");
			createClasses(model);
			registerOutputs(writeNativeClasses(model));
			unit.addOutputItems(outMap);
		} catch (Throwable e) {
			LOG.log(java.util.logging.Level.SEVERE, "Error during java className generation: " + e.getMessage(), e);
			throw new CompilationFailedException(unit.getPhase(), unit, e);
		}
	}

	private Map<String, String> writeNativeClasses(Model model) {
		return new NativesCreator(model, unit.language(), conf).create();
	}

	private void createClasses(Model model) {
		if (model.mograms().stream().anyMatch(m -> m.level() != M1)) {
			final Map<String, Map<String, String>> layers = createLayerClasses(model);
			layers.values().forEach(this::writeLayers);
			writeAbstractGraph(model, layers);
			writeGraph(createGraph());
		} else writeGraphLoader(model);
	}

	private void writeGraphLoader(Model model) {
		File target = new File(new File(outFolder, conf.generationPackage().toLowerCase().replace(".", File.separator)), GRAPH + "Loader" + JAVA);
		write(target, new GraphLoaderCreator(unit.language(), conf).create(model));
		if (outMap.isEmpty()) unit.getSourceUnits().forEach((s, v) -> put(s.getPath(), target.getAbsolutePath()));
		else for (List<String> paths : outMap.values()) paths.add(target.getAbsolutePath());
	}

	private void writeAbstractGraph(Model model, Map<String, Map<String, String>> layers) {
		AbstractGraphCreator abstractGraphCreator = new AbstractGraphCreator(unit.language(), conf);
		registerOutputs(layers, writeAbstractGraph(abstractGraphCreator.create(model)));
	}

	private void registerOutputs(Map<String, Map<String, String>> layers, String abstractGraphPath) {
		fillLayerInOutMap(layers);
		for (List<String> paths : outMap.values()) paths.add(abstractGraphPath);
	}

	private void registerOutputs(Map<String, String> nativeOuts) {
		for (Map.Entry<String, String> src : nativeOuts.entrySet()) {
			if (!outMap.containsKey(src.getValue())) outMap.put(src.getValue(), new ArrayList<>());
			outMap.get(src.getValue()).add(src.getKey());
		}
	}

	private void fillLayerInOutMap(Map<String, Map<String, String>> map) {
		for (Map.Entry<String, Map<String, String>> entry : map.entrySet())
			for (String out : entry.getValue().keySet()) if (!isUnderSource(new File(out))) put(entry.getKey(), out);
	}

	private void put(String key, String value) {
		if (!outMap.containsKey(key)) outMap.put(key, new ArrayList<>());
		outMap.get(key).add(value);
	}

	private String createGraph() {
		FrameBuilder builder = new FrameBuilder("wrapper");
		builder.add(OUT_LANGUAGE, conf.dsl().outDsl());
		builder.add(WORKING_PACKAGE, conf.generationPackage());
		return Generator.customize(new GraphTemplate()).render(builder.toFrame());
	}

	private Map<String, Map<String, String>> createLayerClasses(Model model) {
		Map<String, Map<String, String>> map = new HashMap<>();
		model.components().stream()
				.filter(mogram -> mogram.level() != M1)
				.forEach(mogram -> renderMogram(map, mogram));
		return map;
	}

	private void renderMogram(Map<String, Map<String, String>> map, Mogram mogram) {
		Map.Entry<String, Frame> layerFrame = new LayerFrameCreator(conf, unit.language()).create(mogram);
		if (!map.containsKey(mogram.source().getPath())) map.put(mogram.source().getPath(), new LinkedHashMap<>());
		String destination = destination(layerFrame);
		map.get(mogram.source().getPath()).put(destination, new File(destination).exists() ? "" : render(layerFrame));
		renderFrame(map, mogram, layerFrame);
	}

	private void renderFrame(Map<String, Map<String, String>> map, Mogram mogram, Map.Entry<String, Frame> layerFrame) {
		if (mogram.is(Annotation.Decorable)) {
			Map.Entry<String, Frame> frame = new LayerFrameCreator(conf, unit.language()).createDecorable(mogram);
			File file = new File(srcTarget(frame));
			if (file.exists() && mogram.is(Generalization)) checkAbstractDecorable(file);
			map.get(mogram.source().getPath()).put(file.getAbsolutePath(), file.exists() ? "" : render(frame));
		} else removeDecorable(layerFrame.getKey(), mogram.name());
	}

	private void checkAbstractDecorable(File file) {
		try {
			String text = Files.readString(file.toPath());
			if (text.contains("public class")) {
				text = text.replaceFirst("public class", "public abstract class");
				Files.writeString(file.toPath(), text);
			}
		} catch (IOException e) {
			LOG.log(java.util.logging.Level.SEVERE, e.getMessage(), e);
		}
	}

	private void removeDecorable(String key, String name) {
		final File parentFile = new File(outFolder, key.replace(DOT, separator) + JAVA).getParentFile();
		final File file = new File(parentFile, firstUpperCase().format(javaValidName().format(ABSTRACT + name)).toString() + JAVA);
		if (file.exists()) file.delete();
	}

	private String destination(Map.Entry<String, Frame> layerFrameMap) {
		return new File(outFolder, layerFrameMap.getKey().replace(DOT, separator) + JAVA).getAbsolutePath();
	}

	private String srcTarget(Map.Entry<String, Frame> layerFrameMap) {
		return new File(srcFolder, layerFrameMap.getKey().replace(DOT, separator) + JAVA).getAbsolutePath();
	}

	private void writeLayers(Map<String, String> layersMap) {
		for (Map.Entry<String, String> entry : layersMap.entrySet()) {
			File file = new File(entry.getKey());
			if (entry.getValue().isEmpty() || isUnderSource(file) && file.exists()) continue;
			file.getParentFile().mkdirs();
			write(file, entry.getValue());
		}
	}

	private boolean isUnderSource(File file) {
		return file.getAbsolutePath().startsWith(srcFolder.getAbsolutePath());
	}

	private String writeAbstractGraph(String text) {
		File target = new File(new File(outFolder, conf.generationPackage().replace(".", File.separator)), "Abstract" + GRAPH + JAVA);
		target.getParentFile().mkdirs();
		return write(target, text) ? target.getAbsolutePath() : null;
	}

	private void writeGraph(String text) {
		File target = new File(new File(srcFolder, conf.generationPackage().toLowerCase().replace(".", File.separator)), Format.firstUpperCase().format(javaValidName().format(conf.dsl().outDsl())) + GRAPH + JAVA);
		if (!target.exists()) write(target, text);
	}

	private void cleanOldLayers(Model model) {
		final String generationPackage = conf.generationPackage() == null ? conf.module() : conf.generationPackage();
		File out = new File(conf.genDirectory(), generationPackage.toLowerCase());
		List<File> layers = filterOld(collectAllLayers(out), out, model);
		layers.forEach(File::delete);
	}

	private List<File> filterOld(List<File> files, File base, Model model) {
		List<File> current = calculateCurrentLayers(base, model);
		return files.stream().filter(layer -> !current.contains(layer) && !isUnderSource(layer))
				.collect(Collectors.toList());
	}

	private List<File> calculateCurrentLayers(File base, Model model) {
		return model.components().stream()
				.filter(m -> m.level() != M1 && !m.isAnonymous())
				.map(m -> new File(calculateLayerPath(m, base) + JAVA))
				.collect(Collectors.toList());
	}

	private String calculateLayerPath(Mogram mogram, File generationPackage) {
		return generationPackage.getPath() + File.separator + javaValidName().format(mogram.name()).toString();
	}

	private List<File> collectAllLayers(File out) {
		List<File> list = new ArrayList<>();
		if (!out.isDirectory() && !out.getName().equals(GRAPH + JAVA)) list.add(out);
		else if (!out.isDirectory())
			for (File file : requireNonNull(out.listFiles(f -> !"natives".equals(f.getName()))))
				list.addAll(collectAllLayers(file));
		return list;
	}

	private boolean write(File file, String text) {
		try {
			file.getParentFile().mkdirs();
			Files.writeString(file.toPath(), text);
			return true;
		} catch (IOException e) {
			LOG.log(java.util.logging.Level.SEVERE, e.getMessage(), e);
			return false;
		}
	}

	private String render(Map.Entry<String, Frame> layerFrame) {
		return engine.render(layerFrame.getValue());
	}

	private String prefix() {
		return PRESENTABLE_MESSAGE + "[" + conf.module() + " - " + conf.dsl().outDsl() + "]";
	}
}