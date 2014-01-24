package monet.tara.transpiler.core;

/**
 * Created by oroncal on 24/01/14.
 */
public class TranspilationUnit {
	public TranspilerConfiguration getConfiguration() {
		return null;
	}

	public TaraClassLoader getClassLoader() {
		return null;
	}

	public void getErrorCollector() {

	}

	public void addPhaseOperation(SourceUnitOperation sourceUnitOperation, int conversion) {

	}

	public abstract class SourceUnitOperation {
		public abstract void call(SourceUnit unit) throws TranspilationFailedException;
	}

}
