package monet.tara.compiler.core;

import monet.tara.compiler.core.error_collection.CompilationFailedException;
import monet.tara.compiler.core.error_collection.ErrorCollector;

public abstract class ProcessingUnit {

	protected int phase;
	protected boolean phaseComplete;
	protected ErrorCollector errorCollector;
	protected CompilerConfiguration configuration;


	protected ProcessingUnit(CompilerConfiguration configuration, ErrorCollector er) {
		this.phase = 1;
		configure(configuration == null ? new CompilerConfiguration() : configuration);
		if (er == null) er = new ErrorCollector(getConfiguration());
		this.errorCollector = er;

	}

	public void configure(CompilerConfiguration configuration) {
		this.configuration = configuration;
	}

	public CompilerConfiguration getConfiguration() {
		return this.configuration;
	}

	public ErrorCollector getErrorCollector() {
		return this.errorCollector;
	}

	public void setConfiguration(CompilerConfiguration configuration) {
		this.configuration = configuration;
	}


	public void gotoPhase(int phase) throws CompilationFailedException {
		if (!this.phaseComplete)
			completePhase();

		this.phase = phase;
		this.phaseComplete = false;
	}

	public void completePhase() throws CompilationFailedException {
		this.errorCollector.failIfErrors();
		this.phaseComplete = true;
	}

	public void nextPhase() throws CompilationFailedException {
		gotoPhase(this.phase + 1);
	}
}
