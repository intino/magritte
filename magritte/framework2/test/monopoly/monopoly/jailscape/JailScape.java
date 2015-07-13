package monopoly.monopoly.jailscape;

import tara.magritte.Expression;
import tara.magritte.Morph;
import tara.magritte.Node;

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
        Money
    }
}
