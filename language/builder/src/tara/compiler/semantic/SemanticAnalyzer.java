package tara.compiler.semantic;

import tara.Checker;
import tara.Language;
import tara.Resolver;
import tara.compiler.model.Model;
import tara.compiler.model.NodeImpl;
import tara.lang.model.Facet;
import tara.lang.model.FacetTarget;
import tara.lang.model.Node;
import tara.lang.semantics.errorcollector.SemanticException;
import tara.lang.semantics.errorcollector.SemanticFatalException;

import java.util.ArrayList;
import java.util.List;

public class SemanticAnalyzer {
	private final Model model;
	private final boolean dynamicLoad;
	private final Resolver resolver;
	private Checker checker;
	private AnchorChecker anchorChecker = new AnchorChecker();
	List<SemanticException> notifications = new ArrayList<>();

	public SemanticAnalyzer(Model model, Language language, boolean dynamicLoad) {
		this.model = model;
		this.dynamicLoad = dynamicLoad;
		resolver = new Resolver(language);
		checker = new Checker(language);
	}

	public void analyze() throws SemanticFatalException {
		resolveTypes(model);
		checkNode(model);
		check(model);
		if (!notifications.isEmpty()) throw new SemanticFatalException(notifications);
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

	private void check(Node node) {
		new ArrayList<>(node.components()).forEach(this::checkNode);
		if (node instanceof NodeImpl) {
			for (FacetTarget facetTarget : node.facetTargets())
				facetTarget.components().forEach(this::checkNode);
			for (Facet facet : node.facets())
				facet.components().forEach(this::checkNode);
		}
	}

	private void resolveNode(Node node) {
		resolver.resolve(node);
		if (node instanceof NodeImpl)
			resolveTypes(node);
	}

	private void checkNode(Node node) {
		try {
			if (dynamicLoad) anchorChecker.check(node);
			checker.check(node);
			if (node instanceof NodeImpl) check(node);
		} catch (SemanticFatalException e) {
			notifications.addAll(e.exceptions());
		}
	}

}
