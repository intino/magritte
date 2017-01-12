package io.intino.tara.compiler.core.operation.setup;

import io.intino.legio.LegioApplication;
import io.intino.legio.Project;
import io.intino.tara.compiler.core.CompilationUnit;
import io.intino.tara.compiler.core.CompilerConfiguration;
import io.intino.tara.compiler.core.errorcollection.CompilationFailedException;
import io.intino.tara.compiler.core.errorcollection.TaraException;
import io.intino.tara.compiler.core.errorcollection.message.Message;
import io.intino.tara.compiler.shared.Configuration;
import io.intino.tara.compiler.shared.Configuration.Level;
import io.intino.tara.io.Stash;
import io.intino.tara.io.StashDeserializer;
import io.intino.tara.magritte.Graph;

import java.io.File;
import java.util.logging.Logger;

import static io.intino.tara.compiler.shared.TaraBuildConstants.PRESENTABLE_MESSAGE;
import static java.lang.System.out;

public class SetupConfigurationOperation extends SetupOperation {
	private static final Logger LOG = Logger.getGlobal();

	private final CompilerConfiguration configuration;
	private final CompilationUnit unit;

	public SetupConfigurationOperation(CompilationUnit unit) {
		this.configuration = unit.getConfiguration();
		this.unit = unit;
	}

	@Override
	public void call() throws CompilationFailedException {
		if (configuration.isVerbose()) out.println(PRESENTABLE_MESSAGE + "Tarac: Setup configuration...");
		try {
			readConfiguration();
		} catch (TaraException e) {
			LOG.severe("Error during dependency resolution: " + e.getMessage());
			unit.getErrorCollector().addError(Message.create(e.getMessage(), unit));
		}
	}

	private boolean readConfiguration() throws TaraException {
		try {
			final File miscDirectory = configuration.getMiscDirectory();
			if (miscDirectory == null || !miscDirectory.exists()) return checkConfiguration();
			final File file = new File(miscDirectory, configuration.getModule() + ".conf");
			if (!file.exists()) return checkConfiguration();
			final Stash stash = StashDeserializer.stashFrom(file);
			final Graph graph = Graph.use(LegioApplication.class, null).loadStashes(stash);
			if (graph == null) throw new TaraException("Configuration corrupt or not found");
			LegioApplication legio = graph.application();
			if (legio == null) return checkConfiguration();
			extractConfiguration(legio);
			return checkConfiguration();
		} catch (Throwable t) {
			t.printStackTrace();
			throw new TaraException(t.getMessage());
		}
	}

	private boolean checkConfiguration() throws TaraException {
		if (configuration.language() == null)
			throw new TaraException("Language not defined or not found: " + configuration.dslName() + "-" + configuration.dslVersion());
		return true;
	}

	private void extractConfiguration(LegioApplication legio) {
		Project project = legio.project();
		Project.Factory factory = project.factory();
		final Level level = Level.valueOf(factory.node().conceptList().stream().filter(c -> c.id().contains("#")).map(c -> c.id().split("#")[0]).findFirst().orElse("Platform"));
		if (configuration.isTest()) {
			configuration.language(project.name());
			configuration.dslVersion(project.version());
			configuration.level(Configuration.Level.values()[level.ordinal() == 0 ? 0 : level.ordinal() - 1]);
		} else {
			configuration.language(factory.asLevel().language());
			configuration.dslVersion(factory.asLevel().version());
			configuration.level(level);
		}
		configuration.outDSL(project.name());
		configuration.workingPackage(factory.inPackage() != null ? factory.inPackage() : project.name().toLowerCase());
		configuration.artifactId(project.name().toLowerCase());
		configuration.groupId(project.groupId());
		configuration.modelVersion(project.version());
	}
}
