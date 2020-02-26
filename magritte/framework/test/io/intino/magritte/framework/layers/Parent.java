package io.intino.magritte.framework.layers;

import io.intino.magritte.framework.Node;
import io.intino.magritte.framework.loaders.StringLoader;
import io.intino.magritte.framework.tags.Terminal;

import java.util.List;

public class Parent extends Grandparent implements Terminal {

	private String parentVar;
	private String fillByChildVar;

	@SuppressWarnings("WeakerAccess")
	public Parent(Node _node) {
		super(_node);
	}

	public String parentVar() {
		return parentVar;
	}

	public String fillByChildVar() {
		return fillByChildVar;
	}

	@Override
	protected void load$(String name, List<?> object) {
		super.load$(name, object);
		if (name.equals("parentVar")) parentVar = StringLoader.load(object, this).get(0);
		else if (name.equals("fillByChildVar")) fillByChildVar = StringLoader.load(object, this).get(0);
	}

}
