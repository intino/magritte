package siani.tara.compiler.core.operation.module;

import siani.tara.compiler.core.SourceUnit;
import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.core.operation.Operation;

import java.util.Collection;

public abstract class ModuleUnitOperation extends Operation {
	public abstract void call(Collection<SourceUnit> units) throws CompilationFailedException;
}

