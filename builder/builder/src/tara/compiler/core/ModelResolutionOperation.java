package tara.compiler.core;

import tara.compiler.core.operation.model.ModelOperation;
import tara.compiler.dependencyresolution.InstanceMarker;
import tara.compiler.model.Model;

import static tara.compiler.shared.Configuration.Level.System;

class ModelResolutionOperation extends ModelOperation {

	@Override
	public void call(Model model)  {
		if (model.level() == System) new InstanceMarker(model).all();
	}
}
