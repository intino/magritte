package siani.tara.intellij.annotator.semanticanalizer;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.CompilerModuleExtension;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.OrderEntry;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.annotator.TaraAnnotator;
import siani.tara.intellij.annotator.fix.CreateMeasureClassIntention;
import siani.tara.intellij.lang.lexer.TaraPrimitives;
import siani.tara.intellij.lang.psi.Contract;
import siani.tara.intellij.lang.psi.TaraAttributeType;
import siani.tara.intellij.lang.psi.Variable;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.facet.TaraFacet;
import siani.tara.intellij.project.module.ModuleProvider;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

import static java.io.File.separator;
import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;

public class MetricClassCreationAnalyzer extends TaraAnalyzer {

	private static final Logger LOG = Logger.getInstance(MetricClassCreationAnalyzer.class.getName());

	private final String metricsPackage;
	private final Contract contract;
	private final TaraAttributeType attribute;

	public MetricClassCreationAnalyzer(TaraAttributeType contract) {
		this.contract = contract.getContract();
		this.attribute = contract;
		final String generatedDslName = TaraFacet.getTaraFacetByModule(ModuleProvider.getModuleOf(contract)).getConfiguration().getGeneratedDslName();
		metricsPackage = generatedDslName + "." + "metrics";
	}

	@Override
	public void analyze() {
		if (!Variable.class.isInstance(attribute.getParent()) || !TaraPrimitives.MEASURE.equals(((Variable) attribute.getParent()).getType()))
			return;
		Module module = getModule();
		String measureName = contract.getFormattedName();
		File metricClassFile = getClassFile(module, measureName);
		Map.Entry<Long, Class<?>> savedClass = Metrics.getInstance().get(measureName);
		File javaFile = findJavaFile(module, measureName);

		if (javaFile == null || !javaFile.exists()) {
			error();
			return;
		}

		Class<?> metricValue;
		if (savedClass == null || isOld(savedClass.getKey(), javaFile) || (metricValue = loadCompiledMetricClass(module, measureName)) == null) {
			compile(module, javaFile);
			metricValue = loadCompiledMetricClass(module, measureName);
		}
		if (metricValue != null) Metrics.getInstance().add(metricValue, metricClassFile.lastModified());
		else error();
	}

	private boolean isOld(Long lastModified, File classFile) {
		return classFile.lastModified() > lastModified;
	}

	private void compile(final Module module, File file) {
		try {
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
			StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
			Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromStrings(Collections.singletonList(file.getAbsolutePath()));
			Iterable<String> options = Arrays.asList("-d", getDestiny(module), "-source", "1.8", "-target", "1.8", "-classpath", getClassPath());
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

	private String getClassPath() {
		final File magritteLibrary = findMagritteLibrary();
		return magritteLibrary != null && magritteLibrary.exists() ? magritteLibrary.getAbsolutePath() : "";
	}

	@NotNull
	private File getClassFile(Module module, String measureName) {
		File outDir = getOutDir(module);
		if (outDir == null) return new File("");
		return new File(outDir, (metricsPackage.toLowerCase() + "." + measureName).replace(".", separator) + ".class");
	}

	private File getOutDir(Module module) {
		try {
			if (module == null || getCompilerOutputPath(module) == null) return null;
			final URL compilerOutputPath = getCompilerOutputPath(module);
			if (compilerOutputPath == null) return null;
			return new File(compilerOutputPath.toURI().getPath());
		} catch (URISyntaxException e) {
			return null;
		}
	}

	private URL getCompilerOutputPath(Module module) {
		try {
			final CompilerModuleExtension instance = CompilerModuleExtension.getInstance(module);
			if (instance == null || instance.getCompilerOutputUrl() == null) return null;
			return new URL(instance.getCompilerOutputUrl());
		} catch (MalformedURLException e) {
			return null;
		}
	}

	private File findJavaFile(Module module, String metricName) {
		if (module == null) return null;
		Collection<VirtualFile> sourceRoots = TaraUtil.getSourceRoots(module);
		VirtualFile srcRoot = TaraUtil.getSrcRoot(sourceRoots);
		return new File(new File(srcRoot.getPath()), separator + (metricsPackage.toLowerCase() + "." + metricName).replace(".", separator) + ".java");
	}

	private Class<?> loadCompiledMetricClass(@NotNull Module module, String className) {
		try {
			if (getOutDir(module) == null) {
				Notifications.Bus.notify(new Notification("Tara", "Metric Class Generation", "Out-Directory of Module " + module.getName() + " not found", NotificationType.ERROR), module.getProject());
				throw new Exception("Null Out directory of Module");
			} else
				return loadClass(getOutDir(module).getAbsolutePath(), metricsPackage.toLowerCase() + "." + className);
		} catch (Exception | UnsupportedClassVersionError e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	private Class<?> loadClass(String path, String className) {
		File file = new File(path);
		try {
			final File magritteLibrary = findMagritteLibrary();
			if (magritteLibrary == null || !magritteLibrary.exists()) return null;
			ClassLoader cl = new URLClassLoader(new URL[]{file.toURI().toURL(), magritteLibrary.toURI().toURL()});
			return cl.loadClass(className);
		} catch (MalformedURLException | ClassNotFoundException e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	private File findMagritteLibrary() {
		final Module moduleOf = ModuleProvider.getModuleOf(contract);
		final ModuleRootManager instance = ModuleRootManager.getInstance(moduleOf);
		for (OrderEntry library : instance.getOrderEntries())
			if (isMagritteLibrary(library))
				return new File(library.getFiles(OrderRootType.CLASSES)[0].getPath().replace("!", ""));
		return null;
	}

	private boolean isMagritteLibrary(OrderEntry library) {
		for (VirtualFile virtualFile : library.getFiles(OrderRootType.CLASSES))
			if (virtualFile.getName().contains("magritte")) return true;
		return false;
	}

	private void error() {
		results.put(contract,
			new TaraAnnotator.AnnotateAndFix(ERROR, "Metric Not Found. Create it.",
				new CreateMeasureClassIntention(contract.getFormattedName(), metricsPackage.toLowerCase())));
	}

	private Module getModule() {
		return ModuleProvider.getModuleOf(contract.getContainingFile());
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
			return metrics.put(metric.getSimpleName(), new AbstractMap.SimpleEntry<>(date, metric));
		}

		public Map.Entry<Long, Class<?>> get(String measure) {
			return metrics.get(measure);
		}
	}
}
