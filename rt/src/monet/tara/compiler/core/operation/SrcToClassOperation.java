package monet.tara.compiler.core.operation;

import monet.tara.compiler.core.errorcollection.CompilationFailedException;

public abstract class SrcToClassOperation extends Operation {
	public abstract void call() throws CompilationFailedException;
}
