package happysense.resources;

import teseo.exceptions.*;
import happysense.*;
import tara.magritte.Graph;
import teseo.framework.web.Resource;
import teseo.framework.web.SparkManager;
import happysense.schemas.*;

public class CategorizationsResource implements Resource {

	private Graph graph;
	private SparkManager manager;

	public CategorizationsResource(Graph graph, SparkManager manager) {
		this.graph = graph;
		this.manager = manager;
	}

	public void execute() throws ErrorUnknown {
		write(fill(new happysense.actions.CategorizationsAction()).execute());
	}

	private happysense.actions.CategorizationsAction fill(happysense.actions.CategorizationsAction action) {
		action.graph = this.graph;
		return action;
	}

    private void write(CategorizationCatalogResponseSchema object) {
    	manager.write(object);
    }
}