package io.intino.tara.compiler.codegeneration;

import io.intino.tara.compiler.core.errorcollection.TaraException;

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
		options.add("1.8");
		options.add("-target");
		options.add("1.8");
		options.add("-d");
		options.add(destiny.getAbsolutePath());
		options.add("-classpath");
		options.add(classPath);
		CompilationTask task = compiler.getTask(null, fileManager, diagnostics, options, null, compilationUnits);
		if (!task.call()) {
			StringBuilder message = new StringBuilder();
			for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics())
				message.append(diagnostic.getMessage(Locale.ENGLISH)).append("\n");
			throw new TaraException(message.substring(0, message.indexOf("\n")));
		}
		try {
			fileManager.close();
		} catch (IOException e) {
			throw new TaraException("Error compiling language. " + e.getMessage());
		}
	}
}
