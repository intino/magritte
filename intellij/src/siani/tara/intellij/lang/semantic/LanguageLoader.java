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
		File file = new File(languagesDirectory);
		try {
			ClassLoader cl = new URLClassLoader(new URL[]{file.toURI().toURL()}, LanguageLoader.class.getClassLoader());
			Class cls = cl.loadClass(TaraLanguage.LANGUAGES_PACKAGE + "." + name);
			return (Language) cls.newInstance();
		} catch (MalformedURLException | ClassNotFoundException | NoClassDefFoundError e1) {
			LOG.info(e1.getMessage());
			return null;
		} catch (InstantiationException | IllegalAccessException e2) {
			return null;
		}
	}
}
