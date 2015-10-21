package tara.compiler.dependencyresolution;

import tara.compiler.model.Model;
import tara.compiler.model.NodeReference;
import tara.language.model.FacetTarget;
import tara.language.model.Node;
import tara.language.model.NodeContainer;
import tara.language.model.Tag;

public class TerminalResolver {

	private final Model model;
	private final int level;

	public TerminalResolver(Model model, int level) {
		this.model = model;
		this.level = level;
	}

	public void resolve() {
		resolveTerminals(model);
	}

	private void resolveTerminals(Node node) {
		for (Node include : node.components()) {
			if (include instanceof NodeReference) continue;
			if (!include.isTerminal() && level > 1) resolveTerminals(include);
			else {
				if (!include.isTerminal()) include.addFlags(Tag.TERMINAL);
				propagateTerminalToInside(include);
			}
		}
	}

	private void propagateTerminalToInside(Node node) {
		for (Node inner : node.components()) {
			if (inner instanceof NodeReference) continue;
			if (!inner.isTerminal()) inner.addFlags(Tag.TERMINAL);
			propagateTerminalToInside(inner);
		}
		for (FacetTarget facetTarget : node.facetTargets()) {
			for (Node inner : facetTarget.components()) {
				if (!inner.isTerminal()) inner.addFlags(Tag.TERMINAL);
				propagateTerminalToInside(inner);
				propagateTerminalToVariables(facetTarget);
			}
		}
		propagateTerminalToVariables(node);
	}

	private void propagateTerminalToVariables(NodeContainer node) {
		node.variables().stream().
			filter(variable -> !variable.isTerminal()).
			forEach(variable -> variable.addFlags(Tag.TERMINAL));
	}
}
