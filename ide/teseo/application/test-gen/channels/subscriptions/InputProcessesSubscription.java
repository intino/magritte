package channels.subscriptions;

import channels.schemas.*;
import com.google.gson.Gson;
import tara.magritte.Graph;
import teseo.jms.Consumer;

import javax.jms.*;

public class InputProcessesSubscription implements Consumer {

	private Graph graph;

	public InputProcessesSubscription(Graph graph) {
		this.graph = graph;
	}

	public void consume(Message message) {
		actionFor(message).execute();
	}

	private channels.actions.InputProcessesAction actionFor(Message message) {
		final channels.actions.InputProcessesAction action = new channels.actions.InputProcessesAction();
		action.graph = this.graph;
		try {
			action.message = new Gson().fromJson(((TextMessage) message).getText(), Processes.class);
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return action;
	}
}