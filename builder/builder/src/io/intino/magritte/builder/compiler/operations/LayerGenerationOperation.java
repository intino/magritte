package io.intino.magritte.builder.compiler.operations;

import io.intino.itrules.Frame;
import io.intino.itrules.FrameBuilder;
import io.intino.itrules.Template;
import io.intino.magritte.builder.compiler.codegeneration.magritte.TemplateTags;
import io.intino.magritte.builder.compiler.codegeneration.magritte.layer.AbstractGraphCreator;
import io.intino.magritte.builder.compiler.codegeneration.magritte.layer.LayerFrameCreator;
import io.intino.magritte.builder.compiler.codegeneration.magritte.layer.LayerTemplate;
import io.intino.magritte.builder.compiler.codegeneration.magritte.layer.templates.GraphTemplate;
import io.intino.magritte.builder.compiler.codegeneration.magritte.natives.NativesCreator;
import io.intino.magritte.builder.core.CompilationUnit;
import io.intino.magritte.builder.core.CompilerConfiguration;
import io.intino.magritte.builder.core.errorcollection.CompilationFailedException;
import io.intino.magritte.builder.core.operation.model.ModelOperation;
import io.intino.magritte.builder.model.Model;
import io.intino.magritte.builder.model.NodeImpl;
import io.intino.magritte.builder.utils.Format;
import io.intino.magritte.lang.model.Node;
import io.intino.magritte.lang.model.Tag;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static io.intino.Configuration.Artifact.Model.Level.Solution;
import static io.intino.magritte.builder.compiler.shared.TaraBuildConstants.PRESENTABLE_MESSAGE;
import static io.intino.magritte.builder.utils.Format.firstUpperCase;
import static io.intino.magritte.builder.utils.Format.javaValidName;
import static java.io.File.separator;
import static java.util.Objects.requireNonNull;

public class LayerGenerationOperation extends ModelOperation implements TemplateTags {
	private static final Logger LOG = Logger.getGlobal();
	private static final String DOT = ".";
	private static final String JAVA = ".java";
	private static final String GRAPH = "Graph";

	private final CompilationUnit compilationUnit;
	private final CompilerConfiguration conf;
	private final File srcFolder;
	private final File outFolder;
	private final Template template;
	private final Map<String, List<String>> outMap = new LinkedHashMap<>();

	public LayerGenerationOperation(CompilationUnit compilationUnit) {
		this.compilationUnit = compilationUnit;
		this.conf = compilationUnit.configuration();
		this.outFolder = conf.getOutDirectory();
		this.srcFolder = conf.sourceDirectories().isEmpty() ? null : conf.sourceDirectories().get(0);
		this.template = Format.customize(new LayerTemplate());
	}

	@Override
	public void call(Model model) {
		try {
			if (conf.isVerbose()) conf.out().println(prefix() + " Cleaning Old Layers...");
			if (!conf.model().level().equals(Solution)) cleanOldLayers(model);
			if (conf.isVerbose()) conf.out().println(prefix() + " Generating Layers...");
			if (!model.level().equals(Solution)) createLayers(model);
			registerOutputs(writeNativeClasses(model));
			compilationUnit.addOutputItems(outMap);
			compilationUnit.compilationDifferentialCache().saveCache(model.components().stream().map(c -> ((NodeImpl) c).getHashCode()).collect(Collectors.toList()));
		} catch (Throwable e) {
			LOG.log(java.util.logging.Level.SEVERE, "Error during java className generation: " + e.getMessage(), e);
			throw new CompilationFailedException(compilationUnit.getPhase(), compilationUnit, e);
		}
	}

	private Map<String, String> writeNativeClasses(Model model) {
		return new NativesCreator(model, conf).create();
	}

	private void createLayers(Model model) {
		final Map<String, Map<String, String>> layers = createLayerClasses(model);
		layers.values().forEach(this::writeLayers);
		registerOutputs(layers, writeAbstractGraph(new AbstractGraphCreator(model.language(), conf.model().outDsl(), conf.model().level(), conf.workingPackage(), conf.model().language().generationPackage()).create(model)));
		writeGraph(createGraph());
	}

	private void registerOutputs(Map<String, Map<String, String>> layers, String modelPath) {
		fillLayerInOutMap(layers);
		for (List<String> paths : outMap.values()) paths.add(modelPath);
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
		for (Node node : model.components()) {
			if (node.is(Tag.Instance) || !((NodeImpl) node).isDirty() || ((NodeImpl) node).isVirtual()) continue;
			renderNode(map, model, node);
		}
		return map;
	}

	private void renderNode(Map<String, Map<String, String>> map, Model model, Node node) {
		Map.Entry<String, Frame> layerFrame = new LayerFrameCreator(conf, node.languageName(), model).create(node);
		if (!map.containsKey(node.file())) map.put(node.file(), new LinkedHashMap<>());
		String destination = destination(layerFrame);
		map.get(node.file()).put(destination, !isModified(node) && new File(destination).exists() ? "" : render(layerFrame));
		renderFrame(map, node, model, layerFrame);
	}

	private boolean isModified(Node node) {
		return compilationUnit.compilationDifferentialCache().isModified((NodeImpl) node);
	}

	private void renderFrame(Map<String, Map<String, String>> map, Node node, Model model, Map.Entry<String, Frame> layerFrame) {
		if (node.is(Tag.Decorable)) {
			Map.Entry<String, Frame> frame = new LayerFrameCreator(conf, node.languageName(), model).createDecorable(node);
			File file = new File(srcDestiny(frame));
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

	private String srcDestiny(Map.Entry<String, Frame> layerFrameMap) {
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
		File destiny = new File(new File(outFolder, conf.workingPackage().replace(".", File.separator)), "Abstract" + GRAPH + JAVA);
		destiny.getParentFile().mkdirs();
		return write(destiny, text) ? destiny.getAbsolutePath() : null;
	}

	private void writeGraph(String text) {
		File destiny = new File(new File(conf.srcDirectory(), conf.workingPackage().toLowerCase().replace(".", File.separator)), Format.firstUpperCase().format(javaValidName().format(conf.model().outDsl())) + GRAPH + JAVA);
		if (!destiny.exists()) write(destiny, text);
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

	private String calculateLayerPath(Node node, File workingPackage) {
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
			BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
			fileWriter.write(text);
			fileWriter.close();
			return true;
		} catch (IOException e) {
			LOG.log(java.util.logging.Level.SEVERE, e.getMessage(), e);
		}
		return false;
	}

	private String render(Map.Entry<String, Frame> layerFrame) {
		return template.render(layerFrame.getValue());
	}

	private String prefix() {
		return PRESENTABLE_MESSAGE + "[" + conf.getModule() + " - " + conf.model().outDsl() + "]";
	}
}