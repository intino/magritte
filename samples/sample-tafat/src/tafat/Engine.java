package tafat;

import tara.magritte.Graph;
import magritte.castings.NodeSetWrapper;
import tara.magritte.handlers.Casting;
import tafat.morphs.Entity;
import tafat.morphs.Simulation;

public class Engine {
	private final Graph graph;

	public Engine(Graph graph) {
		this.graph = graph;
	}

	public Simulation simulation() {
		return Casting.cast(graph.get(Simulation.class).item(0)).with(Simulation.class);
	}

	public Entity[] entities() {
		return NodeSetWrapper.wrap(graph.get(Entity.class)).with(Entity.class);
	}


}
