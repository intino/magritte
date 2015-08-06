package tara.compiler.semantic;

import tara.Language;
import tara.compiler.core.errorcollection.TaraException;
import tara.dsl.Proteo;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Logger;

public class LanguageLoader {
	private static final Logger LOG = Logger.getLogger(LanguageLoader.class.getName());
	private static final String LANGUAGE_PACKAGE = "tara.dsl";

	private LanguageLoader() {
	}

	public static Language load(String name, String languagesDirectory) throws TaraException {
		if (name.equalsIgnoreCase("Proteo")) return new Proteo();
		try {
			File jar = getLanguagePath(name, languagesDirectory);
			if (!jar.exists()) throw new TaraException("Language file not found: " + jar.getPath());
			ClassLoader cl = new URLClassLoader(new URL[]{jar.toURI().toURL()}, LanguageLoader.class.getClassLoader());
			Class cls = cl.loadClass(LANGUAGE_PACKAGE + "." + name);
			return (Language) cls.newInstance();
		} catch (MalformedURLException | ClassNotFoundException e1) {
			LOG.info(e1.getMessage());
			e1.printStackTrace();
			return null;
		} catch (InstantiationException | IllegalAccessException e2) {
			e2.printStackTrace();
			throw new TaraException("Impossible to create a language instance based in " + name);
		}
	}

	private static File getLanguagePath(String name, String languagesDirectory) {
		return new File(languagesDirectory + File.separator + name, name + ".jar");
	}
}
