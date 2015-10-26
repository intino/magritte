package tara.lang.model.rules;

import tara.lang.model.Rule;

import java.io.File;

public class FileRule implements Rule<File> {

	private final String[] extensions;

	public FileRule(String... extensions) {
		this.extensions = extensions;
	}

	@Override
	public boolean accept(File value) {
		for (String extension : extensions) if (value.getName().endsWith("." + extension)) return true;
		return false;
	}

	public String[] getAllowedExtensions() {
		return extensions;
	}
}
