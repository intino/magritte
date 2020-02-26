package io.intino.magritte.framework.stresstests.layers;

import io.intino.magritte.framework.Layer;
import io.intino.magritte.framework.Node;
import io.intino.magritte.framework.loaders.DateXLoader;
import io.intino.magritte.framework.types.DateX;

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
