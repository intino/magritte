package tara.magritte.loaders;

import java.io.InputStream;
import java.net.URL;

public class ClassFinder {

	public static Class find(String qualifiedName) throws ClassNotFoundException {
		return Thread.currentThread().getContextClassLoader().loadClass(qualifiedName);
	}

	public static InputStream getResourceAsStream(String name) {
		final ClassLoader loader = Thread.currentThread().getContextClassLoader();
		return loader.getResourceAsStream(name) != null ? loader.getResourceAsStream(name) : name.startsWith("/") ? loader.getResourceAsStream(name.substring(1)) : null;
	}

	public static URL getResource(String name) {
		final ClassLoader loader = Thread.currentThread().getContextClassLoader();
		return loader.getResource(name) != null ? loader.getResource(name) : name.startsWith("/") ? loader.getResource(name.substring(1)) : null;
	}
}
