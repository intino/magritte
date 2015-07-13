package siani.tara.semantic.model;

import java.util.List;

public interface FacetTarget extends NodeContainer {

	String ANY = "any";

	String target();

	List<String> constraints();
}
