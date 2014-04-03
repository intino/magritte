package monet.tara.compiler.core.operation;

import monet.tara.compiler.core.SourceUnit;
import monet.tara.compiler.core.errorcollection.CompilationFailedException;

import java.util.Collection;

public abstract class ModuleUnitOperation extends Operation {
	public abstract void call(Collection<SourceUnit> units) throws CompilationFailedException;
}

