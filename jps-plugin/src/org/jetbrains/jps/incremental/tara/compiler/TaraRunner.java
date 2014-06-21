package org.jetbrains.jps.incremental.tara.compiler;

import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.util.ArrayUtil;
import com.intellij.util.Consumer;
import com.intellij.util.SystemProperties;
import com.intellij.util.containers.ContainerUtilRt;
import org.jetbrains.annotations.Nullable;
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
	private static File argsFile;

	protected TaraRunner(final String projectName, final String moduleName, final String outputDir,
	                     final Collection<String> sources,
	                     String finalOutput, @Nullable final String encoding, String projectIcon, String[] iconPaths) throws IOException {
		argsFile = FileUtil.createTempFile("ideaTaraToCompile", ".txt", true);
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(argsFile)))) {
			writer.write(TaraRtConstants.SRC_FILE + "\n");
			for (String file : sources)
				writer.write(file + "\n");
			writer.write("\n");
			writer.write(TaraRtConstants.PROJECT + "\n" + projectName + "\n");
			writer.write(TaraRtConstants.MODULE + "\n" + moduleName + "\n");
			if (encoding != null) writer.write(TaraRtConstants.ENCODING + "\n" + encoding + "\n");
			writer.write(TaraRtConstants.IDEA_HOME + "\n");
			writer.write(PathManager.getHomePath() + File.separator + "lib" + File.separator + "\n");
			writer.write(TaraRtConstants.PLUGIN_HOME + "\n");
			writer.write(PathManager.getPluginsPath() + File.separator + "tara" + File.separator + "\n");
			for (String iconPath : iconPaths) {
				writer.write(TaraRtConstants.ICONS_PATH + "\n");
				writer.write(iconPath + "\n");
			}
			if (projectIcon != null) writer.write(TaraRtConstants.PROJECT_ICON + "\n" + projectIcon + "\n");
			writer.write(TaraRtConstants.OUTPUTPATH + "\n");
			writer.write(outputDir + "\n");
			writer.write(TaraRtConstants.FINAL_OUTPUTPATH + "\n");
			writer.write(finalOutput + "\n");
			writer.write(TaraRtConstants.CLASSPATH + "\n");
			writer.write(join(generateClasspath()));
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
		classPath.add(getGsonLib().getPath());
		classPath.add(getTemplationLib().getPath());
		return classPath;
	}

	private File getTaraRtRoot() {
		File root = ClasspathBootstrap.getResourceFile(TaraBuilder.class);
		if (root.isFile()) return new File(root.getParentFile(), "tara.jar");
		return root;
	}

	private File getAntlrLib() {
		File root = ClasspathBootstrap.getResourceFile(TaraBuilder.class);
		root = new File(root.getParentFile(), "antlr-4.2-complete.jar");
		return (root.exists()) ? new File(root.getParentFile(), "antlr-4.2-complete.jar") :
			new File(root.getParentFile(), "lib/antlr-4.2-complete.jar");
	}

	private File getGsonLib() {
		File root = ClasspathBootstrap.getResourceFile(TaraBuilder.class);
		root = new File(root.getParentFile(), "gson-2.2.4.jar");
		return (root.exists()) ? new File(root.getParentFile(), "gson-2.2.4.jar") :
			new File(root.getParentFile(), "lib/gson-2.2.4.jar");
	}

	private File getTemplationLib() {
		File root = ClasspathBootstrap.getResourceFile(TaraBuilder.class);
		root = new File(root.getParentFile(), "templation.jar");
		return (root.exists()) ? new File(root.getParentFile(), "templation.jar") :
			new File(root.getParentFile(), "lib/templation.jar");
	}

	private Collection<String> generateClasspath() {
		final Set<String> cp = new LinkedHashSet<>();
		cp.add(getTaraRtRoot().getPath());
		return cp;
	}

}
