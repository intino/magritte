package tara.intellij.lang;

import com.intellij.openapi.application.PathManager;

import java.io.File;

import static java.io.File.separator;

public class LanguageFactory {


	private static final String PROTEO_LIB = "Proteo.jar";
	private static final String PROTEO_DIRECTORY = PathManager.getPluginsPath() + separator + "tara" + separator + "lib";

	public static ImportedLanguage getProteo() {
		return new ImportedLanguage(new File(PROTEO_DIRECTORY, PROTEO_LIB));
	}


	public static class ImportedLanguage {
		File path = null;
		int level = 2;
		boolean isExtensible = false;
		File pathToSource = null;

		public ImportedLanguage(File path) {
			this.path = path;
		}

		public ImportedLanguage(File path, int level) {
			this.level = level;
			this.path = path;
		}

		public ImportedLanguage(File path, File pathToSource) {
			this.path = path;
			this.isExtensible = true;
			this.pathToSource = pathToSource;
		}

		public ImportedLanguage(File path, int level, File pathToSource) {
			this.path = path;
			this.level = level;
			this.isExtensible = true;
			this.pathToSource = pathToSource;
		}

		public File path() {
			return path;
		}

		public boolean isExtensible() {
			return isExtensible;
		}

		public File pathToSource() {
			return pathToSource;
		}

		public int getLevel() {
			return level;
		}
	}
}
