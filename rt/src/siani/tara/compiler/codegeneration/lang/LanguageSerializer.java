package siani.tara.compiler.codegeneration.lang;

import siani.tara.compiler.codegeneration.CodeGenerator;
import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.compiler.model.impl.Model;

import javax.tools.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LanguageSerializer extends CodeGenerator {
	private static final Logger LOG = Logger.getLogger(LanguageSerializer.class.getName());
	private static final String JAVA = ".java";

	CompilerConfiguration conf;

	public LanguageSerializer(CompilerConfiguration conf) {
		this.conf = conf;
	}

	public void serialize(Model model) throws TaraException {
		try {
			LanguageCreator creator = new LanguageCreator(conf, model);
			serialize(creator.create(), new File(conf.getLanguageDirectory(), conf.getGeneratedLanguage() + JAVA));
			new File(conf.getLanguageDirectory(), conf.getGeneratedLanguage() + ".reload").createNewFile();
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
			throw new TaraException("Error sav¡ng model: " + e.getMessage());
		}
	}

	private boolean serialize(String content, File file) throws TaraException {
		try {
			file.getParentFile().mkdirs();
//			file.deleteOnExit();
			FileWriter writer = new FileWriter(file);
			writer.write(content);
			writer.close();
			compile(file);
			return true;
		} catch (IOException e) {
			throw new TaraException("Error saving language: " + e.getMessage());
		} catch (InterruptedException e) {
			throw new TaraException("Error compiling language: " + e.getMessage());
		}
	}

	private void compile(File file) throws TaraException, IOException, InterruptedException {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
		Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromStrings(Collections.singletonList(file.getAbsolutePath()));
		final Collection<String> compilerOptions = new ArrayList<>();
		compilerOptions.add("-source");
		compilerOptions.add("1.8");
		compilerOptions.add("-target");
		compilerOptions.add("1.8");
		compilerOptions.add("-d");
		compilerOptions.add(conf.getLanguageDirectory());
		compilerOptions.add("-classpath");
		compilerOptions.add(conf.getSemanticRulesLib());
		JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, compilerOptions, null, compilationUnits);
		if (!task.call()) {
			String message = "";
			for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics())
				message += diagnostic.getMessage(Locale.ENGLISH) + "\n";
			throw new TaraException(message);
		}
		fileManager.close();
	}
}
