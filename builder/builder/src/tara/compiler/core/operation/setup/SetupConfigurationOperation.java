package tara.compiler.core.operation.setup;

import org.siani.legio.LegioApplication;
import org.siani.legio.Project;
import tara.compiler.core.CompilationUnit;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.shared.Configuration.Level;
import tara.io.Stash;
import tara.io.StashDeserializer;
import tara.magritte.Graph;

import java.io.File;

import static java.lang.System.out;
import static tara.compiler.shared.TaraBuildConstants.PRESENTABLE_MESSAGE;

public class SetupConfigurationOperation extends SetupOperation {

	private final CompilerConfiguration configuration;

	public SetupConfigurationOperation(CompilationUnit compilationUnit) {
		this.configuration = compilationUnit.getConfiguration();
	}

	@Override
	public void call() throws CompilationFailedException {
		if (configuration.isVerbose()) out.println(PRESENTABLE_MESSAGE + "Tarac: Setup configuration...");
		readConfiguration();
	}

	private void readConfiguration() {
		final File miscDirectory = configuration.getMiscDirectory();
		if (miscDirectory == null || !miscDirectory.exists()) return;
		final File file = new File(miscDirectory, configuration.getModule() + ".conf");
		final Stash stash = StashDeserializer.stashFrom(file);
		LegioApplication legio = Graph.from(stash).wrap(LegioApplication.class).application();
		extractConfiguration(legio);
	}

	private void extractConfiguration(LegioApplication legio) {
		Project project = legio.project();
		Project.Factory factory = project.factory();
		configuration.language(factory.modeling().language());
		configuration.dslVersion(factory.modeling().version());
		configuration.outDSL(project.name());
		configuration.workingPackage(factory.generationPackage());
		configuration.level(Level.valueOf(factory.node().conceptList().stream().filter(c -> c.id().contains("#")).map(c -> c.id().split("#")[0]).findFirst().orElse("Platform")));
		configuration.artifactId(project.name().toLowerCase());
		configuration.groupId(project.groupId());
		configuration.persistent(factory.persistent());
		configuration.modelVersion(project.version());
	}
}
