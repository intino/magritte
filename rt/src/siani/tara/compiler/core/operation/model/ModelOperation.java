package siani.tara.compiler.core.operation.model;

import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.core.operation.Operation;
import siani.tara.lang.TreeWrapper;

public abstract class ModelOperation extends Operation {
	public abstract void call(TreeWrapper tree) throws CompilationFailedException;
}