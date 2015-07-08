package siani.tara.semantic.model;

import java.util.List;

public interface Facet extends NodeContainer {

	String type();

	List<String> nodeTypes();

	List<Parameter> parameters();
}
