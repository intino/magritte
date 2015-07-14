package tara.compiler.semantic.wrappers;

import tara.compiler.model.impl.Model;
import tara.compiler.model.impl.NodeImpl;
import tara.semantic.model.Facet;
import tara.semantic.model.FacetTarget;
import tara.semantic.model.Node;
import tara.semantic.model.Parameter;
import tara.semantic.model.Variable;

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
		includes = wrap(model.components());
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
	public Node destinyOfReference() {
		return this;
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
	public List<Node> components() {
		return includes;
	}

	@Override
	public List<Variable> variables() {
		return Collections.emptyList();
	}

	private List<Node> wrap(Collection<tara.compiler.model.Node> nodes) {
		return unmodifiableList(nodes.stream().map(node -> new LanguageNode((NodeImpl) node)).collect(Collectors.toList()));
	}

	@Override
	public tara.compiler.model.Element element() {
		return model;
	}
}
