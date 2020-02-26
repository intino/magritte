package io.intino.magritte.compiler.core;

import io.intino.magritte.compiler.core.errorcollection.TaraException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractReaderSource {

	private static final Logger LOG = Logger.getGlobal();

	protected CompilerConfiguration configuration;
	private BufferedReader lineSource = null;

	AbstractReaderSource(CompilerConfiguration configuration) {
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
				LOG.log(Level.SEVERE, e.getMessage(), e);
				throw new TaraException("Error cleaning source");
			}
		}
		this.lineSource = null;
	}
}
