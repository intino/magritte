package siani.tara.semantic;

import siani.tara.semantic.model.*;

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
	public Node context() {
		return null;
	}

	@Override
	public Node[] includes() {
		return includes.toArray(new Node[includes.size()]);
	}

	@Override
	public Variable[] variables() {
		return new Variable[0];
	}
}
