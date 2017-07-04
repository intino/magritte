package io.intino.tara.magritte.stresstests.layers;

import io.intino.tara.magritte.types.DateX;
import io.intino.tara.magritte.Layer;
import io.intino.tara.magritte.Node;
import io.intino.tara.magritte.loaders.DateXLoader;

import java.util.List;

public class Fact extends Layer {

    @SuppressWarnings("unused")
    private DateX instant;

    public Fact(Node node) {
        super(node);
    }

    @Override
    protected void load$(String name, List<?> object) {
        super.load$(name, object);
        if (name.equals("instant")) instant = DateXLoader.load(object, this).get(0);
    }
}
