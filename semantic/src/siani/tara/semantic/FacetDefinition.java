package siani.tara.semantic;

import siani.tara.semantic.model.Facet;
import siani.tara.semantic.model.Node;

public interface FacetDefinition {

	FacetDefinition as(String name);

	FacetDefinition parameter(int position, String name, Object... values);

	FacetDefinition parameter(int position, Object... values);

	FacetDefinition include(Node node);

	Facet facet();
}
