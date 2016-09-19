package channels.resources;

import teseo.exceptions.*;
import channels.*;
import com.google.gson.Gson;
import tara.magritte.Graph;
import teseo.jms.RequestConsumer;

import javax.jms.*;
import channels.schemas.*;

public class CatalogsResource implements RequestConsumer {

	private Graph graph;

	public  CatalogsResource(Graph graph) {
		this.graph = graph;
	}

	public void consume(Session session, Message request) {
		response(session, replyTo(request), responseMessage(session, idOf(request), actionFor(request).execute()));
	}

	private channels.actions.CatalogsAction actionFor(Message message) {
		final channels.actions.CatalogsAction action = new channels.actions.CatalogsAction();
		action.graph = this.graph;
		try {
			action.catalogs = new Gson().fromJson(((TextMessage) message).getText(), CategorizationCatalog.class);
			action.name = message.getStringProperty("name");
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return action;
	}

	private Message responseMessage(Session session, String responseId, Boolean response) {
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