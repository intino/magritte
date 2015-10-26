package tara.compiler.dependencyresolution;

import tara.compiler.codegeneration.JavaCompiler;
import tara.compiler.core.errorcollection.TaraException;
import tara.lang.model.rules.CustomRule;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;

public class RuleLoader {
	public static Class<?> compileAndLoad(CustomRule rule, String generatedLanguage, File rulesDirectory, File classPath) {
		File compilationDirectory = tempDirectory();
		compile(new File(rulesDirectory, rule.getSource() + ".java"), classPath, compilationDirectory);
		return load(rule.getSource(), generatedLanguage, compilationDirectory);
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

	public static Class<?> load(String source, String generatedDslName, File outDirectory) {
		try {
			URL url = outDirectory.toURI().toURL();
			URL[] urls = new URL[]{url};
			ClassLoader cl = new URLClassLoader(urls);
			return cl.loadClass(composeQualifiedName(generatedDslName, source));
		} catch (ClassNotFoundException | MalformedURLException e) {
			e.printStackTrace();
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
		return generatedDslName + ".rules." + aClass;
	}


}
