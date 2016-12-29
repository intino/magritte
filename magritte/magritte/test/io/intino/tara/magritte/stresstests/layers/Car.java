package io.intino.tara.magritte.stresstests.layers;

import io.intino.tara.magritte.Layer;
import io.intino.tara.magritte.Node;
import io.intino.tara.magritte.loaders.StringLoader;
import io.intino.tara.magritte.tags.Terminal;

import java.util.List;

public class Car extends Layer implements Terminal {

    @SuppressWarnings("unused")
    private String plate;

    public Car(Node node) {
        super(node);
    }

    @Override
    protected void _load(String name, List<?> object) {
        super._load(name, object);
        if (name.equals("plate")) plate = StringLoader.load(object, this).get(0);
    }
}
