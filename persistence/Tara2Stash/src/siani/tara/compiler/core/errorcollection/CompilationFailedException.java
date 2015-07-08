package siani.tara.compiler.core.errorcollection;

import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.Phases;

public class CompilationFailedException extends RuntimeException {
	protected final int phase;
	protected final CompilationUnit unit;

	public CompilationFailedException(int phase, CompilationUnit unit, Throwable cause) {
		super(Phases.getDescription(phase) + " failed", cause);
		this.phase = phase;
		this.unit = unit;
	}

	public CompilationFailedException(int phase, CompilationUnit unit) {
		super(Phases.getDescription(phase) + " failed");
		this.phase = phase;
		this.unit = unit;
	}

	public CompilationUnit getUnit() {
		return this.unit;
	}
}
