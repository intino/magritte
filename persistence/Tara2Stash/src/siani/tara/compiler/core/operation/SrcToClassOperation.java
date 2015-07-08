package siani.tara.compiler.core.operation;

import siani.tara.compiler.core.errorcollection.CompilationFailedException;

public abstract class SrcToClassOperation extends Operation {
	public abstract void call() throws CompilationFailedException;
}
