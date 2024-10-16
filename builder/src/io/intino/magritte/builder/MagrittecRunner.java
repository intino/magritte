package io.intino.magritte.builder;

import io.intino.builder.BuildConstants;
import io.intino.magritte.builder.compiler.operations.LayerGenerationOperation;
import io.intino.tara.builder.TaraCompilerRunner;
import io.intino.tara.builder.core.errorcollection.TaraException;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.intino.builder.BuildConstants.MESSAGES_END;
import static io.intino.builder.BuildConstants.MESSAGES_START;

public class MagrittecRunner {
	private static final Logger LOG = Logger.getGlobal();

	private MagrittecRunner() {
	}

	public static void main(String[] args) {
		final boolean verbose = args.length != 2 || Boolean.parseBoolean(args[1]);
		if (verbose) System.out.println(BuildConstants.PRESENTABLE_MESSAGE + "Starting compiling");
		try {
			File argsFile;
			if (checkArgumentsNumber(args) || (argsFile = checkConfigurationFile(args[0])) == null)
				throw new TaraException("Error finding args file");
			TaraCompilerRunner runner = new TaraCompilerRunner(verbose, List.of(LayerGenerationOperation.class));
			runner.run(argsFile);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, e.getMessage() == null ? e.getStackTrace()[0].toString() : e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}

	private static File checkConfigurationFile(String arg) {
		final File argsFile = new File(arg);
		if (!argsFile.exists()) {
			LOG.severe(MESSAGES_START + "Arguments file for Tara compiler not found" + MESSAGES_END);
			return null;
		}
		return argsFile;
	}

	private static boolean checkArgumentsNumber(String[] args) {
		if (args.length < 1) {
			LOG.severe(MESSAGES_START + "There is no arguments for tara compiler" + MESSAGES_END);
			return true;
		}
		return false;
	}
}