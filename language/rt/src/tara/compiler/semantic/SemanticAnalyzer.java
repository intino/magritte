package tara.compiler.semantic;

import tara.Checker;
import tara.Language;
import tara.Resolver;
import tara.compiler.model.Facet;
import tara.compiler.model.FacetTarget;
import tara.compiler.model.Node;
import tara.compiler.model.impl.Model;
import tara.compiler.model.impl.NodeImpl;
import tara.compiler.model.impl.NodeReference;
import tara.compiler.semantic.wrappers.LanguageNode;
import tara.compiler.semantic.wrappers.LanguageNodeReference;
import tara.compiler.semantic.wrappers.LanguageRoot;
import tara.semantic.SemanticException;

public class SemanticAnalyzer {
	private final Model model;
	private final Resolver resolver;
	private Checker checker;

	public SemanticAnalyzer(Model model, Language language) {
		this.model = model;
		resolver = new Resolver(language);
		checker = new Checker(language);
	}

	public void analyze() throws SemanticException {
		resolveTypes(model);
		checker.check(wrap(model));
		check(model);
	}

	private void resolveTypes(Node node) {
		node.getIncludedNodes().forEach(this::resolveNode);
		if (node instanceof NodeImpl) {
			for (FacetTarget facetTarget : node.getFacetTargets())
				facetTarget.getIncludedNodes().forEach(this::resolveNode);
			for (Facet facet : node.getFacets())
				facet.getIncludedNodes().forEach(this::resolveNode);
		}
	}

	private void check(Node node) throws SemanticException {
		for (Node include : node.getIncludedNodes())
			checkNode(include);
		if (node instanceof NodeImpl) {
			for (FacetTarget facetTarget : node.getFacetTargets())
				for (Node include : facetTarget.getIncludedNodes()) checkNode(include);
			for (Facet facet : node.getFacets())
				for (Node include : facet.getIncludedNodes())
					checkNode(include);
		}
	}

	private void resolveNode(Node include) {
		resolver.resolve(wrap(include));
		if (include instanceof NodeImpl)
			resolveTypes(include);
	}

	private void checkNode(Node include) throws SemanticException {
		checker.check(wrap(include));
		if (include instanceof NodeImpl) check(include);
	}

	private LanguageNode wrap(Node node) {
		return node instanceof NodeImpl ? new LanguageNode((NodeImpl) node) : new LanguageNodeReference((NodeReference) node);
	}

	private LanguageRoot wrap(Model model) {
		return new LanguageRoot(model);
	}
}
