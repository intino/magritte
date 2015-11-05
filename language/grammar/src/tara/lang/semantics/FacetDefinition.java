package tara.lang.semantics;

import tara.lang.model.Facet;
import tara.lang.model.Node;

public interface FacetDefinition {

	FacetDefinition as(String name);

	FacetDefinition parameter(int position, String name, Object... values);

	FacetDefinition parameter(int position, Object... values);

	FacetDefinition include(Node node);

	Facet facet();
}
