package io.intino.magritte.builder.compiler.core.operation.sourceunit;

import io.intino.magritte.builder.compiler.core.SourceUnit;
import io.intino.magritte.builder.compiler.core.errorcollection.CompilationFailedException;
import io.intino.magritte.builder.compiler.core.operation.Operation;

import java.util.Collection;

public abstract class SourceUnitCollectionOperation implements Operation {
	public abstract void call(Collection<SourceUnit> units) throws CompilationFailedException;
}

