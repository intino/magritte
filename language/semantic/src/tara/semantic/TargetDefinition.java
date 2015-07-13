package tara.semantic;

import tara.semantic.model.FacetTarget;
import tara.semantic.model.Node;

public interface TargetDefinition {

	TargetDefinition on(String name);

	TargetDefinition include(Node node);

	FacetTarget target();
}
