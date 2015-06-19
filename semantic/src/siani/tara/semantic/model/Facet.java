package siani.tara.semantic.model;

import java.util.List;

public interface Facet extends Element {

	String type();

	List<Parameter> parameters();

	List<Node> includes();

}
