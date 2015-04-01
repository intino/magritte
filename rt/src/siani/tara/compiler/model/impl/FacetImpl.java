package siani.tara.compiler.model.impl;

import siani.tara.compiler.model.*;

import java.util.*;

public class FacetImpl extends Element implements Facet {

	private String file;
	private int line;
	private List<Parameter> parameters = new ArrayList<>();
	private NodeContainer container;
	private List<Node> includes = new ArrayList<>();
	private String facet;
	private String doc;

	public FacetImpl(String facet) {
		this.facet = facet;
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
		for (Node include : includes)
			if (name.equals(include.getName()))
				return include;
		return null;
	}

	@Override
	public boolean contains(Node node) {
		return includes.contains(node);
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
		ArrayList<Node> siblings = new ArrayList<>();
		siblings.addAll(getContainer().getIncludedNodes());
		return siblings;
	}

	@Override
	public Collection<Variable> getVariables() {
		return Collections.EMPTY_LIST;
	}

	@Override
	public void addVariables(Variable... variables) {

	}

	@Override
	public void addVariables(int pos, Variable... variables) {
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
	public void setDoc(String doc) {
		this.doc = doc;
	}

	@Override
	public Collection<Parameter> getParameters() {
		return parameters;
	}

	@Override
	public void addParameter(String name, int position, String extension, Object... values) {
		ParameterImpl parameter = new ParameterImpl(name, position, extension, values);
		parameter.setFile(file);
		parameter.setLine(line);
		parameter.setOwner(this);
		parameters.add(parameter);
	}

	@Override
	public void addParameter(int position, String extension, Object... values) {
		addParameter("", position, extension, values);
	}

	@Override
	public String getType() {
		return facet;
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
		return getType();
	}
}
