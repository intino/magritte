package siani.tara.compiler.core.operation;

import siani.tara.compiler.core.SourceUnit;
import siani.tara.compiler.core.errorcollection.CompilationFailedException;

public abstract class SourceUnitOperation extends Operation {
	public abstract void call(SourceUnit unit) throws CompilationFailedException;
}
