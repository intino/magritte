package siani.tara.compiler.core.operation;

import de.hunsicker.jalopy.Jalopy;
import org.siani.itrules.Template;
import org.siani.itrules.model.Frame;
import siani.tara.compiler.codegeneration.Format;
import siani.tara.compiler.codegeneration.magritte.BoxUnitFrameCreator;
import siani.tara.compiler.codegeneration.magritte.MorphFrameCreator;
import siani.tara.compiler.codegeneration.magritte.NameFormatter;
import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.compiler.core.operation.model.ModelOperation;
import siani.tara.compiler.model.Element;
import siani.tara.compiler.model.FacetTarget;
import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.rt.TaraRtConstants;
import siani.tara.templates.BoxDSLTemplate;
import siani.tara.templates.BoxUnitTemplate;
import siani.tara.templates.MorphTemplate;
import siani.tara.templates.SceneTemplate;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.io.File.separator;
import static siani.tara.compiler.codegeneration.magritte.NameFormatter.*;

public class ModelToJavaOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(ModelToJavaOperation.class.getName());
	private static final String JAVA = ".java";
	protected static final String DOT = ".";
	private final CompilationUnit compilationUnit;
	private Model model;
	private File outFolder;
	private final CompilerConfiguration conf;

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
			List<List<Node>> groupByBox = groupByBox(model);
			Map<String, String> boxUnits = createBoxUnits(groupByBox);
			writeBoxUnits(getBoxUnitPath(separator), boxUnits);
			if (model.isTerminal()) return;
			writeMorphs(createMorphs());
			writeBox(getBoxPath(separator), createBoxes(boxUnits.keySet()));
			writeScene(createScene());
		} catch (TaraException e) {
			LOG.log(Level.SEVERE, "Error during java model generation: " + e.getMessage(), e);
			throw new CompilationFailedException(compilationUnit.getPhase(), compilationUnit, e);
		}
	}

	private String createScene() {
		Frame frame = new Frame(null).addTypes("scene");
		frame.addFrame("name", conf.getGeneratedLanguage());
		for (Node node : collectRootNodes())
			frame.addFrame("root", createRootFrame(node));
		return customize(SceneTemplate.create()).format(frame);
	}

	private Frame createRootFrame(Node node) {
		Frame frame = new Frame();
		frame.addTypes("root");
		frame.addFrame("qn", getQn(node));
		frame.addFrame("name", node.getName());
		return frame;
	}

	private String getQn(Node node) {
		return NameFormatter.composeMorphPackagePath(node, conf.getLocale(), conf.getGeneratedLanguage()) + DOT + node.getQualifiedName();
	}

	private Collection<Node> collectRootNodes() {
		return model.getIncludedNodes();
	}

	private Template customize(Template template) {
		template.add("date", Format.date());
		template.add("string", Format.string());
		template.add("reference", Format.reference());
		template.add("toCamelCase", Format.toCamelCase());
		return template;
	}

	private Map<String, String> createBoxUnits(List<List<Node>> groupByBox) throws TaraException {
		Map<String, String> map = new HashMap();
		for (List<Node> nodes : groupByBox)
			map.put(buildBoxUnitName(nodes.get(0)), customize(BoxUnitTemplate.create()).format(new BoxUnitFrameCreator(conf, model, nodes).create()));
		return map;
	}

	private String createBoxes(Set<String> boxes) throws TaraException {
		Frame frame = new Frame(null).addTypes("Box");
		frame.addFrame("name", conf.getGeneratedLanguage());
		for (String box : boxes)
			frame.addFrame("namebox", buildBoxUnitName(box));
		return customize(BoxDSLTemplate.create()).format(frame);
	}

	private Map<String, String> createMorphs() throws TaraException {
		Map<String, String> map = new HashMap();
		for (Node node : model.getIncludedNodes()) {
			if (node.isTerminalInstance() || node.isAnonymous()) continue;
			renderNode(map, node);
			renderFacetTargets(map, node);
		}
		return map;
	}

	private String buildBoxUnitName(Node node) {
		return capitalize(conf.getGeneratedLanguage()) + buildFileName(((Element) node).getFile());
	}

	private String buildBoxUnitName(String box) {
		return "magritte.boxes." + box + DOT + "box";
	}

	private void renderFacetTargets(Map<String, String> map, Node node) {
		for (FacetTarget facetTarget : node.getFacetTargets()) {
			Map.Entry<String, Frame> morphFrame = new MorphFrameCreator(conf.getProject(), conf.getGeneratedLanguage(), conf.getLanguage(), conf.getLocale()).create(facetTarget);
			map.put(morphFrame.getKey(), customize(MorphTemplate.create()).format(morphFrame.getValue()));
		}
	}

	private void renderNode(Map<String, String> map, Node node) {
		Map.Entry<String, Frame> morphFrame = new MorphFrameCreator(conf.getProject(), conf.getGeneratedLanguage(), conf.getLanguage(), conf.getLocale()).create(node);
		map.put(morphFrame.getKey(), customize(MorphTemplate.create()).format(morphFrame.getValue()));
	}

	private void writeBoxUnits(String directory, Map<String, String> documentMap) {
		File destiny = new File(outFolder, directory);
		destiny.mkdirs();
		for (Map.Entry<String, String> entry : documentMap.entrySet()) {
			File file = new File(destiny, entry.getKey().replace(DOT, separator) + JAVA);
			file.getParentFile().mkdirs();
			try {
				BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
				fileWriter.write(entry.getValue());
				fileWriter.close();
			} catch (IOException e) {
				LOG.log(Level.SEVERE, e.getMessage(), e);
			}
		}
	}

	private void writeMorphs(Map<String, String> documentMap) {
		for (Map.Entry<String, String> entry : documentMap.entrySet()) {
			File file = new File(outFolder, entry.getKey().replace(DOT, separator) + JAVA);
			file.getParentFile().mkdirs();
			try {
				BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
				fileWriter.write(entry.getValue());
				fileWriter.close();
			} catch (IOException e) {
				LOG.log(Level.SEVERE, e.getMessage(), e);
			}
			prettyPrint(file);
		}
	}

	private void writeBox(String boxPath, String document) {
		File destiny = new File(outFolder, boxPath);
		destiny.mkdirs();
		try {
			File file = new File(destiny, conf.getGeneratedLanguage() + JAVA);
			BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
			fileWriter.write(document);
			fileWriter.close();
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	private void writeScene(String scene) {
		File destiny = new File(outFolder, conf.getGeneratedLanguage().toLowerCase());
		destiny.mkdirs();
		try {
			File file = new File(destiny, capitalize(conf.getGeneratedLanguage()) + JAVA);
			BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
			fileWriter.write(scene);
			fileWriter.close();
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	private void prettyPrint(File file) {
		try {
			org.apache.log4j.BasicConfigurator.configure(new org.apache.log4j.varia.NullAppender());
			Jalopy jalopy = new Jalopy();
			jalopy.setInput(file);
			jalopy.setOutput(file);
			jalopy.format();
		} catch (FileNotFoundException ignored) {
		}
	}

	private List<List<Node>> groupByBox(Model model) {
		Map<String, List<Node>> nodes = new HashMap();
		for (Node node : model.getIncludedNodes()) {
			if (!nodes.containsKey(((Element) node).getFile()))
				nodes.put(((Element) node).getFile(), new ArrayList<Node>());
			nodes.get(((Element) node).getFile()).add(node);
		}
		return pack(nodes);
	}

	private List<List<Node>> pack(Map<String, List<Node>> nodes) {
		List<List<Node>> lists = new ArrayList<>();
		for (List<Node> nodeList : nodes.values())
			lists.add(nodeList);
		return lists;
	}
}
