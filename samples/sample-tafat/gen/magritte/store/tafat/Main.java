package magritte.ontology.tafat;

import tara.magritte.Graph;
import tara.magritte.editors.GraphEditor;
import tara.magritte.schema.Box;

public class Main extends Box.Ontology {
	public static final Box box = new Main();

	@Override
	public Box[] dependencies() {
		return new Box[]{};
	}

	@Override
	protected GraphEditor modelEditor(Graph graph) {
		return new GraphEditor(graph) {
			@Override
			public void write() {
				def(1).name("Entity").type("Concept").set(Named);
				def(2).name("Behavior").type("Concept").set(Named);
				def(3).name("Behavior.Action").type("Concept").set(Intention, Required);
				def(3).name("Simulation").type("Concept").set(Single, Scene, Required);
			}
		};
	}
}

