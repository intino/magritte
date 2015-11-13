package tara.intellij.lang;

import com.intellij.openapi.diagnostic.Logger;
import tara.Language;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class LanguageLoader {
	private static final Logger LOG = Logger.getInstance(LanguageLoader.class.getName());

	private LanguageLoader() {
	}

	public static Language load(String name, String languagesDirectory) {
		try {
			File jar = new File(languagesDirectory, name + ".jar");
			if (!jar.exists()) return null;
			final ClassLoader classLoader = createClassLoader(jar);
			if (classLoader == null) return null;
			Class cls = classLoader.loadClass(TaraLanguage.LANGUAGES_PACKAGE + "." + name);
			return (Language) cls.newInstance();
		} catch (ClassNotFoundException | NoClassDefFoundError | InstantiationException | IllegalAccessException e) {
//			LOG.error(e.getMessage(), e);
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
