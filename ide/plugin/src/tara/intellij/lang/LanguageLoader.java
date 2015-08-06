package tara.intellij.lang;

import tara.Language;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class LanguageLoader {

	private LanguageLoader() {
	}

	public static Language load(String name, String languagesDirectory) {
		try {
			File jar = new File(languagesDirectory, name + ".jar");
			if (!jar.exists()) return null;
			ClassLoader cl = new URLClassLoader(new URL[]{jar.toURI().toURL()}, LanguageLoader.class.getClassLoader());
			Class cls = cl.loadClass(TaraLanguage.LANGUAGES_PACKAGE + "." + name);
			return (Language) cls.newInstance();
		} catch (MalformedURLException | ClassNotFoundException | NoClassDefFoundError e1) {
			e1.printStackTrace();
			return null;
		} catch (InstantiationException | IllegalAccessException e2) {
			e2.printStackTrace();
			return null;
		} catch (NullPointerException e3) {
			System.out.println("Name: " + name + ". Language: " + languagesDirectory);
			return null;
		}
	}
}
