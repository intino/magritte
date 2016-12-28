package org.jetbrains.jps.tara.compiler;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.util.ArrayUtil;
import com.intellij.util.SystemProperties;
import com.intellij.util.containers.ContainerUtilRt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.cmdline.ClasspathBootstrap;
import org.jetbrains.jps.incremental.CompileContext;
import org.jetbrains.jps.incremental.ExternalProcessUtil;
import org.jetbrains.jps.incremental.messages.ProgressMessage;
import org.jetbrains.jps.service.SharedThreadPool;
import org.jetbrains.jps.tara.model.impl.JpsModuleConfiguration;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static io.intino.tara.compiler.shared.TaraBuildConstants.*;

class TaraRunner {
	private static final char NL = '\n';
	private static final Logger LOG = Logger.getInstance(TaraRunner.class.getName());
	private static final String[] TARA_BUILDER = {"builder.jar", "builder-constants.jar"};
	private static final String INTINO_PATH = "intino-plugin";
	private static final String[] INTINO = {"intino-plugin.jar", "proteo-alone-1.0.0.jar"};
	private static final String TARA_CORE_JAR = "tara-plugin.jar";
	private static final String ANTLR = "antlr4-runtime-4.6.jar";
	private static final String GSON = "gson-2.4.jar";
	private static final String[] KRYO = {"asm-5.0.4.jar", "kryo-4.0.0.jar", "minlog-1.3.0.jar", "objenesis-2.2.jar", "reflectasm-1.11.3.jar"};
	private static final String ITRULES_VERSION = "1.6.0";
	private static final String[] ITRULES = {"itrules-" + ITRULES_VERSION + ".jar", "itrules-itr-reader-" + ITRULES_VERSION + ".jar"};
	private static final String[] CSV_READER = {"opencsv-3.7.jar"};
	private static final String LIB = "lib/";
	private static final int COMPILER_MEMORY = 600;
	private static File argsFile;

