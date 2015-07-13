package tara.semantic;

import tara.semantic.model.Facet;
import tara.semantic.model.Node;

public interface FacetDefinition {

	FacetDefinition as(String name);

	FacetDefinition parameter(int position, String name, Object... values);

	FacetDefinition parameter(int position, Object... values);

	FacetDefinition include(Node node);

	Facet facet();
}
