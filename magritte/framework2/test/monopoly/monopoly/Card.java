package monopoly.monopoly;

import monopoly.monopoly.natives.Movement;
import tara.magritte.Morph;
import tara.magritte.NativeCode;
import tara.magritte.Node;

public class Card extends Morph {

	protected int moveTo = -1000;
	protected Movement transport = new Transport_meme();

	public Card(Node node) {
		super(node);
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
		// TODO como no se setean no est� hecho
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
