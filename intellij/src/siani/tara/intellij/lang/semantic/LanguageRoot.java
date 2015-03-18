package siani.tara.intellij.lang.semantic;

import siani.tara.intellij.lang.psi.TaraModel;
import siani.tara.model.Facet;
import siani.tara.model.FacetTarget;
import siani.tara.model.Node;
import siani.tara.model.Parameter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LanguageRoot implements Node {

	TaraModel model;

	public LanguageRoot(TaraModel model) {
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
	public void type(String type) {

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
	public Long address() {
		return null;
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
		return wrap(model.getNodes());
	}

	private Node[] wrap(Collection<siani.tara.intellij.lang.psi.Node> nodes) {
		List<LanguageNode> languageNodes = new ArrayList<>();
		for (siani.tara.intellij.lang.psi.Node node : nodes) languageNodes.add(new LanguageNode(node));
		return languageNodes.toArray(new LanguageNode[languageNodes.size()]);
	}
}
