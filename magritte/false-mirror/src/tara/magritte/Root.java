package tara.magritte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class Root extends Facet {

    private static final Logger LOG = Logger.getLogger(Root.class.getName());
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
    protected void _add(Instance component) {
        if(component instanceof Case)
            components.add((Case) component);
        LOG.severe("Root facet cannot have components that are not cases.");
    }

}
