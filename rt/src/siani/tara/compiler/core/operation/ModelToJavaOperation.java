package siani.tara.compiler.core.operation;

import org.siani.itrules.*;
import org.siani.itrules.Formatter;
import siani.tara.compiler.codegeneration.FrameCreator;
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
import static siani.tara.lang.Annotation.TERMINAL;

public class ModelToJavaOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(ModelToJavaOperation.class.getName());
	private static final String BOX_ITR = "Box.itr";
	private static final String MORPH_ITR = "Morph.itr";
	private static final String JAVA = ".java";
	private final CompilationUnit compilationUnit;
	private FrameCreator creator;
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
		creator = new FrameCreator(model);
		List<List<Node>> groupByBox = groupByBox(model.getTreeModel());
		try {
			writeBoxes(getBoxPath(File.separator), createBoxes(groupByBox));
			if (!model.isTerminal())
				writeMorphs(createMorphs());
		} catch (TaraException e) {
			LOG.severe("Error during java model generation: " + e.getMessage());
			throw new CompilationFailedException(compilationUnit.getPhase(), compilationUnit, e);
		}
	}

	private Map<String, Document> createBoxes(List<List<Node>> groupByBox) throws TaraException {
		File file = new File(rulesFolder, BOX_ITR);
		InputStream stream;
		if (!file.exists()) {
			LOG.log(Level.INFO, "User Box.itr rules file not found. Trying use default");
			stream = getRulesFromResources(BOX_ITR);
		} else stream = getRulesInput(file);
		if (stream == null) {
			LOG.log(Level.SEVERE, "Box.itr rules file not found.");
			throw new TaraException("Box.itr rules file not found.");
		}
		return processBoxes(groupByBox, stream);
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
		Set<Node> set = new HashSet<>();
		getRootNodes(model, set);
		return processMorphs(set, stream);
	}

	private void getRootNodes(Model model, Set<Node> list) {
		addRootAndSubs(model.getTreeModel(), list);
		addAggregated(model.getNodeTable(), list);
	}

	private void addAggregated(Map<String, Node> nodeTable, Set<Node> list) {
		for (Node node : nodeTable.values())
			if (node.is(DeclaredNode.class) && node.isAggregated()) list.add(node);
	}

	private void addRootAndSubs(Collection<Node> treeModel, Set<Node> list) {
		for (Node node : treeModel) {
			list.add(node);
			Node[] concepts = node.getSubConcepts();
			if (concepts.length > 0) {
				Collections.addAll(list, concepts);
				addRootAndSubs(Arrays.asList(concepts), list);
			}
		}
	}

	private Map<String, Document> processMorphs(Collection<Node> nodes, InputStream rulesInput) {
		Map<String, Document> map = new HashMap();
		RuleEngine ruleEngine = new RuleEngine(new TemplateReader(rulesInput).read());
		addReferenceFormatter(ruleEngine);
		for (Node node : nodes) {
			if (!node.getModelOwner().equals(model.getModelName())) continue;
			Document document = new Document();
			Map.Entry<String, Frame> morphFrame = creator.createMorphFrame(node);
			ruleEngine.render(morphFrame.getValue(), document);
			map.put(morphFrame.getKey(), document);
		}
		return map;
	}

	private void addReferenceFormatter(RuleEngine ruleEngine) {
		ruleEngine.register("reference", new Formatter() {
			@Override
			public Object format(Object value) {
				String val = value.toString();
				if (!val.contains(".")) return val.substring(0, 1).toUpperCase() + val.substring(1);
				return getMorphPath(".") + "." + buildMorphPath(val);
			}
		});
	}

	private Map<String, Document> processBoxes(List<List<Node>> groupByBox, InputStream rulesInput) {
		Map<String, Document> map = new HashMap();
		RuleEngine ruleEngine = new RuleEngine(new TemplateReader(rulesInput).read());
		for (List<Node> nodes : groupByBox) {
			Document document = new Document();
			ruleEngine.render(creator.createBoxFrame(nodes, collectParentBoxes(nodes)), document);
			map.put(composeBoxName(nodes.get(0).getFile()), document);
		}
		return map;
	}

	private String composeBoxName(String file) {
		return NameFormatter.camelCase(model.getModelName().replace(".", "_") + "_" +
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
				e.printStackTrace();
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
			if (node.getObject().is(TERMINAL) && !node.getModelOwner().equals(model.getModelName())) continue;
			boxes.add(buildFileName(parent.searchNode(node.getObject().getMetaQN()).getFile(), parent.getModelName()));
		}
		return boxes;
	}

	private List<List<Node>> groupByBox(NodeTree treeModel) {
		Map<String, List<Node>> nodes = new HashMap();
		for (Node node : treeModel) {
			if (!model.getModelName().equals(node.getModelOwner())) continue;
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
			LOG.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public InputStream getRulesFromResources(String rules) throws TaraException {
		return ResourceManager.getStream("rules/" + rules);
	}
}
