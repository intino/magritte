package monopoly.monopoly.mover;

import tara.magritte.Expression;
import tara.magritte.Morph;
import tara.magritte.Node;

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
