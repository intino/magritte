package tara.semantic.model;

import java.util.List;

public interface FacetTarget extends NodeContainer, Element {

	String ANY = "any";

	String target();

	List<String> constraints();

	void target(String destiny);

	void constraints(List<String> constraints);

	Node targetNode();

	void targetNode(Node destiny);
}
