package tara.compiler.dependencyresolution;

import tara.compiler.codegeneration.Format;
import tara.compiler.codegeneration.JavaCompiler;
import tara.compiler.core.errorcollection.TaraException;
import tara.lang.model.rules.CustomRule;
import tara.lang.model.rules.custom.Url;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.logging.Level;
import java.util.logging.Logger;

class CustomRuleLoader {

	private static final Logger LOG = Logger.getGlobal();

	private CustomRuleLoader() {
	}

	static Class<?> compileAndLoad(CustomRule rule, String generatedLanguage, File rulesDirectory, File classPath, File tempDirectory) throws TaraException {
		final File source = new File(rulesDirectory, rule.getSource() + ".java");
		if (source.exists()) return compileAndLoadRules(rule, generatedLanguage, classPath, tempDirectory, source);
		else return tryAsProvided(rule);
	}

	private static Class<?> tryAsProvided(CustomRule rule) {
		try {
			return Class.forName(Url.class.getPackage().getName() + "." + Format.reference().format(rule.getSource()));
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	private static Class<?> compileAndLoadRules(CustomRule rule, String generatedLanguage, File classPath, File temp, File source) throws TaraException {
		compile(source, classPath, temp);
		return load(rule.getSource(), generatedLanguage, temp, classPath);
	}

	private static File compile(File source, File classPath, File compilationDirectory) throws TaraException {
		JavaCompiler.compile(source, classPath.getAbsolutePath(), compilationDirectory);
		return compilationDirectory;
	}

	public static Class<?> load(String source, String generatedDslName, File baseDirectory, File classPath) {
		return AccessController.doPrivileged((PrivilegedAction<Class<?>>) () -> {
			try {
				URL url = baseDirectory.toURI().toURL();
				URL[] urls = new URL[]{url, classPath.toURI().toURL()};
				ClassLoader cl = new URLClassLoader(urls);
				return cl.loadClass(composeQualifiedName(generatedDslName, source));
			} catch (ClassNotFoundException | MalformedURLException e) {
				LOG.log(Level.SEVERE, "Error loading class " + source + " in " + baseDirectory.getAbsolutePath());
			}
			return null;
		});
	}

	private static String composeQualifiedName(String generatedDslName, String aClass) {
		return generatedDslName.toLowerCase() + ".rules." + aClass;
	}
}
