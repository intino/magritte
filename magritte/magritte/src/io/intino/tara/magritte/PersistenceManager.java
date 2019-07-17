package io.intino.tara.magritte;

import sun.misc.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public interface PersistenceManager {
	InputStream read(String path);

	void write(String path, InputStream stream);

	class FilePersistenceManager implements PersistenceManager {
		private final File directory;

		public FilePersistenceManager(File directory) {
			this.directory = directory;
		}

		@Override
		public InputStream read(String path) {
			try {
				if(!new File(directory, path).exists()) return new ByteArrayInputStream(new byte[0]);
				return Files.newInputStream(new File(directory, path).toPath());
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}

		@Override
		public void write(String path, InputStream stream) {
			try {
				File file = new File(directory, path);
				file.getParentFile().mkdirs();
				Files.copy(stream, file.toPath(), REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	class InMemoryPersistenceManager implements PersistenceManager {
		private final Map<String, byte[]> content = new HashMap<>();

		@Override
		public InputStream read(String path) {
			return new ByteArrayInputStream(content.getOrDefault(path, new byte[0]));
		}

		@Override
		public void write(String path, InputStream stream) {
			try {
				content.put(path, IOUtils.readFully(stream, -1, true));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
