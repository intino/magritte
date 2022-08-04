package io.intino.magritte.builder.utils;

import io.intino.magritte.builder.core.errorcollection.TaraException;

import javax.tools.*;
import javax.tools.JavaCompiler.CompilationTask;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

public class JavaCompiler {

	public static void compile(File file, String classPath, File destiny) throws TaraException {
		javax.tools.JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
		Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromStrings(Collections.singletonList(file.getAbsolutePath()));
		final Collection<String> options = new ArrayList<>();
		options.add("-source");
		options.add("11");
		options.add("-target");
		options.add("11");
		options.add("-d");
		options.add(destiny.getAbsolutePath());
		options.add("-classpath");
		options.add(classPath);
		CompilationTask task = compiler.getTask(null, fileManager, diagnostics, options, null, compilationUnits);
		if (!task.call()) {
			StringBuilder message = new StringBuilder();
			for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics())
				message.append(diagnostic.getMessage(Locale.ENGLISH)).append(" in ").append(diagnostic.getLineNumber()).append(":").append(diagnostic.getColumnNumber()).append("\n");
			throw new TaraException(message.toString());
		}
		try {
			fileManager.close();
		} catch (IOException e) {
			throw new TaraException("Error compiling language. " + e.getMessage());
		}
	}
}
