package siani.tara.compiler.model;

import java.util.Collection;

public interface NodeContainer {

	Collection<Node> getIncludedNodes();

	void addIncludedNodes(Node... nodes);

	Node getInclude(String name);

	Collection<Node> getNodeSiblings();

	Collection<Variable> getVariables();

	void addVariables(Variable... variables);

	NodeContainer getContainer();

	void setContainer(NodeContainer container);

	String getQualifiedName();
}
