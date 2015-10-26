package tara.compiler.semantic;

import tara.Checker;
import tara.Language;
import tara.Resolver;
import tara.compiler.model.Model;
import tara.compiler.model.NodeImpl;
import tara.lang.model.Facet;
import tara.lang.model.FacetTarget;
import tara.lang.model.Node;
import tara.lang.semantics.SemanticException;

import java.util.ArrayList;

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
		checker.check(model);
		check(model);
	}

	private void resolveTypes(Node node) {
		node.components().forEach(this::resolveNode);
		if (node instanceof NodeImpl) {
			for (FacetTarget facetTarget : node.facetTargets())
				facetTarget.components().forEach(this::resolveNode);
			for (Facet facet : node.facets())
				facet.components().forEach(this::resolveNode);
		}
	}

	private void check(Node node) throws SemanticException {
		for (Node include : new ArrayList<>(node.components()))
			checkNode(include);
		if (node instanceof NodeImpl) {
			for (FacetTarget facetTarget : node.facetTargets())
				for (Node include : facetTarget.components()) checkNode(include);
			for (Facet facet : node.facets())
				for (Node include : facet.components())
					checkNode(include);
		}
	}

	private void resolveNode(Node include) {
		resolver.resolve(include);
		if (include instanceof NodeImpl)
			resolveTypes(include);
	}

	private void checkNode(Node include) throws SemanticException {
		checker.check(include);
		if (include instanceof NodeImpl) check(include);
	}

}
