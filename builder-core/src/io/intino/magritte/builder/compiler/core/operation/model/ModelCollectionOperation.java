package io.intino.magritte.builder.compiler.core.operation.model;

import io.intino.magritte.builder.compiler.core.errorcollection.CompilationFailedException;
import io.intino.magritte.builder.compiler.core.operation.Operation;
import io.intino.magritte.builder.compiler.model.Model;

import java.util.Collection;

public abstract class ModelCollectionOperation implements Operation {
	public abstract void call(Collection<Model> model) throws CompilationFailedException;
}