package siani.tara.compiler.core.operation;

import org.siani.itrules.Document;
import org.siani.itrules.ItrRulesReader;
import org.siani.itrules.RuleEngine;
import org.siani.itrules.formatter.Formatter;
import org.siani.itrules.model.Frame;
import siani.tara.compiler.codegeneration.ResourceManager;
import siani.tara.compiler.codegeneration.StringFormatter;
import siani.tara.compiler.codegeneration.magritte.BoxFrameCreator;
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

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static siani.tara.compiler.codegeneration.magritte.NameFormatter.*;

public class ModelToJavaOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(ModelToJavaOperation.class.getName());
	private static final String BOX_UNIT_ITR = "BoxUnit.itr";
	private static final String BOX_ITR = "Box.itr";
	private static final String MORPH_ITR = "Morph.itr";
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
			Map<String, Document> boxUnits = createBoxUnits(groupByBox);
			writeBoxUnits(buildBoxUnitDirectory(), boxUnits);
			if (model.isTerminal()) return;
			writeBox(getBoxPath(File.separator), createBoxes(boxUnits.keySet()));
			writeMorphs(createMorphs());
		} catch (TaraException e) {
			LOG.log(Level.SEVERE, "Error during java model generation: " + e.getMessage(), e);
			throw new CompilationFailedException(compilationUnit.getPhase(), compilationUnit, e);
		}
	}

	private Map<String, Document> createBoxUnits(List<List<Node>> groupByBox) throws TaraException {
		return processBoxUnits(groupByBox, loadRules(BOX_UNIT_ITR));
	}

	private Document createBoxes(Set<String> boxes) throws TaraException {
		return processBoxes(boxes, loadRules(BOX_ITR));
	}

	private Map<String, Document> createMorphs() throws TaraException {
		return processMorphs(model.getIncludedNodes(), loadRules(MORPH_ITR));
	}

	private InputStream loadRules(String itr) throws TaraException {
		InputStream stream = getRulesFromResources(itr);
		if (stream == null) {
			LOG.log(Level.SEVERE, itr + ".itr rules file not found.");
			throw new TaraException(itr + ".itr rules file not found.");
		}
		return stream;
	}

	private Map<String, Document> processBoxUnits(List<List<Node>> groupByBox, InputStream rulesInput) throws TaraException {
		Map<String, Document> map = new HashMap();
		RuleEngine ruleEngine = createRuleEngine(rulesInput);
		for (List<Node> nodes : groupByBox) {
			Document document = new Document();
			ruleEngine.render(new BoxFrameCreator(conf, model).create(nodes), document);
			map.put(NameFormatter.buildFileName(((Element) nodes.get(0)).getFile()), document);
		}
		return map;
	}

	private RuleEngine createRuleEngine(InputStream rulesInput) {
		RuleEngine ruleEngine = new RuleEngine(new ItrRulesReader(rulesInput).read(), conf.getLocale());
		ruleEngine.register("date", buildDateFormatter());
		ruleEngine.register("string", new StringFormatter());
		ruleEngine.register("reference", buildReferenceFormatter());
		return ruleEngine;
	}

	private Document processBoxes(Set<String> boxes, InputStream rulesInput) throws TaraException {
		RuleEngine ruleEngine = createRuleEngine(rulesInput);
		Document document = new Document();
		Frame frame = new Frame("Box");
		frame.addFrame("name", conf.getGeneratedLanguage());
		for (String box : boxes)
			frame.addFrame("boxName", buildBoxUnitName(box));
		ruleEngine.render(frame, document);
		return document;
	}

	private String buildBoxUnitName(String box) {
		return "magritte.store." + conf.getProject().toLowerCase() + DOT + conf.getModule().toLowerCase() + DOT + box + DOT + "box";
	}

	private Map<String, Document> processMorphs(Collection<Node> nodes, InputStream rulesInput) {
		Map<String, Document> map = new HashMap();
		RuleEngine ruleEngine = createRuleEngine(rulesInput);
		for (Node node : nodes) {
			if (node.isCase() || node.isAnonymous()) continue;
			renderNode(map, ruleEngine, node);
			renderFacetTargets(map, ruleEngine, node);
		}
		return map;
	}

	private void renderFacetTargets(Map<String, Document> map, RuleEngine ruleEngine, Node node) {
		for (FacetTarget facetTarget : node.getFacetTargets()) {
			Document document = new Document();
			Map.Entry<String, Frame> morphFrame = new MorphFrameCreator(conf.getProject(), conf.getLanguage(), conf.getLocale()).create(facetTarget);
			ruleEngine.render(morphFrame.getValue(), document);
			map.put(morphFrame.getKey(), document);
		}
	}

	private void renderNode(Map<String, Document> map, RuleEngine ruleEngine, Node node) {
		Document document = new Document();
		Map.Entry<String, Frame> morphFrame =
			new MorphFrameCreator(conf.getProject(), conf.getLanguage(), conf.getLocale()).create(node);
		ruleEngine.render(morphFrame.getValue(), document);
		map.put(morphFrame.getKey(), document);
	}

	private Formatter buildReferenceFormatter() {
		return new Formatter() {
			@Override
			public Object format(Object value) {
				String val = value.toString();
				if (!val.contains(DOT)) return val.substring(0, 1).toUpperCase() + val.substring(1);
				return buildMorphPath(getMorphPath(DOT) + DOT + val);
			}
		};
	}

	private Formatter buildDateFormatter() {
		return new Formatter() {
			@Override
			public Object format(Object value) {
				String val = value.toString();
				if (!val.contains("/")) return value;
				return val.replace("/", ", ");
			}
		};
	}

	private void writeBoxUnits(String directory, Map<String, Document> documentMap) {
		File destiny = new File(outFolder, directory);
		destiny.mkdirs();
		for (Map.Entry<String, Document> entry : documentMap.entrySet()) {
			File file = new File(destiny, entry.getKey().replace(DOT, File.separator) + JAVA);
			file.getParentFile().mkdirs();
			try {
				BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
				fileWriter.write(entry.getValue().content());
				fileWriter.close();
			} catch (IOException e) {
				LOG.log(Level.SEVERE, e.getMessage(), e);
			}
		}
	}

	private void writeBox(String boxPath, Document document) {
		File destiny = new File(outFolder, boxPath);
		destiny.mkdirs();
		try {
			File file = new File(destiny, conf.getGeneratedLanguage() + JAVA);
			BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
			fileWriter.write(document.content());
			fileWriter.close();
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	private void writeMorphs(Map<String, Document> documentMap) {
		for (Map.Entry<String, Document> entry : documentMap.entrySet()) {
			File file = new File(outFolder, entry.getKey().replace(DOT, File.separator) + JAVA);
			file.getParentFile().mkdirs();
			try {
				BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
				fileWriter.write(entry.getValue().content());
				fileWriter.close();
			} catch (IOException e) {
				LOG.log(Level.SEVERE, e.getMessage(), e);
			}
		}
	}

	private String buildBoxUnitDirectory() {
		return getBoxUnitPath(File.separator) + File.separator + conf.getProject().toLowerCase() + File.separator + conf.getModule().toLowerCase();
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


	public InputStream getRulesFromResources(String rules) throws TaraException {
		return ResourceManager.getStream("rules/" + rules);
	}

}
