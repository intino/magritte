package tara.compiler.core.operation.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import tara.compiler.core.CompilationUnit;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.model.Model;
import tara.compiler.refactor.RefactorsManager;
import tara.io.refactor.Refactors;
import tara.io.refactor.RefactorsDeserializer;
import tara.lang.model.Facet;
import tara.lang.model.Node;
import tara.lang.model.NodeContainer;
import tara.lang.model.Refactorizable;

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

	private static final String REFACTORS = "refactors";
	private final File taraDirectory;
	private final boolean isMake;
	private final CompilerConfiguration conf;
	private final int level;
	private Map<String, String> anchors;
	private Refactors refactors;

	public RefactorHistoryOperation(CompilationUnit unit) {
		this.conf = unit.getConfiguration();
		this.isMake = unit.getConfiguration().isMake();
		this.taraDirectory = unit.getConfiguration().getTaraDirectory();
		this.level = unit.getConfiguration().level();
		this.anchors = loadLastAnchors();
		this.refactors = loadRefactors();
	}

	@Override
	public void call(Model model) throws CompilationFailedException {
		if (!isMake) return;
		if (conf.isVerbose()) System.out.println(PRESENTABLE_MESSAGE + "[" + conf.getModule() + "]" + " Generating Refactor info...");
		List<Refactorizable> nodes = collectAllAnchoredNodes(model);
		RefactorsManager manager = new RefactorsManager(getAnchorsFile(), getRefactorsFile(), anchors, refactors);
		if (anchors != null && !anchors.isEmpty()) manager.commitRefactors(nodes);
		if (conf.level() != 0) manager.updateAnchors(nodes);
	}

	private List<Refactorizable> collectAllAnchoredNodes(Node node) {
		List<Refactorizable> list = new ArrayList<>();
		if (node.anchor() != null && !node.isReference()) list.add(node);
		addComponents(list, node);
		addVariables(list, node);
		for (Facet facet : node.facets()) {
			addComponents(list, facet);
			addVariables(list, node);
		}
		return list;
	}

	private void addComponents(List<Refactorizable> list, NodeContainer node) {
		node.components().stream().filter(component -> !component.isReference()).forEach(component -> list.addAll(collectAllAnchoredNodes(component)));
	}

	private void addVariables(List<Refactorizable> list, NodeContainer node) {
		list.addAll(node.variables());
	}

	private Map<String, String> loadLastAnchors() {
		File file = getAnchorsFile();
		if (!file.exists()) return new LinkedHashMap<>();
		return (Map<String, String>) fromJson(file, new TypeToken<Map<String, String>>() {
		}.getType());
	}

	private Refactors loadRefactors() {
		final File file = getRefactorsFile();
		if (!file.exists()) return new Refactors();
		return RefactorsDeserializer.refactorFrom(file);
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
		return new File(taraDirectory, REFACTORS + File.separator + (level == 2 ? "engine.json" : "domain.json"));
	}

	private File getRefactorsFile() {
		return new File(taraDirectory, REFACTORS + File.separator + (level == 2 ? "engine" : "domain"));
	}
}
