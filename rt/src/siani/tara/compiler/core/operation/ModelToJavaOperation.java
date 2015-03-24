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
import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.impl.Model;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static siani.tara.compiler.codegeneration.magritte.NameFormatter.*;

public class ModelToJavaOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(ModelToJavaOperation.class.getName());
	private static final String BOX_ITR = "BoxUnit.itr";
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
		this.model = model;
		List<List<Node>> groupByBox = groupByBox(model);
		try {
			writeBoxes(getBoxPath(File.separator), createBoxes(groupByBox));
			if (!model.isTerminal()) writeMorphs(createMorphs());
		} catch (TaraException e) {
			LOG.log(Level.SEVERE, "Error during java model generation: " + e.getMessage(), e);
			throw new CompilationFailedException(compilationUnit.getPhase(), compilationUnit, e);
		}
	}

	private Map<String, Document> createBoxes(List<List<Node>> groupByBox) throws TaraException {
		return processBoxes(groupByBox, loadRules(BOX_ITR));
	}

	private Map<String, Document> createMorphs() throws TaraException {
		return processMorphs(getRootNodes(model), loadRules(MORPH_ITR));
	}

	private InputStream loadRules(String itr) throws TaraException {
		InputStream stream = getRulesFromResources(itr);
		if (stream == null) {
			LOG.log(Level.SEVERE, itr + ".itr rules file not found.");
			throw new TaraException(itr + ".itr rules file not found.");
		}
		return stream;
	}

	private Set<Node> getRootNodes(Model model) {
		Set<Node> list = new HashSet<>();
		addRootAndSubs(model.getIncludedNodes(), list);
		return list;
	}

	private void addRootAndSubs(Collection<Node> treeModel, Set<Node> list) {
		for (Node node : treeModel) {
			list.add(node);
			Collection<Node> nodes = node.getSubNodes();
			if (!nodes.isEmpty()) {
				list.addAll(nodes);
				addRootAndSubs(nodes, list);
			}
		}
	}

	private Map<String, Document> processBoxes(List<List<Node>> groupByBox, InputStream rulesInput) throws TaraException {
		Map<String, Document> map = new HashMap();
		RuleEngine ruleEngine = new RuleEngine(new ItrRulesReader(rulesInput).read());
		ruleEngine.register("date", buildDateFormatter());
		ruleEngine.register("string", new StringFormatter());
		for (List<Node> nodes : groupByBox) {
			Document document = new Document();
			ruleEngine.render(new BoxFrameCreator(conf, model).create(nodes), document);
			map.put(NameFormatter.buildFileName(((Element) nodes.get(0)).getFile()), document);
		}
		return map;
	}

	private Map<String, Document> processMorphs(Collection<Node> nodes, InputStream rulesInput) {
		Map<String, Document> map = new HashMap();
		RuleEngine ruleEngine = new RuleEngine(new ItrRulesReader(rulesInput).read(), conf.getLocale());
		ruleEngine.register("reference", buildReferenceFormatter());
		for (Node node : nodes) {
			if (!((Element) node).getFile().equals(model.getName()) || node.isCase()) continue;
			Document document = new Document();
			String project = compilationUnit.getConfiguration().getProject();
			Map.Entry<String, Frame> morphFrame = new MorphFrameCreator(project, model).create(node);
			ruleEngine.render(morphFrame.getValue(), document);
			map.put(morphFrame.getKey(), document);
		}
		return map;
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

	private void writeBoxes(String directory, Map<String, Document> documentMap) {
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
