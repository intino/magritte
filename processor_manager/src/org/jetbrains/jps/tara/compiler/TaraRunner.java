package org.jetbrains.jps.tara.compiler;

import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.util.ArrayUtil;
import com.intellij.util.Consumer;
import com.intellij.util.SystemProperties;
import com.intellij.util.containers.ContainerUtilRt;
import monet.tara.compiler.rt.TaraRtConstants;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.cmdline.ClasspathBootstrap;
import org.jetbrains.jps.incremental.CompileContext;
import org.jetbrains.jps.incremental.ExternalProcessUtil;
import org.jetbrains.jps.incremental.messages.ProgressMessage;
import org.jetbrains.jps.service.SharedThreadPool;

import java.io.*;
import java.util.*;
import java.util.concurrent.Future;

public class TaraRunner {
	private static final Logger LOG = Logger.getInstance(TaraRunner.class.getName());

	protected static TaracOSProcessHandler runTaraCompiler(final CompileContext context,
	                                                       File tempFile,
	                                                       final JpsTaraSettings settings, boolean pluginGeneration) throws IOException {
		List<String> classpath = new ArrayList<>(generateClasspath());
		if (LOG.isDebugEnabled()) LOG.debug("Tarac classpath: " + classpath);
		List<String> programParams = ContainerUtilRt.newArrayList(pluginGeneration ? "--gen-plugin" : "tarac", tempFile.getPath());
		List<String> vmParams = ContainerUtilRt.newArrayList();
		vmParams.add("-Xmx" + settings.getHeapSize() + "m");
		vmParams.add("-Dfile.encoding=" + System.getProperty("file.encoding"));
		final List<String> cmd = ExternalProcessUtil.buildJavaCommandLine(
			getJavaExecutable(), "monet.tara.TaracRunner", Collections.<String>emptyList(), classpath, vmParams, programParams);
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

	protected static File fillFileWithTaracParameters(final String projectName, final String outputDir,
	                                               final Collection<String> changedSources,
	                                               String finalOutput, @Nullable final String encoding, String iconPath) throws IOException {
		File tempFile = FileUtil.createTempFile("ideaTaraToCompile", ".txt", true);
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempFile)))) {
			writer.write(TaraRtConstants.SRC_FILE + "\n");
			for (String file : changedSources)
				writer.write(file + "\n");
			writer.write(TaraRtConstants.PROJECT + "\n" + projectName + "\n");
			if (encoding != null) writer.write(TaraRtConstants.ENCODING + "\n" + encoding + "\n");
			writer.write(TaraRtConstants.IDEA_HOME + "\n");
			writer.write(PathManager.getHomePath() + File.separator + "lib" + File.separator + "\n");
			if (iconPath != null) writer.write(TaraRtConstants.PROJECT_ICON + "\n" + iconPath + "\n");
			writer.write(TaraRtConstants.OUTPUTPATH + "\n");
			writer.write(outputDir + "\n");
			writer.write(TaraRtConstants.FINAL_OUTPUTPATH + "\n");
			writer.write(finalOutput + "\n");
		}
		return tempFile;
	}

	private static String getJavaExecutable() {
		return SystemProperties.getJavaHome() + "/bin/java";
	}

	private static Collection<String> generateClasspath() {
		final Set<String> clashPath = new LinkedHashSet<>();
		clashPath.add(getTaraRtRoot().getPath());
		clashPath.add(getAntlrLib().getPath());
		clashPath.add(getTemplationLib().getPath());
		return clashPath;
	}

	private static File getTaraRtRoot() {
		File root = ClasspathBootstrap.getResourceFile(TaraBuilder.class);
		if (root.isFile()) return new File(root.getParentFile(), "tara_rt.jar");
		return root;
	}

	private static File getAntlrLib() {
		File root = ClasspathBootstrap.getResourceFile(TaraBuilder.class);
		return new File(root.getParentFile().getAbsolutePath(), File.separator + "lib" + File.separator + "antlr-4.2-complete.jar");
	}

	private static File getTemplationLib() {
		File root = ClasspathBootstrap.getResourceFile(TaraBuilder.class);
		return new File(root.getParentFile().getAbsolutePath(), File.separator + "lib" + File.separator + "templation.jar");
	}

}
