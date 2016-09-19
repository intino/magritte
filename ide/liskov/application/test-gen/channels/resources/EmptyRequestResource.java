package channels.resources;

import teseo.exceptions.*;
import channels.*;
import com.google.gson.Gson;
import tara.magritte.Graph;
import teseo.jms.RequestConsumer;

import javax.jms.*;
import channels.schemas.*;

public class EmptyRequestResource implements RequestConsumer {

	private Graph graph;

	public  EmptyRequestResource(Graph graph) {
		this.graph = graph;
	}

	public void consume(Session session, Message request) {
		actionFor(request).execute();
	}

	private channels.actions.EmptyRequestAction actionFor(Message message) {
		final channels.actions.EmptyRequestAction action = new channels.actions.EmptyRequestAction();
		action.graph = this.graph;
		return action;
	}

}