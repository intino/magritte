package tara.compiler.semantic;

import tara.Language;
import tara.compiler.codegeneration.Format;
import tara.compiler.core.errorcollection.TaraException;
import tara.dsl.Proteo;
import tara.dsl.ProteoConstants;
import tara.dsl.Verso;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.logging.Logger;

public class LanguageLoader {
	private static final Logger LOG = Logger.getGlobal();
	private static final String LANGUAGE_PACKAGE = "tara.dsl";

	private LanguageLoader() {
	}

	public static Language load(String name, String version, String languagesDirectory) throws TaraException {
		if (ProteoConstants.PROTEO.equalsIgnoreCase(name)) return new Proteo();
		if (ProteoConstants.VERSO.equalsIgnoreCase(name)) return new Verso();
		final String[] errorMessage = {"Impossible to create a language instance based in " + name};
		final Language language = AccessController.doPrivileged((PrivilegedAction<Language>) () -> {
			try {
				File jar = getLanguagePath(name, version, languagesDirectory);
				if (!jar.exists()) errorMessage[0] = "Language file not found: " + jar.getPath();
				ClassLoader cl = new URLClassLoader(new URL[]{jar.toURI().toURL()}, LanguageLoader.class.getClassLoader());
				Class cls = cl.loadClass(LANGUAGE_PACKAGE + "." + Format.firstUpperCase().format(name));
				return (Language) cls.newInstance();
			} catch (MalformedURLException | ClassNotFoundException e1) {
				LOG.info(e1.getMessage());
				return null;
			} catch (InstantiationException | IllegalAccessException e2) {
				LOG.info(e2.getMessage());
				return null;
			}
		});
		if (language == null) throw new TaraException(errorMessage[0]);
		return language;

	}

	private static File getLanguagePath(String name, String version, String languagesDirectory) {
		return new File(languagesDirectory + File.separator + name + File.separator + version, name + "-" + version + ".jar");
	}
}
