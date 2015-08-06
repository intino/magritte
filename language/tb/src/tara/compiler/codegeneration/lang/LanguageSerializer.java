package tara.compiler.codegeneration.lang;

import tara.compiler.codegeneration.FileSystemUtils;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.errorcollection.TaraException;
import tara.compiler.model.Model;

import javax.tools.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.io.File.separator;

public class LanguageSerializer {
	private static final Logger LOG = Logger.getLogger(LanguageSerializer.class.getName());
	private static final String JAVA = ".java";

	CompilerConfiguration conf;

	public LanguageSerializer(CompilerConfiguration conf) {
		this.conf = conf;
	}

	public void serialize(Model model) throws TaraException {
		try {
			File file = new File(conf.getLanguageDirectory(), conf.getGeneratedLanguage() + ".reload");
			if (!file.exists()) file.createNewFile();
			LanguageCreator creator = new LanguageCreator(conf, model);
			serialize(creator.create(), getDslDestiny());
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
			throw new TaraException("Error saving model: " + e.getMessage(), e);
		}
	}

	private File getDslDestiny() {
		final File file = new File(conf.getLanguageDirectory() + separator + conf.getGeneratedLanguage());
		file.mkdirs();
		return new File(file, conf.getGeneratedLanguage() + JAVA);
	}

	private boolean serialize(String content, File destiny) throws TaraException {
		try {
			destiny.getParentFile().mkdirs();
//			destiny.deleteOnExit();
			FileWriter writer = new FileWriter(destiny);
			writer.write(content);
			writer.close();
			compile(destiny);
			jar(destiny.getParentFile());
			return true;
		} catch (IOException e) {
			throw new TaraException("Error saving language: " + e.getMessage(), e);
		} catch (InterruptedException e) {
			throw new TaraException("Error compiling language: " + e.getMessage(), e);
		}
	}

	private void jar(File dslDir) throws IOException {
		Manifest manifest = new Manifest();
		manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
		JarOutputStream target = new JarOutputStream(new FileOutputStream(new File(dslDir, conf.getGeneratedLanguage() + ".jar")), manifest);
		final File gen = new File(dslDir, "tara");
		add(dslDir, gen, target);
		target.close();
		FileSystemUtils.removeDir(gen);
	}

	private void add(File base, File source, JarOutputStream target) throws IOException {
		BufferedInputStream in = null;
		try {
			if (source.isDirectory()) {
				String name = getRelativePath(base, source).replace("\\", "/");
				if (!name.isEmpty()) {
					if (!name.endsWith("/")) name += "/";
					JarEntry entry = new JarEntry(name);
					entry.setTime(source.lastModified());
					target.putNextEntry(entry);
					target.closeEntry();
				}
				for (File nestedFile : source.listFiles())
					add(base, nestedFile, target);
				return;
			}

			JarEntry entry = new JarEntry(getRelativePath(base, source).replace("\\", "/"));
			entry.setTime(source.lastModified());
			target.putNextEntry(entry);
			in = new BufferedInputStream(new FileInputStream(source));

			byte[] buffer = new byte[1024];
			while (true) {
				int count = in.read(buffer);
				if (count == -1)
					break;
				target.write(buffer, 0, count);
			}
			target.closeEntry();
		} finally {
			if (in != null)
				in.close();
		}
	}

	private String getRelativePath(File base, File source) {
		return source.getPath().replace(base.getAbsolutePath() + File.separator, "");
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
		compilerOptions.add(getDslDestiny().getParentFile().getAbsolutePath());
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
