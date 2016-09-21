package channels.resources;

import teseo.exceptions.*;
import channels.*;
import com.google.gson.Gson;
import tara.magritte.Graph;
import teseo.jms.RequestConsumer;

import javax.jms.*;
import channels.schemas.*;

public class ProcessesResource implements RequestConsumer {

	private Graph graph;

	public  ProcessesResource(Graph graph) {
		this.graph = graph;
	}

	public void consume(Session session, Message request) {
		response(session, replyTo(request), responseMessage(session, idOf(request), actionFor(request).execute()));
	}

	private channels.actions.ProcessesAction actionFor(Message message) {
		final channels.actions.ProcessesAction action = new channels.actions.ProcessesAction();
		action.graph = this.graph;
		return action;
	}

	private Message responseMessage(Session session, String responseId, Processes response) {
		try {
			TextMessage message = session.createTextMessage();
			message.setJMSCorrelationID(responseId);
			message.setText(new Gson().toJson(response));
			return message;
		} catch (JMSException e) {
			e.printStackTrace();
			return null;
		}
	}
}