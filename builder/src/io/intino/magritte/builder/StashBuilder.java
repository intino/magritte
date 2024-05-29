package io.intino.magritte.builder;

import io.intino.magritte.builder.compiler.operations.StashGenerationOperation;
import io.intino.magritte.io.StashDeserializer;
import io.intino.magritte.io.model.Stash;
import io.intino.tara.Language;
import io.intino.tara.builder.TaraCompilerRunner;
import io.intino.tara.builder.core.CompilerConfiguration;
import io.intino.tara.builder.utils.FileSystemUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.intino.tara.builder.core.CompilerConfiguration.Level.Model;

@SuppressWarnings("unused")
public class StashBuilder {
	private final String dsl;
	private final String dslVersion;
	private final String module;
	private final List<File> files;
	private final Language language;
	private final Charset charset;
	private final PrintStream stream;
	private File workingDirectory;

	public StashBuilder(List<File> files, String dsl, String dslVersion, String module, PrintStream stream) {
		this.files = files;
		this.dsl = dsl;
		this.dslVersion = dslVersion;
		this.module = module;
		this.stream = stream;
		this.language = null;
		this.charset = StandardCharsets.UTF_8;
		try {
			this.workingDirectory = Files.createTempDirectory("_stash_builder").toFile();
		} catch (IOException ignored) {
		}
	}

	public StashBuilder(Map<File, Charset> files, Language language, String module, PrintStream stream) {
		this.files = new ArrayList<>(files.keySet());
		this.charset = files.entrySet().iterator().next().getValue();
		this.language = language;
		this.dsl = language.languageName();
		this.stream = stream;
		this.dslVersion = null;
		this.module = module;
		try {
			this.workingDirectory = Files.createTempDirectory("_stash_builder").toFile();
		} catch (IOException ignored) {
		}
	}

	public Stash[] build() {
		TaraCompilerRunner runner = new TaraCompilerRunner(true, List.of(StashGenerationOperation.class));
		runner.run(createConfiguration(), files.stream().collect(Collectors.toMap(f -> f, f -> true)));
		final File[] createdStashes = findCreatedStashes();
		if (createdStashes.length == 0) return null;
		final Stash[] stash = Arrays.stream(createdStashes).map(StashDeserializer::stashFrom).toArray(Stash[]::new);
		for (File createdStash : createdStashes) createdStash.delete();
		FileSystemUtils.removeDir(workingDirectory);
		return stash;
	}

	private File[] findCreatedStashes() {
		return workingDirectory.listFiles((dir, name) -> name.endsWith(".stash"));
	}

	private CompilerConfiguration createConfiguration() {
		CompilerConfiguration configuration = new CompilerConfiguration();
		configuration.model().level(Model);
		configuration.languagesRepository(new File(new File(java.lang.System.getProperty("user.home")), ".m2/repository"));
		configuration.setOutDirectory(workingDirectory);
		configuration.setResourcesDirectory(workingDirectory);
		configuration.setModule(module);
		configuration.setExcludedPhases(List.of(1));
		configuration.model().outDsl(module);
		configuration.sourceEncoding(charset.name());
		configuration.out(this.stream);
		if (language == null) configuration.language(dsl, dslVersion);
		else configuration.language(language);
		return configuration;
	}
}