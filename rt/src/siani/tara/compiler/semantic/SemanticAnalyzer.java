package siani.tara.compiler.semantic;

import siani.tara.Checker;
import siani.tara.Language;
import siani.tara.Resolver;
import siani.tara.compiler.model.Facet;
import siani.tara.compiler.model.FacetTarget;
import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.compiler.model.impl.NodeReference;
import siani.tara.compiler.semantic.wrappers.LanguageNode;
import siani.tara.compiler.semantic.wrappers.LanguageNodeReference;
import siani.tara.semantic.SemanticException;

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
		check(model);
	}

	private void resolveTypes(Node node) {
		for (Node include : node.getIncludedNodes()) resolveNode(include);
		if (node instanceof NodeImpl) {
			for (FacetTarget facetTarget : node.getFacetTargets())
				for (Node include : facetTarget.getIncludedNodes()) resolveNode(include);
			for (Facet facet : node.getFacets())
				for (Node include : facet.getIncludedNodes()) resolveNode(include);
		}
	}

	private void check(Node node) throws SemanticException {
		for (Node include : node.getIncludedNodes()) checkNode(include);
		if (node instanceof NodeImpl) {
			for (FacetTarget facetTarget : node.getFacetTargets())
				for (Node include : facetTarget.getIncludedNodes()) checkNode(include);
			for (Facet facet : node.getFacets())
				for (Node include : facet.getIncludedNodes()) checkNode(include);
		}
	}

	private void resolveNode(Node include) {
		resolver.resolve(wrap(include));
		if (include instanceof NodeImpl)
			resolveTypes(include);
	}

	private void checkNode(Node include) throws SemanticException {
		checker.check(wrap(include));
		if (include instanceof NodeImpl)
			check(include);
	}

	private LanguageNode wrap(Node node) {
		return node instanceof NodeImpl ? new LanguageNode((NodeImpl) node) : new LanguageNodeReference((NodeReference) node);
	}
}
