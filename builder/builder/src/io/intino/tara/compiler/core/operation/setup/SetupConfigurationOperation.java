package io.intino.tara.compiler.core.operation.setup;

import io.intino.legio.graph.Artifact;
import io.intino.legio.graph.LegioGraph;
import io.intino.legio.graph.Parameter;
import io.intino.tara.compiler.codegeneration.Format;
import io.intino.tara.compiler.core.CompilationUnit;
import io.intino.tara.compiler.core.CompilerConfiguration;
import io.intino.tara.compiler.core.errorcollection.CompilationFailedException;
import io.intino.tara.compiler.core.errorcollection.TaraException;
import io.intino.tara.compiler.core.errorcollection.message.Message;
import io.intino.tara.compiler.shared.Configuration.Model.Level;
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

	private void readConfiguration() throws TaraException {
		try {
			final File intinoArtifactsDirectory = configuration.artifactsDirectory();
			if (intinoArtifactsDirectory == null || !intinoArtifactsDirectory.exists()) {
				checkConfiguration();
				return;
			}
			final File file = new File(intinoArtifactsDirectory, configuration.getModule() + ".conf");
			if (!file.exists()) {
				checkConfiguration();
				return;
			}
			final Stash stash = StashDeserializer.stashFrom(file);
			final Graph graph = new Graph().loadStashes(stash);
			if (graph == null) throw new TaraException("Configuration corrupt or not found");
			LegioGraph legio = graph.as(LegioGraph.class);
			if (legio == null) {
				checkConfiguration();
				return;
			}
			extractConfiguration(legio);
			checkConfiguration();
		} catch (Throwable t) {
			throw new TaraException(t.getMessage() == null ? "java.lang.NullPointerException at " + t.getStackTrace()[0].toString() : t.getMessage());
		}
	}

	private void checkConfiguration() throws TaraException {
		if (configuration.model().language() == null)
			throw new TaraException("Language not defined or not found.");
		if (configuration.artifactId() == null || configuration.artifactId().isEmpty())
			throw new TaraException("Project name not found. Reload configuration");
		else if (configuration.model().language().get() == null)
			throw new TaraException("Language not defined or not found: " + configuration.model().language().name() + "-" + configuration.model().language().version());
	}

	private void extractConfiguration(LegioGraph legio) {
		Artifact artifact = legio.artifact();
		Artifact.Code code = artifact.code();
		final Level level = Level.valueOf(artifact.core$().conceptList().stream().filter(c -> c.id().contains("$")).map(c -> c.id().split("\\$")[1]).findFirst().orElse("Platform"));
		if (artifact.isLevel()) {
			String outDSL = artifact.asLevel().model().outLanguage();
			configuration.model().outLanguage(outDSL == null ? snakeCaseToCamelCase(artifact.name$()) : outDSL);
		}
		final String workingPackage = code != null && code.targetPackage() != null ? code.targetPackage() : artifact.groupId() + "." + Format.reference().format(artifact.name$()).toString().toLowerCase();
		configuration.workingPackage((configuration.isTest() ? workingPackage + ".test" : workingPackage) + ".graph");
		configuration.artifactId(artifact.name$().toLowerCase());
		configuration.groupId(artifact.groupId());
		configuration.version(artifact.version());
		Map<String, String> params = new HashMap<>();
		for (Parameter p : legio.artifact().parameterList()) params.put(p.name(), p.defaultValue());
		configuration.packageParameters(params);
		if (configuration.isTest()) {
			String language = null;
			if (artifact.isLevel())
				language = artifact.asLevel().model().outLanguage() != null ? artifact.asLevel().model().outLanguage() : artifact.name$();
			configuration.addLanguage(language, artifact.version());
			configuration.model().level(Level.values()[level.ordinal() == 0 ? 0 : level.ordinal() - 1]);
		} else if (artifact.isLevel()) {
			final Artifact.Level.Model model = artifact.asLevel().model();
			configuration.addLanguage(model.language(), model.effectiveVersion().isEmpty() ? model.version() : model.effectiveVersion());
			configuration.model().level(level);
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
