package magritte.schema;

import magritte.Graph;
import magritte.helpers.SceneLoader;

public class MemoryScene {

	public static Graph graph = createGraph();

	static Graph createGraph() {
		return SceneLoader.load(new MemoryGraph()).with().graph();
	}

	public abstract static class Definition {
		public abstract String[] boxes();
	}

}
