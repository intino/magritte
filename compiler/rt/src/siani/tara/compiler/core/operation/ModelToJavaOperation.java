package siani.tara.compiler.core.operation;

import de.hunsicker.jalopy.Jalopy;
import org.siani.itrules.Template;
import org.siani.itrules.model.Frame;
import siani.tara.compiler.codegeneration.Format;
import siani.tara.compiler.codegeneration.magritte.NameFormatter;
import siani.tara.compiler.codegeneration.magritte.box.BoxUnitFrameCreator;
import siani.tara.compiler.codegeneration.magritte.morph.MorphFrameCreator;
import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.compiler.core.operation.model.ModelOperation;
import siani.tara.compiler.model.FacetTarget;
import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.rt.TaraRtConstants;
import siani.tara.templates.BoxDSLTemplate;
import siani.tara.templates.BoxUnitTemplate;
import siani.tara.templates.ModelTemplate;
import siani.tara.templates.MorphTemplate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.io.File.separator;
import static siani.tara.compiler.codegeneration.magritte.NameFormatter.*;

public class ModelToJavaOperation extends ModelOperation {
	protected static final String DOT = ".";
	private static final Logger LOG = Logger.getLogger(ModelToJavaOperation.class.getName());
	private static final String JAVA = ".java";
	private final CompilationUnit compilationUnit;
	private final CompilerConfiguration conf;
	private Model model;
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
			this.model = model;
			Map<String, SimpleEntry<String, String>> boxUnits = createBoxUnits(groupByBox(model));
			writeBoxUnits(boxUnits);
			fillBoxesInOutMap(boxUnits);
			if (model.getLevel() == 0) return;
			final Map<String, Map<String, String>> morphs = createMorphs();
			morphs.values().forEach(this::writeMorphs);
			fillMorphsInOutMap(morphs);
			final String boxDslPath = writeBoxDSL(getBoxDSLPath(separator), createBoxDSL(boxUnits.keySet()));
			final String modelPath = writeModel(createModel());
			for (String boxUnit : boxUnits.keySet()) put(boxUnit, boxDslPath);
			for (String boxUnit : boxUnits.keySet()) put(boxUnit, modelPath);
			compilationUnit.addOutputItems(outMap);
		} catch (TaraException e) {
			LOG.log(Level.SEVERE, "Error during java model generation: " + e.getMessage(), e);
			throw new CompilationFailedException(compilationUnit.getPhase(), compilationUnit, e);
		}
	}

	private void fillBoxesInOutMap(Map<String, SimpleEntry<String, String>> map) {
		for (Map.Entry<String, SimpleEntry<String, String>> entry : map.entrySet())
			put(entry.getKey(), entry.getValue().getKey());
	}

	private void fillMorphsInOutMap(Map<String, Map<String, String>> map) {
		for (Map.Entry<String, Map<String, String>> entry : map.entrySet())
			for (String out : entry.getValue().keySet()) put(entry.getKey(), out);
	}

	private void put(String key, String value) {
		if (!outMap.containsKey(key)) outMap.put(key, new ArrayList<>());
		outMap.get(key).add(value);
	}

	private String createModel() {
		Frame frame = new Frame().addTypes("scene");
		frame.addFrame("name", conf.getGeneratedLanguage());
		collectRootNodes().stream().filter(node -> node.getName() != null && !node.isTerminalInstance()).forEach(node -> frame.addFrame("root", createRootFrame(node)));
		return customize(ModelTemplate.create()).format(frame);
	}

	private Frame createRootFrame(Node node) {
		Frame frame = new Frame();
		frame.addTypes("root");
		if (node.isSingle()) frame.addTypes("single");
		frame.addFrame("qn", getQn(node));
		frame.addFrame("name", node.getName());
		return frame;
	}

	private String getQn(Node node) {
		return NameFormatter.composeMorphPackagePath(conf.getGeneratedLanguage()) + DOT + node.getQualifiedName();
	}

	private Collection<Node> collectRootNodes() {
		return model.getIncludedNodes().stream().filter((node) -> node.isMain() && !node.isAbstract()).collect(Collectors.toList());
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

	private Map<String, SimpleEntry<String, String>> createBoxUnits(List<List<Node>> groupByBox) throws TaraException {
		Map<String, SimpleEntry<String, String>> map = new HashMap();
		File destiny = new File(outFolder, getBoxUnitPath(separator));
		for (List<Node> nodes : groupByBox)
			map.put(nodes.get(0).getFile(),
				new SimpleEntry<>(new File(destiny, buildBoxUnitName(nodes.get(0)).replace(DOT, separator) + JAVA).getAbsolutePath(),
					customize(BoxUnitTemplate.create()).format(new BoxUnitFrameCreator(conf, model, nodes).create())));
		return map;
	}

	private String createBoxDSL(Set<String> boxes) throws TaraException {
		Frame frame = new Frame().addTypes("Box");
		frame.addFrame("name", conf.getGeneratedLanguage());
		for (String box : boxes) {
			frame.addFrame("namebox", buildBoxUnitName(box));
		}
		return customize(BoxDSLTemplate.create()).format(frame);
	}

	private Map<String, Map<String, String>> createMorphs() throws TaraException {
		Map<String, Map<String, String>> map = new HashMap();
		for (Node node : model.getIncludedNodes()) {
			if (node.isTerminalInstance() || node.isAnonymous() || node.isFeatureInstance()) continue;
			renderNode(map, node);
			renderFacetTargets(map, node);
		}
		return map;
	}

	private String buildBoxUnitName(Node node) {
		return (String) Format.javaValidName().format(capitalize(conf.getGeneratedLanguage() != null ? conf.getGeneratedLanguage() : conf.getModule()) + buildFileName(node.getFile()));
	}

	private String buildBoxUnitName(String taraPath) {
		String box = taraPath.substring(taraPath.lastIndexOf(separator) + 1);
		box = conf.getGeneratedLanguage() + box.substring(0, box.lastIndexOf("."));
		return "magritte.ontology." + box + DOT + "box";
	}

	private void renderFacetTargets(Map<String, Map<String, String>> map, Node node) {
		for (FacetTarget facetTarget : node.getFacetTargets()) {
			Map.Entry<String, Frame> morphFrame = new MorphFrameCreator(conf).create(facetTarget);
			if (!map.containsKey(node.getFile())) map.put(node.getFile(), new LinkedHashMap<>());
			map.get(node.getFile()).put(new File(outFolder, morphFrame.getKey().replace(DOT, separator) + JAVA).getAbsolutePath(), customize(MorphTemplate.create()).format(morphFrame.getValue()));
		}
	}

	private void renderNode(Map<String, Map<String, String>> map, Node node) {
		Map.Entry<String, Frame> morphFrame = new MorphFrameCreator(conf).create(node);
		if (!map.containsKey(node.getFile())) map.put(node.getFile(), new LinkedHashMap<>());
		map.get(node.getFile()).put(new File(outFolder, morphFrame.getKey().replace(DOT, separator) + JAVA).getAbsolutePath(), customize(MorphTemplate.create()).format(morphFrame.getValue()));
	}


	private List<String> writeBoxUnits(Map<String, SimpleEntry<String, String>> documentMap) {
		List<String> outputs = new ArrayList<>();

		for (SimpleEntry<String, String> entry : documentMap.values()) {
			File file = new File(entry.getKey());
			file.getParentFile().mkdirs();
			try {
				BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
				fileWriter.write(entry.getValue());
				fileWriter.close();
				outputs.add(file.getAbsolutePath());
			} catch (IOException e) {
				LOG.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		return outputs;
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
			prettyPrint(file);
			outputs.add(file.getAbsolutePath());
		}
		return outputs;
	}

	private String writeBoxDSL(String boxPath, String document) {
		File destiny = new File(outFolder, boxPath);
		destiny.mkdirs();
		try {
			File file = new File(destiny, Format.reference().format(conf.getGeneratedLanguage()) + "Dsl" + JAVA);
			BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
			fileWriter.write(document);
			fileWriter.close();
			return file.getAbsolutePath();
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
		}
		return null;
	}

	private String writeModel(String scene) {
		File destiny = new File(outFolder, conf.getGeneratedLanguage().toLowerCase());
		destiny.mkdirs();
		try {
			File file = new File(destiny, capitalize(conf.getGeneratedLanguage()) + "Model" + JAVA);
			BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
			fileWriter.write(scene);
			fileWriter.close();
			return file.getAbsolutePath();
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
		}
		return null;
	}

	private void prettyPrint(File file) {
		try {
			org.apache.log4j.BasicConfigurator.configure(new org.apache.log4j.varia.NullAppender());
			Jalopy jalopy = new Jalopy();
			jalopy.setInput(file);
			jalopy.setOutput(file);
			jalopy.format();
		} catch (Exception ignored) {
		}
	}

	private List<List<Node>> groupByBox(Model model) {
		Map<String, List<Node>> nodes = new HashMap();
		for (Node node : model.getIncludedNodes()) {
			if (!nodes.containsKey(node.getFile()))
				nodes.put(node.getFile(), new ArrayList<>());
			nodes.get(node.getFile()).add(node);
		}
		return pack(nodes);
	}

	private List<List<Node>> pack(Map<String, List<Node>> nodes) {
		return nodes.values().stream().collect(Collectors.toList());
	}
}
