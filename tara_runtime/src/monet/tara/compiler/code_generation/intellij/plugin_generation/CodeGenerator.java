package monet.tara.compiler.code_generation.intellij.plugin_generation;

import monet.tara.compiler.core.error_collection.TaraException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class CodeGenerator {


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


	protected static String newTemplate(String project, String template) {
		return template.replace("-", project).replace("/tara/", "/" + project + "/");
	}

	protected static void out(String s) {
		System.out.print(s);
	}
}
