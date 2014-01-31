package monet.tara.compiler.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public abstract class AbstractReaderSource {

	protected CompilerConfiguration configuration;
	private BufferedReader lineSource = null;
	private String line = null;
	private int number = 0;

	public AbstractReaderSource(CompilerConfiguration configuration) {
		if (configuration == null) {
			throw new IllegalArgumentException("Transpiler configuration must not be null!");
		}

		this.configuration = configuration;
	}

	public boolean canReopenSource() {
		return true;
	}

	public abstract Reader getReader() throws IOException;

	public String getLine(int lineNumber) {
		if ((this.lineSource != null) && (this.number > lineNumber)) {
			cleanup();
		}

		if (this.lineSource == null) {
			try {
				this.lineSource = new BufferedReader(getReader());
			} catch (Exception e) {
			}
			this.number = 0;
		}

		if (this.lineSource != null) {
			while (this.number < lineNumber) {
				try {
					this.line = this.lineSource.readLine();
					this.number += 1;
				} catch (IOException e) {
					cleanup();
				}
			}
		}

		return this.line;
	}

	public void cleanup() {
		if (this.lineSource != null) {
			try {
				this.lineSource.close();
			} catch (Exception e) {
			}
		}
		this.lineSource = null;
		this.line = null;
		this.number = 0;
	}
}
