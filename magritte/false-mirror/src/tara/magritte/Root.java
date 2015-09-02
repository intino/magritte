package tara.magritte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Root extends Morph {

    List<Node> components = new ArrayList<>();

    public Root(Node node) {
        super(node);
    }

    public List<Type> types() {
        return PersistenceManager.types();
    }

    public Type type(String type) {
        return PersistenceManager.type(type);
    }

    public Type type(Class<? extends Morph> aClass) {
        return PersistenceManager.type(MorphFactory.type(aClass).get(0));
    }

    @Override
    public List<Node> _components() {
        return Collections.unmodifiableList(components);
    }

    @Override
    protected void _add(Node component) {
        components.add(component);
    }

}
