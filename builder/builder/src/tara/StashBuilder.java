package tara;

import tara.compiler.core.CompilerConfiguration;
import tara.io.Stash;
import tara.io.StashDeserializer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class StashBuilder {

	private final String dsl;
	private final String module;
	private final File file;
	private File workingDirectory;

	public StashBuilder(File source, String dsl, String module) {
		this.dsl = dsl;
		this.module = module;
		this.file = source;
		try {
			this.workingDirectory = Files.createTempDirectory("_stash_builder").toFile();
		} catch (IOException ignored) {
		}
	}

	public Stash build() {
		new TaraCompilerRunner(true).run(createConfiguration(), file);
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
		configuration.moduleType(CompilerConfiguration.ModuleType.System);
		configuration.systemLanguage(dsl);
		configuration.setOutDirectory(workingDirectory);
		configuration.setResourcesDirectory(workingDirectory);
		configuration.setStashGeneration(true);
		configuration.setModule(module);
		configuration.setExcludedPhases(Arrays.asList(8, 10, 11));
		configuration.setMake(true);
		configuration.systemStashName(module);
		configuration.setTaraDirectory(new File(new File(System.getProperty("user.home")), ".tara"));
		return configuration;
	}
}