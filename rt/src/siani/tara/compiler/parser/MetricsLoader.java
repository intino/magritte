package siani.tara.compiler.parser;

import siani.tara.compiler.core.CompilerConfiguration;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MetricsLoader {

	private static final Logger LOG = Logger.getLogger(MetricsLoader.class.getName());

	private MetricsLoader() {
	}

	public static Map<String, List<SimpleEntry<String, String>>> loadMetrics(CompilerConfiguration config) {
		Map<String, List<SimpleEntry<String, String>>> map = new HashMap();
		String metricDir = config.getMetricsDirectory().getPath() + File.separator + config.getProject() + File.separator + "metrics";
		if (!new File(metricDir).exists()) return map;
		for (File file : new File(metricDir).listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return !name.contains("$") && name.endsWith(".class");
			}
		})) {
			String className = getClassName(file);
			Class<?> aClass = loadClass(config.getMetricsDirectory().getPath(), config.getProject().toLowerCase() + ".metrics." + className, getLibs(config.getTdkHome()));
			map.put(className, extractEnums(aClass));
		}
		return map;
	}

	private static List<URL> getLibs(String tdkHome) {
		List<URL> urls = new ArrayList<>();
		for (File file : new File(tdkHome).listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".jar");
			}
		})) {
			try {
				urls.add(file.toURI().toURL());
			} catch (MalformedURLException e) {
				LOG.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		return urls;
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
		File file = new File(path);
		if (!file.exists()) return null;
		try {
			URL url = file.toURI().toURL();
			urls.add(url);
			ClassLoader cl = new URLClassLoader(urls.toArray(new URL[urls.size()]));
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
