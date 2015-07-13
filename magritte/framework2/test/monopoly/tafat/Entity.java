package monopoly.tafat;

import tara.magritte.Morph;
import tara.magritte.Node;

import java.util.ArrayList;
import java.util.List;


public class Entity extends Morph {
    private ArrayList<Entity> entitySet = new ArrayList<>(0);
    private ArrayList<Feature> featureSet = new ArrayList<>(0);

    public Entity(Node node) {
        super(node);
    }

    public List<Entity> entitySet() {
        return entitySet;
    }

    public Entity entity(int index) {
        return entitySet().get(index);
    }

    public List<Feature> featureSet() {
        return featureSet;
    }

    public Feature feature(int index) {
        return featureSet().get(index);
    }

    @Override
    protected void add(Node component) {
        if(component.is(Entity.class))
            entitySet.add(component.morph(Entity.class));
        if(component.is(Feature.class))
            featureSet.add(component.morph(Feature.class));
    }

    @Override
    protected void set(String name, Object object) {

    }

    public static class Feature extends Morph {
        public Feature(Node node) {
            super(node);
        }

        @Override
        protected void add(Node component) {

        }

        @Override
        protected void set(String name, Object object) {

        }
    }
}
