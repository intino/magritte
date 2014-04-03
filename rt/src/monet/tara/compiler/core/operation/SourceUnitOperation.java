package monet.tara.compiler.core.operation;

import monet.tara.compiler.core.SourceUnit;
import monet.tara.compiler.core.errorcollection.CompilationFailedException;

public abstract class SourceUnitOperation extends Operation {
	public abstract void call(SourceUnit unit) throws CompilationFailedException;
}
