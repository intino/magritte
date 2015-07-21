package tara.compiler.core;

import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.core.errorcollection.ErrorCollector;

public abstract class ProcessingUnit {

	protected ErrorCollector errorCollector;
	protected CompilerConfiguration configuration;


	protected ProcessingUnit(CompilerConfiguration configuration, ErrorCollector er) {
		this.configuration = configuration == null ? new CompilerConfiguration() : configuration;
		if (er == null) errorCollector = new ErrorCollector(this.configuration);
		else this.errorCollector = er;
	}

	public CompilerConfiguration getConfiguration() {
		return this.configuration;
	}

	public ErrorCollector getErrorCollector() {
		return this.errorCollector;
	}

	public void completeOperation() throws CompilationFailedException {
		this.errorCollector.failIfErrors();
	}

}
