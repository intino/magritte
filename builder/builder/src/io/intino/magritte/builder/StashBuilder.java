package io.intino.magritte.builder;

import io.intino.Configuration;
import io.intino.magritte.Language;
import io.intino.magritte.builder.compiler.core.CompilerConfiguration;
import io.intino.magritte.builder.compiler.utils.FileSystemUtils;
import io.intino.magritte.io.Stash;
import io.intino.magritte.io.StashDeserializer;

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
		try {
			new TaraCompilerRunner(true).run(createConfiguration(), files);
			final File[] createdStashes = findCreatedStashes();
			if (createdStashes.length == 0) return null;
			final Stash[] stash = Arrays.stream(createdStashes).map(StashDeserializer::stashFrom).toArray(Stash[]::new);
			for (File createdStash : createdStashes) createdStash.delete();
			FileSystemUtils.removeDir(workingDirectory);
			return stash;
		} catch (Throwable e) {
			return null;
		}
	}

	private File[] findCreatedStashes() {
		return workingDirectory.listFiles((dir, name) -> name.endsWith(".stash"));
	}

	private CompilerConfiguration createConfiguration() {
		CompilerConfiguration configuration = new CompilerConfiguration();
		configuration.model().level(Configuration.Artifact.Model.Level.Solution);
		configuration.setTaraDirectory(new File(new File(java.lang.System.getProperty("user.home")), ".m2"));
		configuration.setOutDirectory(workingDirectory);
		configuration.setResourcesDirectory(workingDirectory);
		configuration.setModule(module);
		configuration.setExcludedPhases(Arrays.asList(1, 8, 10, 11));
		configuration.setMake(true);
		configuration.model().outDsl(module);
		configuration.sourceEncoding(charset.name());
		configuration.out(this.stream);
		if (language == null) configuration.addLanguage(dsl, dslVersion);
		else configuration.addLanguage(language);
		return configuration;
	}
}