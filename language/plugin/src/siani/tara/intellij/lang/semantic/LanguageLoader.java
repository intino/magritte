package siani.tara.intellij.lang.semantic;

import com.intellij.openapi.diagnostic.Logger;
import siani.tara.Language;
import siani.tara.intellij.lang.TaraLanguage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class LanguageLoader {
	private static final Logger LOG = Logger.getInstance(LanguageLoader.class.getName());

	public static Language load(String name, String languagesDirectory) {
		try {
			File jar = new File(languagesDirectory, name + ".jar");
			if (!jar.exists()) return null;
			ClassLoader cl = new URLClassLoader(new URL[]{new URL("jar:" + jar.toURI().toURL() + "!/")}, LanguageLoader.class.getClassLoader());
			Class cls = cl.loadClass(TaraLanguage.LANGUAGES_PACKAGE + "." + name);
			return (Language) cls.newInstance();
		} catch (MalformedURLException | ClassNotFoundException | NoClassDefFoundError e1) {
			return null;
		} catch (InstantiationException | IllegalAccessException e2) {
			return null;
		}
	}
}
