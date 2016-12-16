package tara.magritte.layers;

import tara.magritte.Layer;
import tara.magritte.Node;
import tara.magritte.loaders.StringLoader;
import tara.magritte.tags.Terminal;

import java.util.List;

public class Child extends Parent implements Terminal {

    private String childVar;

	public Child(Node _node) {
		super(_node);
	}


    public String childVar() {
        return childVar;
    }

    public void childVar(String childVar) {
        this.childVar = childVar;
    }

	@Override
	protected void _load(String name, List<?> object) {
	    super._load(name, object);
		if (name.equals("childVar")) childVar = StringLoader.load(object, this).get(0);
	}
}
