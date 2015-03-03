package siani.tara.compiler.core;

import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.core.errorcollection.ErrorCollector;

public abstract class ProcessingUnit {

	protected int phase;
	protected boolean phaseComplete;
	protected ErrorCollector errorCollector;
	protected CompilerConfiguration configuration;


	protected ProcessingUnit(CompilerConfiguration configuration, ErrorCollector er) {
		this.phase = 0;
		this.configuration = configuration == null ? new CompilerConfiguration() : configuration;
		if (er == null) errorCollector = new ErrorCollector(this.configuration);
		else this.errorCollector = er;
	}

	public void configure(CompilerConfiguration configuration) {
		this.configuration = configuration;
	}

	public CompilerConfiguration getConfiguration() {
		return this.configuration;
	}

	public void setConfiguration(CompilerConfiguration configuration) {
		this.configuration = configuration;
	}

	public ErrorCollector getErrorCollector() {
		return this.errorCollector;
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

	public int getPhase() {
		return phase;
	}

	public boolean isPhaseComplete() {
		return phaseComplete;
	}

	public void nextPhase() throws CompilationFailedException {
		gotoPhase(this.phase + 1);
	}
}
