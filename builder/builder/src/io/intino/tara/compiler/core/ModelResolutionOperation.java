package io.intino.tara.compiler.core;

import io.intino.tara.compiler.core.operation.model.ModelOperation;
import io.intino.tara.compiler.dependencyresolution.InstanceMarker;
import io.intino.tara.compiler.model.Model;

import static io.intino.tara.compiler.shared.Configuration.Level.System;

class ModelResolutionOperation extends ModelOperation {

	@Override
	public void call(Model model)  {
		if (model.level() == System) new InstanceMarker(model).all();
	}
}
