package siani.tara.compiler.model.impl;

import siani.tara.compiler.model.FacetTarget;
import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.NodeContainer;
import siani.tara.compiler.model.Variable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FacetTargetImpl implements FacetTarget {

	String destiny;
	Node destinyNode;
	NodeContainer container;
	List<Node> includes = new ArrayList<>();
	List<Variable> variables = new ArrayList<>();

	@Override
	public String getDestiny() {
		return destiny;
	}

	@Override
	public void setDestiny(String destiny) {
		this.destiny = destiny;
	}

	@Override
	public Node getDestinyNode() {
		return destinyNode;
	}

	@Override
	public void setDestiny(Node destiny) {
		this.destinyNode = destiny;
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
		return null;
	}

	@Override
	public Collection<Node> getNodeSiblings() {
		ArrayList<Node> objects = new ArrayList<>();
		objects.addAll(getContainer().getIncludedNodes());
		objects.remove(this);
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
}
