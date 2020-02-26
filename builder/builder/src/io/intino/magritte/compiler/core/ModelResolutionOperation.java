package io.intino.magritte.compiler.core;

import io.intino.Configuration.Artifact.Model.Level;
import io.intino.magritte.compiler.core.operation.model.ModelOperation;
import io.intino.magritte.compiler.dependencyresolution.InstanceMarker;
import io.intino.magritte.compiler.model.Model;

class ModelResolutionOperation extends ModelOperation {

	@Override
	public void call(Model model)  {
		if (model.level() == Level.Solution) new InstanceMarker(model).all();
	}
}
