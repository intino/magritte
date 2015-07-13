package monopoly.monopoly;

import siani.tara.magritte.Node;
import monopoly.monopoly.natives.Count;

public class Community extends Square {

    protected int count = 0;
    protected Count increment = () -> count++;

    public Community(Node node) {
        super(node);
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public void increment() {
        increment.increment();
    }

    @Override
    public void count(int value) {
        count = value;
    }

    @Override
    public void increment(Count value) {
        increment = value;
    }

    @Override
    protected void add(Node component) {
    }

    @Override
    protected void set(String name, Object object) {
        // TODO como no se setean no est� hecho
    }
}
