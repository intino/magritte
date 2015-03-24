package siani.tara.compiler.model.impl;

import siani.tara.compiler.model.*;

import java.util.*;

public class FacetTargetImpl extends Element implements FacetTarget {

	private String file;
	private int line;
	String destiny;
	Node targetNode;
	NodeContainer container;
	List<Node> includes = new ArrayList<>();
	List<Variable> variables = new ArrayList<>();

	@Override
	public String getTarget() {
		return destiny;
	}

	@Override
	public void setTarget(String destiny) {
		this.destiny = destiny;
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
	public Collection<Node> getIncludedNodes() {
		return includes;
	}

	@Override
	public void addIncludedNodes(Node... nodes) {
		Collections.addAll(includes, nodes);
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
	public Collection<Node> getNodeSiblings() {
		ArrayList<Node> objects = new ArrayList<>();
		objects.addAll(getContainer().getIncludedNodes());
		return objects;
	}

	@Override
	public Collection<Variable> getVariables() {
		return variables;
	}

	@Override
	public void addVariables(Variable... variables) {
		Collections.addAll(this.variables, variables);
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
}
