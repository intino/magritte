package scripts;

import magritte.Graph;
import magritte.Node;
import magritte.editors.NodeEditor;
import magritte.schema.MemoryGraph;

import static magritte.Tag.*;

public class SimpleProject {

	private final Graph graph;

	private SimpleProject(Graph graph) {
		this.graph = graph;
	}

	public static SimpleProject start() {
		return new SimpleProject(new MemoryGraph());
	}

	public Graph graph() {
		return graph;
	}

	public SimpleProject singleConcept() {
		edit("thing").type("Concept").set(Root, Case);
		return this;
	}

	public SimpleProject singleConceptWithType() {
		edit("Class|100").type("Concept");
		edit("thing").type("Class").set(Root, Case);
		return this;
	}

	public SimpleProject typeWithDefaultVars() {
		edit("Class").type("Concept").set("*a", 1).set("*b", "text");
		return this;
	}

	public SimpleProject mainType() {
		edit("Class").type("Concept").set(Main);
		return this;
	}

	public SimpleProject mainTypeWithRequiredMembers() {
		edit("Container").type("Concept").set(Main).requires("Component");
		edit("Component").type("Concept");
		return this;
	}

	public SimpleProject mainTypeWithCase() {
		edit("Container").type("Concept").set(Main).has("Component");
		edit("Component").type("Concept").set(Case);
		return this;
	}

	public SimpleProject mainTypeWithPrototype() {
		NodeEditor component = new NodeEditor(graph.createNode());
		edit("Container").type("Concept").set(Main).has(component.node());
		component.type("Concept").set(Case, Prototype);
		return this;
	}

	private NodeEditor edit(String name) {
		if (graph.exists(name)) return new NodeEditor(graph.get(name));
		Node node = graph.createNode();
		node.set(name);
		return new NodeEditor(node);
	}

}
