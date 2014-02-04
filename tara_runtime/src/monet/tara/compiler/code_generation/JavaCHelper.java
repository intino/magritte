package monet.tara.compiler.code_generation;

import com.intellij.openapi.util.text.StringUtil;

import java.io.*;
import java.util.ArrayList;

public class JavaCHelper {

	public static ArrayList<String> buildJavaCommandLine(File[] sources,
	                                                String[] classpath,
	                                                String[] vmParams,
	                                                String tempDirectory) {
		final ArrayList<String> cmdLine = new ArrayList<>();
		cmdLine.add(getJavacExecutable());
		if (vmParams != null)
			for (String param : vmParams)
				cmdLine.add(param);
		if (!(sources.length == 0)) {
			try {
				File sourcesFile = new File(tempDirectory, "argsFile");
				sourcesFile.createNewFile();
				PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(sourcesFile)));
				for (File source : sources)
					writer.println(source.getName());
			} catch (IOException ignored) {
			}
		}
		cmdLine.add("-classpath");
		cmdLine.add(StringUtil.join(classpath, File.pathSeparator));
		cmdLine.add("@argsFile");
		return cmdLine;
	}

	private static String getJavacExecutable() {
		return System.getProperty("java.home") + "/bin/javac";
	}
}
