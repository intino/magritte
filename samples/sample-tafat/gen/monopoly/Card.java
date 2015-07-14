package monopoly;

import monopoly.natives.Movement;
import tara.magritte.Morph;
import tara.magritte.NativeCode;
import tara.magritte.Node;

import java.util.Collections;
import java.util.List;

public class Card extends Morph {

	protected int moveTo;
	protected Movement transport;

	public Card(Node node) {
		super(node);
		set("moveTo", -1000);
		set("transport", new Transport_meme());
	}

	public Card(Morph morph, Node node) {
		super(morph, node);
		set("moveTo", ((Card)morph).moveTo);
		set("transport", ((Card)morph).transport);
	}

	public int moveTo() {
		return moveTo;
	}

	public boolean transport() {
		return transport.involvesMovement();
	}

	public void transport(Movement value) {
		transport = value;
	}

	@Override
	protected void add(Node component) {
	}

	@Override
	protected void set(String name, Object object) {
		if (name.equalsIgnoreCase("moveTo")) moveTo = (int) object;
		else if (name.equalsIgnoreCase("transport")) transport = (Movement) link((NativeCode) object);
	}


	@Override
	public List<Node> components() {
		return Collections.emptyList();
	}

	private class Transport_meme implements Movement, NativeCode {
		Card $;


		@Override
		public boolean involvesMovement() {
			return $.moveTo() != -1000;
		}

		@Override
		public void set(Morph context) {
			$ = (Card) context;
		}

		@Override
		public Class<? extends Morph> $Class() {
			return Card.class;
		}
	}
}
