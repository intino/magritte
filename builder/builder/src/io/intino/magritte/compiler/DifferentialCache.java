package io.intino.magritte.compiler;

import io.intino.magritte.compiler.model.NodeImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

public class DifferentialCache {
	private final File directory;
	List<String> hashCodes;

	public DifferentialCache(File directory) {
		this.directory = directory;
		directory.mkdirs();
		loadCache();
	}

	private void loadCache() {
		File file = cacheFile();
		try {
			hashCodes = !file.exists() ? new ArrayList<>() : Files.readAllLines(file.toPath());
		} catch (IOException e) {
			new ArrayList<>();
		}
	}

	private File cacheFile() {
		return new File(directory, ".cache");
	}

	public void saveCache(List<String> codes) {
		try {
			Files.writeString(cacheFile().toPath(), String.join("\n", codes), CREATE, TRUNCATE_EXISTING);
		} catch (IOException ignored) {
		}
	}


	public boolean isModified(NodeImpl node) {
		return !hashCodes.contains(node.getHashCode());
	}
}
