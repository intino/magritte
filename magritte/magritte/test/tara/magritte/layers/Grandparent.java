package tara.magritte.layers;

import tara.magritte.Layer;
import tara.magritte.Node;
import tara.magritte.loaders.NodeLoader;
import tara.magritte.loaders.StringLoader;
import tara.magritte.tags.Terminal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyMap;
import static java.util.Collections.fill;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

public class Grandparent extends Layer implements Terminal {

    private String grandparentVar;
    private String fillByParentVar;

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
