package io.intino.magritte.builder.core.operation.setup;

import io.intino.magritte.builder.core.errorcollection.CompilationFailedException;
import io.intino.magritte.builder.core.operation.Operation;

public abstract class SetupOperation implements Operation {
	public abstract void call() throws CompilationFailedException;

}
