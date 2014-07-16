package tafat.engine.core;

import magritte.schema.Node;
import magritte.metamorph.Concept;

public class ::identifier:: extends ::parent:: ::type:: {

	::properties::

    public ::identifier::(Node node) {
        super(node);
    }

    ::vars::
    ::propertySetters::
}

@vars
    public ::type:: ::name::() {
        return node.getFact("::name::").getValue();
    }

@type
implements ::typeName::

@properties
	public static final Variable<::type::> ::name:: = new Variable<>();

@propertySetters
    public void ::nameLower::(::name:: ::nameLower::) {
    }