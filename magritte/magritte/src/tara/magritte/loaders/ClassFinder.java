package tara.magritte.loaders;

public class ClassFinder {

	public static Class find(String qualifiedName) throws ClassNotFoundException {
		return Thread.currentThread().getContextClassLoader().loadClass(qualifiedName);
	}

	public static ClassLoader classLoader() {
		return Thread.currentThread().getContextClassLoader();
	}
}
