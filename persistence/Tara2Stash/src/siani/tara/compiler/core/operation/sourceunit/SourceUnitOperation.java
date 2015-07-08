package siani.tara.compiler.core.operation.sourceunit;

import siani.tara.compiler.core.SourceUnit;
import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.core.operation.Operation;

public abstract class SourceUnitOperation extends Operation {
	public abstract void call(SourceUnit unit) throws CompilationFailedException;
}
