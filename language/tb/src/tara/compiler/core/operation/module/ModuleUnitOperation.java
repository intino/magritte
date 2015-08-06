package tara.compiler.core.operation.module;

import tara.compiler.core.SourceUnit;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.core.operation.Operation;

import java.util.Collection;

public abstract class ModuleUnitOperation implements Operation {
	public abstract void call(Collection<SourceUnit> units) throws CompilationFailedException;
}

