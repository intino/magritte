package siani.tara.compiler.codegeneration.intellij;

import siani.tara.compiler.codegeneration.PathManager;
import siani.tara.compiler.core.errorcollection.StreamWrapper;
import siani.tara.compiler.core.errorcollection.TaraException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public abstract class CodeGenerator {
	private static final Logger LOG = Logger.getLogger(CodeGenerator.class.getName());

	protected CodeGenerator() {
	}

	protected static PrintWriter getOutWriter(File file) throws TaraException {
		try {
			file.getParentFile().mkdirs();
			file.createNewFile();
			return new PrintWriter(new FileOutputStream(file));
		} catch (IOException e) {
			throw new TaraException("Error during plugin generation");
		}
	}

	protected static String printResult(Process process) throws InterruptedException {
		StreamWrapper error, output;
		error = StreamWrapper.getStreamWrapper(process.getErrorStream(), "ERROR");
		output = StreamWrapper.getStreamWrapper(process.getInputStream(), "OUTPUT");
		error.start();
		output.start();
		error.join(3000);
		output.join(3000);
		if (output.getMessage() != null && !"".equals(output.getMessage()))
			LOG.info(output.getMessage());
		if (error.getMessage() != null && !"".equals(error.getMessage()))
			if (!error.getMessage().startsWith("Note"))
				LOG.severe(error.getMessage());
		return error.getMessage();
	}

	protected static String newTemplate(String project, String template) {
		String sep = PathManager.SEP;
		return template.replace("-", project).replace("/tara/", sep + project + sep).replace("tpl/", "").replaceAll("/", "\\" + sep);
	}

	protected static void out(String s) {
		LOG.info(s);
	}
}
