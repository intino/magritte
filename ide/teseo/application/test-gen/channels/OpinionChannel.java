package channels;

import channels.subscriptions.*;
import channels.schemas.*;
import tara.magritte.Graph;
import teseo.jms.*;

import javax.jms.Session;
import javax.jms.JMSException;

public class OpinionChannel {

	public static void init(Session session, Graph graph) {
		new QueueConsumer(session, "catalogs").listen(new OpinionsSubscription(graph));
		new TopicConsumer(session, "processes").listen(new InputProcessesSubscription(graph));
	}
}