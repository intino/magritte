package tara.compiler.core.operation.setup;

import tara.compiler.core.CompilationUnit;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.io.Stash;
import tara.io.StashDeserializer;

import java.io.File;

import static java.lang.System.out;
import static tara.compiler.constants.TaraBuildConstants.PRESENTABLE_MESSAGE;

public class SetupConfigurationOperation extends SetupOperation {

	private final CompilerConfiguration configuration;

	public SetupConfigurationOperation(CompilationUnit compilationUnit) {
		this.configuration = compilationUnit.getConfiguration();
	}

	@Override
	public void call() throws CompilationFailedException {
		if (configuration.isVerbose()) out.println(PRESENTABLE_MESSAGE + "Tarac: Setup configuration...");
		if (configuration.configurationFile() != null) readConfiguration();
	}

	private void readConfiguration() {
		final File miscDirectory = configuration.getMiscDirectory();
		if (miscDirectory == null || !miscDirectory.exists()) return;
		final File file = new File(miscDirectory, configuration.getModule() + ".conf");
		final Stash stash = StashDeserializer.stashFrom(file);
		assert stash != null;
	}
}
