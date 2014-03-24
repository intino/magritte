package monet.tara.compiler.core.errorcollection;

import monet.tara.compiler.core.CompilationUnit;
import monet.tara.compiler.core.Phases;

public class CompilationFailedException extends RuntimeException {
	protected int phase;
	protected CompilationUnit unit;

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
