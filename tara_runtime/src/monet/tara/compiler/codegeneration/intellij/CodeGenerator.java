package monet.tara.compiler.codegeneration.intellij;

import monet.tara.compiler.codegeneration.PathManager;
import monet.tara.compiler.core.errorcollection.StreamWrapper;
import monet.tara.compiler.core.errorcollection.TaraException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public abstract class CodeGenerator {
	private static final Logger LOG = Logger.getLogger(CodeGenerator.class.getName());

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
			LOG.info("Output:\n" + output.getMessage());
		if (!error.getMessage().equals(""))
			LOG.severe("\n\n" + error.getMessage());
	}

	protected static String newTemplate(String project, String template) {
		String SEP = PathManager.SEP;
		return template.replace("-", project).replace("tara" + SEP, SEP + project + SEP).replace("tpl" + SEP, "");
	}

	protected static void out(String s) {
		LOG.info(s);
	}

}
