package monopoly.tafat;

import tara.magritte.Morph;
import tara.magritte.Node;

import java.util.ArrayList;
import java.util.List;


public class Behavior extends Facet {

    protected int step;
    protected ArrayList<Start> startSet = new ArrayList<>(0);
    protected ArrayList<Action> actionSet = new ArrayList<>(0);
    protected ArrayList<Knol> knolSet = new ArrayList<>(0);

    public Behavior(Node node) {
        super(node);
    }

    // TODO solo se ponen los componentes necesarios para esta simulaci�n
    public int step() {
        return step;
    }

    public void step(Integer value) {
        step = value;
    }

//    public void step(Expression<Integer> value) {
//        node.set("step", value);
//    }

    public List<Start> startSet() {
        return startSet;
    }

    public Start start(int index) {
        return startSet().get(index);
    }

    public List<Knol> knolSet() {
        return knolSet;
    }

    public Knol knol(int index) {
        return knolSet().get(index);
    }

    public List<Action> actionSet() {
        return actionSet;
    }

    public Action action(int index) {
        return actionSet().get(index);
    }

    @Override
    protected void add(Node component) {
        if (component.is(Action.class)) actionSet.add(component.morph(Action.class));
        if (component.is(Start.class)) startSet.add(component.morph(Start.class));
        if (component.is(Knol.class)) knolSet.add(component.morph(Knol.class));
    }

    @Override
    protected void set(String name, Object object) {

    }

    public static class Start extends Morph {
        protected monopoly.tafat.natives.Action start;

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

        public void start(monopoly.tafat.natives.Action value) {
            start = value;
        }

        @Override
        protected void add(Node component) {

        }

        @Override
        protected void set(String name, Object object) {
            //TODO
//            if (name.equalsIgnoreCase("start"))
//                start = (monopoly.tafat.natives.Action) link((Patch) object);
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
    }
}
