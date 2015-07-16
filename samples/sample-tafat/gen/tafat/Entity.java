package tafat;

import tara.magritte.Morph;
import tara.magritte.Node;

import java.util.*;


public class Entity extends Morph {
    private List<Entity> entityList = new ArrayList<>();
    private List<Feature> featureList = new ArrayList<>();

    public Entity(Node node) {
        super(node);
    }

    public Entity(Morph morph, Node node) {
        super(morph, node);
    }

    public List<Entity> entityList() {
        return entityList;
    }

    public Entity entity(int index) {
        return entityList.get(index);
    }

    public List<Feature> featureList() {
        return featureList;
    }

    public Feature feature(int index) {
        return featureList.get(index);
    }

    @Override
    public List<Node> _components() {
        Set<Node> nodes = new LinkedHashSet<>();
        entityList.stream().forEach(c -> nodes.add(c.node()));
        featureList.stream().forEach(c -> nodes.add(c.node()));
        return new ArrayList<>(nodes);
    }

    @Override
    public Map<String, Object> _variables() {
        return Collections.emptyMap();
    }

    @Override
    protected void add(Node component) {
        if(component.is("Entity")) entityList.add(component.morph(Entity.class));
        if(component.is("Entity$Feature")) featureList.add(component.morph(Feature.class));
    }

    @Override
    protected void set(String name, Object object) {
    }

    public static class Feature extends Morph {
        public Feature(Node node) {
            super(node);
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
        protected void add(Node component) {
        }

        @Override
        protected void set(String name, Object object) {
        }
    }
}
