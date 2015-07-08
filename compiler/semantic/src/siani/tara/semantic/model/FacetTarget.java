package siani.tara.semantic.model;

import java.util.List;

public interface FacetTarget extends NodeContainer {

	String target();

	List<String> constraint();
}
