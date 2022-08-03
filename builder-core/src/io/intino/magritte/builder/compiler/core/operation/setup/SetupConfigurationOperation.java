package io.intino.magritte.builder.compiler.core.operation.setup;

import io.intino.magritte.builder.compiler.core.CompilationUnit;
import io.intino.magritte.builder.compiler.core.CompilerConfiguration;
import io.intino.magritte.builder.compiler.core.errorcollection.CompilationFailedException;

import java.util.logging.Logger;

public class SetupConfigurationOperation extends SetupOperation {
	private static final Logger LOG = Logger.getGlobal();

	private final CompilerConfiguration configuration;
	private final CompilationUnit unit;

	public SetupConfigurationOperation(CompilationUnit unit) {
		this.configuration = unit.configuration();
		this.unit = unit;
	}

	@Override
	public void call() throws CompilationFailedException {
	}

}