	TaraRunner(final String projectName, final String moduleName, JpsModuleConfiguration conf, String nativeLanguage, boolean isMake,
			   final Map<String, Boolean> sources,
			   final String encoding,
			   final boolean isTest,
			   List<String> paths) throws IOException {
		argsFile = FileUtil.createTempFile("ideaTaraToCompile", ".txt", false);
		LOG.info("args file: " + argsFile.getAbsolutePath());
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(argsFile), Charset.forName(encoding)))) {
			writer.write(SRC_FILE + NL);
			for (Map.Entry<String, Boolean> file : sources.entrySet()) writer.write(file.getKey() + "#" + file.getValue() + NL);
			writer.write(NL);
			writer.write(PROJECT + NL + projectName + NL);
			writer.write(MODULE + NL + moduleName + NL);
			writePaths(paths, writer);

			if (conf != null) fillConfiguration(conf, writer);

			writer.write(MAKE + NL + isMake + NL);
			writer.write(TEST + NL + isTest + NL);
			writer.write(ENCODING + NL + encoding + NL);
			writer.write(NATIVES_LANGUAGE + NL + nativeLanguage + NL);
			writer.write(CLASSPATH + NL);
			writer.write(join(generateClasspath()));
			writer.close();
		}
	}

	private void fillConfiguration(JpsModuleConfiguration conf, Writer writer) throws IOException {
		if (!conf.level.isEmpty()) writer.write(LEVEL + NL + conf.level + NL);
		if (!conf.dsl.isEmpty()) writer.write(DSL + NL + conf.dsl + NL);
		if (!conf.dslVersion.isEmpty()) writer.write(DSL_VERSION + NL + conf.dslVersion + NL);
		if (!conf.outDSL.isEmpty()) writer.write(OUT_DSL + NL + conf.outDSL + NL);
		if (!conf.groupID.isEmpty()) writer.write(GROUP_ID + NL + conf.groupID + NL);
		if (!conf.artifactID.isEmpty()) writer.write(ARTIFACT_ID + NL + conf.artifactID + NL);
		if (!conf.version.isEmpty()) writer.write(VERSION + NL + conf.version + NL);
		if (!conf.workingPackage.isEmpty()) writer.write(WORKING_PACKAGE + NL + conf.workingPackage + NL);
	}

	private void writePaths(List<String> paths, Writer writer) throws IOException {
		writer.write(SEMANTIC_LIB + NL + getTaraJar(ClasspathBootstrap.getResourceFile(TaraBuilder.class)).getAbsolutePath() + NL);
		writer.write(OUTPUTPATH + NL + paths.get(0) + NL);
		writer.write(FINAL_OUTPUTPATH + NL + paths.get(1) + NL);
		writer.write(RESOURCES + NL + paths.get(2) + NL);
		if (paths.get(3) != null) writer.write(TARA_PATH + NL + paths.get(3) + NL);
		if (paths.get(4) != null) writer.write(TARA_PROJECT_PATH + NL + paths.get(4) + NL);
		writer.write(SRC_PATH + NL);
		for (int i = 5; i < paths.size(); i++)
			writer.write(paths.get(i) + NL);
		writer.write(NL);
	}

	TaracOSProcessHandler runTaraCompiler(final CompileContext context) throws IOException {
		List<String> classpath = new ArrayList<>(generateRunnerClasspath());
		LOG.info("Tarac classpath: " + String.join("\n", classpath));
		List<String> programParams = ContainerUtilRt.newArrayList(argsFile.getPath());
		List<String> vmParams = ContainerUtilRt.newArrayList();
		vmParams.add("-Xmx" + COMPILER_MEMORY + "m");
		vmParams.add("-Dfile.encoding=" + System.getProperty("file.encoding"));
		final List<String> cmd = ExternalProcessUtil.buildJavaCommandLine(
			getJavaExecutable(), "io.intino.tara.TaracRunner", Collections.emptyList(), classpath, vmParams, programParams);
		final Process process = Runtime.getRuntime().exec(ArrayUtil.toStringArray(cmd));
		final TaracOSProcessHandler handler = new TaracOSProcessHandler(process, statusUpdater -> context.processMessage(new ProgressMessage(statusUpdater))) {
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
		classPath.addAll(getIntinoLib().stream().map(File::getPath).collect(Collectors.toList()));
		classPath.add(getAntlrLib().getPath());
		classPath.add(getGsonLib().getPath());
		classPath.addAll(getItRulesLibs().stream().map(File::getPath).collect(Collectors.toList()));
		classPath.addAll(getKryoLibs().stream().map(File::getPath).collect(Collectors.toList()));
		classPath.addAll(getCsvReaderLibs().stream().map(File::getPath).collect(Collectors.toList()));
		return classPath;
	}

	private Collection<String> generateClasspath() {
		final Set<String> cp = new LinkedHashSet<>();
		cp.addAll(getTaraBuilderRoot().stream().map(File::getPath).collect(Collectors.toList()));
		cp.addAll(getIntinoLib().stream().map(File::getPath).collect(Collectors.toList()));
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

	private List<File> getIntinoLib() {
		File root = new File(pluginsDirectory(), INTINO_PATH + File.separator + LIB);
		List<File> libs = new ArrayList<>();
		for (String lib : INTINO) addLib(root, lib, libs);
		if (!libs.get(0).exists()) return Collections.singletonList(getTaraJar(root));
		return libs;
	}

	private File pluginsDirectory() {
		return ClasspathBootstrap.getResourceFile(TaraBuilder.class).getParentFile().getParentFile().getParentFile();
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
		return new File(root.getParentFile(), TARA_CORE_JAR);
	}

	private void addLib(File root, String lib, List<File> libs) {
		root = new File(root.getParentFile(), lib);
		libs.add((root.exists()) ?
			new File(root.getParentFile(), lib) :
			new File(root.getParentFile(), LIB + lib));
	}
}
