package io.intino.tara;

import io.intino.tara.compiler.core.CompilerConfiguration;
import io.intino.tara.io.Stash;
import io.intino.tara.io.StashDeserializer;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.intino.tara.compiler.shared.Configuration.Level.Solution;

public class StashBuilder {

	private final String dsl;
	private final String dslVersion;
	private final String module;
	private final List<File> files;
	private final Language language;
	private final Charset charset;
	private File workingDirectory;

	public StashBuilder(List<File> files, String dsl, String dslVersion, String module) {
		this.files = files;
		this.dsl = dsl;
		this.dslVersion = dslVersion;
		this.module = module;
		this.language = null;
		this.charset = Charset.forName("UTF-8");
		try {
			this.workingDirectory = Files.createTempDirectory("_stash_builder").toFile();
		} catch (IOException ignored) {
		}
	}

	public StashBuilder(Map<File, Charset> files, Language language, String module) {
		this.files = new ArrayList<>(files.keySet());
		this.charset = files.entrySet().iterator().next().getValue();
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
		try {
			new TaraCompilerRunner(false).run(createConfiguration(), files);
			final File createdStash = findCreatedStash();
			if (createdStash == null || !createdStash.exists()) return null;
			final Stash stash = StashDeserializer.stashFrom(createdStash);
			createdStash.delete();
			return stash;
		} catch (Throwable e) {
			return null;
		}
	}

	private File findCreatedStash() {
		final File[] list = workingDirectory.listFiles((dir, name) -> name.endsWith(".stash"));
		if (list == null || list.length == 0) return null;
		return Arrays.asList(list).get(0);
	}

	private CompilerConfiguration createConfiguration() {
		CompilerConfiguration configuration = new CompilerConfiguration();
		configuration.level(Solution);
		configuration.setTaraDirectory(new File(new File(java.lang.System.getProperty("user.home")), ".m2"));
		configuration.setOutDirectory(workingDirectory);
		configuration.setResourcesDirectory(workingDirectory);
		configuration.setModule(module);
		configuration.setExcludedPhases(Arrays.asList(1, 8, 10, 11));
		configuration.setMake(true);
		configuration.systemStashName(module);
		configuration.sourceEncoding(charset.name());
		configuration.setStashGeneration();
		if (language == null) configuration.addLanguage(dsl, dslVersion);
		else configuration.addLanguage(language);
		return configuration;
	}
}