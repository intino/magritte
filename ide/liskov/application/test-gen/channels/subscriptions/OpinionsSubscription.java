package channels.subscriptions;

import channels.schemas.*;
import com.google.gson.Gson;
import tara.magritte.Graph;
import teseo.jms.Consumer;

import javax.jms.*;

public class OpinionsSubscription implements Consumer {

	private Graph graph;

	public OpinionsSubscription(Graph graph) {
		this.graph = graph;
	}

	public void consume(Message message) {
		actionFor(message).execute();
	}

	private channels.actions.OpinionsAction actionFor(Message message) {
		final channels.actions.OpinionsAction action = new channels.actions.OpinionsAction();
		action.graph = this.graph;
		try {
			action.message = new Gson().fromJson(((TextMessage) message).getText(), Boolean.class);
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return action;
	}
}