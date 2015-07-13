package monopoly.monopoly;

import monopoly.monopoly.natives.Movement;
import wata._magritte.lite.Morph;
import wata._magritte.lite.Node;

public class Card extends Morph {

    protected int moveTo = -1000;
    protected Movement transport = () -> moveTo() != -1000;

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
}
