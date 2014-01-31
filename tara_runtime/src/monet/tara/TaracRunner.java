package monet.tara;

import monet.tara.compiler.rt.TaraRtConstants;

import java.io.File;

public class TaracRunner {

	private TaracRunner() {

	}

	public static void main(String[] args) {

		if (args.length != 2) {
			System.err.println("There is no arguments for tara compiler");
			System.exit(1);
		}
		final boolean pluginGeneration = "--gen-plugin".equals(args[0]);
		final File argsFile = new File(args[1]);
		if (!argsFile.exists()) {
			System.err.println("Arguments file for Tara compiler not found");
			System.exit(1);
		}

		try {
			Class.forName("monet.tara.compiler.core.CompilationUnit");
		} catch (Throwable e) {
			System.err.println(TaraRtConstants.NO_TARA);
			System.exit(1);
		}
		try {
			TaraCompilerRunner.runTaraCompiler(argsFile, pluginGeneration);
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
