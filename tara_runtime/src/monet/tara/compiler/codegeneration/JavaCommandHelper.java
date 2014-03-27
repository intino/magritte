package monet.tara.compiler.codegeneration;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JavaCommandHelper {

	public static final String JAVA_HOME = System.getProperty("java.home");

	private JavaCommandHelper() {
	}

	public static List<String> buildJavaCompileCommandLine(File[] sources,
	                                                       String[] classpath,
	                                                       String[] vmParams,
	                                                       String buildDirectory) throws IOException {
		final List<String> cmdLine = new ArrayList<>();
		cmdLine.add(getJavacExecutable());
		if (vmParams != null)
			Collections.addAll(cmdLine, vmParams);
		if (sources.length != 0) {
			File buildPath = new File(buildDirectory);
			buildPath.mkdirs();
			cmdLine.add("-nowarn");
			cmdLine.add("-d");
			cmdLine.add(buildPath.getAbsolutePath());
			if (classpath != null && classpath.length > 0) {
				cmdLine.add("-classpath");
				cmdLine.add(join(classpath, File.pathSeparator));
			}
			File temp = File.createTempFile("__srcToCompile", null);
			temp.deleteOnExit();
			BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
			for (File source : sources)
				writer.append(source.getAbsolutePath()).append("\n");
			writer.close();
			cmdLine.add("@" + temp);
		}
		return cmdLine;
	}

	public static List<String> buildJavaJarExecuteCommandLine(String jar,
	                                                          String[] vmParams,
	                                                          String[] args) {
		final List<String> cmdLine = new ArrayList<>();
		cmdLine.add(getJavaExecutable());
		if (vmParams != null) Collections.addAll(cmdLine, vmParams);
		cmdLine.add("-jar " + jar + " ");
		cmdLine.add(join(args, " "));
		return cmdLine;
	}


	public static List<String> buildJarCommandLine(String tempPath, String destinyName, String[] libraries) {
		final List<String> cmdLine = new ArrayList<>();
		cmdLine.add(getJarExecutable());
		cmdLine.add("cf");
		cmdLine.add(destinyName + ".jar");
		cmdLine.add(join(libraries, " "));
		cmdLine.add("-C");
		cmdLine.add(tempPath + File.separator + "build" + File.separator);
		cmdLine.add(".");
		return cmdLine;
	}

	public static String getJavacExecutable() {
		return JAVA_HOME.substring(0, JAVA_HOME.lastIndexOf(File.separator)) + File.separator + "bin" + File.separator + "javac";
	}

	public static String getJarExecutable() {
		return JAVA_HOME.substring(0, JAVA_HOME.lastIndexOf(File.separator)) + File.separator + "bin" + File.separator + "jar";
	}

	public static String getJavaExecutable() {
		return JAVA_HOME.substring(0, JAVA_HOME.lastIndexOf(File.separator)) + File.separator + "bin" + File.separator + "java";
	}

	@NotNull
	public static String join(@NotNull final String[] strings, @NotNull final String separator) {
		return join(strings, 0, strings.length, separator);
	}

	public static String join(@NotNull final String[] strings, int startIndex, int endIndex, @NotNull final String separator) {
		final StringBuilder result = new StringBuilder();
		for (int i = startIndex; i < endIndex; i++) {
			if (i > startIndex) result.append(separator);
			result.append(strings[i]);
		}
		return result.toString();
	}
}
