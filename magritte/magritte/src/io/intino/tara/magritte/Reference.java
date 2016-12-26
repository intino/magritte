package io.intino.tara.magritte;


import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

public class Reference {

	String name;
    DynamicGraph model;
    Node node;
	LocalDateTime time = now();

	Reference() {
	}

	public Reference(String name, DynamicGraph model) {
		this.name = name;
        this.model = model;
		model.register(this);
    }

	public Reference(Node node) {
		this(node.id, (DynamicGraph) node.graph());
		this.node = node;
    }

	public String name() {
		return name;
	}

	public Node node() {
		time = now();
		if (node == null) node = model.loadNode(this);
		return node;
	}

	Node free() {
		Node result = node;
		node = null;
		time = now();
		return result;
	}
}
