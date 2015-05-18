package siani.tara.semantic.model;

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
	public String[] secondaryTypes() {
		return new String[0];
	}

	@Override
	public String[] types() {
		return new String[0];
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
		return new Node[0];
	}

	@Override
	public Variable[] variables() {
		return new Variable[0];
	}

	@Override
	public String toString() {
		return "empty";
	}
}
