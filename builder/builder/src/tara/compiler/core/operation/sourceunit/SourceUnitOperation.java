package tara.compiler.core.operation.sourceunit;

import tara.compiler.core.SourceUnit;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.core.operation.Operation;

public abstract class SourceUnitOperation implements Operation {
	public abstract void call(SourceUnit unit) throws CompilationFailedException;
}
