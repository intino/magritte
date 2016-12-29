package io.intino.tara.magritte.stresstests.layers;

import io.intino.tara.magritte.Date;
import io.intino.tara.magritte.Layer;
import io.intino.tara.magritte.Node;
import io.intino.tara.magritte.loaders.DateLoader;

import java.util.List;

public class Fact extends Layer {

    @SuppressWarnings("unused")
    private Date instant;

    public Fact(Node node) {
        super(node);
    }

    @Override
    protected void _load(String name, List<?> object) {
        super._load(name, object);
        if (name.equals("instant")) instant = DateLoader.load(object, this).get(0);
    }
}
