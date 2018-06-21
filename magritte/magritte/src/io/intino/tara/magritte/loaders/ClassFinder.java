package io.intino.tara.magritte.loaders;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.currentThread;

public class ClassFinder {

	private static Map<String, Class> classMap = new HashMap<>();

	public static Class find(String qualifiedName) throws ClassNotFoundException {
		if (!classMap.containsKey(qualifiedName))
			classMap.put(qualifiedName, currentThread().getContextClassLoader().loadClass(qualifiedName));
		return classMap.get(qualifiedName);
	}

	public static InputStream getResourceAsStream(String name) {
		final ClassLoader loader = currentThread().getContextClassLoader();
		return loader.getResourceAsStream(name) != null ? loader.getResourceAsStream(name) : name.startsWith("/") ? loader.getResourceAsStream(name.substring(1)) : null;
	}

	public static URL getResource(String name) {
		final ClassLoader loader = currentThread().getContextClassLoader();
		return loader.getResource(name) != null ? loader.getResource(name) : name.startsWith("/") ? loader.getResource(name.substring(1)) : null;
	}


	public static void clear() {
		classMap.clear();
	}
}
