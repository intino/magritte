package io.intino.magritte.builder.core;

import io.intino.Configuration.Artifact.Model.Level;
import io.intino.magritte.builder.core.operation.model.ModelOperation;
import io.intino.magritte.builder.dependencyresolution.InstanceMarker;
import io.intino.magritte.builder.model.Model;

class ModelResolutionOperation extends ModelOperation {

	@Override
	public void call(Model model) {
		if (model.level() == Level.Solution) new InstanceMarker(model).all();
	}
}
