package io.intino.tara.magritte.layers;

import io.intino.tara.magritte.Layer;
import io.intino.tara.magritte.Node;
import io.intino.tara.magritte.loaders.StringLoader;
import io.intino.tara.magritte.tags.Terminal;

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
	protected void _load(String name, List<?> object) {
		if (name.equals("grandparentVar")) grandparentVar = StringLoader.load(object, this).get(0);
		else if (name.equals("fillByParentVar")) fillByParentVar = StringLoader.load(object, this).get(0);
	}

}
