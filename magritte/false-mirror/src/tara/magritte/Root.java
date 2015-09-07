package tara.magritte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class Root extends Morph {

    private static final Logger LOG = Logger.getLogger(Root.class.getName());
    List<Declaration> components = new ArrayList<>();

    public Root(Declaration aDeclaration) {
        super(aDeclaration);
    }

    @SuppressWarnings("unused")
    public List<Definition> types() {
        return PersistenceManager.types();
    }

    @SuppressWarnings("unused")
    public Definition type(String type) {
        return PersistenceManager.type(type);
    }

    @SuppressWarnings("unused")
    public Definition type(Class<? extends Morph> aClass) {
        return PersistenceManager.type(MorphFactory.names(aClass).get(0));
    }

    @Override
    public List<Declaration> _components() {
        return Collections.unmodifiableList(components);
    }

    @Override
    protected void _addComponent(Declaration component) {
        components.add(component);
    }

}
