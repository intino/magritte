package happysense.resources;

import teseo.exceptions.*;
import happysense.*;
import tara.magritte.Graph;
import teseo.framework.web.Resource;
import teseo.framework.web.SparkManager;
import happysense.schemas.*;

public class SnapshotResource implements Resource {

	private Graph graph;
	private SparkManager manager;

	public SnapshotResource(Graph graph, SparkManager manager) {
		this.graph = graph;
		this.manager = manager;
	}

	public void execute() throws ErrorUnknown {
		write(fill(new happysense.actions.SnapshotAction()).execute());
	}

	private happysense.actions.SnapshotAction fill(happysense.actions.SnapshotAction action) {
		action.graph = this.graph;
		action.scale = manager.fromQuery("scale", String.class);
		action.range = manager.fromBody("range", RangeSchema.class);
		action.filters = manager.fromBody("filters", FilterListSchema.class);
		return action;
	}

    private void write(OpinionSnapshotResponseSchema object) {
    	manager.write(object);
    }
}