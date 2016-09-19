package happysense.resources;

import teseo.exceptions.*;
import happysense.*;
import tara.magritte.Graph;
import teseo.framework.web.Resource;
import teseo.framework.web.SparkManager;
import happysense.schemas.*;

public class SummaryResource implements Resource {

	private Graph graph;
	private SparkManager manager;

	public SummaryResource(Graph graph, SparkManager manager) {
		this.graph = graph;
		this.manager = manager;
	}

	public void execute() throws ErrorUnknown {
		write(fill(new happysense.actions.SummaryAction()).execute());
	}

	private happysense.actions.SummaryAction fill(happysense.actions.SummaryAction action) {
		action.graph = this.graph;
		return action;
	}

    private void write(SummaryResponseSchema object) {
    	manager.write(object);
    }
}