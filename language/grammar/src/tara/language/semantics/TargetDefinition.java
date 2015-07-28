package tara.language.semantics;

import tara.language.model.FacetTarget;
import tara.language.model.Node;

public interface TargetDefinition {

	TargetDefinition on(String name);

	TargetDefinition include(Node node);

	FacetTarget target();
}
