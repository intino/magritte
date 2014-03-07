package monet.tara.compiler.core.operation;

import monet.tara.compiler.core.error_collection.CompilationFailedException;

/**
 * Created by oroncal on 06/03/14.
 */
public abstract class SrcToClassOperation extends Operation {
	public abstract void call() throws CompilationFailedException;
}
