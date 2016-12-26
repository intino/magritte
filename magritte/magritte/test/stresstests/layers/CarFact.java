package stresstests.layers;

import io.intino.tara.magritte.Layer;
import io.intino.tara.magritte.Node;
import io.intino.tara.magritte.loaders.DoubleLoader;
import io.intino.tara.magritte.loaders.NodeLoader;
import io.intino.tara.magritte.tags.Terminal;

import java.util.List;

public class CarFact extends Layer implements Terminal {

    Fact _metatype;
    Car car;
    double speed;
    double distance;

    public CarFact(Node node) {
        super(node);
        this._metatype = node.as(Fact.class);
    }

    @Override
    protected void _load(String name, List<?> object) {
        super._load(name, object);
        if(name.equals("car")) this.car = NodeLoader.load(object, Car.class, this).get(0);
        else if(name.equals("speed")) this.speed = DoubleLoader.load(object, this).get(0);
        else if(name.equals("distance")) this.distance = DoubleLoader.load(object, this).get(0);
    }
}
