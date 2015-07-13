package siani.tara.compiler.dependencyresolution;

import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeReference;

public class FinalResolver {

	private final Model model;

	public FinalResolver(Model model) {
		this.model = model;
	}

	public void resolve() {
		resolveFinals(model);
	}

	private void resolveFinals(Node node) {
		for (Node include : node.getIncludedNodes()) {
			if (include instanceof NodeReference || !node.isFinal()) continue;
			propagateFinalToVariables(include);
			resolveFinals(include);
		}
	}

	private void propagateFinalToVariables(Node node) {
		node.getVariables().stream().filter(variable -> !variable.isFinal()).forEach(variable -> variable.addFlags("final"));
	}
}
