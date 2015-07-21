package tara.compiler.core;

import java.util.logging.Logger;

public abstract class AbstractReaderSource {

	private static final Logger LOG = Logger.getLogger(AbstractReaderSource.class.getName());

	protected CompilerConfiguration configuration;

	public AbstractReaderSource(CompilerConfiguration configuration) {
		if (configuration == null)
			throw new IllegalArgumentException("Transpiler configuration must not be null!");
		this.configuration = configuration;
	}
}
