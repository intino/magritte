package org.jetbrains.jps.tara.compiler;

import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.util.ArrayUtil;
import com.intellij.util.Consumer;
import com.intellij.util.SystemProperties;
import com.intellij.util.containers.ContainerUtilRt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.cmdline.ClasspathBootstrap;
import org.jetbrains.jps.incremental.CompileContext;
import org.jetbrains.jps.incremental.ExternalProcessUtil;
import org.jetbrains.jps.incremental.messages.ProgressMessage;
import org.jetbrains.jps.service.SharedThreadPool;
import tara.compiler.constants.TaraBuildConstants;

import java.io.*;
import java.util.*;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static java.io.File.separator;

public class TaraRunner {
	public static final char NL = '\n';
	private static final Logger LOG = Logger.getInstance(TaraRunner.class.getName());
	private static final String ANTLR = "antlr4-runtime-4.5.jar";
	private static final String[] KRYO = {"asm-4.2.jar", "kryo-3.0.0.jar", "minlog-1.3.0.jar", "objenesis-2.1.jar", "reflectasm-1.10.0.jar"};
	private static final String ITRULES_VERSION = "1.2.5";
	private static final String[] ITRULES = {"itrules-" + ITRULES_VERSION + ".jar", "itrules-itr-reader-" + ITRULES_VERSION + ".jar"};
	private static final String SEMANTIC_RULES = "tara.jar";
	private static final String LIB = "lib";
	private static File argsFile;

	protected TaraRunner(final String projectName, final String moduleName, final String language,
	                     final String generatedLangName, final int level, final String dictionary, boolean plateRequired,
	                     final Collection<String> sources,
	                     final String encoding,
	                     String[] iconPaths,
	                     List<String> paths) throws IOException {
		argsFile = FileUtil.createTempFile("ideaTaraToCompile", ".txt", true);
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(argsFile)))) {
			writer.write(TaraBuildConstants.SRC_FILE + NL);
			for (String file : sources) writer.write(file + NL);
			writer.write(NL);
			writer.write(TaraBuildConstants.PROJECT + NL + projectName + NL);
			writer.write(TaraBuildConstants.MODULE + NL + moduleName + NL);
			if (!language.isEmpty()) writer.write(TaraBuildConstants.LANGUAGE + NL + language + NL);
			writer.write(TaraBuildConstants.DICTIONARY + NL + dictionary + NL);
			writer.write(TaraBuildConstants.REQUIRED_PLATE + NL + plateRequired + NL);
			if (generatedLangName != null && !generatedLangName.isEmpty()) {
				writer.write(TaraBuildConstants.GENERATED_LANG_NAME + NL + generatedLangName + NL);
				writer.write(TaraBuildConstants.MODEL_LEVEL + NL + level + NL);
			}
			if (encoding != null) writer.write(TaraBuildConstants.ENCODING + NL + encoding + NL);
			for (String iconPath : iconPaths)
				writer.write(TaraBuildConstants.ICONS_PATH + NL + iconPath + NL);
			writePaths(paths, writer);
			writer.write(TaraBuildConstants.CLASSPATH + NL);
			writer.write(join(generateClasspath()));
			writer.close();
		}
	}

	private void writePaths(List<String> paths, Writer writer) throws IOException {
		String semanticLib = PathManager.getPluginsPath() + separator + "tara" + separator + LIB + separator + SEMANTIC_RULES;
		writer.write(TaraBuildConstants.SEMANTIC_LIB + NL + semanticLib + NL);
		writer.write(TaraBuildConstants.OUTPUTPATH + NL + paths.get(0) + NL);
		writer.write(TaraBuildConstants.FINAL_OUTPUTPATH + NL + paths.get(1) + NL);
		writer.write(TaraBuildConstants.MAGRITTE + NL + paths.get(2) + NL);
		if (paths.get(3) != null) writer.write(TaraBuildConstants.IT_RULES + NL + paths.get(3) + NL);
		writer.write(TaraBuildConstants.METRICS + NL + paths.get(4) + NL);
		writer.write(TaraBuildConstants.RESOURCES + NL + paths.get(5) + NL);
		if (paths.get(6) != null) writer.write(TaraBuildConstants.NATIVES_PATH + NL + paths.get(6) + NL);
		if (paths.get(7) != null) writer.write(TaraBuildConstants.LANGUAGES_PATH + NL + paths.get(7) + NL);
	}

	protected TaracOSProcessHandler runTaraCompiler(final CompileContext context,
	                                                final JpsTaraSettings settings) throws IOException {
		List<String> classpath = new ArrayList<>(generateRunnerClasspath());
		if (LOG.isDebugEnabled()) LOG.debug("Tarac classpath: " + classpath);
		List<String> programParams = ContainerUtilRt.newArrayList(argsFile.getPath());
		List<String> vmParams = ContainerUtilRt.newArrayList();
		vmParams.add("-Xmx" + settings.heapSize + "m");
		vmParams.add("-Dfile.encoding=" + System.getProperty("file.encoding"));
		final List<String> cmd = ExternalProcessUtil.buildJavaCommandLine(
			getJavaExecutable(), "tara.TaracRunner", Collections.emptyList(), classpath, vmParams, programParams);
		final Process process = Runtime.getRuntime().exec(ArrayUtil.toStringArray(cmd));
		final Consumer<String> updater = s -> context.processMessage(new ProgressMessage(s));
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
		for (String s : array) message += s + NL;
		return message;
	}

	private String getJavaExecutable() {
		return SystemProperties.getJavaHome() + "/bin/java";
	}

	private Collection<String> generateRunnerClasspath() {
		final Set<String> classPath = new LinkedHashSet<>();
		classPath.add(getTaraRtRoot().getPath());
		classPath.add(getAntlrLib().getPath());
		classPath.add(getSemanticsLib().getPath());
		classPath.addAll(getItRulesLibs().stream().map(File::getPath).collect(Collectors.toList()));
		classPath.addAll(getKryoLibs().stream().map(File::getPath).collect(Collectors.toList()));
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

	private File getSemanticsLib() {
		File root = ClasspathBootstrap.getResourceFile(TaraBuilder.class);
		root = new File(root.getParentFile(), SEMANTIC_RULES);
		return (root.exists()) ? new File(root.getParentFile(), SEMANTIC_RULES) :
			new File(root.getParentFile(), "lib/" + SEMANTIC_RULES);
	}

	private Collection<File> getItRulesLibs() {
		File root = ClasspathBootstrap.getResourceFile(TaraBuilder.class);
		List<File> libs = new ArrayList<>();
		for (String lib : ITRULES) root = createLib(root, libs, lib);
		return libs;
	}

	private Collection<File> getKryoLibs() {
		File root = ClasspathBootstrap.getResourceFile(TaraBuilder.class);
		List<File> libs = new ArrayList<>();
		for (String lib : KRYO) root = createLib(root, libs, lib);
		return libs;
	}

	@NotNull
	private File createLib(File root, List<File> libs, String lib) {
		root = new File(root.getParentFile(), lib);
		libs.add((root.exists()) ? new File(root.getParentFile(), lib) :
			new File(root.getParentFile(), "lib/" + lib));
		return root;
	}

	private File getTaraRtRoot() {
		File root = ClasspathBootstrap.getResourceFile(TaraBuilder.class);
		if (root.isFile()) return new File(root.getParentFile(), "tara.jar");
		return root;
	}

}
