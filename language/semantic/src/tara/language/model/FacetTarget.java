package tara.language.model;

import java.util.List;

public interface FacetTarget extends NodeContainer {

	String ANY = "any";

	String target();

	Node targetNode();

	<T extends tara.language.model.Node> void targetNode(T destiny);

	List<String> constraints();

	void target(String destiny);

	void constraints(List<String> constraints);
}
