package monet.tara;

import java.io.File;

public class TaracRunner {

	private TaracRunner() {

	}

	public static void main(String[] args) {

		checkArgumentsNumber(args);
		final File argsFile = checkConfigurationFile(args[1]);
		try {
			TaraCompilerRunner.runTaraCompiler(argsFile, isPluginGeneration(args[0]));
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private static File checkConfigurationFile(String arg) {
		final File argsFile = new File(arg);
		if (!argsFile.exists()) {
			System.err.println("Arguments file for Tara compiler not found");
			System.exit(1);
		}
		return argsFile;
	}

	private static boolean isPluginGeneration(String arg) {
		return "--gen-plugin".equals(arg);
	}

	private static void checkArgumentsNumber(String[] args) {
		if (args.length != 2) {
			System.err.println("There is no arguments for tara compiler");
			System.exit(1);
		}
	}
}
