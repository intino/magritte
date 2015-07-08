package siani.tara.semantic;

import siani.tara.semantic.model.FacetTarget;
import siani.tara.semantic.model.Node;

public interface TargetDefinition {

	TargetDefinition on(String name);

	TargetDefinition include(Node node);

	FacetTarget target();
}
