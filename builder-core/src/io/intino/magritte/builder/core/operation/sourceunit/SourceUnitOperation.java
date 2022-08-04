package io.intino.magritte.builder.core.operation.sourceunit;

import io.intino.magritte.builder.core.SourceUnit;
import io.intino.magritte.builder.core.errorcollection.CompilationFailedException;
import io.intino.magritte.builder.core.operation.Operation;

public abstract class SourceUnitOperation implements Operation {
	public abstract void call(SourceUnit unit) throws CompilationFailedException;
}
