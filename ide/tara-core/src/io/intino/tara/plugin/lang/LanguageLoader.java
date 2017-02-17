package io.intino.tara.plugin.lang;

import com.intellij.openapi.diagnostic.Logger;
import io.intino.tara.Language;
import io.intino.tara.plugin.codeinsight.languageinjection.helpers.Format;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.*;
import java.util.stream.Collectors;

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
			Class cls = classLoader.loadClass(LANGUAGES_PACKAGE + "." + Format.snakeCasetoCamelCase().format(name).toString());
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
		final File[] versionsArray = new File(languageDirectory).listFiles(File::isDirectory);
		if (versionsArray == null || versionsArray.length == 0) return "1.0.0";
		return lastOf(Arrays.stream(versionsArray).map(File::getName).collect(Collectors.toList()));
	}

	private static String lastOf(List<String> versions) {
		Map<String, String> versionMap = new LinkedHashMap<>();
		List<String> names = new ArrayList<>();
		for (String version : versions) {
			final String normalize = normalize(version);
			names.add(normalize);
			versionMap.put(normalize, version);
		}
		Collections.sort(names);
		return versionMap.get(names.get(names.size() - 1));
	}

	private static String normalize(String version) {
		final String[] split = version.split("\\.");
		String result = "";
		for (String number : split) result += number.length() == 1 ? "0" + number : number;
		return result;
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
