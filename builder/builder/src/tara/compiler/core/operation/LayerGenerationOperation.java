package tara.compiler.core.operation;

import org.siani.itrules.Template;
import org.siani.itrules.model.Frame;
import tara.compiler.codegeneration.Format;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.codegeneration.magritte.layer.DynamicTemplate;
import tara.compiler.codegeneration.magritte.layer.GraphWrapperCreator;
import tara.compiler.codegeneration.magritte.layer.LayerFrameCreator;
import tara.compiler.codegeneration.magritte.layer.LayerTemplate;
import tara.compiler.codegeneration.magritte.layer.templates.LevelTemplate;
import tara.compiler.codegeneration.magritte.natives.NativesCreator;
import tara.compiler.core.CompilationUnit;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.core.errorcollection.TaraException;
import tara.compiler.core.operation.model.ModelOperation;
import tara.compiler.model.Model;
import tara.compiler.model.NodeImpl;
import tara.compiler.shared.Configuration.Level;
import tara.lang.model.FacetTarget;
import tara.lang.model.Node;
import tara.lang.model.Tag;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.io.File.separator;
import static java.lang.System.out;
import static tara.compiler.codegeneration.Format.customize;
import static tara.compiler.codegeneration.magritte.NameFormatter.facetLayerPackage;
import static tara.compiler.shared.TaraBuildConstants.PRESENTABLE_MESSAGE;

public class LayerGenerationOperation extends ModelOperation implements TemplateTags {
	private static final Logger LOG = Logger.getGlobal();
	private static final String DOT = ".";
	private static final String JAVA = ".java";
	private static final String WRAPPER = "GraphWrapper";

	private final CompilationUnit compilationUnit;
	private final CompilerConfiguration conf;
	private Template template;
	private File outFolder;
	private Map<String, List<String>> outMap = new LinkedHashMap<>();

	public LayerGenerationOperation(CompilationUnit compilationUnit) {
		super();
		this.compilationUnit = compilationUnit;
		this.conf = compilationUnit.getConfiguration();
		this.outFolder = conf.getOutDirectory();
	}

	@Override
	public void call(Model model) {
		this.template = customize(conf.isPersistent() ? DynamicTemplate.create() : LayerTemplate.create());
		try {
			if (conf.isVerbose())
				out.println(PRESENTABLE_MESSAGE + "[" + conf.getModule() + " - " + conf.outDSL() + "] Cleaning Old Layers...");
			if (!conf.level().equals(Level.System)) cleanOldLayers(model);
			if (conf.isVerbose())
				out.println(PRESENTABLE_MESSAGE + "[" + conf.getModule() + " - " + conf.outDSL() + "] Generating Layers...");
			if (!model.level().equals(Level.System)) createLayers(model);
			registerOutputs(writeNativeClasses(model));
			compilationUnit.addOutputItems(outMap);
		} catch (TaraException e) {
			LOG.log(java.util.logging.Level.SEVERE, "Error during java className generation: " + e.getMessage(), e);
			throw new CompilationFailedException(compilationUnit.getPhase(), compilationUnit, e);
		}
	}

	private Map<String, String> writeNativeClasses(Model model) {
		return new NativesCreator(model, conf).create();
	}

	private void createLayers(Model model) throws TaraException {
		final Map<String, Map<String, String>> layers = createLayerClasses(model);
		layers.values().forEach(this::writeLayers);
		registerOutputs(layers, writeGraphWrapper(new GraphWrapperCreator(conf.language(), conf.outDSL(), conf.level(), conf.workingPackage(), conf.calculateLanguageWorkingPackage(), conf.isPersistent()).create(model)));
		if (conf.level().equals(Level.Platform)) writePlatform(createPlatform());
		else writeApplication(createApplication());
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
			for (String out : entry.getValue().keySet()) put(entry.getKey(), out);
	}

	private void put(String key, String value) {
		if (!outMap.containsKey(key)) outMap.put(key, new ArrayList<>());
		outMap.get(key).add(value);
	}

	private String createPlatform() {
		Frame frame = new Frame().addTypes("platform");
		frame.addFrame(OUT_LANGUAGE, conf.outDSL());
		return customize(LevelTemplate.create()).format(frame);
	}

	private String createApplication() {
		Frame frame = new Frame().addTypes("application");
//		if (conf.isOntology()) frame.addTypes("ontology");
		frame.addFrame(OUT_LANGUAGE, conf.outDSL());
		return customize(LevelTemplate.create()).format(frame);
	}

