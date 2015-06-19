package siani.tara.compiler.semantic.wrappers;

import siani.tara.compiler.model.Element;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.semantic.model.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;

public class LanguageRoot extends LanguageElement implements Node {

	Model model;
	private List<Node> includes;

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
	public List<String> secondaryTypes() {
		return Collections.emptyList();
	}

	@Override
	public List<String> types() {
		return Collections.emptyList();
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
	public List<String> annotations() {
		return Collections.emptyList();
	}

	@Override
	public List<String> flags() {
		return Collections.emptyList();
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
	public List<Facet> facets() {
		return Collections.emptyList();
	}

	@Override
	public List<FacetTarget> facetTargets() {
		return Collections.emptyList();
	}

	@Override
	public List<Parameter> parameters() {
		return Collections.emptyList();
	}

	@Override
	public List<Node> includes() {
		return includes;
	}

	@Override
	public List<Variable> variables() {
		return Collections.emptyList();
	}

	private List<Node> wrap(Collection<siani.tara.compiler.model.Node> nodes) {
		return unmodifiableList(nodes.stream().map(node -> new LanguageNode((NodeImpl) node)).collect(Collectors.toList()));
	}

	@Override
	public Element element() {
		return model;
	}
}
