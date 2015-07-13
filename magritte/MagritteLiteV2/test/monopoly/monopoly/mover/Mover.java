package monopoly.monopoly.mover;

import siani.tara.magritte.Expression;
import siani.tara.magritte.Morph;
import siani.tara.magritte.Node;

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
