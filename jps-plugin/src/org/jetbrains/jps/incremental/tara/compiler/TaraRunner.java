package org.jetbrains.jps.incremental.tara.compiler;

import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.util.ArrayUtil;
import com.intellij.util.Consumer;
import com.intellij.util.SystemProperties;
import com.intellij.util.containers.ContainerUtilRt;
import org.jetbrains.jps.cmdline.ClasspathBootstrap;
import org.jetbrains.jps.incremental.CompileContext;
import org.jetbrains.jps.incremental.ExternalProcessUtil;
import org.jetbrains.jps.incremental.messages.ProgressMessage;
import org.jetbrains.jps.service.SharedThreadPool;
import siani.tara.compiler.rt.TaraRtConstants;

import java.io.*;
import java.util.*;
import java.util.concurrent.Future;

public class TaraRunner {
	private static final Logger LOG = Logger.getInstance(TaraRunner.class.getName());

	private static final String ANTLR = "antlr-4.4-complete.jar";
	private static final String ITRULES = "itrules.jar";
	private static final String GSON = "gson-2.2.4.jar";
	private static File argsFile;

	protected TaraRunner(final String projectName, final String moduleName, final String generatedLangName, final String language,
	                     final Collection<String> sources,
	                     final String encoding,
	                     String[] iconPaths,
	                     List<String> paths) throws IOException {
		argsFile = FileUtil.createTempFile("ideaTaraToCompile", ".txt", true);
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(argsFile)))) {
			writer.write(TaraRtConstants.SRC_FILE + "\n");
			for (String file : sources)
				writer.write(file + "\n");
			writer.write("\n");
			writer.write(TaraRtConstants.PROJECT + "\n" + projectName + "\n");
			writer.write(TaraRtConstants.MODULE + "\n" + moduleName + "\n");
			writer.write(TaraRtConstants.TERMINAL + "\n" + (generatedLangName == null ? "true" : "false") + "\n");
			writer.write(TaraRtConstants.LANGUAGE + "\n" + language + "\n");
			if (generatedLangName != null)
				writer.write(TaraRtConstants.GENERATED_LANG_NAME + "\n" + generatedLangName + "\n");
			if (encoding != null) writer.write(TaraRtConstants.ENCODING + "\n" + encoding + "\n");
			String taraModels = PathManager.getPluginsPath() + File.separator + "tara_models" + File.separator;
			writer.write(TaraRtConstants.MODELS_PATH + "\n" + taraModels + "\n");
			for (String iconPath : iconPaths)
				writer.write(TaraRtConstants.ICONS_PATH + "\n" + iconPath + "\n");
			writer.write(TaraRtConstants.OUTPUTPATH + "\n" + paths.get(0) + "\n");
			writer.write(TaraRtConstants.FINAL_OUTPUTPATH + "\n" + paths.get(1) + "\n");
			writer.write(TaraRtConstants.TDK_HOME + "\n" + paths.get(2) + File.separator + "lib" + File.separator + "\n");
			if (paths.get(3) != null) writer.write(TaraRtConstants.IT_RULES + "\n" + paths.get(3) + "\n");
			writer.write(TaraRtConstants.METRICS + "\n" + paths.get(4) + "\n");
			writer.write(TaraRtConstants.RESOURCES + "\n" + paths.get(5) + "\n");
			if (!paths.get(6).isEmpty())
				writer.write(TaraRtConstants.METAMODEL_FILE + "\n" + paths.get(6) + "\n");
			writer.write(TaraRtConstants.CLASSPATH + "\n");
			writer.write(join(generateClasspath()));
			writer.close();
		}
	}

	protected TaracOSProcessHandler runTaraCompiler(final CompileContext context,
	                                                final JpsTaraSettings settings, boolean pluginGeneration) throws IOException {
		List<String> classpath = new ArrayList<>(generateRunnerClasspath());
		if (LOG.isDebugEnabled()) LOG.debug("Tarac classpath: " + classpath);
		List<String> programParams = ContainerUtilRt.newArrayList(pluginGeneration ? "--gen-plugin" : "tarac", argsFile.getPath());
		List<String> vmParams = ContainerUtilRt.newArrayList();
		vmParams.add("-Xmx" + settings.heapSize + "m");
		vmParams.add("-Dfile.encoding=" + System.getProperty("file.encoding"));
		final List<String> cmd = ExternalProcessUtil.buildJavaCommandLine(
			getJavaExecutable(), "siani.tara.TaracRunner", Collections.<String>emptyList(), classpath, vmParams, programParams);
		final Process process = Runtime.getRuntime().exec(ArrayUtil.toStringArray(cmd));
		final Consumer<String> updater = new Consumer<String>() {
			public void consume(String s) {
				context.processMessage(new ProgressMessage(s));
			}
		};
		final TaracOSProcessHandler handler = new TaracOSProcessHandler(process, updater) {
			@Override
			protected Future<?> executeOnPooledThread(Runnable task) {
				return SharedThreadPool.getInstance().executeOnPooledThread(task);
			}
		};
		handler.startNotify();
		handler.waitFor();
		return handler;
	}

	private String join(Collection<String> array) {
		String message = "";
		for (String s : array) {
			message += s + "\n";
		}
		return message;
	}

	private String getJavaExecutable() {
		return SystemProperties.getJavaHome() + "/bin/java";
	}

	private Collection<String> generateRunnerClasspath() {
		final Set<String> classPath = new LinkedHashSet<>();
		classPath.add(getTaraRtRoot().getPath());
		classPath.add(getAntlrLib().getPath());
		classPath.add(getItRulesLib().getPath());
		classPath.add(getGsonLib().getPath());
		return classPath;
	}

	private Collection<String> generateClasspath() {
		final Set<String> cp = new LinkedHashSet<>();
		cp.add(getTaraRtRoot().getPath());
		return cp;
	}

	private File getAntlrLib() {
		File root = ClasspathBootstrap.getResourceFile(TaraBuilder.class);
		root = new File(root.getParentFile(), ANTLR);
		return (root.exists()) ? new File(root.getParentFile(), ANTLR) :
			new File(root.getParentFile(), "lib/" + ANTLR);
	}

	private File getItRulesLib() {
		File root = ClasspathBootstrap.getResourceFile(TaraBuilder.class);
		root = new File(root.getParentFile(), ITRULES);
		return (root.exists()) ? new File(root.getParentFile(), ITRULES) :
			new File(root.getParentFile(), "lib/" + ITRULES);
	}

	private File getGsonLib() {
		File root = ClasspathBootstrap.getResourceFile(TaraBuilder.class);
		root = new File(root.getParentFile(), GSON);
		return (root.exists()) ? new File(root.getParentFile(), GSON) :
			new File(root.getParentFile(), "lib/" + GSON);
	}


	private File getTaraRtRoot() {
		File root = ClasspathBootstrap.getResourceFile(TaraBuilder.class);
		if (root.isFile()) return new File(root.getParentFile(), "tara.jar");
		return root;
	}

}
