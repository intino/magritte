package siani.tara.compiler.model.impl;

import siani.tara.compiler.model.FacetTarget;
import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.NodeContainer;
import siani.tara.compiler.model.Variable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.addAll;
import static java.util.Collections.unmodifiableList;

public class FacetTargetImpl implements FacetTarget {

	private String file;
	private int line;
	private String doc;
	private String destiny;
	private List<String> constraint;
	private Node targetNode;
	private NodeContainer container;
	private List<Node> includes = new ArrayList<>();
	private List<Variable> variables = new ArrayList<>();

	@Override
	public String getTarget() {
		return destiny;
	}

	@Override
	public void setTarget(String destiny) {
		this.destiny = destiny;
	}

	@Override
	public List<String> getConstraints() {
		return constraint;
	}

	public void setConstraint(List<String> constraints) {
		this.constraint = constraints;
	}

	@Override
	public Node getTargetNode() {
		return targetNode;
	}

	@Override
	public void setTargetNode(Node destiny) {
		this.targetNode = destiny;
	}

	@Override
	public List<Node> getIncludedNodes() {
		return unmodifiableList(includes);
	}

	@Override
	public void addIncludedNodes(Node... nodes) {
		addAll(includes, nodes);
	}

	@Override
	public void addIncludedNodes(int pos, Node... nodes) {
		includes.addAll(pos, Arrays.asList(nodes));
	}

	@Override
	public Node getInclude(String name) {
		return null;
	}

	@Override
	public boolean contains(Node nodeContainer) {
		return includes.contains(nodeContainer);
	}

	@Override
	public boolean remove(Node node) {
		return includes.remove(node);
	}

	@Override
	public void moveToTheTop() {

	}

	@Override
	public List<Node> getNodeSiblings() {
		ArrayList<Node> objects = new ArrayList<>();
		objects.addAll(getContainer().getIncludedNodes());
		return unmodifiableList(objects);
	}

	@Override
	public List<Variable> getVariables() {
		return unmodifiableList(variables);
	}

	@Override
	public void addVariables(Variable... variables) {
		addAll(this.variables, variables);
	}

	@Override
	public void addVariables(int pos, Variable... variables) {
		this.variables.addAll(pos, Arrays.asList(variables));
	}

	@Override
	public NodeContainer getContainer() {
		return container;
	}

	@Override
	public void setContainer(NodeContainer container) {
		this.container = container;
	}

	@Override
	public String getQualifiedName() {
		return "";
	}

	@Override
	public String getDoc() {
		return doc;
	}

	@Override
	public void addDoc(String doc) {
		this.doc = doc;
	}

	@Override
	public String getFile() {
		return file;
	}

	@Override
	public void setFile(String file) {
		this.file = file;
	}

	@Override
	public int getLine() {
		return line;
	}

	@Override
	public void setLine(int line) {
		this.line = line;
	}

	@Override
	public String toString() {
		return "on " + destiny;
	}
}
