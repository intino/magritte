package io.intino.magritte.builder.compiler.core.operation.model;

import io.intino.magritte.builder.compiler.core.errorcollection.CompilationFailedException;
import io.intino.magritte.builder.compiler.core.operation.Operation;
import io.intino.magritte.builder.compiler.model.Model;

public abstract class ModelOperation implements Operation {
	public abstract void call(Model model) throws CompilationFailedException;
}