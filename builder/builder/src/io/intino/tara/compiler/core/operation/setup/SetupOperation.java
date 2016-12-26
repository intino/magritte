package io.intino.tara.compiler.core.operation.setup;

import io.intino.tara.compiler.core.errorcollection.CompilationFailedException;
import io.intino.tara.compiler.core.operation.Operation;

public abstract class SetupOperation implements Operation {
	public abstract void call() throws CompilationFailedException;

}
