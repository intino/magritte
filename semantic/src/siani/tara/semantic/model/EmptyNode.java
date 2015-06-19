package siani.tara.semantic.model;

import java.util.Collections;
import java.util.List;

public class EmptyNode implements Node {
	@Override
	public Node context() {
		return null;
	}

	@Override
	public String type() {
		return null;
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
		return "empty";
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
		return null;
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
		return Collections.emptyList();
	}

	@Override
	public List<Variable> variables() {
		return Collections.emptyList();
	}


	@Override
	public String toString() {
		return "empty";
	}
}
