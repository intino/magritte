package io.intino.magritte.framework.layers;

import io.intino.magritte.framework.Layer;
import io.intino.magritte.framework.Node;
import io.intino.magritte.framework.loaders.StringLoader;
import io.intino.magritte.framework.tags.Terminal;

import java.util.List;

public class Grandparent extends Layer implements Terminal {

	private String grandparentVar;
	private String fillByParentVar;

	@SuppressWarnings("WeakerAccess")
	public Grandparent(Node _node) {
		super(_node);
	}

	public String grandparentVar() {
		return grandparentVar;
	}

	public void grandparentVar(String grandparentVar) {
		this.grandparentVar = grandparentVar;
	}

	public String fillByParentVar() {
		return fillByParentVar;
	}

	public void fillByParentVar(String fillByParentVar) {
		this.fillByParentVar = fillByParentVar;
	}

	@Override
	protected void load$(String name, List<?> object) {
		if (name.equals("grandparentVar")) grandparentVar = StringLoader.load(object, this).get(0);
		else if (name.equals("fillByParentVar")) fillByParentVar = StringLoader.load(object, this).get(0);
	}

}
