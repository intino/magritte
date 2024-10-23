package io.intino.magritte.builder;

import java.io.File;

import static io.intino.builder.BuildConstants.MESSAGES_END;
import static io.intino.builder.BuildConstants.MESSAGES_START;
import static io.intino.magritte.builder.MagrittecRunner.LOG;

public class Check {

	static boolean checkConfigurationFile(String arg) {
		final File argsFile = new File(arg);
		if (!argsFile.exists()) {
			LOG.severe(MESSAGES_START + "Arguments file for Tara compiler not found" + MESSAGES_END);
			return false;
		}
		return true;
	}

	static boolean checkArgumentsNumber(String[] args) {
		if (args.length < 1) {
			LOG.severe(MESSAGES_START + "There is no arguments for tara compiler" + MESSAGES_END);
			return true;
		}
		return false;
	}
}
