package tara.lang.model.rules.variable;

import tara.lang.semantics.errorcollector.SemanticNotification.Level;

import java.io.File;
import java.util.Collections;
import java.util.List;


public class FileRule implements VariableRule<List<File>> {

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
		if (!(values.get(0) instanceof File)) return true;
		for (File file : values) {
			if (file == null) continue;
			if (!file.exists()) {
				message = "reject.file.parameter.not.exists";
				return false;
			}
			for (String extension : extensions)
				if (file.getName().endsWith("." + extension)) return true;
			message = "reject.file.parameter.with.unavailable.extension";
			return false;
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

	@Override
	public Level level() {
		return message != null && message.equals("reject.file.parameter.not.exists") ? Level.WARNING : Level.ERROR;
	}
}
