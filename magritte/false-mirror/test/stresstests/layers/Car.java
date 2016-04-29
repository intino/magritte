package stresstests.layers;

import tara.magritte.Layer;
import tara.magritte.Node;
import tara.magritte.loaders.StringLoader;
import tara.magritte.tags.Terminal;

import java.util.List;

public class Car extends Layer implements Terminal{

    String plate;

    public Car(Node node) {
        super(node);
    }

    @Override
    protected void _load(String name, List<?> object) {
        super._load(name, object);
        if(name.equals("plate")) plate = StringLoader.load(object, this).get(0);
    }
}
