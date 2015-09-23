package tara.compiler.parser;

import tara.compiler.codegeneration.FileSystemUtils;
import tara.compiler.codegeneration.JavaCompiler;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.errorcollection.TaraException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MetricsLoader {

	private static final Logger LOG = Logger.getLogger(MetricsLoader.class.getName());
	private final CompilerConfiguration config;

	public MetricsLoader(CompilerConfiguration config) {
		this.config = config;
	}

	public Map<String, List<String>> loadMetrics() {
		Map<String, List<String>> map = new HashMap();
		if (config.getMetricsDirectory() == null) return map;
		File metricsDir = config.getMetricsDirectory();
		if (!metricsDir.exists()) return map;
		File compilationDirectory = compileMetrics(metricsDir);
		if (compilationDirectory == null) return map;
		for (File file : collectClasses(compilationDirectory)) {
			if (file.isDirectory() || file.getName().contains("$")) continue;
			String className = getClassName(file);
			Class<?> aClass = loadClass(compilationDirectory.getAbsolutePath(), config.getGeneratedLanguage().toLowerCase() + ".metrics." + className);
			map.put(className, extractEnums(aClass));
		}
		FileSystemUtils.removeDir(compilationDirectory);
		return map;
	}

	private List<File> collectClasses(File compilationDirectory) {
		List<File> files = new ArrayList<>();
		FileSystemUtils.getAllFiles(compilationDirectory, files);
		return files;
	}

	private File compileMetrics(File metricsDir) {
		try {
			File compilationDirectory = Files.createTempDirectory("_compiled").toFile();
			for (File file : metricsDir.listFiles((dir, name) -> {
				return name.endsWith(".java");
			})) {
				JavaCompiler.compile(file, "", compilationDirectory);
			}
			return compilationDirectory;
		} catch (TaraException | IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static List<String> extractEnums(Class<?> aClass) {
		List<String> enums = new ArrayList<>();
		for (Field field : aClass.getFields()) enums.add(field.getName());
		return enums;
	}

	private static Class<?> loadClass(String path, String className) {
		List<URL> classPath = new ArrayList<>();
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
