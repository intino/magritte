package io.intino.magritte.framework.layers;

import io.intino.magritte.framework.Node;
import io.intino.magritte.framework.loaders.StringLoader;
import io.intino.magritte.framework.tags.Terminal;

import java.util.List;

public class Child extends Parent implements Terminal {

	private String childVar;

	public Child(Node _node) {
		super(_node);
	}


	public String childVar() {
		return childVar;
	}

	@Override
	protected void load$(String name, List<?> object) {
		super.load$(name, object);
		if (name.equals("childVar")) childVar = StringLoader.load(object, this).get(0);
	}
}
