package siani.tara;


import siani.tara.compiler.rt.TaraRtConstants;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaracRunner {

	private static final Logger LOG = Logger.getLogger(TaracRunner.class.getName());

	private TaracRunner() {
	}

	public static void main(String[] args) {
		System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Starting compiling");
		try {
			File argsFile;
			if (checkArgumentsNumber(args) || (argsFile = checkConfigurationFile(args[1])) == null)
				throw new Exception("Error finding agrs file");
			TaraCompilerRunner.runTaraCompiler(argsFile, isPluginGeneration(args[0]));
		} catch (Exception e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
			System.exit(1);
		}
	}

	private static File checkConfigurationFile(String arg) {
		final File argsFile = new File(arg);
		if (!argsFile.exists()) {
			System.err.println("%%mArguments file for Tara compiler not found/%m");
			return null;
		}
		return argsFile;
	}

	private static boolean isPluginGeneration(String arg) {
		return "--gen-plugin".equals(arg);
	}

	private static boolean checkArgumentsNumber(String[] args) {
		if (args.length != 2) {
			System.err.println("%%mThere is no arguments for tara compiler/%m");
			return true;
		}
		return false;
	}
}
