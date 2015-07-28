package tara.language.semantics;

import tara.language.model.Facet;
import tara.language.model.Node;

public interface FacetDefinition {

	FacetDefinition as(String name);

	FacetDefinition parameter(int position, String name, Object... values);

	FacetDefinition parameter(int position, Object... values);

	FacetDefinition include(Node node);

	Facet facet();
}
