package siani.tara.intellij.annotator.semanticAnalizers;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.CompilerModuleExtension;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.annotator.TaraAnnotator;
import siani.tara.intellij.annotator.fix.CreateMeasureClassIntention;
import siani.tara.intellij.lang.psi.TaraAttributeType;
import siani.tara.intellij.lang.psi.TaraMeasureAttribute;
import siani.tara.intellij.project.module.ModuleProvider;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.WARNING;

public class MeasureAttributeAnalyzer extends TaraAnalyzer {
	private final String metricsPackage;
	private final TaraAttributeType measure;

	public MeasureAttributeAnalyzer(TaraAttributeType measure) {
		this.measure = measure;
		metricsPackage = measure.getProject().getName() + "." + "metrics";
	}

	@Override
	public void analyze() {
		if (TaraMeasureAttribute.class.isInstance(measure.getParent())) {
			if (Metrics.getInstance().get(measure.getText()) == null) {
				Class<?> metricValue = loadCompiledMetricClass(getModule(), measure.getMeasureType().getText());
				if (metricValue != null) Metrics.getInstance().add(metricValue);
				else results.put(measure.getMeasureType(),
					new TaraAnnotator.AnnotateAndFix(WARNING, "Measure Not Found. Create or Compile it.",
						new CreateMeasureClassIntention(measure.getMeasureType().getText(), metricsPackage.toLowerCase())));
			}
		}
	}

	private Class<?> loadCompiledMetricClass(@NotNull Module module, String className) {
		VirtualFile compilerOutputPath = CompilerModuleExtension.getInstance(module).getCompilerOutputPath();
		return loadClass(compilerOutputPath.getPath(), metricsPackage + "." + className);
	}

	private Class<?> loadClass(String path, String className) {
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

	private Module getModule() {
		return ModuleProvider.getModuleOfFile(measure.getContainingFile());
	}

	public static class Metrics {

		private static Metrics instance;
		private static Map<String, Class<?>> metrics = new HashMap<>();

		public interface Converter {
			public double convert(double value);
		}

		private Metrics() {
		}

		public static Metrics getInstance() {
			if (instance == null) instance = new Metrics();
			return instance;
		}

		public Class<?> add(Class<?> metric) {
			return metrics.put(metric.getClass().getSimpleName(), metric);
		}

		public Class<?>[] getMetrics() {
			return metrics.values().toArray(new Class<?>[metrics.size()]);
		}

		public Class<?> get(String measure) {
			return metrics.get(measure);
		}
	}
}
