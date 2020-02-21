package io.intino.tara.compiler.core;

import io.intino.tara.compiler.core.operation.model.ModelOperation;
import io.intino.tara.compiler.dependencyresolution.InstanceMarker;
import io.intino.tara.compiler.model.Model;
import io.intino.Configuration.Artifact.Model.Level;

class ModelResolutionOperation extends ModelOperation {

	@Override
	public void call(Model model)  {
		if (model.level() == Level.Solution) new InstanceMarker(model).all();
	}
}
