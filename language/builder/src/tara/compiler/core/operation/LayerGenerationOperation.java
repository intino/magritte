package tara.compiler.core.operation;

import org.siani.itrules.Template;
import org.siani.itrules.model.Frame;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.codegeneration.magritte.layer.DynamicTemplate;
import tara.compiler.codegeneration.magritte.layer.LayerFrameCreator;
import tara.compiler.codegeneration.magritte.layer.LayerTemplate;
import tara.compiler.codegeneration.magritte.layer.ModelWrapperCreator;
import tara.compiler.codegeneration.magritte.natives.NativesCreator;
import tara.compiler.constants.TaraBuildConstants;
import tara.compiler.core.CompilationUnit;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.core.errorcollection.TaraException;
import tara.compiler.core.operation.model.ModelOperation;
import tara.compiler.model.Model;
import tara.compiler.model.NodeImpl;
import tara.lang.model.Node;
import tara.lang.model.Tag;
import tara.templates.LevelTemplate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.io.File.separator;
import static tara.compiler.codegeneration.Format.customize;

public class LayerGenerationOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(LayerGenerationOperation.class.getName());
	private static final String DOT = ".";
	private static final String JAVA = ".java";
	private static final String HANDLER = "ModelWrapper";

	private final CompilationUnit compilationUnit;
	private final CompilerConfiguration conf;
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
		try {
			if (conf.isVerbose())
				System.out.println(TaraBuildConstants.PRESENTABLE_MESSAGE + "[" + conf.getModule() + "] Generating Layers...");
			if (model.getLevel() != 0) createLayers(model);
			else if (!conf.isTest()) writeMain(createMain());
			registerOutputs(writeNativeClasses(model));
			compilationUnit.addOutputItems(outMap);
		} catch (TaraException e) {
			LOG.log(Level.SEVERE, "Error during java className generation: " + e.getMessage(), e);
			throw new CompilationFailedException(compilationUnit.getPhase(), compilationUnit, e);
		}
	}

	private Map<String, String> writeNativeClasses(Model model) {
		return new NativesCreator(model, conf).create();
	}

	private void createLayers(Model model) throws TaraException {
		final Map<String, Map<String, String>> layers;
		layers = createLayerClasses(model);
		layers.values().forEach(this::writeLayers);
		registerOutputs(layers, writeModelHandler(new ModelWrapperCreator(conf.getLanguage(), conf.generatedLanguage(), conf.level(), conf.isDynamicLoad(), conf.getImportsFile()).create(model)));
		if (conf.level() == 2) writePlatform(createPlatform());
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
		frame.addFrame("generatedLanguage", conf.generatedLanguage());
		return customize(LevelTemplate.create()).format(frame);
	}

	private String createApplication() {
		Frame frame = new Frame().addTypes("application");
		if (conf.isOntology()) frame.addTypes("ontology");
		frame.addFrame("generatedLanguage", conf.generatedLanguage());
		return customize(LevelTemplate.create()).format(frame);
	}

	private String createMain() {
		Frame frame = new Frame().addTypes("launcher");
		if (conf.isOntology()) frame.addTypes("ontology");
		frame.addFrame("language", conf.getLanguage().languageName());
		frame.addFrame("metaLanguage", conf.getLanguage().metaLanguage());
		frame.addFrame("dynamic", conf.isDynamicLoad() ? "Dynamic" : "");
		return customize(LevelTemplate.create()).format(frame);
	}

	private Map<String, Map<String, String>> createLayerClasses(Model model) throws TaraException {
		Map<String, Map<String, String>> map = new HashMap();
		model.components().forEach(node -> {
			if (node.is(Tag.Instance) || !((NodeImpl) node).isDirty()) return;
			if (node.facetTarget() != null && node.facetTarget().owner().equals(node)) renderNodeWithFacetTarget(map, node);
			else renderNode(map, node);
		});
		return map;
	}

	private void renderNodeWithFacetTarget(Map<String, Map<String, String>> map, Node node) {
		if (node.facetTarget() != null) {
			Map.Entry<String, Frame> layerFrame = new LayerFrameCreator(conf).create(node.facetTarget());
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

	private String writeModelHandler(String text) {
		File destiny = new File(new File(outFolder, conf.generatedLanguage().toLowerCase()), HANDLER + JAVA);
		destiny.getParentFile().mkdirs();
		return write(destiny, text) ? destiny.getAbsolutePath() : null;
	}

	private String writeMain(String text) {
		File destiny = new File(conf.getSrcPath(), TemplateTags.MAIN + JAVA);
		return destiny.exists() ? destiny.getAbsolutePath() : write(destiny, text) ? destiny.getAbsolutePath() : null;
	}

	private String writeApplication(String text) {
		File destiny = new File(new File(conf.getSrcPath(), conf.generatedLanguage().toLowerCase()), conf.generatedLanguage() + TemplateTags.APPLICATION + JAVA);
		return destiny.exists() ? destiny.getAbsolutePath() : write(destiny, text) ? destiny.getAbsolutePath() : null;
	}

	private String writePlatform(String text) {
		File destiny = new File(new File(conf.getSrcPath(), conf.generatedLanguage().toLowerCase()), conf.generatedLanguage() + TemplateTags.PLATFORM + JAVA);
		return destiny.exists() ? destiny.getAbsolutePath() : write(destiny, text) ? destiny.getAbsolutePath() : null;
	}


	private boolean write(File file, String text) {
		try {
			file.getParentFile().mkdirs();
			BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
			fileWriter.write(text);
			fileWriter.close();
			return true;
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
		}
		return false;
	}

	private Template getTemplate() {
		return conf.isDynamicLoad() ? DynamicTemplate.create() : LayerTemplate.create();
	}

	private String format(Map.Entry<String, Frame> layerFrame) {
		return customize(getTemplate()).format(layerFrame.getValue());
	}


}