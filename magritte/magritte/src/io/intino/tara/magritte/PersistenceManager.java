package io.intino.tara.magritte;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

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
				return Files.newInputStream(new File(directory, path).toPath());
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}

		@Override
		public void write(String path, InputStream stream) {
			try {
				Files.copy(stream, new File(directory, path).toPath(), REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
