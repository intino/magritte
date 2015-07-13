package monopoly.monopoly.jailscape;

import wata._magritte.lite.Expression;
import wata._magritte.lite.Morph;
import wata._magritte.lite.Node;

public class JailScape extends Morph {
    public JailScape(Node node) {
        super(node);
    }

    public Modes[] modes() {
        return (Modes[]) node.get("modes");
    }

    public int step() {
        return (int) node.get("step");
    }

    public void Modes(Modes... values) {
        node.set("modes", values);
    }

    public void step(Integer value) {
        node.set("step", value);
    }

    public void step(Expression<Integer> value) {
        node.set("step", value);
    }

    @Override
    protected void add(Node component) {

    }

    @Override
    protected void set(String name, Object object) {

    }

    public enum Modes {
        Card,
        Money;
    }
}
