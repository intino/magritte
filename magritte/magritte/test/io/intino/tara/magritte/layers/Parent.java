package io.intino.tara.magritte.layers;

import io.intino.tara.magritte.Node;
import io.intino.tara.magritte.loaders.StringLoader;
import io.intino.tara.magritte.tags.Terminal;

import java.util.List;

public class Parent extends Grandparent implements Terminal {

    private String parentVar;
    private String fillByChildVar;

    @SuppressWarnings("WeakerAccess")
    public Parent(Node _node) {
        super(_node);
    }

    public String parentVar() {
        return parentVar;
    }

    public String fillByChildVar() {
        return fillByChildVar;
    }

    @Override
    protected void _load(String name, List<?> object) {
        super._load(name, object);
        if (name.equals("parentVar")) parentVar = StringLoader.load(object, this).get(0);
        else if (name.equals("fillByChildVar")) fillByChildVar = StringLoader.load(object, this).get(0);
    }

}
