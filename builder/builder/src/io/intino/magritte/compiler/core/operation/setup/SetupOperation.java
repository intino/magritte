package io.intino.magritte.compiler.core.operation.setup;

import io.intino.magritte.compiler.core.errorcollection.CompilationFailedException;
import io.intino.magritte.compiler.core.operation.Operation;

public abstract class SetupOperation implements Operation {
	public abstract void call() throws CompilationFailedException;

}
