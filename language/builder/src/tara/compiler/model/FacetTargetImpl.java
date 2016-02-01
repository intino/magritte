package tara.compiler.model;

import tara.lang.model.FacetTarget;
import tara.lang.model.Node;

import java.util.ArrayList;
import java.util.List;

public class FacetTargetImpl implements FacetTarget {

	private String file;
	private int line;
	private List<String> constraints = new ArrayList<>();
	private List<Node> constraintNodes = new ArrayList<>();
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
	public List<String> constraints() {
		return constraints;
	}

	@Override
	public List<Node> constraintNodes() {
		return constraintNodes;
	}

	@Override
	public void constraints(List<String> constraints) {
		this.constraints = constraints;
	}

	@Override
	public void constraintNodes(List<Node> constraints) {
		constraintNodes = constraints;
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


}
