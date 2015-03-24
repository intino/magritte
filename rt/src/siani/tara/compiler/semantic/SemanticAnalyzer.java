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
import siani.tara.compiler.semantic.wrappers.LanguageRoot;
import siani.tara.semantic.SemanticException;

public class SemanticAnalyzer {
	private final Model model;
	private final Language language;
	private final Resolver resolver;

	public SemanticAnalyzer(Model model, Language language) {
		this.model = model;
		this.language = language;
		resolver = new Resolver(language);
	}

	public void analyze() throws SemanticException {
		resolveTypes(model);
		new Checker(language).check(new LanguageRoot(model));
	}

	private void resolveTypes(Node node) {
		for (Node include : node.getIncludedNodes()) {
			resolver.resolve(wrap(include));
			if (include instanceof NodeImpl)
				resolveTypes(include);
		}
		if (node instanceof NodeImpl) {
			for (FacetTarget facetTarget : node.getFacetTargets()) {
				for (Node include : facetTarget.getIncludedNodes()) {
					resolver.resolve(wrap(include));
					if (include instanceof NodeImpl)
						resolveTypes(include);
				}
			}
			for (Facet facet : node.getFacets()) {
				for (Node include : facet.getIncludedNodes()) {
					resolver.resolve(wrap(include));
					if (include instanceof NodeImpl)
						resolveTypes(include);
				}
			}
		}
	}

	private LanguageNode wrap(Node node) {
		return node instanceof NodeImpl ? new LanguageNode((NodeImpl) node) : new LanguageNodeReference((NodeReference) node);
	}
}
