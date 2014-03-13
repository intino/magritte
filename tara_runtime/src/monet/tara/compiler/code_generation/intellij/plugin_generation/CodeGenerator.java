package monet.tara.compiler.code_generation.intellij.plugin_generation;

import monet.tara.compiler.core.error_collection.StreamWrapper;
import monet.tara.compiler.core.error_collection.TaraException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class CodeGenerator {

	public static final String IDE = "intellij/";
	public static final String SRC = "src";
	public static final String BUILD = "build";
	public static final String PROJECT_PATH = "monet/tara/" + IDE;
	public static final String SEP = File.separator;

	protected static PrintWriter getOutWriter(File file) throws TaraException {
		try {
			file.getParentFile().mkdirs();
			file.createNewFile();
			out("// ---- " + file.getName() + "\n");
			return new PrintWriter(new FileOutputStream(file));
		} catch (IOException e) {
			throw new TaraException("Error during plugin generation");
		}
	}

	protected static void printResult(Process process) throws InterruptedException {
		StreamWrapper error, output;
		error = StreamWrapper.getStreamWrapper(process.getErrorStream(), "ERROR");
		output = StreamWrapper.getStreamWrapper(process.getInputStream(), "OUTPUT");
		error.start();
		output.start();
		error.join(3000);
		output.join(3000);
		if (!output.getMessage().equals(""))
			System.out.println("Output:\n" + output.getMessage());
		if (!error.getMessage().equals(""))
			System.err.println("Error: " + error.getMessage());
	}


	protected static String newTemplate(String project, String template) {
		return template.replace("-", project).replace("/tara/", "/" + project + "/");
	}

	protected static void out(String s) {
		System.out.print(s);
	}
}
