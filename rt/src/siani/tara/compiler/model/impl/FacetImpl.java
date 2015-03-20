package siani.tara.compiler.model.impl;

import siani.tara.compiler.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FacetImpl extends Element implements Facet {

	private List<Parameter> parameters = new ArrayList<>();
	private NodeContainer container;
	private List<Node> includes = new ArrayList<>();
	private List<VariableImpl> variables = new ArrayList();
	private String facet;

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
	public Node getInclude(String name) {
		for (Node include : includes)
			if (name.equals(include.getName()))
				return include;
		return null;
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
	public Collection<Parameter> getParameters() {
		return parameters;
	}

	@Override
	public void addParameter(String name, int position, Object... values) {
		parameters.add(new ParameterImpl(name, position, values));
	}

	@Override
	public void addParameter(int position, Object... values) {
		addParameter("", position, values);
	}

	@Override
	public String getFacet() {
		return facet;
	}
}
