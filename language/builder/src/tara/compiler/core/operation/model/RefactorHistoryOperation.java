package tara.compiler.core.operation.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import tara.compiler.core.CompilationUnit;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.model.Model;
import tara.compiler.refactor.RefactorsManager;
import tara.lang.model.Facet;
import tara.lang.model.Node;
import tara.lang.model.NodeContainer;
import tara.lang.refactor.Refactors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static tara.compiler.constants.TaraBuildConstants.PRESENTABLE_MESSAGE;

public class RefactorHistoryOperation extends ModelOperation {

	private static final String ANCHORS_JSON = "anchors.json";
	private static final String REFACTORS_JSON = "refactors.json";
	private static final String FRAMEWORK = "framework";
	private final String generatedLanguage;
	private final File taraDirectory;
	private final boolean isMake;
	private final CompilerConfiguration conf;
	private Map<String, String> anchors;
	private Refactors refactors;

	public RefactorHistoryOperation(CompilationUnit unit) {
		this.conf = unit.getConfiguration();
		this.generatedLanguage = conf.generatedLanguage();
		this.isMake = unit.getConfiguration().isMake();
		this.taraDirectory = unit.getConfiguration().getTaraDirectory();
		this.anchors = loadLastAnchors();
		this.refactors = loadRefactors();
	}

	@Override
	public void call(Model model) throws CompilationFailedException {
		if (!isMake) return;
		if (conf.isVerbose()) System.out.println(PRESENTABLE_MESSAGE + "[" + conf.getModule() + "]" + " Generating Refactor info...");
		List<Node> nodes = collectAllAnchoredNodes(model);
		RefactorsManager manager = new RefactorsManager(getAnchorsFile(), getRefactorsFile(), anchors, refactors);
		if (anchors != null && !anchors.isEmpty()) manager.commitRefactors(nodes);
		if (conf.level() != 0) manager.updateAnchors(nodes);
	}

	private List<Node> collectAllAnchoredNodes(Node node) {
		List<Node> list = new ArrayList<>();
		if (node.anchor() != null && !node.isReference()) list.add(node);
		addComponents(list, node);
		for (Facet facet : node.facets()) addComponents(list, facet);
		return list;
	}

	private void addComponents(List<Node> list, NodeContainer facet) {
		facet.components().stream().filter(component -> !component.isReference()).forEach(component -> list.addAll(collectAllAnchoredNodes(component)));
	}

	private Map<String, String> loadLastAnchors() {
		File file = getAnchorsFile();
		if (!file.exists()) return new LinkedHashMap<>();
		return (Map<String, String>) fromJson(file, new TypeToken<Map<String, String>>() {
		}.getType());
	}

	private Refactors loadRefactors() {
		File file = getRefactorsFile();
		if (!file.exists()) return new Refactors();
		return (Refactors) fromJson(file, new TypeToken<Refactors>() {
		}.getType());
	}

	private Object fromJson(File file, Type type) {
		try {
			Gson gson = new Gson();
			return gson.fromJson(new FileReader(file), type);
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	private File getAnchorsFile() {
		return new File(taraDirectory, FRAMEWORK + File.separator + generatedLanguage + File.separator + ANCHORS_JSON);
	}

	private File getRefactorsFile() {
		return new File(taraDirectory, FRAMEWORK + File.separator + generatedLanguage + File.separator + REFACTORS_JSON);
	}
}
