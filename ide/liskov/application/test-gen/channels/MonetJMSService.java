package channels;

import channels.resources.*;
import tara.magritte.Graph;
import teseo.jms.QueueConsumer;

import javax.jms.Session;

public class MonetJMSService {

	public static void init(Session session, Graph graph) {
		new QueueConsumer(session, "catalogs").listen(new CatalogsResource(graph));
		new QueueConsumer(session, "processes").listen(new ProcessesResource(graph));
		new QueueConsumer(session, "empty").listen(new EmptyRequestResource(graph));
	}
}