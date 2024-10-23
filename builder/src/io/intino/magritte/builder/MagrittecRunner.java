package io.intino.magritte.builder;

import io.intino.builder.BuildConstants;
import io.intino.magritte.builder.compiler.operations.LayerGenerationOperation;
import io.intino.tara.builder.TaraCompilerRunner;
import io.intino.tara.builder.core.errorcollection.TaraException;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.intino.magritte.builder.Check.checkArgumentsNumber;
import static io.intino.magritte.builder.Check.checkConfigurationFile;

public class MagrittecRunner {
	static final Logger LOG = Logger.getGlobal();

	private MagrittecRunner() {
	}

	public static void main(String[] args) {
		try {
			final boolean verbose = args.length != 2 || Boolean.parseBoolean(args[1]);
			if (verbose) System.out.println(BuildConstants.PRESENTABLE_MESSAGE + "Starting compiling");
			if (checkArgumentsNumber(args) || !checkConfigurationFile(args[0]))
				throw new TaraException("Error finding args file");
			TaraCompilerRunner runner = new TaraCompilerRunner(verbose, List.of(LayerGenerationOperation.class));
			runner.run(new File(args[0]));
		} catch (Exception e) {
			LOG.log(Level.SEVERE, e.getMessage() == null ? e.getStackTrace()[0].toString() : e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}
}