package io.intino.tara.compiler.core;

import io.intino.tara.compiler.core.operation.model.ModelOperation;
import io.intino.tara.compiler.dependencyresolution.InstanceMarker;
import io.intino.tara.compiler.model.Model;

import static io.intino.tara.compiler.shared.Configuration.Level.Solution;

class ModelResolutionOperation extends ModelOperation {

	@Override
	public void call(Model model)  {
		if (model.level() == Solution) new InstanceMarker(model).all();
	}
}
