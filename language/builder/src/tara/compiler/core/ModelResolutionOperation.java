package tara.compiler.core;

import tara.compiler.core.operation.model.ModelOperation;
import tara.compiler.dependencyresolution.InstanceAdder;
import tara.compiler.model.Model;

public class ModelResolutionOperation extends ModelOperation {

	@Override
	public void call(Model model)  {
		if (model.getLevel() == 0) new InstanceAdder(model).complete();
	}
}
