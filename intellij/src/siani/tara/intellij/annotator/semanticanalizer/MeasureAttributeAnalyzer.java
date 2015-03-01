package siani.tara.intellij.annotator.semanticanalizer;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.CompilerModuleExtension;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.annotator.TaraAnnotator;
import siani.tara.intellij.annotator.fix.CreateMeasureClassIntention;
import siani.tara.intellij.lang.psi.TaraAttributeType;
import siani.tara.intellij.lang.psi.TaraMeasureAttribute;
import siani.tara.intellij.lang.psi.TaraMeasureType;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleProvider;

import javax.tools.*;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

import static java.io.File.separator;
import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.WARNING;

public class MeasureAttributeAnalyzer extends TaraAnalyzer {

	private static final Logger LOG = Logger.getInstance(MeasureAttributeAnalyzer.class.getName());

	private final String metricsPackage;
	private final TaraMeasureType measure;
	private final TaraAttributeType attribute;

	public MeasureAttributeAnalyzer(TaraAttributeType measure) {
		this.measure = measure.getMeasureType();
		this.attribute = measure;
		metricsPackage = measure.getProject().getName() + "." + "metrics";
	}

	@Override
	public void analyze() {
		if (!TaraMeasureAttribute.class.isInstance(attribute.getParent())) return;
		Module module = getModule();
		String measureName = measure.getText();
		File metricClassFile = getClassFile(module, measureName);
		Map.Entry<Long, Class<?>> savedClass = Metrics.getInstance().get(measureName);
		File javaFile = findJavaFile(module, measureName);
		if (savedClass == null || !metricClassFile.exists() || isOld(savedClass.getKey(), javaFile)) {
			if (!javaFile.exists()) error();
			else {
				if ((savedClass == null && !metricClassFile.exists()) || (savedClass != null && isOld(savedClass.getKey(), javaFile)))
					compile(module, javaFile);
				Class<?> metricValue = loadCompiledMetricClass(module, measureName);
				if (metricValue != null) Metrics.getInstance().add(metricValue, metricClassFile.lastModified());
				else error();
			}
		}
	}

	private boolean isOld(Long lastModified, File classFile) {
		return classFile.lastModified() > lastModified;
	}

	private void compile(final Module module, File file) {
		try {
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
			StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
			Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromStrings(Arrays.asList(file.getAbsolutePath()));
			Iterable<String> options = Arrays.asList("-d", getDestiny(module), "-classpath", getClassPath(module));
			JavaCompiler.CompilationTask task = compiler.getTask(new PrintWriter(System.err), fileManager, diagnostics, options, null, compilationUnits);
			task.call();
			fileManager.close();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	private String getDestiny(Module module) {
		String destiny = getOutDir(module) + separator;
		new File(destiny).mkdirs();
		return destiny;
	}

	private String getClassPath(Module module) {
		String classpath = "";
		for (File file : getJdkHome(module).listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".jar");
			}
		})) {
			classpath += file.getAbsolutePath() + " ";
		}
		return classpath.trim();
	}

	private File getJdkHome(Module module) {
		return new File(ModuleRootManager.getInstance(module).getSdk().getHomePath() + separator + "lib");
	}

	private File getClassFile(Module module, String measureName) {
		File outDir = getOutDir(module);
		if (outDir == null) return null;
		return new File(outDir, (metricsPackage.toLowerCase() + "." + measureName).replace(".", separator) + ".class");
	}

	private File getOutDir(Module module) {
		VirtualFile compilerOutputPath = CompilerModuleExtension.getInstance(module).getCompilerOutputPath();
		if (compilerOutputPath == null) return null;
		return new File(compilerOutputPath.getPath());
	}

	private File findJavaFile(Module module, String metricName) {
		Collection<VirtualFile> sourceRoots = TaraUtil.getSourceRoots(module);
		VirtualFile srcRoot = TaraUtil.getSrcRoot(sourceRoots);
		return new File(new File(srcRoot.getPath()), separator + (metricsPackage + "." + metricName).replace(".", separator) + ".java");
	}

	private Class<?> loadCompiledMetricClass(@NotNull Module module, String className) {
		try {
			return loadClass(getOutDir(module).getAbsolutePath(), getJdkHome(module), metricsPackage.toLowerCase() + "." + className);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	private Class<?> loadClass(String path, File sdk, String className) {
		File file = new File(path);
		try {
			ClassLoader cl = new URLClassLoader(new URL[]{file.toURI().toURL(), new File(sdk, "magritte.jar").toURI().toURL()});
			return cl.loadClass(className);
		} catch (MalformedURLException | ClassNotFoundException e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	private void error() {
		results.put(measure,
			new TaraAnnotator.AnnotateAndFix(WARNING, "Metric Not Found. Create and Compile it.",
				new CreateMeasureClassIntention(measure.getText(), metricsPackage.toLowerCase())));
	}

	private Module getModule() {
		return ModuleProvider.getModuleOf(measure.getContainingFile());
	}

	private static class Metrics {

		private static Metrics instance;
		private static Map<String, Map.Entry<Long, Class<?>>> metrics = new HashMap<>();

		private Metrics() {
		}

		public static Metrics getInstance() {
			if (instance == null) instance = new Metrics();
			return instance;
		}

		public static Map<String, Map.Entry<Long, Class<?>>> getMetrics() {
			return metrics;
		}

		public Map.Entry<Long, Class<?>> add(Class<?> metric, Long date) {
			return metrics.put(metric.getSimpleName(), new AbstractMap.SimpleEntry<Long, Class<?>>(date, metric));
		}

		public Map.Entry<Long, Class<?>> get(String measure) {
			return metrics.get(measure);
		}
	}
}
