package io.intino.magritte.builder.compiler.operations;

import io.intino.itrules.Frame;
import io.intino.itrules.FrameBuilder;
import io.intino.itrules.Template;
import io.intino.magritte.builder.compiler.codegeneration.magritte.TemplateTags;
import io.intino.magritte.builder.compiler.codegeneration.magritte.layer.AbstractGraphCreator;
import io.intino.magritte.builder.compiler.codegeneration.magritte.layer.GraphLoaderCreator;
import io.intino.magritte.builder.compiler.codegeneration.magritte.layer.LayerFrameCreator;
import io.intino.magritte.builder.compiler.codegeneration.magritte.layer.LayerTemplate;
import io.intino.magritte.builder.compiler.codegeneration.magritte.layer.templates.GraphTemplate;
import io.intino.magritte.builder.compiler.codegeneration.magritte.natives.NativesCreator;
import io.intino.tara.builder.core.CompilationUnit;
import io.intino.tara.builder.core.CompilerConfiguration;
import io.intino.tara.builder.core.errorcollection.CompilationFailedException;
import io.intino.tara.builder.core.operation.model.ModelOperation;
import io.intino.tara.builder.model.Model;
import io.intino.tara.builder.model.MogramImpl;
import io.intino.tara.builder.utils.Format;
import io.intino.tara.language.model.Mogram;
import io.intino.tara.language.model.Tag;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static io.intino.builder.BuildConstants.PRESENTABLE_MESSAGE;
import static io.intino.tara.builder.utils.Format.firstUpperCase;
import static io.intino.tara.builder.utils.Format.javaValidName;
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
	private final Template template;
	private final Map<String, List<String>> outMap = new LinkedHashMap<>();

	public LayerGenerationOperation(CompilationUnit compilationUnit) {
		super(compilationUnit);
		this.conf = compilationUnit.configuration();
		this.outFolder = conf.getOutDirectory();
		this.srcFolder = conf.sourceDirectories().isEmpty() ? null : conf.sourceDirectories().stream().filter(d -> !d.getName().equals("gen")).findFirst().orElse(conf.sourceDirectories().get(0));
		this.template = Format.customize(new LayerTemplate());
	}

	@Override
	public void call(Model model) {
		try {
			if (conf.isVerbose()) conf.out().println(prefix() + " Cleaning Old Layers...");
			if (!conf.model().level().equals(CompilerConfiguration.Level.Model)) cleanOldLayers(model);
			if (conf.isVerbose()) conf.out().println(prefix() + " Generating Layers...");
			createClasses(model);
			registerOutputs(writeNativeClasses(model));
			unit.addOutputItems(outMap);
//			unit.compilationDifferentialCache().saveCache(model.components().stream().map(c -> ((MogramImpl) c).getHashCode()).collect(Collectors.toList()));
		} catch (Throwable e) {
			LOG.log(java.util.logging.Level.SEVERE, "Error during java className generation: " + e.getMessage(), e);
			throw new CompilationFailedException(unit.getPhase(), unit, e);
		}
	}

	private Map<String, String> writeNativeClasses(Model model) {
		return new NativesCreator(model, conf).create();
	}

	private void createClasses(Model model) {
		if (!model.level().equals(CompilerConfiguration.Level.Model)) {
			final Map<String, Map<String, String>> layers = createLayerClasses(model);
			layers.values().forEach(this::writeLayers);
			writeAbstractGraph(model, layers);
			writeGraph(createGraph());
		} else writeGraphLoader(model);
	}

	private void writeGraphLoader(Model model) {
		File target = new File(new File(outFolder, conf.workingPackage().toLowerCase().replace(".", File.separator)), GRAPH + "Loader" + JAVA);
		write(target, new GraphLoaderCreator(model.language(), conf).create(model));
		for (List<String> paths : outMap.values()) paths.add(target.getAbsolutePath());
	}

	private void writeAbstractGraph(Model model, Map<String, Map<String, String>> layers) {
		AbstractGraphCreator abstractGraphCreator = new AbstractGraphCreator(model.language(), conf);
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
		builder.add(OUT_LANGUAGE, conf.model().outDsl());
		builder.add(WORKING_PACKAGE, conf.workingPackage());
		return Format.customize(new GraphTemplate()).render(builder.toFrame());
	}

	private Map<String, Map<String, String>> createLayerClasses(Model model) {
		Map<String, Map<String, String>> map = new HashMap<>();
		for (Mogram node : model.components()) {
			if (node.is(Tag.Instance) || !((MogramImpl) node).isDirty() || ((MogramImpl) node).isVirtual()) continue;
			renderNode(map, node);
		}
		return map;
	}

	private void renderNode(Map<String, Map<String, String>> map, Mogram node) {
		Map.Entry<String, Frame> layerFrame = new LayerFrameCreator(conf, node.languageName()).create(node);
		if (!map.containsKey(node.file())) map.put(node.file(), new LinkedHashMap<>());
		String destination = destination(layerFrame);
		boolean modified = true; //isModified(node);
		map.get(node.file()).put(destination, !modified && new File(destination).exists() ? "" : render(layerFrame));
		renderFrame(map, node, layerFrame);
	}

//	private boolean isModified(Mogram node) {
//		return unit.compilationDifferentialCache().isModified((MogramImpl) node);
//	}

	private void renderFrame(Map<String, Map<String, String>> map, Mogram node, Map.Entry<String, Frame> layerFrame) {
		if (node.is(Tag.Decorable)) {
			Map.Entry<String, Frame> frame = new LayerFrameCreator(conf, node.languageName()).createDecorable(node);
			File file = new File(srcTarget(frame));
			if (file.exists() && node.isAbstract()) checkAbstractDecorable(file);
			map.get(node.file()).put(file.getAbsolutePath(), file.exists() ? "" : render(frame));
		} else removeDecorable(layerFrame.getKey(), node.name());
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
		File target = new File(new File(outFolder, conf.workingPackage().replace(".", File.separator)), "Abstract" + GRAPH + JAVA);
		target.getParentFile().mkdirs();
		return write(target, text) ? target.getAbsolutePath() : null;
	}

	private void writeGraph(String text) {
		File target = new File(new File(srcFolder, conf.workingPackage().toLowerCase().replace(".", File.separator)), Format.firstUpperCase().format(javaValidName().format(conf.model().outDsl())) + GRAPH + JAVA);
		if (!target.exists()) write(target, text);
	}

	private void cleanOldLayers(Model model) {
		final String workingPackage = conf.workingPackage() == null ? conf.getModule() : conf.workingPackage();
		File out = new File(conf.getOutDirectory(), workingPackage.toLowerCase());
		List<File> layers = filterOld(collectAllLayers(out), out, model);
		layers.forEach(File::delete);
	}

	private List<File> filterOld(List<File> files, File base, Model model) {
		List<File> current = calculateCurrentLayers(base, model);
		return files.stream().filter(layer -> !current.contains(layer) && !isUnderSource(layer)).collect(Collectors.toList());
	}

	private List<File> calculateCurrentLayers(File base, Model model) {
		return model.components().stream().filter(n -> !n.is(Tag.Instance) && !n.isAnonymous()).map(node -> new File(calculateLayerPath(node, base) + JAVA)).collect(Collectors.toList());
	}

	private String calculateLayerPath(Mogram node, File workingPackage) {
		return workingPackage.getPath() + File.separator + javaValidName().format(node.name()).toString();
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
		return template.render(layerFrame.getValue());
	}

	private String prefix() {
		return PRESENTABLE_MESSAGE + "[" + conf.getModule() + " - " + conf.model().outDsl() + "]";
	}
}