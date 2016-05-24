package tara.intellij.lang;

import com.intellij.openapi.diagnostic.Logger;
import tara.Language;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;

class LanguageLoader {
	private static final Logger LOG = Logger.getInstance(LanguageLoader.class.getName());

	private LanguageLoader() {
	}

	public static Language load(String name, String languageDirectory) {
		try {
			File jar = new File(languageDirectory, name + ".jar");
			if (!jar.exists()) return null;
			final ClassLoader classLoader = createClassLoader(jar);
			if (classLoader == null) return null;
			Class cls = classLoader.loadClass(LanguageManager.LANGUAGES_PACKAGE + "." + name);
			return (Language) cls.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | Error e) {
			return null;
		}
	}

	private static ClassLoader createClassLoader(File jar) {
		return AccessController.doPrivileged((PrivilegedAction<ClassLoader>) () -> {
			try {
				return new URLClassLoader(new URL[]{jar.toURI().toURL()}, LanguageLoader.class.getClassLoader());
			} catch (MalformedURLException e) {
				LOG.error(e.getMessage(), e);
				return null;
			}
		});
	}
}
