package monopoly.monopoly;

import monopoly.monopoly.natives.Count;
import tara.magritte.Morph;
import tara.magritte.NativeCode;
import tara.magritte.Node;

public class Taxes extends Square {
    protected int count = 0;
    protected Count increment = new increment_meme();

    public Taxes(Node node) {
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

    public static class increment_meme implements Count, NativeCode {
        Luck $;

        @Override
        public void increment() {
            $.count++;
        }

        @Override
        public void set(Morph context) {

        }

        @Override
        public Class<? extends Morph> $Class() {
            return null;
        }
    }
}
