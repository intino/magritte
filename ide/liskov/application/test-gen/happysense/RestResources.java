package happysense;

import teseo.framework.web.TeseoSpark;
import happysense.resources.*;
import tara.magritte.Graph;

import static teseo.framework.actions.Router.Method.*;

public class RestResources {

	public static void setup(TeseoSpark server, Graph graph) {

		server.route("dashboard/categorizations").get((manager) -> new CategorizationsResource(graph, manager).execute());
		server.route("dashboard/summary").get((manager) -> new SummaryResource(graph, manager).execute());
		server.route("dashboard/snapshot").get((manager) -> new SnapshotResource(graph, manager).execute());
	}
}