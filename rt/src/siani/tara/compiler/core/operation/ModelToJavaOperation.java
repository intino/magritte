package siani.tara.compiler.core.operation;

import org.siani.itrules.*;
import org.siani.itrules.Formatter;
import siani.tara.compiler.codegeneration.BoxFrameCreator;
import siani.tara.compiler.codegeneration.MorphFrameCreator;
import siani.tara.compiler.codegeneration.NameFormatter;
import siani.tara.compiler.codegeneration.ResourceManager;
import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.compiler.core.operation.model.ModelOperation;
import siani.tara.lang.DeclaredNode;
import siani.tara.lang.Model;
import siani.tara.lang.Node;
import siani.tara.lang.NodeTree;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static siani.tara.compiler.codegeneration.NameFormatter.*;
import static siani.tara.lang.Annotation.CASE;
import static siani.tara.lang.Annotation.TERMINAL;

public class ModelToJavaOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(ModelToJavaOperation.class.getName());
	private static final String BOX_ITR = "Box.itr";
	private static final String MORPH_ITR = "Morph.itr";
	private static final String JAVA = ".java";
	private final CompilationUnit compilationUnit;
	private Model model;
	private File rulesFolder;
	private File outFolder;

	public ModelToJavaOperation(CompilationUnit compilationUnit) {
		super();
		this.compilationUnit = compilationUnit;
		rulesFolder = compilationUnit.getConfiguration().getRulesDirectory();
		outFolder = compilationUnit.getConfiguration().getOutDirectory();
	}

	@Override
	public void call(Model model) throws CompilationFailedException {
		this.model = model;
		List<List<Node>> groupByBox = groupByBox(model.getTreeModel());
		try {
			writeBoxes(getBoxPath(File.separator), createBoxes(groupByBox));
			if (!model.isTerminal()) writeMorphs(createMorphs());
		} catch (TaraException e) {
			LOG.severe("Error during java model generation: " + e.getMessage());
			throw new CompilationFailedException(compilationUnit.getPhase(), compilationUnit, e);
		}
	}

	private Map<String, Document> createBoxes(List<List<Node>> groupByBox) throws TaraException {
		File file = new File(rulesFolder, BOX_ITR);
		return processBoxes(groupByBox, loadRules(file));
	}

	private InputStream loadRules(File file) throws TaraException {
		InputStream stream;
		if (!file.exists()) {
			LOG.log(Level.INFO, "User Box.itr rules file not found. Trying use default");
			stream = getRulesFromResources(BOX_ITR);
		} else stream = getRulesInput(file);
		if (stream == null) {
			LOG.log(Level.SEVERE, "Box.itr rules file not found.");
			throw new TaraException("Box.itr rules file not found.");
		}
		return stream;
	}

	private Map<String, Document> createMorphs() throws TaraException {
		File file = new File(rulesFolder, MORPH_ITR);
		InputStream stream;
		if (!file.exists()) {
			LOG.log(Level.INFO, "User Morph.itr rules file not found. Trying use default");
			stream = getRulesFromResources(MORPH_ITR);
		} else stream = getRulesInput(file);
		if (stream == null) {
			LOG.log(Level.SEVERE, "Morph.itr rules file not found.");
			throw new TaraException("Morph.itr rules file not found.");
		}
		return processMorphs(getRootNodes(model), stream);
	}

	private Set<Node> getRootNodes(Model model) {
		Set<Node> list = new HashSet<>();
		addRootAndSubs(model.getTreeModel(), list);
		addAggregated(model.getNodeTable(), list);
		return list;
	}

	private void addAggregated(List<Node> nodeTable, Set<Node> list) {
		for (Node node : nodeTable)
			if (node.is(DeclaredNode.class) && node.isAggregated()) list.add(node);
	}

	private void addRootAndSubs(Collection<Node> treeModel, Set<Node> list) {
		for (Node node : treeModel) {
			list.add(node);
			Node[] concepts = node.getSubNodes();
			if (concepts.length > 0) {
				Collections.addAll(list, concepts);
				addRootAndSubs(Arrays.asList(concepts), list);
			}
		}
	}

	private Map<String, Document> processBoxes(List<List<Node>> groupByBox, InputStream rulesInput) {
		Map<String, Document> map = new HashMap();
		RuleEngine ruleEngine = new RuleEngine(new TemplateReader(rulesInput).read());
		ruleEngine.register("date", buildDateFormatter());
		ruleEngine.register("string", buildStringFormatter());
		for (List<Node> nodes : groupByBox) {
			Document document = new Document();
			String project = compilationUnit.getConfiguration().getProject();
			ruleEngine.render(new BoxFrameCreator(project, model).create(nodes, collectParentBoxes(nodes)), document);
			map.put(composeBoxName(nodes.get(0).getFile()), document);
		}
		return map;
	}

	private Map<String, Document> processMorphs(Collection<Node> nodes, InputStream rulesInput) {
		Map<String, Document> map = new HashMap();
		RuleEngine ruleEngine = new RuleEngine(new TemplateReader(rulesInput).read(), model.getLanguage());
		ruleEngine.register("reference", buildReferenceFormatter());
		for (Node node : nodes) {
			if (!node.getModelOwner().equals(model.getName()) || node.is(CASE)) continue;
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
				if (!val.contains(".")) return val.substring(0, 1).toUpperCase() + val.substring(1);
				return buildMorphPath(getMorphPath(".") + "." + val)
					.replace("[", "").replace("]", "").replaceAll(Node.LINK, "")
					.replaceAll(Node.IN_FACET_TARGET, "").replaceAll(Node.ANONYMOUS, "");
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

	private Formatter buildStringFormatter() {
		return new Formatter() {
			@Override
			public Object format(Object value) {
				String val = value.toString();
				if (val.isEmpty()) return val;
				if (val.startsWith("\n")) return transformMultiLineString(val);
				return val;
			}

			private String transformMultiLineString(String value) {
				String val = value.replace("\r", "");
				int i = value.indexOf("-");
				String indent = value.substring(0, i).replace("\t", "    ").replace("\r", "");
				val = val.replace(indent, "\n").trim().replace("\n", "\" +\n\"");
				if (val.startsWith("---")) {
					val = val.substring(val.indexOf("+") + 2);
					val = val.substring(0, val.lastIndexOf("+") - 1);
				}
				return val.replaceFirst("\"", "").substring(0, val.lastIndexOf("\"") - 1);
			}
		};
	}

	private String composeBoxName(String file) {
		return NameFormatter.camelCase(model.getName().replace(".", "_") + "_" +
			file.substring(file.lastIndexOf(File.separator) + 1, file.lastIndexOf(".")) + "Box", "_");
	}

	private void writeBoxes(String directory, Map<String, Document> documentMap) {
		File destiny = new File(outFolder, directory);
		destiny.mkdirs();
		for (Map.Entry<String, Document> entry : documentMap.entrySet()) {
			File file = new File(destiny, entry.getKey().replace(".", File.separator) + JAVA);
			file.getParentFile().mkdirs();
			try {
				BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
				fileWriter.write(entry.getValue().content());
				fileWriter.close();
			} catch (IOException e) {
				LOG.severe(e.getMessage());
			}
		}
	}

	private void writeMorphs(Map<String, Document> documentMap) {
		for (Map.Entry<String, Document> entry : documentMap.entrySet()) {
			File file = new File(outFolder, entry.getKey().replace(".", File.separator) + JAVA);
			file.getParentFile().mkdirs();
			try {
				BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
				fileWriter.write(entry.getValue().content());
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private Collection<String> collectParentBoxes(List<Node> nodes) {
		Model parent = model.getParentModel();
		if (parent == null) return Collections.EMPTY_LIST;
		Set<String> boxes = new HashSet<>();
		for (Node node : nodes) {
			if (node.getObject().is(TERMINAL) && !node.getModelOwner().equals(model.getName())) continue;
			boxes.add(buildFileName(parent.searchNode(node.getObject().getMetaQN()).getFile(), parent.getName()));
		}
		return boxes;
	}

	private List<List<Node>> groupByBox(NodeTree treeModel) {
		Map<String, List<Node>> nodes = new HashMap();
		for (Node node : treeModel) {
			if (!model.getName().equals(node.getModelOwner())) continue;
			if (!nodes.containsKey(node.getFile()))
				nodes.put(node.getFile(), new ArrayList<Node>());
			nodes.get(node.getFile()).add(node);
		}
		return pack(nodes);
	}

	private List<List<Node>> pack(Map<String, List<Node>> nodes) {
		List<List<Node>> lists = new ArrayList<>();
		for (List<Node> nodeList : nodes.values())
			lists.add(nodeList);
		return lists;
	}

	private FileInputStream getRulesInput(File ruleFile) {
		try {
			return new FileInputStream(ruleFile);
		} catch (FileNotFoundException e) {
			LOG.severe(e.getMessage());
			return null;
		}
	}

	public InputStream getRulesFromResources(String rules) throws TaraException {
		return ResourceManager.getStream("rules/" + rules);
	}
}
