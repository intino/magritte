package tafat;

import tara.magritte.Morph;
import tara.magritte.NativeCode;
import tara.magritte.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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
        set("step", ((Behavior)morph).step);
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
    protected void add(Node component) {
        if (component.is("Action")) actionList.add(component.morph(Action.class));
        if (component.is("Start")) startList.add(component.morph(Start.class));
        if (component.is("Knol")) knolList.add(component.morph(Knol.class));
    }

    @Override
    protected void set(String name, Object object) {
        super.set(name, object);
        if (name.equalsIgnoreCase("step")) step = (int) object;
    }

    public static class Start extends Morph {

        protected tafat.natives.Action start;

        public Start(Node node) {
            super(node);
        }

        public Start(Morph morph, Node node) {
            super(morph, node);
            set("start", ((Start) morph).start);
        }

        public void start() {
            start.execute();
        }

        public void start(tafat.natives.Action value) {
            start = value;
        }

        @Override
        protected void add(Node component) {
        }

        @Override
        protected void set(String name, Object object) {
            if (name.equalsIgnoreCase("start"))
                start = (tafat.natives.Action) link((NativeCode) object);
        }

        @Override
        public List<Node> components() {
            return Collections.emptyList();
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
        protected void add(Node component) {
        }

        @Override
        protected void set(String name, Object object) {
        }

        @Override
        public List<Node> components() {
            return Collections.emptyList();
        }
    }
}
