package tara.compiler.core;

import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.core.operation.model.ModelOperation;
import tara.compiler.dependencyresolution.TerminalInstanceAdder;
import tara.compiler.model.Model;

public class SystemDependencyResolutionOperation extends ModelOperation {

	@Override
	public void call(Model model) throws CompilationFailedException {
		if (model.getLevel() == 0) new TerminalInstanceAdder(model).complete();
	}
}
