package tara.intellij.lang;

import com.intellij.openapi.diagnostic.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.Language;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;

class LanguageLoader {
	private static final Logger LOG = Logger.getInstance(LanguageLoader.class.getName());
	private static final String LANGUAGES_PACKAGE = "tara.dsl";

	private LanguageLoader() {
	}

	static Language loadLatest(String name, String languageDirectory) {
		return load(name, latestVersion(languageDirectory), languageDirectory);
	}

	@Nullable
	static Language load(String name, String version, String languageDirectory) {
		try {
			File jar = composeLanguagePath(languageDirectory, name, version);
			if (!jar.exists()) return null;
			final ClassLoader classLoader = createClassLoader(jar);
			if (classLoader == null) return null;
			Class cls = classLoader.loadClass(LANGUAGES_PACKAGE + "." + name);
			return (Language) cls.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | Error e) {
			return null;
		}
	}

	@NotNull
	static File composeLanguagePath(String languageDirectory, String name, String version) {
		return new File(languageDirectory, version + File.separator + name + "-" + version + ".jar");
	}

	private static String latestVersion(String languageDirectory) {
		final File[] versions = new File(languageDirectory).listFiles(File::isDirectory);
		return versions == null || versions.length == 0 ? "1.0.0" : versions[versions.length - 1].getName();
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
