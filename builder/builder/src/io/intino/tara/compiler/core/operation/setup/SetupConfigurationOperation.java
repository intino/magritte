package io.intino.tara.compiler.core.operation.setup;

import io.intino.legio.graph.Artifact;
import io.intino.legio.graph.LegioGraph;
import io.intino.legio.graph.Parameter;
import io.intino.legio.graph.level.LevelArtifact;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static io.intino.tara.compiler.shared.TaraBuildConstants.PRESENTABLE_MESSAGE;

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
		if (configuration.isVerbose()) configuration.out().println(PRESENTABLE_MESSAGE + "Tarac: Setup configuration...");
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
			final Graph graph = new Graph().loadStashes(stash);
			if (graph == null) throw new TaraException("Configuration corrupt or not found");
			LegioGraph legio = graph.as(LegioGraph.class);
			if (legio == null) return checkConfiguration();
			extractConfiguration(legio);
			return checkConfiguration();
		} catch (Throwable t) {
			throw new TaraException(t.getMessage() == null ? "java.lang.NullPointerException at " + t.getStackTrace()[0].toString() : t.getMessage());
		}
	}

	private boolean checkConfiguration() throws TaraException {
		if (configuration.languages().isEmpty())
			throw new TaraException("Language not defined or not found.");
		if (configuration.artifactId() == null || configuration.artifactId().isEmpty())
			throw new TaraException("Project name not found. Reload configuration");
		else if (configuration.languages().get(0).get() == null)
			throw new TaraException("Language not defined or not found: " + configuration.languages().get(0).name() + "-" + configuration.languages().get(0).version());
		return true;
	}

	private void extractConfiguration(LegioGraph legio) {
		Artifact artifact = legio.artifact();
		Artifact.Code code = artifact.code();
		final Level level = Level.valueOf(artifact.core$().conceptList().stream().filter(c -> c.id().contains("#")).map(c -> c.id().split("#")[0]).findFirst().orElse("Platform"));
		configuration.outDSL(snakeCaseToCamelCase(artifact.name$()));
		final String workingPackage = code != null && code.targetPackage() != null ? code.targetPackage() : artifact.groupId() + "." + artifact.name$().toLowerCase();
		configuration.workingPackage((configuration.isTest() ? workingPackage + ".test" : workingPackage) + ".graph");
		configuration.artifactId(artifact.name$().toLowerCase());
		configuration.groupId(artifact.groupId());
		configuration.version(artifact.version());
		Map<String, String> params = new HashMap<>();
		for (Parameter p : legio.artifact().parameterList()) params.put(p.name(), p.defaultValue());
		configuration.packageParameters(params);
		if (configuration.isTest()) {
			configuration.addLanguage(artifact.name$(), artifact.version());
			configuration.level(Configuration.Level.values()[level.ordinal() == 0 ? 0 : level.ordinal() - 1]);
		} else if (artifact.isLevel()) {
			final LevelArtifact.Model model = artifact.asLevel().model();
			configuration.addLanguage(model.language(), model.effectiveVersion().isEmpty() ? model.version() : model.effectiveVersion());
			configuration.level(level);
		}
	}

	private String snakeCaseToCamelCase(String value) {
		return value.contains("-") ? toCamelCase(value.replace("_", "-")) : value;
	}

	private String toCamelCase(String value) {
		if (value.isEmpty()) return "";
		String[] parts = value.split("-");
		if (parts.length == 1) return capitalize(value);
		return Arrays.stream(parts).map(this::capitalize).collect(Collectors.joining());
	}

	private String capitalize(String value) {
		return value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();
	}
}
