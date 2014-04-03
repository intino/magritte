package monet.tara.compiler.core;

import monet.tara.compiler.core.errorcollection.TaraException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public abstract class AbstractReaderSource {

	protected CompilerConfiguration configuration;
	private BufferedReader lineSource = null;

	public AbstractReaderSource(CompilerConfiguration configuration) {
		if (configuration == null)
			throw new IllegalArgumentException("Transpiler configuration must not be null!");
		this.configuration = configuration;
	}

	public boolean canReopenSource() {
		return true;
	}

	public abstract Reader getReader() throws IOException;

	public void cleanup() throws TaraException {
		if (this.lineSource != null) {
			try {
				this.lineSource.close();
			} catch (IOException e) {
				throw new TaraException("Error cleaning source");
			}
		}
		this.lineSource = null;
	}
}
