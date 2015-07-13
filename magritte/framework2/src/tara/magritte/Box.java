package tara.magritte;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;


public abstract class Box {

	public static Map<String, Type> typeRecord = new HashMap<>();
	public Node current;
	public Map<Object, Node> nodeRecord = new WeakHashMap<>();
	private Node root;

	public void load(Node root) {
		this.root = root;
		this.root.add("root");
		dependencies().forEach(d -> d.load(root));
		write();
	}

	public abstract void write();

	public abstract List<Box> dependencies();

	public Box def(String name) {
		finishPrevious();
		name = name.replace(".", "$").replace("+", "_");
		if (!typeRecord.containsKey(name))
			typeRecord.put(name, new Type(name));
		current = typeRecord.get(name);
		return this;
	}

	public Box thing(int number) {
		finishPrevious();
		current = getNode(number);
		return this;
	}

	public Box thing(String name) {
		finishPrevious();
		current = getNode(name);
		return this;
	}

	public Box proto(int number) {
		return thing(number);
	}

	public Box proto(String name) {
		thing(name);
		name = name.replace(".", "$").replace("+", "_");
		current.add(name);
		return this;
	}

	private void finishPrevious() {
		if (current == null) return;
		if (current.owner != null)
			current.owner.add(current);
	}

	public Box set(String parameter, Object object) {
		current.set(parameter, object);
		return this;
	}

	public Box type(String type) {
		type = type.replace(".", "$").replace("+", "_");
		current.add(type);
		if (typeRecord.containsKey(type))
			typeRecord.get(type).components.forEach(c -> {
				current.add(new Node(c, current));
			});
		return this;
	}

	public Box root() {
		root.add(current);
		return this;
	}

	public Box has(int number) {
		has(getNode(number));
		return this;
	}

	public Box has(String name) {
		has(getNode(name));
		return this;
	}

	private void has(Node node) {
		node.owner(current);
	}

	private Node getNode(Object object) {
		if (!nodeRecord.containsKey(object))
			nodeRecord.put(object, new Node(object.toString()));
		return nodeRecord.get(object);
	}

	public void end() {
		finishPrevious();
	}
}
