package tara.magritte;


import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

public class Reference {

	String name;
    DynamicModel model;
    Node node;
	LocalDateTime time = now();

	Reference() {
	}

	public Reference(String name, DynamicModel model) {
		this.name = name;
        this.model = model;
		model.register(this);
    }

	public Reference(Node node) {
		this(node.id, (DynamicModel) node.model());
		this.node = node;
    }

	public String name() {
		return name;
	}

	public Node node() {
		time = now();
		if (node == null) node = model.loadInstance(this);
		return node;
	}

	Node free() {
		Node result = node;
		node = null;
		time = now();
		return result;
	}
}
