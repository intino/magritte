package io.intino.tara.compiler.core.errorcollection;

import io.intino.tara.compiler.core.Phases;
import io.intino.tara.compiler.core.CompilationUnit;

public class CompilationFailedException extends RuntimeException {
	protected final int phase;
	protected final transient CompilationUnit unit;

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