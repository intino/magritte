package tara.compiler.core;

import tara.compiler.core.operation.model.ModelOperation;
import tara.compiler.dependencyresolution.NodeCommiter;
import tara.compiler.model.Model;

import static tara.compiler.core.CompilerConfiguration.ModuleType.System;

public class ModelResolutionOperation extends ModelOperation {

	@Override
	public void call(Model model)  {
		if (model.level() == System) new NodeCommiter(model).complete();
	}
}
