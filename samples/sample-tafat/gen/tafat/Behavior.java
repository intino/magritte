package tafat;

import tara.magritte.Morph;
import tara.magritte.NativeCode;
import tara.magritte.Node;

import java.util.*;


public class Behavior extends Facet {

    protected int step;
    protected ArrayList<Start> startList = new ArrayList<>();
    protected ArrayList<Action> actionList = new ArrayList<>();
    protected ArrayList<Knol> knolList = new ArrayList<>();

    public Behavior(Node node) {
        super(node);
    }

    public Behavior(Morph morph, Node node) {
        super(morph, node);
        _set("step", ((Behavior) morph).step);
    }

    public int step() {
        return step;
    }

    public void step(Integer value) {
        step = value;
    }

    public List<Start> startList() {
        return startList;
    }

    public Start start(int index) {
        return startList.get(index);
    }

    public List<Knol> knolList() {
        return knolList;
    }

    public Knol knol(int index) {
        return knolList.get(index);
    }

    public List<Action> actionList() {
        return actionList;
    }

    public Action action(int index) {
        return actionList.get(index);
    }

    @Override
    public List<Node> _components() {
        Set<Node> nodes = new LinkedHashSet<>(super._components());
        startList.stream().forEach(rule -> nodes.add(rule._node()));
        actionList.stream().forEach(rule -> nodes.add(rule._node()));
        knolList.stream().forEach(rule -> nodes.add(rule._node()));
        return new ArrayList<>(nodes);
    }

    @Override
    public Map<String, Object> _variables() {
        Map<String, Object> map = new LinkedHashMap<>(super._variables());
        map.put("step", step);
        return map;
    }

    @Override
    protected void _add(Node component) {
        super._add(component);
        if (component.is("Action")) actionList.add(component.morph(Action.class));
        if (component.is("Behavior$Start")) startList.add(component.morph(Start.class));
        if (component.is("Behavior$Knol")) knolList.add(component.morph(Knol.class));
    }

    @Override
    protected void _set(String name, Object object) {
        super._set(name, object);
        if (name.equalsIgnoreCase("step")) step = (int) object;
    }

    public static class Start extends Morph {

        protected tafat.natives.Action start;

        public Start(Node node) {
            super(node);
        }

        public Start(Morph morph, Node node) {
            super(morph, node);
            _set("start", ((Start) morph).start);
        }

        public void start() {
            start.execute();
        }

        public void start(tafat.natives.Action value) {
            start = value;
        }

        @Override
        public List<Node> _components() {
            return Collections.emptyList();
        }

        @Override
        public Map<String, Object> _variables() {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("start", start);
            return map;
        }

        @Override
        protected void _add(Node component) {
        }

        @Override
        protected void _set(String name, Object object) {
            if (name.equalsIgnoreCase("start")) start = (tafat.natives.Action) _link((NativeCode) object);
        }
    }

    public static class Knol extends Morph {
        public Knol(Node node) {
            super(node);
        }

        public Knol(Morph morph, Node node) {
            super(morph, node);
        }

        @Override
        public List<Node> _components() {
            return Collections.emptyList();
        }

        @Override
        public Map<String, Object> _variables() {
            return Collections.emptyMap();
        }

        @Override
        protected void _add(Node component) {
        }

        @Override
        protected void _set(String name, Object object) {
        }
    }
}
