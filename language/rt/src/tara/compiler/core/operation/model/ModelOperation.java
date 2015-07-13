package tara.compiler.core.operation.model;

import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.core.operation.Operation;
import tara.compiler.model.impl.Model;

public abstract class ModelOperation extends Operation {
	public abstract void call(Model model) throws CompilationFailedException;
}