package siani.tara.compiler.semantic.wrappers;

import siani.tara.compiler.model.Element;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.semantic.model.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class LanguageRoot extends LanguageElement implements Node {

	Model model;
	private Node[] includes;

	public LanguageRoot(Model model) {
		this.model = model;
		includes = wrap(model.getIncludedNodes());
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
	public String[] types() {
		return new String[0];
	}

	@Override
	public String name() {
		return "";
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
	public String plate() {
		return "";
	}

	@Override
	public String[] annotations() {
		return new String[0];
	}

	@Override
	public String[] flags() {
		return new String[0];
	}

	@Override
	public void flags(String... flags) {
	}

	@Override
	public void annotations(String... annotations) {
	}

	@Override
	public void moveToTheTop() {
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
		return includes;
	}

	@Override
	public Variable[] variables() {
		return new Variable[0];
	}

	private Node[] wrap(Collection<siani.tara.compiler.model.Node> nodes) {
		List<LanguageNode> languageNodes = nodes.stream().map(node -> new LanguageNode((NodeImpl) node)).collect(Collectors.toList());
		return languageNodes.toArray(new LanguageNode[languageNodes.size()]);
	}

	@Override
	public Element element() {
		return model;
	}
}
