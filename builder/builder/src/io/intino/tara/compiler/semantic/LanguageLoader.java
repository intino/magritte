package io.intino.tara.compiler.semantic;

import io.intino.tara.Language;
import io.intino.tara.compiler.codegeneration.Format;
import io.intino.tara.compiler.core.errorcollection.TaraException;
import io.intino.tara.dsl.Meta;
import io.intino.tara.dsl.Proteo;
import io.intino.tara.dsl.ProteoConstants;

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
		if (ProteoConstants.META.equalsIgnoreCase(name)) return new Meta();
		final String[] errorMessage = {"Impossible to create a language instance based in " + name};
		final Language language = AccessController.doPrivileged((PrivilegedAction<Language>) () -> {
			try {
				File jar = getLanguagePath(name, version, languagesDirectory);
				if (!jar.exists()) errorMessage[0] = "Language file not found: " + jar.getPath();
				ClassLoader cl = new URLClassLoader(new URL[]{jar.toURI().toURL()}, LanguageLoader.class.getClassLoader());
				Class cls = cl.loadClass(LANGUAGE_PACKAGE + "." + Format.toCamelCase().format(name));
				return (Language) cls.newInstance();
			} catch (MalformedURLException | ClassNotFoundException | InstantiationException | IllegalAccessException e1) {
				LOG.info(e1.getMessage());
				return null;
			}
		});
		if (language == null) throw new TaraException(errorMessage[0]);
		return language;

	}

	public static File getLanguagePath(String name, String version, String languagesDirectory) {
		String effectiveVersion = version.equals("LATEST") ? findLatestVersion(new File(languagesDirectory + File.separator + languageGroupId().toLowerCase() + name.toLowerCase())) : version;
		return new File(languagesDirectory + File.separator + languageGroupId().toLowerCase() + name.toLowerCase() + File.separator + effectiveVersion, name + "-" + effectiveVersion + ".jar");
	}

	private static String languageGroupId() {
		return "tara" + File.separator + "dsl" + File.separator;
	}

	private static String findLatestVersion(File languageDirectory) {
		final File[] files = languageDirectory.listFiles(File::isDirectory);
		if (files == null || files.length == 0) return "1.0.0";
		return files[files.length - 1].getName();
	}
}
