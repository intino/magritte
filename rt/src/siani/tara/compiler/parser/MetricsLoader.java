package siani.tara.compiler.parser;

import siani.tara.compiler.core.CompilerConfiguration;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

public class MetricsLoader {

	public static Map<String, List<String>> loadMetrics(CompilerConfiguration config) {
		Map<String, List<String>> map = new HashMap();
		String metricDir = config.getMetricsDirectory().getPath() + File.separator + config.getProject() + File.separator + "metrics";
		if (!new File(metricDir).exists()) return map;
		for (File file : new File(metricDir).listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return !name.contains("$") && name.endsWith(".class");
			}
		})) {
			String className = getClassName(file);
			Class<?> aClass = loadClass(config.getMetricsDirectory().getPath(), config.getProject() + ".metrics." + className);
			map.put(className, extractEnums(aClass));
		}
		return map;
	}

	private static List<String> extractEnums(Class<?> aClass) {
		List<String> enums = new ArrayList<>();
		for (Field field : aClass.getFields())
			enums.add(field.getName());
		return enums;
	}

	private static Class<?> loadClass(String path, String className) {
		File file = new File(path);
		try {
			URL url = file.toURI().toURL();
			URL[] urls = new URL[]{url};
			ClassLoader cl = new URLClassLoader(urls);
			return cl.loadClass(className);
		} catch (MalformedURLException | ClassNotFoundException ignored) {
		}
		return null;
	}

	private static String getClassName(File file) {
		return file.getName().substring(0, file.getName().lastIndexOf("."));
	}
}
