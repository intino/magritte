package io.intino.tara.compiler.core.operation.sourceunit;

import io.intino.tara.compiler.core.SourceUnit;
import io.intino.tara.compiler.core.errorcollection.CompilationFailedException;
import io.intino.tara.compiler.core.operation.Operation;

public abstract class SourceUnitOperation implements Operation {
	public abstract void call(SourceUnit unit) throws CompilationFailedException;
}
