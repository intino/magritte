package io.intino.magritte.builder.core.operation.model;

import io.intino.magritte.builder.core.CompilationUnit;
import io.intino.magritte.builder.core.errorcollection.CompilationFailedException;
import io.intino.magritte.builder.core.operation.Operation;
import io.intino.magritte.builder.model.Model;

import java.util.Collection;

public abstract class ModelCollectionOperation implements Operation {
	protected final CompilationUnit compilationUnit;

	public ModelCollectionOperation(CompilationUnit unit) {
		this.compilationUnit = unit;
	}

	public abstract void call(Collection<Model> model) throws CompilationFailedException;
}