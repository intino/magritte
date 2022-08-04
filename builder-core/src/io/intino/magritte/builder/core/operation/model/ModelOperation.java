package io.intino.magritte.builder.core.operation.model;

import io.intino.magritte.builder.core.errorcollection.CompilationFailedException;
import io.intino.magritte.builder.core.operation.Operation;
import io.intino.magritte.builder.model.Model;

public abstract class ModelOperation implements Operation {
	public abstract void call(Model model) throws CompilationFailedException;
}