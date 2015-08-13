package tara.intellij.lang;

import com.intellij.openapi.diagnostic.Logger;
import tara.Language;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class LanguageLoader {
	private static final Logger LOG = Logger.getInstance(LanguageLoader.class.getName());

	private LanguageLoader() {
	}

	public static Language load(String name, String languagesDirectory) {
		try {
			File jar = new File(languagesDirectory, name + ".jar");
			if (!jar.exists()) return null;
			SecurityManager manager = System.getSecurityManager();
			manager.checkCreateClassLoader();
			ClassLoader cl = new URLClassLoader(new URL[]{jar.toURI().toURL()}, LanguageLoader.class.getClassLoader());
			Class cls = cl.loadClass(TaraLanguage.LANGUAGES_PACKAGE + "." + name);
			return (Language) cls.newInstance();
		} catch (MalformedURLException | ClassNotFoundException | NoClassDefFoundError | InstantiationException | IllegalAccessException e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}
}
