package tara.compiler.codegeneration;

import tara.compiler.core.errorcollection.TaraException;

import javax.tools.*;
import javax.tools.JavaCompiler.CompilationTask;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

public class JavaCompiler {

	public static void compile(File file, String classPath, File destiny) throws TaraException, IOException, InterruptedException {
		javax.tools.JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
		Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromStrings(Collections.singletonList(file.getAbsolutePath()));
		final Collection<String> compilerOptions = new ArrayList<>();
		compilerOptions.add("-source");
		compilerOptions.add("1.8");
		compilerOptions.add("-target");
		compilerOptions.add("1.8");
		compilerOptions.add("-d");
		compilerOptions.add(destiny.getAbsolutePath());
		compilerOptions.add("-classpath");
		compilerOptions.add(classPath);
		CompilationTask task = compiler.getTask(null, fileManager, diagnostics, compilerOptions, null, compilationUnits);
		if (!task.call()) {
			String message = "";
			for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics())
				message += diagnostic.getMessage(Locale.ENGLISH) + "\n";
			throw new TaraException(message);
		}
		fileManager.close();
	}
}
