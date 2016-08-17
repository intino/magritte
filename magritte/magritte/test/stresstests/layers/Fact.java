package stresstests.layers;

import tara.magritte.Layer;
import tara.magritte.Node;
import tara.magritte.loaders.DateLoader;

import java.time.LocalDateTime;
import java.util.List;

public class Fact extends Layer {

    LocalDateTime instant;

    public Fact(Node node) {
        super(node);
    }

    @Override
    protected void _load(String name, List<?> object) {
        super._load(name, object);
        if(name.equals("instant")) instant = DateLoader.load(object, this).get(0);
    }
}
