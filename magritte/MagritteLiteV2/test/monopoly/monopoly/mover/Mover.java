package monopoly.monopoly.mover;

import wata._magritte.lite.Expression;
import wata._magritte.lite.Morph;
import wata._magritte.lite.Node;

public abstract class Mover extends Morph {

    public Mover(Node node) {
        super(node);
    }

    public int step() {
        return (int) node.get("step");
    }

    public void step(Integer value) {
        node.set("step", value);
    }

    public void step(Expression<Integer> value) {
        node.set("step", value);
    }
}
