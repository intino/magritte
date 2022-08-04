package io.intino.magritte.builder.core.operation.sourceunit;

import io.intino.magritte.builder.core.SourceUnit;
import io.intino.magritte.builder.core.errorcollection.CompilationFailedException;
import io.intino.magritte.builder.core.operation.Operation;

import java.util.Collection;

public abstract class SourceUnitCollectionOperation implements Operation {
	public abstract void call(Collection<SourceUnit> units) throws CompilationFailedException;
}

