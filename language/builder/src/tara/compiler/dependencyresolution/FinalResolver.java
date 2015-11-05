package tara.compiler.dependencyresolution;

import tara.compiler.model.Model;
import tara.compiler.model.NodeReference;
import tara.lang.model.Node;
import tara.lang.model.Tag;

public class FinalResolver {

	private final Model model;

	public FinalResolver(Model model) {
		this.model = model;
	}

	public void resolve() {
		resolveFinals(model);
	}

	private void resolveFinals(Node node) {
		for (Node include : node.components()) {
			if (include instanceof NodeReference || !node.isFinal()) continue;
			propagateFinalToVariables(include);
			resolveFinals(include);
		}
	}

	private void propagateFinalToVariables(Node node) {
		node.variables().stream().filter(variable -> !variable.isFinal()).forEach(variable -> variable.addFlags(Tag.FINAL));
	}
}
