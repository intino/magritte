package io.intino.tara;

import io.intino.tara.compiler.core.CompilerConfiguration;
import io.intino.tara.io.Stash;
import io.intino.tara.io.StashDeserializer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class StashBuilder {

	private final String dsl;
	private final String dslVersion;
	private final String module;
	private final File file;
	private final Language language;
	private File workingDirectory;

	public StashBuilder(File source, String dsl, String dslVersion, String module) {
		this.dsl = dsl;
		this.dslVersion = dslVersion;
		this.module = module;
		this.file = source;
		this.language = null;
		try {
			this.workingDirectory = Files.createTempDirectory("_stash_builder").toFile();
		} catch (IOException ignored) {
		}
	}

	public StashBuilder(File source, Language language, String module) {
		file = source;
		this.language = language;
		this.dsl = language.languageName();
		this.dslVersion = null;
		this.module = module;
		try {
			this.workingDirectory = Files.createTempDirectory("_stash_builder").toFile();
		} catch (IOException ignored) {
		}
	}

	public Stash build() {
		new TaraCompilerRunner(false).run(createConfiguration(), file);
		final File createdStash = findCreatedStash();
		if (createdStash == null || !createdStash.exists()) return null;
		final Stash stash = StashDeserializer.stashFrom(createdStash);
		createdStash.delete();
		return stash;
	}

	private File findCreatedStash() {
		final File[] list = workingDirectory.listFiles((dir, name) -> name.endsWith(".stash"));
		if (list == null || list.length == 0) return null;
		return Arrays.asList(list).get(0);
	}

	private CompilerConfiguration createConfiguration() {
		CompilerConfiguration configuration = new CompilerConfiguration();
		configuration.level(CompilerConfiguration.Level.System);
		configuration.setTaraDirectory(new File(new File(System.getProperty("user.home")), ".tara"));
		configuration.setOutDirectory(workingDirectory);
		configuration.setResourcesDirectory(workingDirectory);
		configuration.setStashGeneration(true);
		configuration.setModule(module);
		configuration.setExcludedPhases(Arrays.asList(1, 8, 10, 11));
		configuration.setMake(true);
		configuration.systemStashName(module);
		if (language == null) {
			configuration.language(dsl);
			configuration.dslVersion(dslVersion);
		} else configuration.language(language);
		return configuration;
	}
}