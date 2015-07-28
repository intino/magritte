package tara.language.semantics;

import tara.language.model.Facet;
import tara.language.model.FacetTarget;
import tara.language.model.Node;

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
