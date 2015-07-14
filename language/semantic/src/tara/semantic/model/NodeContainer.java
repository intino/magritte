package tara.semantic.model;

import java.util.List;

public interface NodeContainer extends Element {

	List<Node> components();

	String type();

	void add(Node... nodes);

	void add(int pos, Node... nodes);

	Node components(String name);

	boolean contains(Node node);

	boolean remove(Node node);

	void moveToTheTop();

	List<Node> siblings();

	List<Variable> variables();

	void add(Variable... variables);

	void add(int pos, Variable... variables);

	NodeContainer container();

	void container(NodeContainer container);

	String qualifiedName();

	String doc();

	void addDoc(String doc);
}
