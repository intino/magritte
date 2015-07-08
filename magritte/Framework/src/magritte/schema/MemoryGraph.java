package magritte.schema;

import magritte.Graph;
import magritte.Node;
import magritte.Set;
import magritte.Set.Filter;

import java.util.*;

import static magritte.Node.Member.Component;
import static magritte.Tag.Case;
import static magritte.Tag.Root;

public class MemoryGraph implements Graph {

	private final HashSet<Node> roots = new HashSet<>();
	private final Map<String, Node> nodes = new LinkedHashMap<>();

	public MemoryGraph() {
		createConcept();
	}

	@Override
	public Node createNode() {
		return new MemoryNode(this);
	}

	private void createConcept() {
		Node node = createNode();
		node.set("Concept");
		index(node);
	}

	@Override
	public boolean exists(String name) {
		return containsAnyKey(keysOf(name));
	}

	@Override
	public Node get(String name) {
		return get(keysOf(name));
	}

	private Node get(String[] names) {
		for (String name : names)
			if (nodes.containsKey(name)) return nodes.get(name);
		return null;
	}

	private boolean containsAnyKey(String[] names) {
		for (String name : names)
			if (nodes.containsKey(name)) return true;
		return false;
	}

	@Override
	public Set<Node> types() {
		return ListSet.cast(typesFromNodes());
	}

	@Override
	public Set<Node> roots() {
		return ListSet.cast(roots.toArray(new Node[roots.size()]));
	}

	@Override
	public Set<Node> find(Filter filter) {
		return ListSet.cast(find(roots(), filter));
	}

	private List<Node> find(Set<Node> nodes, Filter<Node> filter) {
		List<Node> result = nodes.filter(filter).asList();
		nodes.forEach(node -> result.addAll(find(node.members(Component), filter)));
		return result;
	}

	@Override
	public void index(Node node) {
		if (node.name() != null) indexByName(node);
		if (node.is(Case) && node.is(Root)) indexAsRoot(node);
	}

	private void indexByName(Node node) {
		put(node.title(), node);
		put(node.key(), node);
	}

	private void indexAsRoot(Node node) {
		if (roots.contains(node) || !node.is(Case)) return;
		roots.add(node);
	}

	private void put(String key, Node node) {
		if (key == null) return;
		nodes.put(key, node);
	}

	private String[] keysOf(String name) {
		if (name == null) return new String[0];
		return name.split(MemoryNode.Separator);
	}

	private List<Node> typesFromNodes() {
		List<Node> result = new ArrayList<>();
		for (String name : nodes.keySet()) {
			Node node = nodes.get(name);
			if (node.is(Case) || result.contains(node)) continue;
			result.add(node);
		}
		return result;
	}


}

