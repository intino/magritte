package io.intino.tara.compiler.core.operation.module;

import io.intino.tara.compiler.core.SourceUnit;
import io.intino.tara.compiler.core.errorcollection.CompilationFailedException;
import io.intino.tara.compiler.core.operation.Operation;

import java.util.Collection;

public abstract class ModuleUnitOperation implements Operation {
	public abstract void call(Collection<SourceUnit> units) throws CompilationFailedException;
}

