package tara.compiler.core.operation.setup;

import io.intino.legio.LegioApplication;
import io.intino.legio.Project;
import tara.compiler.core.CompilationUnit;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.core.errorcollection.TaraException;
import tara.compiler.core.errorcollection.message.Message;
import tara.compiler.shared.Configuration;
import tara.compiler.shared.Configuration.Level;
import tara.io.Stash;
import tara.io.StashDeserializer;
import tara.magritte.Graph;

import java.io.File;
import java.util.logging.Logger;

import static java.lang.System.out;
import static tara.compiler.shared.TaraBuildConstants.PRESENTABLE_MESSAGE;

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
			final Graph graph = Graph.from(stash).wrap(LegioApplication.class);
			if (graph == null) throw new TaraException("Configuration corrupt or not found");
			LegioApplication legio = graph.application();
			if (legio == null) return checkConfiguration();
			extractConfiguration(legio);
			return checkConfiguration();
		} catch (Throwable t) {
			throw new TaraException(t.getMessage());
		}

	}

	private boolean checkConfiguration() throws TaraException {
		if (configuration.language() == null) throw new TaraException("Language not defined or not found");
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
			configuration.language(factory.language().name());
			configuration.dslVersion(factory.language().version());
			configuration.level(level);
		}
		configuration.outDSL(project.name());
		configuration.workingPackage(factory.generation().inPackage());
		configuration.artifactId(project.name().toLowerCase());
		configuration.groupId(project.groupId());
		configuration.modelVersion(project.version());
	}
}
