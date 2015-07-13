package magritte.dsl;

import tara.magritte.Graph;
import tara.magritte.schema.Box;

public class Tafat extends Box {
	public static final Box box = new Tafat();

	private Box[] includes = {
		magritte.ontology.tafat.Main.box
	};

	@Override
	protected void doLoad(Graph graph) {
		for (Box include : includes) include.load(graph);

	}
}
