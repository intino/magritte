package siani.tara.intellij.annotator.semanticAnalizers;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.CompilerModuleExtension;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.annotator.TaraAnnotator;
import siani.tara.intellij.annotator.fix.CreateMeasureClassIntention;
import siani.tara.intellij.annotator.semanticAnalizers.metrics.Metrics;
import siani.tara.intellij.lang.psi.TaraAttributeType;
import siani.tara.intellij.lang.psi.TaraMeasureAttribute;
import siani.tara.intellij.project.module.ModuleProvider;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.WARNING;

public class MeasureAnalyzer extends TaraAnalyzer {
	private final String MetricsPackage;
	private final TaraAttributeType measure;

	public MeasureAnalyzer(TaraAttributeType measure) {
		this.measure = measure;
		MetricsPackage = measure.getProject().getName() + "." + "metrics";
	}

	@Override
	public void analyze() {
		if (TaraMeasureAttribute.class.isInstance(measure.getParent())) {
			if (Metrics.getInstance().get(measure.getText()) == null) {
				Class<?> metricValue = loadCompiledMetricClass(getModule(), measure.getMeasureType().getText());
				if (metricValue != null) Metrics.getInstance().add(metricValue);
				else results.put(measure.getMeasureType(),
					new TaraAnnotator.AnnotateAndFix(WARNING, "Measure Not Found. Create or Compile it.",
						new CreateMeasureClassIntention(measure.getMeasureType().getText(), MetricsPackage)));
			}
		}
	}

	private Class<?> loadCompiledMetricClass(@NotNull Module module, String className) {
		VirtualFile compilerOutputPath = CompilerModuleExtension.getInstance(module).getCompilerOutputPath();
		return loadClass(compilerOutputPath.getPath(), MetricsPackage + "." + className);
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

}
