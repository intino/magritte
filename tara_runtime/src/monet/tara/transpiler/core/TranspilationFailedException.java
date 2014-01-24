package monet.tara.transpiler.core;

public class TranspilationFailedException extends RuntimeException {
	protected int phase;
	protected TranspilationUnit unit;

	public TranspilationFailedException(int phase, TranspilationUnit unit, Throwable cause) {
		super(Phases.getDescription(phase) + " failed", cause);
		this.phase = phase;
		this.unit = unit;
	}

	public TranspilationFailedException(int phase, TranspilationUnit unit) {
		super(Phases.getDescription(phase) + " failed");
		this.phase = phase;
		this.unit = unit;
	}

	public TranspilationUnit getUnit() {
		return this.unit;
	}
}
