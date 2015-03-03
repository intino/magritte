package siani.tara.compiler.codegeneration;

import siani.tara.compiler.core.errorcollection.StreamWrapper;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class CodeGenerator {
	private static final Logger LOG = Logger.getLogger(CodeGenerator.class.getName());

	protected CodeGenerator() {
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
			LOG.log(Level.INFO, output.getMessage(), error);
		if (error.getMessage() != null && !"".equals(error.getMessage()) && !error.getMessage().startsWith("Note"))
			LOG.log(Level.SEVERE, error.getMessage(), error);
		return error.getMessage();
	}

	protected static void out(String s) {
		LOG.info(s);
	}
}
