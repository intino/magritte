package org.jetbrains.jps.tara.compiler;

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
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class TaraRunner {
	public static final char NL = '\n';
	private static final Logger LOG = Logger.getInstance(TaraRunner.class.getName());
	private static final String[] TARA_BUILDER = {"builder.jar", "grammar.jar", "bytecode.jar", "builder-constants.jar"};
	private static final String ANTLR = "antlr4-runtime-4.5.jar";
	private static final String[] KRYO = {"asm-4.2.jar", "kryo-3.0.0.jar", "minlog-1.3.0.jar", "objenesis-2.1.jar", "reflectasm-1.10.0.jar"};
	private static final String ITRULES_VERSION = "1.4.0";
	private static final String[] ITRULES = {"itrules-" + ITRULES_VERSION + ".jar", "itrules-itr-reader-" + ITRULES_VERSION + ".jar"};
	private static final String GRAMMAR = "grammar.jar";
	private static final String LIB = "lib/";
	private static File argsFile;

	protected TaraRunner(final String projectName, final String moduleName, final String language,
	                     final String generatedLangName, final int level, final boolean customLayers,
	                     boolean dynamicLoad, final Map<String, Boolean> sources,
	                     final String encoding,
	                     String[] iconPaths,
	                     List<String> paths) throws IOException {
		argsFile = FileUtil.createTempFile("ideaTaraToCompile", ".txt", true);
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(argsFile), Charset.forName(encoding)))) {
			writer.write(TaraBuildConstants.SRC_FILE + NL);
			for (Map.Entry<String, Boolean> file : sources.entrySet())
				writer.write(file.getKey() + "#" + file.getValue() + NL);
			writer.write(NL);
			writer.write(TaraBuildConstants.PROJECT + NL + projectName + NL);
			writer.write(TaraBuildConstants.MODULE + NL + moduleName + NL);
			if (!language.isEmpty()) writer.write(TaraBuildConstants.LANGUAGE + NL + language + NL);
			writer.write(TaraBuildConstants.CUSTOM_MORPHS + NL + customLayers + NL);
			writer.write(TaraBuildConstants.DYNAMIC_LOAD + NL + dynamicLoad + NL);
			if (generatedLangName != null && !generatedLangName.isEmpty()) {
				writer.write(TaraBuildConstants.GENERATED_LANG_NAME + NL + generatedLangName + NL);
				writer.write(TaraBuildConstants.MODEL_LEVEL + NL + level + NL);
			}
			writer.write(TaraBuildConstants.ENCODING + NL + encoding + NL);
			for (String iconPath : iconPaths)
				writer.write(TaraBuildConstants.ICONS_PATH + NL + iconPath + NL);
			writePaths(paths, writer);
			writer.write(TaraBuildConstants.CLASSPATH + NL);
			writer.write(join(generateClasspath()));
			writer.close();
		}
	}

	private void writePaths(List<String> paths, Writer writer) throws IOException {
		File semanticLib = getSemanticsLib().exists() ? getSemanticsLib() : getTaraJar(ClasspathBootstrap.getResourceFile(TaraBuilder.class));
		writer.write(TaraBuildConstants.SEMANTIC_LIB + NL + semanticLib.getAbsolutePath() + NL);
		writer.write(TaraBuildConstants.OUTPUTPATH + NL + paths.get(0) + NL);
		writer.write(TaraBuildConstants.FINAL_OUTPUTPATH + NL + paths.get(1) + NL);
		writer.write(TaraBuildConstants.MAGRITTE + NL + paths.get(2) + NL);
		if (paths.get(3) != null) writer.write(TaraBuildConstants.RULES + NL + paths.get(3) + NL);
		writer.write(TaraBuildConstants.RULES + NL + paths.get(4) + NL);
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
		StringBuilder message = new StringBuilder();
		for (String s : array) message.append(s).append(NL);
		return message.toString();
	}

	private String getJavaExecutable() {
		return SystemProperties.getJavaHome() + "/bin/java";
	}

	private Collection<String> generateRunnerClasspath() {
		final Set<String> classPath = new LinkedHashSet<>();
		classPath.addAll(getTaraBuilderRoot().stream().map(File::getPath).collect(Collectors.toList()));
		classPath.add(getAntlrLib().getPath());
		classPath.add(getSemanticsLib().getPath());
		classPath.addAll(getItRulesLibs().stream().map(File::getPath).collect(Collectors.toList()));
		classPath.addAll(getKryoLibs().stream().map(File::getPath).collect(Collectors.toList()));
		return classPath;
	}

	private Collection<String> generateClasspath() {
		final Set<String> cp = new LinkedHashSet<>();
		cp.addAll(getTaraBuilderRoot().stream().map(File::getPath).collect(Collectors.toList()));
		return cp;
	}

	private File getAntlrLib() {
		File root = ClasspathBootstrap.getResourceFile(TaraBuilder.class);
		root = new File(root.getParentFile(), ANTLR);
		return (root.exists()) ? new File(root.getParentFile(), ANTLR) :
			new File(root.getParentFile(), LIB + ANTLR);
	}

	private File getSemanticsLib() {
		File root = ClasspathBootstrap.getResourceFile(TaraBuilder.class);
		root = new File(root.getParentFile(), GRAMMAR);
		return (root.exists()) ? new File(root.getParentFile(), GRAMMAR) :
			new File(root.getParentFile(), LIB + GRAMMAR);
	}

	private List<File> getItRulesLibs() {
		File root = ClasspathBootstrap.getResourceFile(TaraBuilder.class);
		List<File> libs = new ArrayList<>();
		for (String lib : ITRULES) addLib(root, lib, libs);
		return libs;
	}

	private List<File> getKryoLibs() {
		File root = ClasspathBootstrap.getResourceFile(TaraBuilder.class);
		List<File> libs = new ArrayList<>();
		for (String lib : KRYO) addLib(root, lib, libs);
		return libs;
	}

	private List<File> getTaraBuilderRoot() {
		File root = ClasspathBootstrap.getResourceFile(TaraBuilder.class);
		List<File> libs = new ArrayList<>();
		for (String lib : TARA_BUILDER) addLib(root, lib, libs);
		if (!libs.get(0).exists()) return Collections.singletonList(getTaraJar(root));
		return libs;
	}

	@NotNull
	private File getTaraJar(File root) {
		return new File(root.getParentFile(), "tara.jar");
	}

	private void addLib(File root, String lib, List<File> libs) {
		root = new File(root.getParentFile(), lib);
		libs.add((root.exists()) ?
			new File(root.getParentFile(), lib) :
			new File(root.getParentFile(), LIB + lib));
	}

}
