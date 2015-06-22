package siani.tara.semantic;

import siani.tara.semantic.model.*;

import java.util.Collections;
import java.util.List;

class RootNode extends ScriptNode {

	private final List<ScriptNode> includes;

	RootNode(List<ScriptNode> includes) {
		this.includes = includes;
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
	public Node context() {
		return null;
	}

	@Override
	public List<Node> includes() {
		return Collections.unmodifiableList(includes);
	}

	@Override
	public List<Variable> variables() {
		return Collections.emptyList();
	}
}
