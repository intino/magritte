package monet.tara.compiler.code_generation;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class JavaCHelper {

	public static ArrayList<String> buildJavaCompileCommandLine(File[] sources,
	                                                            String[] classpath,
	                                                            String[] vmParams,
	                                                            String tempDirectory) {
		final ArrayList<String> cmdLine = new ArrayList<>();
		cmdLine.add(getJavacExecutable());
		if (vmParams != null)
			Collections.addAll(cmdLine, vmParams);
		if (sources.length != 0) {
			ArrayList<String> sourceParam = new ArrayList<>();
			File buildPath = new File(tempDirectory + File.separator + "build" + File.separator);
			buildPath.mkdirs();
			cmdLine.add("-d " + buildPath.getAbsolutePath());
			cmdLine.add("-classpath");
			cmdLine.add(join(classpath, File.pathSeparator));
			for (File source : sources)
				sourceParam.add(source.getAbsolutePath());
			cmdLine.add(join(sourceParam.toArray(new String[sourceParam.size()]), " "));
		}
		return cmdLine;
	}

	public static ArrayList<String> buildJarCommandLine(String tempPath, String name, String[] libraries) {
		final ArrayList<String> cmdLine = new ArrayList<>();
		cmdLine.add(getJarExecutable());
		cmdLine.add("cf");
		cmdLine.add(name + ".jar");
		cmdLine.add(join(libraries, " "));
		cmdLine.add("-C");
		cmdLine.add(tempPath + File.separator + "build" + File.separator);
		cmdLine.add(".");
		return cmdLine;
	}

	public static String getJavacExecutable() {
		String javaPath = System.getProperty("java.home");
		return javaPath.substring(0, javaPath.lastIndexOf(File.separator)) + File.separator +"bin"+File.separator+"javac";
	}

	public static String getJarExecutable() {
		String javaPath = System.getProperty("java.home");
		return javaPath.substring(0, javaPath.lastIndexOf(File.separator)) + File.separator + "bin" + File.separator +"jar";
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
