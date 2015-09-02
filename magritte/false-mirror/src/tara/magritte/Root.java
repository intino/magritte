package tara.magritte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Root extends Facet {

    List<Case> components = new ArrayList<>();

    public Root(Case aCase) {
        super(aCase);
    }

    public List<Type> types() {
        return PersistenceManager.types();
    }

    public Type type(String type) {
        return PersistenceManager.type(type);
    }

    public Type type(Class<? extends Facet> aClass) {
        return PersistenceManager.type(FacetFactory.type(aClass).get(0));
    }

    @Override
    public List<Case> _components() {
        return Collections.unmodifiableList(components);
    }

    @Override
    protected void _add(Case component) {
        components.add(component);
    }

}
