package siani.tara.compiler.semantic.wrappers;

import siani.tara.compiler.model.impl.Model;
import siani.tara.semantic.model.Facet;
import siani.tara.semantic.model.FacetTarget;
import siani.tara.semantic.model.Node;
import siani.tara.semantic.model.Parameter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LanguageRoot implements Node {

	Model model;

	public LanguageRoot(Model model) {
		this.model = model;
	}

	@Override
	public Node context() {
		return null;
	}

	@Override
	public String type() {
		return "";
	}

	@Override
	public boolean isReference() {
		return false;
	}

	@Override
	public void type(String type) {

	}

	@Override
	public String[] secondaryTypes() {
		return new String[0];
	}

	@Override
	public String name() {
		return null;
	}

	@Override
	public Node parent() {
		return null;
	}

	@Override
	public boolean hasSubs() {
		return false;
	}

	@Override
	public Long address() {
		return Long.MIN_VALUE;
	}

	@Override
	public String[] annotations() {
		return new String[0];
	}

	@Override
	public void annotations(String... annotations) {
	}

	@Override
	public Facet[] facets() {
		return new Facet[0];
	}

	@Override
	public FacetTarget[] facetTargets() {
		return new FacetTarget[0];
	}

	@Override
	public Parameter[] parameters() {
		return new Parameter[0];
	}

	@Override
	public Node[] includes() {
		return wrap(model.getIncludedNodes());
	}

	private Node[] wrap(Collection<siani.tara.compiler.model.Node> nodes) {
		List<LanguageNode> languageNodes = new ArrayList<>();
		for (siani.tara.compiler.model.Node node : nodes)
			languageNodes.add(new LanguageNode(node));
		return languageNodes.toArray(new LanguageNode[languageNodes.size()]);
	}
}
