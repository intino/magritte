package siani.tara.compiler.model;

import java.util.Collection;

public interface NodeContainer {

	Collection<Node> getIncludedNodes();

	void addIncludedNodes(Node... nodes);

	void addIncludedNodes(int pos, Node... nodes);

	Node getInclude(String name);

	boolean contains(Node node);

	boolean remove(Node node);

	void moveToTheTop();

	Collection<Node> getNodeSiblings();

	Collection<Variable> getVariables();

	void addVariables(Variable... variables);

	void addVariables(int pos, Variable... variables);

	NodeContainer getContainer();

	void setContainer(NodeContainer container);

	String getQualifiedName();

	String getDoc();

	void setDoc(String doc);

}
