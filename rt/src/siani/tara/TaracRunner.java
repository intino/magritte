package siani.tara;


import siani.tara.compiler.rt.TaraRtConstants;

import java.io.File;
import java.util.logging.Logger;

public class TaracRunner {

	private static final Logger LOG = Logger.getLogger(TaracRunner.class.getName());

	private TaracRunner() {
	}

	public static void main(String[] args) {
		System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Starting compiling");
		checkArgumentsNumber(args);
		final File argsFile = checkConfigurationFile(args[1]);
		try {
			TaraCompilerRunner.runTaraCompiler(argsFile, isPluginGeneration(args[0]));
		} catch (Exception e) {
			LOG.severe("Exception in TaraCRunner: " + e.toString());
			System.exit(1);
		}
	}

	private static File checkConfigurationFile(String arg) {
		final File argsFile = new File(arg);
		if (!argsFile.exists()) {
			System.err.println("%%mArguments file for Tara compiler not found/%m");
			System.exit(1);
		}
		return argsFile;
	}

	private static boolean isPluginGeneration(String arg) {
		return "--gen-plugin".equals(arg);
	}

	private static void checkArgumentsNumber(String[] args) {
		if (args.length != 2) {
			System.err.println("%%mThere is no arguments for tara compiler/%m");
			System.exit(1);
		}
	}
}
