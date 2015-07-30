package tara.compiler.parser;

import tara.compiler.core.CompilerConfiguration;

import java.io.File;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.AbstractMap.SimpleEntry;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MetricsLoader {

	private static final Logger LOG = Logger.getLogger(MetricsLoader.class.getName());

	private MetricsLoader() {
	}

	public static Map<String, List<SimpleEntry<String, String>>> loadMetrics(CompilerConfiguration config) {
		Map<String, List<SimpleEntry<String, String>>> map = new HashMap();
		if(config.getMetricsDirectory() == null) return map;
		String metricDir = config.getMetricsDirectory().getPath() + File.separator + config.getProject() + File.separator + "metrics";
		if (!new File(metricDir).exists()) return map;
		for (File file : new File(metricDir).listFiles((dir, name) -> !name.contains("$") && name.endsWith(".class"))) {
			String className = getClassName(file);
			Class<?> aClass = loadClass(config.getMetricsDirectory().getPath(), config.getProject().toLowerCase() + ".metrics." + className, magritteLib(config));
			map.put(className, extractEnums(aClass));
		}
		return map;
	}

	private static List<URL> magritteLib(CompilerConfiguration config) {
		try {
			return Collections.singletonList(new File(config.magriteLibrary()).toURI().toURL());
		} catch (MalformedURLException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
		}
		return Collections.emptyList();
	}

	private static List<SimpleEntry<String, String>> extractEnums(Class<?> aClass) {
		List<SimpleEntry<String, String>> enums = new ArrayList<>();
		for (Field field : aClass.getFields()) {
			Enum anEnum = Enum.valueOf((Class<Enum>) aClass, field.getName());
			enums.add(new SimpleEntry<>(field.getName(), anEnum.toString()));
		}
		return enums;
	}

	private static Class<?> loadClass(String path, String className, List<URL> urls) {
		List<URL> classPath = new ArrayList<>(urls);
		File file = new File(path);
		if (!file.exists()) return null;
		try {
			URL url = file.toURI().toURL();
			classPath.add(url);
			ClassLoader cl = new URLClassLoader(classPath.toArray(new URL[classPath.size()]));
			return cl.loadClass(className);
		} catch (MalformedURLException | ClassNotFoundException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
		}
		return null;
	}

	private static String getClassName(File file) {
		return file.getName().substring(0, file.getName().lastIndexOf("."));
	}
}
