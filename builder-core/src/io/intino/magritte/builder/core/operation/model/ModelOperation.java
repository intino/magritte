package io.intino.magritte.builder.core.operation.model;

import io.intino.magritte.builder.core.CompilationUnit;
import io.intino.magritte.builder.core.errorcollection.CompilationFailedException;
import io.intino.magritte.builder.core.operation.Operation;
import io.intino.magritte.builder.model.Model;

public abstract class ModelOperation implements Operation {
	protected final CompilationUnit unit;

	public ModelOperation(CompilationUnit unit) {
		this.unit = unit;
	}

	public abstract void call(Model model) throws CompilationFailedException;
}