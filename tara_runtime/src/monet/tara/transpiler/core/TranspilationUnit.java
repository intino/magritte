package monet.tara.transpiler.core;

public class TranspilationUnit {

	protected ErrorCollector errorCollector;

	public TranspilerConfiguration getConfiguration() {
		return null;
	}

	public ErrorCollector getErrorCollector() {
		return this.errorCollector;
	}

	public void addPhaseOperation(SourceUnitOperation sourceUnitOperation, int conversion) {

	}

	public void addSource(SourceUnit sourceUnit) {

	}

	public static abstract class SourceUnitOperation {
		public abstract void call(SourceUnit unit) throws TranspilationFailedException;
	}

}
