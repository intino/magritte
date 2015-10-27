package tara.lang.model.rules;

import tara.lang.model.Rule;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileRule implements Rule<File> {

	private String message;
	private final List<String> extensions;

	public FileRule(String... extensions) {
		this.extensions = Arrays.asList(extensions);
	}

	@Override
	public boolean accept(File value) {
		if (!value.exists()) {
			message = "reject.file.parameter.not.exists";
			return false;
		}
		for (String extension : extensions) if (value.getName().endsWith("." + extension)) return true;
		message = "reject.file.parameter.with.unavailable.extension";
		return false;
	}

	public List<String> getAllowedExtensions() {
		return extensions;
	}

	@Override
	public String errorMessage() {
		return message;
	}

	@Override
	public List<String> errorParameters() {
		return Collections.singletonList(String.join(", ", extensions));
	}
}