	private Map<String, Map<String, String>> createLayerClasses(Model model) throws TaraException {
		Map<String, Map<String, String>> map = new HashMap();
		model.components().forEach(node -> {
			if (node.is(Tag.Instance) || !((NodeImpl) node).isDirty() || ((NodeImpl) node).isVirtual()) return;
			if (node.facetTarget() != null) renderNodeWithFacetTarget(map, node);
			else renderNode(map, node);
		});
		return map;
	}

	private void renderNodeWithFacetTarget(Map<String, Map<String, String>> map, Node node) {
		if (node.facetTarget() != null) {
			Map.Entry<String, Frame> layerFrame = new LayerFrameCreator(conf).create(node.facetTarget(), node);
			if (!map.containsKey(node.file())) map.put(node.file(), new LinkedHashMap<>());
			map.get(node.file()).put(destiny(layerFrame), format(layerFrame));
		}
	}

	private void renderNode(Map<String, Map<String, String>> map, Node node) {
		Map.Entry<String, Frame> layerFrame = new LayerFrameCreator(conf).create(node);
		if (!map.containsKey(node.file())) map.put(node.file(), new LinkedHashMap<>());
		map.get(node.file()).put(destiny(layerFrame), format(layerFrame));
	}

	private String destiny(Map.Entry<String, Frame> layerFrame) {
		return new File(outFolder, layerFrame.getKey().replace(DOT, separator) + JAVA).getAbsolutePath();
	}

	private List<String> writeLayers(Map<String, String> documentMap) {
		List<String> outputs = new ArrayList<>();
		for (Map.Entry<String, String> entry : documentMap.entrySet()) {
			File file = new File(entry.getKey());
			file.getParentFile().mkdirs();
			write(file, entry.getValue());
			outputs.add(file.getAbsolutePath());
		}
		return outputs;
	}

	private String writeGraphWrapper(String text) {
		File destiny = new File(new File(outFolder, conf.workingPackage().replace(".", File.separator)), WRAPPER + JAVA);
		destiny.getParentFile().mkdirs();
		return write(destiny, text) ? destiny.getAbsolutePath() : null;
	}

	private String writeApplication(String text) {
		File destiny = new File(new File(conf.srcDirectory(), conf.workingPackage().toLowerCase().replace(".", File.separator)), conf.outDSL() + TemplateTags.APPLICATION + JAVA);
		return destiny.exists() ? destiny.getAbsolutePath() : write(destiny, text) ? destiny.getAbsolutePath() : null;
	}

	private String writePlatform(String text) {
		File destiny = new File(new File(conf.srcDirectory(), conf.workingPackage().toLowerCase().replace(".", File.separator)), conf.outDSL() + TemplateTags.PLATFORM + JAVA);
		return destiny.exists() ? destiny.getAbsolutePath() : write(destiny, text) ? destiny.getAbsolutePath() : null;
	}

	private void cleanOldLayers(Model model) {
		final String workingPackage = conf.workingPackage() == null ? conf.getModule() : conf.workingPackage();
		File out = new File(conf.getOutDirectory(), workingPackage.toLowerCase());
		List<File> layers = filterOld(collectAllLayers(out), out, model);
		layers.forEach(File::delete);
	}

	private List<File> filterOld(List<File> files, File base, Model model) {
		List<File> current = calculateCurrentLayers(base, model);
		return files.stream().filter(layer -> !current.contains(layer)).collect(Collectors.toList());
	}

	private List<File> calculateCurrentLayers(File base, Model model) {
		return model.components().stream().filter(n -> !n.is(Tag.Instance) && !n.isAnonymous()).map(node -> new File(calculateLayerPath(node, base) + JAVA)).collect(Collectors.toList());
	}

	private String calculateLayerPath(Node node, File base) {
		final String aPackage = packageOf(node);
		return base.getPath() + File.separator + aPackage + Format.javaValidName().format(node.name()).toString() + facetName(node.facetTarget());
	}

	private String facetName(FacetTarget facetTarget) {
		return facetTarget != null ? facetTarget.targetNode().name() : "";
	}

	private String packageOf(Node node) {
		return node.facetTarget() != null ? facetLayerPackage(node.facetTarget(), "").substring(1).replace(".", File.separator) : File.separator;
	}

	private List<File> collectAllLayers(File out) {
		List<File> list = new ArrayList<>();
		if (!out.isDirectory() && !out.getName().equals(WRAPPER + JAVA)) list.add(out);
		else if (!out.isDirectory())
			for (File file : out.listFiles(f -> !f.getName().equals("natives")))
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

	private String format(Map.Entry<String, Frame> layerFrame) {
		return template.format(layerFrame.getValue());
	}
}