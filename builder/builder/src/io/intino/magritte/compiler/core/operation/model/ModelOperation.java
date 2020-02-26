package io.intino.magritte.compiler.core.operation.model;

import io.intino.magritte.compiler.core.errorcollection.CompilationFailedException;
import io.intino.magritte.compiler.core.operation.Operation;
import io.intino.magritte.compiler.model.Model;

public abstract class ModelOperation implements Operation {
	public abstract void call(Model model) throws CompilationFailedException;
}