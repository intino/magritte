package io.intino.magritte.builder.compiler.core.operation.sourceunit;

import io.intino.magritte.builder.compiler.core.SourceUnit;
import io.intino.magritte.builder.compiler.core.errorcollection.CompilationFailedException;
import io.intino.magritte.builder.compiler.core.operation.Operation;

public abstract class SourceUnitOperation implements Operation {
	public abstract void call(SourceUnit unit) throws CompilationFailedException;
}
