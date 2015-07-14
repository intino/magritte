package monopoly.mover;

import tafat.Behavior;
import tara.magritte.Expression;
import tara.magritte.Morph;
import tara.magritte.Node;

public abstract class Mover extends Morph {

    private final Behavior _type;

    public Mover(Node node) {
        super(node);
        _type = node.morph(Behavior.class);
    }

    public Mover(Morph morph, Node node) {
        super(morph, node);
        _type = node.morph(Behavior.class);
    }

    public int step() {
        return _type.step();
    }

    public void step(int value) {
        _type.step(value);
    }
}
