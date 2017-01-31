package io.intino.tara.compiler.core.operation.model;

import io.intino.tara.compiler.core.errorcollection.CompilationFailedException;
import io.intino.tara.compiler.core.operation.Operation;
import io.intino.tara.compiler.model.Model;

import java.util.Collection;

public abstract class ModelCollectionOperation implements Operation {
	public abstract void call(Collection<Model> model) throws CompilationFailedException;
}