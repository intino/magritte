package io.intino.magritte.builder.compiler.dependencyresolution;

import io.intino.magritte.builder.compiler.core.errorcollection.TaraException;
import io.intino.magritte.builder.compiler.utils.Format;
import io.intino.magritte.builder.compiler.utils.JavaCompiler;
import io.intino.magritte.lang.model.rules.CustomRule;
import io.intino.magritte.lang.model.rules.custom.Url;

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


	static File compile(CustomRule rule, String workingPackage, File rulesDirectory, File classPath, File tempDirectory) throws TaraException {
		final File source = new File(rulesDirectory, rule.externalClass() + ".java");
		if (source.exists()) {
			compile(source, classPath, tempDirectory);
			return new File(tempDirectory, composeQualifiedName(workingPackage, rule.externalClass()).replace(".", File.separator) + ".class");
		} else return null;
	}

	static Class<?> load(CustomRule rule, String workingPackage, File classPath, File tempDirectory) throws TaraException {
		return load(rule.externalClass(), workingPackage, tempDirectory, classPath);
	}

	static Class<?> tryAsProvided(CustomRule rule) {
		try {
			return Class.forName(Url.class.getPackage().getName() + "." + Format.firstUpperCase().format(rule.externalClass()));
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	private static Class<?> compileAndLoadRules(CustomRule rule, String workingPackage, File classPath, File temp, File source) throws TaraException {
		compile(source, classPath, temp);
		return load(rule.externalClass(), workingPackage, temp, classPath);
	}

	private static File compile(File source, File classPath, File compilationDirectory) throws TaraException {
		JavaCompiler.compile(source, classPath.getAbsolutePath(), compilationDirectory);
		compilationDirectory.deleteOnExit();
		return compilationDirectory;
	}

	public static Class<?> load(String source, String workingPackage, File baseDirectory, File classPath) {
		return AccessController.doPrivileged((PrivilegedAction<Class<?>>) () -> {
			try {
				URL url = baseDirectory.toURI().toURL();
				URL[] urls = new URL[]{url, classPath.toURI().toURL()};
				ClassLoader cl = new URLClassLoader(urls);
				return cl.loadClass(composeQualifiedName(workingPackage, source));
			} catch (ClassNotFoundException | MalformedURLException e) {
				LOG.log(Level.SEVERE, "Error loading class " + source + " in " + baseDirectory.getAbsolutePath());
			}
			return null;
		});
	}

	static String composeQualifiedName(String workingPackage, String aClass) {
		return workingPackage.toLowerCase() + ".rules." + aClass;
	}
}
