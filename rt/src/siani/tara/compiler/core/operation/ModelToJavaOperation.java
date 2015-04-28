package siani.tara.compiler.core.operation;

import org.siani.itrules.Template;
import org.siani.itrules.model.Frame;
import siani.tara.compiler.codegeneration.StringFormatter;
import siani.tara.compiler.codegeneration.magritte.BoxUnitFrameCreator;
import siani.tara.compiler.codegeneration.magritte.MorphFrameCreator;
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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
			writeBox(getBoxPath(separator), createBoxes(boxUnits.keySet()));
			writeMorphs(createMorphs());
		} catch (TaraException e) {
			LOG.log(Level.SEVERE, "Error during java model generation: " + e.getMessage(), e);
			throw new CompilationFailedException(compilationUnit.getPhase(), compilationUnit, e);
		}
	}

	private Map<String, String> createBoxUnits(List<List<Node>> groupByBox) throws TaraException {
		return processBoxUnits(groupByBox);
	}

	private String createBoxes(Set<String> boxes) throws TaraException {
		return processBoxes(boxes);
	}

	private Map<String, String> createMorphs() throws TaraException {
		return processMorphs(model.getIncludedNodes());
	}

	private Map<String, String> processBoxUnits(List<List<Node>> groupByBox) throws TaraException {
		Map<String, String> map = new HashMap();
		Template template = createTemplate(BoxUnitTemplate.class);
		for (List<Node> nodes : groupByBox)
			map.put(buildBoxUnitName(nodes.get(0)), template.render(new BoxUnitFrameCreator(conf, model).create(nodes)));
		return map;
	}

	private String buildBoxUnitName(Node node) {
		return capitalize(conf.getModule()) + buildFileName(((Element) node).getFile());
	}

	private String processBoxes(Set<String> boxes) throws TaraException {
		Template ruleEngine = createTemplate(BoxDSLTemplate.class);
		Frame frame = new Frame(null).addTypes("Box");
		frame.addFrame("name", conf.getGeneratedLanguage());
		for (String box : boxes)
			frame.addFrame("namebox", buildBoxUnitName(box));
		return ruleEngine.render(frame);
	}

	private String buildBoxUnitName(String box) {
		return "magritte.store." + box + DOT + "box";
	}

	private Map<String, String> processMorphs(Collection<Node> nodes) {
		Map<String, String> map = new HashMap();
		Template ruleEngine = createTemplate(MorphTemplate.class);
		for (Node node : nodes) {
			if (node.isTerminalInstance() || node.isAnonymous()) continue;
			renderNode(map, ruleEngine, node);
			renderFacetTargets(map, ruleEngine, node);
		}
		return map;
	}

	private Template createTemplate(Class<? extends Template> aClass) {
		try {
			Template template = (Template) aClass.getMethod("template").invoke(null);
			template.add("date", buildDateFormatter());
			template.add("string", new StringFormatter());
			template.add("reference", buildReferenceFormatter());
			return template;
		} catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException ignored) {
			ignored.printStackTrace();
		}
		return null;
	}

	private void renderFacetTargets(Map<String, String> map, Template template, Node node) {
		for (FacetTarget facetTarget : node.getFacetTargets()) {
			Map.Entry<String, Frame> morphFrame = new MorphFrameCreator(conf.getProject(), conf.getModule(), conf.getLanguage(), conf.getLocale()).create(facetTarget);
			map.put(morphFrame.getKey(), template.render(morphFrame.getValue()));
		}
	}

	private void renderNode(Map<String, String> map, Template template, Node node) {
		Map.Entry<String, Frame> morphFrame =
			new MorphFrameCreator(conf.getProject(), conf.getModule(), conf.getLanguage(), conf.getLocale()).create(node);
		map.put(morphFrame.getKey(), template.render(morphFrame.getValue()));
	}

	private org.siani.itrules.Formatter buildReferenceFormatter() {
		return new org.siani.itrules.Formatter() {
			@Override
			public Object format(Object value) {
				String val = value.toString();
				if (!val.contains(DOT)) return val.substring(0, 1).toUpperCase() + val.substring(1);
				return buildMorphPath(conf.getModule() + DOT + val);
			}
		};
	}

	private org.siani.itrules.Formatter buildDateFormatter() {
		return new org.siani.itrules.Formatter() {
			@Override
			public Object format(Object value) {
				String val = value.toString();
				if (!val.contains("/")) return value;
				return val.replace("/", ", ");
			}
		};
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
