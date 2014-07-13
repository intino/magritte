package siani.tara.compiler.core.operation.model;

import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.compiler.core.operation.Operation;
import siani.tara.lang.Model;

public abstract class ModelOperation extends Operation {
	public abstract void call(Model tree) throws TaraException;
}