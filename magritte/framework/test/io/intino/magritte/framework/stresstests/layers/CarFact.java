package io.intino.magritte.framework.stresstests.layers;

import io.intino.magritte.framework.Layer;
import io.intino.magritte.framework.Node;
import io.intino.magritte.framework.loaders.DoubleLoader;
import io.intino.magritte.framework.loaders.NodeLoader;
import io.intino.magritte.framework.tags.Terminal;

import java.util.List;

@SuppressWarnings("unused")
public class CarFact extends Layer implements Terminal {

	private Fact _metatype;
	private Car car;
	private double speed;
	private double distance;

	public CarFact(Node node) {
		super(node);
		this._metatype = node.as(Fact.class);
	}

	@SuppressWarnings("IfCanBeSwitch")
	@Override
	protected void load$(String name, List<?> object) {
		super.load$(name, object);
		if (name.equals("car")) this.car = NodeLoader.load(object, Car.class, this).get(0);
		else if (name.equals("speed")) this.speed = DoubleLoader.load(object, this).get(0);
		else if (name.equals("distance")) this.distance = DoubleLoader.load(object, this).get(0);
	}
}
