package io.intino.magritte.framework.stresstests.layers;

import io.intino.magritte.framework.Layer;
import io.intino.magritte.framework.Node;
import io.intino.magritte.framework.loaders.StringLoader;
import io.intino.magritte.framework.tags.Terminal;

import java.util.List;

public class Car extends Layer implements Terminal {

	@SuppressWarnings("unused")
	private String plate;

	public Car(Node node) {
		super(node);
	}

	@Override
	protected void load$(String name, List<?> object) {
		super.load$(name, object);
		if (name.equals("plate")) plate = StringLoader.load(object, this).get(0);
	}
}
