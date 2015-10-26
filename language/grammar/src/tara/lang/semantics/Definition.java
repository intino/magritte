package tara.lang.semantics;

import tara.lang.model.FacetTarget;
import tara.lang.model.Facet;
import tara.lang.model.Node;

public interface Definition {

	Definition type(String type);

	Definition name(String name);

	Definition parameter(int position, String name, Object... value);

	Definition annotations(String... annotation);

	Definition flags(String... annotation);

	Definition plate(String address);

	Definition facetTarget(FacetTarget target);

	Definition facet(Facet facet);

	Definition extendsTo(Node parent);

	Definition include(Node... node);

	Definition has(Node... node);

	Node node();

}
