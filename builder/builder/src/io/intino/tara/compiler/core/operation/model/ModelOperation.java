package io.intino.tara.compiler.core.operation.model;

import io.intino.tara.compiler.model.Model;
import io.intino.tara.compiler.core.errorcollection.CompilationFailedException;
import io.intino.tara.compiler.core.operation.Operation;

public abstract class ModelOperation implements Operation {
	public abstract void call(Model model) throws CompilationFailedException;
}