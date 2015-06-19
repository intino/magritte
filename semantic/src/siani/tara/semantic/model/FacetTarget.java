package siani.tara.semantic.model;

import java.util.List;

public interface FacetTarget extends Element {

	String target();

	List<? extends Node> includes();

}
