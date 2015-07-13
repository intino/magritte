package tara.semantic;

import tara.semantic.model.Facet;
import tara.semantic.model.FacetTarget;
import tara.semantic.model.Node;

import java.util.ArrayList;
import java.util.List;

class RootDefinition implements Definition {

	private List<ScriptNode> includes = new ArrayList<>();

	@Override
	public Definition name(String name) {
		return this;
	}

	@Override
	public Definition parameter(int position, String name, Object... value) {
		return this;
	}

	@Override
	public Definition annotations(String... annotation) {
		return this;
	}

	@Override
	public Definition flags(String... annotation) {
		return this;
	}

	@Override
	public Definition plate(String address) {
		return this;
	}

	@Override
	public Definition facetTarget(FacetTarget target) {
		return this;
	}

	@Override
	public Definition facet(Facet facet) {
		return this;
	}

	@Override
	public Definition extendsTo(Node parent) {
		return this;
	}


	@Override
	public Definition type(String type) {
		return this;
	}

	@Override
	public Definition include(Node... nodes) {
		for (Node node : nodes) includes.add((ScriptNode) node);
		return this;
	}

	@Override
	public Definition has(Node... node) {
		throw new RuntimeException("Root node cannot have has");
	}

	@Override
	public Node node() {
		return new RootNode(includes);
	}
}
