package tara.compiler.core.operation.model;

import tara.compiler.core.CompilationUnit;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.model.Model;
import tara.compiler.refactor.Refactors;
import tara.lang.model.Facet;
import tara.lang.model.FacetTarget;
import tara.lang.model.Node;
import tara.lang.model.NodeContainer;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RefactorHistoryOperation extends ModelOperation {

	private final CompilerConfiguration conf;
	private final Map<String, String> lastCompilation;
	private final Refactors refactors;

	public RefactorHistoryOperation(CompilationUnit unit) {
		this.conf = unit.getConfiguration();
		lastCompilation = loadLastMake(this.conf.getRefactorsPath(), conf.getGeneratedLanguage());
		refactors = loadRefactors(this.conf.getRefactorsPath(), conf.getGeneratedLanguage());
	}

	@Override
	public void call(Model model) throws CompilationFailedException {
		if (!conf.isMake()) return;
		List<Node> nodes = collectAllAnchoredNodes(model);
	}

	private List<Node> collectAllAnchoredNodes(Node node) {
		List<Node> list = new ArrayList<>();
		if (node.anchor() != null)
			list.add(node);
		addComponents(list, node);
		for (Facet facet : node.facets()) addComponents(list, facet);
		for (FacetTarget target : node.facetTargets()) addComponents(list, target);
		return list;
	}

	private void addComponents(List<Node> list, NodeContainer facet) {
		for (Node component : facet.components()) list.addAll(collectAllAnchoredNodes(component));
	}

	private Map<String, String> loadLastMake(File refactorsPath, String generatedLanguage) {
		File file = new File(refactorsPath, generatedLanguage + File.separator + "anchors.json");
		if (!file.exists()) return new LinkedHashMap<>();
		return null;
	}

	private Refactors loadRefactors(File refactorsPath, String generatedLanguage) {
		return null;
	}

}
