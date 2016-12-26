package io.intino.tara.compiler.model;

import io.intino.tara.lang.model.FacetTarget;
import io.intino.tara.lang.model.Node;

import java.util.ArrayList;
import java.util.List;

public class FacetTargetImpl implements FacetTarget, Cloneable {

	private String file;
	private int line;
	private List<Constraint> constraints = new ArrayList<>();
	private String target;
	private Node targetNode;
	private Node owner;
	private String language;
	private Node parent;
	private boolean inherited;

	@Override
	public String target() {
		return target;
	}

	@Override
	public void target(String target) {
		this.target = target;
	}

	@Override
	public List<Constraint> constraints() {
		return constraints;
	}

	@Override
	public void constraints(List<String> constraintNames) {
		constraints.clear();
		constraintNames.forEach(c -> this.constraints().add(new FacetConstraint(c)));
	}

	public void setConstraints(List<Constraint> constraints) {
		this.constraints = new ArrayList<>(constraints);
	}

	@Override
	public Node targetNode() {
		return targetNode;
	}

	@Override
	public Node parent() {
		return this.parent;
	}

	@Override
	public boolean inherited() {
		return this.inherited;
	}

	@Override
	public void inherited(boolean inherited) {
		this.inherited = inherited;
	}

	@Override
	public void parent(Node node) {
		this.parent = node;
	}

	@Override
	public void targetNode(Node destiny) {
		this.targetNode = destiny;
	}

	@Override
	public Node owner() {
		return owner;
	}

	public void owner(Node node) {
		this.owner = node;
	}

	@Override
	public String file() {
		return file;
	}

	@Override
	public void file(String file) {
		this.file = file;
	}

	@Override
	public int line() {
		return line;
	}

	@Override
	public void line(int line) {
		this.line = line;
	}

	@Override
	public String toString() {
		return "on " + target;
	}

	@Override
	public String language() {
		return language;
	}

	@Override
	public void language(String language) {
		this.language = language;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	@Override
	public FacetTargetImpl clone() throws CloneNotSupportedException {
		super.clone();
		FacetTargetImpl facetTarget = new FacetTargetImpl();
		facetTarget.file(this.file());
		facetTarget.line(this.line());
		facetTarget.target(this.target);
		facetTarget.parent(this.parent);
		facetTarget.targetNode(this.targetNode);
		List<Constraint> cloned = new ArrayList<>();
		for (Constraint constraint : this.constraints) cloned.add(constraint.clone());
		facetTarget.setConstraints(cloned);
		return facetTarget;
	}

	private static class FacetConstraint implements FacetTarget.Constraint, Cloneable {
		private Node node;
		private boolean negated = false;
		private String name;

		FacetConstraint(String name) {
			this.name = name;
		}

		@Override
		public String name() {
			return this.name;
		}

		@Override
		public Node node() {
			return this.node;
		}

		public void node(Node node) {
			this.node = node;
		}

		@Override
		public boolean negated() {
			return negated;
		}

		@Override
		public FacetConstraint clone() throws CloneNotSupportedException {
			return (FacetConstraint) super.clone();
		}

		public String toString() {
			return (negated() ? "withOut" : "with") + " " + node().qualifiedName();
		}
	}


}
