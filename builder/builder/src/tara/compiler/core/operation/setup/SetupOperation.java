package tara.compiler.core.operation.setup;

import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.core.operation.Operation;

public abstract class SetupOperation implements Operation {
	public abstract void call() throws CompilationFailedException;

}
