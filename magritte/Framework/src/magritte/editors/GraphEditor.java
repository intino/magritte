package magritte.editors;

import magritte.Graph;
import magritte.Node;
import magritte.Reference;

import java.util.HashMap;
import java.util.Map;

import static magritte.Node.Member.Component;
import static magritte.Tag.Edited;
import static magritte.helpers.Selection.instancesOf;

public abstract class GraphEditor {
	protected final Graph graph;
	protected Map<Integer, Node> editions;

	private Node currentNode;

	public GraphEditor(Graph graph) {
		this.graph = graph;
		this.editions = new HashMap<>();
	}

	public abstract void write();

	public Graph graph() {
		return graph;
	}

	public NodeEditor def(int index) {
		return createNodeEditor($(index));
	}

	public NodeEditor def(String name) {
		return createNodeEditor($(name));
	}

	private NodeEditor createNodeEditor(Node node) {
		this.currentNode = node;
		this.currentNode.set(Edited);
		return new NodeEditor(node);
	}

	public Node $(int index) {
		return editions.containsKey(index) ? editions.get(index) : createNode(index);
	}

	public Node $(int index, String type) {
		Node node = find(type);
		editions.put(index, node);
		return node;
	}

	private Node find(String type) {
		return currentNode.members(Component).filter(instancesOf(type)).get(0);
	}

	private Node $(String name) {
		Node node = graph.exists(name) ? graph.get(name) : createNode(name);
		node.set(name);
		return node;
	}

	public Reference ref(String name) {
		return $(name).ref();
	}

	private Node createNode(int index) {
		Node node = createNode();
		editions.put(index, node);
		return node;
	}

	private Node createNode(String name) {
		Node node = createNode();
		node.set(name);
		return node;
	}

	private Node createNode() {
		Node node = graph.createNode();
		node.set(Edited);
		return node;
	}


}
