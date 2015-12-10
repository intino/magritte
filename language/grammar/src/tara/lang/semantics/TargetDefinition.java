package tara.lang.semantics;

import tara.lang.model.FacetTarget;
import tara.lang.model.Node;

public interface TargetDefinition {

	TargetDefinition on(String name);

	TargetDefinition include(Node node);

	FacetTarget target();
}
