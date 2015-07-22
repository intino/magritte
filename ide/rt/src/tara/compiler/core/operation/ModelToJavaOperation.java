package tara.compiler.core.operation;

import org.siani.itrules.Template;
import org.siani.itrules.model.Frame;
import tara.compiler.codegeneration.Format;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.codegeneration.magritte.box.StashCreator;
import tara.compiler.codegeneration.magritte.morph.MorphFrameCreator;
import tara.compiler.core.CompilationUnit;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.core.errorcollection.TaraException;
import tara.compiler.core.operation.model.ModelOperation;
import tara.compiler.model.Model;
import tara.compiler.rt.TaraRtConstants;
import tara.io.Stash;
import tara.io.StashSerializer;
import tara.language.model.FacetTarget;
import tara.language.model.Node;
import tara.templates.ModelTemplate;
import tara.templates.MorphTemplate;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.io.File.separator;

public class ModelToJavaOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(ModelToJavaOperation.class.getName());
	private static final String DOT = ".";
	private static final String STASH = ".stash";
	private static final String JAVA = ".java";

	private final CompilationUnit compilationUnit;
	private final CompilerConfiguration conf;
	private File outFolder;
	private Map<String, List<String>> outMap = new LinkedHashMap<>();

	public ModelToJavaOperation(CompilationUnit compilationUnit) {
		super();
		this.compilationUnit = compilationUnit;
		conf = compilationUnit.getConfiguration();
		outFolder = conf.getOutDirectory();
	}

	@Override
	public void call(Model model) throws CompilationFailedException {
		try {
			System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Generating code representation");
			if (model.getLevel() != 0) createMorphs(model);
			writeStashes(createStashes(pack(model)));
		} catch (TaraException e) {
			LOG.log(Level.SEVERE, "Error during java model generation: " + e.getMessage(), e);
			throw new CompilationFailedException(compilationUnit.getPhase(), compilationUnit, e);
		}
	}

	private void createMorphs(Model model) throws TaraException {
		final Map<String, Map<String, String>> morphs;
		morphs = createMorphClasses(model);
		morphs.values().forEach(this::writeMorphs);
		final String modelPath = writeModel(createModel(model));
		collectOutputs(morphs, modelPath);
	}

	private void collectOutputs(Map<String, Map<String, String>> morphs, String modelPath) {
		fillMorphsInOutMap(morphs);
		for (List<String> paths : outMap.values())
			paths.add(modelPath);
		compilationUnit.addOutputItems(outMap);
	}

	private void fillMorphsInOutMap(Map<String, Map<String, String>> map) {
		for (Map.Entry<String, Map<String, String>> entry : map.entrySet())
			for (String out : entry.getValue().keySet()) put(entry.getKey(), out);
	}

	private void put(String key, String value) {
		if (!outMap.containsKey(key)) outMap.put(key, new ArrayList<>());
		outMap.get(key).add(value);
	}

	private String createModel(Model model) {
		Frame frame = new Frame().addTypes("model");
		frame.addFrame("name", conf.getGeneratedLanguage());
		collectRootNodes(model).stream().filter(node -> node.name() != null && !node.isTerminalInstance()).
			forEach(node -> frame.addFrame("node", createRootFrame(node)));
		return customize(ModelTemplate.create()).format(frame);
	}

	private Frame createRootFrame(Node node) {
		Frame frame = new Frame();
		frame.addTypes("node");
		if (node.isSingle()) frame.addTypes("single");
		frame.addFrame("qn", getQn(node));
		frame.addFrame("name", node.name());
		return frame;
	}

	private String getQn(Node node) {
		return conf.getGeneratedLanguage().toLowerCase() + DOT + node.qualifiedName();
	}

	private Collection<Node> collectRootNodes(Model model) {
		return model.components().stream().filter((node) -> node.isMain() && !node.isAbstract()).collect(Collectors.toList());
	}

	private Template customize(Template template) {
		template.add("string", Format.string());
		template.add("reference", Format.reference());
		template.add("toCamelCase", Format.toCamelCase());
		template.add("key", Format.key());
		template.add("returnValue", (trigger, type) -> trigger.frame().frames("returnValue").next().value().equals(type));
		template.add("WithoutType", Format.nativeParameter());
		template.add("javaValidName", Format.javaValidName());
		return template;
	}

	private Map<String, Stash> createStashes(List<List<Node>> groupByBox) throws TaraException {
		Map<String, Stash> map = new HashMap();
		groupByBox.stream().forEach(nodes -> map.put(nodes.get(0).file(), new StashCreator(nodes).create()));
		return map;
	}

	private Map<String, Map<String, String>> createMorphClasses(Model model) throws TaraException {
		Map<String, Map<String, String>> map = new HashMap();
		for (Node node : model.components()) {
			if (node.isTerminalInstance() || node.isAnonymous() || node.isFeatureInstance()) continue;
			renderNode(map, node);
			renderFacetTargets(map, node);
		}
		return map;
	}

	private void renderFacetTargets(Map<String, Map<String, String>> map, Node node) {
		for (FacetTarget facetTarget : node.facetTargets()) {
			Map.Entry<String, Frame> morphFrame = new MorphFrameCreator(conf).create(facetTarget);
			if (!map.containsKey(node.file())) map.put(node.file(), new LinkedHashMap<>());
			map.get(node.file()).put(new File(outFolder, morphFrame.getKey().replace(DOT, separator) + JAVA).getAbsolutePath(), customize(MorphTemplate.create()).format(morphFrame.getValue()));
		}
	}

	private void renderNode(Map<String, Map<String, String>> map, Node node) {
		Map.Entry<String, Frame> morphFrame = new MorphFrameCreator(conf).create(node);
		if (!map.containsKey(node.file())) map.put(node.file(), new LinkedHashMap<>());
		map.get(node.file()).put(new File(outFolder, morphFrame.getKey().replace(DOT, separator) + JAVA).getAbsolutePath(), customize(MorphTemplate.create()).format(morphFrame.getValue()));
	}


	private List<String> writeMorphs(Map<String, String> documentMap) {
		List<String> outputs = new ArrayList<>();
		for (Map.Entry<String, String> entry : documentMap.entrySet()) {
			File file = new File(entry.getKey());
			file.getParentFile().mkdirs();
			try {
				BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
				fileWriter.write(entry.getValue());
				fileWriter.close();
			} catch (IOException e) {
				LOG.log(Level.SEVERE, e.getMessage(), e);
			}
			outputs.add(file.getAbsolutePath());
		}
		return outputs;
	}

	private String writeModel(String scene) {
		File destiny = new File(outFolder, conf.getGeneratedLanguage().toLowerCase());
		destiny.mkdirs();
		try {
			File file = new File(destiny, NameFormatter.capitalize(conf.getGeneratedLanguage()) + "Model" + JAVA);
			BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
			fileWriter.write(scene);
			fileWriter.close();
			return file.getAbsolutePath();
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
		}
		return null;
	}

	private List<String> writeStashes(Map<String, Stash> stashes) {
		for (Map.Entry<String, Stash> entry : stashes.entrySet()) {
			writeStash(new File(entry.getKey()), entry.getValue());
		}
		return Collections.emptyList();
	}

	private String writeStash(File taraFile, Stash stash) {
		final byte[] content = StashSerializer.serialize(stash);
		final File file = findStashDestiny(taraFile);
		file.getParentFile().mkdirs();
		try (FileOutputStream stream = new FileOutputStream(file)) {
			stream.write(content);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file.getPath();
	}

	private File findStashDestiny(File taraFile) {
		return new File(outFolder, NameFormatter.getBoxUnitPath(separator) + separator + getPresentableName(taraFile.getName()) + STASH);
	}

	private static String getPresentableName(String name) {
		return name.substring(0, name.lastIndexOf("."));
	}

	private List<List<Node>> pack(Model model) {
		Map<String, List<Node>> nodes = new HashMap();
		for (Node node : model.components()) {
			if (!nodes.containsKey(node.file()))
				nodes.put(node.file(), new ArrayList<>());
			nodes.get(node.file()).add(node);
		}
		return pack(nodes);
	}

	private List<List<Node>> pack(Map<String, List<Node>> nodes) {
		return nodes.values().stream().collect(Collectors.toList());
	}
}
