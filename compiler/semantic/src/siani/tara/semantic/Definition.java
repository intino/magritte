package siani.tara.semantic;

import siani.tara.semantic.model.Facet;
import siani.tara.semantic.model.FacetTarget;
import siani.tara.semantic.model.Node;

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
