package tara.lang.model.rules;

import tara.lang.model.Rule;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class FileRule implements Rule<List<File>> {

	private String message;
	private final List<String> extensions;
	private final Size size;

	public FileRule(List<String> extensions, Size size) {
		this.extensions = extensions;
		this.size = size;
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
		return size.accept(values);
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
