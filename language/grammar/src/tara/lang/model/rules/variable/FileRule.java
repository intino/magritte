package tara.lang.model.rules.variable;

import tara.lang.model.Rule;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class FileRule implements Rule<List<File>> {

	private String message;
	private final List<String> extensions;

	public FileRule(List<String> extensions) {
		this.extensions = extensions;
	}

	@Override
	public boolean accept(List<File> value, String metric) {
		return accept(value);
	}

	@Override
	public boolean accept(List<File> values) {
		for (File file : values) {
			if (!file.exists()) {
				message = "reject.file.parameter.not.exists";
				return false;
			}
			for (String extension : extensions)
				if (!file.getName().endsWith("." + extension)) {
					message = "reject.file.parameter.with.unavailable.extension";
					return false;
				}
		}
		return true;
	}

	public List<String> getAllowedExtensions() {
		return extensions;
	}

	@Override
	public String errorMessage() {
		return message;
	}

	@Override
	public List<Object> errorParameters() {
		return Collections.singletonList(String.join(", ", extensions));
	}
}
