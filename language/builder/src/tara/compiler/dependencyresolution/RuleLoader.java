package tara.compiler.dependencyresolution;

import tara.compiler.codegeneration.Format;
import tara.compiler.codegeneration.JavaCompiler;
import tara.compiler.core.errorcollection.TaraException;
import tara.lang.model.rules.custom.Url;
import tara.lang.model.rules.variable.CustomRule;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.util.logging.Logger;

public class RuleLoader {

	private static final Logger LOG = Logger.getLogger(RuleLoader.class.getName());

	public static Class<?> compileAndLoad(CustomRule rule, String generatedLanguage, File rulesDirectory, File classPath) {
		File compilationDirectory = tempDirectory();
		final File source = new File(rulesDirectory, rule.getSource() + ".java");
		if (source.exists()) return compileAndLoad(rule, generatedLanguage, classPath, compilationDirectory, source);
		else return tryAsProvided(rule);
	}

	public static Class<?> tryAsProvided(CustomRule rule) {
		try {
			return Class.forName(Url.class.getPackage().getName() + "." + Format.reference().format(rule.getSource()));
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	private static Class<?> compileAndLoad(CustomRule rule, String generatedLanguage, File classPath, File compilationDirectory, File source) {
		compile(source, classPath, compilationDirectory);
		return load(rule.getSource(), generatedLanguage, compilationDirectory, classPath);
	}

	public static File compile(File source, File classPath, File compilationDirectory) {
		try {
			JavaCompiler.compile(source, classPath.getAbsolutePath(), compilationDirectory);
			return compilationDirectory;
		} catch (TaraException | IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Class<?> load(String source, String generatedDslName, File baseDirectory, File classPath) {
		try {
			URL url = baseDirectory.toURI().toURL();
			URL[] urls = new URL[]{url, classPath.toURI().toURL()};
			ClassLoader cl = new URLClassLoader(urls);
			return cl.loadClass(composeQualifiedName(generatedDslName, source));
		} catch (ClassNotFoundException | MalformedURLException e) {
			LOG.severe("Error loading class " + source + " in " + baseDirectory.getAbsolutePath());
		}
		return null;
	}

	public static File tempDirectory() {
		try {
			return Files.createTempDirectory("_compiled").toFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String composeQualifiedName(String generatedDslName, String aClass) {
		return generatedDslName.toLowerCase() + ".rules." + aClass;
	}
}
