package monopoly;

import tara.magritte.Morph;
import tara.magritte.NativeCode;
import tara.magritte.Node;
import monopoly.natives.Count;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class Square extends Morph {

    protected int count;
    protected Count increment;

    public Square(Node node) {
        super(node);
        set("count", 0);
        set("increment", new increment_meme());
    }

    public Square(Morph morph, Node node) {
        super(morph, node);
        set("count", ((Square)morph).count);
        set("increment", ((Square)morph).increment);
    }

    public int count() {
        return count;
    }

    public void count(int value) {
        count = value;
    }

    public void increment() {
        increment.increment();
    }

    public void increment(Count value) {
        increment = value;
    }

    @Override
    public List<Node> _components() {
        return Collections.emptyList();
    }

    @Override
    public Map<String, Object> _variables() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("count", count);
        map.put("increment", increment);
        return map;
    }

    @Override
    protected void add(Node component) {
    }

    @Override
    protected void set(String name, Object object) {
        if (name.equalsIgnoreCase("count")) count = (int) object;
        else if (name.equalsIgnoreCase("increment")) increment = (Count) link((NativeCode) object);
    }

    public static class increment_meme implements Count, NativeCode {
        Square $;

        @Override
        public void increment() {
            $.count++;
        }

        @Override
        public void set(Morph context) {
            $ = (Square) context;
        }

        @Override
        public Class<? extends Morph> $Class() {
            return Square.class;
        }
    }
}
