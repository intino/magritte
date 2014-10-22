package siani.tara.compiler.core.operation;

import org.siani.itrules.Document;
import org.siani.itrules.Frame;
import org.siani.itrules.RuleEngine;
import siani.tara.compiler.codegeneration.FrameCreator;
import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.compiler.core.operation.model.ModelOperation;
import siani.tara.lang.Model;
import siani.tara.lang.Node;
import siani.tara.lang.NodeTree;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModelToJavaOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(ModelToJavaOperation.class.getName());
	private static final String BOX_ITR = "Box.itr";
	private static final String JAVA = ".java";
	private final CompilationUnit compilationUnit;
	private File rulesFolder;
	private File outFolder;

	public ModelToJavaOperation(CompilationUnit compilationUnit) {
		super();
		this.compilationUnit = compilationUnit;
		rulesFolder = compilationUnit.getConfiguration().getRulesDirectory();
		outFolder = compilationUnit.getConfiguration().getTargetDirectory();
	}

	@Override
	public void call(Model model) throws CompilationFailedException {
//		List<List<Node>> groupByBox = groupByBox(model.getTreeModel());
//		try {
//			writeDocuments(createBoxes(groupByBox));
//			writeDocuments(createModel(FrameCreator.create(model)));
//		} catch (TaraException e) {
//			LOG.severe("Error during dependency resolution: " + e.getMessage());
//			throw new CompilationFailedException(compilationUnit.getPhase(), compilationUnit, e);
//		}

	}

	private Map<String, Document> createModel(Frame frame) {
		return Collections.EMPTY_MAP; //TODO
	}

	private void writeDocuments(Map<String, Document> documentMap) {
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

	private Map<String, Document> createBoxes(List<List<Node>> groupByBox) throws TaraException {
		File file = new File(rulesFolder, BOX_ITR);
		LOG.log(Level.SEVERE, "User Box.itr rules file not found. Trying use default");
		if (!file.exists()) file = getRulesFromResources();
		if (!file.exists()) {
			LOG.log(Level.SEVERE, "Box.itr rules file not found.");
			throw new TaraException("Box.itr rules file not found.");
		}
		return processBoxes(groupByBox, getRulesInput(file));
	}

	private Map<String, Document> processBoxes(List<List<Node>> groupByBox, FileInputStream rulesInput) {
		Map<String, Document> map = new HashMap();
		RuleEngine ruleEngine = new RuleEngine(rulesInput);
		for (List<Node> nodes : groupByBox) {
			Document document = new Document();
			ruleEngine.render(FrameCreator.createBoxFrame(nodes), document);
			map.put(nodes.get(0).getBox(), document);
		}
		return map;
	}

	private List<List<Node>> groupByBox(NodeTree treeModel) {
		Map<String, List<Node>> nodes = new HashMap();
		for (Node node : treeModel) {
			if (!nodes.containsKey(node.getBox()))
				nodes.put(node.getBox(), new ArrayList<Node>());
			nodes.get(node.getBox()).add(node);
		}
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

	public File getRulesFromResources() {
		return new File(this.getClass().getResource("/templates/" + BOX_ITR).getFile());
	}
}
