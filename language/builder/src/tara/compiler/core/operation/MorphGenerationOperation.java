package tara.compiler.core.operation;

import org.siani.itrules.Template;
import org.siani.itrules.model.Frame;
import tara.compiler.codegeneration.Format;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.codegeneration.magritte.morph.MorphFrameCreator;
import tara.compiler.codegeneration.magritte.natives.NativeClassCreator;
import tara.compiler.core.CompilationUnit;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.core.errorcollection.TaraException;
import tara.compiler.core.operation.model.ModelOperation;
import tara.compiler.model.Model;
import tara.compiler.constants.TaraBuildConstants;
import tara.language.model.FacetTarget;
import tara.language.model.Node;
import tara.templates.ModelTemplate;
import tara.templates.MorphTemplate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.io.File.separator;

public class MorphGenerationOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(MorphGenerationOperation.class.getName());
	private static final String DOT = ".";
	private static final String JAVA = ".java";
	private final CompilationUnit compilationUnit;
	private final CompilerConfiguration conf;
	private File outFolder;
	private Map<String, List<String>> outMap = new LinkedHashMap<>();

	public MorphGenerationOperation(CompilationUnit compilationUnit) {
		super();
		this.compilationUnit = compilationUnit;
		this.conf = compilationUnit.getConfiguration();
		this.outFolder = conf.getOutDirectory();
	}

	@Override
	public void call(Model model) {
		try {
			System.out.println(TaraBuildConstants.PRESENTABLE_MESSAGE + "Generating Morphs...");
			if (model.getLevel() != 0) createMorphs(model);
			registerOutputs(writeNativeClasses(model));
		} catch (TaraException e) {
			LOG.log(Level.SEVERE, "Error during java morph generation: " + e.getMessage(), e);
			throw new CompilationFailedException(compilationUnit.getPhase(), compilationUnit, e);
		}
	}

	private void registerOutputs(Map<String, String> outs) {
		for (String src : outs.keySet()) {
			if (!outMap.containsKey(src)) outMap.put(src, new ArrayList<>());
			outMap.get(src).add(outs.get(src));
		}
	}

	private Map<String, String> writeNativeClasses(Model model) {
		return new NativeClassCreator(model, conf).serialize();
	}

	private void createMorphs(Model model) throws TaraException {
		final Map<String, Map<String, String>> morphs;
		morphs = createMorphClasses(model);
		morphs.values().forEach(this::writeMorphs);
		registerOutputs(morphs, writeModelMorph(createModelMorph(model)));
	}

	private void registerOutputs(Map<String, Map<String, String>> morphs, String modelPath) {
		fillMorphsInOutMap(morphs);
		for (List<String> paths : outMap.values()) paths.add(modelPath);
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

	private String createModelMorph(Model model) {
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
		return model.components().stream().filter(Node::isMain).collect(Collectors.toList());
	}

	private Template customize(Template template) {
		template.add("string", Format.string());
		template.add("reference", Format.reference());
		template.add("toCamelCase", Format.toCamelCase());
		template.add("withDollar", Format.withDollar());
		template.add("noPackage", Format.noPackage());
		template.add("key", Format.key());
		template.add("returnValue", (trigger, type) -> trigger.frame().frames("returnValue").next().value().equals(type));
		template.add("WithoutType", Format.nativeParameter());
		template.add("javaValidName", Format.javaValidName());
		return template;
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

	private String writeModelMorph(String model) {
		File destiny = new File(outFolder, conf.getGeneratedLanguage().toLowerCase());
		destiny.mkdirs();
		try {
			File file = new File(destiny, NameFormatter.capitalize(conf.getGeneratedLanguage()) + "Model" + JAVA);
			BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
			fileWriter.write(model);
			fileWriter.close();
			return file.getAbsolutePath();
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
		}
		return null;
	}
}