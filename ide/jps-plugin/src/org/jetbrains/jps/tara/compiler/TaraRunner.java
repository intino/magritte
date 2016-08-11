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
import org.jetbrains.jps.tara.model.JpsTaraFacet;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static tara.compiler.constants.TaraBuildConstants.*;

class TaraRunner {
	private static final char NL = '\n';
	private static final Logger LOG = Logger.getInstance(TaraRunner.class.getName());
	private static final String[] TARA_BUILDER = {"builder.jar", "grammar.jar", "bytecode.jar", "builder-constants.jar"};
	private static final String ANTLR = "antlr4-runtime-4.5.jar";
	private static final String GSON = "gson-2.4.jar";
	private static final String[] KRYO = {"asm-5.0.4.jar", "kryo-4.0.0.jar", "minlog-1.3.0.jar", "objenesis-2.2.jar", "reflectasm-1.11.3.jar"};
	private static final String ITRULES_VERSION = "1.6.0";
	private static final String[] ITRULES = {"itrules-" + ITRULES_VERSION + ".jar", "itrules-itr-reader-" + ITRULES_VERSION + ".jar"};
	private static final String[] CSV_READER = {"opencsv-3.7.jar"};
	private static final String GRAMMAR = "grammar.jar";
	private static final String LIB = "lib/";
	private static final int COMPILER_MEMORY = 600;
	private static File argsFile;

	TaraRunner(final String projectName, final String moduleName, JpsTaraFacet conf, String nativeLanguage, boolean isMake,
			   final Map<String, Boolean> sources,
			   final String encoding,
			   final boolean isTest,
			   List<String> paths) throws IOException {
		argsFile = FileUtil.createTempFile("ideaTaraToCompile", ".txt", false);
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(argsFile), Charset.forName(encoding)))) {
			writer.write(SRC_FILE + NL);
			for (Map.Entry<String, Boolean> file : sources.entrySet()) writer.write(file.getKey() + "#" + file.getValue() + NL);
			writer.write(NL);
			writer.write(PROJECT + NL + projectName + NL);
			writer.write(MODULE + NL + moduleName + NL);
			writePaths(paths, writer);
			if (conf == null) writer.write(MODEL_LEVEL + NL + "System" + NL);
			else fillConfiguration(conf, writer);
			writer.write(MAKE + NL + isMake + NL);
			writer.write(TEST + NL + isTest + NL);
			writer.write(ENCODING + NL + encoding + NL);
			writer.write(NATIVES_LANGUAGE + NL + nativeLanguage + NL);
			writer.write(CLASSPATH + NL);
			writer.write(join(generateClasspath()));
			writer.close();
		}
	}

	private void fillConfiguration(JpsTaraFacet conf, Writer writer) throws IOException {
		writer.write(MODEL_LEVEL + NL + conf.type() + NL);
		if (!conf.applicationDsl().isEmpty()) writer.write(APPLICATION_LANGUAGE + NL + conf.applicationDsl() + NL);
		if (!conf.systemDsl().isEmpty()) writer.write(SYSTEM_LANGUAGE + NL + conf.systemDsl() + NL);
		if (!conf.platformOutDsl().isEmpty()) writer.write(PLATFORM_OUT_DSL + NL + conf.platformOutDsl() + NL);
		if (!conf.applicationOutDsl().isEmpty()) writer.write(APPLICATION_OUT_DSL + NL + conf.applicationOutDsl() + NL);
		writer.write(PERSISTENT_MODEL + NL + conf.isPersistent() + NL);
		writer.write(PLATFORM_REFACTOR_ID + NL + conf.platformRefactorId() + NL);
		writer.write(APPLICATION_REFACTOR_ID + NL + conf.applicationRefactorId() + NL);
	}

	private void writePaths(List<String> paths, Writer writer) throws IOException {
		File semanticLib = getSemanticsLib().exists() ? getSemanticsLib() : getTaraJar(ClasspathBootstrap.getResourceFile(TaraBuilder.class));
		writer.write(SEMANTIC_LIB + NL + semanticLib.getAbsolutePath() + NL);
		writer.write(OUTPUTPATH + NL + paths.get(0) + NL);
		writer.write(FINAL_OUTPUTPATH + NL + paths.get(1) + NL);
		writer.write(MAGRITTE + NL + paths.get(2) + NL);
		if (paths.get(3) != null) writer.write(SRC_PATH + NL + paths.get(3) + NL);
		writer.write(RESOURCES + NL + paths.get(4) + NL);
		if (paths.get(5) != null) writer.write(TARA_PATH + NL + paths.get(5) + NL);
	}

	TaracOSProcessHandler runTaraCompiler(final CompileContext context) throws IOException {
		List<String> classpath = new ArrayList<>(generateRunnerClasspath());
		if (LOG.isDebugEnabled()) LOG.debug("Tarac classpath: " + classpath);
		List<String> programParams = ContainerUtilRt.newArrayList(argsFile.getPath());
		List<String> vmParams = ContainerUtilRt.newArrayList();
		vmParams.add("-Xmx" + COMPILER_MEMORY + "m");
		vmParams.add("-Dfile.encoding=" + System.getProperty("file.encoding"));
		final List<String> cmd = ExternalProcessUtil.buildJavaCommandLine(
			getJavaExecutable(), "tara.TaracRunner", Collections.emptyList(), classpath, vmParams, programParams);
		final Process process = Runtime.getRuntime().exec(ArrayUtil.toStringArray(cmd));
		final Consumer<String> updater = s -> context.processMessage(new ProgressMessage(s));
		final TaracOSProcessHandler handler = new TaracOSProcessHandler(process, updater) {
			@Override
			protected Future<?> executeOnPooledThread(@NotNull Runnable task) {
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
		classPath.add(getGsonLib().getPath());
		classPath.add(getSemanticsLib().getPath());
		classPath.addAll(getItRulesLibs().stream().map(File::getPath).collect(Collectors.toList()));
		classPath.addAll(getKryoLibs().stream().map(File::getPath).collect(Collectors.toList()));
		classPath.addAll(getCsvReaderLibs().stream().map(File::getPath).collect(Collectors.toList()));
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

	private File getGsonLib() {
		File root = ClasspathBootstrap.getResourceFile(TaraBuilder.class);
		root = new File(root.getParentFile(), GSON);
		return (root.exists()) ? new File(root.getParentFile(), GSON) :
			new File(root.getParentFile(), LIB + GSON);
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

	private List<File> getCsvReaderLibs() {
		File root = ClasspathBootstrap.getResourceFile(TaraBuilder.class);
		List<File> libs = new ArrayList<>();
		for (String lib : CSV_READER) addLib(root, lib, libs);
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
